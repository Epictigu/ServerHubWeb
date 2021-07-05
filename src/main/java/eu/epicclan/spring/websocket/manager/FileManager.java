package eu.epicclan.spring.websocket.manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import eu.epicclan.spring.websocket.utils.ServerFile;

public class FileManager {

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
	
}
