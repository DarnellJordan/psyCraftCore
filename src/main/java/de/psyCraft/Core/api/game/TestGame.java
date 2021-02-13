package de.psyCraft.Core.api.game;

import de.psyCraft.Core.core.server.Server;
import de.psyCraft.Core.core.server.events.ServerWorldCreationEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
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
	
	@Override
	public AccessLevel getAccessLevel(Player player) {
		return null;
	}
	
	@Override
	public void onServerWorldCreation(ServerWorldCreationEvent event) {
		event.setSeed(69420);
	}
	
	@Override
	public void onServerInitialize(Server server) {
		System.out.println("enable");
		System.out.println(server.getWorlds());
		System.out.println(server.getWorlds().get("main"));
		System.out.println(server.getWorlds().get("main").getCBWorld());
		System.out.println(server.getWorlds().get("main").getCBWorld().getHighestBlockAt(0, 0));
		
		server.getWorlds().get("main").getCBWorld().getHighestBlockAt(0, 0).setType(Material.GOLD_BLOCK);
	}
	
	@Override
	public void onServerEnable(Server server) {
		
		System.out.println(server.getWorlds());
		
		server
				.getWorlds()
				.get("main")
				.getCBWorld()
				.getHighestBlockAt(server.getServerID(), 0)
				.setType(Material.IRON_BLOCK);
	}
	
	@Override
	public void onServerDisable(Server server) {
		server.getWorlds().get("main").getCBWorld().getHighestBlockAt(-server.getServerID(), 0).setType(Material.LAPIS_BLOCK);
	}
	
	@Override
	public void onGameJoin(Server server, Player player) {
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
		
		/*final AnvilEventInventory inventory;
		
		if (!BaseInventory.getInventoryIDs().contains("psyCraftCore.TextInput")) {
			inventory = new AnvilEventInventory("psyCraftCore.TextInput", new AnvilInventoryBuilder()
					.setItem(AnvilInventoryBuilder.FIRST_SLOT,
							new ItemBuilder(Material.PAPER).setDisplayName(" ").build())
					.setItem(AnvilInventoryBuilder.RESULT_SLOT,
							new ItemBuilder(Material.BOOK).setDisplayName("§eName: §6§l").addEnchantmentGlint().build())
					.setItemAnvilClickEvent(AnvilInventoryBuilder.RESULT_SLOT, event -> {
						Bukkit.broadcastMessage(String.valueOf(event.getInventoryClickEvent().getRawSlot()));
						Bukkit.broadcastMessage(event.getPrepareAnvilEvent().getInventory().getRenameText());
					})
//					.setResult(new ItemBuilder(Material.BOOK)
//							.setDisplayName("§eName: §6§l")
//							.addEnchantmentGlint()
//							.build())
//					.setResultClickEvent(event -> {
//						((Player) event.getWhoClicked()).setLevel(1);
//					})
//					.setResultPrepareEvent(event -> {
//						Bukkit.broadcastMessage(event.getInventory().getRenameText());
//					})
					.setLevelRequirement(1));
		} else {
			inventory = AnvilEventInventory.getWithID("psyCraftCore.TextInput");
		}
		
		inventory.openInventory(player);*/
		System.out.println(server);
		System.out.println(server.getWorlds());
		player.teleport(server.getWorlds().get("main").getSpawnLocation());
	}
	
	@Override
	public void onGameLobbyJoin(Server server, List<Player> players) {
		for (Player player : players) {
			player.sendMessage("Hello theres");
		}
	}
	
	@Override
	public void onGameLeave(Server server, Player player) {
		player.sendMessage("pepeSad");
	}
}
