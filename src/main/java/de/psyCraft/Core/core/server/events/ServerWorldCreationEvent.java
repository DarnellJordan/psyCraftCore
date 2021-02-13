package de.psyCraft.Core.core.server.events;

import de.psyCraft.Core.core.server.Server;
import org.bukkit.World.Environment;
import org.bukkit.WorldType;
import org.bukkit.event.Cancellable;

import java.util.Random;
import java.util.function.Consumer;

public class ServerWorldCreationEvent implements Cancellable {
	
	private final Server server;
	private final String worldName;
	
	private boolean cancelled = false;
	
	private Consumer<Server> postCreationEvent = s -> {
	};
	
	private Environment environment = Environment.NORMAL;
	private String seed = String.valueOf(new Random().nextLong());
	private String generator = "";
	private WorldType type = WorldType.NORMAL;
	
	private boolean generateStructures = true;
	
	public ServerWorldCreationEvent(Server server, String worldName) {
		this.server = server;
		this.worldName = worldName;
	}
	
	public Server getServer() {
		return server;
	}
	
	public String getWorldName() {
		return worldName;
	}
	
	public Environment getEnvironment() {
		return environment;
	}
	
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
	
	public String getSeed() {
		return seed;
	}
	
	public void setSeed(String seed) {
		this.seed = seed;
	}
	
	public void setSeed(long seed) {
		this.seed = String.valueOf(seed);
	}
	
	public boolean isGeneratingStructures() {
		return generateStructures;
	}
	
	public void setGeneratingStructures(boolean generateStructures) {
		this.generateStructures = generateStructures;
	}
	
	public WorldType getType() {
		return type;
	}
	
	public void setType(WorldType type) {
		this.type = type;
	}
	
	public String getGenerator() {
		return generator;
	}
	
	public void setGenerator(String generator) {
		this.generator = generator;
	}
	
	@Override
	public boolean isCancelled() {
		return cancelled;
	}
	
	@Override
	public void setCancelled(boolean cancel) {
		cancelled = cancel;
	}
}
