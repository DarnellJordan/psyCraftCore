package de.psyCraft.core.server;

import de.psyCraft.api.game.GameMode;
import org.bukkit.World;

public class Server {
	
	private final GameMode gameMode;
	private final World world;
	private final int serverID;
	
	public Server(GameMode gameMode, World world, int serverID) {
		this.gameMode = gameMode;
		this.world = world;
		this.serverID = serverID;
		
		
	}
	
	public GameMode getGameMode() {
		return gameMode;
	}
	
	public World getWorld() {
		return world;
	}
	
	public int getServerID() {
		return serverID;
	}
}
