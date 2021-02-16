package de.psyCraft.Core.util.chat;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageUtil {
	
	private static final Pattern hexCodePattern = Pattern.compile("&#[a-fA-F0-9]{6}");
	
	public static final String formatAlertMessage(final String prefix, final String message) {
		return formatMessage(prefix + "&8│ &7" + message);
	}
	
	public static final String formatMessage(final String prefix, final String message) {
		return formatMessage(prefix + message);
	}
	
	public static final String formatMessage(String message) {
		if (Bukkit.getVersion().contains("1.16")) {
			Matcher matcher = hexCodePattern.matcher(message);
			
			while (matcher.find()) {
				final String color = message.substring(matcher.start(), matcher.end());
				
				message = message.replace(color, ChatColor.of(color).toString());
				matcher = hexCodePattern.matcher(message);
			}
		}
		
		return ChatColor.translateAlternateColorCodes('&', message);
	}
}
