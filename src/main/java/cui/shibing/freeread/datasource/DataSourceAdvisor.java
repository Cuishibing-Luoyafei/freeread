package cui.shibing.freeread.datasource;

import cui.shibing.freeread.datastrategy.DataSourceStrategy;
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

    /**
     * 注入这个bean暂时只是为了获取默认的dataSourceName
     */
    @Autowired
    private DynamicDataSource dataSource;

    private Map<Method, DataSourceStrategy> dataSourceStrategyCache = new HashMap<>();
    
    /**
     * 定义切入点为dao层下的所有方法被调用时
     */
    @Pointcut("execution(* cui.shibing.freeread.dao.*.*(..))")
    public void invokeDaoMethod() {
    }

    private DataSourceStrategy getDataSourceStrategy(Method method,
                                                     DataSource annotation)
            throws IllegalAccessException, InstantiationException {
        DataSourceStrategy dataSourceStrategy;
        dataSourceStrategy = dataSourceStrategyCache.get(method);
        if (dataSourceStrategy == null) {
            dataSourceStrategy = annotation.dataSourceStrategy().newInstance();
            dataSourceStrategyCache.put(method, dataSourceStrategy);
        }
        return dataSourceStrategy;
    }

    @Before("invokeDaoMethod()")
    public void beforeInvokeDaoMethod(JoinPoint joinPoint) throws IllegalAccessException,
            InstantiationException {// ProceedingPoint 只使用于环绕
        Method method = ((MethodSignature) joinPoint.getSignature())
                .getMethod();
        DataSource annotation = method.getAnnotation(DataSource.class);
        
        if (annotation == null) {
            throw new NullPointerException("DataSource Annotation is null!");
        }

        String dataSourceName;
        String tableName;
        Map<String, Object> params = null;
        DataSourceStrategy dataSourceStrategy = null;
        /* 如果配置了数据库名*/
        if (!StringUtils.isEmpty(annotation.dataSourceName())) {
            dataSourceName = annotation.dataSourceName();
        } else {
            try {
                dataSourceStrategy = getDataSourceStrategy(method, annotation);
                params = getMethodParmas(joinPoint, method);
                dataSourceName = dataSourceStrategy.getDataSourceName(params);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("get data source key error!" + e.getMessage());
            }
        }

        if (StringUtils.isEmpty(dataSourceName)) {
            dataSourceName = dataSource.getDefaultDataSourceName();
        }

        /*表名在mybatis拦截器中使用,用于动态修改sql*/
        
        if (!StringUtils.isEmpty(annotation.tableName())) {
            tableName = annotation.tableName();
        } else {
            try {
                if (params == null) {
                    params = getMethodParmas(joinPoint, method);
                }
                if (dataSourceStrategy == null) {
                    dataSourceStrategy = getDataSourceStrategy(method, annotation);
                }
                tableName = dataSourceStrategy.getTableName(params);
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

    /**
     * 获取被拦截的方法的参数，返回参数名和参数的键值对。
     * 但是获取不到参数名，获取到的是类似于arg0,arg1这样的。。。
     * 想定用参数的下标代替参数名。
     */
    private Map<String, Object> getMethodParmas(JoinPoint joinPoint, Method targetMethod) {
        Map<String, Object> resultParams = new HashMap<>();
        Parameter[] parameters = targetMethod.getParameters();
        Object[] args = joinPoint.getArgs();
        if (parameters.length != args.length) {
            logger.warning("形参和实参不匹配!");
            return resultParams;
        }
        for (int i = 0; i < parameters.length; i++) {
            resultParams.put(parameters[i].getName(), args[i]);
        }
        return resultParams;
    }
}
