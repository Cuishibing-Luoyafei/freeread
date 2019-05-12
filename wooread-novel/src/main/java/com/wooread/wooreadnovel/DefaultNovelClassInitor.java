package com.wooread.wooreadnovel;

import com.wooread.wooreadnovel.model.NovelClass;
import cui.shibing.commonrepository.CommonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static cui.shibing.commonrepository.Specifications.equal;

/**
 * 启动时插入默认的小说类别
 */
@Slf4j
@Component
@ConfigurationProperties(prefix = "novel.class")
public class DefaultNovelClassInitor implements CommandLineRunner {
    private List<String> defaultClass = new ArrayList<>();
    private Boolean clear;
    @Resource(name = "NovelClass")
    private CommonRepository<NovelClass, String> novelClassCommonRepository;

    @Override
    public void run(String... args) throws Exception {
        log.info("init default novel class start");
        if(clear)
            novelClassCommonRepository.deleteAll();
        if (defaultClass != null) {
            defaultClass.forEach(novelClass -> {
                Specification<NovelClass> deleteQuery = equal("className", novelClass);
                log.info("delete exist novel class");
                List<NovelClass> allClass = novelClassCommonRepository.findAll(deleteQuery);
                novelClassCommonRepository.deleteAll(allClass);

                NovelClass newClass = new NovelClass();
                newClass.setRemoved(false);
                newClass.setClassName(novelClass);
                novelClassCommonRepository.save(newClass);
            });
        }
        log.info("init default novel class end");
    }

    public List<String> getDefaultClass() {
        return defaultClass;
    }

    public void setDefaultClass(List<String> defaultClass) {
        this.defaultClass = defaultClass;
    }

    public Boolean getClear() {
        return clear;
    }

    public void setClear(Boolean clear) {
        this.clear = clear;
    }
}
