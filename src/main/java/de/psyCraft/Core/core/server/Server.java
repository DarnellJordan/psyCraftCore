package de.psyCraft.Core.core.server;

import com.onarandombox.MultiverseCore.api.MVWorldManager;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import de.psyCraft.Core.PsyCraftCore;
import de.psyCraft.Core.api.game.NavigatorItem;
import de.psyCraft.Core.core.hub.HubManager;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * @author psyGamer
 */
public abstract class Server {
	
	static final MVWorldManager manager = PsyCraftCore.getMultiverseCore().getMVWorldManager();
	
	public final String serverName;
	public final int serverID;
	
	protected final Map<Player, World> connectedPlayers = new HashMap();
	protected final Map<String, MultiverseWorld> worlds = new HashMap();
	
	protected Server(final String serverName, final int serverID) {
		this.serverName = serverName;
		this.serverID = serverID;
	}
	
	/**
	 * Should return the {@link ItemStack Item} that will be displayed to the {@link Player}
	 *
	 * @param player The player that has the {@link de.psyCraft.Core.core.hub.items.Navigator Navigator} open.
	 * @return The {@link ItemStack Item} that will be display in the {@link de.psyCraft.Core.core.hub.items.Navigator Navigator}. <br />
	 * Return {@code null} if it should be hidden.
	 */
	public abstract Class<? extends NavigatorItem> getNavigatorItem(Player player);
	
	/**
	 * Gets call after loading the {@link Server}.
	 *
	 * @author psyGamer
	 * @since 1.0
	 */
	public abstract void onServerEnable();
	
	/**
	 * Gets call before deleting a {@link Server}
	 *
	 * @author psyGamer
	 * @since 1.0
	 */
	protected abstract void onServerDeletion();
	
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
	
	final void startServer() {
		for (MultiverseWorld mvWorld : worlds.values()) {
			manager.loadWorld(mvWorld.getName());
		}
		
		onServerEnable();
	}
	
	final void stopServer() {
		onServerDisable();
		
		for (MultiverseWorld mvWorld : worlds.values()) {
			World world = mvWorld.getCBWorld();
			
			for (Player player : world.getPlayers()) {
				onServerLeave(player);
				
				player.teleport(HubManager.HUB_WORLD.getSpawnLocation());
			}
			
			manager.deleteWorld(mvWorld.getName(), true, true);
		}
	}
	
	final void deleteServer() {
		onServerDisable();
		onServerDeletion();
		
		for (MultiverseWorld mvWorld : worlds.values()) {
			World world = mvWorld.getCBWorld();
			
			for (Player player : world.getPlayers()) {
				onServerLeave(player);
				
				player.teleport(HubManager.HUB_WORLD.getSpawnLocation());
			}
			
			manager.deleteWorld(mvWorld.getName(), true, true);
		}
	}
}
