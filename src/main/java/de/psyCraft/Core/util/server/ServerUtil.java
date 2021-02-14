package de.psyCraft.Core.util.server;

import de.psyCraft.Core.api.game.GameMode;
import de.psyCraft.Core.core.server.Server;

import java.util.regex.Pattern;

/**
 * @author psyGamer
 */
public class ServerUtil {
	
	private static final Pattern validWorldName = Pattern.compile("[a-zA-Z0-9_]{1,64}");
	
	public static Server getServer(GameMode gameMode) {
		return null;
	}
	
	public static Server getServer(GameMode gameMode, int id) {
		return null;
	}
	
	public static String getWorldString(String worldName, int serverID, String suffix) {
		return worldName + "-" + String.valueOf(serverID) + "/" + suffix;
	}
	
	public static boolean isWorldNameValid(String worldName) {
		System.out.println();
		return validWorldName.matcher(worldName).matches();
	}
}
