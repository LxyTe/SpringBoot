//package com.dist.datarouse;
//
//import javax.sql.DataSource;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
///**
// * 
// * @author 刘新杨
// *   菩提本无树，
// *   明镜亦非台。
// *   
// *   数据源1
// */
//@Configuration //表示扫描配置信息，比如创建Bean，数据源等。
//@MapperScan(basePackages = "com.dist.service", sqlSessionFactoryRef = "test1SqlSessionFactory")
//public class Datasource1 {
// 
//	@Bean(name = "test1DataSource")
//	@Primary
//	@ConfigurationProperties(prefix = "spring.datasource.test1")//表示配置请求文件的头部
//	public DataSource testDataSource() {
//		return DataSourceBuilder.create().build();
//	}
//	
//	
//	@Bean(name = "test1SqlSessionFactory")
//	@Primary
//	public SqlSessionFactory testSqlSessionFactory(@Qualifier("test1DataSource") DataSource dataSource)
//			throws Exception {
//		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//		bean.setDataSource(dataSource);
////		bean.setMapperLocations(
////				new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/test1/*.xml"));
//		return bean.getObject();
//	}
//}
