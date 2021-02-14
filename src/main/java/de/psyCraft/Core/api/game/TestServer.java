package de.psyCraft.Core.api.game;

import de.psyCraft.Core.core.server.AccessLevel;
import de.psyCraft.Core.core.server.Server;
import de.psyCraft.Core.core.server.legacy.events.ServerWorldCreationEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * @author psyGamer
 */
public class TestServer extends Server {
	
	public TestServer(GameMode gameMode, int serverID) {
		super(gameMode, serverID);
	}
	
	@Override
	public void onServerWorldCreation(ServerWorldCreationEvent event) {
		event.setSeed(69420);
	}
	
	@Override
	public void onServerInitialize() {
		System.out.println("enable");
		System.out.println(worlds);
		System.out.println(worlds.get("main"));
		System.out.println(worlds.get("main").getCBWorld());
		System.out.println(worlds.get("main").getCBWorld().getHighestBlockAt(0, 0));
		
		worlds.get("main").getCBWorld().getHighestBlockAt(0, 0).setType(Material.GOLD_BLOCK);
	}
	
	@Override
	public void onServerEnable() {
		
		System.out.println(worlds);
		
		worlds
				.get("main")
				.getCBWorld()
				.getHighestBlockAt(serverID, 0)
				.setType(Material.IRON_BLOCK);
	}
	
	@Override
	public void onServerDisable() {
		worlds.get("main").getCBWorld().getHighestBlockAt(serverID, 0).setType(Material.LAPIS_BLOCK);
	}
	
	@Override
	public void onServerJoin(Player player) {
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
		System.out.println(this);
		System.out.println(worlds);
		player.teleport(worlds.get("main").getSpawnLocation());
	}
	
	@Override
	public void onServerJoinFromLobby(List<Player> players) {
		for (Player player : players) {
			player.sendMessage("Hello theres");
		}
	}
	
	@Override
	public void onServerLeave(Player player) {
		player.sendMessage("pepeSad");
	}
	
	@Override
	public AccessLevel getAccessLevel(Player player) {
		return AccessLevel.LOBBY;
	}
}
