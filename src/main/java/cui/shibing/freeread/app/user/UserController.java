package cui.shibing.freeread.app.user;

import cui.shibing.freeread.common.Constant;
import cui.shibing.freeread.dto.JsonResponse;
import cui.shibing.freeread.model.User;
import cui.shibing.freeread.model.UserInfo;
import cui.shibing.freeread.service.UserService;
import cui.shibing.freeread.tools.JavaMailHelper;
import cui.shibing.freeread.tools.MyBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
import java.util.UUID;

import static cui.shibing.freeread.security.CustomAuthenticationLoginProcessFilter.getUserNameFromAuthentication;

@Controller
@RequestMapping("user")
@SessionAttributes("emailCode")
public class UserController {

    private static final String LOGIN_FAIL_PAGE = "main/login_fail" + Constant.NO_LEFT_LAYOUT;

    private static final String LOGIN_PAGE = "main/login" + Constant.NO_LEFT_LAYOUT;

    private static final String USER_CENTER_PAGE = "main/user/user_center" + Constant.NO_LEFT_LAYOUT;

    private static final String USER_REGISTER_PAGE = "main/user/user_register" + Constant.NO_LEFT_LAYOUT;

    private static final String USER_REGISTER_FAIL_PAGE = "main/user/user_register_fail" + Constant.NO_LEFT_LAYOUT;

    private static final String USER_REGISTER_SUCCESS_PAGE = "main/user/user_register_success" + Constant.NO_LEFT_LAYOUT;

    private static final String USER_INFO_PAGE = "main/user/user_info" + Constant.NO_LEFT_LAYOUT;

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

    /**
     * 发送邮箱验证码(Ajax方式)
     *
     * @param userEmail 用户提供的邮箱
     *
     * @return json数据响应
     */
    @RequestMapping("sendEmailCode")
    @ResponseBody
    public JsonResponse sendEmailCode(Model model, @RequestParam("userEmail") String userEmail) {
        JsonResponse jsonResponse = new JsonResponse(false, "");
        boolean checkResult = checkEmail(userEmail);
        if (checkResult) {
            String emailCode = randomEmailCode();
            boolean sendSuccess = sendEmailCodeEmail(emailCode, userEmail);
            if (sendSuccess) {
                model.addAttribute("emailCode", emailCode);
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

    @RequestMapping("userInfo")
    public String userInfo(Model model, Authentication authentication, @ModelAttribute("userControllerFrom") UserControllerFrom form) {
        String userName = getUserNameFromAuthentication(authentication);
        UserInfo userInfo = userService.getUserInfo(userName);
        form.setUserName(userName);
        MyBeanUtils.copyProperties(userInfo, form);
        return USER_INFO_PAGE;
    }

    @ModelAttribute("userControllerFrom")
    public UserControllerFrom setUpForm() {
        return new UserControllerFrom();
    }

    @RequestMapping("updateUserInfo")
    public String updateUserInfo(Model model, Authentication authentication,
                                 @Validated @ModelAttribute("userControllerFrom") UserControllerFrom from,
                                 BindingResult bindingResult,
                                 @SessionAttribute(value = "emailCode", required = false) String emailCode) {
        if (bindingResult.hasErrors()) {
            from.setEmailCodeError(false);
            return USER_INFO_PAGE;
        }
        if (StringUtils.isEmpty(emailCode) || !emailCode.equals(from.getUserEmailCode())) {
            from.setEmailCodeError(true);
            return USER_INFO_PAGE;
        } else {
            from.setEmailCodeError(false);
        }

        String userName = getUserNameFromAuthentication(authentication);
        UserInfo oldUserInfo = userService.getUserInfo(userName);
        if (oldUserInfo == null) {
            oldUserInfo = new UserInfo();
            oldUserInfo.setUserInfoId(UUID.randomUUID().toString());
            oldUserInfo.setUserEmail(from.getUserEmail());
            userService.insertUserInfo(oldUserInfo);
            User u = userService.getUserByName(userName);
            u.setUserInfoId(oldUserInfo.getUserInfoId());
            userService.updateUserByName(u);
        } else {
            oldUserInfo.setUserEmail(from.getUserEmail());
            userService.updateUserInfo(userName, oldUserInfo);
        }
        return USER_INFO_PAGE;
    }

    private boolean sendEmailCodeEmail(String randoCode, String userEmail) {
        String emailTitle = "WOOREAD验证码";
        String emailContent = "WOOREAD验证码：<h1><strong>" + randoCode + "</strong></h1>";
        return JavaMailHelper.sendEmail(userEmail, emailTitle, emailContent);
    }

    private boolean checkEmail(String userEmail) {
        return !StringUtils.isEmpty(userEmail) && userEmail.matches("\\w+@\\w+(\\.\\w+)+");
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
