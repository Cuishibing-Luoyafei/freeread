package com.wooread.wooreadnovel.service.impl;

import com.wooread.wooreadbase.dto.BaseServiceOutput;
import com.wooread.wooreadbase.tools.MessageTools;
import com.wooread.wooreadnovel.model.NovelClass;
import com.wooread.wooreadnovel.service.NovelClassService;
import cui.shibing.commonrepository.CommonRepository;
import cui.shibing.commonrepository.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

import static com.wooread.wooreadbase.tools.MessageTools.message;

@Service
public class NovelClassServiceImpl implements NovelClassService {

    @Resource(name = "NovelClass")
    private CommonRepository<NovelClass, String> novelClassCommonRepository;

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @Override
    public BaseServiceOutput<Boolean> add(NovelClass novelClass) {
        if (novelClass == null || StringUtils.isEmpty(novelClass.getClassName())) {
            return BaseServiceOutput.ofFail("类别不能为空");
        }
        novelClass.setRemoved(false);
        List<NovelClass> equalNameClasses = novelClassCommonRepository.findAll(Specifications.equal("className", novelClass.getClassName()));
        if (equalNameClasses.size() > 0) {
            // duplicate class name
            return BaseServiceOutput.ofFail(message("duplicate", "class"));
        }
        novelClass = novelClassCommonRepository.save(novelClass);
        equalNameClasses = novelClassCommonRepository.findAll(Specifications.equal("className", novelClass.getClassName()));
        if (equalNameClasses.size() > 1) {
            novelClassCommonRepository.delete(novelClass);
            return BaseServiceOutput.ofFail(message("duplicate", "class"));
        }
        return BaseServiceOutput.ofSuccess(true);
    }

    @Override
    public BaseServiceOutput<Boolean> remove(String classId) {
        if (classId == null) {
            return BaseServiceOutput.ofSuccess(true);
        }
        Optional<NovelClass> targetClass = novelClassCommonRepository.findById(classId);
        return targetClass.map(novelClass -> BaseServiceOutput.ofSuccess(() -> {
            novelClassCommonRepository.delete(novelClass);
            return true;
        })).orElseGet(() -> BaseServiceOutput.ofFail(message("no-such", "class")));
    }

    @Override
    public List<NovelClass> findAll() {
        return novelClassCommonRepository.findAll(Specifications.equal("removed", false));
    }
}
