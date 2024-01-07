package sg.edu.ntu.springbootheroku.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class DatabaseConfiguration {

    @Value("${application.database_url}")
    private String applicationDatabaseUrl;

    @Bean
    public DataSource dataSource() throws URISyntaxException {
        final var dbUri = new URI(applicationDatabaseUrl);

        final var userInfoSegments = dbUri.getUserInfo().split(":");
        final var username = userInfoSegments[0];
        final var password = userInfoSegments[1];
        final var dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
        final var dataSourceBuilder = DataSourceBuilder.create();
        return dataSourceBuilder
            .url(dbUrl)
            .username(username)
            .password(password)
            .build();
    }
}
