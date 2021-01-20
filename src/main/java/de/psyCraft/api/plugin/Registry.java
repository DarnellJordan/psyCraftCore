package de.psyCraft.api.plugin;

import de.psyCraft.api.game.GameMode;

import java.util.ArrayList;
import java.util.List;

public class Registry {
	public static void registerGame(String command, GameMode gameCommand) {
		registerGame(command, new ArrayList<>(), gameCommand);
	}
	
	public static void registerGame(String command, List<String> aliases, GameMode gameCommand) {
	
	}
}
