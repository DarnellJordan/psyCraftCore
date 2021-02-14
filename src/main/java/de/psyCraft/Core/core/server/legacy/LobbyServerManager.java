package de.psyCraft.Core.core.server.legacy;

import de.psyCraft.Core.util.lobby.LobbyUtil;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * @author psyGamer
 */
public class LobbyServerManager {
	
	private static final Map<Player, LobbyServer> players = new HashMap<>();
	private static final Map<Server, LobbyServer> lobbyServers = new HashMap<>();
	
	public static void joinLobbyServer(Player player, LobbyServer server) {
		if (players.containsKey(player)) {
			leaveLobbyServer(player, players.get(player));
		}
		
		players.put(player, server);
		server.joinLobbyServer(player);
		
		System.out.println(server.getPlayers());
		LobbyUtil.hidePlayerFromEveryoneExcept(player, server.getPlayers());
		LobbyUtil.hideEveryoneFromPlayerExcept(player, server.getPlayers());
		LobbyUtil.showPlayerOnlyTo(player, server.getPlayers());
		LobbyUtil.showPlayersToPlayer(player, server.getPlayers());
	}
	
	public static void leaveLobbyServer(Player player, LobbyServer server) {
		players.remove(player);
		server.leaveLobbyServer(player);
		
		LobbyUtil.showPlayerToEveryone(player);
	}
	
	public static LobbyServer getLobbyServer(Server server) {
		if (!lobbyServers.containsKey(server)) {
			return null;
		}
		
		return lobbyServers.get(server);
	}
	
	static LobbyServer createLobbyServer(Server server) {
		return lobbyServers.put(server, new LobbyServer(server));
	}
	
	
}
