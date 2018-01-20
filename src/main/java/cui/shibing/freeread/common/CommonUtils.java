package cui.shibing.freeread.common;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;

public class CommonUtils {

    /**
     * 验证Pageable对象的有效性
     *
     * @param pageable pageable对象
     *
     * @return true 有效,false 无效
     */
    public static boolean validatePageable(Pageable pageable) {
        return pageable != null && pageable.getPageNumber() >= 0 && pageable.getPageSize() > 0;
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

    private static Object EMPTY_PAGE_IMPL;

    /**
     * 获取一个空的PageImpl对象
     */
    public static <T> Page<T> emptyPageObject(Pageable pageable) {
        if (EMPTY_PAGE_IMPL == null) {
            EMPTY_PAGE_IMPL = new PageImpl<>(Collections.emptyList(), pageable, 0);
            return (Page<T>) EMPTY_PAGE_IMPL;
        }
        return (Page<T>) EMPTY_PAGE_IMPL;
    }

}
