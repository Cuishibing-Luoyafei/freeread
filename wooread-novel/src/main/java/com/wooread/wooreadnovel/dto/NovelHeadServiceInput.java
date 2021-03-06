package com.wooread.wooreadnovel.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NovelHeadServiceInput {
    @Data
    public static class CreateNovelHeadInput {
        @NotEmpty
        @Length(max = 100)
        private String novelName;
        private String picture;
        @Length(max = 300)
        private String description;
        private Boolean canShow;
        private String userId;
        // 小说类别名称
        @NotEmpty
        private String className;
    }

    @Data
    public static class UpdateNovelHeadInput extends CreateNovelHeadInput {
        @NotNull
        private String novelId;
    }

    @Data
    public static class QueryNovelHeadInput {
        private String className;
        private String novelId;
        private String novelName;
    }
}
