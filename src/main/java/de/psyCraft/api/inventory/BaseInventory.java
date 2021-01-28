package de.psyCraft.api.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public class BaseInventory {
	
	private static final Map<Player, String> openInventories = new HashMap<>();
	private final String inventoryID;
	private final Inventory inventory;
	
	protected BaseInventory(String inventoryID, Inventory inventory) {
		this.inventoryID = inventoryID;
		this.inventory = inventory;
	}
	
	protected static Map<Player, String> getOpenInventories() {
		return openInventories;
	}
	
	public void openInventory(Player player) {
		openInventories.put(player, inventoryID);
		
		player.openInventory(inventory);
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
	}
}
