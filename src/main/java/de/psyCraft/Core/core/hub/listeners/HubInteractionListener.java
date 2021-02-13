package de.psyCraft.Core.core.hub.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class HubInteractionListener implements Listener {
	
	@EventHandler
	public void onPlayerInteractionEvent(PlayerInteractEvent event) {
		final Player player = (Player) event.getPlayer();
		
		event.setCancelled(player.getGameMode() != GameMode.CREATIVE);
	}
	
	@EventHandler
	public void onPlayerDamage(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerHitEntity(EntityDamageByEntityEvent event) {
		if (!(event.getDamager() instanceof Player || event.getEntity() instanceof Player)) {
			return;
		}
		
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onPlayerHitEntity(EntityPickupItemEvent event) {
		if (!(event.getEntity() instanceof Player)) {
			return;
		}
		final Player player = (Player) event.getEntity();
		
		event.setCancelled(player.getGameMode() != GameMode.CREATIVE);
	}
	
	@EventHandler
	public void onPlayerHitEntity(PlayerDropItemEvent event) {
		final Player player = event.getPlayer();
		
		event.setCancelled(player.getGameMode() != GameMode.CREATIVE);
	}
}
