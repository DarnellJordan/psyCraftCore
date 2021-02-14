package de.psyCraft.Core.core.server.legacy;

import de.psyCraft.Core.api.game.GameMode;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class ServerManager {
	
	private static final Map<GameMode, List<Server>> servers = new HashMap<>();
	private static final Map<Player, Server> playerConnections = new HashMap<>();
	private static final Map<GameMode, Integer> nextIDs = new HashMap<>();
	
	public static void startServer(GameMode gameMode) {
		if (!nextIDs.containsKey(gameMode)) {
			nextIDs.put(gameMode, 0);
		}
		
		Server server = new Server(gameMode, nextIDs.get(gameMode));
		Future<Integer> nextID = calculateNextID(gameMode);
		
		server.start();
		
		if (!servers.containsKey(gameMode)) {
			servers.put(gameMode, new ArrayList<>());
		}
		
		servers.get(gameMode).add(server);
		
		try {
			nextIDs.put(gameMode, nextID.get());
		} catch (InterruptedException | ExecutionException e) {
			nextIDs.put(gameMode, nextIDs.get(gameMode) + 1);
		}
	}
	
	public static void setPlayerConnection(Player player, Server server) {
		playerConnections.put(player, server);
	}
	
	public static Map<Player, Server> getPlayerConnections() {
		return new HashMap<>(playerConnections);
	}
	
	private static Future<Integer> calculateNextID(GameMode gameMode) {
		return CompletableFuture.supplyAsync(() -> {
			int highestID = 0;
			
			final Set<Integer> usedIDs = new HashSet<>();
			final List<Integer> unusedIDs = new ArrayList<>();
			
			for (Server server : servers.get(gameMode)) {
				highestID = Math.max(server.getServerID(), highestID);
				
				usedIDs.add(server.getServerID());
			}
			
			IntStream.range(0, highestID + 1).forEach(id -> unusedIDs.add(id));
			unusedIDs.removeAll(usedIDs);
			
			return unusedIDs.size() > 0 ? unusedIDs.get(0) : highestID + 1;
		});
	}
	
	/**
	 * DEBUG ONLY
	 *
	 * @param gameMode
	 * @return
	 */
	public static Server getServerByID(GameMode gameMode) {
		System.out.println(servers);
		for (Server server : servers.get(gameMode)) {
			if (server.getGameMode().equals(gameMode)) {
				return server;
			}
		}
		
		return null;
	}
}
