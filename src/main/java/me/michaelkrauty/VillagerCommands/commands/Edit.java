package me.michaelkrauty.VillagerCommands.commands;

import me.michaelkrauty.VillagerCommands.Main;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created on 7/20/2014.
 *
 * @author michaelkrauty
 */
public class Edit {

	private static Main main;

	public Edit(Main main, CommandSender sender, String[] args) {
		Player player = (Player) sender;
		main.editQueue.add(player.getUniqueId());
	}
}
