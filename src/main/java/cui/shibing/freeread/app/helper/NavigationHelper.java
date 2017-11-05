package cui.shibing.freeread.app.helper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import cui.shibing.freeread.model.NovelClass;
import cui.shibing.freeread.service.NovelClassService;

@Component("navigationHelper")
public class NavigationHelper implements PageElementHelper{
	private static volatile String PAGE = "header/navigation";
	@Autowired
	private NovelClassService novelClassService;

	public String getPage(Model model, Object... params) {
		List<NovelClass> allNovelClass = novelClassService.getAllNovelClass();
		model.addAttribute("allNovelClass",allNovelClass);
		return PAGE;
	}
	
}
