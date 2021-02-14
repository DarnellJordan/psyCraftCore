package de.psyCraft.Core.api.registry;

import de.psyCraft.Core.PsyCraftCore;
import de.psyCraft.Core.api.game.GameMode;

import java.util.ArrayList;
import java.util.List;

public class GameRegistry {
	
	private static final List<GameMode> gameModes = new ArrayList<>();
	
	public static boolean registerGame(GameMode gameMode) {
//		if (!ServerWorldUtil.isWorldNameValid(gameMode.getName())) {
//			PsyCraftCore.logger().warning("§cThe name: " + gameMode.getName() + " does not match the criteria to be registered ([a-z, A-Z, 0-9, _], min length: 1 char, max length: 64 chars");
//
//			return false;
//		}
		
		for (GameMode game : gameModes) {
			if (gameMode.getName().equalsIgnoreCase(game.getName())) {
				PsyCraftCore.logger().warning("§cGame Mode: " + gameMode.getName() + " already exists");
				
				return false;
			}
		}
		
		gameModes.add(gameMode);
		PsyCraftCore.logger().info("§aSuccessfully loaded Game Mode: " + gameMode.getName());
		
		return true;
	}
	
	public static List<GameMode> getRegisteredGameModes() {
		return new ArrayList<>(gameModes);
	}
	
	public static GameMode getGameModeByName(String name) {
		return gameModes.stream()
				.filter(gameMode -> gameMode.getName().equalsIgnoreCase(name))
				.findFirst().get();
	}
}
