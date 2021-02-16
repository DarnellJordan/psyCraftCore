package de.psyCraft.Core.util.lobby;

import de.psyCraft.Core.PsyCraftCore;
import de.psyCraft.Core.core.server.legacy.LobbyServer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * A util class for the {@link LobbyServer LobbyServers}
 *
 * @author psyGamer
 * @see LobbyServer LobbyServer
 * @since 1.0
 */
public class LobbyUtil {
	
	/**
	 * Shows a {@link Player} to every online player.
	 *
	 * @param player The Player that should be hidden.
	 * @author psyGamer
	 * @see Player#showPlayer
	 * @since 1.0
	 */
	public static void showPlayerToEveryone(Player player) {
		if (player == null) {
			return;
		}
		
		Bukkit.getOnlinePlayers().stream()
				.forEach(onlinePlayer -> {
					System.out.println("showing " + player.getName() + " to " + onlinePlayer.getName());
					onlinePlayer.showPlayer(PsyCraftCore.getInstance(), player);
				});
	}
	
	/**
	 * Shows a {@link Player} to only those specified.
	 *
	 * @param player The Player that should be shown.
	 * @param only   A {@link List} of {@link Player Players} to which the {@link Player} will be shown
	 * @author psyGamer
	 * @see Player#showPlayer
	 * @since 1.0
	 */
	public static void showPlayerOnlyTo(Player player, List<Player> only) {
		if (only == null) {
			showPlayerToEveryone(player);
		}
		
		System.out.println("SHOWING:" + player.getName());
		System.out.println("ONLY:" + only);
		
		only.forEach(onlinePlayer -> {
			System.out.println("showing " + player.getName() + " to " + onlinePlayer.getName());
			onlinePlayer.showPlayer(PsyCraftCore.getInstance(), player);
		});
	}
	
	/**
	 * Shows a {@link Player} to players those specified.
	 *
	 * @param player  The Player that should be shown.
	 * @param players A {@link List} of {@link Player Players} to which the {@link Player} will be shown
	 * @author psyGamer
	 * @see Player#showPlayer
	 * @since 1.0
	 */
	public static void showPlayersToPlayer(Player player, List<Player> players) {
		if (players == null) {
			showPlayerToEveryone(player);
		}
		
		System.out.println("SHOWING:" + player.getName());
		System.out.println("ONLY:" + players);
		
		players.forEach(onlinePlayer -> {
			System.out.println("showing " + onlinePlayer.getName() + " to " + player.getName());
			player.showPlayer(PsyCraftCore.getInstance(), onlinePlayer);
		});
	}
	
	
	/**
	 * Shows a {@link Player} to everyone, except those specified.
	 *
	 * @param player The Player that should be shown.
	 * @param except A {@link List} of {@link Player Players} to which the {@link Player} will not be shown
	 * @author psyGamer
	 * @see Player#showPlayer
	 * @since 1.0
	 */
	public static void showPlayerToEveryoneExcept(Player player, List<Player> except) {
		if (except == null) {
			showPlayerToEveryone(player);
		}
		
		Bukkit.getOnlinePlayers().stream()
				.filter(onlinePlayer -> !except.contains(onlinePlayer))
				.forEach(onlinePlayer -> onlinePlayer.showPlayer(PsyCraftCore.getInstance(), player));
	}
	
	/**
	 * Hides a {@link Player} from every online player.
	 *
	 * @param player The Player that should be hidden.
	 * @author psyGamer
	 * @see Player#hidePlayer
	 * @since 1.0
	 */
	public static void hidePlayerFromEveryone(Player player) {
		if (player == null) {
			return;
		}
		
		Bukkit.getOnlinePlayers().stream()
				.forEach(onlinePlayer -> onlinePlayer.hidePlayer(PsyCraftCore.getInstance(), player));
	}
	
	
	/**
	 * Hides a {@link Player} from only those specified.
	 *
	 * @param player The Player that should be hidden.
	 * @param only   A {@link List} of {@link Player Players} from which the player should be hidden {@link Player}
	 * @author psyGamer
	 * Player#hidePlayer
	 * @since 1.0
	 */
	public static void hidePlayerOnlyFrom(Player player, List<Player> only) {
		if (only == null) {
			hidePlayerFromEveryone(player);
		}
		
		only.forEach(onlinePlayer -> onlinePlayer.hidePlayer(PsyCraftCore.getInstance(), player));
	}
	
	/**
	 * Hides a {@link Player} from every online player, except those specified.
	 *
	 * @param player The Player that should be hidden.
	 * @param except A {@link List} of {@link Player Players} which should still be able to see that {@link Player}
	 * @author psyGamer
	 * Player#hidePlayer
	 * @since 1.0
	 */
	public static void hidePlayerFromEveryoneExcept(Player player, List<Player> except) {
		if (except == null) {
			hidePlayerFromEveryone(player);
		}
		
		System.out.println("HIDING:" + player.getName());
		System.out.println("EXCEPT:" + except);
		
		Bukkit.getOnlinePlayers().stream()
				.filter(onlinePlayer -> !except.contains(onlinePlayer))
				.forEach(onlinePlayer -> {
					System.out.println("hiding " + player.getName() + " from " + onlinePlayer.getName());
					onlinePlayer.hidePlayer(PsyCraftCore.getInstance(), player);
				});
	}
	
	/**
	 * Hides a {@link Player} from every online player, except those specified.
	 *
	 * @param player The Player that should be hidden.
	 * @param except A {@link List} of {@link Player Players} which should still be able to see that {@link Player}
	 * @author psyGamer
	 * Player#hidePlayer
	 * @since 1.0
	 */
	public static void hideEveryoneFromPlayerExcept(Player player, List<Player> except) {
		if (except == null) {
			hidePlayerFromEveryone(player);
		}
		
		System.out.println("HIDING:" + except);
		System.out.println("FROM:" + player.getName());
		
		Bukkit.getOnlinePlayers().stream()
				.filter(onlinePlayer -> !except.contains(onlinePlayer))
				.forEach(onlinePlayer -> {
					System.out.println("hiding " + onlinePlayer.getName() + " from " + player.getName());
					player.hidePlayer(PsyCraftCore.getInstance(), onlinePlayer);
				});
	}
}
