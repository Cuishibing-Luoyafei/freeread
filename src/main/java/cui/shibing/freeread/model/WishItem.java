package cui.shibing.freeread.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户心愿单item
 */
public class WishItem implements Serializable {
    private static final long serialVersionUID = -91553398888911708L;
    /**
     * 用户名,主键
     */
    private String userName;
    /**
     * 用户邮箱,主键
     */
    private String userEmail;
    /**
     * 小说名(用户输入的,不一定是正确的小说名,要模糊处理),主键
     */
    private String novelName;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 是否通知过用户了
     */
    private boolean isNotify;
    /**
     * 对应的小说是否存在了
     */
    private boolean isExist;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getNovelName() {
        return novelName;
    }

    public void setNovelName(String novelName) {
        this.novelName = novelName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isNotify() {
        return isNotify;
    }

    public void setNotify(boolean notify) {
        isNotify = notify;
    }

    public boolean isExist() {
        return isExist;
    }

    public void setExist(boolean exist) {
        isExist = exist;
    }
}
