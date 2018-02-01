package cui.shibing.freeread.datasource;

import cui.shibing.freeread.datastrategy.DataSourceStrategy;
import cui.shibing.freeread.datastrategy.DefaultDataSourceStrategy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static cui.shibing.freeread.datasource.DataSourceType.MASTER;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface DataSource {

	/**
	 * 标识用哪一个库，如果配置了这个属性，就使用配置的值，否则需要进一步判断(根据配置的分库分表策略)
	 */
	String dataSourceName() default "";

	/**
	 * 标识sql语句中原始的表名，主要是为了在替换表名的时候比较方便
	 */
	String originalTableName() default "";

	/**
	 * 标识用哪一张表，如果配置了这个属性，就使用配置的值，否则需要进一步判断(根据配置的分库分表策略)
	 */
	String tableName() default "";

	/**
	 * 分库分表策略的实现类
	 */
	Class<? extends DataSourceStrategy> dataSourceStrategy() default DefaultDataSourceStrategy.class;

	/**
	 * 主从库标识
	 */
	DataSourceType value() default MASTER;
}
