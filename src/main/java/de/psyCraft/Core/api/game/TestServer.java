package de.psyCraft.Core.api.game;

import de.psyCraft.Core.core.server.AccessLevel;
import de.psyCraft.Core.core.server.Server;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/**
 * @author psyGamer
 */
public class TestServer extends Server {
	
	public TestServer(int gameID, int serverID) {
		super("game_" + gameID, serverID);
	}
	
	@Override
	public Class<? extends NavigatorItem> getNavigatorItem(Player player) {
		return TestGame.class;
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
	protected void onServerDeletion() {
	
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
	public void onServerLeave(Player player) {
		player.sendMessage("pepeSad");
	}
	
	@Override
	public AccessLevel getAccessLevel(Player player) {
		return AccessLevel.LOBBY;
	}
}
