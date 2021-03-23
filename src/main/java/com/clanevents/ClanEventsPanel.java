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

package com.clanevents;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.clanevents.components.combobox.ComboBoxIconEntry;
import com.clanevents.components.combobox.ComboBoxIconListRenderer;
import com.clanevents.panels.*;
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
class ClanEventsPanel extends PluginPanel
{
    final JComboBox<ComboBoxIconEntry> dropdown = new JComboBox<>();
    JPanel cmmenLayouit;
    JPanel lookupLayouit;
    JPanel ranksLayouit;
    private String tab;


    void init(ClanEventsConfig config, int index){


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
        dropdown.addItem(new ComboBoxIconEntry(new ImageIcon(icon), " Ranks", "ranks"));
        icon = ImageUtil.loadImageResource(getClass(), "cm.png");
        dropdown.addItem(new ComboBoxIconEntry(new ImageIcon(icon), " CM Meta", "cmmen"));

        RankData[] ranks = GetRankData();

        Lookup lookup = new Lookup(config, ranks);
        CmMen cmMen = new CmMen(config);
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
                    case "cmmen":
                        this.add(cmmenLayouit);
                        break;
                    case "lookup":
                        this.add(lookupLayouit);
                        break;
                    case "ranks":
                        this.add(ranksLayouit);
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

        lookupLayouit = lookup.getLayout();
        lookupLayouit.setSize( new Dimension( 200, 700 ) );

        cmmenLayouit = cmMen.getLayout();
        cmmenLayouit.setSize( new Dimension( 200, 700 ) );

        ranksLayouit = ranksPanel.getLayout();
        ranksLayouit.setSize( new Dimension( 200, 700 ) );

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