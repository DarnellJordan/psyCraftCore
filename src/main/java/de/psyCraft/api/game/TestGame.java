package de.psyCraft.api.game;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

public class TestGame implements GameMode {
	
	@Override
	public String getName() {
		return null;
	}
	
	@Override
	public String getDescription() {
		return null;
	}
	
	@Override
	public Material getIcon() {
		return null;
	}
	
	@Override
	public void onServerInitialize() {
	
	}
	
	@Override
	public void onServerEnable() {
	
	}
	
	@Override
	public void onServerDisable() {
	
	}
	
	@Override
	public void onGameJoin(Player player) {
	
	}
	
	@Override
	public void onGameLobbyJoin(List<Player> players) {
	
	}
	
	@Override
	public void onGameLeave(Player player) {
	
	}
}
