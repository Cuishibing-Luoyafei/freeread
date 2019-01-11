package com.wooread.wooreadnovel.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NovelChapterServiceInput {

    @Data
    public static class CreateNovelChapterInput {
        @NotNull
        private Integer novelId;
        @NotEmpty
        @Length(max = 100)
        private String title;
        @NotEmpty
        @Length(max = 30000)
        private String content;
    }

    @Data
    public static class UpdateNovelChapterInput {
        @NotNull
        private Integer chapterId;
        @NotEmpty
        @Length(max = 100)
        private String title;
        @NotEmpty
        @Length(max = 30000)
        private String content;
    }

}
