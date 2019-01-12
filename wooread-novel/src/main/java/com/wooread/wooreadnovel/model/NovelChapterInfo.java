package com.wooread.wooreadnovel.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class NovelChapterInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer chapterInfoId;
    private Integer chapterId;
    private Integer novelId;

    private String title;
    private Integer wordCount;// 字数
}
