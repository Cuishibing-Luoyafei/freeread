package com.wooread.wooreadnovel.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class NovelClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer classId;
    private String className;
    private Boolean removed;
}
