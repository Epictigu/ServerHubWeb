package eu.epicclan.spring.websocket.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import eu.epicclan.spring.websocket.config.AppConfiguration;
import eu.epicclan.spring.websocket.utils.ServerFile;

public class SocketManager {

	public int sPort;
	public boolean lRun = true;
	
	public String password = "";
	
	public ServerFile dFile;
	public AppConfiguration cFile;
	public ServerSocket sSocket;
	public ServerSocket wSocket;
	
	public void log(String msg) {
		System.out.println("[ServerHub] " + msg);
	}
	
	
	public SocketManager(ServerFile serverFile, AppConfiguration config) {
		this.dFile = serverFile;
		this.cFile = config;
		
		setup();
	}
	
	public void setup() {
		this.sPort = 9955;
		
		password = this.cFile.getPassword();
		
		try {
			sSocket = new ServerSocket(sPort);
			wSocket = new ServerSocket(3001);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		log("Started listener!");
	}
	
	public void startTask() {
		new Thread() {
			public void run() {
				while(lRun) {
					log("Waiting for data ...");
					
					try {
						Socket dSocket = sSocket.accept();
						dataWork(dSocket);
					} catch(Exception e) {
						log("Cancelling because of error.");
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	
	public void dataWork(Socket dSocket) throws IOException {
		List<String> dList = readLines(dSocket);
		log("Data reveived: " + dList);
		
		if(!dList.get(0).equals(password)) {
			if(dList.get(0).equals("checkpw")) {
				log("Password check requested.");
				checkPassword(dSocket, dList.get(1));
				
				return;
			}
			log("Password is wrong! Cancelling.");
			return;
		}
		String cmdData = dList.get(1);
		
		
		if(cmdData.equalsIgnoreCase("getservers")) {
			sendInfo(dSocket);
		} else if(cmdData.equalsIgnoreCase("exec")) {
			execCmd(dList.get(2));
		} else if(cmdData.equalsIgnoreCase("reload")) {
			reloadConfig(dSocket);
		}
		log("Datawork completed. Restarting.");
	}
	
	public void checkPassword(Socket dSocket, String password){
		OutputStreamWriter sStream;
		try {
			sStream = new OutputStreamWriter(dSocket.getOutputStream());
			PrintWriter sWriter = new PrintWriter(sStream);
			if(password.equals(password)) {
				log("Password check successful! (" + password + ")");
				sWriter.println("1");
			} else {
				log("Password check failed! (" + password + ")");
				sWriter.println("0");
			}
			sWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<String> readLines(Socket dSocket) throws IOException{
		List<String> dList = new ArrayList<String>();
		BufferedReader dReader = new BufferedReader(new InputStreamReader(dSocket.getInputStream()));
		dList.add(dReader.readLine());
		while(dReader.ready()) {
			dList.add(dReader.readLine());
		}
		
		return dList;
	}
	
	public void sendInfo(Socket dSocket) throws IOException {
		log("Sending serverinfo ...");
		OutputStreamWriter sStream = new OutputStreamWriter(dSocket.getOutputStream());
		PrintWriter sWriter = new PrintWriter(sStream);
		for(String line : dFile.lines) {
			sWriter.println(line);
		}
		sWriter.flush();
	}
	
	public void execCmd(String cmd) throws IOException {
		log("Executing command: " + cmd);
		Runtime.getRuntime().exec(cmd);
	}
	
	public void reloadConfig(Socket dSocket) throws IOException {
		log("Reloading Config ...");
		dFile = FileManager.loadFile("data.txt");
		
		OutputStreamWriter sStream = new OutputStreamWriter(dSocket.getOutputStream());
		PrintWriter sWriter = new PrintWriter(sStream);
		for(String line : dFile.lines) {
			sWriter.println(line);
		}
		sWriter.flush();
	}
	
}
