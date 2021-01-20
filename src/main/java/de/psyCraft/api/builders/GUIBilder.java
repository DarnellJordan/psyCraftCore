package de.psyCraft.api.builders;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GUIBilder {
	private final Inventory inventory;
	
	public GUIBilder(GUIType type, String title) {
		switch (type) {
			default:
			case CHEST:
				inventory = Bukkit.createInventory(null, 3 * 9, title);
				break;
			case DOUBLE_CHEST:
				inventory = Bukkit.createInventory(null, 6 * 9, title);
				break;
		}
	}
	
	public GUIBilder(int rows, String title) {
		if (rows < 1 || rows > 6) {
			throw new IllegalArgumentException("Rows cant be less then 1 or higher then 6");
		}
		
		inventory = Bukkit.createInventory(null, rows * 9, title);
	}
	
	public enum GUIType {
		CHEST,
		DOUBLE_CHEST
	}
	
	public GUIBilder addItem(final int slot, final ItemStack item) {
		inventory.setItem(slot, item);
		
		return this;
	}
	
	public GUIBilder addItem(final int row, final int column, final ItemStack item) {
		addItem(row * column, item);
		
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
	
	public GUIBilder addItem(final int fromRow, final int fromColumn, final int toRow, final int toColumn, final ItemStack item) {
		fillItems(fromRow * fromColumn, toRow * toColumn, item);
		
		return this;
	}
	
	public Inventory build() {
		return inventory;
	}
}
