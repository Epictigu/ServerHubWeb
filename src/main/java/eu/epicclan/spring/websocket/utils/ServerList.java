package eu.epicclan.spring.websocket.utils;

import java.io.IOException;
import java.util.Scanner;

public class ServerList {

	private ServerInfo[] servers;
	
	public ServerList() {
	}
	
	public ServerList(Server[] servers) {
		this.servers = new ServerInfo[servers.length];
		for(int i = 0; i < servers.length; i++) {
			String status = "Offline";
			try {
				String cmd = execStatusCmd(servers[i].getStatusCheck());
				if(cmd != null)
					status = "Online";
			} catch(IOException e) {
				System.err.println("[ERROR] Status-Befehl für [" + servers[i].getName() + "] konnte nicht ausgeführt werden!");
				System.err.println(servers[i].getStatusCheck());
			}
			
			this.servers[i] = new ServerInfo(servers[i], status);
		}
	}
	
	public ServerInfo[] getServers() {
		return this.servers;
	}
	
	@SuppressWarnings("resource")
	private String execStatusCmd(String cmd) throws IOException {
		String[] cmds = { "/bin/sh", "-c", cmd };
		Scanner s = new Scanner(Runtime.getRuntime().exec(cmds).getInputStream()).useDelimiter("\\A");
		String r = null;
		if(s.hasNext()) {
			r = s.next();
		}
		s.close();
		return r;
	}
	
}
