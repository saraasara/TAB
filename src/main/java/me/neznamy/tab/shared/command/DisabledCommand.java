package me.neznamy.tab.shared.command;

import java.util.ArrayList;
import java.util.List;

import me.neznamy.tab.shared.TAB;

/**
 * Command processor when TAB is disabled due to broken configuration file
 */
public class DisabledCommand {

	/**
	 * Performs command and return messages to be sent back
	 * @param args - command arguments
	 * @param hasReloadPermission - if player has reload permission or not
	 * @param hasAdminPermission - if player has admin permission or not
	 * @return list of messages to send back
	 */
	public List<String> execute(String[] args, boolean hasReloadPermission, boolean hasAdminPermission) {
		List<String> messages = new ArrayList<String>();
		if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
			if (hasReloadPermission) {
				TAB.getInstance().load();
				if (TAB.getInstance().isDisabled()) {
					messages.add(TAB.getInstance().getConfiguration().reloadFailed.replace("%file%", TAB.getInstance().brokenFile));
				} else {
					messages.add(TAB.getInstance().getConfiguration().translation.getString("reloaded"));
				}
			} else {
				messages.add(TAB.getInstance().getConfiguration().translation.getString("no_permission"));
			}
		} else {
			if (hasAdminPermission) {
				String command = TAB.getInstance().getPlatform().getSeparatorType().equals("world") ? "/tab" : "/btab";
				messages.add("&m                                                                                ");
				messages.add(" &cPlugin is disabled due to a broken configuration file (" + TAB.getInstance().brokenFile + "). Check console for more details.");
				messages.add(" &8>> &3&l" + command + " reload");
				messages.add("      - &7Reloads plugin and config");
				messages.add("&m                                                                                ");
			}
		}
		return messages;
	}
}