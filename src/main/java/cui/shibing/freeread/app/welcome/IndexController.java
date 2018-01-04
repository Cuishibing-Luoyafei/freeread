package cui.shibing.freeread.app.welcome;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    private static final String PAGE = "redirect:/novelHead/";

    @RequestMapping("/")
    public String welcome() {
        return PAGE;
    }
}
