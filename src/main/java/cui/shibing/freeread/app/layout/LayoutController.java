package cui.shibing.freeread.app.layout;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面布局的Controller，该Controller返回的是某一个布局（布局由很多子页面构成）
 **/
@Controller
public class LayoutController {
	/**
	 * 基本布局，带有header、left、main以及footer
	 **/
	private static final String BASE_LAYOUT = ".base_layout";

	/**
	 * 没有left的布局，继承自基本布局
	 **/
	private static final String NO_LEFT_LAYOUT = ".no_left_layout";

	/**
	 * 首页（推荐）
	 **/
	@RequestMapping(value = {"/", "/recommend"})
	public String index() {
		return "novelHead/recommend"
				+ BASE_LAYOUT;
	}

	/**
	 * 小说详情
	 **/
	@RequestMapping(value = "/novelDetails", params = "novelId")
	public String novelDetails() {
		return "novelHead/novelDetails"
				+ NO_LEFT_LAYOUT;
	}

	/**
	 * 小说章节列表
	 * **/
	@RequestMapping(value="/novelChapterList",params="novelId")
	public String novelChapterList(){
		return "novelChapter/novelChapterList"
				+ NO_LEFT_LAYOUT;
	}

	/**
	 * 小说章节内容
	 **/
	@RequestMapping(value = "/novelChapter", params = {"novelId", "chapterIndex"})
	public String novelContent() {
		return "novelChapter/novelChapter" + NO_LEFT_LAYOUT;
	}

	/**
	 * 根据小说名搜索小说
	 **/
	@RequestMapping(value="/searchNovelByName",params = {"searchNovelName"})
	public String searchNovelByName(){
		return "novelHead/novelSearchResult" + NO_LEFT_LAYOUT;
	}

	/**
	 * 用户登录
	 **/
	@RequestMapping(value="/loginPage")
	public String loginLayout(){
        return "user/loginPage" + NO_LEFT_LAYOUT;
	}

	/**
	 * 用户个人中心
	 * */
	@RequestMapping("/userCenter")
	public String userCenter(){
		return "user/userCenterPage" + NO_LEFT_LAYOUT;
	}

	/**
	 * 书架
	 * */
	@RequestMapping("/secretNovels")
	public String secretNovels(){
		return "secretNovel/listSecretNovels" + NO_LEFT_LAYOUT;
	}

	@RequestMapping("/addSecretNovel")
	public String addSecretNovel(){
		return "secretNovel/addSecretNovel" + NO_LEFT_LAYOUT;
	}
}
