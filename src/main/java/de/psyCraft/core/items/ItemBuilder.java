package de.psyCraft.core.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

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
		final List<String> lore = itemMeta.getLore();
		lore.add(loreLine);
		
		itemMeta.setLore(lore);
		
		return this;
	}
	
	public ItemBuilder addEnchantment(Enchantment enchantment) {
		this.addEnchantment(enchantment, 1);
		
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
	
	public ItemStack build() {
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}
}
