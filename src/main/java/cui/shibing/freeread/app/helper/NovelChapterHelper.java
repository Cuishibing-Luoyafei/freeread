package cui.shibing.freeread.app.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import cui.shibing.freeread.model.NovelChapter;
import cui.shibing.freeread.service.NovelChapterService;

@Component("novelContentHelper")
public class NovelChapterHelper implements PageElementHelper{
	private static final String PAGE = "main/novel_chapter_content";
	private static final String PAGE_NO_CHAPTER = "main/no_chapter";
	@Autowired
	private NovelChapterService  novelContentService;
	public String getPage(Model model, Object... params) {
		String novelId = null;
		Integer chapterIndex = -1;
		if(params!=null&&params.length==2) {
			if(params[0] instanceof String)
				novelId = (String)params[0];
			if(params[1] instanceof Integer)
				chapterIndex = (Integer)params[1];
		}
		if(!StringUtils.isEmpty(novelId) && chapterIndex != -1) {
			NovelChapter novelContent = novelContentService.searchByNovelHeadAndChapter(novelId, chapterIndex);
			if(novelContent!=null){
				model.addAttribute("novelContent", novelContent);
				return PAGE;
			}
			return PAGE_NO_CHAPTER;
		}else {
			//TODO:返回错误页面
		}
		return PAGE;
	}

}
