//Copyright (c) 2016 by Disy Informationssysteme GmbH
//package net.disy.biggis.kef.app;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//// NOT_PUBLISHED
//@Configuration
//public class DataSourceConfiguration {
//
//  @Value("${db.driver}")
//  private String driverClassName;
//
//  @Value("${db.url}")
//  private String jdbcUrl;
//
//  @Value("${db.username}")
//  private String username;
//
//  @Value("${db.password}")
//  private String password;
//
//  @Bean
//  @Primary
//  public DataSource dataSource() {
//    DriverManagerDataSource dataSource = new DriverManagerDataSource();
//    dataSource.setDriverClassName(driverClassName);
//    dataSource.setUrl(jdbcUrl);
//    dataSource.setUsername(username);
//    dataSource.setPassword(password);
//    return dataSource;
//  }
//
//}
