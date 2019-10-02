package by.epam.crackertracker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Properties;
import java.util.ResourceBundle;


@Configuration
@ComponentScan(basePackages = {"by.epam.crackertracker"})
@PropertySource("classpath:database.properties")
public class SpringConfig {
    @Value("${db.url}")
    public String URL;
    @Value("${db.user}")
    public String USER;
    @Value("${db.password}")
    public String PASSWORD;
    @Value("${db.driver}")
    public String DRIVER;

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(URL);
        dataSource.setUsername(USER);
        dataSource.setPassword(PASSWORD);
        dataSource.setDriverClassName(DRIVER);
        return dataSource;
    }
}
