package de.psyCraft.Core.util.server;

import de.psyCraft.Core.core.server.Server;
import de.psyCraft.Core.core.server.ServerManager;

import java.util.regex.Pattern;

/**
 * @author psyGamer
 */
public class ServerUtil {
	
	private static final Pattern validWorldName = Pattern.compile("[a-zA-Z0-9_]{1,16}");
	
	public static Server getServer(final String serverName, final int id) {
		for (Server server : ServerManager.getServers().get(serverName)) {
			if (server.serverID == id) {
				return server;
			}
		}
		
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
