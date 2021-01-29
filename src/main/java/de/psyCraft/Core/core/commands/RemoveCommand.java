package de.psyCraft.Core.core.commands;

import de.psyCraft.Core.psyCraftCore;
import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R3.CraftServer;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;

public class RemoveCommand implements CommandExecutor {
	
	private final psyCraftCore plugin;
	
	public RemoveCommand(psyCraftCore plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
		MinecraftServer mcServer = ((CraftServer) Bukkit.getServer()).getServer();
		WorldServer nmsWorld = ((CraftWorld) Bukkit.getWorlds().get(0)).getHandle();
		EntityPlayer player = ((CraftPlayer) Bukkit.getPlayer(strings[0])).getHandle();
		
		PlayerConnection connection = ((CraftPlayer) commandSender).getHandle().playerConnection;
		
		connection.sendPacket(new PacketPlayOutEntityDestroy(player.getId()));
		connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, player));
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
			connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, player));
			connection.sendPacket(new PacketPlayOutNamedEntitySpawn(player));
		}, 10 * 20);
		
		return true;
	}
}
