package cui.shibing.freeread.app.helper;

import cui.shibing.freeread.model.NovelClass;
import cui.shibing.freeread.service.NovelClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.List;

@Component("navigationHelper")
public class NavigationHelper implements PageElementHelper {
	private static volatile String PAGE = "header/navigation";
	@Autowired
	private NovelClassService novelClassService;

	public String getPage(Model model, Object... params) {
		List<NovelClass> classes = novelClassService.getAllNovelClasses();
		model.addAttribute("allNovelClasses", classes);
		return PAGE;
	}

	/*
	 * public String getPage(Model model, Object... params) { List<NovelClass>
	 * allNovelClasses = novelClassService.getAllNovelClasses();
	 * model.addAttribute("allNovelClasses",allNovelClasses); return PAGE; }
	 */
}
