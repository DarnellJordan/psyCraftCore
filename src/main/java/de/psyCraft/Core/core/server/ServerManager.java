package de.psyCraft.Core.core.server;

import de.psyCraft.Core.api.registry.ServerRegistry;
import de.psyCraft.Core.util.refelct.ReflactionUtil;
import de.psyCraft.Core.util.server.ServerUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author psyGamer
 */
public class ServerManager {
	
	private static final Map<String, List<Server>> servers = new HashMap<>();
	private static final List<Server> activeServers = new ArrayList<>();
	
	public static Map<String, List<Server>> getServers() {
		return new HashMap<>(servers);
	}
	
	public static final void startServer(final Server server) {
		server.startServer();
		
		activeServers.add(server);
	}
	
	public static final void startServer(final String serverName, final int id) {
		startServer(ServerUtil.getServer(serverName, id));
	}
	
	public static final void stopServer(final Server server) {
//		activeServers.remove(server);
		
		server.stopServer();
	}
	
	public static final void stopServer(final String serverName, final int id) {
		stopServer(ServerUtil.getServer(serverName, id));
	}
	
	public static final Server createServer(final String serverName, final int id) {
		Server server;
		
		try {
			final Class serverClass = ServerRegistry.getServerClass(serverName);
			final Object[] parameters = ServerRegistry.getServerConstuctorArgumentTypes(serverClass);
			
			server = (Server) ReflactionUtil.newInstance(serverClass, parameters);
		} catch (Exception e) {
			return null;
		}
		
		if (!servers.containsKey(serverName)) {
			servers.put(serverName, new ArrayList<>());
		}
		
		servers.get(serverName).add(server);
		
		return server;
	}
	
	public static final void deleteServer(final String serverName, final int id) {
		Server server = ServerUtil.getServer(serverName, id);
		
		if (server == null) {
			return;
		}
		
		server.deleteServer();
		
		servers.get(serverName).remove(server);
	}
}
