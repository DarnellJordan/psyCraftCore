package de.psyCraft.api.game;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Game {

	public static List<Game> games = new ArrayList<>();
	
	public final String name;
	public final String description;
	public final ItemStack icon;
	
	
	public Game(String name, String description, ItemStack icon) {
		this.name = name;
		this.description = description;
		this.icon = icon;
		
		games.add(this);
	}
}
