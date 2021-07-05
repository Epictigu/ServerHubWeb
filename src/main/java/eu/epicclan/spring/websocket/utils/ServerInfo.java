package eu.epicclan.spring.websocket.utils;

public class ServerInfo {
	
	private String name;
	private String desc;
	private String status;
	private String icon;
	
	public ServerInfo() {
	}
	
	public ServerInfo(Server server, String status) {
		name = server.getName();
		desc = server.getDesc();
		icon = server.getIcon();
		
		this.status = status;
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
	
}
