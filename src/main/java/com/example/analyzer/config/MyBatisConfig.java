//package com.example.analyzer.config;
//
//import com.baomidou.mybatisplus.annotation.DbType;
//import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
//import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
//import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//
///**
// * Mybatis支持*匹配扫描包
// *
// * @author valarchie
// */
//@Configuration
//@EnableTransactionManagement
//public class MyBatisConfig {
//
//    @Bean
//    public MybatisPlusInterceptor mybatisPlusInterceptor() {
//        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
//        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.POSTGRE_SQL));
//        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
//        return interceptor;
//    }
//
//
////    @Bean
////    public ConfigurationCustomizer configurationCustomizer(){
////        return configuration -> configuration.setUs
////    }
//    @Bean
//    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
//        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
//        transactionManager.setDataSource(dataSource);
//        return transactionManager;
//    }
//
//}
