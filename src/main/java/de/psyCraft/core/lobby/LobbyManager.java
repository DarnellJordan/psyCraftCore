package de.psyCraft.core.lobby;

import de.psyCraft.api.builders.GUIBilder;
import de.psyCraft.api.builders.ItemBuilder;
import de.psyCraft.api.game.GameMode;
import de.psyCraft.api.plugin.registry.GameRegistry;
import de.psyCraft.core.lobby.listeners.LobbyClickListener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class LobbyManager {
	
	public static final ItemStack PLACEHOLDER = new ItemBuilder(Material.GRASS_BLOCK)
			.setDisplayName("Placeholder")
			.build();
	
	public static final World WORLD = Bukkit.getWorld("world");
	
	public static final NavigatorGUI NAVIGATOR = new NavigatorGUI();
	
	public static class NavigatorGUI extends LobbyGUI {
		@Override
		public int getSlot() {
			return 0;
		}
		
		@Override
		public ItemStack getItem() {
			return new ItemBuilder(Material.COMPASS)
					.setDisplayName("§c§lNavigation §8|§7§o Rechtsklick")
					.build();
		}
		
		@Override
		public Inventory getGUI() {
			final GUIBilder builder = new GUIBilder(5, "§c§lNavigation §0§l|§8 Wähle einen Modus");
			
			for (GameMode game : GameRegistry.getRegisteredGames()) {
				game.getIcon();

//				builder.addItemWithCLickEvent
			}
			
			return builder.build();
		}
	}
	
	private static abstract class LobbyGUI {
		
		private LobbyGUI() {
			LobbyClickListener.addClickEventToSlot(getSlot(), (player) -> {
				player.sendMessage("Servus");
				player.openInventory(getGUI());
			});
		}
		
		abstract int getSlot();
		
		abstract ItemStack getItem();
		
		abstract Inventory getGUI();
	}
	
}
