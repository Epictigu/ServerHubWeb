package eu.epicclan.spring.websocket.config;

import java.io.File;
import java.io.IOException;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

	@Bean
	public DataSource getDataSource() {
		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.url("jdbc:mysql://localhost:3306/" + AppConfiguration.getInstance().getSql().getDatabase() + "?useSSL=false");
		dataSourceBuilder.username(AppConfiguration.getInstance().getSql().getUser());
		File f = new File(AppConfiguration.getInstance().getSql().getUser());
		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		dataSourceBuilder.password(AppConfiguration.getInstance().getSql().getPassword());
		return dataSourceBuilder.build();
	}
	
}
