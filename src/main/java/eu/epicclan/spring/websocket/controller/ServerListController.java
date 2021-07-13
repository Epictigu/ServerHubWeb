package eu.epicclan.spring.websocket.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import eu.epicclan.spring.websocket.WebsocketApplication;
import eu.epicclan.spring.websocket.utils.ServerList;

@Controller
public class ServerListController {
	
	@MessageMapping("/hello")
	@SendToUser("/topic/serverinfo")
	public ServerList serverList() throws Exception {
		return new ServerList(WebsocketApplication.sFile.servers);
	}
	
}
