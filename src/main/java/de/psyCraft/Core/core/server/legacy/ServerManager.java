package de.psyCraft.Core.core.server.legacy;

import de.psyCraft.Core.api.game.NavigatorItem;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class ServerManager {
	
	private static final Map<NavigatorItem, List<Server>> servers = new HashMap<>();
	private static final Map<Player, Server> playerConnections = new HashMap<>();
	private static final Map<NavigatorItem, Integer> nextIDs = new HashMap<>();
	
	public static void startServer(NavigatorItem navigatorItem) {
		if (!nextIDs.containsKey(navigatorItem)) {
			nextIDs.put(navigatorItem, 0);
		}
		
		Server server = new Server(navigatorItem, nextIDs.get(navigatorItem));
		Future<Integer> nextID = calculateNextID(navigatorItem);
		
		server.start();
		
		if (!servers.containsKey(navigatorItem)) {
			servers.put(navigatorItem, new ArrayList<>());
		}
		
		servers.get(navigatorItem).add(server);
		
		try {
			nextIDs.put(navigatorItem, nextID.get());
		} catch (InterruptedException | ExecutionException e) {
			nextIDs.put(navigatorItem, nextIDs.get(navigatorItem) + 1);
		}
	}
	
	public static void setPlayerConnection(Player player, Server server) {
		playerConnections.put(player, server);
	}
	
	public static Map<Player, Server> getPlayerConnections() {
		return new HashMap<>(playerConnections);
	}
	
	private static Future<Integer> calculateNextID(NavigatorItem navigatorItem) {
		return CompletableFuture.supplyAsync(() -> {
			int highestID = 0;
			
			final Set<Integer> usedIDs = new HashSet<>();
			final List<Integer> unusedIDs = new ArrayList<>();
			
			for (Server server : servers.get(navigatorItem)) {
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
	 * @param navigatorItem
	 * @return
	 */
	public static Server getServerByID(NavigatorItem navigatorItem) {
		System.out.println(servers);
		for (Server server : servers.get(navigatorItem)) {
			if (server.getGameMode().equals(navigatorItem)) {
				return server;
			}
		}
		
		return null;
	}
}
