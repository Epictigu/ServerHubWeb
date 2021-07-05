package eu.epicclan.spring.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import eu.epicclan.spring.websocket.manager.FileManager;
import eu.epicclan.spring.websocket.utils.ServerFile;

@SpringBootApplication
public class WebsocketApplication {
	
	public static ServerFile sFile;
	
	public static void main(String[] args) {
		sFile = FileManager.loadFile("data.txt");
		
		SpringApplication.run(WebsocketApplication.class, args);
	}

}
