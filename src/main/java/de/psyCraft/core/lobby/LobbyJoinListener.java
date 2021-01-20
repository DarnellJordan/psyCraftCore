package de.psyCraft.core.lobby;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class LobbyJoinListener implements Listener {
	
	private static void givePlayerItems(final Player player, final Location location) {
		if (!location.getWorld().getName().equalsIgnoreCase(LobbyManager.LOBBY_WORLD.getName())) {
			return;
		}
		
		player.getInventory().setItem(0, LobbyManager.COMPASS);
		LobbyClickListener.addClickEventToSlot(0, () -> {
		});
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
