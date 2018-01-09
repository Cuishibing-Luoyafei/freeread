package cui.shibing.freeread.app.novelhead;

import cui.shibing.freeread.common.Constant;
import cui.shibing.freeread.dto.JsonResponse;
import cui.shibing.freeread.model.NovelHead;
import cui.shibing.freeread.service.NovelHeadService;
import cui.shibing.freeread.service.SubscribNovelService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static cui.shibing.freeread.security.CustomAuthenticationLoginProcessFilter.getUserNameFromAuthentication;

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
    private static final String NOVEL_RANK_LIST_PAGE = "left/novel_ranking";
    /**
     * 小说搜索页面
     */
    private static final String NOVEL_SEARCH_RESULT_PAGE = "main/novelhead/novel_search_result" + Constant.BASE_LAYOUT;
    /**
     * 小说订阅结果页面
     */
    private static final String SUBSCRIB_NOVEL_RESULT_PAGE = "main/operation_result" + Constant.NO_LEFT_LAYOUT;

    @Autowired
    private NovelHeadService novelHeadService;

    @Autowired
    private SubscribNovelService subscribNovelService;

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

    @RequestMapping("novelSearchResultPage")
    public String novelSearchResultPage() {
        return NOVEL_SEARCH_RESULT_PAGE;
    }

    /**
     * 小说搜索结果
     */
    @RequestMapping("novelSearchResult")
    public String novelSearchResult(RedirectAttributes model, @RequestParam("searchNovelName") String searchNovelName,
                                    @PageableDefault(value = 6) Pageable pageable, Authentication authentication) {

        Page<NovelHead> novelHeads = novelHeadService.searchByNovelName(searchNovelName, pageable);
        model.addFlashAttribute("searchResult", novelHeads);
        model.addFlashAttribute("novelName", searchNovelName);

        return "redirect:/novelHead/novelSearchResultPage";
    }

    /**
     * 订阅一个小说(小说被收录时,通知订阅的用户)
     */
    @RequestMapping("addSubscribNovel")
    public String subscribeNovel(Model model, Authentication authentication,
                                 @RequestParam("novelName") String novelName) {
        SubscribNovelService.ServiceResult result = subscribNovelService.addSubscribNovel(novelName, getUserNameFromAuthentication(authentication));
        if (result.isSuccess) {
            JsonResponse jsonResponse = new JsonResponse(true, "订阅成功!我们会第一时间通知您！");
            model.addAttribute("response", jsonResponse);
            return SUBSCRIB_NOVEL_RESULT_PAGE;
        } else {
            //用户还没有设置邮箱,进入个人信息设置界面
            if (result.result == SubscribNovelService.Result.NO_EMAIL) {
                return "redirect:/user/userInfo";
            } else {
                JsonResponse jsonResponse = new JsonResponse();
                jsonResponse.setMessage(result.result.toString());
                model.addAttribute("response", jsonResponse);
                return SUBSCRIB_NOVEL_RESULT_PAGE;
            }
        }
    }

}
