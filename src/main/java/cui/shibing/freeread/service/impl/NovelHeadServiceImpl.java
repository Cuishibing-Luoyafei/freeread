package cui.shibing.freeread.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cui.shibing.freeread.dao.NovelHeadDao;
import cui.shibing.freeread.model.NovelHead;
import cui.shibing.freeread.service.NovelHeadService;

@Service
public class NovelHeadServiceImpl implements NovelHeadService {

	@Autowired
	private NovelHeadDao novelHeadDao;

	public Page<NovelHead> searchByNovelClass(String className,
			Pageable pageable) {
		//TODO:没有查询总量
		List<NovelHead> result = null;

		if (StringUtils.isEmpty(className) || pageable == null) {
			result = Collections.emptyList();
		} else {
			result = novelHeadDao.selectNovelHeadByNovelClassName(className, pageable);
		}
		return new PageImpl<NovelHead>(result);
	}
	
	@Cacheable(value="default",cacheManager="cacheManager",key="#root.targetClass+'.'+#root.methodName +'.'+ #pageable")
	public Page<NovelHead> searchByPopularity(Pageable pageable) {
		List<NovelHead> result = null;
		long count = 0;
		if (pageable == null) {
			result = Collections.emptyList();
		} else {
			result = novelHeadDao.selectNovelHeadByPopularity(pageable);
			count = searchNovelHeadCount();
		}
		if(result.isEmpty())
			return new PageImpl<NovelHead>(result);
		return new PageImpl<NovelHead>(result,pageable,count);
	}

	public Page<NovelHead> searchByNovelName(String novelName, Pageable pageable) {
		List<NovelHead> result = null;
		long count = 0;
		if (StringUtils.isEmpty(novelName) || pageable == null) {
			result = Collections.emptyList();
		} else {
			result = novelHeadDao.selectNovelHeadByNovelName(novelName, pageable);
			count = searchCountByNovelName(novelName);
		}
		if(result.isEmpty())
			return new PageImpl<NovelHead>(result);
		return new PageImpl<NovelHead>(result,pageable,count);
	}

	public NovelHead searchByNovelId(String novelId) {
		if(StringUtils.isEmpty(novelId))
			return null;
		return novelHeadDao.selectNovelHeadByNovelId(novelId);
	}

	public long searchCountByNovelName(String novelName) {
		return novelHeadDao.selectNovelHeadCountByNovelName(novelName);
	}

	public long searchNovelHeadCount() {
		return novelHeadDao.selectNovelHeadCountByPopularity();
	}
}
