package de.psyCraft.Core.core.server;

import com.onarandombox.MultiverseCore.api.MVWorldManager;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import de.psyCraft.Core.PsyCraftCore;
import de.psyCraft.Core.api.game.GameMode;
import de.psyCraft.Core.core.server.events.ServerWorldCreationEvent;
import de.psyCraft.Core.util.world.ServerWorldUtil;
import org.bukkit.World.Environment;
import org.bukkit.WorldType;

import java.util.HashMap;
import java.util.Map;

public class Server {
	
	private static final MVWorldManager manager = PsyCraftCore.getMultiverseCore().getMVWorldManager();
	
	private final Map<String, MultiverseWorld> worlds = new HashMap<>();
	
	private final GameMode gameMode;
	private final String worldName;
	private final int serverID;
	
	private ServerStatus status;
	
	public Server(GameMode gameMode, int serverID) {
		this.gameMode = gameMode;
		this.serverID = serverID;
		
		worldName = gameMode.getName();
		status = ServerStatus.OFFLINE;
	}
	
	void start() {
		if (status != ServerStatus.OFFLINE) {
			return;
		}
		
		status = ServerStatus.STARTING;
		
		System.out.println("load");
		if (!manager.loadWorld(ServerWorldUtil.getWorldString(worldName, serverID, "main"))) {
			System.out.println("start");
			createWorld();
		}
		System.out.println("done");
		
		worlds.put("main", manager.getMVWorld(ServerWorldUtil.getWorldString(worldName, serverID, "main")));
		gameMode.onServerEnable(this);
		
		status = ServerStatus.ONLINE;
	}
	
	public void shutdown() {
		if (status == ServerStatus.OFFLINE) {
			return;
		}
		
		status = ServerStatus.STOPPING;
		
		gameMode.onServerDisable(this);
		manager.unloadWorld(worldName);
		
		status = ServerStatus.OFFLINE;
	}
	
	public void createWorld() {
		createWorld("main");
	}
	
	public void createWorld(String suffix) {
		if (!(suffix.isEmpty() || ServerWorldUtil.isWorldNameValid(suffix)) || manager.getMVWorld(worldName) != null) {
			System.out.println(!ServerWorldUtil.isWorldNameValid(suffix));
			System.out.println(manager.getMVWorld(worldName) != null);
			return;
		}
		
		ServerWorldCreationEvent event = new ServerWorldCreationEvent(
				this,
				ServerWorldUtil.getWorldString(worldName, serverID, suffix)
		);
		
		gameMode.onServerWorldCreation(event);
		
		String name = event.getWorldName();
		Environment environment = event.getEnvironment();
		String seed = event.getSeed();
		String generator = event.getGenerator();
		WorldType type = event.getType();
		
		boolean generateStructures = event.isGeneratingStructures();
		
		manager.addWorld(name, environment, seed, type, generateStructures, generator);
		worlds.put(suffix, manager.getMVWorld(name));
		
		gameMode.onServerInitialize(this);
	}
	
	public GameMode getGameMode() {
		return gameMode;
	}
	
	public Map<String, MultiverseWorld> getWorlds() {
		return new HashMap<String, MultiverseWorld>(worlds);
	}
	
	public int getServerID() {
		return serverID;
	}
}
