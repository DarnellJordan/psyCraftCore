package de.psyCraft.core.lobby.items;

import de.psyCraft.core.lobby.listeners.LobbyClickListener;
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
