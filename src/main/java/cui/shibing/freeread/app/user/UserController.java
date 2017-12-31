package cui.shibing.freeread.app.user;

import cui.shibing.freeread.dto.JsonResponse;
import cui.shibing.freeread.service.UserService;
import cui.shibing.freeread.tools.JavaMailHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

@Controller
@RequestMapping("user")
public class UserController {

    private static final String LOGIN_FAIL_PAGE = "main/loginFail";

    private static final String LOGIN_PAGE = "main/login";

    private static final String USER_CENTER_PAGE = "main/user/user_center";

    private static final String USER_REGISTER_PAGE = "main/user/user_register";

    private static final String USER_REGISTER_FAIL_PAGE = "main/user/user_register_fail";

    private static final String USER_REGISTER_SUCCESS_PAGE = "main/user/user_register_success";


    @Autowired
    private UserService userService;

    /**
     * 用户登录页面
     */
    @RequestMapping("loginPage")
    public String loginPage() {
        return LOGIN_PAGE;
    }

    /**
     * 用户登录失败页面
     */
    @RequestMapping("loginFailPage")
    public String loginFailPage() {
        return LOGIN_FAIL_PAGE;
    }

    /**
     * 用户个人中心页面
     */
    @RequestMapping("userCenterPage")
    public String userCenterPage() {
        return USER_CENTER_PAGE;
    }

    /**
     * 用户注册页面
     */
    @RequestMapping("registerUserPage")
    public String registerUserPage() {
        return USER_REGISTER_PAGE;
    }

    /**
     * 用户注册
     */
    @RequestMapping("registerUser")
    public String registerUser(@RequestParam("userName") String userName,
                               @RequestParam("userPass") String userPass) {
        boolean isSuccess = userService.registerUser(userName, userPass);
        String page = USER_REGISTER_FAIL_PAGE;
        if (isSuccess) {
            page = USER_REGISTER_SUCCESS_PAGE;
        }
        return page;
    }

    @RequestMapping("sendEmailCode")
    @ResponseBody
    public JsonResponse sendEmailCode(@RequestParam("userEmail") String userEmail) {
        JsonResponse jsonResponse = new JsonResponse(false, "");
        boolean checkResult = checkEmail(userEmail);
        if (checkResult) {
            boolean sendSuccess = sendEmailCodeEmail(randomEmailCode(), userEmail);
            if (sendSuccess) {
                jsonResponse.setIsSuccess(true);
                jsonResponse.setMessage("发送成功");
            } else {
                jsonResponse.setIsSuccess(false);
                jsonResponse.setMessage("验证码邮件发送失败,请稍后再试");
            }
        } else {
            jsonResponse.setIsSuccess(false);
            jsonResponse.setMessage("请输入正确的邮箱地址");
        }
        return jsonResponse;
    }

    private boolean sendEmailCodeEmail(String randoCode, String userEmail) {
        String emailTitle = "WOOREAD验证码";
        String emailContent = "WOOREAD验证码：<h1><strong>" + randoCode + "</strong></h1>";
        return JavaMailHelper.sendEmail(userEmail, emailTitle, emailContent);
    }

    private boolean checkEmail(String userEmail) {
        if (StringUtils.isEmpty(userEmail)) {
            return false;
        }
        return userEmail.matches("\\w+@\\w+(\\.\\w+)+");
    }

    /**
     * 生成随机的验证码
     */
    private String randomEmailCode() {
        final int codeLength = 6;
        char[] codeChars = new char[codeLength];
        Random random = new Random();
        for (int i = 0; i < codeLength; i++) {
            boolean isUpperCase = random.nextBoolean();
            if (isUpperCase) {
                codeChars[i] = (char) ('A' + random.nextInt(26));
            } else {
                codeChars[i] = (char) ('a' + random.nextInt(26));
            }
        }
        return String.valueOf(codeChars);
    }

}
