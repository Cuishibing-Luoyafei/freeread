package cui.shibing.freeread.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cui.shibing.freeread.dao.NovelChapterDao;
import cui.shibing.freeread.dto.NovelChapterInfoDto;
import cui.shibing.freeread.model.NovelChapter;
import cui.shibing.freeread.service.NovelChapterService;
import cui.shibing.freeread.tools.MyBeanUtils;

@Service
public class NovelChapterServiceImpl implements NovelChapterService {

	@Autowired
	private NovelChapterDao novelContentDao;

	@Override
	@Cacheable(value = "default", cacheManager = "cacheManager", key = "#root.targetClass+'.'+#root.methodName+'.'+#novelId+'.'+#chapterIndex")
	public NovelChapter searchByNovelHeadAndChapter(String novelId, Integer chapterIndex) {
		if (StringUtils.isEmpty(novelId) || chapterIndex == null)
			return null;
		return novelContentDao.selectNovleChapterByNovelIdAndChapterIndex(novelId, chapterIndex);
	}

	@Override
	public Page<NovelChapter> searchByNovelHeadId(String novelId, Pageable pageable) {
		List<NovelChapter> result = null;
		long count = 0;
		if (StringUtils.isEmpty(novelId) || pageable == null)
			result = Collections.emptyList();
		else {
			count = searchNovelChapterCountByNovelId(novelId);
			result = novelContentDao.selectNovelChapterByNovelId(novelId, pageable);
		}
		return new PageImpl<NovelChapter>(result, pageable, count);
	}

	@Cacheable(value = "default", cacheManager = "cacheManager", key = "#root.targetClass+'.'+#root.methodName+'.'+#novelId")
	@Override
	public long searchNovelChapterCountByNovelId(String novelId) {
		if (!StringUtils.isEmpty(novelId)) {
			return novelContentDao.selectNovelChapterCountByNovelId(novelId);
		}
		return 0;
	}

	@Cacheable(value = "default", cacheManager = "cacheManager", key = "#root.targetClass+'.'+#root.methodName+'.'+#novelId+'.'+#pageable")
	@Override
	public Page<NovelChapterInfoDto> searchChapterInfoByNovelId(String novelId, Pageable pageable) {
		List<NovelChapter> result = null;
		long count = 0;
		if (StringUtils.isEmpty(novelId) || pageable == null)
			result = Collections.emptyList();
		else {
			count = searchNovelChapterCountByNovelId(novelId);
			result = novelContentDao.selectNovelChapterInfoByNovelId(novelId, pageable);
		}
		List<NovelChapterInfoDto> chapterInfos = new ArrayList<NovelChapterInfoDto>(result.size());
		MyBeanUtils.copyListProperties(result, chapterInfos, NovelChapterInfoDto.class);
		return new PageImpl<NovelChapterInfoDto>(chapterInfos, pageable, count);
	}

}
