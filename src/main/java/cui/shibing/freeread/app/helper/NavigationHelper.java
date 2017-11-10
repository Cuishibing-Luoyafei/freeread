package cui.shibing.freeread.app.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import cui.shibing.freeread.service.NovelClassService;
import cui.shibing.freeread.service.NovelClassService.NovelClassServiceOutputBean;

@Component("navigationHelper")
public class NavigationHelper implements PageElementHelper {
	private static volatile String PAGE = "header/navigation";
	@Autowired
	private NovelClassService novelClassService;

	public String getPage(Model model, Object... params) {
		NovelClassServiceOutputBean outputBean = novelClassService.getAllNovelClasses(null);
		model.addAttribute("allNovelClasses", outputBean.getNovelClasses());
		return PAGE;
	}

	/*
	 * public String getPage(Model model, Object... params) { List<NovelClass>
	 * allNovelClasses = novelClassService.getAllNovelClasses();
	 * model.addAttribute("allNovelClasses",allNovelClasses); return PAGE; }
	 */
}
