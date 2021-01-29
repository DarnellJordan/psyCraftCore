package de.psyCraft.api.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseInventory {
	
	private static final Map<Player, String> openInventories = new HashMap<>();
	private static final List<String> inventoryIDs = new ArrayList<>();
	private final String inventoryID;
	private final Inventory inventory;
	
	protected BaseInventory(String inventoryID, Inventory inventory) {
		if (inventoryIDs.contains(inventoryID)) {
			throw new IllegalArgumentException("An inventory with that ID (" + inventoryID + ") already exists");
		}
		
		this.inventoryID = inventoryID;
		this.inventory = inventory;
		
		inventoryIDs.add(inventoryID);
	}
	
	protected static Map<Player, String> getOpenInventories() {
		return openInventories;
	}
	
	public void openInventory(Player player) {
		player.openInventory(inventory);
		
		openInventories.put(player, inventoryID);
	}
	
	public String getInventoryID() {
		return inventoryID;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public static class InventoryListener implements Listener {
		@EventHandler
		public void onInventoryClose(InventoryCloseEvent event) {
			openInventories.remove(event.getPlayer());
		}
		
		@EventHandler
		public void onInventoryClick(InventoryClickEvent event) {
			event.setCancelled(true);
		}
	}
}
