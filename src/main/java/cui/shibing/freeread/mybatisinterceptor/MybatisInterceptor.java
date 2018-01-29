package cui.shibing.freeread.mybatisinterceptor;

import java.sql.Connection;
import java.util.Properties;
import java.util.logging.Logger;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.util.StringUtils;

import cui.shibing.freeread.datasource.DynamicDataSource;

/**
 * 处理分表的拦截器
 * 
 */
@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args= {Connection.class,Integer.class})})
public class MybatisInterceptor implements Interceptor {
	private Logger logger = Logger.getLogger(getClass().toString());
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        String originalTableName = DynamicDataSource.DynamicDataSourceInfoHolder.getOriginalTableName();
        String tableName = DynamicDataSource.DynamicDataSourceInfoHolder.getTableName();
        logger.info("tableName:"+tableName);
        logger.info("originalTableName:"+originalTableName);
        if(StringUtils.isEmpty(tableName)||StringUtils.isEmpty(originalTableName)) {
        	//没有配置表名或者没有设置原始表名,不做任何处理
        	return invocation.proceed();
        }
        MetaObject metaObject = SystemMetaObject.forObject(invocation.getTarget());
		String sql = (String) metaObject.getValue("delegate.boundSql.sql");
		logger.info("处理前的sql:"+sql);
		sql = sql.replace(originalTableName, tableName);
		logger.info("处理后的sql:"+sql);
		metaObject.setValue("delegate.boundSql.sql", sql);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
