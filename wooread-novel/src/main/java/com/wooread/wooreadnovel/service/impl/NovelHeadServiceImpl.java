package com.wooread.wooreadnovel.service.impl;

import com.wooread.wooreadnovel.dto.BaseServiceOutput;
import com.wooread.wooreadnovel.dto.NovelHeadServiceInput;
import com.wooread.wooreadnovel.model.NovelHead;
import com.wooread.wooreadnovel.service.NovelHeadService;
import cui.shibing.commonrepository.CommonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static com.wooread.wooreadnovel.dto.BaseServiceOutput.CODE_FAIL;
import static com.wooread.wooreadnovel.dto.BaseServiceOutput.CODE_SUCCESS;
import static com.wooread.wooreadnovel.tools.MessageTools.message;

@Service
@Transactional
public class NovelHeadServiceImpl implements NovelHeadService {

    @Resource(name = "NovelHead")
    private CommonRepository<NovelHead, Integer> novelHeadcommonRespository;

    @Override
    public BaseServiceOutput<NovelHead> createNovelHead(NovelHeadServiceInput.CreateNovelHeadInput input) {
        return new BaseServiceOutput<>(CODE_SUCCESS, message("success"), () -> {
            NovelHead novelHead = new NovelHead();
            BeanUtils.copyProperties(input, novelHead);
            return novelHeadcommonRespository.save(novelHead);
        });
    }

    @Override
    public BaseServiceOutput<NovelHead> updateNovelHead(NovelHeadServiceInput.UpdateNovelHeadInput input) {
        return novelHeadcommonRespository.findById(input.getNovelId()).map(novelHead -> {
            return new BaseServiceOutput<>(CODE_SUCCESS, message("success"), () -> {
                BeanUtils.copyProperties(input, novelHead);
                return novelHeadcommonRespository.save(novelHead);
            });
        }).orElse(new BaseServiceOutput<>(CODE_FAIL, message("no-such", "novel head")));
    }
}
