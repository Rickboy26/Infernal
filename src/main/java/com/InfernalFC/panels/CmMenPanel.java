package com.InfernalFC.panels;

import com.InfernalFC.InfernalFCConfig;
import com.InfernalFC.GoogleSheet;
import lombok.SneakyThrows;
import net.runelite.client.ui.ColorScheme;
import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class CmMenPanel extends JPanel {
    private final JLabel ssText = new JLabel();
    private JPanel cmButtonPanel = new JPanel();
    private final GoogleSheet sheet = new GoogleSheet();
    private String color1;
    private String color2;
    private int cmMan = 3;
    private List<List<Object>> sheetValues;

    @Inject
    private CmMenPanel(InfernalFCConfig config) {
        // Google sheet API
        sheet.setKey("");
        sheet.setSheetId("1B3Oq_M45N5HULV1_OnMYE_RKgvIrXGiOrCQJKodjxU0");

        //Set the color
        color1 = "#"+Integer.toHexString(config.col1color().getRGB()).substring(2);
        color2 = "#"+Integer.toHexString(config.col2color().getRGB()).substring(2);

        for (int i = 3; i < 10; i++) {
            cmButtonPanel.add(createCmButton(i), BorderLayout.WEST);
        }

        this.add(cmButtonPanel, BorderLayout.NORTH);

        ssText.setText(getSheetDataFormatted());
        this.add(ssText);

        Runnable task = () -> {
            try {
                sheetValues = sheet.getValues("cmmen");
                ssText.setText(getSheetDataFormatted());

            }catch (Exception ioException)
            {
                ssText.setText("Could not load google sheet data.");
            }
        };

        Thread thread = new Thread(task);
        thread.start();

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
                    ssText.setText(getSheetDataFormatted());
                }
            }
        });

        return label;
    }

    private String getSheetDataFormatted()
    {
        String data = "";


        if (sheetValues == null || sheetValues.isEmpty()) {
            System.out.println("No data found.");
        } else {
            data += "<html><table width=230>";
            int i = 0;
            for (java.util.List row : sheetValues) {
                if (i  > 12) {
                    break;
                }
                String val1 = "";
                String val2 = "";

                try
                {
                    val1 = ""+row.get(0);
                }catch(Exception e)
                {
                    val1 = "";
                }
                try
                {
                    val2 = ""+row.get(1);
                }catch(Exception e)
                {
                    val2 = "";
                }

                if (val1.equals(cmMan + " man") || i > 0) {
                    data += "<tr>";
                    data += "<td><font color='" + color1 + "'>";
                    data += val1;
                    data += "</font></td>";
                    data += "<td><font color='" + color2 + "'>";
                    data += val2;
                    data += "</font></td>";
                    data += "</tr>";

                    i++;
                }
            }
            data += "</table></html>";

        }
        return data;

    }
}
