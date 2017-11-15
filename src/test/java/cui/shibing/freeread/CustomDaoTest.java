package cui.shibing.freeread;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml" })
@Rollback(value= true)
@Transactional(transactionManager = "transactionManager")
public class CustomDaoTest extends AbstractTransactionalJUnit4SpringContextTests{
	@Override
	protected void executeSqlScript(String sqlResourcePath,
			boolean continueOnError) throws DataAccessException {
		super.executeSqlScript(sqlResourcePath, continueOnError);
	}

	@Test
	public void nullTestMethod(){

	}
}
