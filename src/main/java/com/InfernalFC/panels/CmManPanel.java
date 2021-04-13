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

public class CmManPanel extends JPanel {
    private final JLabel ssText = new JLabel();
    private JPanel cmButtonPanel = new JPanel();
    private String color1;
    private String color2;
    private int cmMan = 3;
    private CmManData data;

    @Inject
    private CmManPanel(InfernalFCConfig config, CmManData data) {
        this.data = data;
        //Set the color
        color1 = "#"+Integer.toHexString(config.col1color().getRGB()).substring(2);
        color2 = "#"+Integer.toHexString(config.col2color().getRGB()).substring(2);

        for (int i = 3; i < 10; i++) {
            cmButtonPanel.add(createCmButton(i), BorderLayout.WEST);
        }

        this.setPreferredSize(new Dimension(200, 400));
        this.add(cmButtonPanel, BorderLayout.NORTH);

        ssText.setText(getDataFormatted());
        this.add(ssText);
    }

    private JButton createCmButton(int index )
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
                    cmMan = index;
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
            String[][] cmManData = this.data.itemMapping.get(String.valueOf(cmMan));

            data += "<html><table width=230>";
            for (String[] row : cmManData) {

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
