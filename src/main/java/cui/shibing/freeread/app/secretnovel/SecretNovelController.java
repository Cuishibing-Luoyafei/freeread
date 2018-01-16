package cui.shibing.freeread.app.secretnovel;

import cui.shibing.freeread.common.Constant;
import cui.shibing.freeread.dto.JsonResponse;
import cui.shibing.freeread.model.SecretNovel;
import cui.shibing.freeread.service.SecretNovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static cui.shibing.freeread.tools.CommonUtils.getUserNameFromAuthentication;

/**
 * 书架相关的Controller
 */
@Controller
@RequestMapping("secretNovel")
public class SecretNovelController {

    private static final String SECRET_NOVELS_PAGE = "main/secretnovel/list_secret_novel" + Constant.NO_LEFT_LAYOUT;

    private static final String OPERATION_RESULT_PAGE = "main/operation_result" + Constant.NO_LEFT_LAYOUT;

    @Autowired
    private SecretNovelService secretNovelService;

    /**
     * 用于异步添加收藏
     */
    @RequestMapping("addSecretNovelAsyn")
    @ResponseBody
    public JsonResponse addSecretNovel(@RequestParam("novelId") String novelId, Authentication authentication) {
        /* TODO:提示信息相关不应该硬编码 */
        JsonResponse response = new JsonResponse(false, "error");

        String userName = getUserNameFromAuthentication(authentication);
        List<SecretNovel> novelList = secretNovelService.getSecretNovels(userName);
        if (containSecretNovel(novelId, novelList)) {
            //书架中已经存在了
            response.setIsSuccess(true);
            response.setMessage("已经添加过了!");
        } else {
            if (secretNovelService.addSecretNovel(userName, novelId)) {
                response.setIsSuccess(true);
                response.setMessage("添加成功!");
            }
        }

        return response;
    }

    /**
     * 添加一本书到我的书架
     */
    @RequestMapping("addSecretNovel")
    public String addSecretNovel(Model model, @RequestParam("novelId") String novelId, Authentication authentication) {
        JsonResponse response = addSecretNovel(novelId, authentication);
        model.addAttribute("response", response);
        return OPERATION_RESULT_PAGE;
    }

    @RequestMapping("removeSecretNovelAsyn")
    @ResponseBody
    public JsonResponse removeSecretNovel(@RequestParam("novelId") String novelId, Authentication authentication) {
        JsonResponse response = new JsonResponse(false, "error!");

        String userName = getUserNameFromAuthentication(authentication);
        boolean isSuccess = secretNovelService.removeSecretNovel(userName, novelId);
        response.setIsSuccess(isSuccess);
        if (isSuccess) {
            response.setMessage("删除成功!");
        } else {
            response.setMessage("删除失败!");
        }

        return response;
    }

    /**
     * 从书架中删除一本小说
     */
    @RequestMapping("removeSecretNovel")
    public String removelSecretNovel(Model model, @RequestParam("novelId") String novelId, Authentication authentication) {
        JsonResponse response = removeSecretNovel(novelId, authentication);
        model.addAttribute("response", response);
        return OPERATION_RESULT_PAGE;
    }

    @RequestMapping("listSecretNovels")
    public String listSecretNovels(Model model, Authentication authentication) {

        List<SecretNovel> secretNovels = secretNovelService.getSecretNovels(getUserNameFromAuthentication(authentication));
        model.addAttribute("secretNovels", secretNovels);

        return SECRET_NOVELS_PAGE;
    }

    private boolean containSecretNovel(String novelId, List<SecretNovel> novelList) {
        Assert.isTrue(novelList != null);
        for (SecretNovel secretNovel : novelList) {
            if (secretNovel.getNovelId().equals(novelId)) {
                return true;
            }
        }
        return false;
    }
}
