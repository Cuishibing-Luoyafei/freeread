package cui.shibing.freeread.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;
import java.util.logging.Logger;

import static cui.shibing.freeread.datasource.DataSourceType.MASTER;

@Aspect
public class DataSourceAdvisor {
    private Logger logger = Logger.getLogger(getClass().toString());
    
    /**
     * 定义切入点为dao层下的所有方法被调用时
     */
    @Pointcut("execution(* cui.shibing.freeread.dao.*.*(..))")
    public void invokeDaoMethod() {
    }
    
    @Before("invokeDaoMethod()")
    public void beforeInvokeDaoMethod(JoinPoint joinPoint) {// ProceedingPoint
                                                            // 只使用于环绕
        Method method = ((MethodSignature) joinPoint.getSignature())
                .getMethod();
        DataSource annotation = method.getAnnotation(DataSource.class);
        
        if (annotation == null) {
            throw new Exception("DataSource Annotation not null!");
        }
        
        String dataSourceName = "";
        String tableName = "";
        Map<String, Object> params = null;
        DataInfo dataInfo = null;
        /**
         * 如果配置了数据库的名字
         * */
        if (!StringUtils.isEmpty(annotation.dataSourceName())) {
            dataSourceName = annotation.dataSourceName();
        } else {
            dataInfo = annotation.dataInfo().newInstance();
            params = getMethodParmas(joinPoint);
            dataSourceName = dataInfo.getDataSourceName(params);
        }
        
        if (!StringUtils.isEmpty(annotation.tableName())) {
            tableName = annotation.tableName();
        } else {
            if(params == null){
                params = getMethodParmas(joinPoint);
            }
            tableName = dataInfo.getTableName(params); 
        }
        
        DataSourceType dataSourceType = MASTER;
        dataSourceType = annotation.value();
        
        DynamicDataSource.DynamicDataSourceTypeHolder
                .setDatasourceType(dataSourceType);
    }
    
    private Map<String, Object> getMethodParmas(JoinPoint joinPoint) {
        //TODO:获取方法的参数值
        return null;
    }
}
