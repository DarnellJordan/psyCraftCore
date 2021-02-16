package de.psyCraft.Core.core.server.legacy;

import com.onarandombox.MultiverseCore.api.MVWorldManager;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import de.psyCraft.Core.PsyCraftCore;
import de.psyCraft.Core.api.game.NavigatorItem;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server {
	
	private static final MVWorldManager manager = PsyCraftCore.getMultiverseCore().getMVWorldManager();
	
	private final List<Player> players = new ArrayList<>();
	private final Map<String, MultiverseWorld> worlds = new HashMap<>();
	
	private final LobbyServer lobbyServer = new LobbyServer(this);
	
	private final NavigatorItem navigatorItem;
	private final String worldName = "";
	private final int serverID;
	
	private ServerStatus status;
	
	public Server(NavigatorItem navigatorItem, int serverID) {
		this.navigatorItem = navigatorItem;
		this.serverID = serverID;

//		worldName = navigatorItem.getName();
		status = ServerStatus.OFFLINE;
		
		LobbyServerManager.createLobbyServer(this);
	}
	
	public void onPlayerJoin(Player player) {
		if (player == null) {
			return;
		}
		
		if (ServerManager.getPlayerConnections().containsKey(player)) {
			Server oldServer = ServerManager.getPlayerConnections().get(player);
			
			if (oldServer.equals(this)) {
				return;
			}
			
			oldServer.onPlayerDisconnect(player);
		}
		
		ServerManager.setPlayerConnection(player, this);

//		gameMode.onServerJoin(this, player);
	}
	
	public void onPlayersJoinFromLobby(List<Player> players) {
		if (players == null || players.isEmpty()) {
			return;
		}
		
		for (Player player : players) {
			
			if (ServerManager.getPlayerConnections().containsKey(player)) {
				Server oldServer = ServerManager.getPlayerConnections().get(player);
				
				if (oldServer.equals(this)) {
					return;
				}
				
				oldServer.onPlayerDisconnect(player);
			}
			
			ServerManager.setPlayerConnection(player, this);
		}

//		gameMode.onServerJoinFromLobby(this, players);
	}
	
	public void onPlayerDisconnect(Player player) {
//		gameMode.onServerLeave(this, player);
	}
	
	void start() {
		if (status != ServerStatus.OFFLINE) {
			return;
		}
		
		status = ServerStatus.STARTING;
		
		System.out.println("load");
//		if (!manager.loadWorld(ServerWorldUtil.getWorldString(worldName, serverID, "main"))) {
//			System.out.println("start");
//			createWorld();
//		}
		System.out.println("done");

//		worlds.put("main", manager.getMVWorld(ServerWorldUtil.getWorldString(worldName, serverID, "main")));
//		gameMode.onServerEnable(this);
//
		status = ServerStatus.ONLINE;

//		PsyCraftCore.logger().info("Server: " + navigatorItem.getName() + ":" + serverID + "successfully started");
	}
	
	public void shutdown() {
		if (status == ServerStatus.OFFLINE) {
			return;
		}
		
		status = ServerStatus.STOPPING;

//		gameMode.onServerDisable(this);
		manager.unloadWorld(worldName);
		
		status = ServerStatus.OFFLINE;
	}
	
	public void createWorld() {
		createWorld("main");
	}
	
	public void createWorld(String suffix) {
//		if (!(suffix.isEmpty() || ServerWorldUtil.isWorldNameValid(suffix)) || manager.getMVWorld(worldName) != null) {
//			System.out.println(!ServerWorldUtil.isWorldNameValid(suffix));
		System.out.println(manager.getMVWorld(worldName) != null);
		return;
//		}
//
//		ServerWorldCreationEvent event = new ServerWorldCreationEvent(
//				this,
//null
////				ServerWorldUtil.getWorldString(worldName, serverID, suffix)
//		);

//		gameMode.onServerWorldCreation(event);

//		String name = event.getWorldName();
//		Environment environment = event.getEnvironment();
//		String seed = event.getSeed();
//		String generator = event.getGenerator();
//		WorldType type = event.getType();
//
//		boolean generateStructures = event.isGeneratingStructures();

//		manager.addWorld(name, environment, seed, type, generateStructures, generator);
//		worlds.put(suffix, manager.getMVWorld(name));
//
//		gameMode.onServerInitialize(this);
	}
	
	public NavigatorItem getGameMode() {
		return navigatorItem;
	}
	
	public Map<String, MultiverseWorld> getWorlds() {
		return new HashMap<String, MultiverseWorld>(worlds);
	}
	
	public int getServerID() {
		return serverID;
	}
}
