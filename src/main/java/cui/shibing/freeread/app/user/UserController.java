package cui.shibing.freeread.app.user;

import cui.shibing.freeread.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
     * */
    @RequestMapping("loginPage")
    public String loginPage(){
        return LOGIN_PAGE;
    }

    /**
     * 用户登录失败页面
     */
    @RequestMapping("loginFailPage")
    public String loginFailPage(){
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
}
