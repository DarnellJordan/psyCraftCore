package de.psyCraft.Core.util.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class AnvilInventoryBuilder {
	
	private final Inventory inventory;
	private final Map<Integer, Consumer<PrepareAnvilEvent>> prepareAnvilEvents = new HashMap() {{
		put(0, (Consumer<PrepareAnvilEvent>) prepareAnvilEvent -> {
		});
		put(1, (Consumer<PrepareAnvilEvent>) prepareAnvilEvent -> {
		});
		put(2, (Consumer<PrepareAnvilEvent>) prepareAnvilEvent -> {
		});
	}};
	private final Map<Integer, Consumer<InventoryClickEvent>> clickEvents = new HashMap() {{
		put(0, (Consumer<InventoryClickEvent>) clickEvent -> {
		});
		put(1, (Consumer<InventoryClickEvent>) clickEvent -> {
		});
		put(2, (Consumer<InventoryClickEvent>) clickEvent -> {
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
	
	public AnvilInventoryBuilder setFirstItemWithClickEvent(ItemStack item, Consumer<InventoryClickEvent> clickEvent) {
		clickEvents.put(0, clickEvent);
		
		return setFirstItem(item);
	}
	
	public AnvilInventoryBuilder setFirstItemClickEvent(Consumer<InventoryClickEvent> clickEvent) {
		return setFirstItemWithClickEvent(inventory.getItem(0), clickEvent);
	}
	
	public AnvilInventoryBuilder setFirstItemWithPrepareEvent(ItemStack item, Consumer<PrepareAnvilEvent> prepareAnvilEvent) {
		prepareAnvilEvents.put(0, prepareAnvilEvent);
		
		return setFirstItem(item);
	}
	
	public AnvilInventoryBuilder setFirstItemPrepareEvent(Consumer<PrepareAnvilEvent> prepareAnvilEvent) {
		return setFirstItemWithPrepareEvent(inventory.getItem(0), prepareAnvilEvent);
	}
	
	public AnvilInventoryBuilder setSecondItem(ItemStack item) {
		inventory.setItem(1, item);
		
		return this;
	}
	
	public AnvilInventoryBuilder setSecondItemWithClickEvent(ItemStack item, Consumer<InventoryClickEvent> event) {
		clickEvents.put(1, event);
		
		return setSecondItem(item);
	}
	
	public AnvilInventoryBuilder setSecondItemClickEvent(Consumer<InventoryClickEvent> clickEvent) {
		return setSecondItemWithClickEvent(inventory.getItem(1), clickEvent);
	}
	
	public AnvilInventoryBuilder setSecondItemWithPrepareEvent(ItemStack item, Consumer<PrepareAnvilEvent> prepareAnvilEvent) {
		prepareAnvilEvents.put(1, prepareAnvilEvent);
		
		return setFirstItem(item);
	}
	
	public AnvilInventoryBuilder setSecondItemPrepareEvent(Consumer<PrepareAnvilEvent> prepareAnvilEvent) {
		return setSecondItemWithPrepareEvent(inventory.getItem(1), prepareAnvilEvent);
	}
	
	public AnvilInventoryBuilder setResult(ItemStack item) {
		inventory.setItem(2, item);
		
		return this;
	}
	
	public AnvilInventoryBuilder setResultWithClickEvent(ItemStack item, Consumer<InventoryClickEvent> event) {
		clickEvents.put(2, event);
		
		return setResult(item);
	}
	
	public AnvilInventoryBuilder setResultClickEvent(Consumer<InventoryClickEvent> clickEvent) {
		return setResultWithClickEvent(inventory.getItem(1), clickEvent);
	}
	
	public AnvilInventoryBuilder setResultWithPrepareEvent(ItemStack item, Consumer<PrepareAnvilEvent> prepareAnvilEvent) {
		prepareAnvilEvents.put(1, prepareAnvilEvent);
		
		return setFirstItem(item);
	}
	
	public AnvilInventoryBuilder setResultPrepareEvent(Consumer<PrepareAnvilEvent> prepareAnvilEvent) {
		return setResultWithPrepareEvent(inventory.getItem(1), prepareAnvilEvent);
	}
	
	public AnvilInventoryBuilder setLevelRequirement(int requiredLevels) {
		this.requiredLevels = requiredLevels;
		
		return this;
	}
	
	public int getRequiredLevels() {
		return requiredLevels;
	}
	
	public Map<Integer, Consumer<InventoryClickEvent>> getClickEvents() {
		return new HashMap<>(clickEvents);
	}
	
	public Map<Integer, Consumer<PrepareAnvilEvent>> getPrepareAnvilEvents() {
		return new HashMap<>(prepareAnvilEvents);
	}
	
	public Inventory build() {
		return inventory;
	}
}