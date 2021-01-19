package de.psyCraft.api.plugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class psyCraftPlugin extends JavaPlugin {
	
	@Override
	public void onEnable() {
		Bukkit.broadcastMessage("Loaded psyCraftPlugin");
	}
	
	@Override
	public void onDisable() {
		Bukkit.broadcastMessage("Unloaded psyCraftPlugin");
	}
}
