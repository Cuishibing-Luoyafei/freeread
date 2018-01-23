package cui.shibing.freeread.datasource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.StringUtils;

import javax.activation.UnsupportedDataTypeException;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static cui.shibing.freeread.datasource.DataSourceType.MASTER;
import static cui.shibing.freeread.datasource.DataSourceType.SLAVER;

/**
 * 使用顺序的算法选择数据源
 * 例如,一个"slaver"名称对应了n个读数据源,在使用"slaver"这个key获取数据源的时候按照顺序返回n个数据源中的某一个.
 * 第一次获取时返回第k个,那么第二次返回第k+1个
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private int masterDataSourceNum = 0;//主数据源的数量

    private int slaverDataSourceNum = 0;//从数据源的数量

    private AtomicInteger masterDataSourceIndex = new AtomicInteger(0);

    private AtomicInteger slaverDataSourceIndex = new AtomicInteger(0);

    private Class<?> dataSourceClass;

    private List<String> masterSourcePositions;//主数据源位置集合,就是url的集合

    private List<String> slaverSourcePositions;//从数据源位置集合,就是url的集合

    /**
     * 该方法返回一个key,这个key用来索引数据源.
     * 关于读写分离的主从数据库,已经通过配置文件配置完成,这个方法只是提供key去寻找相应的数据源
     */
    @Override
    protected Object determineCurrentLookupKey() {
        if (masterDataSourceNum == 0 || slaverDataSourceNum == 0) {
            throw new IllegalArgumentException("masterDataSourceNum or slaverDataSourceNum == 0");
        }
        DataSourceType dataSourceType = DynamicDataSourceInfoHolder.getDatasourceType();

        //这是一个主数据源
        if (dataSourceType == MASTER) {
            return processLookupKey(MASTER, masterDataSourceIndex, masterDataSourceNum);
        } else {//这是一个从数据源
            return processLookupKey(SLAVER, slaverDataSourceIndex, slaverDataSourceNum);
        }
    }

    private static final String DIVISION = "_";

    /**
     * 按顺序取数据源
     */
    private String processLookupKey(DataSourceType dataSourceType, AtomicInteger dataSourceIndex, int dataSouceNum) {
        if (dataSourceIndex.get() < dataSouceNum) {
            int temp = dataSourceIndex.getAndAdd(1);
            if (dataSourceIndex.get() >= dataSouceNum) {
                dataSourceIndex.set(0);
            }
            return dataSourceType + DIVISION + temp;
        }
        return null;
    }

    @Override
    public void afterPropertiesSet() {
        try {
            resolveDataSources();
        } catch (UnsupportedDataTypeException | InstantiationException | IllegalAccessException e) {
            logger.info(e);
            throw new RuntimeException(e);
        }
        super.afterPropertiesSet();//不调用父类的该方法的话,报数据源未初始化的异常
    }

    /**
     * 数据源的属性,还没有完善
     */
    private String dataSourceDriver;
    private String dataSourceUserName;
    private String dataSourcePassword;
    private Integer dataSourceInitialSize;
    private Integer dataSourceMaxActive = -1;
    private Integer dataSourceMaxIdle = -1;
    private Integer dataSourceMinIdle = -1;
    private Integer dataSourceMaxWait = -1;

    private void resolveDataSources() throws UnsupportedDataTypeException, InstantiationException, IllegalAccessException {
        if (masterSourcePositions == null || slaverSourcePositions == null || dataSourceClass == null) {
            throw new NullPointerException("ha ha ha");
        }
        //暂时只支持org.apache.commons.dbcp.BasicDataSource
        if (!BasicDataSource.class.isAssignableFrom(dataSourceClass)) {
            throw new UnsupportedDataTypeException();
        }
        masterDataSourceNum = masterSourcePositions.size();
        slaverDataSourceNum = slaverSourcePositions.size();
        Map<Object, Object> targetDataSources = new HashMap<>(masterDataSourceNum + slaverDataSourceNum);
        resolveMasterDataSources(targetDataSources);
        this.setTargetDataSources(targetDataSources);
    }

    private void resolveMasterDataSources(final Map<Object, Object> targetDataSources) throws IllegalAccessException, InstantiationException {
        for (int i = 0; i < masterSourcePositions.size(); i++) {
            String dataSourcePosition = masterSourcePositions.get(i);
            DataSource dataSource = getDataSource(dataSourcePosition);
            targetDataSources.put(MASTER + DIVISION + i, dataSource);
        }
        for (int i = 0; i < slaverSourcePositions.size(); i++) {
            String dataSourcePosition = slaverSourcePositions.get(i);
            DataSource dataSource = getDataSource(dataSourcePosition);
            targetDataSources.put(SLAVER + DIVISION + i, dataSource);
        }
    }

    private DataSource getDataSource(String dataSourcePostion) throws IllegalAccessException, InstantiationException {
        BasicDataSource dataSource = (BasicDataSource) dataSourceClass.newInstance();
        if (!StringUtils.isEmpty(dataSourcePostion)) {
            dataSource.setUrl(dataSourcePostion);
        }
        if (!StringUtils.isEmpty(dataSourceDriver))
            dataSource.setDriverClassName(dataSourceDriver);
        if (!StringUtils.isEmpty(dataSourceUserName))
            dataSource.setUsername(dataSourceUserName);
        if (!StringUtils.isEmpty(dataSourcePassword))
            dataSource.setPassword(dataSourcePassword);
        if (isRightIntegerValue(dataSourceInitialSize)) {
            dataSource.setInitialSize(dataSourceInitialSize);
        }
        if (isRightIntegerValue(dataSourceMaxActive)) {
            dataSource.setMaxActive(dataSourceMaxActive);
        }
        if (isRightIntegerValue(dataSourceMaxIdle)) {
            dataSource.setMaxIdle(dataSourceMaxIdle);
        }
        if (isRightIntegerValue(dataSourceMinIdle)) {
            dataSource.setMinIdle(dataSourceMinIdle);
        }
        if (isRightIntegerValue(dataSourceMaxWait)) {
            dataSource.setMaxWait(dataSourceMaxWait);
        }
        return dataSource;
    }

    private boolean isRightIntegerValue(Object object) {
        if (object != null && object instanceof Integer) {
            Integer val = (Integer) object;
            if (val > -1)
                return true;
        }
        return false;
    }

    public void setDataSourceDriver(String dataSourceDriver) {
        this.dataSourceDriver = dataSourceDriver;
    }

    public void setDataSourceUserName(String dataSourceUserName) {
        this.dataSourceUserName = dataSourceUserName;
    }

    public void setDataSourcePassword(String dataSourcePassword) {
        this.dataSourcePassword = dataSourcePassword;
    }

    public void setDataSourceInitialSize(Integer dataSourceInitialSize) {
        this.dataSourceInitialSize = dataSourceInitialSize;
    }

    public void setDataSourceMaxActive(Integer dataSourceMaxActive) {
        this.dataSourceMaxActive = dataSourceMaxActive;
    }

    public void setDataSourceMaxIdle(Integer dataSourceMaxIdle) {
        this.dataSourceMaxIdle = dataSourceMaxIdle;
    }

    public void setDataSourceMinIdle(Integer dataSourceMinIdle) {
        this.dataSourceMinIdle = dataSourceMinIdle;
    }

    public void setDataSourceMaxWait(Integer dataSourceMaxWait) {
        this.dataSourceMaxWait = dataSourceMaxWait;
    }

    public void setMasterSourcePositions(List<String> masterSourcePositions) {
        this.masterSourcePositions = masterSourcePositions;
    }

    public void setSlaverSourcePositions(List<String> slaverSourcePositions) {
        this.slaverSourcePositions = slaverSourcePositions;
    }

    public void setDataSourceClass(Class<?> dataSourceClass) {
        this.dataSourceClass = dataSourceClass;
    }

    /**
     * 使用ThreadLocal变量保存索引数据源的key,包级私有
     */
    static class DynamicDataSourceInfoHolder {
        private static final ThreadLocal<DataSourceType> dataSourceType = new ThreadLocal<>();
        private static final ThreadLocal<String> dataSourceName = new ThreadLocal<>();
        private static final ThreadLocal<String> tableName = new ThreadLocal<>();

        static void setDataSourceName(String dataSourceNameStr) {
            dataSourceName.set(dataSourceNameStr);
        }

        static String getDataSourceName() {
            return dataSourceName.get();
        }

        static void setTableName(String tableNameStr) {
            tableName.set(tableNameStr);
        }

        static String getTableName() {
            return tableName.get();
        }

        static void setDatasourceType(DataSourceType dataSourceTypeRaw) {
            dataSourceType.set(dataSourceTypeRaw);
        }

        static DataSourceType getDatasourceType() {
            return dataSourceType.get();
        }
    }
}
