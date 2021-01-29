package de.psyCraft.Core.api.plugin.registry;

import de.psyCraft.Core.api.game.GameMode;
import de.psyCraft.Core.psyCraftCore;

import java.util.ArrayList;
import java.util.List;

public class GameRegistry {
	
	private static final List<GameMode> games = new ArrayList<>();
	
	public static void registerGame(GameMode gameMode) {
		for (GameMode game : games) {
			if (gameMode.getName().equalsIgnoreCase(game.getName())) {
				psyCraftCore.log(psyCraftCore.LogLevel.WARNING, "§cGame Mode: " + gameMode.getName() + " already exists");
				return;
			}
		}
		
		games.add(gameMode);
		psyCraftCore.log(psyCraftCore.LogLevel.INFO, "§aSuccessfully loaded Game Mode: " + gameMode.getName());
	}
	
	public static List<GameMode> getRegisteredGames() {
		return new ArrayList<>(games);
	}
}
