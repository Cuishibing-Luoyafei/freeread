package com.wooread.wooreadnovel.service.impl;

import com.wooread.wooreadbase.dto.BaseServiceOutput;
import com.wooread.wooreadnovel.model.NovelClass;
import com.wooread.wooreadnovel.service.NovelClassService;
import cui.shibing.commonrepository.CommonRepository;
import cui.shibing.commonrepository.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

import static com.wooread.wooreadbase.dto.BaseServiceOutput.ofFail;
import static com.wooread.wooreadbase.dto.BaseServiceOutput.ofSuccess;
import static com.wooread.wooreadbase.tools.MessageTools.message;

@Service
@Transactional
public class NovelClassServiceImpl implements NovelClassService {

    @Resource(name = "NovelClass")
    private CommonRepository<NovelClass, String> novelClassCommonRepository;

    @Override
    public BaseServiceOutput<Boolean> add(NovelClass novelClass) {
        if (novelClass == null || StringUtils.isEmpty(novelClass.getClassName())) {
            return BaseServiceOutput.ofFail(message("NotEmpty","class name"));
        }
        try {
            novelClassCommonRepository.save(novelClass);
            return ofSuccess(true);
        } catch (Exception e) {
            // 捕捉重复类名称异常
            return ofFail(message("duplicate","class name"));
        }
    }

    @Override
    public BaseServiceOutput<Boolean> remove(String classId) {
        if (classId == null) {
            return ofSuccess(true);
        }
        Optional<NovelClass> targetClass = novelClassCommonRepository.findById(classId);
        return targetClass.map(novelClass -> ofSuccess(() -> {
            novelClassCommonRepository.delete(novelClass);
            return true;
        })).orElseGet(() -> BaseServiceOutput.ofFail(message("no-such", "class")));
    }

    @Override
    public List<NovelClass> findAll() {
        return novelClassCommonRepository.findAll(Specifications.equal("removed", false));
    }
}
