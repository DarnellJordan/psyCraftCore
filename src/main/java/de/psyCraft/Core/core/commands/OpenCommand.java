package de.psyCraft.Core.core.commands;

import org.bukkit.block.Biome;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Random;

public class OpenCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
//		BaseInventory.invs.get(strings[0]).openInventory((Player) commandSender);
		int pick = new Random().nextInt(Biome.values().length);
		commandSender.sendMessage(Biome.values()[pick].toString());
		return true;
	}
}
