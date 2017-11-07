package cui.shibing.freeread.app;

import org.springframework.cache.Cache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LayoutController {

	@RequestMapping(value="/")
	public String index(Model model) {
		return "pageElement/recommend.base_layout";
	}
	@RequestMapping(value="/",params= {"page","size"})
	public String recommendPage() {
		return "pageElement/recommend.base_layout";
	}

	@RequestMapping(value = "/novelDeatil", params = "novelId")
	public String novelDetails(Model model) {
		Cache a;
		return "pageElement/novelDeatil.no_left_layout";
	}

}
