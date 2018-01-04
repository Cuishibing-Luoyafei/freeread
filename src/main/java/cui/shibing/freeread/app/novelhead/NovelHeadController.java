package cui.shibing.freeread.app.novelhead;

import cui.shibing.freeread.common.Constant;
import cui.shibing.freeread.model.NovelHead;
import cui.shibing.freeread.service.NovelHeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("novelHead")
public class NovelHeadController {
    /**
     * 小说推荐页面
     */
    private static final String RECOMMEND_PAGE = "main/recommend" + Constant.BASE_LAYOUT;
    /**
     * 小说详情页面
     */
    private static final String NOVEL_DETAILS_PAGE = "main/novel_detail" + Constant.BASE_LAYOUT;
    /**
     * 小说排行榜页面
     */
    private static final String NOVEL_RANKLIST_PAGE = "left/novel_ranking";
    /**
     * 小说搜索页面
     * */
    private static final String NOVEL_SEARCH_RESULT_PAGE = "main/novelhead/novel_search_result" + Constant.BASE_LAYOUT;

    @Autowired
    private NovelHeadService novelHeadService;

    /**
     * 首页（推荐）
     **/
    @RequestMapping(value = {"recommend", "/"})
    public String recommend(Model model, @PageableDefault(value = 12) Pageable pageable,
                            @RequestParam(value = "className", required = false) String className) {
        Page<NovelHead> recommendNovels;
        if (!StringUtils.isEmpty(className)) {
            model.addAttribute("className", className);
            recommendNovels = novelHeadService.searchByNovelClass(className, pageable);
        } else {
            recommendNovels = novelHeadService.searchByPopularity(pageable);
        }
        model.addAttribute("pageRecommendNovels", recommendNovels);
        return RECOMMEND_PAGE;
    }

    /**
     * 小说详情
     **/
    @RequestMapping("novelDetails")
    public String novelDetails(Model model, @RequestParam("novelId") String novelId) {
        if (!StringUtils.isEmpty(novelId)) {
            NovelHead novelHead = novelHeadService.searchByNovelId(novelId);
            model.addAttribute("novelHead", novelHead);
        }
        return NOVEL_DETAILS_PAGE;
    }

    @RequestMapping("novelRankingList")
    public String novelRankingList(Model model) {
        /**
         * 排行榜默认显示10个
         * */
        Pageable pageable = new PageRequest(0, 10);
        if (pageable != null) {
            Page<NovelHead> novels = novelHeadService.searchByPopularity(pageable);
            model.addAttribute("pagePopularityNovels", novels);
        }
        return NOVEL_RANKLIST_PAGE;
    }

    @RequestMapping("novelSearchResult")
    public String novelSearchResult(Model model, @RequestParam("searchNovelName") String searchNovelName,
                                    @PageableDefault(value = 6) Pageable pageable) {
        if (pageable != null && !StringUtils.isEmpty(searchNovelName)) {
            Page<NovelHead> novelHeads = novelHeadService.searchByNovelName(searchNovelName, pageable);
            model.addAttribute("searchResult", novelHeads);
        }
        return NOVEL_SEARCH_RESULT_PAGE;
    }

}
