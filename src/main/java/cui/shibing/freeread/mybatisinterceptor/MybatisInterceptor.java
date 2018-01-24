package cui.shibing.freeread.mybatisinterceptor;

import cui.shibing.freeread.datasource.DynamicDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;

import java.util.Properties;

/**
 * 处理分表的拦截器
 * *
 */
public class MybatisInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        String tableName = DynamicDataSource.DynamicDataSourceInfoHolder.getTableName();
        //TODO:未完成,获取sql语句并根据表名修改sql语句
        return null;
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
