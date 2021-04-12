package com.InfernalFC.panels;

import com.InfernalFC.InfernalFCConfig;
import net.runelite.client.callback.ClientThread;
import javax.inject.Inject;
import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class LookupPanel extends JPanel {
    private final DataManager dataManager;
    private final JLabel ssText = new JLabel();
    private final JComboBox combobox = new JComboBox();
    private PlayerData[] playerData = new PlayerData[0];
    private String color1;
    private String color2;

    @Inject
    public LookupPanel(InfernalFCConfig config, DataManager dataManager) {

        this.dataManager = dataManager;

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
                        Search(searchString);
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

                        Runnable task = () -> {
                            SetPlayerStats(player);
                        };

                        Thread thread = new Thread(task);
                        thread.start();
                    }
                }
        );
        this.setPreferredSize(new Dimension(200, 700));
        this.add(combobox);
        this.add(ssText);
    }

    public void Search(String searchString) {
        Runnable task = () -> {
            playerData  = dataManager.GetPlayerData(searchString);

            String[] dropdownData = Arrays.stream(playerData).map(PlayerData::getUsername).toArray(String[]::new);
            combobox.setModel(new DefaultComboBoxModel(dropdownData));
            combobox.setSelectedIndex(-1);
            combobox.getEditor().setItem(searchString);
            combobox.showPopup();
        };

        Thread thread = new Thread(task);
        thread.start();
    }

    private void SetPlayerStats(PlayerData playerData) {
        String data = "";

        if (playerData != null) {
            PlayerData[] alts = dataManager.GetAltData(playerData.getId());
            RankData[] ranks = dataManager.GetRankData();
            RankData rank = Arrays.stream(ranks).filter(r -> playerData.getRank_id()
                    == r.getId()).findFirst().orElse(null);

            data += "<html><table width=230>";

            data += "<tr>";
            data += "</tr>";
            data += "<tr>";
            data += "<td><font color='yellow'><b>General</b></font></td>";
            data += "<td><font color='" + color2 + "'>";
            data += "</font></td>";
            data += "</tr>";

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
            data += "<td><font color='" + color1 + "'>Active</font></td>";
            data += "<td><font color='" + color2 + "'>";
            data += playerData.getActive()? "Yes" : "No";
            data += "</font></td>";
            data += "</tr>";

            data += "<tr>";
            data += "</tr>";
            data += "<tr>";
            data += "<td><font color='yellow'><b>Points Section</b></font></td>";
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
            data += "<td><font color='yellow'><b>Loot Section</b></font></td>";
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
            data += "<td><font color='yellow'><b>Mentor Section</b></font></td>";
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
            data += "<td><font color='yellow'><b>Alt Section</b></font></td>";
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

