package de.psyCraft.Core.util.gui;

import de.psyCraft.Core.util.inventory.EventInventory;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class ChestInventoryBuilder {
	private final EventInventory eventInventory;
	
	public ChestInventoryBuilder(int rows, String title, String id) {
		if (rows < 1 || rows > 6) {
			throw new IllegalArgumentException("Rows cant be less then 1 or higher then 6");
		}
		
		eventInventory = new EventInventory(id, Bukkit.createInventory(null, rows * 9, title));
	}
	
	public ChestInventoryBuilder addItem(final int slot, final ItemStack item) {
		eventInventory.getInventory().setItem(slot, item);
		
		return this;
	}
	
	public ChestInventoryBuilder addItem(final int row, final int column, final ItemStack item) {
		return addItem(row + (column - 1) * 9 - 1, item);
	}
	
	public ChestInventoryBuilder addItemWIthClickEvent(final int slot, final ItemStack item, final Consumer<InventoryClickEvent> event) {
		eventInventory.addClickEvent(slot, event);
		
		return addItem(slot, item);
	}
	
	public ChestInventoryBuilder addItemWithClickEvent(final int row, final int column, final ItemStack item, final Consumer<InventoryClickEvent> event) {
		return addItemWIthClickEvent(row + (column - 1) * 9 - 1, item, event);
	}
	
	public ChestInventoryBuilder fillItems(final int fromSlot, final int toSlot, final ItemStack item) {
		for (int x = fromSlot % 9 ; x <= toSlot % 9 ; x++) {
			for (int y = (int) Math.floor((float) fromSlot / 9) ; y <= (int) Math.floor((float) toSlot / 9) ; y++) {
				addItem(x + 1, y + 1, item);
			}
		}
		
		return this;
	}
	
	public ChestInventoryBuilder fillItems(final int fromRow, final int fromColumn, final int toRow, final int toColumn, final ItemStack item) {
		return fillItems(fromRow + (fromColumn - 1) * 9 - 1, toRow + (toColumn - 1) * 9 - 1, item);
	}
	
	public EventInventory build() {
		return eventInventory;
	}
	
	public Inventory buildToInventory() {
		return eventInventory.getInventory();
	}
}
