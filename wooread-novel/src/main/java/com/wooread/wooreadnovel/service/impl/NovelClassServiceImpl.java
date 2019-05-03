package com.wooread.wooreadnovel.service.impl;

import com.wooread.wooreadbase.dto.BaseServiceOutput;
import com.wooread.wooreadbase.tools.MessageTools;
import com.wooread.wooreadnovel.model.NovelClass;
import com.wooread.wooreadnovel.service.NovelClassService;
import cui.shibing.commonrepository.CommonRepository;
import cui.shibing.commonrepository.Specifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class NovelClassServiceImpl implements NovelClassService {

    @Autowired
    private CommonRepository<NovelClass, Integer> novelClassCommonRepository;

    @Override
    public BaseServiceOutput<Boolean> add(NovelClass novelClass) {
        if (novelClass == null || StringUtils.isEmpty(novelClass.getClassName())) {
            return BaseServiceOutput.ofFail("类别不能为空");
        }
        novelClass.setRemoved(false);
        return BaseServiceOutput.ofSuccess(() -> {
            novelClassCommonRepository.save(novelClass);
            return true;
        });
    }

    @Override
    public BaseServiceOutput<Boolean> remove(Integer classId) {
        if (classId == null) {
            return BaseServiceOutput.ofSuccess(true);
        }
        return BaseServiceOutput.ofSuccess(() -> {
            novelClassCommonRepository.deleteById(classId);
            return true;
        });
    }

    @Override
    public List<NovelClass> findAll() {
        return novelClassCommonRepository.findAll(Specifications.equal("removed", "0"));
    }
}
