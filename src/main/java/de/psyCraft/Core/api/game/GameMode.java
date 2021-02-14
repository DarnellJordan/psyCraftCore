package de.psyCraft.Core.api.game;

import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * The interface to implement a new GameMode
 *
 * @author psyGamer
 * @since 1.0
 */
public interface GameMode {
	/**
	 * Should return the internal registry name. <br /> <br />
	 * Allowed characters: a-z A-Z 0-9 _ <br />
	 * Minimum length: 1 <br />
	 * Maximum length: 64 <br />
	 *
	 * @return Internal that will be used for registry.
	 * @author psyGamer
	 * @since 1.0
	 */
	String getName();
	
	/**
	 * Should return the name that will be display in the Navigator. <br /> <br />
	 * You can use {@code &} or {@code §} for color does and {@code #<HexCode>} for hex codes.
	 *
	 * @return Display name that wil be shown to the player.
	 * @author psyGamer
	 * @since 1.0
	 */
	String getDisplayName();
	
	/**
	 * Should return the description shown in the Navigator. <br /> <br />
	 * You can use {@code &} or {@code §} for color does and {@code #<HexCode>} for hex codes.
	 *
	 * @return Description that will be shown to the player.
	 * @author psyGamer
	 * @since 1.0
	 */
	List<String> getDescription();
	
	/**
	 * Should return the {@link ItemStack} that will be shown in the Navigator. <br />
	 * Some attributes of the {@link org.bukkit.inventory.meta.ItemMeta ItemMeta} may be overwritten.
	 *
	 * @return {@link ItemStack} that will be shown to the player.
	 * @author psyGamer
	 * @since 1.0
	 */
	ItemStack getItem();
}
