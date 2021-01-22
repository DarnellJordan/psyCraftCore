package de.psyCraft;

import de.psyCraft.api.game.TestGame;
import de.psyCraft.api.plugin.Registry;
import de.psyCraft.core.commands.NpcCommand;
import de.psyCraft.core.commands.RemoveCommand;
import de.psyCraft.core.lobby.LobbyClickListener;
import de.psyCraft.core.lobby.LobbyJoinListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class psyCraftCore extends JavaPlugin {
	
	public static psyCraftCore INSTANCE;
	
	private static Logger logger;
	
	public enum LogLevel {
		INFO,
		WARNING
	}
	
	public static void log(LogLevel level, Object message) {
		switch (level) {
			case INFO:
				logger.info(message.toString());
				break;
			case WARNING:
				logger.warning(message.toString());
				break;
		}
	}
	
	@Override
	public void onLoad() {
		INSTANCE = this;
	}
	
	@Override
	public void onEnable() {
		logger = getLogger();
		
		Registry.registerGame(new TestGame());
		
		register();
	}
	
	private void register() {
		registerCommands();
		registerListeners(getServer().getPluginManager());
	}
	
	private void registerCommands() {
		getCommand("npc").setExecutor(new NpcCommand(this));
		getCommand("remove").setExecutor(new RemoveCommand(this));
	}
	
	private void registerListeners(PluginManager manager) {
		manager.registerEvents(new LobbyJoinListener(), this);
		manager.registerEvents(new LobbyClickListener(), this);
	}
	
	@Override
	public void onDisable() {
		// Plugin shutdown logic
	}
}
