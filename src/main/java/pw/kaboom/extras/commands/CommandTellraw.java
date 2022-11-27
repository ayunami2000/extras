package pw.kaboom.extras.commands;

import com.google.gson.JsonObject;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.chat.TextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;

public final class CommandTellraw implements CommandExecutor {
	private static final TextComponentSerializer TEXT_COMPONENT_SERIALIZER = new TextComponentSerializer();
	public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
		if (args.length == 0) {
			sender.sendMessage(ChatColor.RED + "Usage: /" + label + " <message ..>");
		} else {
			JsonObject object = new JsonObject();
			object.addProperty("text", ChatColor.translateAlternateColorCodes('&', String.join(" ", args)));
			BaseComponent msg = TEXT_COMPONENT_SERIALIZER.deserialize(object, null, null);
			for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
				onlinePlayer.sendMessage(msg);
			}
		}
		return true;
	}
}
