package de.psyCraft.api.game;

import de.psyCraft.core.server.Server;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

public class TestGame implements GameMode {
	
	@Override
	public String getName() {
		return "Test Game";
	}
	
	@Override
	public String getDescription() {
		return "A test game";
	}
	
	@Override
	public Material getIcon() {
		return Material.WOODEN_AXE;
	}
	
	@Override
	public boolean requireLobby() {
		return true;
	}
	
	@Override
	public boolean allowReconnect() {
		return true;
	}
	
	@Override
	public long getReconnectTimeout() {
		return 0;
	}
	
	@Override
	public void onServerInitialize(Server server) {
		server.getWorld().getHighestBlockAt(0, 0).setType(Material.GOLD_BLOCK);
	}
	
	@Override
	public void onServerEnable(Server server) {
		server.getWorld().getHighestBlockAt(server.getServerID(), 0).setType(Material.IRON_BLOCK);
	}
	
	@Override
	public void onServerDisable(Server server) {
		server.getWorld().getHighestBlockAt(-server.getServerID(), 0).setType(Material.LAPIS_BLOCK);
	}
	
	@Override
	public void onGameJoin(Player player) {
		player.sendMessage("Hello there");
	}
	
	@Override
	public void onGameLobbyJoin(List<Player> players) {
		for (Player player : players) {
			player.sendMessage("Hello theres");
		}
	}
	
	@Override
	public void onGameLeave(Player player) {
		player.sendMessage("pepeSad");
	}
}
