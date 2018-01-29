package cui.shibing.freeread.datastrategy;

import cui.shibing.freeread.datasource.DataInfo;

import java.util.Map;

public class DefaultDataSourceStrategy implements DataInfo {
    @Override
    public String getDataSourceName(Map<String, Object> params) {
        return null;
    }

    @Override
    public String getTableName(Map<String, Object> params) {
        return null;
    }
}
