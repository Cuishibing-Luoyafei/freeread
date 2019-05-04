package com.wooread.wooreaduser.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = -7585586864581205174L;
    @Id
    private String userId = UUID.randomUUID().toString().replace("-","");
    private String userName;
    private String password;
    private String userInfoId;
}
