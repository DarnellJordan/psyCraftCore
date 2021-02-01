package de.psyCraft.Core.util.inventory;

import de.psyCraft.Core.psyCraftCore;
import de.psyCraft.Core.util.common.Pair;
import de.psyCraft.Core.util.gui.AnvilInventoryBuilder;
import de.psyCraft.Core.util.item.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class AnvilEventInventory extends BaseInventory {
	
	private final Map<Player, InventoryView> inventoryViews = new HashMap<>();
	private final Map<Integer, Consumer<InventoryClickEvent>> clickEvents = new HashMap<>();
	private final Map<Integer, Consumer<PrepareAnvilEvent>> prepareAnvilEvents = new HashMap<>();
	
	private final Inventory anvilInventory;
	
	private final ItemStack resultItemTemplate;
	private final int requiredLevels;
	
	public AnvilEventInventory(String inventoryID) {
		super(inventoryID, Bukkit.createInventory(null, InventoryType.ANVIL));
		
		anvilInventory = null;
		resultItemTemplate = null;
		
		ClickListener.eventInventories.add(this);
		
		requiredLevels = 0;
	}
	
	public AnvilEventInventory(String inventoryID, Inventory anvilInventory) {
		super(inventoryID, Bukkit.createInventory(null, InventoryType.ANVIL));
		
		this.anvilInventory = anvilInventory;
		resultItemTemplate = anvilInventory.getItem(2);
		
		ClickListener.eventInventories.add(this);
		
		requiredLevels = 0;
	}
	
	public AnvilEventInventory(String inventoryID, AnvilInventoryBuilder anvilInventoryBuilder) {
		super(inventoryID, Bukkit.createInventory(null, InventoryType.ANVIL));
		
		addClickEvent(0, anvilInventoryBuilder.getClickEvents().get(0));
		addClickEvent(1, anvilInventoryBuilder.getClickEvents().get(1));
		addClickEvent(2, anvilInventoryBuilder.getClickEvents().get(2));
		
		anvilInventory = anvilInventoryBuilder.build();
		
		resultItemTemplate = anvilInventory.getItem(2);
		requiredLevels = anvilInventoryBuilder.getRequiredLevels();
		
		ClickListener.eventInventories.add(this);
	}
	
	public static AnvilEventInventory getWithID(String id) {
		return ClickListener.eventInventories.stream().filter(eventInventory -> eventInventory.getInventoryID().equalsIgnoreCase(id)).findFirst().get();
	}
	
	public void addPrepareAnvilEvent(final int slot, final Consumer<PrepareAnvilEvent> prepareAnvilEvent) {
		prepareAnvilEvents.put(slot, prepareAnvilEvent);
	}
	
	private void executeClickEvent(Pair<InventoryClickEvent, Integer> clickEvent) {
		final InventoryClickEvent event = clickEvent.first;
		final int slot = clickEvent.second;
		
		if (clickEvents.containsKey(slot)) {
			Bukkit.getScheduler().runTask(psyCraftCore.INSTANCE, () -> {
				clickEvents.get(slot).accept(event);
			});
		}
	}
	
	private void executePrepareAnvilEvent(Pair<PrepareAnvilEvent, Integer> prepareAnvilEvent) {
		final PrepareAnvilEvent event = prepareAnvilEvent.first;
		final int slot = prepareAnvilEvent.second;
		
		if (clickEvents.containsKey(slot)) {
			Bukkit.getScheduler().runTask(psyCraftCore.INSTANCE, () -> {
				prepareAnvilEvents.get(slot).accept(event);
			});
		}
	}
	
	public void addClickEvent(final int slot, final Consumer<InventoryClickEvent> clickEvent) {
		clickEvents.put(slot, clickEvent);
	}
	
	@Override
	public void openInventory(Player player) {
		InventoryView view = player.openAnvil(player.getLocation(), true);
		
		inventoryViews.put(player, view);
		openInventories.put(player, getInventoryID());
		
		if (anvilInventory != null) {
			view.getTopInventory().setItem(0, anvilInventory.getItem(0));
			view.getTopInventory().setItem(1, anvilInventory.getItem(1));
			view.getTopInventory().setItem(2, anvilInventory.getItem(2));
		}
	}
	
	@Override
	boolean cancelClickEvent(InventoryClickEvent event) {
		return event.getRawSlot() != 2;
	}
	
	public InventoryView getInventoryView(Player player) {
		return inventoryViews.get(player);
	}
	
	public static class ClickListener implements Listener {
		private static final List<AnvilEventInventory> eventInventories = new ArrayList<>();
		
		private int clickedSlot = 0;
		
		@EventHandler
		public void onPlayerPrepareAnvil(PrepareAnvilEvent event) {
			final Player player = (Player) event.getView().getPlayer();
			
			if (!getOpenInventories().containsKey(player) ||
					getOpenInventories().get(player) == null) {
				return;
			}
			
			for (AnvilEventInventory eventInventory : eventInventories) {
				if (eventInventory.getInventoryID().equalsIgnoreCase(getOpenInventories().get(player))) {
					if (eventInventory.resultItemTemplate != null && event.getInventory().getRenameText() != null) {
						event.setResult(new ItemBuilder(eventInventory.resultItemTemplate)
								.setDisplayName(eventInventory.resultItemTemplate.getItemMeta().getDisplayName() + event.getInventory().getRenameText().trim())
								.build());
					}
					
					event.getInventory().setRepairCost(eventInventory.requiredLevels);
					
					if (clickedSlot >= 0) {
						eventInventory.executePrepareAnvilEvent(Pair.of(event, clickedSlot));
					}
				}
			}
			
			clickedSlot = -1;
		}
		
		@EventHandler
		public void onPlayerClickInventory(InventoryClickEvent event) {
			clickedSlot = event.getRawSlot();
			
			for (AnvilEventInventory eventInventory : eventInventories) {
				eventInventory.executeClickEvent(Pair.of(event, clickedSlot));
			}
		}
	}
}
