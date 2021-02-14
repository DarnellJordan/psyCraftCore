package de.psyCraft.Core.core.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * @author psyGamer
 */
public class ResetCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		Player p = (Player) sender;
		
		for (Player pl : Bukkit.getOnlinePlayers()) {
			
			if (pl.equals(p)) {
				continue;
			}
			
			p.chat("/mv tp " + pl.getName() + " login");
			p.chat("/kick " + pl.getName() + " reset");
			
		}
		
		p.chat("/mv tp " + p.getName() + " login");
		p.chat("/kick " + p.getName() + " reset");
		
		return true;
	}
}
