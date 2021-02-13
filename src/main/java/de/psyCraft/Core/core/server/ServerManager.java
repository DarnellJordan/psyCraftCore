package de.psyCraft.Core.core.server;

import de.psyCraft.Core.api.game.GameMode;

import java.util.HashMap;
import java.util.Map;

public class ServerManager {
	
	private static final Map<Integer, Server> servers = new HashMap<>();
	
	public static void startServer(GameMode gamemode, int id) {
		Server server = new Server(gamemode, id);
		
		server.start();
		servers.put(id, server);
		System.out.println("STARTED SERVER " + gamemode + "#" + id);
	}
	
	public static Server getServerByID(GameMode gameMode) {
		System.out.println(servers);
		for (Server server : servers.values()) {
			if (server.getGameMode().equals(gameMode)) {
				return server;
			}
		}
		
		return null;
	}
}
