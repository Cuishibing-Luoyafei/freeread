package cui.shibing.freeread.datasource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.StringUtils;

import javax.activation.UnsupportedDataTypeException;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cui.shibing.freeread.datasource.DataSourceType.MASTER;
import static cui.shibing.freeread.datasource.DataSourceType.SLAVER;

public class DynamicDataSource extends AbstractRoutingDataSource {

    private Class<?> dataSourceClass;

    private Map<String, List<String>> masterSources;//主数据源url集合

    private Map<String, List<String>> slaverSources;//从数据源url集合

    private DataSourceKeyHolder keyHolder;

    /**
     * 该方法返回一个key,这个key用来索引数据源.
     * 关于读写分离的主从数据库,已经通过配置文件配置完成,这个方法只是提供key去寻找相应的数据源
     */
    @Override
    protected Object determineCurrentLookupKey() {
        DataSourceType dataSourceType = DynamicDataSourceInfoHolder.getDatasourceType();
        String baseDataSourceName = DynamicDataSourceInfoHolder.getDataSourceName();
        return keyHolder.getDataSourceKey(baseDataSourceName, dataSourceType);
    }

    private static final String DIVISION = "_";

    @Override
    public void afterPropertiesSet() {
        try {
            resolveDataSources();
            keyHolder = new DataSourceKeyHolder(masterSources, slaverSources);
        } catch (UnsupportedDataTypeException | InstantiationException | IllegalAccessException e) {
            logger.error(e);
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

    private void resolveDataSources() throws UnsupportedDataTypeException,
            InstantiationException, IllegalAccessException {
        if (masterSources == null || slaverSources == null || dataSourceClass == null) {
            throw new RuntimeException("数据源列表为空");
        }
        //暂时只支持org.apache.commons.dbcp.BasicDataSource
        if (!BasicDataSource.class.isAssignableFrom(dataSourceClass)) {
            throw new UnsupportedDataTypeException();
        }

        Map<Object, Object> targetDataSources = new HashMap<>();
        resolveDataSources(targetDataSources, masterSources, MASTER);
        resolveDataSources(targetDataSources, slaverSources, SLAVER);
        this.setTargetDataSources(targetDataSources);
    }

    private static String generateDataSourceKey(DataSourceType type, String baseName, int index) {
        return type + baseName + DIVISION + index;
    }

    private void resolveDataSources(Map<Object, Object> targetDataSources,
                                    Map<String, List<String>> dataSourceUrls,
                                    DataSourceType type)
            throws IllegalAccessException, InstantiationException {
        for (Map.Entry<String, List<String>> entry : dataSourceUrls.entrySet()) {
            String baseName = entry.getKey();
            List<String> urls = entry.getValue();
            for (int i = 0; i < urls.size(); i++) {
                targetDataSources.put(generateDataSourceKey(type, baseName, i), getDataSource(urls.get(i)));
            }
        }
    }

    private DataSource getDataSource(String dataSourceUrl) throws IllegalAccessException,
            InstantiationException {
        BasicDataSource dataSource = (BasicDataSource) dataSourceClass.newInstance();
        if (!StringUtils.isEmpty(dataSourceUrl)) {
            dataSource.setUrl(dataSourceUrl);
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

    public void setDataSourceClass(Class<?> dataSourceClass) {
        this.dataSourceClass = dataSourceClass;
    }

    public void setMasterSources(Map<String, List<String>> masterSources) {
        this.masterSources = masterSources;
    }

    public void setSlaverSources(Map<String, List<String>> slaverSources) {
        this.slaverSources = slaverSources;
    }

    /**
     * 使用ThreadLocal变量传递数据源相关的信息
     */
    public static class DynamicDataSourceInfoHolder {
        private static final ThreadLocal<DataSourceType> dataSourceType = new ThreadLocal<>();
        private static final ThreadLocal<String> dataSourceName = new ThreadLocal<>();
        private static final ThreadLocal<String> originalTableName = new ThreadLocal<>();
        private static final ThreadLocal<String> tableName = new ThreadLocal<>();

        static void setDataSourceName(String dataSourceNameStr) {
            dataSourceName.set(dataSourceNameStr);
        }

        static String getDataSourceName() {
            return dataSourceName.get();
        }

        static void setOriginalTableName(String tableNameStr) {
            originalTableName.set(tableNameStr);
        }

        public static String getOriginalTableName() {
            return originalTableName.get();
        }

        static void setTableName(String tableNameStr) {
            tableName.set(tableNameStr);
        }

        public static String getTableName() {
            return tableName.get();
        }

        static void setDatasourceType(DataSourceType dataSourceTypeRaw) {
            dataSourceType.set(dataSourceTypeRaw);
        }

        static DataSourceType getDatasourceType() {
            return dataSourceType.get();
        }
    }

    private static class DataSourceKeyHolder {
        private final int MASTER_INDEX = 0;
        private final int SLAVER_INDEX = 1;
        private final int KEY_GROUP_SIZE = 2;
        private Map<String, DataSourceKey[]> dataSourceKeys;

        DataSourceKeyHolder(Map<String, List<String>> masterDataSourceUrls,
                            Map<String, List<String>> slaverDataSourceUrls) {
            construct(masterDataSourceUrls, slaverDataSourceUrls);
        }

        private void construct(Map<String, List<String>> masterDataSourceUrls,
                               Map<String, List<String>> slaverDataSourceUrls) {
            if (masterDataSourceUrls == null || slaverDataSourceUrls == null) {
                throw new NullPointerException("masterDataSourceUrls or slaverDataSourceUrls is null");
            }
            dataSourceKeys = new HashMap<>();
            for (Map.Entry<String, List<String>> entry : masterDataSourceUrls.entrySet()) {
                DataSourceKey[] keyGroup = new DataSourceKey[KEY_GROUP_SIZE];
                keyGroup[MASTER_INDEX] = translate(entry.getKey(), entry.getValue(), MASTER);
                dataSourceKeys.put(entry.getKey(), keyGroup);
            }
            for (Map.Entry<String, List<String>> entry : slaverDataSourceUrls.entrySet()) {
                DataSourceKey[] keyGroup = dataSourceKeys.get(entry.getKey());
                if (keyGroup != null) {
                    keyGroup[SLAVER_INDEX] = translate(entry.getKey(), entry.getValue(), SLAVER);
                }
            }
        }

        private DataSourceKey translate(String baseName, List<String> urls, DataSourceType type) {
            if (urls != null && urls.size() > 0 && !StringUtils.isEmpty(baseName)) {
                DataSourceKey firstDataSourceKey = new DataSourceKey();
                firstDataSourceKey.key = generateDataSourceKey(type, baseName, 0);
                DataSourceKey currentDataSourceKey = firstDataSourceKey;
                for (int i = 1; i < urls.size(); i++) {
                    DataSourceKey tempName = new DataSourceKey();
                    tempName.key = generateDataSourceKey(type, baseName, i);
                    currentDataSourceKey.nextKey = tempName;
                    currentDataSourceKey = tempName;
                }
                currentDataSourceKey.nextKey = firstDataSourceKey;
                return firstDataSourceKey;
            }
            return null;
        }

        String getDataSourceKey(String dataSourceName, DataSourceType type) {
            String result = null;
            switch (type) {
                case MASTER: {
                    DataSourceKey[] keyGroup = dataSourceKeys.get(dataSourceName);
                    if (keyGroup != null) {
                        result = keyGroup[MASTER_INDEX].key;
                        keyGroup[MASTER_INDEX] = keyGroup[MASTER_INDEX].nextKey;
                    }
                }
                break;
                case SLAVER: {
                    DataSourceKey[] keyGroup = dataSourceKeys.get(dataSourceName);
                    if (keyGroup != null) {
                        result = keyGroup[SLAVER_INDEX].key;
                        keyGroup[SLAVER_INDEX] = keyGroup[SLAVER_INDEX].nextKey;
                    }
                }
                break;
            }
            return result;
        }

        private static class DataSourceKey {
            String key;
            DataSourceKey nextKey;
        }
    }
}
