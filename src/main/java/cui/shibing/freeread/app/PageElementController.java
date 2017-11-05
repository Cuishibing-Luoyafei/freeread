package cui.shibing.freeread.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cui.shibing.freeread.app.helper.PageElementHelper;

@Controller
@RequestMapping("pageElement")
public class PageElementController {
	@Autowired
	@Qualifier("novelRankingHelper")
	private PageElementHelper novelRankingHelper;
	/*
	 * 小说排行榜页面
	 * */
	@RequestMapping("novelRanking")
	public String novelRanking(Model model){
		return novelRankingHelper.getPage(model,(Object[])null);
	}
	
	@Autowired
	@Qualifier("navigationHelper")
	private PageElementHelper navigationHelper;
	/*
	 * 导航栏页面
	 * */
	@RequestMapping("navigation")
	public String navigation(Model model){
		return navigationHelper.getPage(model,(Object[])null);
	}
	
	@Autowired
	@Qualifier("novelRecommendHelper")
	private PageElementHelper novelRecommendHelper;
	/*
	 * 小说推荐页面
	 * **/
	@RequestMapping("recommend")
	public String recommend(Model model,@PageableDefault(value = 15) Pageable pageable) {
		return novelRecommendHelper.getPage(model,pageable);
	}
	
	@Autowired
	@Qualifier("novelDeatileHelper")
	private PageElementHelper novelDeatileHelper;
	/*
	 * 小说详情页面
	 * **/
	@RequestMapping("novelDeatil")
	public String novelDetail(Model model,@RequestParam("novelId")String novelId) {
		return novelDeatileHelper.getPage(model,novelId);
	}
}
