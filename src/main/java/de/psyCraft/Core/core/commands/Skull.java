package de.psyCraft.Core.core.commands;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.libs.org.apache.commons.codec.binary.Base64;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Field;
import java.util.UUID;

public class Skull implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
		((Player) commandSender).getInventory().addItem(getSkull(strings[0]));
		
		return true;
	}
	
	private ItemStack getSkull(String url) {
		final ItemStack head = new ItemStack(Material.PLAYER_HEAD);
		
		if (url == null || url.isEmpty()) {
			return null;
		}
		
		final ItemMeta headMeta = head.getItemMeta();
		final GameProfile profile = new GameProfile(UUID.randomUUID(), null);
		
		headMeta.setDisplayName("head");
		
		byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
		
		profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
		
		Field profileField = null;
		
		try {
			profileField = headMeta.getClass().getDeclaredField("profile");
			
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		
		profileField.setAccessible(true);
		
		try {
			profileField.set(headMeta, profile);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		head.setItemMeta(headMeta);
		return head;
	}
}
