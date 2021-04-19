package com.InfernalFC.panels;

import com.InfernalFC.InfernalFCConfig;
import lombok.SneakyThrows;
import net.runelite.client.ui.ColorScheme;
import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class HomePanel extends JPanel {
    private final DataManager dataManager;
    private JPanel buttonPanel = new JPanel();
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
        buttonPanel.setLayout(new GridLayout(0,3));

        buttonPanel.add(createLinkButton("Website", "https://infernal-fc.com/"));
        buttonPanel.add(createLinkButton("Forum", "http://services.runescape.com/m=forum/c=IKL-JSzhrmo/users.ws?searchname=Infernal+Fc&lookup=view"));
        buttonPanel.add(createLinkButton("Discord", "https://discord.gg/ABYs3VK"));
        this.add(buttonPanel);
        this.add(ssText);
    }

    public void Load() {
        Runnable task = this::SetPointsStats;

        Thread thread = new Thread(task);
        thread.start();
    }

    private JButton createLinkButton(String name, String url )
    {
        final JButton button = new JButton(name);
        button.setPreferredSize(new Dimension(74, 33));
        button.setFont(new Font("Arial", Font.PLAIN, 12));
        button.setFocusable(false);
        button.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e)
            {
                button.setBackground(ColorScheme.DARK_GRAY_HOVER_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                button.setBackground(ColorScheme.DARK_GRAY_COLOR);
            }

            @SneakyThrows
            @Override
            public void mousePressed(MouseEvent e)
            {
                if (e.getButton() == MouseEvent.BUTTON1)
                {
                    OpenUrl(url);
                }
            }
        });

        return button;
    }

    private void OpenUrl(String url) {

        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
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
        data += "<td colspan='2' style='text-align: center;'><font color='" + color1 + "'><b>Overall Points:</b></font></td>";
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

