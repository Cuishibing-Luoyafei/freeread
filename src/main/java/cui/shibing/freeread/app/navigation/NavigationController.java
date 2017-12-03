package cui.shibing.freeread.app.navigation;

import cui.shibing.freeread.model.NovelClass;
import cui.shibing.freeread.service.NovelClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("navigation")
public class NavigationController {
    /**
     * 小说导航栏页面
     */
    private static final String NAVIGATION_PAGE = "header/navigation";

    @Autowired
    private NovelClassService novelClassService;

    /**
     * 导航栏页面
     */
    @RequestMapping("navigationBar")
    public String novelNavigation(Model model) {
        List<NovelClass> classes = novelClassService.getAllNovelClasses();
        model.addAttribute("allNovelClasses", classes);
        return NAVIGATION_PAGE;
    }

}
