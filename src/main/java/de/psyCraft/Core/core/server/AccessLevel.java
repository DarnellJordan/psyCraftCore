package de.psyCraft.Core.core.server;

import de.psyCraft.Core.core.server.legacy.Server;
import org.bukkit.entity.Player;

/**
 * Defines where a {@link Player} should connect to when joining a {@link de.psyCraft.Core.core.server.legacy.Server}
 *
 * @author psyGamer
 * @since 1.0
 */
public enum AccessLevel {
	/**
	 * The {@link Player} is not able to connect to either the {@link de.psyCraft.Core.core.server.legacy.Server} or the Lobby.
	 *
	 * @since 1.0
	 */
	FORBIDDEN,
	
	/**
	 * The {@link Player} will be sent to the Lobby and needs to be moved, to be able to join the{@link de.psyCraft.Core.core.server.legacy.Server}
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
