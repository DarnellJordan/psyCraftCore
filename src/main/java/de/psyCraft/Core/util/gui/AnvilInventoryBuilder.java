package de.psyCraft.Core.util.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class AnvilInventoryBuilder {
	
	private final Inventory inventory;
	private final Map<Integer, Consumer<PrepareAnvilEvent>> clickEvents = new HashMap() {{
		put(0, (Consumer<PrepareAnvilEvent>) prepareAnvilEvent -> {
		});
		put(1, (Consumer<PrepareAnvilEvent>) prepareAnvilEvent -> {
		});
		put(2, (Consumer<PrepareAnvilEvent>) prepareAnvilEvent -> {
		});
	}};
	
	private int requiredLevels = 0;
	
	public AnvilInventoryBuilder() {
		inventory = Bukkit.createInventory(null, InventoryType.ANVIL);
		
		inventory.setItem(0, new ItemStack(Material.AIR));
		inventory.setItem(1, new ItemStack(Material.AIR));
		inventory.setItem(2, new ItemStack(Material.AIR));
	}
	
	public AnvilInventoryBuilder setFirstItem(ItemStack item) {
		inventory.setItem(0, item);
		
		return this;
	}
	
	public AnvilInventoryBuilder setFirstItemWithClickEvent(ItemStack item, Consumer<PrepareAnvilEvent> event) {
		clickEvents.put(0, event);
		
		return setFirstItem(item);
	}
	
	public AnvilInventoryBuilder setSecondItem(ItemStack item) {
		inventory.setItem(1, item);
		
		return this;
	}
	
	public AnvilInventoryBuilder setSecondItemWithClickEvent(ItemStack item, Consumer<PrepareAnvilEvent> event) {
		clickEvents.put(1, event);
		
		return setSecondItem(item);
	}
	
	public AnvilInventoryBuilder setResult(ItemStack item) {
		inventory.setItem(2, item);
		
		return this;
	}
	
	public AnvilInventoryBuilder setResultWithClickEvent(ItemStack item, Consumer<PrepareAnvilEvent> event) {
		clickEvents.put(2, event);
		
		return setResult(item);
	}
	
	public AnvilInventoryBuilder setLevelRequirement(int requiredLevels) {
		this.requiredLevels = requiredLevels;
		
		return this;
	}
	
	public int getRequiredLevels() {
		return requiredLevels;
	}
	
	public Map<Integer, Consumer<PrepareAnvilEvent>> getClickEvents() {
		return new HashMap<>(clickEvents);
	}
	
	public Inventory build() {
		return inventory;
	}
}