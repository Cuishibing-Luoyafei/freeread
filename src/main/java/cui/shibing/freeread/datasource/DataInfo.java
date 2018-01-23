package cui.shibing.freeread.datasource;

/**
 * 用来获取分库分表信息的接口
 * */
public interface DataInfo {
    
    /**
     * 获取库的名称
     * */
    String getDataSourceName(Object key);
    
    /**
     * 获取表的名称
     * */
    String getTableName(Object key);
    
}
