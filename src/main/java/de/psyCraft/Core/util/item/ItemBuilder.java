package de.psyCraft.Core.util.item;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.craftbukkit.libs.org.apache.commons.codec.binary.Base64;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ItemBuilder {
	
	private ItemStack itemStack;
	private ItemMeta itemMeta;
	
	public ItemBuilder(Material material) {
		itemStack = new ItemStack(material);
		itemMeta = itemStack.getItemMeta();
	}
	
	public ItemBuilder setDisplayName(String displayName) {
		itemMeta.setDisplayName(displayName);
		
		return this;
	}
	
	public ItemBuilder setLore(List<String> lore) {
		itemMeta.setLore(lore);
		
		return this;
	}
	
	public ItemBuilder addLoreLine(String loreLine) {
		final List<String> lore = itemMeta.getLore() == null ? new ArrayList<>() : itemMeta.getLore();
		lore.add(loreLine);
		
		itemMeta.setLore(lore);
		
		return this;
	}
	
	public ItemBuilder addEnchantment(Enchantment enchantment) {
		addEnchantment(enchantment, 1);
		
		return this;
	}
	
	public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
		itemMeta.addEnchant(enchantment, level, true);
		
		return this;
	}
	
	public ItemBuilder addEnchantmentGlint() {
		itemMeta.addEnchant(Enchantment.MENDING, 1, true);
		itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		return this;
	}
	
	public ItemBuilder setHeadURL(String url) {
		if (itemStack.getType() != Material.PLAYER_HEAD || url == null || url.isEmpty()) {
			return this;
		}
		
		final GameProfile profile = new GameProfile(UUID.randomUUID(), null);
		final byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
		
		profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
		
		Field profileField = null;
		
		try {
			profileField = itemMeta.getClass().getDeclaredField("profile");
			
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		
		profileField.setAccessible(true);
		
		try {
			profileField.set(itemMeta, profile);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		itemStack.setItemMeta(itemMeta);
		
		return this;
	}
	
	public ItemStack build() {
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}
}
