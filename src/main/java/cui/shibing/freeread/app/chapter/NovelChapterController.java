package cui.shibing.freeread.app.chapter;

import cui.shibing.freeread.common.Constant;
import cui.shibing.freeread.dto.NovelChapterInfoDto;
import cui.shibing.freeread.model.NovelChapter;
import cui.shibing.freeread.service.NovelChapterService;
import cui.shibing.freeread.service.SecretNovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static cui.shibing.freeread.common.CommonUtils.getUserNameFromAuthentication;


@Controller
@RequestMapping("novelChapter")
public class NovelChapterController {
    /**
     * 小说章节内容页面
     */
    private static final String NOVEL_CHAPTER_PAGE = "main/novel_chapter_content" + Constant.NO_LEFT_LAYOUT;
    /**
     * 小说章节内容不存在页面
     */
    private static final String NO_CHAPTER_PAGE = "main/no_chapter" + Constant.NO_LEFT_LAYOUT;
    /**
     * 小说章节列表页面
     */
    private static final String NOVEL_CHAPTER_LIST_PAGE = "main/chapter_list" + Constant.NO_LEFT_LAYOUT;

    @Autowired
    private NovelChapterService chapterService;

    @Autowired
    private SecretNovelService secretNovelService;

    /**
     * 小说章节内容页面
     **/
    @RequestMapping("novelChapter")
    public String novelChapter(Model model, @RequestParam("novelId") String novelId,
                               @RequestParam("chapterIndex") Integer chapterIndex,
                               Authentication authentication) {
        NovelChapter novelChapter = chapterService.getChapterByNovelIdAndIndex(novelId, chapterIndex, getUserNameFromAuthentication(authentication));

        if (novelChapter != null) {
            model.addAttribute("novelChapter", novelChapter);
            return NOVEL_CHAPTER_PAGE;
        }

        return NO_CHAPTER_PAGE;
    }

    /**
     * 小说章节列表
     **/
    @RequestMapping("novelChapterList")
    public String novelChapterList(Model model, @RequestParam("novelId") String novelId,
                                   @PageableDefault(value = 50) Pageable pageable) {

        Page<NovelChapterInfoDto> novelChapterInfo = chapterService.searchChapterInfoByNovelId(novelId, pageable);
        model.addAttribute("pageNovelContents", novelChapterInfo);

        return NOVEL_CHAPTER_LIST_PAGE;
    }

}
