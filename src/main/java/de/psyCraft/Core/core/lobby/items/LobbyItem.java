package de.psyCraft.Core.core.lobby.items;

import de.psyCraft.Core.core.lobby.listeners.LobbyClickListener;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class LobbyItem {
	
	LobbyItem() {
		LobbyClickListener.addClickEventToSlot(getHotbarSlot(), (player) -> {
			onInteract(player);
		});
	}
	
	public abstract int getHotbarSlot();
	
	public abstract ItemStack getItem();
	
	abstract void onInteract(Player player);
}
