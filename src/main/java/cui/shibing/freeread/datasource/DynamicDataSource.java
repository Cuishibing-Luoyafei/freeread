package cui.shibing.freeread.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用顺序的算法选择数据源
 * 例如,一个"slaver"名称对应了n个读数据源,在使用"slaver"这个key获取数据源的时候按照顺序返回n个数据源中的某一个.
 * 第一次获取时返回第k个,那么第二次返回第k+1个
 * */
public class DynamicDataSource extends AbstractRoutingDataSource{

    private static final String MASTER_NAME = "master";

    private static final String SLAVER_NAME = "slaver";

    private int masterDataSourceNum = 0;//主数据源的数量

    private int slaverDataSourceNum = 0;//从数据源的数量

    private AtomicInteger masterDataSourceIndex = new AtomicInteger(0);

    private AtomicInteger slaverDataSourceIndex = new AtomicInteger(0);

    /**
     * 该方法返回一个key,这个key用来索引数据源.
     * 关于读写分离的主从数据库,已经通过配置文件配置完成,这个方法只是提供key去寻找相应的数据源
     * */
    @Override
    protected Object determineCurrentLookupKey() {
        if(masterDataSourceNum ==0 || slaverDataSourceNum == 0){
            throw new IllegalArgumentException("masterDataSourceNum or slaverDataSourceNum == 0");
        }
        String lookupKey = DynamicDataSourceKeyHolder.getDataSourceKey();

        //这是一个主数据源
        if(lookupKey.equals(MASTER_NAME)){
            return processLookupKey(MASTER_NAME,masterDataSourceIndex,masterDataSourceNum);
        }else{//这是一个从数据源
            return processLookupKey(SLAVER_NAME,slaverDataSourceIndex,slaverDataSourceNum);
        }
    }

    private String processLookupKey(String dataSourceName,AtomicInteger dataSourceIndex,int dataSouceNum){
        if(dataSourceIndex.get() < dataSouceNum){
            int temp = dataSourceIndex.getAndAdd(1);
            if(dataSourceIndex.get() >= dataSouceNum){
                dataSourceIndex.set(0);
            }
            return dataSourceName+"_"+temp;
        }
        return null;
    }

    /**
     * 使用ThreadLocal变量保存索引数据源的key
     * */
    public static class DynamicDataSourceKeyHolder{
        private static final ThreadLocal<String> dataSourceKey = new ThreadLocal<>();

        public static void setDataSourceKey(String key){
            dataSourceKey.set(key);
        }

        public static String getDataSourceKey(){
            return dataSourceKey.get();
        }
    }
}
