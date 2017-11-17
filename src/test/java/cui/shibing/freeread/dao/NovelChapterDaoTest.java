package cui.shibing.freeread.dao;

import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cui.shibing.freeread.model.NovelChapter;

public class NovelChapterDaoTest extends CustomDaoTest{
	@Autowired
	private NovelChapterDao novelContentDao;
	
	/*
	 * 插入成功
	 * **/
	@Test
	public void testInsertNovelContent01() {
		NovelChapter novelContent = new NovelChapter();
		novelContent.setNovelId(UUID.randomUUID().toString());
		novelContent.setNovelChapterName(UUID.randomUUID().toString());
		novelContent.setNovelChapterContent(UUID.randomUUID().toString());
		novelContent.setNovelChapterIndex(1);
		assertTrue(novelContentDao.insertNovelChapter(novelContent)==1);
	}
	
}
