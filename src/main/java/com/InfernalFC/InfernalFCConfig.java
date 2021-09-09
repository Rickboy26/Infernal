package com.InfernalFC;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

import java.awt.*;

@ConfigGroup(InfernalFCPlugin.CONFIG_GROUP)
public interface InfernalFCConfig extends Config {
	@ConfigSection(
			name = "Event Codeword",
			description = "Codeword info for clan event",
			position = 0
	)
	String eventPassSection = "Event Passphrase";


	@ConfigSection(
			name = "Configuration",
			description = "Menu config",
			position = 2
	)
	String menuSection = "Lookup menu";

	@ConfigSection(
			name = "Chambers CM",
			description = "Challenge mode Tiles",
			position = 3
	)
	String chambersSection = "Chambers CM";

	@ConfigItem(
			position = 1,
			keyName = "overlay",
			name = "Display the overlay",
			description = "Display the overlay on your game screen",
			section = eventPassSection
	)
	default boolean overlay() {
		return false;
	}

	@ConfigItem(
			position = 2,
			keyName = "dtm",
			name = "Include Date & Time",
			description = "Display the date and time",
			section = eventPassSection
	)
	default boolean dtm() {
		return true;
	}

	@ConfigItem(
			position = 3,
			keyName = "eventPass",
			name = "Event Codeword",
			description = "Creates an overlay with the event codeword",
			section = eventPassSection
	)
	default String eventPass() {
		return "";
	}


	@ConfigItem(
			position = 5,
			keyName = "passColor",
			name = "Overlay Color",
			description = "Configures the color of the passphrase",
			section = eventPassSection
	)
	default Color passColor() {
		return Color.GREEN;
	}


	@ConfigItem(
			position = 8,
			keyName = "col1color",
			name = "Column 1 Color",
			description = "Configures the color of the spreadsheet column displayed",
			section = menuSection
	)
	default Color col1color() {
		return Color.white;
	}

	@ConfigItem(
			position = 9,
			keyName = "col2color",
			name = "Column 2 Color",
			description = "Configures the color of the spreadsheet column displayed",
			section = menuSection
	)
	default Color col2color() {
		return Color.GRAY;
	}

	@ConfigItem(
			position = 10,
			keyName = "menuOption",
			name = "Enable Clan Check",
			description = "Right click someone to check their clan stats",
			section = menuSection
	)
	default boolean menuOption() {
		return true;
	}

	@ConfigItem(
			position = 10,
			keyName = "slashSwaperOption",
			name = "Enable Slash Swapper",
			description = "SlashSwapper changes the / and // around for clan chat",
			section = menuSection
	)
	default boolean slashSwaperOption() {
		return false;
	}

	@ConfigItem(
			position = 1,
			keyName = "tekton",
			name = "Tekton",
			description = "Tekton",
			section = chambersSection
	)
	default boolean chamberTektonOption() {
		return true;
	}

	@ConfigItem(
			position = 2,
			keyName = "crabs",
			name = "Crabs",
			description = "Show tiles for crabs META",
			section = chambersSection
	)
	default boolean chamberCrabOption() {
		return true;
	}

	@ConfigItem(
			position = 3,
			keyName = "ice",
			name = "Ice Demon",
			description = "Show tiles for crabs META",
			section = chambersSection
	)
	default boolean chamberIceOption() {
		return true;
	}
	@ConfigItem(
			position = 4,
			keyName = "shamans",
			name = "Shamans",
			description = "Show tiles for crabs META",
			section = chambersSection
	)
	default boolean chamberShamansOption() {
		return true;
	}
	@ConfigItem(
			position = 5,
			keyName = "vanguards",
			name = "Vanguards",
			description = "Show tiles for crabs META",
			section = chambersSection
	)
	default boolean chamberVanguardsOption() {
		return true;
	}
	@ConfigItem(
			position = 6,
			keyName = "thieving",
			name = "Thieving",
			description = "Show tiles for crabs META",
			section = chambersSection
	)
	default boolean chamberThievingOption() {
		return true;
	}

	@ConfigItem(
			position = 7,
			keyName = "vespula",
			name = "Vespula",
			description = "Show tiles for crabs META",
			section = chambersSection
	)
	default boolean chamberVespulaOption() {
		return true;
	}
	@ConfigItem(
			position = 8,
			keyName = "tightrope",
			name = "Tightrope",
			description = "Show tiles for crabs META",
			section = chambersSection
	)
	default boolean chamberTightropeOption() {
		return true;
	}

	@ConfigItem(
			position = 9,
			keyName = "guardians",
			name = "Guardians",
			description = "Show tiles for crabs META",
			section = chambersSection
	)
	default boolean chamberGuardiansOption() {
		return true;
	}
	@ConfigItem(
			position = 10,
			keyName = "vasa",
			name = "Vasa Nistiro",
			description = "Show tiles for crabs META",
			section = chambersSection
	)
	default boolean chamberVasaOption() {
		return true;
	}
	@ConfigItem(
			position = 11,
			keyName = "mystics",
			name = "Mystics",
			description = "Show tiles for crabs META",
			section = chambersSection
	)
	default boolean chamberMysticOption() {
		return true;
	}
	@ConfigItem(
			position = 12,
			keyName = "muttadile",
			name = "Muttadile",
			description = "Show tiles for crabs META",
			section = chambersSection
	)
	default boolean chamberMuttadileOption() {
		return true;
	}
	@ConfigItem(
			position = 13,
			keyName = "olm",
			name = "Olm",
			description = "Show tiles for crabs META",
			section = chambersSection
	)
	default boolean chamberOlmOption() {
		return true;
	}
	@ConfigItem(
			position = 14,
			keyName = "tips",
			name = "Tips",
			description = "Show tiles for crabs META",
			section = chambersSection
	)
	default boolean chamberTipsOption() {
		return true;
	}

}