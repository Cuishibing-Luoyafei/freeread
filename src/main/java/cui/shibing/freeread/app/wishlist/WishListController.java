package cui.shibing.freeread.app.wishlist;

import cui.shibing.freeread.common.CommonUtils;
import cui.shibing.freeread.common.Constant;
import cui.shibing.freeread.dto.JsonResponse;
import cui.shibing.freeread.model.WishItem;
import cui.shibing.freeread.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("wishlist")
public class WishListController {

    private static final String ADD_WISH_ITEM_RESULT = "main/operation_result" + Constant.NO_LEFT_LAYOUT;

    private static final String LIST_WISH_ITEM_PAGE = "main/wishlist/list_wish_item" + Constant.NO_LEFT_LAYOUT;

    private static final String REMOVE_WISH_ITEM_PAGE = "main/operation_result" + Constant.NO_LEFT_LAYOUT;

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

    @RequestMapping("removeWishItem")
    public String removeWishItem(Model model, @RequestParam("userName") String userName,
                                 @RequestParam("novelName") String novelName,
                                 @RequestParam("userEmail") String userEmail){
        boolean isSuccess = wishListService.removeWishItem(userName,userEmail,novelName);
        JsonResponse response = new JsonResponse(isSuccess, "");
        if (isSuccess) {
            response.setMessage("删除成功");
        } else {
            response.setMessage("删除失败");
        }
        model.addAttribute("response", response);
        return REMOVE_WISH_ITEM_PAGE;
    }

    @RequestMapping("listWishItem")
    public String listWishItem(Model model, Authentication authentication, @PageableDefault Pageable pageable) {
        Page<WishItem> result = wishListService.getWishItemFromUser(CommonUtils.getUserNameFromAuthentication(authentication), pageable);
        model.addAttribute("wishItems", result);
        return LIST_WISH_ITEM_PAGE;
    }

}
