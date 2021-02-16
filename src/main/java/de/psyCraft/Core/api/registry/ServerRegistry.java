package de.psyCraft.Core.api.registry;

import de.psyCraft.Core.api.game.GameMode;
import de.psyCraft.Core.core.server.Server;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author psyGamer
 */
public class ServerRegistry {
	
	private static final List<Server> servers = new ArrayList<>();
	
	public static boolean registerServer(Server server) {
		// TODO some checks maybe
		servers.add(server);
		return true;
	}
	
	public static List<Server> getRegisteredServers() {
		return new ArrayList<>(servers);
	}
	
	public static final Map<String, Class<? extends Server>> getRegisteredServerClasses() {
		return new HashMap<>(serverClasses);
	}
	
	public static final Class<? extends Server> getServerClass(final String serverName) {
		for (int i = 0 ; i < serverClasses.size() ; i++) {
			System.out.println("serverClasses[" + i + "]: " + serverClasses.keySet().toArray(new NavigatorItem[0])[i] + ":" + serverClasses.keySet().toArray(new NavigatorItem[0])[i] + ", " + serverClasses.values().toArray(new Class[0])[i]);
		}
		return serverClasses.get(serverName);
	}
	
	public static final Object[] getServerConstuctorArguments(final Class<? extends Server> serverClass) {
		return serverClassConstructorArguments.get(serverClass);
	}
	
	public static final Class[] getServerConstuctorArgumentTypes(final Class<? extends Server> serverClass) {
		return serverClassConstructorArgumentTypes.get(serverClass);
	}
}
