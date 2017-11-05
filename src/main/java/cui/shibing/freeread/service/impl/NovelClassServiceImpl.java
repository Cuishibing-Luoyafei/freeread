package cui.shibing.freeread.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cui.shibing.freeread.dao.NovelClassDao;
import cui.shibing.freeread.model.NovelClass;
import cui.shibing.freeread.service.NovelClassService;

@Service
public class NovelClassServiceImpl implements NovelClassService{

	@Autowired
	private NovelClassDao novelClassDao;
	public List<NovelClass> getAllNovelClass() {
		return novelClassDao.selectAllClass();
	}

}
