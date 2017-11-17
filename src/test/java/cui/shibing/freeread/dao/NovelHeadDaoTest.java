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
	private NovelHeadDao novelHeadMapper;
	
	/*
	 * 全条件满足
	 * **/
	@Test
	public void testInsertNovelHead01() {
		NovelHead novelHead = new NovelHead();
		novelHead.setNovelId(UUID.randomUUID().toString());
		novelHead.setNovelName("novel1");
		novelHead.setNovelContentTableName("abcd");
		novelHead.setNovelClassId1(1);
		novelHead.setNovelStatus(12);
		novelHead.setNovelChapterNum(123);
		assertTrue(novelHeadMapper.insertNovelHead(novelHead) == 1);
	}
	
	/*
	 * 对应的NovelHead不存在
	 * **/
	@Test
	public void testSelectNovelHeadByNovelName01() {
		executeSqlScript("novel_head_insert-1.sql",false);
		Pageable pageable = new PageRequest(1,5);
		List<NovelHead> novelHeads = novelHeadMapper.selectNovelHeadByNovelName("1", pageable);
		assertTrue(novelHeads.size()==0);
	}
	
	/*
	 * 对应的NovelHead存在
	 * **/
	@Test
	public void testSelectNovelHeadByNovelName02() {
		executeSqlScript("novel_head_insert-1.sql",false);
		Pageable pageable = new PageRequest(1,5);
		List<NovelHead> novelHeads = novelHeadMapper.selectNovelHeadByNovelName("zhe tian", pageable);
		assertTrue(novelHeads.size()==1);
		assertTrue(novelHeads.get(0).getNovelName().equals("zhe tian"));
	}
	
	/*
	 * 对应的NovleHead不存在
	 * **/
	@Test
	public void testSelectNovelHeadByNovelClassName01() {
		executeSqlScript("novel_head_insert-1.sql",false);
		Pageable pageable = new PageRequest(1,5);
		List<NovelHead> novelHeads = novelHeadMapper.selectNovelHeadByNovelClassName("six", pageable);
		assertTrue(novelHeads.size()==0);
	}
	
	/*
	 * 对应的NovleHead存在
	 * **/
	@Test
	public void testSelectNovelHeadByNovelClassName02() {
		executeSqlScript("novel_head_insert-1.sql",false);
		Pageable pageable = new PageRequest(1,5);
		List<NovelHead> novelHeads = novelHeadMapper.selectNovelHeadByNovelClassName("one", pageable);
		assertTrue(novelHeads.size()==1);
		assertTrue(novelHeads.get(0).getNovelClassId1() == 1);
	}
	
	/*
	 * 对应的NovleHead存在
	 * **/
	@Test
	public void testSelectNovelHeadByNovelClassName03() {
		executeSqlScript("novel_head_insert-1.sql",false);
		Pageable pageable = new PageRequest(1,5);
		List<NovelHead> novelHeads = novelHeadMapper.selectNovelHeadByNovelClassName("two", pageable);
		assertTrue(novelHeads.size()==1);
		assertTrue(novelHeads.get(0).getNovelClassId2() == 2);
	}
	
	/*
	 * 对应的NovleHead存在
	 * **/
	@Test
	public void testSelectNovelHeadByNovelClassName04() {
		executeSqlScript("novel_head_insert-1.sql",false);
		Pageable pageable = new PageRequest(1,5);
		List<NovelHead> novelHeads = novelHeadMapper.selectNovelHeadByNovelClassName("three", pageable);
		assertTrue(novelHeads.size()==1);
		assertTrue(novelHeads.get(0).getNovelClassId3() == 3);
	}
	
	@Test
	public void testSelectNovelHeadByPopularity01() {
		Pageable pageable = new PageRequest(1,3);
		List<NovelHead> novelHeads = novelHeadMapper.selectNovelHeadByPopularity(pageable);
		assertTrue(novelHeads.size()==5);
		assertTrue(novelHeads.get(0).getNovelPopularity()==128);
		assertTrue(novelHeads.get(1).getNovelPopularity()==127);
		assertTrue(novelHeads.get(2).getNovelPopularity()==126);
		assertTrue(novelHeads.get(3).getNovelPopularity()==125);
		assertTrue(novelHeads.get(4).getNovelPopularity()==124);
		Pageable pageable1 = new PageRequest(2,5);
		List<NovelHead> novelHeads1 = novelHeadMapper.selectNovelHeadByPopularity(pageable1);
		assertTrue(novelHeads1.size()==1);
		assertTrue(novelHeads1.get(0).getNovelPopularity()==123);
	}
	
}
