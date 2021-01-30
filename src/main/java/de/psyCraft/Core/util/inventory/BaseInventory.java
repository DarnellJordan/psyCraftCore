package de.psyCraft.Core.util.inventory;

import org.bukkit.GameMode;
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
import java.util.function.Function;

public abstract class BaseInventory {
	
	protected static final Map<Player, String> openInventories = new HashMap<>();
	private static final Map<String, Function<InventoryClickEvent, Boolean>> cancelClickEvent = new HashMap<>();
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
		
		cancelClickEvent.put(inventoryID, this::cancelClickEvent);
	}
	
	public static Map<Player, String> getOpenInventories() {
		return new HashMap<>(openInventories);
	}
	
	public static List<String> getInventoryIDs() {
		return new ArrayList<>(inventoryIDs);
	}
	
	public void openInventory(Player player) {
		player.openInventory(inventory);
		
		openInventories.put(player, inventoryID);
	}
	
	abstract boolean cancelClickEvent(InventoryClickEvent event);
	
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
			if (openInventories.containsKey(event.getWhoClicked()) && cancelClickEvent.get(openInventories.get(event.getWhoClicked())).apply(event)) {
				
				event.setCancelled(event.getWhoClicked().getGameMode() != GameMode.CREATIVE);
			}
		}
	}
}
