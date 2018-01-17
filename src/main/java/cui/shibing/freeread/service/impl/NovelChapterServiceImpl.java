package cui.shibing.freeread.service.impl;

import cui.shibing.freeread.common.MyBeanUtils;
import cui.shibing.freeread.dao.NovelChapterDao;
import cui.shibing.freeread.dto.NovelChapterInfoDto;
import cui.shibing.freeread.model.NovelChapter;
import cui.shibing.freeread.model.SecretNovel;
import cui.shibing.freeread.service.NovelChapterService;
import cui.shibing.freeread.service.SecretNovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class NovelChapterServiceImpl implements NovelChapterService {

    @Autowired
    private NovelChapterDao novelChapterDao;

    @Autowired
    private SecretNovelService secretNovelService;

    /*@Cacheable(value = "default", cacheManager = "cacheManager", key = "#root.targetClass+'.'+#root.methodName+'.'+#novelId+'.'+#chapterIndex",unless = "#result != null")*/
    @Override
    public NovelChapter getChapterByNovelIdAndIndex(String novelId, Integer chapterIndex) {
        if (StringUtils.isEmpty(novelId) || chapterIndex == null)
            return null;
        return novelChapterDao.selectNovelChapterByNovelIdAndChapterIndex(novelId, chapterIndex);
    }

    /*@Cacheable(value = "default", cacheManager = "cacheManager", key = "#root.targetClass+'.'+#root.methodName+'.'+#novelId+'.'+#chapterIndex",unless = "#result != null")*/
    @Override
    public NovelChapter getChapterByNovelIdAndIndex(String novelId, Integer chapterIndex, String userName) {
        NovelChapter novelChapter = getChapterByNovelIdAndIndex(novelId, chapterIndex);
        if (novelChapter != null && !StringUtils.isEmpty(userName)) {
            //TODO:应该把设置最后阅读章节的操作放到一个计划任务队列中
            List<SecretNovel> secretNovels = secretNovelService.getSecretNovels(userName);
            for (SecretNovel secretNovel : secretNovels) {
                if (secretNovel.getNovelId().equals(novelId) && !secretNovel.getLastReadChapter().equals(chapterIndex)) {
                    secretNovel.setLastReadChapter(chapterIndex);
                    secretNovelService.updateLastReadIndex(userName, novelId, chapterIndex);
                    break;
                }
            }
        }
        return novelChapter;
    }

    @Override
    public Page<NovelChapter> searchByNovelHeadId(String novelId, Pageable pageable) {
        List<NovelChapter> result = null;
        long count = 0;
        if (StringUtils.isEmpty(novelId) || pageable == null)
            result = Collections.emptyList();
        else {
            count = searchNovelChapterCountByNovelId(novelId);
            result = novelChapterDao.selectNovelChapterByNovelId(novelId, pageable);
        }
        return new PageImpl<NovelChapter>(result, pageable, count);
    }

    /*@Cacheable(value = "default", cacheManager = "cacheManager", key = "#root.targetClass+'.'+#root.methodName+'.'+#novelId")*/
    @Override
    public long searchNovelChapterCountByNovelId(String novelId) {
        if (!StringUtils.isEmpty(novelId)) {
            return novelChapterDao.selectNovelChapterCountByNovelId(novelId);
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
            result = novelChapterDao.selectNovelChapterInfoByNovelId(novelId, pageable);
        }
        List<NovelChapterInfoDto> chapterInfos = new ArrayList<NovelChapterInfoDto>(result.size());
        MyBeanUtils.copyListProperties(result, chapterInfos, NovelChapterInfoDto.class);
        return new PageImpl<NovelChapterInfoDto>(chapterInfos, pageable, count);
    }

    @Override
    @Transactional
    public boolean addNovelChapter(NovelChapter novelChapter) {
        if (novelChapter != null && !StringUtils.isEmpty(novelChapter.getNovelId()) &&
                !StringUtils.isEmpty(novelChapter.getNovelChapterIndex())) {
            return novelChapterDao.insertNovelChapter(novelChapter) == 1;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean removeNovelChapter(String novelId) {
        if (!StringUtils.isEmpty(novelId)) {
            return novelChapterDao.deleteNovelChapterByNovelId(novelId) > 0;
        }
        return false;
    }
}
