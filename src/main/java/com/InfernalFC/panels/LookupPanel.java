package com.InfernalFC.panels;

import com.InfernalFC.InfernalFCConfig;
import com.InfernalFC.InfernalFCPlugin;
import com.google.gson.Gson;
import javax.inject.Inject;
import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class LookupPanel extends JPanel {
    private final JLabel ssText = new JLabel();
    private final JComboBox combobox = new JComboBox();
    private PlayerData[] playerData = new PlayerData[0];
    private String color1;
    private String color2;
    private RankData[] ranks;

    @Inject
    public LookupPanel(InfernalFCConfig config, InfernalFCPlugin infernalFCPlugin) {
        this.ranks = infernalFCPlugin.getRanks();
        //Set the color
        color1 = "#"+Integer.toHexString(config.col1color().getRGB()).substring(2);
        color2 = "#"+Integer.toHexString(config.col2color().getRGB()).substring(2);

        combobox.setEditable(true);
        combobox.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent event) {
                if (event.getKeyChar() == KeyEvent.VK_ENTER) {
                    String searchString = ((JTextComponent) ((JComboBox) ((Component) event
                            .getSource()).getParent()).getEditor()
                            .getEditorComponent()).getText();
                    if (!searchString.isEmpty()) {
                        GetPlayerData(searchString);
                    }
                }
            }
        });

        combobox.addItemListener(e ->
                {
                    if (e.getStateChange() == ItemEvent.SELECTED)
                    {
                        String selection = e.getItem().toString();
                        PlayerData player = Arrays.stream(playerData).filter(data ->
                                selection.equals(data.getUsername())).findFirst().orElse(null);
                        SetPlayerStats(player);
                    }
                }
        );
        this.setPreferredSize(new Dimension(200, 700));
        this.add(combobox);
        this.add(ssText);
    }

    public void GetPlayerData(String searchString) {
        try {
            URL url = new URL("https://infernal-fc.com/api/Members?active=1&_start=0&_end=10&username="
                    + URLEncoder.encode(searchString, StandardCharsets.UTF_8.toString()));

            InputStream input = url.openStream();
            Reader reader = new InputStreamReader(input, StandardCharsets.UTF_8);
            playerData  = new Gson().fromJson(reader, PlayerData[].class);

            String[] dropdownData = Arrays.stream(playerData).map(PlayerData::getUsername).toArray(String[]::new);
            combobox.setModel(new DefaultComboBoxModel(dropdownData));
            combobox.setSelectedIndex(-1);
            combobox.getEditor().setItem(searchString);
            combobox.showPopup();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private PlayerData[] GetAltData(int id) {
        try {
            // Create a neat value object to hold the URL
            URL url = new URL("https://infernal-fc.com/api/Members?active=1&_start=0&_end=10&parentAccount=" + id);

            InputStream input = url.openStream();
            Reader reader = new InputStreamReader(input, StandardCharsets.UTF_8);
            return new Gson().fromJson(reader, PlayerData[].class);

        } catch (Exception e) {
            System.out.println(e);
            return new PlayerData[0];
        }
    }

    private void SetPlayerStats(PlayerData playerData) {
        String data = "";

        if (playerData != null) {
            PlayerData[] alts = GetAltData(playerData.getId());
            RankData rank = Arrays.stream(ranks).filter(r -> playerData.getRank_id()
                    == r.getId()).findFirst().orElse(null);

            data += "<html><table width=230>";

            data += "<tr>";
            data += "</tr>";
            data += "<tr>";
            data += "<td><font color='" + color1 + "'>Username</font></td>";
            data += "<td><font color='" + color2 + "'>";
            data += playerData.getUsername();
            data += "</font></td>";
            data += "</tr>";

            data += "<tr>";
            data += "<td><font color='" + color1 + "'>Rank</font></td>";
            data += "<td><font color='" + color2 + "'>";
            data += rank.getName();
            data += "</font></td>";
            data += "</tr>";

            data += "<tr>";
            data += "<td><font color='" + color1 + "'>Active?</font></td>";
            data += "<td><font color='" + color2 + "'>";
            data += playerData.getActive();
            data += "</font></td>";
            data += "</tr>";

            data += "<tr>";
            data += "</tr>";
            data += "<tr>";
            data += "<td><font color='" + color1 + "'><b>Points Section</b></font></td>";
            data += "<td><font color='" + color2 + "'>";
            data += "</font></td>";
            data += "</tr>";

            data += "<tr>";
            data += "<td><font color='" + color1 + "'>PvM Points</font></td>";
            data += "<td><font color='" + color2 + "'>";
            data += playerData.getPvmPoints();
            data += "</font></td>";
            data += "</tr>";

            data += "<tr>";
            data += "<td><font color='" + color1 + "'>Community Points</font></td>";
            data += "<td><font color='" + color2 + "'>";
            data += playerData.getNonPvmPoints();
            data += "</font></td>";
            data += "</tr>";

            data += "<tr>";
            data += "<td><font color='" + color1 + "'>Total points</font></td>";
            data += "<td><font color='" + color2 + "'>";
            data += playerData.getOverallPoints();
            data += "</font></td>";
            data += "</tr>";

            data += "<tr>";
            data += "</tr>";
            data += "<tr>";
            data += "<td><font color='" + color1 + "'><b>Loot Section</b></font></td>";
            data += "<td><font color='" + color2 + "'>";
            data += "</font></td>";
            data += "</tr>";

            data += "<tr>";
            data += "<td><font color='" + color1 + "'>Amount split</font></td>";
            data += "<td><font color='" + color2 + "'>";
            data += playerData.getValueSplit() ;
            data += "<font color='" + color2 + "'>M</td>";
            data += "</tr>";

            data += "<tr>";
            data += "<td><font color='" + color1 + "'>Amount Tanked</font></td>";
            data += "<td><font color='" + color2 + "'>";
            data += playerData.getValueTanked() ;
            data += "<font color='" + color2 + "'>M</td>";
            data += "</tr>";

            data += "<tr>";
            data += "</tr>";
            data += "<tr>";
            data += "<td><font color='" + color1 + "'><b>Mentor Section</b></font></td>";
            data += "<td><font color='" + color2 + "'>";
            data += "</font></td>";
            data += "</tr>";

            data += "<tr>";
            data += "<td><font color='" + color1 + "'>Mentor Points</font></td>";
            data += "<td><font color='" + color2 + "'>";
            data += playerData.getMentorPoints();
            data += "</font></td>";
            data += "</tr>";

            data += "<tr>";
            data += "<td><font color='" + color1 + "'>Helper Points</font></td>";
            data += "<td><font color='" + color2 + "'>";
            data += playerData.getHelperPoints();
            data += "</font></td>";
            data += "</tr>";

            data += "<tr>";
            data += "</tr>";
            data += "<tr>";
            data += "<td><font color='" + color1 + "'><b>Alt Section</b></font></td>";
            data += "<td><font color='" + color2 + "'>";
            data += "</font></td>";
            data += "</tr>";
            if (alts.length > 0) {
                int altIndex = 0;
                for (PlayerData alt : alts) {
                    data += "<tr>";
                    if (altIndex == 0) {
                        data += "<td><font color='" + color1 + "'>Usernames</font></td>";
                    } else {
                        data += "<td></td>";
                    }

                    data += "<td><font color='" + color2 + "'>";
                    data += alt.getUsername();
                    data += "</font></td>";
                    data += "</tr>";
                    altIndex ++;
                }
            } else {
                data += "<tr><td><font color='" + color1 + "'>No registered alts</font></td></tr>";
            }

            data += "</table></html>";
        }

        ssText.setText(data);
    }
}

