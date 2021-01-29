package de.psyCraft;

import de.psyCraft.api.game.TestGame;
import de.psyCraft.api.inventory.BaseInventory;
import de.psyCraft.api.inventory.EventInventory;
import de.psyCraft.api.plugin.registry.GameRegistry;
import de.psyCraft.core.commands.NpcCommand;
import de.psyCraft.core.commands.OpenCommand;
import de.psyCraft.core.commands.RemoveCommand;
import de.psyCraft.core.commands.Skull;
import de.psyCraft.core.lobby.listeners.LobbyClickListener;
import de.psyCraft.core.lobby.listeners.LobbyInteractionListener;
import de.psyCraft.core.lobby.listeners.LobbyJoinListener;
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
	public void onEnable() {
		INSTANCE = this;
		logger = getLogger();
		
		// didn't want to create unique game modes
		GameRegistry.registerGame(new TestGame(1));
		GameRegistry.registerGame(new TestGame(2));
		GameRegistry.registerGame(new TestGame(3));
		GameRegistry.registerGame(new TestGame(4));
		GameRegistry.registerGame(new TestGame(5));
		GameRegistry.registerGame(new TestGame(6));
		GameRegistry.registerGame(new TestGame(7));
		GameRegistry.registerGame(new TestGame(8));
//		GameRegistry.registerGame(new TestGame(9));
//		GameRegistry.registerGame(new TestGame(10));
		
		register();
	}
	
	private void register() {
		registerCommands();
		registerListeners(getServer().getPluginManager());
	}
	
	private void registerCommands() {
		getCommand("npc").setExecutor(new NpcCommand(this));
		getCommand("remove").setExecutor(new RemoveCommand(this));
		getCommand("head").setExecutor(new Skull());
		getCommand("open").setExecutor(new OpenCommand());
	}
	
	private void registerListeners(PluginManager manager) {
		manager.registerEvents(new LobbyJoinListener(), this);
		manager.registerEvents(new LobbyClickListener(), this);
		manager.registerEvents(new LobbyInteractionListener(), this);
		
		manager.registerEvents(new BaseInventory.InventoryListener(), this);
		manager.registerEvents(new EventInventory.ClickListener(), this);
	}
	
	@Override
	public void onDisable() {
		// Plugin shutdown logic
	}
}
