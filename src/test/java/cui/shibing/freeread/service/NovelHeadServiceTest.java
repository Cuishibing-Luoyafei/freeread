package cui.shibing.freeread.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import cui.shibing.freeread.CustomDaoTest;
import cui.shibing.freeread.CustomPageable;

public class NovelHeadServiceTest extends CustomDaoTest{
	@Autowired
	private NovelHeadService novelHeadService;
	@Test
	public void testSearchByPopularity() {
		
	}
}
