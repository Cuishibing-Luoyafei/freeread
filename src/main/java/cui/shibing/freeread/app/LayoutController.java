package cui.shibing.freeread.app;

import org.springframework.cache.CacheManager;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LayoutController {

	@RequestMapping("/")
	public String recommend(Model model) {
		return "pageElement/recommend.base_layout";
	}

	@RequestMapping(value = "/novelDeatil", params = "novelId")
	public String novelDetails(Model model) {
		CacheManager a = new RedisCacheManager();
		return "pageElement/novelDeatil.no_left_layout";
	}

}
