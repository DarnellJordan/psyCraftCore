package de.psyCraft.Core.core.hub.items;

import de.psyCraft.Core.api.game.GameMode;
import de.psyCraft.Core.api.registry.GameRegistry;
import de.psyCraft.Core.core.server.legacy.Server;
import de.psyCraft.Core.core.server.legacy.ServerManager;
import de.psyCraft.Core.util.gui.ChestInventoryBuilder;
import de.psyCraft.Core.util.inventory.EventInventory;
import de.psyCraft.Core.util.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class NavigatorItem extends HubItem {
	
	private final List<EventInventory> pages = new ArrayList<>();
	
	public NavigatorItem() {
		super();
		
		generatePages();
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
		pages.get(0).openInventory(player);
	}
	
	private void generatePages() {
		final Map<Integer, List<Integer>> slotMap = new HashMap() {{
			put(1, Arrays.asList(22));
			put(2, Arrays.asList(21, 23));
			put(3, Arrays.asList(20, 22, 24));
			put(4, Arrays.asList(12, 14, 30, 32));
			put(5, Arrays.asList(12, 14, 29, 31, 33));
			put(6, Arrays.asList(11, 13, 15, 29, 31, 33));
			put(7, Arrays.asList(3, 5, 20, 22, 24, 39, 41));
		}};
		
		final ItemStack grayStainedGlassPane = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE)
				.setDisplayName(" ")
				.build();
		final ItemStack leftArrow = new ItemBuilder(Material.PLAYER_HEAD)
				.setDisplayName("§7« §oVorherige Seite")
				.setHeadURL("http://textures.minecraft.net/texture/37aee9a75bf0df7897183015cca0b2a7d755c63388ff01752d5f4419fc645")
				.build();
		final ItemStack rightArrow = new ItemBuilder(Material.PLAYER_HEAD)
				.setDisplayName("§7» §oNächste Seite")
				.setHeadURL("http://textures.minecraft.net/texture/682ad1b9cb4dd21259c0d75aa315ff389c3cef752be3949338164bac84a96e")
				.build();
		
		final List<GameMode> gameModes = new ArrayList<>(GameRegistry.getRegisteredGameModes());
		final int iterations = (int) Math.ceil((double) gameModes.size() / 7);
		
		for (int i = 0 ; i < iterations ; i++) {
			final int jIterations = Math.min(gameModes.size(), 7);
			final ChestInventoryBuilder page = new ChestInventoryBuilder(5, "§c§lNavigation §0§l|§8 Wähle einen Modus", "psyCraftCore:Lobby.Navigator." + i);
			
			for (int j = 0 ; j < jIterations ; j++) {
				final GameMode gameMode = gameModes.remove(0);
				final ItemBuilder gameItemBuilder = new ItemBuilder(gameMode.getItem())
						.setDisplayName("§r" + gameMode.getDisplayName());
				
				for (String line : gameMode.getDescription()) {
					gameItemBuilder.addLoreLine("§r" + line);
				}
				
				final ItemStack gameItem = gameItemBuilder.build();
				final int slot = slotMap.get(jIterations).get(j);
				
				page.addItemWIthClickEvent(slot, gameItem, (event) -> {
					final Player player = (Player) event.getWhoClicked();
//					final GameMode.AccessLevel accessLevel = gameMode.getAccessLevel(player);
					
					final Server server = ServerManager.getServerByID(gameMode);

//					switch (accessLevel) {
//						case FORBIDDEN:
//							player.sendMessage("Du hast keine Berechtigung dafür");
//							break;
//						case LOBBY:
//							LobbyServer lobbyServer = LobbyServerManager.getLobbyServer(server);
//							LobbyServerManager.joinLobbyServer(player, lobbyServer);
//							break;
//						case SERVER:
//							server.onPlayerJoin(player);
//							break;
//					}
				});
			}
			
			page.fillItems(1, 1, 1, 5, grayStainedGlassPane);
			page.fillItems(9, 1, 9, 5, grayStainedGlassPane);
			
			final int currentPageIteration = i;
			
			if (i > 0) {
				page.addItemWithClickEvent(1, 3, leftArrow, (event) -> {
					pages.get(currentPageIteration - 1).openInventory((Player) event.getWhoClicked());
				});
			}
			if (iterations > i + 1) {
				page.addItemWithClickEvent(9, 3, rightArrow, (event) -> {
					pages.get(currentPageIteration + 1).openInventory((Player) event.getWhoClicked());
				});
			}
			
			pages.add(page.build());
		}
	}
}
