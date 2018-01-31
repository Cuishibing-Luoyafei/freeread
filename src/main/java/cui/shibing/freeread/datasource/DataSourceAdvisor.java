package cui.shibing.freeread.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Aspect
public class DataSourceAdvisor {
    private Logger logger = Logger.getLogger(getClass().toString());

    @Autowired
    private DynamicDataSource dataSource;
    
    /**
     * 定义切入点为dao层下的所有方法被调用时
     */
    @Pointcut("execution(* cui.shibing.freeread.dao.*.*(..))")
    public void invokeDaoMethod() {
    }
    
    @Before("invokeDaoMethod()")
    public void beforeInvokeDaoMethod(JoinPoint joinPoint) throws IllegalAccessException, InstantiationException {// ProceedingPoint 只使用于环绕
        Method method = ((MethodSignature) joinPoint.getSignature())
                .getMethod();
        DataSource annotation = method.getAnnotation(DataSource.class);
        
        if (annotation == null) {
            throw new NullPointerException("DataSource Annotation not null!");
        }
        
        String dataSourceName = "";
        String tableName = "";
        Map<String, Object> params = null;
        DataInfo dataInfo = null;
        /**
         * 如果配置了数据库名
         * */
        if (!StringUtils.isEmpty(annotation.dataSourceName())) {
            dataSourceName = annotation.dataSourceName();
        } else {
            try {
                dataInfo = annotation.dataInfo().newInstance();
                params = getMethodParmas(joinPoint, method);
                dataSourceName = dataInfo.getDataSourceName(params);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("get data source key error!" + e.getMessage());
            }
        }

        if (StringUtils.isEmpty(dataSourceName)) {
            dataSourceName = dataSource.getDefaultDataSourceName();
        }

        /**
         * 表名在mybatis拦截器中使用,用于动态修改sql
         * */
        
        if (!StringUtils.isEmpty(annotation.tableName())) {
            tableName = annotation.tableName();
        } else {
            try {
                if (params == null) {
                    params = getMethodParmas(joinPoint, method);
                }
                if (dataInfo == null) {
                    dataInfo = annotation.dataInfo().newInstance();
                }
                tableName = dataInfo.getTableName(params);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("get table key error!" + e.getMessage());
            }
        }
        DynamicDataSource.DynamicDataSourceInfoHolder.setDataSourceName(dataSourceName);
        DynamicDataSource.DynamicDataSourceInfoHolder.setTableName(tableName);
        DynamicDataSource.DynamicDataSourceInfoHolder.setOriginalTableName(annotation.originalTableName());
        DynamicDataSource.DynamicDataSourceInfoHolder
                .setDatasourceType(annotation.value());
    }

    private Map<String, Object> getMethodParmas(JoinPoint joinPoint, Method targetMethod) {
        Map<String, Object> resultParams = new HashMap<>();
        Parameter[] parameters = targetMethod.getParameters();
        Object[] args = joinPoint.getArgs();
        if (parameters.length != args.length) {
            logger.info("形参和实参不匹配!");
            return resultParams;
        }
        for (int i = 0; i < parameters.length; i++) {
            resultParams.put(parameters[i].getName(), args[i]);
        }
        return resultParams;
    }
}
