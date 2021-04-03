package com.InfernalFC;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.InfernalFC.components.combobox.ComboBoxIconEntry;
import com.InfernalFC.components.combobox.ComboBoxIconListRenderer;
import com.InfernalFC.panels.*;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import net.runelite.client.ui.PluginPanel;
import net.runelite.client.util.ImageUtil;

@Slf4j
class InfernalFCPanel extends PluginPanel
{
    final JComboBox<ComboBoxIconEntry> dropdown = new JComboBox<>();
    JPanel homeLayout;
    JPanel cmmenLayout;
    JPanel lookupLayout;
    JPanel ranksLayout;
    private String tab;


    void init(InfernalFCConfig config, int index){

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

        RankData[] ranks = GetRankData();

        CmMen cmMen = new CmMen(config);
        Home home = new Home(config);
        Lookup lookup = new Lookup(config, ranks);
        Ranks ranksPanel = new Ranks(ranks);

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
                        this.add(homeLayout);
                        break;
                    case "cmmen":
                        this.add(cmmenLayout);
                        break;
                    case "lookup":
                        this.add(lookupLayout);
                        break;
                    case "ranks":
                        this.add(ranksLayout);
                        ranksPanel.rankChange("Trial");
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

        homeLayout = home.getLayout();
        homeLayout.setSize( new Dimension( 200, 700 ) );

        cmmenLayout = cmMen.getLayout();
        cmmenLayout.setSize( new Dimension( 200, 700 ) );

        lookupLayout = lookup.getLayout();
        lookupLayout.setSize( new Dimension( 200, 700 ) );

        ranksLayout = ranksPanel.getLayout();
        ranksLayout.setSize( new Dimension( 200, 700 ) );

        this.add(homeLayout);
    }

    public RankData[] GetRankData() {
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
}