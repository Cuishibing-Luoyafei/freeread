package com.wooread.wooreadnovel.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
public class NovelChapterInfo implements Serializable {
    @Id
    private String chapterInfoId = UUID.randomUUID().toString().replace("-","");
    private String chapterId;
    private String novelId;

    private String title;
    private Integer wordCount;// 字数
}
