package com.wooread.wooreadnovel;

import com.wooread.wooreadnovel.model.NovelClass;
import cui.shibing.commonrepository.CommonRepository;
import cui.shibing.commonrepository.Specifications;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 启动时插入默认的小说类别
 */
@Slf4j
@Component
@ConfigurationProperties(prefix = "novel.class")
public class DefaultNovelClassInitor implements CommandLineRunner {
    private List<String> defaultClass = new ArrayList<>();
    private Boolean clear = false;
    @Resource(name = "NovelClass")
    private CommonRepository<NovelClass, String> novelClassCommonRepository;

    @Override
    public void run(String... args) throws Exception {
        log.info("init default novel class start");
        if (clear) {
            log.info("clear all novel class");
            novelClassCommonRepository.findAll(Specifications.equal("removed",false)).forEach(novelClass -> {
                novelClass.setRemoved(true);
                novelClassCommonRepository.save(novelClass);
            });
        }
        if (defaultClass != null) {
            defaultClass.forEach(novelClass->{
                Optional<NovelClass> className = novelClassCommonRepository.findOne(Specifications.equal("className", novelClass));
                if (!className.isPresent()) {
                    // if not present
                    NovelClass newClass = new NovelClass();
                    newClass.setRemoved(false);
                    newClass.setClassName(novelClass);
                    novelClassCommonRepository.save(newClass);
                }
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
