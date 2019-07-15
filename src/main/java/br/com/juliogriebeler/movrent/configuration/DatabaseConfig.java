package br.com.juliogriebeler.movrent.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    private Environment environment;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.url}")
    private String url;

    public DatabaseConfig(Environment environment){
        this.environment = environment;
    }


    @Bean
    @Primary
    public DataSource dataSource() {
        final DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(DatabaseDriver.MYSQL.getDriverClassName());
        driverManagerDataSource.setUrl(this.url);
        driverManagerDataSource.setUsername(this.username);
        driverManagerDataSource.setPassword(this.password);

        return driverManagerDataSource;
    }
}