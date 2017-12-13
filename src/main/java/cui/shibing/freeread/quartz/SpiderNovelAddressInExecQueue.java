/**
* Copyright © 1998-2017, Glodon Inc. All Rights Reserved.
*/
package cui.shibing.freeread.quartz;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 
 * <p>
 * 爬取小说排行榜中的小说url,
 * <br/>
 * 存入执行爬取小说章节的集合set中,
 * <br/>
 * 该集合在redis中存着
 * </p>
 * @author luoyf
 * @since jdk1.8
 * 2017年12月11日
 *  
 */
public class SpiderNovelAddressInExecQueue {
	
	private static final Logger log = LoggerFactory.getLogger(SpiderNovelAddressInExecQueue.class);
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	private static final String server_url = "http://www.biqukan.com/";// 服务器地址
	private static final String address_url = server_url + "paihangbang/";// 小说排行榜地址
	private static final String charset = "gbk";// 网站中页面的编码
	
	private static final String spider_novel_address_set_redis = "spider_novel_address_set_redis";
	
	public void exec() {
		log.debug(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ",开始将小说爬取地址,录入redis队列中==========");
		SetOperations<String, String> addressSetOps = redisTemplate.opsForSet();
		String catalogHtml = "";// 获取目录的html源码
		HttpPost hp = new HttpPost(address_url);
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
//		获取页面中包含所有小说的div
		Elements rootDiv = document.getElementsByClass("wrap rank");
//		获取包含各个小说目录的所有a标签
		Elements aLabel = rootDiv.select("a");
		List<String> aLabelHrefs = aLabel.eachAttr("href");
		for (String href : aLabelHrefs) {
			addressSetOps.add(spider_novel_address_set_redis, href);
		}
	}
}
