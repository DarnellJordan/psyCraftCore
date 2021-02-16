package de.psyCraft.Core.api.game;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TestGame implements NavigatorItem {
	
	String name;
	
	public TestGame(int name) {
		this.name = "" + name;
		if (name == 0) {
			this.name = "";
		}
		if (name == 69) {
			this.name = "#YEET";
		}
		if (name == 420) {
			this.name = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVQXYZ01234567889abcdef";
		}
	}
	
	@Override
	public String getDisplayName(Player player) {
		return name;
	}
	
	@Override
	public List<String> getDescription(Player player) {
		final List<String> description = new ArrayList<>();
		
		description.add("A test game");
		description.add("(which is cool)");
		
		return description;
	}
}
