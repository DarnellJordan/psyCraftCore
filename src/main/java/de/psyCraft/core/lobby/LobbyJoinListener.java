package de.psyCraft.core.lobby;

import de.psyCraft.core.items.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class LobbyJoinListener implements Listener {
	
	@EventHandler
	public void onPlayerJoin(final PlayerJoinEvent event) {
		final Player player = event.getPlayer();
		
		givePlayerItems(player);
	}
	
	@EventHandler
	public void onPlayerTeleport(final PlayerTeleportEvent event) {
		final Player player = event.getPlayer();
		
		givePlayerItems(player);
	}
	
	private static void givePlayerItems(final Player player) {
		if (!player.getWorld().getName().equalsIgnoreCase(LobbyManager.LOBBY_WORLD.getName())) {
			return;
		}
		
		player.getInventory().clear();
		player.getInventory().setItem(0, LobbyManager.COMPASS);
	}
}
