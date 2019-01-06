package com.wooread.wooreaduser.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserInfo implements Serializable {
    private static final long serialVersionUID = -2889451560871791473L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userInfoId;
    private Integer userId;
    private String email;
    private String phone;
    private Byte gender;
    private String userRoleIds;// 用户角色id集合，逗号分割
}
