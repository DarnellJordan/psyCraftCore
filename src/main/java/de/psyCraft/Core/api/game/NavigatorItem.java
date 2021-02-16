package de.psyCraft.Core.api.game;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Used to create an {@link ItemStack Item} for the {@link de.psyCraft.Core.core.hub.items.Navigator Navigator}. <br />
 * Override the {@link #getCustomItem(Player)} method to completely customize the {@link ItemStack Item}
 *
 * @author psyGamer
 * @see de.psyCraft.Core.core.hub.items.Navigator Navigator
 * @since 1.0
 */
public interface NavigatorItem {
	
	/**
	 * Should return the name that will be display in the {@link de.psyCraft.Core.core.hub.items.Navigator Navigator}. <br /> <br />
	 * You can use {@code &} or {@code §} for color does and {@code &#<HexCode>} for hex codes.
	 *
	 * @param player The {@link Player} the DisplayName is shown to.
	 * @return Display name that wil be shown to the player.
	 * @author psyGamer
	 * @since 1.0
	 */
	String getDisplayName(Player player);
	
	/**
	 * Should return the description shown in the {@link de.psyCraft.Core.core.hub.items.Navigator Navigator}. <br /> <br />
	 * You can use {@code &} or {@code §} for color does and {@code #<HexCode>} for hex codes.
	 *
	 * @param player The {@link Player} the Description is shown to.
	 * @return Description that will be shown to the player.
	 * @author psyGamer
	 * @since 1.0
	 */
	List<String> getDescription(Player player);
	
	/**
	 * Should return the {@link ItemStack Item} that will be shown to the {@link Player} in the {@link de.psyCraft.Core.core.hub.items.Navigator Navigator}. <br />
	 * To get a template of the default {@link ItemStack Item}, you can use the {@link de.psyCraft.Core.core.hub.items.Navigator#getItemPreset(NavigatorItem) Navigator.getItemPreset(NavigatorItem)} <br /> <br />
	 *
	 * @param player The {@link Player} the CustomItem is shown to.
	 * @return {@link ItemStack} that will be shown to the player.
	 * @author psyGamer
	 * @see de.psyCraft.Core.core.hub.items.Navigator#getItemPreset(NavigatorItem) Navigator.getItemPreset(NavigatorItem)
	 * @since 1.0
	 */
	default ItemStack getCustomItem(Player player) {
		return null;
	}
}
