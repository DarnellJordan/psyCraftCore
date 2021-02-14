package de.psyCraft.Core.api.game;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class TestGame implements GameMode {
	
	String name;
	
	public TestGame(int name) {
		this.name = "" + name;
	}
	
	@Override
	public String getName() {
		return "game_" + name;
	}
	
	@Override
	public String getDisplayName() {
		return name;
	}
	
	@Override
	public List<String> getDescription() {
		final List<String> description = new ArrayList<>();
		
		description.add("A test game");
		description.add("(which is cool)");
		
		return description;
	}
	
	@Override
	public ItemStack getItem() {
		return new ItemStack(Material.WOODEN_AXE);
	}
}
