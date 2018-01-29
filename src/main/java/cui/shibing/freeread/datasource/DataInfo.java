package cui.shibing.freeread.datasource;

import java.util.Map;

/**
 * 用来获取分库分表信息的接口
 * */
public interface DataInfo {
    
    /**
     * 获取库的名称
     * */
    String getDataSourceName(Map<String, Object> params);
    
    /**
     * 获取表的名称
     * */
    String getTableName(Map<String, Object> params);
    
}
