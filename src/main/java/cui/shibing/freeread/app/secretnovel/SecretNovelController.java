package cui.shibing.freeread.app.secretnovel;

import cui.shibing.freeread.model.SecretNovel;
import cui.shibing.freeread.service.SecretNovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static cui.shibing.freeread.security.CustomAuthenticationLoginProcessFilter.getUserNameFromAuthentication;

/**
 * 书架相关的Controller
 */
@Controller
@RequestMapping("secretNovel")
public class SecretNovelController {

    @Autowired
    private SecretNovelService secretNovelService;

    private static final String SECRET_NOVELS_PAGE = "main/secretnovel/list_secret_novel";

    /**
     * 用于异步添加收藏
     * */
    @RequestMapping("addSecretNovelAsyn")
    @ResponseBody
    public JsonResponse addSecretNovel(@RequestParam("novelId") String novelId, Authentication authentication) {
        /* TODO:提示信息相关不应该硬编码 */
        JsonResponse response = new JsonResponse(false, "error");
        if (!StringUtils.isEmpty(novelId) && authentication != null) {
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
        }
        return response;
    }

    private static final String OPERATION_RESULT_PAGE = "main/operation_result";

    @RequestMapping("addSecretNovel")
    public String addSecretNovel(Model model,@RequestParam("novelId") String novelId, Authentication authentication){
        JsonResponse response = addSecretNovel(novelId,authentication);
        model.addAttribute("response",response);
        return OPERATION_RESULT_PAGE;
    }

    @RequestMapping("removeSecretNovelAsyn")
    @ResponseBody
    public JsonResponse removeSecretNovel(@RequestParam("novelId") String novelId, Authentication authentication) {
        JsonResponse response = new JsonResponse(false, "error!");
        if (!StringUtils.isEmpty(novelId) && authentication != null) {
            String userName = getUserNameFromAuthentication(authentication);
            boolean isSuccess = secretNovelService.removeSecretNovel(userName, novelId);
            response.setIsSuccess(isSuccess);
            if (isSuccess) {
                response.setMessage("删除成功!");
            } else {
                response.setMessage("删除失败!");
            }
        }
        return response;
    }

    @RequestMapping("removeSecretNovel")
    public String removelSecretNovel(Model model, @RequestParam("novelId") String novelId, Authentication authentication) {
        JsonResponse response = removeSecretNovel(novelId, authentication);
        model.addAttribute("response", response);
        return OPERATION_RESULT_PAGE;
    }

    @RequestMapping("listSecretNovels")
    public String listSecretNovels(Model model, Authentication authentication) {
        if (authentication != null) {
            List<SecretNovel> secretNovels = secretNovelService.getSecretNovels(getUserNameFromAuthentication(authentication));
            model.addAttribute("secretNovels", secretNovels);
        }
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

    public static class JsonResponse {
        private boolean isSuccess;
        private String message;
        private Object data;

        public JsonResponse() {
        }

        public JsonResponse(boolean isSuccess, String message) {
            this.isSuccess = isSuccess;
            this.message = message;
        }

        public boolean getIsSuccess() {
            return isSuccess;
        }

        public void setIsSuccess(boolean isSuccess) {
            this.isSuccess = isSuccess;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }
    }

}
