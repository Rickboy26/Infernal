package com.InfernalFC;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

import java.awt.*;

@ConfigGroup(InfernalFCPlugin.CONFIG_GROUP)
public interface InfernalFCConfig extends Config
{
		@ConfigSection(
				name = "Event Codeword",
				description = "Codeword info for clan event",
				position = 0
		)
		String eventPassSection = "Event Passphrase";

		@ConfigSection(
				name = "Colours",
				description = "Colour config",
				position = 1
		)
		String gSheetsSection = "Google Sheets API";

		@ConfigItem(
				position = 1,
				keyName = "overlay",
				name = "Display the overlay",
				description = "Display the overlay on your game screen",
				section = eventPassSection
		)
		default boolean overlay()
		{
			return false;
		}

		@ConfigItem(
				position = 2,
				keyName = "eventPass",
				name = "Event Codeword",
				description = "Creates an overlay with the event codeword",
				section = eventPassSection
		)
		default String eventPass()
		{
			return "";
		}
		@ConfigItem(
				position = 3,
				keyName = "dtm",
				name = "Include Date & Time",
				description = "Display the date and time",
				section = eventPassSection
		)
		default boolean dtm()
		{
			return true;
		}


		@ConfigItem(
				position = 5,
				keyName = "passColor",
				name = "Overlay Color",
				description = "Configures the color of the passphrase",
				section = eventPassSection
		)
		default Color passColor()
		{
			return Color.GREEN;
		}


		@ConfigItem(
				position = 8,
				keyName = "col1color",
				name = "Column 1 Color",
				description = "Configures the color of the spreadsheet column displayed",
				section = gSheetsSection
		)
		default Color col1color()
		{
			return Color.white;
		}

		@ConfigItem(
				position = 9,
				keyName = "col2color",
				name = "Column 2 Color",
				description = "Configures the color of the spreadsheet column displayed",
				section = gSheetsSection
		)
		default Color col2color()
		{
			return Color.GREEN;
		}
	}
