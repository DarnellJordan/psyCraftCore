package de.psyCraft.Core.util.world;

import java.util.regex.Pattern;

public class ServerWorldUtil {
	
	private static final Pattern validWorldName = Pattern.compile("[a-zA-Z0-9_]{1,64}");
	
	public static String getWorldString(String worldName, int serverID, String suffix) {
		return worldName + "-" + String.valueOf(serverID) + "/" + suffix;
	}
	
	public static boolean isWorldNameValid(String worldName) {
		System.out.println();
		return validWorldName.matcher(worldName).matches();
	}
}
