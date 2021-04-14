package com.InfernalFC.panels;

import com.InfernalFC.InfernalFCConfig;
import lombok.SneakyThrows;
import net.runelite.client.ui.ColorScheme;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;


public class TobSpecPanel extends JPanel {
    private final JLabel ssText = new JLabel();
    private JPanel TobButtonPanel = new JPanel();
    private String color1;
    private String color2;
    private int TobSpecPanel = 3;
    private TobData data;

    @Inject
    private TobSpecPanel(InfernalFCConfig config, TobData data) {
        this.data = data;
        //Set the color
        color1 = "#"+Integer.toHexString(config.col1color().getRGB()).substring(2);
        color2 = "#"+Integer.toHexString(config.col2color().getRGB()).substring(2);

        for (int i = 3; i < 7; i++) {
            TobButtonPanel.add(createTobButton(i), BorderLayout.WEST);
        }

        this.setPreferredSize(new Dimension(200, 400));
        this.add(TobButtonPanel, BorderLayout.NORTH);

        ssText.setText(getDataFormatted());
        this.add(ssText);
    }

    private JButton createTobButton(int index )
    {
        final JButton label = new JButton(String.valueOf(index));
        label.setPreferredSize(new Dimension(26, 26));
        label.setFont(new Font("Arial", Font.PLAIN, 9));
        label.setFocusable(false);
        label.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e)
            {
                label.setBackground(ColorScheme.DARK_GRAY_HOVER_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                label.setBackground(ColorScheme.DARK_GRAY_COLOR);
            }

            @SneakyThrows
            @Override
            public void mousePressed(MouseEvent e)
            {
                if (e.getButton() == MouseEvent.BUTTON1)
                {
                    TobSpecPanel = index;
                    ssText.setText(getDataFormatted());
                }
            }
        });

        return label;
    }

    private String getDataFormatted()
    {
        String data = "";

        try {
            String[][] TobData = this.data.itemMapping.get(String.valueOf(TobSpecPanel));

            data += "<html><table width=230>";
            for (String[] row : TobData) {

                data += "<tr>";
                data += "<td><font color='" + color1 + "'>";
                data += (String) Array.get(row, 0);
                data += "</font></td>";
                data += "<td><font color='" + color2 + "'>";
                data += (String) Array.get(row, 1);
                data += "</font></td>";
                data += "</tr>";
            }
            data += "</table></html>";
        } catch (Exception e) {

        }

        return data;
    }
}
