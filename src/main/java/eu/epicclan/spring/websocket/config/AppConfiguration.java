package eu.epicclan.spring.websocket.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import eu.epicclan.spring.websocket.utils.ServerFile;

public class AppConfiguration {

	
	public static AppConfiguration getInstance() {
		if(instance == null) {
			try {
				loadConfiguration("config.yml");
			} catch (FileNotFoundException e) {
				instance = new AppConfiguration();
			}
		}
		return instance;
	}
	private static AppConfiguration instance = null;
	
	private AppConfiguration() {}
	
	private String password = "100";
	private SqlConfiguration sql = new SqlConfiguration();
	
	public static class SqlConfiguration {
		private String hostname = "localhost";
		private Integer port = 3306;
		private String database = "testdb";
		private String user = "test";
		private String password = "123456";
		
		public SqlConfiguration() {}
		
		public String getHostname() {
			return hostname;
		}
		public void setHostname(String hostname) {
			this.hostname = hostname;
		}
		public Integer getPort() {
			return port;
		}
		public void setPort(Integer port) {
			this.port = port;
		}
		public String getDatabase() {
			return database;
		}
		public void setDatabase(String database) {
			this.database = database;
		}
		public String getUser() {
			return user;
		}
		public void setUser(String user) {
			this.user = user;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public SqlConfiguration getSql() {
		return sql;
	}

	public void setSql(SqlConfiguration sql) {
		this.sql = sql;
	}
	
	
	
	public static ServerFile loadFile(String path){
		File f = new File(path);
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(f));
			
			List<String> lines = new ArrayList<String>();
			String s = reader.readLine();
			while(s != null){
				lines.add(s);
				s = reader.readLine();
			}
			
			reader.close();
			return new ServerFile(lines);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void loadConfiguration(String path) throws FileNotFoundException {
		File f = new File(path);
		if(!f.exists())
			throw new FileNotFoundException("Konfiguration konnte nicht gefunden werden!");
		
		InputStream inputStream = new FileInputStream(f);
		
		Yaml yaml = new Yaml(new Constructor(AppConfiguration.class));
		instance = yaml.load(inputStream);
	}
	
	public static void createNewConfiguration(String path) throws IOException {
		File f = new File(path);
		if(f.exists())
			throw new IllegalArgumentException("Konfiguration existiert bereits!");
		f.createNewFile();
		
		final DumperOptions options = new DumperOptions();
		options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
		options.setPrettyFlow(true);
		
		Representer rep = new Representer();
		rep.addClassTag(AppConfiguration.class, Tag.MAP);
		
		Yaml yaml = new Yaml(rep, options);
		
		FileWriter fWriter = new FileWriter(f);
		
		instance = new AppConfiguration();
		yaml.dump(instance, fWriter);
	}
	
}
