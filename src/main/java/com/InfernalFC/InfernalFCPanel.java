package com.InfernalFC;

import java.awt.*;
import java.awt.event.*;
import javax.inject.Inject;
import com.InfernalFC.components.combobox.ComboBoxIconEntry;
import com.InfernalFC.components.combobox.ComboBoxIconListRenderer;
import com.InfernalFC.helpers.DataManager;
import com.InfernalFC.panels.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.image.BufferedImage;
import javax.inject.Singleton;
import javax.swing.*;

import net.runelite.client.ui.PluginPanel;
import net.runelite.client.util.ImageUtil;

@Slf4j
@Singleton
public class InfernalFCPanel extends PluginPanel
{
    final JComboBox<ComboBoxIconEntry> dropdown = new JComboBox<>();

    @Getter
    private final HomePanel homePanel;
    @Getter
    private final CmManPanel cmManPanel;
    @Getter
    public final CmManRolePanel cmManRolePanel;
    @Getter
    private final RanksPanel ranksPanel;
    @Getter
    private final LookupPanel lookupPanel;
    @Getter
    private final TobSpecPanel tobSpecPanel;

    private String tab;

    @Inject
    private InfernalFCPanel(CmManPanel cmManPanel, CmManRolePanel cmManRolePanel, HomePanel homePanel, DataManager dataManager,
                            RanksPanel ranksPanel, LookupPanel lookupPanel, TobSpecPanel tobSpecPanel){
        this.homePanel = homePanel;
        this.cmManPanel = cmManPanel;
        this.cmManRolePanel = cmManRolePanel;
        this.ranksPanel = ranksPanel;
        this.lookupPanel = lookupPanel;
        this.tobSpecPanel = tobSpecPanel;
        int index = 0;

        //dropdown menu
        dropdown.setFocusable(false); // To prevent an annoying "focus paint" effect
        dropdown.setForeground(Color.WHITE);
        final ComboBoxIconListRenderer renderer = new ComboBoxIconListRenderer();
        dropdown.setRenderer(renderer);

        BufferedImage icon = ImageUtil.loadImageResource(getClass(), "home.png");
        dropdown.addItem(new ComboBoxIconEntry(new ImageIcon(icon), " Home", "home"));
        icon = ImageUtil.loadImageResource(getClass(), "Lookup_icon.png");
        dropdown.addItem(new ComboBoxIconEntry(new ImageIcon(icon), " Player Lookup", "lookup"));
        icon = ImageUtil.loadImageResource(getClass(), "Requirement_icon.png");
        dropdown.addItem(new ComboBoxIconEntry(new ImageIcon(icon), " Requirements", "ranks"));
        icon = ImageUtil.loadImageResource(getClass(), "cm.png");
        dropdown.addItem(new ComboBoxIconEntry(new ImageIcon(icon), " CM Meta", "cmman"));
    //    icon = ImageUtil.loadImageResource(getClass(), "tob.png");
    //    dropdown.addItem(new ComboBoxIconEntry(new ImageIcon(icon), " Tob Spec", "Tob"));

        dropdown.addItemListener(e ->
        {
            if (e.getStateChange() == ItemEvent.SELECTED)
            {
                final ComboBoxIconEntry source = (ComboBoxIconEntry) e.getItem();
                tab = source.getData().toString();

                SwitchPanel(tab);
            }
        });

        dropdown.setSelectedIndex(index);

        final GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.gridx = 0;
        c.gridy = 0;

        this.add(dropdown, c);
        c.gridy++;

        homePanel.setSize( new Dimension( 200, 700 ) );
        cmManPanel.setSize( new Dimension( 200, 700 ) );
        lookupPanel.setSize( new Dimension( 200, 700 ) );
        ranksPanel.setSize( new Dimension( 200, 700 ) );
        tobSpecPanel.setSize( new Dimension( 200, 700 ) );

        dropdown.setSelectedIndex(-1);
        dropdown.setSelectedIndex(0);

        Runnable task = dataManager::GetRankData;
        Thread thread = new Thread(task);
        thread.start();

    }

    public void SwitchPanel(String tab) {
        this.removeAll();
        this.add(dropdown);

        switch (tab) {
            case "home":
                this.add(getHomePanel());
                getHomePanel().Load();
                break;
            case "cmman":
                this.add(getCmManPanel());
                break;
            case "cmmanrole":
                this.add(getCmManRolePanel());
                break;
            case "lookup":
                this.add(getLookupPanel());
                break;
            case "ranks":
                this.add(getRanksPanel());
                getRanksPanel().rankChange("Trial");
                break;
            case "Tob":
                this.add(getTobSpecPanel());
                break;
        }
        this.updateUI();
    }
}