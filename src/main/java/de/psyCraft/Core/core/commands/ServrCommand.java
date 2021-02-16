package de.psyCraft.Core.core.commands;

import de.psyCraft.Core.core.server.ServerManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ServrCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
		final String serverName = args[1];
		final int serverID = Integer.parseInt(args[2]);
		
		switch (args[0]) {
			case "create":
				ServerManager.createServer(serverName, serverID);
				break;
			case "delete":
				ServerManager.deleteServer(serverName, serverID);
				break;
			case "start":
				ServerManager.startServer(serverName, serverID);
				break;
			case "stop":
				ServerManager.stopServer(serverName, serverID);
				break;
		}
		
		return true;
	}
}
