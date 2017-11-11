package cui.shibing.freeread.app.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import cui.shibing.freeread.dto.NovelChapterInfoDto;
import cui.shibing.freeread.service.NovelChapterService;

@Component("novelChapterListHelper")
public class NovelChapterListHelper implements PageElementHelper {
	private static final String PAGE = "main/chapter_list";
	@Autowired
	private NovelChapterService novelContentService;

	public String getPage(Model model, Object... params) {
		String novelId = null;
		Pageable pageable = null;
		if (params != null && params.length == 2) {
			if(params[0] instanceof String)
				novelId = (String)params[0];
			if(params[1] instanceof Pageable)
				pageable = (Pageable)params[1];
		}
		if(pageable != null && !StringUtils.isEmpty(novelId)) {
			Page<NovelChapterInfoDto> pageNovelContens = novelContentService.searchChapterInfoByNovelId(novelId, pageable);
			model.addAttribute("pageNovelContents",pageNovelContens);
		}else{
			//TODO:返回错误页面
		}
		return PAGE;
	}

}
