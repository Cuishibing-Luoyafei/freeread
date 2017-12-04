package cui.shibing.freeread.model;

import java.io.Serializable;

public class User implements Serializable {

    /**
     * 用户名,唯一
     * */
    private String userName;
    /**
     * 用户密码
     * */
    private String userPass;
    /**
     * 用户角色
     * */
    private Integer userRole;
    /**
     * 用户信息的外键id
     * */
    private String userInfoId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    public String getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(String userInfoId) {
        this.userInfoId = userInfoId;
    }
}
