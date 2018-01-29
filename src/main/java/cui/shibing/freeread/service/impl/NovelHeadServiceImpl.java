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

import java.util.List;

import static cui.shibing.freeread.common.CommonUtils.emptyPageObject;
import static cui.shibing.freeread.common.CommonUtils.validatePageable;

@Service
public class NovelHeadServiceImpl implements NovelHeadService {

    @Autowired
    private NovelHeadDao novelHeadDao;

    @Autowired
    private NovelChapterService novelChapterService;

    @Override
    @Cacheable(value = "default", cacheManager = "cacheManager", key = "#root.targetClass+'.'+#root.methodName+'.'+ #className+'.'+#pageable")
    public Page<NovelHead> searchByNovelClass(String className, Pageable pageable) {
        if (StringUtils.isEmpty(className) || !validatePageable(pageable)) {
            return emptyPageObject(pageable);
        }
        long count = novelHeadDao.selectNovelHeadCountByNovelClass(className);
        if (count > 0) {
            List<NovelHead> result = novelHeadDao.selectNovelHeadByNovelClassName(className, pageable);
            return new PageImpl<NovelHead>(result,pageable,count);
        }
        return emptyPageObject(pageable);
    }

    @Override
    @Cacheable(value = "default", cacheManager = "cacheManager", key = "#root.targetClass+'.'+#root.methodName +'.'+ #pageable")
    public Page<NovelHead> searchByPopularity(Pageable pageable) {
        if (!validatePageable(pageable)) {
            return emptyPageObject(pageable);
        }
        long count = novelHeadDao.selectNovelHeadCountByPopularity();
        if (count > 0) {
            List<NovelHead> result = novelHeadDao.selectNovelHeadByPopularity(pageable);
            return new PageImpl<NovelHead>(result, pageable, count);
        }
        return emptyPageObject(pageable);
    }

    @Override
    @Cacheable(value = "default", cacheManager = "cacheManager", key = "#root.targetClass+'.'+#root.methodName +'.'+ #novelName +'.' + #pageable")
    public Page<NovelHead> searchByNovelName(String novelName, Pageable pageable) {
        if (StringUtils.isEmpty(novelName) || !validatePageable(pageable)) {
            return emptyPageObject(pageable);
        }
        long count = novelHeadDao.selectNovelHeadCountByNovelName(novelName);
        if (count > 0) {
            List<NovelHead> result = novelHeadDao.selectNovelHeadByNovelName(novelName, pageable);
            return new PageImpl<NovelHead>(result,pageable,count);
        }
        return emptyPageObject(pageable);
    }

    @Override
    @Transactional
    public boolean addNovelHead(NovelHead head) {
        return head != null && !StringUtils.isEmpty(head.getNovelId()) && novelHeadDao.insertNovelHead(head) == 1;
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
        if (novelHead != null && !StringUtils.isEmpty(novelHead.getNovelId())) {
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
        if (StringUtils.isEmpty(userName) || !validatePageable(pageable)) {
            return emptyPageObject(pageable);
        }
        long count = novelHeadDao.selectNovelHeadCountByAuthor(userName);
        if (count > 0) {
            List<NovelHead> result = novelHeadDao.selectNovelHeadByAuthor(userName, pageable);
            return new PageImpl<NovelHead>(result, pageable, count);
        }
        return emptyPageObject(pageable);
    }

    @Override
    public long searchCountByNovelName(String novelName) {
        if (StringUtils.isEmpty(novelName)) {
            return 0;
        }
        return novelHeadDao.selectNovelHeadCountByNovelName(novelName);
    }

    @Override
    public long searchCountByAuthor(String userName) {
        if (StringUtils.isEmpty(userName)) {
            return 0;
        }
        return novelHeadDao.selectNovelHeadCountByAuthor(userName);
    }

    @Override
    public long searchNovelHeadCount() {
        return novelHeadDao.selectNovelHeadCountByPopularity();
    }

    @Override
    public long searchCountByClassName(String className) {
        if (StringUtils.isEmpty(className)) {
            return 0;
        }
        return novelHeadDao.selectNovelHeadCountByNovelClass(className);
    }

}
