package cui.shibing.freeread.app.chapter;

import cui.shibing.freeread.dto.NovelChapterInfoDto;
import cui.shibing.freeread.model.NovelChapter;
import cui.shibing.freeread.service.NovelChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("novelChapter")
public class NovelChapterController {
    /**
     * 小说章节内容页面
     */
    private static final String NOVEL_CHAPTER_PAGE = "main/novel_chapter_content";
    /**
     * 小说章节内容不存在页面
     */
    private static final String NO_CHAPTER_PAGE = "main/no_chapter";
    /**
     * 小说章节列表页面
     */
    private static final String NOVEL_CHAPTERLIST_PAGE = "main/chapter_list";
    @Autowired
    private NovelChapterService chapterService;

    /**
     * 小说章节内容页面
     **/
    @RequestMapping("novelChapter")
    public String novelChapter(Model model, @RequestParam("novelId") String novelId,
                               @RequestParam("chapterIndex") Integer chapterIndex) {
        if (!StringUtils.isEmpty(novelId) && chapterIndex != -1) {
            NovelChapter novelChapter = chapterService.searchByNovelHeadAndChapter(novelId, chapterIndex);
            if (novelChapter != null) {
                model.addAttribute("novelChapter", novelChapter);
                return NOVEL_CHAPTER_PAGE;
            }
            return NO_CHAPTER_PAGE;
        }
        return NO_CHAPTER_PAGE;
    }

    @RequestMapping("novelChapterList")
    public String novelChapterList(Model model, @RequestParam("novelId") String novelId,
                                   @PageableDefault(value = 50) Pageable pageable) {
        if (pageable != null && !StringUtils.isEmpty(novelId)) {
            Page<NovelChapterInfoDto> novelChapterInfo = chapterService.searchChapterInfoByNovelId(novelId, pageable);
            model.addAttribute("pageNovelContents", novelChapterInfo);
        }
        return NOVEL_CHAPTERLIST_PAGE;
    }

}
