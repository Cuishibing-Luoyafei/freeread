package cui.shibing.freeread.tools;

import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

public class CommonUtils {

    /**
     * 验证Pageable对象的有效性
     *
     * @param pageable pageable对象
     *
     * @return true 有效,false 无效
     */
    public static boolean validatePageable(Pageable pageable) {
        return pageable != null && pageable.getPageNumber() >= 1 && pageable.getPageSize() >= 1;
    }

    /**
     * 根据Authentication对象中获取用户名
     *
     * @param authentication
     *
     * @return 用户名
     */
    public static String getUserNameFromAuthentication(Authentication authentication) {
        return ((UserDetails) authentication.getPrincipal()).getUsername();
    }

}
