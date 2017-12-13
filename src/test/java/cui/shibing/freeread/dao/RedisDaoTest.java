/**
* Copyright © 1998-2017, Glodon Inc. All Rights Reserved.
*/
package cui.shibing.freeread.dao;

import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 
 * <p>
 * 使用Redis的测试类
 * </p>
 * @author luoyf
 * @since jdk1.8
 * 2017年12月11日
 *  
 */
public class RedisDaoTest extends CustomDaoTest {

	@Autowired
	private StringRedisTemplate redisTemplate;
	
	private static final String spider_novel_address_set_redis = "spider_novel_address_set_redis";
	
	@Test
	public void useRedisTest() {
		SetOperations<String, String> addressSetOps = redisTemplate.opsForSet();
		Set<String> setStr = addressSetOps.members(spider_novel_address_set_redis);
		System.out.println(setStr);
	}
}
