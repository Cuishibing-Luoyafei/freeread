package cui.shibing.freeread.app.user;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * UserController使用的Form
 *
 * @author cui
 */
public class UserControllerForm {

    /**
     * 邮箱
     */
    @NotEmpty
    @Email
    private String userEmail;
    /**
     * 邮箱验证码
     */
    @NotEmpty
    private String userEmailCode;

    /**
     * 用户名
     */
    private String userName;

    private boolean isEmailCodeError = false;

    public boolean isEmailCodeError() {
        return isEmailCodeError;
    }

    public void setEmailCodeError(boolean emailCodeError) {
        isEmailCodeError = emailCodeError;
    }

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

    public String getUserEmailCode() {
        return userEmailCode;
    }

    public void setUserEmailCode(String userEmailCode) {
        this.userEmailCode = userEmailCode;
    }
}
