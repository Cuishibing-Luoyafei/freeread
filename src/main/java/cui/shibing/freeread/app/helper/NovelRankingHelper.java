package cui.shibing.freeread.app.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import cui.shibing.freeread.model.NovelHead;
import cui.shibing.freeread.service.NovelHeadService;

@Component("novelRankingHelper")
public class NovelRankingHelper implements PageElementHelper{
	private static volatile String PAGE = "left/novel_ranking";
	@Autowired
	private NovelHeadService novelHeadService;

	public String getPage(Model model, Object... params) {
		if (params != null && params.length > 0) {
			Object param = params[0];
			Pageable pageable = null;
			if (param != null && param instanceof Pageable) {
				pageable = (Pageable) param;
			} else {
				pageable = new PageRequest(1, 20);
			}
			Page<NovelHead> novels = novelHeadService.searchByPopularity(pageable);
			model.addAttribute("pagePopularityNovels", novels);
		}
		return PAGE;
	}
	
}
