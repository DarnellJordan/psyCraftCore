package de.psyCraft.api.plugin;

import de.psyCraft.api.game.GameMode;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

public class Registry {
	
	private static final List<GameMode> games = new ArrayList<>();
	
	public static void registerGame(GameMode gameMode) {
		for (GameMode game : games) {
			if (gameMode.getName().equalsIgnoreCase(game.getName())) {
				Bukkit.getConsoleSender().sendMessage("§cGame Mode: " + gameMode.getName() + " already exists");
				return;
			}
		}
		
		games.add(gameMode);
	}
}
