package com.wooread.wooreadnovel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class NovelChapter implements Serializable {
    @Id
    private String chapterId = UUID.randomUUID().toString().replace("-","");
    private String novelId;

    @Column(columnDefinition = "text")
    private String content;
}
