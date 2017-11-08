package cui.shibing.freeread.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LayoutController {
	private static final String BASE_LAYOUT = ".base_layout";
	private static final String NO_LEFT_LAYOUT = ".no_left_layout";
	@RequestMapping(value="/")
	public String index() {
		return "pageElement/recommend"
				+ BASE_LAYOUT;
	}
	@RequestMapping(value="/recomend",params= {"page","size"})
	public String recommendPage() {
		return "pageElement/recommend"
				+ BASE_LAYOUT;
	}

	@RequestMapping(value = "/novelDeatil", params = "novelId")
	public String novelDetails() {
		return "pageElement/novelDeatil"
				+ NO_LEFT_LAYOUT;
	}
	
	@RequestMapping(value="/novelChapterList",params="novelId")
	public String novelChapterList(){
		return "pageElement/novelChapterList"
				+ NO_LEFT_LAYOUT;
	}

}
