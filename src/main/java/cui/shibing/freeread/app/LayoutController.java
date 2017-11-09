package cui.shibing.freeread.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/*
 * 页面布局的Controller，该Controller返回的是某一个布局（布局由很多子页面构成）
 * **/
@Controller
public class LayoutController {
	/*
	 * 基本布局，带有header、left、main以及footer
	 * **/
	private static final String BASE_LAYOUT = ".base_layout";
	
	/*
	 * 没有left的布局，继承自基本布局
	 * **/
	private static final String NO_LEFT_LAYOUT = ".no_left_layout";
	
	/*
	 * 首页（推荐）
	 * **/
	@RequestMapping(value={"/","/recomend"})
	public String index() {
		return "pageElement/recommend"
				+ BASE_LAYOUT;
	}

	/*
	 * 小说详情
	 * **/
	@RequestMapping(value = "/novelDeatil", params = "novelId")
	public String novelDetails() {
		return "pageElement/novelDeatil"
				+ NO_LEFT_LAYOUT;
	}
	
	/*
	 * 小说章节列表
	 * **/
	@RequestMapping(value="/novelChapterList",params="novelId")
	public String novelChapterList(){
		return "pageElement/novelChapterList"
				+ NO_LEFT_LAYOUT;
	}

}
