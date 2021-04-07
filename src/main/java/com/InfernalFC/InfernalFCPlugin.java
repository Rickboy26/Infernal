package com.InfernalFC;

import com.InfernalFC.panels.RankData;
import com.google.gson.Gson;
import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.game.SkillIconManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.util.ImageUtil;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Slf4j
@PluginDescriptor(
		name = "Infernal FC",
		description = "A plugin used to keep track of clan events/announcements",
		tags = {"Infernal"}
)
public class InfernalFCPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private InfernalFCConfig config;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private InfernalFCOverlay overlay;

	@Inject
	private SkillIconManager skillIconManager;

	@Getter
	public RankData[] ranks;

	@Inject
	private ClientToolbar clientToolbar;
	@Getter(AccessLevel.PACKAGE)
	@Setter(AccessLevel.PACKAGE)
	private InfernalFCPanel panel;
	private NavigationButton uiNavigationButton;

	static final String CONFIG_GROUP = "InfernalFC";

	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(overlay);
		startClanPanel();
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlay);
		clientToolbar.removeNavigation(uiNavigationButton);
	}

	@Subscribe
	private void onConfigChanged(ConfigChanged event) throws IOException {
		if (event.getGroup().equals(CONFIG_GROUP))
		{
			panel.removeAll();
			clientToolbar.removeNavigation(uiNavigationButton);
			startClanPanel();
		}
	}

	private void startClanPanel()
	{
		ranks = GetRankData();
		final BufferedImage icon = ImageUtil.loadImageResource(getClass(), "icon.png");
		panel = injector.getInstance(InfernalFCPanel.class);
		uiNavigationButton = NavigationButton.builder()
				.tooltip("Infernal FC")
				.icon(icon)
				.priority(7)
				.panel(panel)
				.build();
		clientToolbar.addNavigation(uiNavigationButton);
	}

	private RankData[] GetRankData() {
		try {
			// Create a neat value object to hold the URL
			URL url = new URL("https://infernal-fc.com/api/ranks?_start=0&_end=5000");

			InputStream input = url.openStream();
			Reader reader = new InputStreamReader(input, StandardCharsets.UTF_8);
			return new Gson().fromJson(reader, RankData[].class);

		} catch (Exception e) {
			System.out.println(e);
			return new RankData[0];
		}
	}

	@Provides
	InfernalFCConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(InfernalFCConfig.class);
	}
}
