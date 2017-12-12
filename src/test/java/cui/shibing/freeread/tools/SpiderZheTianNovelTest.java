/**
* Copyright © 1998-2017, Glodon Inc. All Rights Reserved.
*/
package cui.shibing.freeread.tools;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cui.shibing.freeread.dao.CustomDaoTest;
import cui.shibing.freeread.dao.NovelChapterDao;
import cui.shibing.freeread.dao.NovelHeadDao;
import cui.shibing.freeread.model.NovelChapter;
import cui.shibing.freeread.model.NovelHead;

/**
 *
 * 此处填写类简介
 * <p>
 * 此处填写类说明
 * </p>
 * @author luoyf
 * @since jdk1.6
 * 2017年11月24日
 *
 */
public class SpiderZheTianNovelTest extends CustomDaoTest {

	@Autowired
	private NovelHeadDao novelHeadDao;
	@Autowired
	private NovelChapterDao novelChapterDao;

	private static final String server_url = "http://www.biqukan.com/";// 服务器地址
	private static final String zhetian_url = server_url + "3_3042/";// 遮天小说概览地址
	private static final String charset = "gbk";// 网站中页面的编码
	private static int skipChapter = 0;// 目录页面进行录取时，要跳过的无效章节数
	@Test
	public void saveNovelHead() {
		String catalogHtml = "";// 获取目录的html源码
		HttpPost hp = new HttpPost(zhetian_url);
		try (
				CloseableHttpClient httpClient = HttpClients.createDefault();
				CloseableHttpResponse httpResponse = httpClient.execute(hp);
		) {
			catalogHtml = EntityUtils.toString(httpResponse.getEntity(), charset);
		} catch (IOException e) {
			e.printStackTrace();
		}
//		System.out.println(catalogHtml);
		Document document = Jsoup.parse(catalogHtml);
//		获取小说简介
		String novelIntro = document.getElementsByClass("intro").get(0).text();
//		小说名称
		String novelName = document.selectFirst("meta[property='og:novel:book_name']").attr("content");
//		作者
		String novelAuthor = document.selectFirst("meta[property='og:novel:author']").attr("content");
//		类型
//		String novelCategory = document.selectFirst("meta[property='og:novel:category']").attr("content");
//		小说状态
//		String novelStatus = document.selectFirst("meta[property='og:novel:status']").attr("content");
//		创建小说概要
		NovelHead nh = new NovelHead();
		nh.setNovelId(UUID.randomUUID().toString().replaceAll("-", ""));
		nh.setNovelAuthor(novelAuthor);
		nh.setNovelDesc(novelIntro);
		nh.setNovelName(novelName);
		nh.setNovelStatus(2);
		nh.setNovelClassId1(1);
		nh.setNovelClassId2(1);
		nh.setNovelClassId3(1);
		nh.setNovelContentTableName("");
//		获取页面中包含所有章节目录的div
		Elements rootDiv = document.getElementsByClass("listmain");
//		获取目录下的所有a标签
		Elements aLabel = rootDiv.select("a");
//		小说章节数
		nh.setNovelChapterNum(aLabel.size());
		if(novelHeadDao.insertNovelHead(nh) == 0) {
			System.out.println("保存小说" + nh + "时，失败！");
			return;
		} else {
			System.out.println("目录成功保存");
		}
		List<String> aLabelHrefs = aLabel.eachAttr("href");
		int i = skipChapter;
		for(String aLabelHref : aLabelHrefs.subList(skipChapter, aLabelHrefs.size())) {
			getItemNovalByUrl(server_url + aLabelHref, nh.getNovelId(), ++i);
		}
	}

	/**
	 * 通过传入的具体章节的url，获取对应的小说html页面，并将其保存入章节表中
	 * @param itemUrl 小说章节的url
	 * @param novelId 小说id
	 * @param index 小说章节标记
	 * @return
	 */
	private void getItemNovalByUrl(String itemUrl, String novelId, Integer index) {
		HttpPost hp = new HttpPost(itemUrl);
//		小说章节内容
		String itemNovalContent = "";
		try (
				CloseableHttpClient httpClient = HttpClients.createDefault();
				CloseableHttpResponse httpResponse = httpClient.execute(hp);
		) {
			itemNovalContent = EntityUtils.toString(httpResponse.getEntity(), charset);
		}catch (IOException e) {
			e.printStackTrace();
		}
		Document chapterDocument = Jsoup.parse(itemNovalContent);
//		小说章节名
		Elements h1Tags = chapterDocument.getElementsByTag("h1");
		String title = "暂无章节名";
		if(h1Tags.size() > 0) {
			title = h1Tags.get(0).text();
		}
		Element chapterContent = chapterDocument.getElementById("content");
//		章节页面中的小说内容
		String content = "暂无内容";
		if(chapterContent != null) {
			content = chapterContent.html();
		}
		NovelChapter nc = new NovelChapter();
		nc.setNovelId(UUID.randomUUID().toString().replaceAll("-", ""));
		nc.setNovelId(novelId);
		nc.setNovelChapterName(title);
		nc.setNovelChapterContent(content);
		nc.setNovelChapterIndex(index);

		if(novelChapterDao.insertNovelChapter(nc) == 0) {
			System.out.println("保存" + nc + "时，失败！");
		} else {
			System.out.println("章节保存成功:" + title);
		}
	}
}
