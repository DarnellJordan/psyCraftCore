package de.psyCraft.Core.core.lobby.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
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
	
	private static void executeClickEvent(Player player, int slot) {
		if (clickEvents.containsKey(slot)) {
			clickEvents.get(slot).accept(player);
		}
	}
	
	@EventHandler
	public void onPlayerInteractionEvent(PlayerInteractEvent event) {
		final Player player = (Player) event.getPlayer();
		final int slot = player.getInventory().getHeldItemSlot();
		
		executeClickEvent(player, slot);
	}
	
	@EventHandler
	public void onPlayerHitEntity(EntityDamageByEntityEvent event) {
		if (!(event.getDamager() instanceof Player)) {
			return;
		}
		
		final Player player = (Player) event.getDamager();
		final int slot = player.getInventory().getHeldItemSlot();
		
		executeClickEvent(player, slot);
	}
	
	@EventHandler
	public void onPlayerInventoryClickEvent(InventoryClickEvent event) {
		final Player player = (Player) event.getWhoClicked();
		final int slot = event.getSlot();
		
		if (event.getClickedInventory() != null && event.getClickedInventory().equals(event.getWhoClicked().getInventory())) {
			executeClickEvent(player, slot);
		}
		
		event.setCancelled(player.getGameMode() != GameMode.CREATIVE);
	}
}
