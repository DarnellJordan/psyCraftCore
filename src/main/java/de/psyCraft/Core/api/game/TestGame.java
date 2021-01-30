package de.psyCraft.Core.api.game;

import de.psyCraft.Core.core.server.Server;
import de.psyCraft.Core.util.gui.AnvilInventoryBuilder;
import de.psyCraft.Core.util.inventory.AnvilEventInventory;
import de.psyCraft.Core.util.inventory.BaseInventory;
import de.psyCraft.Core.util.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TestGame implements GameMode {
	
	String name;
	
	public TestGame(int name) {
		this.name = "" + name;
	}
	
	@Override
	public String getName() {
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
	public Material getIcon() {
		return Material.WOODEN_AXE;
	}
	
	@Override
	public boolean requireLobby() {
		return true;
	}
	
	@Override
	public boolean allowReconnect() {
		return true;
	}
	
	@Override
	public long getReconnectTimeout() {
		return 0;
	}
	
	@Override
	public void onServerInitialize(Server server) {
		server.getWorld().getHighestBlockAt(0, 0).setType(Material.GOLD_BLOCK);
	}
	
	@Override
	public void onServerEnable(Server server) {
		server.getWorld().getHighestBlockAt(server.getServerID(), 0).setType(Material.IRON_BLOCK);
	}
	
	@Override
	public void onServerDisable(Server server) {
		server.getWorld().getHighestBlockAt(-server.getServerID(), 0).setType(Material.LAPIS_BLOCK);
	}
	
	@Override
	public void onGameJoin(Player player) {
		player.sendMessage("Hello there");
//		player.getLocation().getBlock().setType(Material.ANVIL);
//		Inventory inventory = player.openAnvil(player.getLocation(), true).getTopInventory();
//
////		inventory.setItem(0, new ItemBuilder(Material.PAPER)
////				.setDisplayName(" ")
////				.build());
//		inventory.setItem(2, new ItemBuilder(Material.BOOK)
//				.addEnchantmentGlint()
//				.build());
		
		final AnvilEventInventory inventory;
		
		if (!BaseInventory.getInventoryIDs().contains("psyCraftCore.TextInput")) {
			inventory = new AnvilEventInventory("psyCraftCore.TextInput", new AnvilInventoryBuilder()
					.setFirstItemWithClickEvent(new ItemBuilder(Material.PAPER)
							.setDisplayName(" ")
							.build(), event -> event.getInventory().getRenameText())
					.setResultWithClickEvent(new ItemBuilder(Material.BOOK)
							.setDisplayName("§eName: §6§l")
							.addEnchantmentGlint()
							.build(), event -> {
						player.sendMessage("you entered: " + event.getInventory().getRenameText());
						player.closeInventory();
					})
					.setLevelRequirement(1));
		} else {
			inventory = AnvilEventInventory.getWithID("psyCraftCore.TextInput");
		}
		
		inventory.openInventory(player);
	}
	
	@Override
	public void onGameLobbyJoin(List<Player> players) {
		for (Player player : players) {
			player.sendMessage("Hello theres");
		}
	}
	
	@Override
	public void onGameLeave(Player player) {
		player.sendMessage("pepeSad");
	}
}
