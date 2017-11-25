package cui.shibing.freeread.app.helper;

import cui.shibing.freeread.service.NovelHeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;

@Component("searchNovelByNameHelper")
public class SearchNovelByNameHelper implements PageElementHelper{
    //TODO:返回了推荐页面,因为这两个页面比较相似
    private static final String PAGE = "main/recommend";
    @Autowired
    private NovelHeadService novelHeadService;
    @Override
    public String getPage(Model model, Object... params) {
        Pageable pageable = null;
        String searchNovelName = null;
        if(params!=null&&params.length==2){
            if(params[0] instanceof String)
                searchNovelName = (String)params[0];
            if(params[1]instanceof Pageable)
                pageable = (Pageable)params[1];
        }
        if(pageable!=null&& !StringUtils.isEmpty(searchNovelName)){
            model.addAttribute("pageRecommendNovels",novelHeadService.searchByNovelName(searchNovelName,pageable));
        }
        return PAGE;
    }
}
