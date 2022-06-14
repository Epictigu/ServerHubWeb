package eu.epicclan.spring.websocket.utils;

public class ServerInfo {
	
	private String uuid;
	
	private String name;
	private String desc;
	private String status;
	private String icon;
	private String category;
	
	public ServerInfo() {
	}
	
	public ServerInfo(Server server, String status) {
		uuid = server.getUUID().toString();
		name = server.getName();
		desc = server.getDesc();
		icon = server.getIcon();
		category = server.getCategory();
		
		this.status = status;
	}
	
	public String getUUID() {
		return uuid;
	}

	public String getName() {
		return name;
	}

	public String getDesc() {
		return desc;
	}
	
	public String getStatus() {
		return status;
	}
	
	public String getIcon() {
		return icon;
	}
	
	public String getCategory() {
		return category;
	}
	
}
