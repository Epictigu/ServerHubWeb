package eu.epicclan.spring.websocket.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import eu.epicclan.spring.websocket.WebsocketApplication;
import eu.epicclan.spring.websocket.utils.ServerList;

@RestController
public class ServerController {
	
	@GetMapping("/api/getservers")
	@PreAuthorize("hasRole('ADMIN')")
	public ServerList serverList() throws Exception {
		return new ServerList(WebsocketApplication.sFile.servers);
	}
	
	
	@PostMapping("/api/start/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public void startServer(@PathVariable String id) {
		System.out.println("Start Server received: " + id);
	}
	
	@PostMapping("api/stop/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public void stopServer(@PathVariable String id) {
		System.out.println("Stop Server received: " + id);
	}
	
}
