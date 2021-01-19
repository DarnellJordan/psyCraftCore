package de.psyCraft.core.lobby;

import de.psyCraft.core.items.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;

public class LobbyManager {

	// Lobby Items
	
	public static final ItemStack COMPASS = new ItemBuilder(Material.COMPASS)
			.setDisplayName("§c§lNavigation §8|§7§o Rechtsklick")
			.build();
	
	// Lobby World
	
	public static final World LOBBY_WORLD = Bukkit.getWorld("world");
	
}
