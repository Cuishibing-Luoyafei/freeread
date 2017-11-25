package cui.shibing.freeread.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cui.shibing.freeread.dao.NovelHeadDao;
import cui.shibing.freeread.model.NovelHead;
import cui.shibing.freeread.service.NovelHeadService;

@Service
public class NovelHeadServiceImpl implements NovelHeadService {

	@Autowired
	private StringRedisTemplate redisTemplate;

	@Autowired
	private NovelHeadDao novelHeadDao;

	@Override
	public Page<NovelHead> searchByNovelClass(String className, Pageable pageable) {
		List<NovelHead> result = null;
		long count = 0;
		if (StringUtils.isEmpty(className) || pageable == null) {
			result = Collections.emptyList();
		} else {
			count = searchCountByClassName(className);
			result = novelHeadDao.selectNovelHeadByNovelClassName(className, pageable);
		}
		return new PageImpl<NovelHead>(result,pageable,count);
	}

	@Override
	@Cacheable(value = "default", cacheManager = "cacheManager", key = "#root.targetClass+'.'+#root.methodName +'.'+ #pageable")
	public Page<NovelHead> searchByPopularity(Pageable pageable) {
		List<NovelHead> result = null;
		long count = 0;
		if (pageable == null) {
			result = Collections.emptyList();
		} else {
			result = novelHeadDao.selectNovelHeadByPopularity(pageable);
			count = searchNovelHeadCount();
		}
		return new PageImpl<NovelHead>(result, pageable, count);
	}

	@Override
	public Page<NovelHead> searchByNovelName(String novelName, Pageable pageable) {
		List<NovelHead> result = null;
		long count = 0;
		if (StringUtils.isEmpty(novelName) || pageable == null) {
			result = Collections.emptyList();
		} else {
			result = novelHeadDao.selectNovelHeadByNovelName(novelName, pageable);
			count = searchCountByNovelName(novelName);
		}
		return new PageImpl<NovelHead>(result, pageable, count);
	}

	@Override
	public NovelHead searchByNovelId(String novelId) {
		if (StringUtils.isEmpty(novelId))
			return null;
		return novelHeadDao.selectNovelHeadByNovelId(novelId);
	}

	@Override
	public boolean addNovelHead(NovelHead head) {
		if(head != null && !StringUtils.isEmpty(head.getNovelId())){
			return novelHeadDao.insertNovelHead(head) == 1;
		}
		return false;
	}

	@Override
	public long searchCountByNovelName(String novelName) {
		return novelHeadDao.selectNovelHeadCountByNovelName(novelName);
	}

	@Override
	public long searchNovelHeadCount() {
		return novelHeadDao.selectNovelHeadCountByPopularity();
	}

	@Override
	public long searchCountByClassName(String className) {
		if (StringUtils.isEmpty(className))
			return 0;
		return novelHeadDao.selectNovelHeadCountByNovelClass(className);
	}
}
