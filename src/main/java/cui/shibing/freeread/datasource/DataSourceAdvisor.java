package cui.shibing.freeread.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.logging.Logger;

import static cui.shibing.freeread.datasource.DataSourceType.MASTER;

@Aspect
public class DataSourceAdvisor {
    private Logger logger = Logger.getLogger(getClass().toString());
    /**
     * 定义切入点为dao层下的所有方法被调用时
     * */
    @Pointcut("execution(* cui.shibing.freeread.dao.*.*(..))")
    public void invokeDaoMethod(){}

    @Before("invokeDaoMethod()")
    public void beforeInvokeDaoMethod(JoinPoint joinPoint){//ProceedingPoint 只使用于环绕
        Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
        DataSourceTypeSetter annotation = method.getAnnotation(DataSourceTypeSetter.class);
        DataSourceType dataSourceType = MASTER;
        if(annotation!=null){
            dataSourceType = annotation.value();
            if(dataSourceType == MASTER)
                logger.info(method.getName()+":use master data source");
            else logger.info(method.getName()+":use slaver data source");
        }
        DynamicDataSource.DynamicDataSourceTypeHolder.setDatasourceType(dataSourceType);
    }

}
