/*
 * Copyright (c) 2018, Kruithne <kruithne@gmail.com>
 * Copyright (c) 2018, Psikoi <https://github.com/psikoi>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

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
        icon = ImageUtil.loadImageResource(getClass(), "home.png");
        dropdown.addItem(new ComboBoxIconEntry(new ImageIcon(icon), " Requirements", "ranks"));
        icon = ImageUtil.loadImageResource(getClass(), "cm.png");
        dropdown.addItem(new ComboBoxIconEntry(new ImageIcon(icon), " CM Meta", "cmmen"));

        RankData[] ranks = GetRankData();

        CmMen cmMen = new CmMen(config);
        Home home = new Home(config);
        Lookup lookup = new Lookup(config, ranks);
        Ranks ranksPanel = new Ranks(config, ranks);

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