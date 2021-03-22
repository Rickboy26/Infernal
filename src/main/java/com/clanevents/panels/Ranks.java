package com.clanevents.panels;

import com.clanevents.ClanEventsConfig;
import com.clanevents.GoogleSheet;
import lombok.SneakyThrows;
import net.runelite.client.ui.ColorScheme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Ranks {
    private final JPanel ssArea = new JPanel();
    private final JLabel ssText = new JLabel();
    private JPanel cmButtonPanel = new JPanel();
    private final GoogleSheet sheet = new GoogleSheet();
    private String color1;
    private String color2;
    private int cmMan = 3;
    private RankData[] ranks;

    public Ranks(ClanEventsConfig config, RankData[] ranks) {
        this.ranks = ranks;
        // Google sheet API
        sheet.setKey(config.apiKey());
        sheet.setSheetId(config.sheetId());

        //Set the color
        color1 = "#"+Integer.toHexString(config.col1color().getRGB()).substring(2);
        color2 = "#"+Integer.toHexString(config.col2color().getRGB()).substring(2);

        cmButtonPanel.add(createRankButton("trial", "⏱", "Trial"), BorderLayout.WEST);
        cmButtonPanel.add(createRankButton("trial", "😊", "Junior Member"), BorderLayout.WEST);
        cmButtonPanel.add(createRankButton("trial", "➀", "Member"), BorderLayout.WEST);
        cmButtonPanel.add(createRankButton("trial", "➁", "Senior Member"), BorderLayout.WEST);
        cmButtonPanel.add(createRankButton("trial", "➂", "Elite Member"), BorderLayout.WEST);
        cmButtonPanel.add(createRankButton("trial", "⭐", "Lieutenant"), BorderLayout.WEST);

        ssArea.add(cmButtonPanel, BorderLayout.NORTH);


        ssText.setText(getSheetDataFormatted(sheet, "cmmen"));
        ssArea.add(ssText);
    }

    public JPanel getLayout() {
        return ssArea;
    }

    private JButton createRankButton(String name, String label, String tooltip )
    {
        final JButton button = new JButton(label);
        button.setToolTipText(tooltip);
        button.setPreferredSize(new Dimension(34, 34));
        button.setFont(new Font("Arial Unicode MS", Font.PLAIN, 13));
        button.setFocusable(false);
        button.setMargin(new Insets(0, 0, 0, 0));
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

                }
            }
        });

        return button;
    }

    private String getSheetDataFormatted(GoogleSheet sheet, String field)
    {
        try
        {
            String data = "";
            java.util.List<java.util.List<Object>> values = sheet.getValues(field);

            if (values == null || values.isEmpty()) {
                System.out.println("No data found.");
            } else {
                data += "<html><table width=230>";
                int i = 0;
                for (java.util.List row : values) {
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
        }catch (Exception ioException)
        {
            return "Could not load google sheet data.";
        }
    }
}
