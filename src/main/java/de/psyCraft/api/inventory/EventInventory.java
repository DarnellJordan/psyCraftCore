package de.psyCraft.api.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class EventInventory extends BaseInventory implements Listener {
	
	private final Map<Integer, Consumer<InventoryClickEvent>> events = new HashMap<>();
	
	public EventInventory(String inventoryID, Inventory baseInventory) {
		super(inventoryID, baseInventory);
		
		ClickListener.eventInventories.add(this);
	}
	
	private void executeClickEvent(InventoryClickEvent event) {
		if (events.containsKey(event.getRawSlot())) {
			events.get(event.getRawSlot()).accept(event);
		}
	}
	
	public void addClickEvent(final int slot, final Consumer<InventoryClickEvent> event) {
		events.put(slot, event);
	}
	
	public static class ClickListener implements Listener {
		private static final List<EventInventory> eventInventories = new ArrayList<>();
		
		@EventHandler
		public void onPlayerClick(InventoryClickEvent event) {
			final Player player = (Player) event.getWhoClicked();
			
			if (!getOpenInventories().containsKey(player) ||
					getOpenInventories().get(player) == null) {
				return;
			}
			
			for (EventInventory eventInventory : eventInventories) {
				if (eventInventory.getInventoryID().equalsIgnoreCase(getOpenInventories().get(player))) {
					eventInventory.executeClickEvent(event);
				}
			}
		}
	}
}
