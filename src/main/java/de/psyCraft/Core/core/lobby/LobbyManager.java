package de.psyCraft.Core.core.lobby;

import de.psyCraft.Core.core.lobby.items.LobbyItem;
import de.psyCraft.Core.core.lobby.items.NavigatorItem;
import de.psyCraft.Core.util.item.ItemBuilder;
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
