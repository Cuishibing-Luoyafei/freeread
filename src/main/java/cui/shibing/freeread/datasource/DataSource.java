package cui.shibing.freeread.datasource;

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
	 * 标识用哪一个库，如果配置了这个属性，就使用配置的值，否则需要进一步判断
	 */
	String dataSourceName() default "";

	/**
	 * 标识sql语句中原始的表名
	 */
	String originalTableName() default "";

	/**
	 * 标识用哪一张表，如果配置了这个属性，就使用配置的值，否则需要进一步判断
	 */
	String tableName() default "";

	/**
	 * 获取分库分表信息的接口类，在需要动态获取分库分表信息时会动态生成该类的实例来获取
	 */
	Class<? extends DataInfo> dataInfo() default DefaultDataSourceStrategy.class;

	/**
	 * 标识主从库
	 */
	DataSourceType value() default MASTER;
}
