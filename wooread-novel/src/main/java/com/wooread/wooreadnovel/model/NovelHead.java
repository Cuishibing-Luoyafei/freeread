package com.wooread.wooreadnovel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class NovelHead implements Serializable {
    @Id
    private String novelId = UUID.randomUUID().toString().replace("-","");
    private String novelName;
    private String picture;
    private String description;
    private Boolean canShow;
    private String userId;
}
