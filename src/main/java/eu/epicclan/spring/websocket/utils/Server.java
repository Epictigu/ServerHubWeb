package eu.epicclan.spring.websocket.utils;

import java.util.UUID;

public class Server {

	private UUID uuid;
	
	private String name;
	private String desc;
	private String startSSH;
	private String stopSSH;
	private String category;
	private String icon;
	private String statusCheck;
	
	public Server() {
	}
	
	public Server(String line) {
		String[] args = line.split(";");
		
		this.uuid = UUID.randomUUID();
		
		name = args[0];
		desc = args[1];
		startSSH = args[2];
		stopSSH = args[3];
		category = args[4];
		icon = args[5];
		statusCheck = args[6];
	}
	
	public UUID getUUID() {
		return uuid;
	}

	public String getName() {
		return name;
	}

	public String getDesc() {
		return desc;
	}

	public String getStartSSH() {
		return startSSH;
	}

	public String getStopSSH() {
		return stopSSH;
	}
	public String getCategory() {
		return category;
	}

	public String getIcon() {
		return icon;
	}
	
	public String getStatusCheck() {
		return statusCheck;
	}
	
}
