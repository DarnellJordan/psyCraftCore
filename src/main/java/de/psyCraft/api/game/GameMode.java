package de.psyCraft.api.game;

import de.psyCraft.core.server.Server;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

public interface GameMode {
	
	String getName();
	
	String getDescription();
	
	Material getIcon();
	
	void onServerInitialize(Server server);
	
	void onServerEnable(Server server);
	
	void onServerDisable(Server server);
	
	void onGameJoin(Player player);
	
	void onGameLobbyJoin(List<Player> players);
	
	void onGameLeave(Player player);
}
