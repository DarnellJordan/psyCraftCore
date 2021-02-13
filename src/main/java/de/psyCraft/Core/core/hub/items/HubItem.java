package de.psyCraft.Core.core.hub.items;

import de.psyCraft.Core.core.hub.listeners.HubClickListener;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class HubItem {
	
	HubItem() {
		HubClickListener.addClickEventToSlot(getHotbarSlot(), (player) -> {
			onInteract(player);
		});
	}
	
	public abstract int getHotbarSlot();
	
	public abstract ItemStack getItem();
	
	abstract void onInteract(Player player);
}
