package cui.shibing.freeread.app.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {

    private static final String LOGIN_FAIL_PAGE = "main/loginFail";

    private static final String LOGIN_PAGE = "main/login";

    private static final String USER_CENTER_PAGE = "main/user_center/user_center";

    /**
     * 用户登录页面，只是为了返回这个页面，可以使用配置文件代替
     * */
    @RequestMapping("loginPage")
    public String loginPage(){
        return LOGIN_PAGE;
    }

    /**
     * 用户登录失败页面，只是为了返回这个页面，可以使用配置文件代替
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
}
