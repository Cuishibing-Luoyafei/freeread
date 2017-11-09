package cui.shibing.freeread.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cui.shibing.freeread.app.helper.PageElementHelper;
/*
 * 页面子页面Controller，该Controller返回的是一个具体的子页面
 * **/
@Controller
@RequestMapping("pageElement")
public class PageElementController {
	@Autowired
	@Qualifier("novelRankingHelper")
	private PageElementHelper novelRankingHelper;

	/*
	 * 小说排行榜页面
	 */
	@RequestMapping("novelRanking")
	public String novelRanking(Model model) {
		return novelRankingHelper.getPage(model, new PageRequest(0, 20));
	}

	@Autowired
	@Qualifier("navigationHelper")
	private PageElementHelper navigationHelper;

	/*
	 * 导航栏页面
	 */
	@RequestMapping("navigation")
	public String navigation(Model model) {
		return navigationHelper.getPage(model, (Object[]) null);
	}

	@Autowired
	@Qualifier("novelRecommendHelper")
	private PageElementHelper novelRecommendHelper;

	/*
	 * 小说推荐页面
	 */
	@RequestMapping("recommend")
	public String recommend(Model model, @PageableDefault(value = 3) Pageable pageable) {
		return novelRecommendHelper.getPage(model, pageable);
	}

	@Autowired
	@Qualifier("novelDeatileHelper")
	private PageElementHelper novelDeatileHelper;

	/*
	 * 小说详情页面
	 */
	@RequestMapping("novelDeatil")
	public String novelDetail(Model model, @RequestParam("novelId") String novelId) {
		return novelDeatileHelper.getPage(model, novelId);
	}

	@Autowired
	@Qualifier("novelChapterListHelper")
	private PageElementHelper novelChapterListHelper;

	/*
	 * 小說章節列表頁面
	 **/
	@RequestMapping("novelChapterList")
	public String novelChapterList(Model model, @RequestParam("novelId") String novelId,
			@PageableDefault(value = 50) Pageable pageable) {
		return novelChapterListHelper.getPage(model, novelId, pageable);
	}
}
