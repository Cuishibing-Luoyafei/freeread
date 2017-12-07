package cui.shibing.freeread.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.concurrent.atomic.AtomicInteger;

import static cui.shibing.freeread.datasource.DataSourceType.MASTER;
import static cui.shibing.freeread.datasource.DataSourceType.SLAVER;

/**
 * 使用顺序的算法选择数据源
 * 例如,一个"slaver"名称对应了n个读数据源,在使用"slaver"这个key获取数据源的时候按照顺序返回n个数据源中的某一个.
 * 第一次获取时返回第k个,那么第二次返回第k+1个
 * */
public class DynamicDataSource extends AbstractRoutingDataSource{

    public int getMasterDataSourceNum() {
        return masterDataSourceNum;
    }

    public void setMasterDataSourceNum(int masterDataSourceNum) {
        this.masterDataSourceNum = masterDataSourceNum;
    }

    public int getSlaverDataSourceNum() {
        return slaverDataSourceNum;
    }

    public void setSlaverDataSourceNum(int slaverDataSourceNum) {
        this.slaverDataSourceNum = slaverDataSourceNum;
    }

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
        DataSourceType dataSourceType = DynamicDataSourceTypeHolder.getDatasourceType();

        //这是一个主数据源
        if(dataSourceType == MASTER){
            return processLookupKey(MASTER,masterDataSourceIndex,masterDataSourceNum);
        }else{//这是一个从数据源
            return processLookupKey(SLAVER,slaverDataSourceIndex,slaverDataSourceNum);
        }
    }

    private String processLookupKey(DataSourceType dataSourceType,AtomicInteger dataSourceIndex,int dataSouceNum){
        if(dataSourceIndex.get() < dataSouceNum){
            int temp = dataSourceIndex.getAndAdd(1);
            if(dataSourceIndex.get() >= dataSouceNum){
                dataSourceIndex.set(0);
            }
            return dataSourceType+"_"+temp;
        }
        return null;
    }

    /**
     * 使用ThreadLocal变量保存索引数据源的key,包级私有
     * */
    static class DynamicDataSourceTypeHolder {
        private static final ThreadLocal<DataSourceType> dataSourceType = new ThreadLocal<>();

        static void setDatasourceType(DataSourceType dataSourceType){
            DynamicDataSourceTypeHolder.dataSourceType.set(dataSourceType);
        }

        static DataSourceType getDatasourceType(){
            return dataSourceType.get();
        }
    }
}
