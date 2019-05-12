package com.wooread.wooreadnovel.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NovelChapterServiceInput {

    @Data
    public static class CreateNovelChapterInput {
        @NotNull
        private String novelId;
        @NotEmpty
        @Length(max = 30000)
        private String content;

        @NotEmpty
        @Length(max = 100)
        private String title;
    }

    @Data
    public static class UpdateNovelChapterInput {
        @NotNull
        private String chapterId;
        @NotEmpty
        @Length(max = 30000)
        private String content;
    }

}
