package com.InfernalFC.panels;

import com.InfernalFC.InfernalFCConfig;
import com.google.gson.Gson;
import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static com.InfernalFC.panels.PointsEnum.*;

public class HomePanel extends JPanel {
    private final JLabel ssText = new JLabel();
    private String color1;
    private String color2;

    @Inject
    private HomePanel(InfernalFCConfig config) {

        //Set the color
        color1 = "#"+Integer.toHexString(config.col1color().getRGB()).substring(2);
        color2 = "#"+Integer.toHexString(config.col2color().getRGB()).substring(2);


        this.setPreferredSize(new Dimension(200, 900));
        this.add(ssText);

        SetPointsStats();
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

    public EventData[] GetEvents() {
        try {
            URL url = new URL("https://infernal-fc.com/api/Events?_end=10&_order=DESC&_sort=date&_start=0");

            InputStream input = url.openStream();
            Reader reader = new InputStreamReader(input, StandardCharsets.UTF_8);
            EventData[] eventData  = new Gson().fromJson(reader, EventData[].class);

            Date today = new Date();
            today.setHours(0);
            today.setMinutes(0);
            today.setSeconds(0);
            eventData = Arrays.stream(eventData).filter(x -> x.getDate().after(today)).toArray(EventData[]::new);
            invertArray(eventData);

            return eventData;
        } catch (Exception e) {
            System.out.println(e);
            return new EventData[0];
        }
    }

    private void invertArray(EventData[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            EventData temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
    }

    private void SetPointsStats() {
        PointsData overall = GetPoints(OVERALL);
        PointsData ehb = GetPoints(EHB);
        PointsData kc = GetPoints(KC);
        EventData[] events = GetEvents();

        String pattern = "yyyy/MM/dd";
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

enum PointsEnum {
    OVERALL,
    EHB,
    KC
}

