package de.psyCraft.core.lobby;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.HashMap;
import java.util.Map;

public class LobbyClickListener implements Listener {
	
	private static Map<Integer, Runnable> clickEvents = new HashMap<>();
	
	public void addClickEventToSlot(int slot, Runnable action) {
		clickEvents.put(slot, action);
	}
	
	@EventHandler
	public void onPlayerInteractionEvent(PlayerInteractEvent event) {
		final int slot = event.getPlayer().getInventory().getHeldItemSlot();
		
		if (clickEvents.containsKey(slot)) {
			clickEvents.get(slot).run();
		}
		
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onPlayerInventoryClickEvent(InventoryClickEvent event) {
		final int slot = event.getSlot();
		
		if (clickEvents.containsKey(slot)) {
			clickEvents.get(slot).run();
		}
		
		event.setCancelled(true);
	}
}
