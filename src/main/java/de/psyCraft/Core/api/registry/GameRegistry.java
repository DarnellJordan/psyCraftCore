package de.psyCraft.Core.api.registry;

import de.psyCraft.Core.PsyCraftCore;
import de.psyCraft.Core.api.game.GameMode;
import de.psyCraft.Core.util.world.ServerWorldUtil;

import java.util.HashMap;
import java.util.Map;

public class GameRegistry {
	
	private static final Map<String, GameMode> gameModes = new HashMap<>();
	
	public static boolean registerGame(GameMode gameMode) {
		if (!ServerWorldUtil.isWorldNameValid(gameMode.getName())) {
			PsyCraftCore.logger().warning("§cThe name: " + gameMode.getName() + " does not match the criteria to be registered ([a-z, A-Z, 0-9, _], min length: 1 char, max length: 64 chars");
			
			return false;
		}
		
		for (GameMode game : gameModes.values()) {
			if (gameMode.getName().equalsIgnoreCase(game.getName())) {
				PsyCraftCore.logger().warning("§cGame Mode: " + gameMode.getName() + " already exists");
				
				return false;
			}
		}
		
		gameModes.put(gameMode.getName(), gameMode);
		PsyCraftCore.logger().info("§aSuccessfully loaded Game Mode: " + gameMode.getName());
		
		return true;
	}
	
	public static Map<String, GameMode> getRegisteredGameModes() {
		return new HashMap<>(gameModes);
	}
}
