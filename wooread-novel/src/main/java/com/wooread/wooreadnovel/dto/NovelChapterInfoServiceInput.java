package com.wooread.wooreadnovel.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NovelChapterInfoServiceInput {

    @Data
    public static class UpdateChapterInfoInput {
        @NotNull
        private Integer chapterInfoId;
        @NotEmpty
        private String title;
        @NotNull
        private Integer wordCount;// 字数
    }
}
