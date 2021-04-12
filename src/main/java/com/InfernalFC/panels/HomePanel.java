package com.InfernalFC.panels;

import com.InfernalFC.InfernalFCConfig;
import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class HomePanel extends JPanel {
    private final DataManager dataManager;
    private final JLabel ssText = new JLabel();
    private String color1;
    private String color2;

    @Inject
    private HomePanel(InfernalFCConfig config, DataManager dataManager) {
        this.dataManager = dataManager;

        //Set the color
        color1 = "#"+Integer.toHexString(config.col1color().getRGB()).substring(2);
        color2 = "#"+Integer.toHexString(config.col2color().getRGB()).substring(2);


        this.setPreferredSize(new Dimension(200, 900));
        this.add(ssText);
    }

    public void Load() {
        Runnable task = this::SetPointsStats;

        Thread thread = new Thread(task);
        thread.start();
    }

    private void SetPointsStats() {
        PointsData overall = dataManager.GetOverallPoints();
        PointsData ehb = dataManager.GetEhbPoints();
        PointsData kc = dataManager.GetKcPoints();
        PointsData pets = dataManager.GetPetPoints();
        EventData[] events = dataManager.GetEvents();

        String pattern = "dd/MM/yyyy";
        DateFormat df = new SimpleDateFormat(pattern);

        String data = "";

        data += "<html><table width=230>";

        data += "<tr></tr>";

        data += "<tr>";
        data += "<td colspan='2' style='text-align: center; font-size:30px;'><font color='yellow'><b>Highscores</b></font></td>";
        data += "</tr>";

        data += "<tr></tr>";

        data += "<tr>";
        data += "<td colspan='2' style='text-align: center;'><font color='" + color1 + "'><b>Overall points:</b></font></td>";
        data += "</tr>";

        data += "<tr>";
        data += "<td colspan='2' style='text-align: center;'><font color='" + color1 + "'>";
        data += overall.getMember().getUsername();
        data += "</font></td>";
        data += "</tr>";
        data += "<tr>";
        data += "<td colspan='2' style='text-align: center;'><font color='yellow'>";
        data += (int)overall.getSum();
        data += "</font></td>";
        data += "</tr>";

        data += "<tr></tr>";

        data += "<tr>";
        data += "<td colspan='2' style='text-align: center;'><font color='" + color1 + "'><b>EHB:</b></font></td>";
        data += "</tr>";

        data += "<tr>";
        data += "<td colspan='2' style='text-align: center;'><font color='" + color1 + "'>";
        data += ehb.getMember().getUsername();
        data += "</font></td>";
        data += "</tr>";
        data += "<tr>";
        data += "<td colspan='2' style='text-align: center;'><font color='yellow'>";
        data += (int)ehb.getSum();
        data += "</font></td>";
        data += "</tr>";

        data += "<tr></tr>";

        data += "<tr>";
        data += "<td colspan='2' style='text-align: center;'><font color='" + color1 + "'><b>KC:</b></font></td>";
        data += "</tr>";

        data += "<tr>";
        data += "<td colspan='2' style='text-align: center;'><font color='" + color1 + "'>";
        data += kc.getMember().getUsername();
        data += "</font></td>";
        data += "</tr>";
        data += "<tr>";
        data += "<td colspan='2' style='text-align: center;'><font color='yellow'>";
        data += (int)kc.getSum();
        data += "</font></td>";
        data += "</tr>";
        data += "<tr></tr>";

        data += "<tr>";
        data += "<td colspan='2' style='text-align: center;'><font color='" + color1 + "'><b>Most Pets:</b></font></td>";
        data += "</tr>";

        data += "<tr>";
        data += "<td colspan='2' style='text-align: center;'><font color='" + color1 + "'>";
        data += pets.getMember().getUsername();
        data += "</font></td>";
        data += "</tr>";
        data += "<tr>";
        data += "<td colspan='2' style='text-align: center;'><font color='yellow'>";
        data += pets.getCount();
        data += "</font></td>";
        data += "</tr>";
        data += "<tr></tr>";

        data += "</table><hr></hr><table width=230>";

        data += "<tr></tr>";

        data += "<tr>";
        data += "<td colspan='2' style='text-align: center; font-size:30px;'><font color='yellow'><b>Events</b></font></td>";
        data += "</tr>";

        for (EventData event : events) {
            data += "<tr></tr>";
            data += "<tr>";
            data += "<td colspan='2' style='text-align: center;'><font color='" + color1 + "'><b>";
            data += df.format(event.getDate());
            data += "</b></font></td>";
            data += "</tr>";

            data += "<tr>";
            data += "<td colspan='2' style='text-align: center;'><font color='" + color1 + "'>";
            data += event.getName();
            data += "</font></td>";
            data += "</tr>";
        }

        data += "</table></html>";

        ssText.setText(data);
    }
}

