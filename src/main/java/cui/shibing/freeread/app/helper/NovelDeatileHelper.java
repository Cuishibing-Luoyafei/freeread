package cui.shibing.freeread.app.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import cui.shibing.freeread.model.NovelHead;
import cui.shibing.freeread.service.NovelHeadService;
@Component("novelDeatileHelper")
public class NovelDeatileHelper implements PageElementHelper{
	private static final String PAGE = "main/novel_detail";
	@Autowired
	private NovelHeadService novelHeadService;
	
	public String getPage(Model model, Object... params) {
		if(params == null || params.length < 1) {
			//TODO:返回错误页面
			return "";
		}
		if(params[0] == null) {
			//TODO:返回错误页面
			return "";
		}
		if(!(params[0] instanceof String)) {
			//TODO:返回错误页面
			return "";
		}else {
			String novelId = (String)params[0];
			NovelHead novelHead = novelHeadService.searchByNovelId(novelId);
			model.addAttribute("novelHead",novelHead);
		}
		return PAGE;
	}

}
