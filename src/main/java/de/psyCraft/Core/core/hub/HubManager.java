package de.psyCraft.Core.core.hub;

import de.psyCraft.Core.core.hub.items.HubItem;
import de.psyCraft.Core.core.hub.items.Navigator;
import de.psyCraft.Core.util.item.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;

public class HubManager {
	
	public static final ItemStack PLACEHOLDER = new ItemBuilder(Material.GRASS_BLOCK)
			.setDisplayName("Placeholder")
			.build();
	
	public static final World HUB_WORLD = Bukkit.getWorld("world");
	
	public static final HubItem NAVIGATOR = new Navigator();
	
}
