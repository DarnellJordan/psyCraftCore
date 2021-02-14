package de.psyCraft.Core.core.commands;

import de.psyCraft.Core.api.game.GameMode;
import de.psyCraft.Core.api.registry.GameRegistry;
import de.psyCraft.Core.core.server.legacy.ServerManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class StartCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
		GameMode mode = GameRegistry.getGameModeByName(strings[0]);
		ServerManager.startServer(mode);
		
		return true;
	}
}
