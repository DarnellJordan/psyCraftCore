package de.psyCraft.core.lobby.listeners.items;

import de.psyCraft.api.builders.GUIBilder;
import de.psyCraft.api.builders.ItemBuilder;
import de.psyCraft.api.game.GameMode;
import de.psyCraft.api.plugin.registry.GameRegistry;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NavigatorItem extends LobbyItem {
	
	public NavigatorItem() {
		super();
	}
	
	@Override
	public int getHotbarSlot() {
		return 0;
	}
	
	@Override
	public ItemStack getItem() {
		return new ItemBuilder(Material.COMPASS)
				.setDisplayName("§c§lNavigation §8|§7§o Rechtsklick")
				.build();
	}
	
	@Override
	void onInteract(Player player) {
		final List<Integer> slotPositions = Arrays.asList(3, 5, 20, 22, 24, 39, 41);
		
		final GUIBilder builder = new GUIBilder(5, "§c§lNavigation §0§l|§8 Wähle einen Modus");
		final List<Integer> slots = new ArrayList<>(slotPositions);
		
		final ItemStack grayStainedGlassPane = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE)
				.setDisplayName(" ")
				.build();
		
		for (GameMode game : GameRegistry.getRegisteredGames()) {
			final Material material = game.getIcon();
			final ItemBuilder item = new ItemBuilder(material)
//						.setDisplayName(game.getName())
					.setLore(Arrays.asList(game.getDescription().split("\\n")));
			
			for (int i = 0 ; i < 10 ; i++) {
				if (!slots.isEmpty()) {
					final int slot = slots.remove(0);
					
					builder.addItemWIthClickEvent(slot, item.setDisplayName("" + i).build(), (p) -> {
						game.onGameJoin(p);
					});
				} else {
					builder.fillItems(0, 36, grayStainedGlassPane);
					builder.fillItems(8, 44, grayStainedGlassPane);
				}
			}
		}
		
		player.openInventory(builder.build());
	}
}
