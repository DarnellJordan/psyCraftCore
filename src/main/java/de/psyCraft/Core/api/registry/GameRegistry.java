package de.psyCraft.Core.api.registry;

import de.psyCraft.Core.api.game.NavigatorItem;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class GameRegistry {
	
	private static final List<NavigatorItem> NAVIGATOR_ITEMS = new ArrayList<>();
	
	public static boolean registerGame(NavigatorItem navigatorItem) {
//		if (!ServerWorldUtil.isWorldNameValid(gameMode.getName())) {
//			PsyCraftCore.logger().warning("§cThe name: " + gameMode.getName() + " does not match the criteria to be registered ([a-z, A-Z, 0-9, _], min length: 1 char, max length: 64 chars");
//
//			return false;
//		}
		
		for (NavigatorItem game : NAVIGATOR_ITEMS) {
//			if (navigatorItem.getName().equalsIgnoreCase(game.getName())) {
//				PsyCraftCore.logger().warning("§cGame Mode: " + navigatorItem.getName() + " already exists");
//
//				return false;
//			}
		}
		
		NAVIGATOR_ITEMS.add(navigatorItem);
//		PsyCraftCore.logger().info("§aSuccessfully loaded Game Mode: " + navigatorItem.getName());
		
		return true;
	}
	
	public static List<NavigatorItem> getRegisteredGameModes() {
		return new ArrayList<>(NAVIGATOR_ITEMS);
	}
	
	public static NavigatorItem getGameModeByName(String name) {
		for (NavigatorItem navigatorItem : NAVIGATOR_ITEMS) {
//			if (navigatorItem.getName().equalsIgnoreCase(name)) {
//				return navigatorItem;
//			}
		}
		
		return null;
	}
}
