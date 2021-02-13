package de.psyCraft.Core.api.game;

import de.psyCraft.Core.core.server.Server;
import de.psyCraft.Core.core.server.events.ServerWorldCreationEvent;
import org.bukkit.entity.Player;
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
	 * Defines where a {@link Player} should connect to when joining a {@link Server}
	 *
	 * @author psyGamer
	 * @since 1.0
	 */
	enum AccessLevel {
		/**
		 * The {@link Player} is not able to connect to either the {@link Server} or the Lobby.
		 *
		 * @since 1.0
		 */
		FORBIDDEN,
		
		/**
		 * The {@link Player} will be sent to the Lobby and needs to be moved, to be able to join the{@link Server}
		 *
		 * @since 1.0
		 */
		LOBBY,
		
		/**
		 * The {@link Player} will be immediately sent to the {@link Server Main Server}
		 *
		 * @since 1.0
		 */
		SERVER
	}
	
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
	
	/**
	 * Should return the {@link AccessLevel AccessLevel} specific to that {@link Player}.
	 *
	 * @param player The {@link Player} of which the {@link AccessLevel AccessLevel} is being requested,
	 * @return {@link AccessLevel AccessLevel} of {@link Player}.
	 * @author psyGamer
	 * @see AccessLevel
	 * @since 1.0
	 */
	AccessLevel getAccessLevel(Player player);
	
	/**
	 * Gets call before creating the {@link org.bukkit.World World} for the {@link Server}. <br />
	 * Use this to set generation settings.
	 *
	 * @param event The {@link ServerWorldCreationEvent} to set generation settings.
	 * @author psyGamer
	 * @since 1.0
	 */
	void onServerWorldCreation(ServerWorldCreationEvent event);
	
	/**
	 * Gets call after creating the {@link org.bukkit.World World} for the {@link Server}. <br />
	 * Use this to initialize the {@link org.bukkit.World World}.
	 *
	 * @param server The {@link Server} that is currently initializing.
	 * @author psyGamer
	 * @since 1.0
	 */
	void onServerInitialize(Server server);
	
	/**
	 * Gets call after loading the {@link Server}.
	 *
	 * @param server The {@link Server} that is currently booting up.
	 * @author psyGamer
	 * @since 1.0
	 */
	void onServerEnable(Server server);
	
	/**
	 * Gets call before unloading the {@link Server}.
	 *
	 * @param server The {@link Server} that is currently shutting down.
	 * @author psyGamer
	 * @since 1.0
	 */
	void onServerDisable(Server server);
	
	/**
	 * Gets called when a {@link Player} directly joins the server from the hub.
	 *
	 * @param server The {@link Server} the player tries to connect to.
	 * @param player The {@link Player} that tries to connect.
	 */
	void onGameJoin(Server server, Player player);
	
	/**
	 * Gets called when one or more players get moved from a LobbyServer to a Server.
	 *
	 * @param server  The {@link Server} to which players will be moved to.
	 * @param players A {@link List} of {@link Player Players} which will be moved to the server.
	 */
	void onGameLobbyJoin(Server server, List<Player> players);
	
	/**
	 * Gets called when a {@link Player} disconnects from a {@link Server}.
	 *
	 * @param server The {@link Server}, which the player is disconnecting from.
	 * @param player The {@link Player} which disconnects
	 */
	void onGameLeave(Server server, Player player);
}
