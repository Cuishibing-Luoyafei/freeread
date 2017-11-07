package cui.shibing.freeread.app.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import cui.shibing.freeread.model.NovelHead;
import cui.shibing.freeread.service.NovelHeadService;

@Component("novelRecommendHelper")
public class NovelRecommendHelper implements PageElementHelper {
	private static volatile String PAGE = "main/recommend";
	@Autowired
	private NovelHeadService novelHeadService;

	public String getPage(Model model, Object... params) {
		if (params != null && params.length > 0) {
			Object param = params[0];
			Pageable pageable = null;
			if (param != null && param instanceof Pageable) {
				pageable = (Pageable) param;
			} else {
				pageable = new CustomPageable(1, 20);
			}
			Page<NovelHead> recommendNovels = novelHeadService.searchByPopularity(pageable);
			model.addAttribute("pageRecommendNovels", recommendNovels);
		}
		return PAGE;
	}
}
