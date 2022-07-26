package com.InfernalFC;

import com.InfernalFC.helpers.GroundMarkerOverlay;
import com.InfernalFC.helpers.MarkersManager;
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
import net.runelite.api.events.*;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.chat.ChatMessageManager;
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
	private GroundMarkerOverlay groundMarkerOverlay;

	@Inject
	private MarkersManager markersManager;

	@Inject
	private SkillIconManager skillIconManager;
	@Inject
	private ChatMessageManager chatMessageManager;

	@Inject
	private ClientToolbar clientToolbar;
	@Getter(AccessLevel.PACKAGE)
	@Setter(AccessLevel.PACKAGE)
	private InfernalFCPanel panel;
	private NavigationButton uiNavigationButton;

	static final String CONFIG_GROUP = "InfernalFC";
	static final String LOOKUP = "Clan Check";
	static final int FRIENDS_CHAT = 9;
	static final int CLAN_CHAT = 41;
	private static final String KICK_OPTION = "Kick";
	private static final ImmutableList<String> AFTER_OPTIONS = ImmutableList.of("Message", "Add ignore", "Remove friend", "Delete", KICK_OPTION);

	@Override
	protected void startUp() throws Exception
	{
		setOverLay();
		startClanPanel();
		overlayManager.add(groundMarkerOverlay);

		if (config.menuOption() && client != null)
		{
			menuManager.get().addPlayerMenuItem(LOOKUP);
		}
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlay);
		overlayManager.remove(groundMarkerOverlay);
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
	public void onScriptCallbackEvent(ScriptCallbackEvent scriptCallbackEvent)
	{
		if (scriptCallbackEvent.getEventName().equals("preChatSendpublic")) {
			if (!config.slashSwaperOption())
			{
				return;
			}
			final String chatboxInput = client.getVar(VarClientStr.CHATBOX_TYPED_TEXT);
			if(chatboxInput == null){
				return;
			}

			if(chatboxInput.length()>=2 && chatboxInput.charAt(0)=='/' && chatboxInput.charAt(1) != '/'){
				int[] intStack = client.getIntStack();
				final int intStackSize = client.getIntStackSize();
				intStack[intStackSize - 1] = CLAN_CHAT;
				intStack[intStackSize - 2] = 1;

				return;
			}


			if(chatboxInput.length()>2 && chatboxInput.charAt(0)=='/' && chatboxInput.charAt(1) == '/' && chatboxInput.charAt(2) != '/'){
				int[] intStack = client.getIntStack();
				final int intStackSize = client.getIntStackSize();
				intStack[intStackSize - 1] = FRIENDS_CHAT;
				intStack[intStackSize - 2] = 0;
				client.setVar(VarClientStr.CHATBOX_TYPED_TEXT,chatboxInput.substring(2));
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
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() != GameState.LOGGED_IN)
		{
			return;
		}

		// map region has just been updated
		markersManager.loadPoints();
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

			client.createMenuEntry(-1)
					.setOption(LOOKUP)
					.setTarget(event.getTarget())
					.setType(MenuAction.RUNELITE)
					.setParam0(event.getActionParam0())
					.setParam1(event.getActionParam1())
					.setIdentifier(event.getIdentifier());
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
