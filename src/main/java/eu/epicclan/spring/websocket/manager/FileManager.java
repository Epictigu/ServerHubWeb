package eu.epicclan.spring.websocket.manager;

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

import eu.epicclan.spring.websocket.config.AppConfiguration;
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
	
	public static AppConfiguration loadConfiguration(String path) throws FileNotFoundException {
		File f = new File(path);
		if(!f.exists())
			throw new FileNotFoundException("Konfiguration konnte nicht gefunden werden!");
		
		InputStream inputStream = new FileInputStream(f);
		
		Yaml yaml = new Yaml(new Constructor(AppConfiguration.class));
		AppConfiguration conf = yaml.load(inputStream);
		
		return conf;
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
		yaml.dump(AppConfiguration.getInstance(), fWriter);
	}
	
}
