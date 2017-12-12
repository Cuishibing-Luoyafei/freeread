/**
* Copyright © 1998-2017, Glodon Inc. All Rights Reserved.
*/
package cui.shibing.freeread.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
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
	
	private static final String spider_novel_address_redis = "spider_novel_address_redis";
	
	@Test
	public void useRedisTest() {
		ListOperations<String, String> list = redisTemplate.opsForList();
		List<String> addressList = list.range(spider_novel_address_redis, 0, -1);
		System.out.println(addressList);
	}
}
