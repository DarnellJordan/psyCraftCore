package de.psyCraft.api.game;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

public interface GameMode {
	
	String getName();
	
	String getDescription();
	
	Material getIcon();
	
	void onServerInitialize();
	
	void onServerEnable();
	
	void onServerDisable();
	
	void onGameJoin(Player player);
	
	void onGameLobbyJoin(List<Player> players);
	
	void onGameLeave(Player player);
}
