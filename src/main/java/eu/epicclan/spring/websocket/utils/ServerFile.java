package eu.epicclan.spring.websocket.utils;
import java.util.ArrayList;
import java.util.List;

public class ServerFile {

	public List<String> lines = new ArrayList<String>();
	public Server[] servers;
	
	public ServerFile(List<String> lines){
		this.lines = lines;
		this.servers = new Server[lines.size()];
		for(int i = 0; i < lines.size(); i++)
			this.servers[i] = new Server(lines.get(i));
	}
	
	public String getLine(int line){
		return lines.get(line);
	}
	
}
