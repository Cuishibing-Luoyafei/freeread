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

    private DataSourceKeyHolder keyHolder;

    private List<DataSourceConfigObject> configObjects;//数据源配置对象

    private String defaultDataSourceName;

    public static class DataSourceConfigObject {
        private String dataSourceName;
        private List<String> masterUrls;
        private List<String> slaverUrls;

        public void setDataSourceName(String dataSourceName) {
            this.dataSourceName = dataSourceName;
        }

        public void setMasterUrls(List<String> masterUrls) {
            this.masterUrls = masterUrls;
        }

        public void setSlaverUrls(List<String> slaverUrls) {
            this.slaverUrls = slaverUrls;
        }
    }

    public void setConfigObjects(List<DataSourceConfigObject> configObjects) {
        this.configObjects = configObjects;
    }

    public void setDefaultDataSourceName(String defaultDataSourceName) {
        this.defaultDataSourceName = defaultDataSourceName;
    }

    public String getDefaultDataSourceName() {
        return defaultDataSourceName;
    }

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
            keyHolder = new DataSourceKeyHolder(configObjects);
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
        if (configObjects == null || configObjects.size() < 1) {
            throw new RuntimeException("数据源列表为空");
        }
        if (dataSourceClass == null) {
            throw new RuntimeException("没有配置数据源类型");
        }
        //暂时只支持org.apache.commons.dbcp.BasicDataSource
        if (!BasicDataSource.class.isAssignableFrom(dataSourceClass)) {
            throw new UnsupportedDataTypeException("暂时只支持org.apache.commons.dbcp.BasicDataSource");
        }

        Map<Object, Object> targetDataSources = new HashMap<>();
        resolveDataSources(targetDataSources, configObjects);
        if (targetDataSources.size() < 1) {
            throw new RuntimeException("无效的数据源配置");
        }
        this.setTargetDataSources(targetDataSources);
    }

    private static String generateDataSourceKey(DataSourceType type, String baseName, int index) {
        return type + baseName + DIVISION + index;
    }

    private void resolveDataSources(Map<Object, Object> targetDataSources,
                                    List<DataSourceConfigObject> configObjects)
            throws InstantiationException, IllegalAccessException {
        for (DataSourceConfigObject configObject : configObjects) {
            String dataSourceName = configObject.dataSourceName;
            for (int i = 0; i < configObject.masterUrls.size(); i++) {
                targetDataSources.put(generateDataSourceKey(MASTER, dataSourceName, i),
                        getDataSource(configObject.masterUrls.get(i)));
            }
            for (int i = 0; i < configObject.slaverUrls.size(); i++) {
                targetDataSources.put(generateDataSourceKey(SLAVER, dataSourceName, i),
                        getDataSource(configObject.slaverUrls.get(i)));
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

        DataSourceKeyHolder(List<DataSourceConfigObject> configObjects) {
            construct(configObjects);
        }

        private void construct(List<DataSourceConfigObject> configObjects) {
            if (configObjects == null) {
                throw new NullPointerException("configObjects is null");
            }
            dataSourceKeys = new HashMap<>();
            for (DataSourceConfigObject configObject : configObjects) {
                String baseName = configObject.dataSourceName;
                DataSourceKey[] keyGroup = new DataSourceKey[KEY_GROUP_SIZE];
                keyGroup[MASTER_INDEX] = translate(baseName, configObject.masterUrls, MASTER);
                keyGroup[SLAVER_INDEX] = translate(baseName, configObject.slaverUrls, SLAVER);
                dataSourceKeys.put(baseName, keyGroup);
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
