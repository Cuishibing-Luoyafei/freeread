package cui.shibing.freeread.dao;

import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cui.shibing.freeread.CustomDaoTest;
import cui.shibing.freeread.model.NovelContent;

public class NovelContentDaoTest extends CustomDaoTest{
	@Autowired
	private NovelContentDao novelContentDao;
	
	/*
	 * 插入成功
	 * **/
	@Test
	public void testInsertNovelContent01() {
		NovelContent novelContent = new NovelContent();
		novelContent.setNovelId(UUID.randomUUID().toString());
		novelContent.setNovelChapterName(UUID.randomUUID().toString());
		novelContent.setNovelChapterContent(UUID.randomUUID().toString());
		novelContent.setNovelChapterIndex(1);
		assertTrue(novelContentDao.insertNovelContent(novelContent)==1);
	}
	
}
