package de.psyCraft.core.commands;

import com.mojang.authlib.GameProfile;
import de.psyCraft.psyCraftCore;
import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R3.CraftServer;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class NpcCommand implements CommandExecutor {
	
	private final psyCraftCore plugin;
	
	public NpcCommand(psyCraftCore plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
		MinecraftServer mcServer = ((CraftServer) Bukkit.getServer()).getServer();
		WorldServer nmsWorld = ((CraftWorld) Bukkit.getWorlds().get(0)).getHandle();
		EntityPlayer npc = new EntityPlayer(mcServer, nmsWorld, new GameProfile(UUID.randomUUID(), "uludak"), new PlayerInteractManager(nmsWorld));
		
		Location location = ((Player) commandSender).getLocation();
		
		npc.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
		
		PlayerConnection connection = ((CraftPlayer) commandSender).getHandle().playerConnection;
		
		connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
		connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
			connection.sendPacket(new PacketPlayOutEntityDestroy(npc.getId()));
			connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, npc));
		}, 10 * 20);
		
		return true;
	}
}
