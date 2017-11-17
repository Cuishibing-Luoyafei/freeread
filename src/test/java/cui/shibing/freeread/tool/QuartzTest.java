/**
 * Copyright © 1998-2017, Glodon Inc. All Rights Reserved.
 */
package cui.shibing.freeread.tool;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * 测试Quartz的定时器使用
 * </p>
 * 
 * @author luoyf
 * @since jdk1.6 2017年11月17日
 */

public class QuartzTest {
	public void quartz() {
		 System.out.println("luoyf测试Quartz，每3秒执行一次：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}
}
