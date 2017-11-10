package cui.shibing.freeread.tools;

import java.util.List;

import org.springframework.beans.BeanUtils;

public class MyBeanUtils extends BeanUtils{
	/*public static void copyListProperties(List source,List target) {
		if(source == null || target == null)
			return;
		for(Object s:source) {
			Object t =  new Object();
			copyProperties(s, t);
			target.add(t);
		}
	}*/
	
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
