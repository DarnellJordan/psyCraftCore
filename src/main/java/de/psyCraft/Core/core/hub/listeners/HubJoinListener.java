package de.psyCraft.Core.core.hub.listeners;

import de.psyCraft.Core.core.hub.HubManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class HubJoinListener implements Listener {
	
	private static void givePlayerItems(final Player player, final Location location) {
		if (!location.getWorld().getName().equalsIgnoreCase(HubManager.WORLD.getName())) {
			return;
		}
		
		player.getInventory().setItem(0, HubManager.NAVIGATOR.getItem());
	}
	
	@EventHandler
	public void onPlayerJoin(final PlayerJoinEvent event) {
		final Player player = event.getPlayer();
		final Location location = event.getPlayer().getLocation();
		
		givePlayerItems(player, location);
	}
	
	@EventHandler
	public void onPlayerTeleport(final PlayerTeleportEvent event) {
		final Player player = event.getPlayer();
		final Location location = event.getTo();
		
		givePlayerItems(player, location);
	}
}
