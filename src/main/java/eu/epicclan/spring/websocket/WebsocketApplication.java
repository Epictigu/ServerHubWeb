package eu.epicclan.spring.websocket;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import eu.epicclan.spring.websocket.config.AppConfiguration;
import eu.epicclan.spring.websocket.manager.FileManager;
import eu.epicclan.spring.websocket.manager.SocketManager;
import eu.epicclan.spring.websocket.utils.ServerFile;

@SpringBootApplication
public class WebsocketApplication {
	
	public static ServerFile sFile;
	//public static AppConfiguration conf;
	
	public static void main(String[] args) {
		try {
			FileManager.loadConfiguration("config.yml");
		} catch(FileNotFoundException e) {
			try {
				FileManager.createNewConfiguration("config.yml");
				
				System.out.println("Eine neue Konfigurationsdatei wurde erstellt!\nBitte erst die Applikation wieder einschalten nach dem Konfigurieren.");
				System.exit(0);
			} catch (IOException eIO) {
				System.err.println("[Error!] Eine neue Konfigurationsdatei konnte nicht erstellt werden.\n\tDas Programm wird gestoppt ...");
				eIO.printStackTrace();
				System.exit(-1);
			}
		}
		
		
		
		sFile = FileManager.loadFile("data.txt");
		
		SocketManager sManager = new SocketManager(sFile, AppConfiguration.getInstance());
		sManager.startTask();
		
		new SpringApplication(WebsocketApplication.class).run(args);
	}
	
}
