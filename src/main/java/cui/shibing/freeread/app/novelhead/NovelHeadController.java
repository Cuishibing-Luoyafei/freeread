package cui.shibing.freeread.app.novelhead;

import cui.shibing.freeread.common.CommonUtils;
import cui.shibing.freeread.common.Constant;
import cui.shibing.freeread.model.NovelHead;
import cui.shibing.freeread.model.UserInfo;
import cui.shibing.freeread.service.NovelHeadService;
import cui.shibing.freeread.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
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
    private static final String NOVEL_DETAILS_PAGE = "main/novel_detail" + Constant.NO_LEFT_LAYOUT;
    /**
     * 小说排行榜页面
     */
    private static final String NOVEL_RANK_LIST_PAGE = "left/novel_ranking";
    /**
     * 小说搜索页面
     */
    private static final String NOVEL_SEARCH_RESULT_PAGE = "main/novelhead/novel_search_result" + Constant.NO_LEFT_LAYOUT;

    @Autowired
    private NovelHeadService novelHeadService;

    @Autowired
    private UserService userService;

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

        NovelHead novelHead = novelHeadService.searchByNovelId(novelId);
        if (novelHead != null)
            model.addAttribute("novelHead", novelHead);

        return NOVEL_DETAILS_PAGE;
    }

    /**
     * 小说排行榜
     */
    @RequestMapping("novelRankingList")
    public String novelRankingList(Model model) {
        Pageable pageable = new PageRequest(0, 10);

        Page<NovelHead> novels = novelHeadService.searchByPopularity(pageable);
        model.addAttribute("pagePopularityNovels", novels);

        return NOVEL_RANK_LIST_PAGE;
    }

    /**
     * 小说搜索结果
     */
    @RequestMapping("novelSearchResult")
    public String novelSearchResult(Model model, @RequestParam("searchNovelName") String searchNovelName,
                                    @PageableDefault(value = 6) Pageable pageable, Authentication authentication) {

        Page<NovelHead> novelHeads = novelHeadService.searchByNovelName(searchNovelName, pageable);
        model.addAttribute("searchResult", novelHeads);
        model.addAttribute("novelName", searchNovelName);
        UserInfo userInfo = userService.getUserInfo(CommonUtils.getUserNameFromAuthentication(authentication));
        if(userInfo == null){
            userInfo = new UserInfo();
        }
        model.addAttribute("userEmail", userInfo.getUserEmail());

        return NOVEL_SEARCH_RESULT_PAGE;
    }

}
