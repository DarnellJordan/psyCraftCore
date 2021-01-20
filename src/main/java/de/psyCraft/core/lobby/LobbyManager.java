package de.psyCraft.core.lobby;

import de.psyCraft.api.builders.GUIBilder;
import de.psyCraft.api.builders.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class LobbyManager {
	
	// Lobby Items
	
	public static final ItemStack COMPASS = new ItemBuilder(Material.COMPASS)
			.setDisplayName("§c§lNavigation §8|§7§o Rechtsklick")
			.build();
	
	// Inventory Items
	
	public static final ItemStack PLACEHOLDER = new ItemBuilder(Material.GRASS_BLOCK)
			.setDisplayName("Placeholder")
			.build();
	
	// Lobby Inventories
	
	public static final Inventory NAVIGATION = new GUIBilder(5, "§cNavigation §8§l|§7 Wähle einen Spielmodus")
			.addItemWithClickEvent(4, 1, PLACEHOLDER, (player) -> {
				player.sendMessage("Placeholder");
			})
			.build();
	
	// Lobby World
	
	public static final World LOBBY_WORLD = Bukkit.getWorld("world");
	
}
