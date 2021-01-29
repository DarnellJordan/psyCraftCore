package de.psyCraft.api.builders;

import de.psyCraft.api.inventory.EventInventory;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class GUIBilder {
	private final EventInventory eventInventory;
	
	public GUIBilder(GUIType type, String title, String id) {
		switch (type) {
			default:
			case CHEST:
				eventInventory = new EventInventory(id, Bukkit.createInventory(null, 3 * 9, title));
				break;
			case DOUBLE_CHEST:
				eventInventory = new EventInventory(id, Bukkit.createInventory(null, 6 * 9, title));
				break;
		}
	}
	
	public GUIBilder(int rows, String title, String id) {
		if (rows < 1 || rows > 6) {
			throw new IllegalArgumentException("Rows cant be less then 1 or higher then 6");
		}
		
		eventInventory = new EventInventory(id, Bukkit.createInventory(null, rows * 9, title));
	}
	
	public enum GUIType {
		CHEST,
		DOUBLE_CHEST
	}
	
	public GUIBilder addItem(final int slot, final ItemStack item) {
		eventInventory.getInventory().setItem(slot, item);
		
		return this;
	}
	
	public GUIBilder addItem(final int row, final int column, final ItemStack item) {
		addItem(row + (column - 1) * 9 - 1, item);
		
		return this;
	}
	
	public GUIBilder addItemWIthClickEvent(final int slot, final ItemStack item, final Consumer<InventoryClickEvent> event) {
		eventInventory.addClickEvent(slot, event);
		addItem(slot, item);
		
		return this;
	}
	
	public GUIBilder addItemWithClickEvent(final int row, final int column, final ItemStack item, final Consumer<InventoryClickEvent> event) {
		addItemWIthClickEvent(row + (column - 1) * 9 - 1, item, event);
		
		return this;
	}
	
	public GUIBilder fillItems(final int fromSlot, final int toSlot, final ItemStack item) {
		for (int x = fromSlot % 9 ; x <= toSlot % 9 ; x++) {
			for (int y = (int) Math.floor((float) fromSlot / 9) ; y <= (int) Math.floor((float) toSlot / 9) ; y++) {
				addItem(x, y, item);
			}
		}
		
		return this;
	}
	
	public GUIBilder fillItems(final int fromRow, final int fromColumn, final int toRow, final int toColumn, final ItemStack item) {
		fillItems(fromRow + (fromColumn - 1) * 9 - 1, toRow + (toColumn - 1) * 9 - 1, item);
		
		return this;
	}
	
	public EventInventory build() {
		return eventInventory;
	}
	
	public Inventory buildToInventory() {
		return eventInventory.getInventory();
	}
}
