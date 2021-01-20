package de.psyCraft.core.lobby;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class LobbyClickListener implements Listener {
	
	private static Map<Integer, Consumer<Player>> clickEvents = new HashMap<>();
	
	public static void addClickEventToSlot(int slot, Consumer<Player> action) {
		clickEvents.put(slot, action);
	}
	
	@EventHandler
	public void onPlayerInteractionEvent(PlayerInteractEvent event) {
		final int slot = event.getPlayer().getInventory().getHeldItemSlot();
		
		if (clickEvents.containsKey(slot)) {
			clickEvents.get(slot).accept(event.getPlayer());
		}
		
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onPlayerInventoryClickEvent(InventoryClickEvent event) {
		if (!(event.getWhoClicked() instanceof Player)) {
			return;
		}
		
		final int slot = event.getSlot();
		
		if (clickEvents.containsKey(slot)) {
			clickEvents.get(slot).accept((Player) event.getWhoClicked());
		}
		
		event.setCancelled(true);
	}
}
