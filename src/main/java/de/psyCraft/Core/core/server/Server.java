package de.psyCraft.Core.core.server;

import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import de.psyCraft.Core.api.game.GameMode;
import de.psyCraft.Core.core.server.legacy.LobbyServer;
import de.psyCraft.Core.core.server.legacy.events.ServerWorldCreationEvent;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author psyGamer
 */
public abstract class Server {
	
	public final GameMode gameMode;
	public final int serverID;
	
	protected final Map<Player, World> connectedPlayers = new HashMap();
	protected final Map<String, MultiverseWorld> worlds = new HashMap();
	
	protected Server(GameMode gameMode, int serverID) {
		this.gameMode = gameMode;
		this.serverID = serverID;
	}
	
	/**
	 * Gets call before creating the {@link org.bukkit.World World} for the {@link Server}. <br />
	 * Use this to set generation settings.
	 *
	 * @param event The {@link ServerWorldCreationEvent} to set generation settings.
	 * @author psyGamer
	 * @since 1.0
	 */
	public abstract void onServerWorldCreation(ServerWorldCreationEvent event);
	
	/**
	 * Gets call after creating the {@link org.bukkit.World World} for the {@link Server}. <br />
	 * Use this to initialize the {@link org.bukkit.World World}.
	 *
	 * @author psyGamer
	 * @since 1.0
	 */
	public abstract void onServerInitialize();
	
	/**
	 * Gets call after loading the {@link Server}.
	 *
	 * @author psyGamer
	 * @since 1.0
	 */
	public abstract void onServerEnable();
	
	/**
	 * Gets call before unloading the {@link Server}.
	 *
	 * @author psyGamer
	 * @since 1.0
	 */
	public abstract void onServerDisable();
	
	/**
	 * Gets called when a {@link Player} directly joins the server from the hub.
	 *
	 * @param player The {@link Player} that tries to connect.
	 */
	public abstract void onServerJoin(Player player);
	
	/**
	 * Gets called when one or more players get moved from a LobbyServer to a Server.
	 *
	 * @param players A {@link List} of {@link Player Players} which will be moved to the server.
	 * @deprecated
	 */
	@Deprecated
	public abstract void onServerJoinFromLobby(List<Player> players);
	
	/**
	 * Gets called when a {@link Player} disconnects from a {@link de.psyCraft.Core.core.server.legacy.Server}.
	 *
	 * @param player The {@link Player} which disconnects
	 */
	public abstract void onServerLeave(Player player);
	
	/**
	 * Should return the {@link AccessLevel AccessLevel} specific to that {@link Player}.
	 *
	 * @param player The {@link Player} of which the {@link AccessLevel AccessLevel} is being requested,
	 * @return {@link AccessLevel AccessLevel} of {@link Player}.
	 * @author psyGamer
	 * @since 1.0
	 */
	public abstract AccessLevel getAccessLevel(Player player);
	
	/**
	 * Gets called when a {@link Player} joins a {@link LobbyServer}.
	 *
	 * @param server The {@link LobbyServer} the player tries to connect to.
	 * @param player The {@link Player} that tries to connect.
	 * @deprecated
	 */
	@Deprecated
	public void onLobbyServerJoin(LobbyServer server, Player player) {
	
	}
	
	/**
	 * Gets called when a {@link Player} disconnects from a {@link LobbyServer}.
	 *
	 * @param server The {@link LobbyServer}, which the player is disconnecting from.
	 * @param player The {@link Player} which disconnects
	 * @deprecated
	 */
	@Deprecated
	public void onLobbyServerLeave(LobbyServer server, Player player) {
	
	}
}
