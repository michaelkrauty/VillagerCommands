package me.michaelkrauty.VillagerCommands;

import org.bukkit.entity.Villager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created on 7/20/2014.
 *
 * @author michaelkrauty
 */
public class Main extends JavaPlugin {

	public static HashMap<Villager, ArrayList<String>> villagers = new HashMap<Villager, ArrayList<String>>();

	public static HashMap<UUID, Villager> editing = new HashMap<UUID, Villager>();

	public static ArrayList<UUID> editQueue = new ArrayList<UUID>();

	public void onEnable() {
		getServer().getPluginCommand("villagercommands").setExecutor(new Command(this));
		getServer().getPluginManager().registerEvents(new Listener(this), this);
	}
}
