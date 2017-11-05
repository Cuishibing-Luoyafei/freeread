package cui.shibing.freeread.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cui.shibing.freeread.dao.NovelContentDao;
import cui.shibing.freeread.model.NovelContent;
import cui.shibing.freeread.service.NovelContentService;
@Service
public class NovelContentServiceImpl implements NovelContentService{

	@Autowired
	private NovelContentDao novelContentDao;
	
	public NovelContent searchByNovelHeadAndChapter(String novelId, Integer chapterIndex) {
		if(StringUtils.isEmpty(novelId) || chapterIndex == null)
			return null;
		return novelContentDao.selectNovleContentByNovelIdAndChapter(novelId, chapterIndex);
	}

	public Page<NovelContent> searchByNovelHeadId(String novelId, Pageable pageable) {
		List<NovelContent> result = null;
		if(StringUtils.isEmpty(novelId) || pageable == null)
			result = Collections.emptyList();
		else
			result = novelContentDao.selectNovelContentByNovelId(novelId, pageable);
		return new PageImpl<NovelContent>(result);
	}

}
 