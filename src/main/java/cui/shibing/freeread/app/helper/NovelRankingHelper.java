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
	@Autowired
	private NovelHeadService novelHeadService;

	public String getPage(Model model, Object... params) {
		Pageable pageable = null;
		if (params != null && params.length == 1) {
			if (params[0] instanceof Pageable) {
				pageable = (Pageable) params[0];
			}
		}
		if(pageable != null){
			Page<NovelHead> novels = novelHeadService.searchByPopularity(pageable);
			model.addAttribute("pagePopularityNovels", novels);
		}else{
			//TODO:返回错误页面
		}
		return PAGE;
	}
}
