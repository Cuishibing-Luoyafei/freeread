package cui.shibing.freeread.datastrategy;

import cui.shibing.freeread.datasource.DataInfo;

import java.util.Map;

public class SelectNovelHeadByIdStrategy implements DataInfo {
    @Override
    public String getDataSourceName(Map<String, Object> params) throws Throwable {
        return null;
    }

    @Override
    public String getTableName(Map<String, Object> params) throws Throwable {
        String novelId = (String) params.get("novelId");
        System.out.println(novelId);
        return "novel_head";
    }
}
