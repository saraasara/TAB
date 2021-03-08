package me.neznamy.tab.shared.features.scoreboard.lines;

import me.neznamy.tab.api.TabPlayer;
import me.neznamy.tab.shared.features.scoreboard.Scoreboard;

/**
 * A line with static text (no placeholders) with numbers 1-15
 * Limitations:
 *   1.5.x - 1.7.x: 48 characters
 *   1.8.x - 1.12.x: 72 characters
 */
public class NumberedStaticLine extends StaticLine {

	/**
	 * Constructs new instance with given parameters
	 * @param parent - scoreboard this line belongs to
	 * @param lineNumber - ID of this line
	 * @param text - text to display
	 */
	public NumberedStaticLine(Scoreboard parent, int lineNumber, String text) {
		super(parent, lineNumber, text, "");
	}

	@Override
	public void register(TabPlayer p) {
		p.setProperty(teamName, text);
		if (p.getVersion().getMinorVersion() >= 13) {
			addLine(p, teamName, playerName, text, "", parent.lines.size() + 1 - lineNumber);
		} else if (p.getVersion().getMinorVersion() >= 8) {
			addLine(p, teamName, name, prefix, suffix, parent.lines.size() + 1 - lineNumber);
		} else {
			addLine(p, teamName, name1_7, prefix1_7, suffix1_7, parent.lines.size() + 1 - lineNumber);
		}
	}

	@Override
	public void unregister(TabPlayer p) {
		if (p.getProperty(teamName).get().length() > 0) {
			if (p.getVersion().getMinorVersion() >= 8) {
				removeLine(p, playerName, teamName);
			} else if (p.getVersion().getMinorVersion() >= 8) {
				removeLine(p, name, teamName);
			} else {
				removeLine(p, name1_7, teamName);
			}
		}
	}
}