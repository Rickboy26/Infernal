package com.InfernalFC;

import java.awt.*;
import java.awt.event.*;
import javax.inject.Inject;
import com.InfernalFC.components.combobox.ComboBoxIconEntry;
import com.InfernalFC.components.combobox.ComboBoxIconListRenderer;
import com.InfernalFC.panels.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import net.runelite.client.ui.PluginPanel;
import net.runelite.client.util.ImageUtil;

@Slf4j
class InfernalFCPanel extends PluginPanel
{
    private final InfernalFCConfig config;

    final JComboBox<ComboBoxIconEntry> dropdown = new JComboBox<>();

    @Getter
    private final HomePanel homePanel;
    @Getter
    private final CmMenPanel cmMenPanel;
    @Getter
    private final RanksPanel ranksPanel;
    @Getter
    private final LookupPanel lookupPanel;

    private String tab;

    @Inject
    private InfernalFCPanel(InfernalFCConfig config, CmMenPanel cmMenPanel, HomePanel homePanel,
                            RanksPanel ranksPanel, LookupPanel lookupPanel){
        this.config = config;
        this.homePanel = homePanel;
        this.cmMenPanel = cmMenPanel;
        this.ranksPanel = ranksPanel;
        this.lookupPanel = lookupPanel;
        int index = 0;

        //dropdown menu
        dropdown.setFocusable(false); // To prevent an annoying "focus paint" effect
        dropdown.setForeground(Color.WHITE);
        final ComboBoxIconListRenderer renderer = new ComboBoxIconListRenderer();
        dropdown.setRenderer(renderer);

        BufferedImage icon = ImageUtil.loadImageResource(getClass(), "home.png");
        dropdown.addItem(new ComboBoxIconEntry(new ImageIcon(icon), " Home", "home"));
        icon = ImageUtil.loadImageResource(getClass(), "home.png");
        dropdown.addItem(new ComboBoxIconEntry(new ImageIcon(icon), " Player Lookup", "lookup"));
//        icon = ImageUtil.loadImageResource(getClass(), "home.png");
//        dropdown.addItem(new ComboBoxIconEntry(new ImageIcon(icon), " Requirements", "ranks"));
        icon = ImageUtil.loadImageResource(getClass(), "cm.png");
        dropdown.addItem(new ComboBoxIconEntry(new ImageIcon(icon), " CM Meta", "cmmen"));

        dropdown.addItemListener(e ->
        {
            if (e.getStateChange() == ItemEvent.SELECTED)
            {
                final ComboBoxIconEntry source = (ComboBoxIconEntry) e.getItem();
                tab = source.getData().toString();
                this.removeAll();
                this.add(dropdown);

                switch (tab) {
                    case "home":
                        this.add(getHomePanel());
                        getHomePanel().Load();
                        break;
                    case "cmmen":
                        this.add(getCmMenPanel());
                        break;
                    case "lookup":
                        this.add(getLookupPanel());
                        break;
                    case "ranks":
                        this.add(getRanksPanel());
                        getRanksPanel().rankChange("Trial");
                        break;
                }
                this.updateUI();
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
        cmMenPanel.setSize( new Dimension( 200, 700 ) );
        lookupPanel.setSize( new Dimension( 200, 700 ) );
        ranksPanel.setSize( new Dimension( 200, 700 ) );

        this.add(getHomePanel());
        getHomePanel().Load();
    }
}