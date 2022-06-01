package eu.epicclan.spring.websocket.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
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
	
}
