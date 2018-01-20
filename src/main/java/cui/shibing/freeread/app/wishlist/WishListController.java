package cui.shibing.freeread.app.wishlist;

import cui.shibing.freeread.common.CommonUtils;
import cui.shibing.freeread.common.Constant;
import cui.shibing.freeread.dto.JsonResponse;
import cui.shibing.freeread.model.WishItem;
import cui.shibing.freeread.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("wishlist")
public class WishListController {

    private static final String ADD_WISH_ITEM_RESULT = "main/operation_result" + Constant.NO_LEFT_LAYOUT;

    @Autowired
    private WishListService wishListService;

    @RequestMapping("addWishItem")
    public String addWishItem(Model model, WishItem wishItem, Authentication authentication) {
        String userName = CommonUtils.getUserNameFromAuthentication(authentication);
        wishItem.setUserName(userName);
        boolean isSuccess = wishListService.addWishItem(wishItem);
        JsonResponse response = new JsonResponse(isSuccess, "");
        if (isSuccess) {
            response.setMessage("添加成功");
        } else {
            response.setMessage("添加失败");
        }
        model.addAttribute("response", response);
        return ADD_WISH_ITEM_RESULT;
    }

}
