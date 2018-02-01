package cui.shibing.freeread.datastrategy;

import java.util.Map;

/**
 * 分库分表策略
 * */
public interface DataSourceStrategy {
    
    /**
     * 获取数据库名
     * @param params Dao层方法的参数
     * @return 数据库名
     * */
    String getDataSourceName(Map<String, Object> params) throws Exception;
    
    /**
     * 获取数据库表名
     * @param params Dao层方法的参数
     * @return 数据库表名
     * */
    String getTableName(Map<String, Object> params) throws Exception;
    
}
