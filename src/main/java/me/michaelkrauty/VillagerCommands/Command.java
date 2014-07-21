package me.michaelkrauty.VillagerCommands;

import me.michaelkrauty.VillagerCommands.commands.Create;
import me.michaelkrauty.VillagerCommands.commands.Edit;
import me.michaelkrauty.VillagerCommands.commands.Help;
import me.michaelkrauty.VillagerCommands.commands.Remove;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created on 7/20/2014.
 *
 * @author michaelkrauty
 */
public class Command implements CommandExecutor {

	private static Main main;

	public Command(Main main) {
		this.main = main;
	}

	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("You're a console, you can't spawn villagers!");
			return true;
		}
		if (args.length == 0) {
			new Help(sender);
			return true;
		}
		if (args[0].equalsIgnoreCase("create")) {
			new Create(main, sender, args);
			return true;
		}
		if (args[0].equalsIgnoreCase("remove")) {
			new Remove();
			return true;
		}
		if (args[0].equalsIgnoreCase("edit")) {
			new Edit(main, sender, args);
			return true;
		}
		return false;
	}
}
