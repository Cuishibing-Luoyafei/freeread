package cui.shibing.freeread.app.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("login")
public class LoginController {

    private static final String LOGIN_FAIL_PAGE = "main/loginFail";

    private static final String LOGIN_PAGE = "main/login";

    /*
	 * 用户登录页面
	 * */
    @RequestMapping("loginPage")
    public String loginPage(){
        return LOGIN_PAGE;
    }

    @RequestMapping("loginFailPage")
    public String loginFailPage(){
        return LOGIN_FAIL_PAGE;
    }
}
