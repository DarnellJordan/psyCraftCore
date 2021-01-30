package de.psyCraft.Core.util.gui;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class AnvilInventoryBuilder {
	
	private final Inventory inventory;
	
	public AnvilInventoryBuilder() {
		inventory = Bukkit.createInventory(null, InventoryType.ANVIL);
	}
	
	public AnvilInventoryBuilder setFirstItem(ItemStack item) {
		inventory.setItem(0, item);
		
		return this;
	}
	
	public AnvilInventoryBuilder setSecondItem(ItemStack item) {
		inventory.setItem(1, item);
		
		return this;
	}
	
	public Inventory build() {
		return inventory;
	}
}