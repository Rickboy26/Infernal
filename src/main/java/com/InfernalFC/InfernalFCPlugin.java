package com.InfernalFC;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ObjectArrays;
import com.google.inject.Provides;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.swing.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.ItemContainerChanged;
import net.runelite.api.events.MenuEntryAdded;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.game.SkillIconManager;
import net.runelite.client.menus.MenuManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.util.ImageUtil;
import net.runelite.client.util.Text;
import org.apache.commons.lang3.ArrayUtils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@Slf4j
@PluginDescriptor(
		name = "Infernal",
		description = "A plugin designed for clans to keep track of members/events & much more!",
		tags = {"Infernal"}
)

public class InfernalFCPlugin extends Plugin
{
	@Inject
	private Provider<MenuManager> menuManager;

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

	@Inject
	private ClientToolbar clientToolbar;
	@Getter(AccessLevel.PACKAGE)
	@Setter(AccessLevel.PACKAGE)
	private InfernalFCPanel panel;
	private NavigationButton uiNavigationButton;

	static final String CONFIG_GROUP = "InfernalFC";
	static final String LOOKUP = "Clan Check";
	private static final String KICK_OPTION = "Kick";
	private static final ImmutableList<String> AFTER_OPTIONS = ImmutableList.of("Message", "Add ignore", "Remove friend", "Delete", KICK_OPTION);

	@Override
	protected void startUp() throws Exception
	{
		setOverLay();
		startClanPanel();

		if (config.menuOption() && client != null)
		{
			menuManager.get().addPlayerMenuItem(LOOKUP);
		}
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlay);
		clientToolbar.removeNavigation(uiNavigationButton);

		if (client != null)
		{
			menuManager.get().removePlayerMenuItem(LOOKUP);
		}
	}


	@Subscribe
	private void onConfigChanged(ConfigChanged event) throws IOException {
		if (event.getGroup().equals(CONFIG_GROUP))
		{
			if (event.getKey().equals("overlay")) {
				setOverLay();
			} else if (event.getKey().equals("col1color") || event.getKey().equals("col2color")){
				panel.removeAll();
				clientToolbar.removeNavigation(uiNavigationButton);
				startClanPanel();
			}

			if (client != null)
			{
				menuManager.get().removePlayerMenuItem(LOOKUP);

				if (config.menuOption())
				{
					menuManager.get().addPlayerMenuItem(LOOKUP);
				}
			}
		}
	}

	@Subscribe
	private void onItemContainerChanged(ItemContainerChanged event) {
		if (panel.getRanksPanel().isShowing() && event.getContainerId() == InventoryID.INVENTORY.getId()) {
			try {
				SwingUtilities.invokeAndWait(() ->
				{
					panel.getRanksPanel().updateItems();
				});
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}

	@Subscribe
	public void onMenuEntryAdded(MenuEntryAdded event)
	{
		if (!config.menuOption())
		{
			return;
		}

		int groupId = WidgetInfo.TO_GROUP(event.getActionParam1());
		String option = event.getOption();

		if (groupId == WidgetInfo.FRIENDS_LIST.getGroupId() || groupId == WidgetInfo.FRIENDS_CHAT.getGroupId() ||
				groupId == WidgetInfo.CHATBOX.getGroupId() && !KICK_OPTION.equals(option) || //prevent from adding for Kick option (interferes with the raiding party one)
				groupId == WidgetInfo.RAIDING_PARTY.getGroupId() || groupId == WidgetInfo.PRIVATE_CHAT_MESSAGE.getGroupId() ||
				groupId == WidgetInfo.IGNORE_LIST.getGroupId())
		{
			if (!AFTER_OPTIONS.contains(option) || (option.equals("Delete") && groupId != WidgetInfo.IGNORE_LIST.getGroupId()))
			{
				return;
			}

			final MenuEntry lookup = new MenuEntry();
			lookup.setOption(LOOKUP);
			lookup.setTarget(event.getTarget());
			lookup.setType(MenuAction.RUNELITE.getId());
			lookup.setParam0(event.getActionParam0());
			lookup.setParam1(event.getActionParam1());
			lookup.setIdentifier(event.getIdentifier());

			insertMenuEntry(lookup, client.getMenuEntries());
		}
	}

	@Subscribe
	public void onMenuOptionClicked(MenuOptionClicked event)
	{
		if ((event.getMenuAction() == MenuAction.RUNELITE || event.getMenuAction() == MenuAction.RUNELITE_PLAYER)
				&& event.getMenuOption().equals(LOOKUP))
		{
			final String target;
			if (event.getMenuAction() == MenuAction.RUNELITE_PLAYER)
			{
				// The player id is included in the event, so we can use that to get the player name,
				// which avoids having to parse out the combat level and any icons preceding the name.
				Player player = client.getCachedPlayers()[event.getId()];
				if (player == null)
				{
					return;
				}

				target = player.getName();
			}
			else
			{
				target = Text.removeTags(event.getMenuTarget());
			}

			SwingUtilities.invokeLater(() ->
			{
				if (!uiNavigationButton.isSelected())
				{
					uiNavigationButton.getOnSelect().run();
				}
				panel.SwitchPanel("lookup");
				panel.getLookupPanel().SearchExact(target);
			});
		}
	}

	private void insertMenuEntry(MenuEntry newEntry, MenuEntry[] entries)
	{
		MenuEntry[] newMenu = ObjectArrays.concat(entries, newEntry);
		int menuEntryCount = newMenu.length;
		ArrayUtils.swap(newMenu, menuEntryCount - 1, menuEntryCount - 2);
		client.setMenuEntries(newMenu);
	}

	private void startClanPanel()
	{
		final BufferedImage icon = ImageUtil.loadImageResource(getClass(), "icon.png");
		panel = injector.getInstance(InfernalFCPanel.class);
		uiNavigationButton = NavigationButton.builder()
				.tooltip("Infernal")
				.icon(icon)
				.priority(7)
				.panel(panel)
				.build();
		clientToolbar.addNavigation(uiNavigationButton);
	}

	private void setOverLay() {
		if (config.overlay()) {
			overlayManager.add(overlay);
		} else {
			overlayManager.remove(overlay);
		}
	}

	@Provides
	InfernalFCConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(InfernalFCConfig.class);
	}
}
