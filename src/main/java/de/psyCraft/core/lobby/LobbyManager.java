package de.psyCraft.core.lobby;

import de.psyCraft.api.builders.ItemBuilder;
import de.psyCraft.core.lobby.items.LobbyItem;
import de.psyCraft.core.lobby.items.NavigatorItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;

public class LobbyManager {
	
	public static final ItemStack PLACEHOLDER = new ItemBuilder(Material.GRASS_BLOCK)
			.setDisplayName("Placeholder")
			.build();
	
	public static final World WORLD = Bukkit.getWorld("world");
	
	public static final LobbyItem NAVIGATOR = new NavigatorItem();
	
}
