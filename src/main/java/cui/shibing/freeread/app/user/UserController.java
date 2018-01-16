package cui.shibing.freeread.app.user;

import cui.shibing.freeread.common.Constant;
import cui.shibing.freeread.dto.JsonResponse;
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

import static cui.shibing.freeread.tools.CommonUtils.getUserNameFromAuthentication;
import static cui.shibing.freeread.tools.JavaMailHelper.checkEmail;
import static cui.shibing.freeread.tools.JavaMailHelper.randomEmailCode;

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

        return isSuccess ? USER_REGISTER_SUCCESS_PAGE : USER_REGISTER_FAIL_PAGE;
    }

    /**
     * 发送邮箱验证码(Ajax方式)
     *
     * @param userEmail 用户提供的邮箱
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
    public String userInfo(Authentication authentication, @ModelAttribute("userControllerFrom") UserControllerForm form) {
        String userName = getUserNameFromAuthentication(authentication);
        UserInfo userInfo = userService.getUserInfo(userName);
        form.setUserName(userName);
        if (userInfo != null) {
            MyBeanUtils.copyProperties(userInfo, form);
        }
        return USER_INFO_PAGE;
    }

    @ModelAttribute("userControllerFrom")
    public UserControllerForm setUpForm() {
        return new UserControllerForm();
    }

    @RequestMapping("updateUserInfo")
    public String updateUserInfo(Authentication authentication,
                                 @Validated @ModelAttribute("userControllerFrom") UserControllerForm form,
                                 BindingResult bindingResult,
                                 @SessionAttribute(value = "emailCode", required = false) String emailCode) {
        if (bindingResult.hasErrors()) {
            return USER_INFO_PAGE;
        }
        if (StringUtils.isEmpty(emailCode) || !emailCode.equals(form.getUserEmailCode())) {
            form.setEmailCodeError(true);//标识失败的原因是由于验证码的错误
            return USER_INFO_PAGE;
        }
        String userName = getUserNameFromAuthentication(authentication);
        userService.updateUserEmail(userName, form.getUserEmail());
        return USER_INFO_PAGE;
    }

    private boolean sendEmailCodeEmail(String randomCode, String userEmail) {
        String emailTitle = "WOOREAD验证码";
        String emailContent = "WOOREAD验证码：<h1><strong>" + randomCode + "</strong></h1>";
        return JavaMailHelper.sendEmail(userEmail, emailTitle, emailContent);
    }

}
