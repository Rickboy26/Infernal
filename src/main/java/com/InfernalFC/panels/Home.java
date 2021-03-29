package com.InfernalFC.panels;

import com.InfernalFC.InfernalFCConfig;
import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static com.InfernalFC.panels.PointsEnum.*;

public class Home {
    private final JPanel ssArea = new JPanel();
    private final JLabel ssText = new JLabel();
    private String color1;
    private String color2;

    public Home(InfernalFCConfig config) {

        //Set the color
        color1 = "#"+Integer.toHexString(config.col1color().getRGB()).substring(2);
        color2 = "#"+Integer.toHexString(config.col2color().getRGB()).substring(2);


        ssArea.setPreferredSize(new Dimension(200, 700));
        ssArea.add(ssText);

        SetPointsStats();
    }

    public JPanel getLayout() {
        return ssArea;
    }

    public PointsData GetPoints(PointsEnum score) {
        try {
            URL url;

            switch (score) {
                case EHB:
                    url = new URL("https://infernal-fc.com/api/Members/killcounts/ehb?_start=0&_end=1");
                    break;
                case KC:
                    url = new URL("https://infernal-fc.com/api/Members/killcounts/overall?_start=0&_end=1");
                    break;
                default:
                    url = new URL("https://infernal-fc.com/api/Members/points/overall?_start=0&_end=1");
                    break;
            }

            InputStream input = url.openStream();
            Reader reader = new InputStreamReader(input, StandardCharsets.UTF_8);
            PointsData[] pointsData  = new Gson().fromJson(reader, PointsData[].class);

            return pointsData[0];

        } catch (Exception e) {
            System.out.println(e);
            return new PointsData();
        }
    }

    private void SetPointsStats() {
        PointsData overall = GetPoints(OVERALL);
        PointsData ehb = GetPoints(EHB);
        PointsData kc = GetPoints(KC);

        String data = "";

        data += "<html><table width=230>";

        data += "<tr></tr>";

        data += "<tr>";
        data += "<td><font color='" + color1 + "'><b>Overall points:</b></font></td>";
        data += "</tr>";

        data += "<tr>";
        data += "<td><font color='" + color1 + "'>";
        data += overall.getMember().getUsername();
        data += "</font></td>";
        data += "<td><font color='" + color2 + "'>";
        data += (int)overall.getSum();
        data += "</font></td>";
        data += "</tr>";

        data += "<tr></tr>";

        data += "<tr>";
        data += "<td><font color='" + color1 + "'><b>EHB:</b></font></td>";
        data += "</tr>";

        data += "<tr>";
        data += "<td><font color='" + color1 + "'>";
        data += ehb.getMember().getUsername();
        data += "</font></td>";
        data += "<td><font color='" + color2 + "'>";
        data += (int)ehb.getSum();
        data += "</font></td>";
        data += "</tr>";

        data += "<tr></tr>";

        data += "<tr>";
        data += "<td><font color='" + color1 + "'><b>KC:</b></font></td>";
        data += "</tr>";

        data += "<tr>";
        data += "<td><font color='" + color1 + "'>";
        data += kc.getMember().getUsername();
        data += "</font></td>";
        data += "<td><font color='" + color2 + "'>";
        data += (int)kc.getSum();
        data += "</font></td>";
        data += "</tr>";


        data += "</table></html>";

        ssText.setText(data);
    }
}

enum PointsEnum {
    OVERALL,
    EHB,
    KC
}

