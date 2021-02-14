package de.psyCraft.Core.api.registry;

import de.psyCraft.Core.api.game.GameMode;
import de.psyCraft.Core.core.server.Server;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author psyGamer
 */
public class ServerRegistry {
	
	private static final List<Server> servers = new ArrayList<>();
	
	public static boolean registerServer(Server server) {
		// TODO some checks maybe
		servers.add(server);
		return true;
	}
	
	public static List<Server> getRegisteredServers() {
		return new ArrayList<>(servers);
	}
	
	public static List<Server> getServerOfGameMode(GameMode gameMode) {
		return servers.stream()
				.filter(server -> server.gameMode.equals(gameMode))
				.collect(Collectors.toList());
	}
}
