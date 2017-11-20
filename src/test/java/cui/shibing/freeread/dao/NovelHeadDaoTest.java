package cui.shibing.freeread.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import cui.shibing.freeread.model.NovelHead;

public class NovelHeadDaoTest extends CustomDaoTest{

	@Autowired
	private NovelHeadDao novelHeadDao;
	
	/*
	 * 测试NovelHeadDao.insertNovelHead()
	 * 事前条件:全条件满足
	 * **/
	@Test
	public void testInsertNovelHead01() {
		String novelId = UUID.randomUUID().toString();

		NovelHead novelHead = new NovelHead();

		novelHead.setNovelId(novelId);
		novelHead.setNovelName(UUID.randomUUID().toString());
		novelHead.setNovelContentTableName(UUID.randomUUID().toString());
		novelHead.setNovelClassId1(1);
		novelHead.setNovelStatus(12);
		novelHead.setNovelChapterNum(123);
		assertTrue(novelHeadDao.insertNovelHead(novelHead) == 1);
		NovelHead novelHead1 = novelHeadDao.selectNovelHeadByNovelId(novelId);
		assertTrue(novelHead1!=null);
		assertTrue(novelHead1.getNovelId().equals(novelId));
	}

	/*
	 * 测试NovelHeadDao.insertNovelHead()
	 * 事前条件:约束条件不满足(novelId为null)
	 * **/
	@Test
	public void testInsertNovelHead02() {
		NovelHead novelHead = new NovelHead();

		novelHead.setNovelId(null);
		novelHead.setNovelName(UUID.randomUUID().toString());
		novelHead.setNovelContentTableName(UUID.randomUUID().toString());
		novelHead.setNovelClassId1(1);
		novelHead.setNovelStatus(12);
		novelHead.setNovelChapterNum(123);
		try{
			novelHeadDao.insertNovelHead(novelHead);
		}catch (Exception e) {
			logger.info(e);
		}
	}

	/*
	 * 测试NovelHeadDao.insertNovelHead()
	 * 事前条件:约束条件不满足(novelName为null)
	 * **/
	@Test
	public void testInsertNovelHead03() {
		NovelHead novelHead = new NovelHead();

		novelHead.setNovelId(UUID.randomUUID().toString());
		novelHead.setNovelName(null);
		novelHead.setNovelContentTableName(UUID.randomUUID().toString());
		novelHead.setNovelClassId1(1);
		novelHead.setNovelStatus(12);
		novelHead.setNovelChapterNum(123);
		try{
			novelHeadDao.insertNovelHead(novelHead);
		}catch (Exception e) {
			logger.info(e);
		}
	}

	/*
	 * 测试NovelHeadDao.insertNovelHead()
	 * 事前条件:约束条件不满足(novelContentTableName为null)
	 * **/
	@Test
	public void testInsertNovelHead04() {
		NovelHead novelHead = new NovelHead();

		novelHead.setNovelId(UUID.randomUUID().toString());
		novelHead.setNovelName(UUID.randomUUID().toString());
		novelHead.setNovelContentTableName(null);
		novelHead.setNovelClassId1(1);
		novelHead.setNovelStatus(12);
		novelHead.setNovelChapterNum(123);
		try{
			novelHeadDao.insertNovelHead(novelHead);
		}catch (Exception e) {
			logger.info(e);
		}
	}

	/*
	 * 测试NovelHeadDao.insertNovelHead()
	 * 事前条件:约束条件不满足(novelStatus为null)
	 * **/
	@Test
	public void testInsertNovelHead05() {
		NovelHead novelHead = new NovelHead();

		novelHead.setNovelId(UUID.randomUUID().toString());
		novelHead.setNovelName(UUID.randomUUID().toString());
		novelHead.setNovelContentTableName(UUID.randomUUID().toString());
		novelHead.setNovelClassId1(1);
		novelHead.setNovelStatus(null);
		novelHead.setNovelChapterNum(123);
		try{
			novelHeadDao.insertNovelHead(novelHead);
		}catch (Exception e) {
			logger.info(e);
		}
	}

