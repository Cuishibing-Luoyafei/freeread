package cui.shibing.freeread.service.impl;

import cui.shibing.freeread.dao.NovelClassDao;
import cui.shibing.freeread.model.NovelClass;
import cui.shibing.freeread.service.NovelClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NovelClassServiceImpl implements NovelClassService {

    @Autowired
    private NovelClassDao novelClassDao;

    @Override
    @Cacheable(value = "default", cacheManager = "cacheManager", key = "#root.targetClass+'.'+#root.methodName")
    public List<NovelClass> getAllNovelClasses() {
        List<NovelClass> classes = novelClassDao.selectAllClass();
        return classes;
    }
}
