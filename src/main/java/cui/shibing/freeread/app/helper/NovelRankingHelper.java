package cui.shibing.freeread.app.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import cui.shibing.freeread.model.NovelHead;
import cui.shibing.freeread.service.NovelHeadService;

@Component("novelRankingHelper")
public class NovelRankingHelper implements PageElementHelper{
	private static volatile String PAGE = "left/novel_ranking";
	private static final int POPULARITY_NUM = 20;
	@Autowired
	private NovelHeadService novelHeadService;

	public String getPage(Model model, Object... params) {
		Pageable pageable = new CustomPageable(1, POPULARITY_NUM);
		Page<NovelHead> leftPopularityNovels = novelHeadService.searchByPopularity(pageable);
		model.addAttribute("leftPopularityNovels", leftPopularityNovels.getContent());
		return PAGE;
	}
	
}