	/*
	 * 测试NovelHeadDao.insertNovelHead()
	 * 事前条件:约束条件不满足(novelChapterNum为null)
	 * **/
	@Test
	public void testInsertNovelHead06() {
		NovelHead novelHead = new NovelHead();

		novelHead.setNovelId(UUID.randomUUID().toString());
		novelHead.setNovelName(UUID.randomUUID().toString());
		novelHead.setNovelContentTableName(UUID.randomUUID().toString());
		novelHead.setNovelClassId1(1);
		novelHead.setNovelStatus(1);
		novelHead.setNovelChapterNum(null);
		try{
			novelHeadDao.insertNovelHead(novelHead);
		}catch (Exception e) {
			logger.info(e);
		}
	}
	
	/*
	 * 测试novelHeadDao.selectNovelHeadByNovelName
	 * 事前条件:条件对应的记录不存在
	 * **/
	@Test
	public void testSelectNovelHeadByNovelName01() {
		executeSqlScript("sql/testSelectNovelHeadByNovelName01.sql",false);
		Pageable pageable = new PageRequest(0,5);
		List<NovelHead> novelHeads = novelHeadDao.selectNovelHeadByNovelName("1", pageable);
		assertTrue(novelHeads.size()==0);
	}

	/*
	 * 测试novelHeadDao.selectNovelHeadByNovelName
	 * 事前条件:条件对应的记录存在
	 * **/
	@Test
	public void testSelectNovelHeadByNovelName02() {
		executeSqlScript("sql/testSelectNovelHeadByNovelName02.sql",false);
		Pageable pageable = new PageRequest(0,5);
		List<NovelHead> novelHeads = novelHeadDao.selectNovelHeadByNovelName("zhe tian", pageable);
		assertTrue(novelHeads.size()==1);
		assertTrue(novelHeads.get(0).getNovelName().equals("zhe tian"));
	}
	
	/*
	 * 测试novelHeadDao.selectNovelHeadByNovelClassName()
	 * 条件对应的记录不存在
	 * **/
	@Test
	public void testSelectNovelHeadByNovelClassName01() {
		executeSqlScript("sql/testSelectNovelHeadByNovelClassName01.sql",false);
		Pageable pageable = new PageRequest(0,5);
		List<NovelHead> novelHeads = novelHeadDao.selectNovelHeadByNovelClassName("six", pageable);
		assertTrue(novelHeads.size()==0);
	}

	/*
	 * 测试novelHeadDao.selectNovelHeadByNovelClassName()
	 * 条件对应的记录存在
	 * **/
	@Test
	public void testSelectNovelHeadByNovelClassName02() {
		executeSqlScript("sql/testSelectNovelHeadByNovelClassName02.sql",false);
		Pageable pageable = new PageRequest(0,5);
		List<NovelHead> novelHeads = novelHeadDao.selectNovelHeadByNovelClassName("修真", pageable);
		assertTrue(novelHeads.size()==1);
		assertTrue(novelHeads.get(0).getNovelClassId1() == 1);
	}

	/*
	 * 测试novelHeadDao.selectNovelHeadByNovelClassName()
	 * 条件对应的记录存在且有多条
	 * **/
	@Test
	public void testSelectNovelHeadByNovelClassName03() {
		executeSqlScript("sql/testSelectNovelHeadByNovelClassName03.sql",false);
		Pageable pageable = new PageRequest(0,5);
		List<NovelHead> novelHeads = novelHeadDao.selectNovelHeadByNovelClassName("修真", pageable);
		assertTrue(novelHeads.size()==2);
	}
	
	@Test
	public void testSelectNovelHeadByPopularity01() {
		executeSqlScript("sql/testSelectNovelHeadByPopularity01.sql",false);
		Pageable pageable = new PageRequest(0,5);
		List<NovelHead> novelHeads = novelHeadDao.selectNovelHeadByPopularity(pageable);
		assertTrue(novelHeads.size()==5);
		assertTrue(novelHeads.get(0).getNovelPopularity()==128);
		assertTrue(novelHeads.get(1).getNovelPopularity()==127);
		assertTrue(novelHeads.get(2).getNovelPopularity()==126);
		assertTrue(novelHeads.get(3).getNovelPopularity()==125);
		assertTrue(novelHeads.get(4).getNovelPopularity()==124);
		Pageable pageable1 = new PageRequest(1,5);
		List<NovelHead> novelHeads1 = novelHeadDao.selectNovelHeadByPopularity(pageable1);
		assertTrue(novelHeads1.size()==1);
		assertTrue(novelHeads1.get(0).getNovelPopularity()==123);
	}
	
}
