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
        @NotNull
        private Integer authorId;
        private String picture;
        @Length(max = 300)
        private String description;
        private Boolean canShow;
    }

    @Data
    public static class UpdateNovelHeadInput extends CreateNovelHeadInput {
        @NotNull
        private Integer novelId;
    }
}
