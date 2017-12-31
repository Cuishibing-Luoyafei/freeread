package cui.shibing.freeread.tools;

import org.springframework.beans.BeanUtils;

import java.util.List;

public class MyBeanUtils extends BeanUtils{

    /**
     * 复制List对象,对源List中的对象依次执行copyProperties操作
     *
     * @param source 源List对象
     * @param target 目标List对象
     * @param targetEleClass List中对象的Class对象
     */
    public static <T, E> void copyListProperties(List<T> source,List<E> target,Class<E> targetEleClass) {
		if(source == null || target == null)
			return;
		for(T s:source) {
			try {
				E t = targetEleClass.newInstance();
				copyProperties(s, t);
				target.add(t);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
				target.clear();
			}
		}
	}
}
