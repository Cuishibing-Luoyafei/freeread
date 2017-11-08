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
	private NovelHeadDao novelHeadMapper;

	public Page<NovelHead> searchByNovelClass(String className,
			Pageable pageable) {
		List<NovelHead> result = null;

		if (StringUtils.isEmpty(className) || pageable == null) {
			result = Collections.emptyList();
		} else {
			result = novelHeadMapper.selectNovelHeadByNovelClassName(className, pageable);
		}
		return new PageImpl<NovelHead>(result);
	}
	
	/*
	 * 缓存小说排行榜
	 * key:searchByPopularity_pageNumber_pageSize
	 * **/
	@Cacheable(value="default",cacheManager="cacheManager",key="#root.methodName +'_'+ #pageable.pageNumber + '_' + #pageable.pageSize")
	public Page<NovelHead> searchByPopularity(Pageable pageable) {
		List<NovelHead> result = null;
		long count = 0;
		if (pageable == null) {
			result = Collections.emptyList();
		} else {
			result = novelHeadMapper.selectNovelHeadByPopularity(pageable);
			count = searchNovelHeadCount();
		}
		return new PageImpl<NovelHead>(result,pageable,count);
	}

	public Page<NovelHead> searchByNovelName(String novelName, Pageable pageable) {
		List<NovelHead> result = null;
		long count = 0;
		if (StringUtils.isEmpty(novelName) || pageable == null) {
			result = Collections.emptyList();
		} else {
			result = novelHeadMapper.selectNovelHeadByNovelName(novelName, pageable);
			count = searchCountByNovelName(novelName);
		}
		return new PageImpl<NovelHead>(result,pageable,count);
	}

	public NovelHead searchByNovelId(String novelId) {
		if(StringUtils.isEmpty(novelId))
			return null;
		return novelHeadMapper.selectNovelHeadByNovelId(novelId);
	}

	public long searchCountByNovelName(String novelName) {
		return novelHeadMapper.selectNovelHeadCountByNovelName(novelName);
	}

	public long searchNovelHeadCount() {
		return novelHeadMapper.selectNovelHeadCountByPopularity();
	}
}
