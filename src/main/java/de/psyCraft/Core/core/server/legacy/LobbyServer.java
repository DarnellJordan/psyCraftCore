package de.psyCraft.Core.core.server.legacy;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * @author psyGamer
 */
public class LobbyServer {
	
	public static final World LOBBY = Bukkit.getWorld("lobby");
	
	private final Server server;
	private final List<Player> players = new ArrayList<>();
	
	LobbyServer(Server server) {
		this.server = server;
	}
	
	public Server getServer() {
		return server;
	}
	
	public List<Player> getPlayers() {
		return new ArrayList<>(players);
	}
	
	void joinLobbyServer(Player player) {
		players.add(player);
		
		player.teleport(LOBBY.getSpawnLocation());
		player.sendMessage("Join Lobby: " + server.getGameMode().getName() + "#" + server.getServerID());
	}
	
	void leaveLobbyServer(Player player) {
		players.remove(player);
		
		player.sendMessage("Leave Lobby: " + server.getGameMode().getName() + "#" + server.getServerID());
		player.teleport(Bukkit.getWorld("login").getSpawnLocation());
	}
	
	public void moveToMainServer(Player player) {
	
	}
}
