package me.michaelkrauty.VillagerCommands.objects;

import me.michaelkrauty.VillagerCommands.Main;
import org.bukkit.entity.Villager;

import java.util.ArrayList;

/**
 * Created on 7/20/2014.
 *
 * @author michaelkrauty
 */
public class CommandVillager {

	private static Main main;
	private static Villager villager;
	private static ArrayList<String> commands;

	public CommandVillager(Main main, Villager villager) {
		this.main = main;
		this.villager = villager;
	}
}
