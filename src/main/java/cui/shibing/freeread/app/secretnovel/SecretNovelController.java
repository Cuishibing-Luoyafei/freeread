package cui.shibing.freeread.app.secretnovel;

import com.google.gson.Gson;
import cui.shibing.freeread.model.SecretNovel;
import cui.shibing.freeread.service.SecretNovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 书架相关的Controller
 */
@Controller
@RequestMapping("secretNovel")
public class SecretNovelController {

    @Autowired
    private SecretNovelService secretNovelService;

    @RequestMapping("addSecretNovel")
    @ResponseBody
    public String addSecretNovel(@RequestParam("novelId") String novelId, Authentication authentication) {
        /* TODO:提示信息相关不应该硬编码 */
        JsonResponse response = new JsonResponse(false, "error");
        if (!StringUtils.isEmpty(novelId) && authentication != null) {
            String userName = ((UserDetails) authentication.getPrincipal()).getUsername();
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
        return new Gson().toJson(response);
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

    private static class JsonResponse {
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
