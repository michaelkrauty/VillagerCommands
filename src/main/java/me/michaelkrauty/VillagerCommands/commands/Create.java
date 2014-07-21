package me.michaelkrauty.VillagerCommands.commands;

import me.michaelkrauty.VillagerCommands.Main;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created on 7/20/2014.
 *
 * @author michaelkrauty
 */
public class Create {

	public Create(Main main, CommandSender sender, String[] args) {
		Player player = (Player) sender;
		World world = player.getWorld();
		Villager villager = (Villager) world.spawnEntity(player.getLocation(), EntityType.VILLAGER);
		villager.setRemoveWhenFarAway(false);
		main.villagers.put(villager, new ArrayList<String>());
	}
}
