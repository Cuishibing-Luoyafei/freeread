package cui.shibing.freeread.datasource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static cui.shibing.freeread.datasource.DataSourceType.MASTER;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface DataSource {
    DataSourceType value() default MASTER;
}
