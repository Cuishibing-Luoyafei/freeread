package com.wooread.wooreadnovel.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class NovelClass {
    @Id
    private String classId = UUID.randomUUID().toString().replace("-","");
    @Column(unique = true)
    private String className;
    private boolean removed = false;
}
