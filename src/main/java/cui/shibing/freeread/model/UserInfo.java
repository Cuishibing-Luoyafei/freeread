package cui.shibing.freeread.model;

import java.io.Serializable;

public class UserInfo implements Serializable {
    private static final long serialVersionUID = -1712122027029513287L;
    private String userInfoId;
    private String userEmail;

    public String getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(String userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
