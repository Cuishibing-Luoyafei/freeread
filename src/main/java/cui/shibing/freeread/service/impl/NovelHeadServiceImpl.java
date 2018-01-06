package cui.shibing.freeread.service.impl;

import cui.shibing.freeread.dao.NovelHeadDao;
import cui.shibing.freeread.model.NovelHead;
import cui.shibing.freeread.service.NovelChapterService;
import cui.shibing.freeread.service.NovelHeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

@Service
public class NovelHeadServiceImpl implements NovelHeadService {

    @Autowired
    private NovelHeadDao novelHeadDao;

    @Autowired
    private NovelChapterService novelChapterService;

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
        return new PageImpl<NovelHead>(result, pageable, count);
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
    @Transactional
    public boolean addNovelHead(NovelHead head) {
        if (head != null && !StringUtils.isEmpty(head.getNovelId())) {
            return novelHeadDao.insertNovelHead(head) == 1;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean removeNovelHead(String novelId) {
        if (StringUtils.isEmpty(novelId)) {
            return false;
        }
        NovelHead novelHead = novelHeadDao.selectNovelHeadByNovelId(novelId);
        if (novelHead == null) {
            return false;
        }
        novelHeadDao.deleteNovelHeadByNovelId(novelId);
        novelChapterService.removeNovelChapter(novelId);
        return true;
    }

    @Override
    @Transactional
    public boolean updateNovelHead(NovelHead novelHead) {
        if (novelHead != null) {
            return novelHeadDao.updateNovelHeadByNovelId(novelHead) == 1;
        }
        return false;
    }

    @Override
    public NovelHead searchByNovelId(String novelId) {
        if (StringUtils.isEmpty(novelId))
            return null;
        return novelHeadDao.selectNovelHeadByNovelId(novelId);
    }

    @Override
    public Page<NovelHead> searchByAuthor(String userName, Pageable pageable) {
        List<NovelHead> result = null;
        long count = 0;
        if (StringUtils.isEmpty(userName) || pageable == null) {
            result = Collections.emptyList();
        } else {
            result = novelHeadDao.selectNovelHeadByAuthor(userName, pageable);
            count = searchCountByAuthor(userName);
        }
        return new PageImpl<NovelHead>(result, pageable, count);
    }

    @Override
    public long searchCountByNovelName(String novelName) {
        return novelHeadDao.selectNovelHeadCountByNovelName(novelName);
    }

    @Override
    public long searchCountByAuthor(String userName) {
        return novelHeadDao.selectNovelHeadCountByAuthor(userName);
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
