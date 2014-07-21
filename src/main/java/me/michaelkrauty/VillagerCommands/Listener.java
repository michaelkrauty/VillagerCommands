package me.michaelkrauty.VillagerCommands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.ArrayList;

/**
 * Created on 7/20/2014.
 *
 * @author michaelkrauty
 */
public class Listener implements org.bukkit.event.Listener {

	private static Main main;

	public Listener(Main main) {
		this.main = main;
	}

	@EventHandler
	public void onPlayerInteracEntity(PlayerInteractEntityEvent event) {
		if (event.getRightClicked() instanceof Villager) {
			Villager villager = (Villager) event.getRightClicked();
			if (main.villagers.get(villager) != null) {
				event.setCancelled(true);
				if (!main.editQueue.contains(event.getPlayer().getUniqueId())) {
					for (int i = 0; i < main.villagers.get(villager).size(); i++) {
						main.getServer().dispatchCommand(event.getPlayer(), main.villagers.get(villager).get(i));
					}
				} else {
					Player player = event.getPlayer();
					player.sendMessage(ChatColor.GRAY + "Current Commands:");
					for (int i = 0; i < main.villagers.get(villager).size(); i++) {
						player.sendMessage(ChatColor.GRAY + main.villagers.get(villager).get(i));
					}
					player.sendMessage(ChatColor.GREEN + "say \"exit\" to exit the editor.");
					main.editQueue.remove(player.getUniqueId());
					main.editing.put(player.getUniqueId(), villager);
				}
			}
		}
	}

	@EventHandler
	public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
		if (main.editing.get(event.getPlayer().getUniqueId()) != null) {
			event.setCancelled(true);
			Villager villager = main.editing.get(event.getPlayer().getUniqueId());
			ArrayList<String> villagerCommands = main.villagers.get(villager);
			Player player = event.getPlayer();
			String message = event.getMessage();
			String[] args = message.split(" ");
			if (args.length == 0 || message.equalsIgnoreCase("help")) {
				player.sendMessage("this is a placeholder, a help menu will be here soonish");
				return;
			}
			if (args[0].equalsIgnoreCase("exit")) {
				main.editing.remove(player.getUniqueId());
				player.sendMessage(ChatColor.GREEN + "exited the editor.");
				return;
			}
			if (args[0].equalsIgnoreCase("line")) {
				if (args[1].equalsIgnoreCase("insert")) {
					try {
						Integer.parseInt(args[2]);
					} catch (Exception e) {
						player.sendMessage(ChatColor.GREEN + "Usage: line <insert/edit/remove> <number> <command>");
						player.sendMessage(ChatColor.GREEN + "Example:");
						player.sendMessage(ChatColor.GREEN + "line insert 1 list");
						return;
					}
					String cmd = "";
					for (int i = 3; i < args.length; i++) {
						cmd = cmd + args[i] + " ";
					}
					int line = Integer.parseInt(args[2]);
					ArrayList<String> newone = new ArrayList<String>();
					for (int i = 0; i < villagerCommands.size() + 1; i++) {
						if (i == line - 1) {
							newone.add(cmd);
						}
						try {
							newone.add(villagerCommands.get(i));
						} catch (Exception ignored) {
						}
					}
					for (int i = 0; i < newone.size(); i++) {
						player.sendMessage(ChatColor.GRAY + "" + (i + 1) + ": " + newone.get(i));
					}
					main.villagers.remove(villager);
					main.villagers.put(villager, newone);
					return;
				}
				if (args[1].equalsIgnoreCase("edit")) {
					try {
						Integer.parseInt(args[2]);
					} catch (Exception e) {
						player.sendMessage(ChatColor.GREEN + "Usage: line <insert/edit/remove> <number> <command>");
						player.sendMessage(ChatColor.GREEN + "Example:");
						player.sendMessage(ChatColor.GREEN + "line insert 1 list");
						return;
					}
					String cmd = "";
					for (int i = 3; i < args.length; i++) {
						cmd = cmd + args[i] + " ";
					}
					int line = Integer.parseInt(args[2]);
					villagerCommands.set(Integer.parseInt(args[2]) - 1, cmd);
					for (int i = 0; i < villagerCommands.size(); i++) {
						player.sendMessage(ChatColor.GRAY + "" + (i + 1) + ": " + villagerCommands.get(i));
					}
					main.villagers.remove(villager);
					main.villagers.put(villager, villagerCommands);
					return;
				}
				if (args[1].equalsIgnoreCase("remove")) {
					try {
						Integer.parseInt(args[2]);
					} catch (Exception e) {
						player.sendMessage(ChatColor.GREEN + "Usage: line <insert/edit/remove> <number> <command>");
						player.sendMessage(ChatColor.GREEN + "Example:");
						player.sendMessage(ChatColor.GREEN + "line insert 1 list");
						return;
					}
					int line = Integer.parseInt(args[2]);
					villagerCommands.remove(Integer.parseInt(args[2]) - 1);
					for (int i = 0; i < villagerCommands.size(); i++) {
						player.sendMessage(ChatColor.GRAY + "" + (i + 1) + ": " + villagerCommands.get(i));
					}
					main.villagers.remove(villager);
					main.villagers.put(villager, villagerCommands);
					return;
				}
				player.sendMessage(ChatColor.GREEN + "Usage: line <insert/edit/remove> <number> <command>");
				return;
			}
			player.sendMessage(ChatColor.GREEN + "say \"help\" for help.");
			return;
		}
	}
}