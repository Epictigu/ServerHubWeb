package eu.epicclan.spring.websocket.config;

public class AppConfiguration {

	private String password = "";
	private SqlConfiguration sql = new SqlConfiguration();
	
	public static class SqlConfiguration {
		private String hostname = "";
		private Integer port = 3306;
		private String database = "";
		private String user = "";
		private String password = "";
		
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
	
	public AppConfiguration() {}

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
	
}
