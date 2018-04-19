package cui.shibing.freeread.common;

import org.springframework.beans.BeanUtils;

import java.util.List;

import java.lang.RuntimeException;

import java.util.logging.Logger;

public class MyBeanUtils extends BeanUtils {
    
    private static Logger logger = Logger.getLogger(MyBeanUtils.class);

    /**
     * 复制List对象,对源List中的对象依次执行copyProperties操作
     *
     * @param source         源List对象
     * @param target         目标List对象
     * @param targetEleClass List中对象的Class对象
     */
    public static <T, E> boolean copyListProperties(List<T> source, List<E> target, Class<E> targetEleClass) {
        if (source == null || target == null || targetEleClass == null){
            logger.warning("copy failed");
            return false;
        }
        for (T s : source) {
            try {
                E t = targetEleClass.newInstance();
                copyProperties(s, t);
                target.add(t);
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }
}
