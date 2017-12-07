package cui.shibing.freeread.datasource;

public enum DataSourceType {
    MASTER("master"),
    SLAVER("slaver");

    private String dataSourceType;

    DataSourceType(String dataSourceType){
        this.dataSourceType = dataSourceType;
    }

    @Override
    public String toString(){
        return this.dataSourceType;
    }
}
