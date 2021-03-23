package com.clanevents.panels;

import com.clanevents.ClanEventsConfig;
import com.clanevents.GoogleSheet;
import lombok.SneakyThrows;
import net.runelite.client.ui.ColorScheme;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;


public class Ranks {
    private final JPanel ssArea = new JPanel();
    private JPanel cmButtonPanel = new JPanel();
    private final GoogleSheet sheet = new GoogleSheet();
    private String color1;
    private String color2;
    private int cmMan = 3;
    private RankData[] ranks;
    private RankData selectedRank;

    public Ranks(ClanEventsConfig config, RankData[] ranks) {
        this.ranks = ranks;
        selectedRank = Arrays.stream(ranks).filter(data -> "Trial".equals(data.getName())).findFirst().orElse(null);
        // Google sheet API
        sheet.setKey(config.apiKey());
        sheet.setSheetId(config.sheetId());

        //Set the color
        color1 = "#"+Integer.toHexString(config.col1color().getRGB()).substring(2);
        color2 = "#"+Integer.toHexString(config.col2color().getRGB()).substring(2);

        cmButtonPanel.add(createRankButton("Trial", "⏱"), BorderLayout.WEST);
        cmButtonPanel.add(createRankButton("Junior Member", "☻"), BorderLayout.WEST);
        cmButtonPanel.add(createRankButton("Member", "➀"), BorderLayout.WEST);
        cmButtonPanel.add(createRankButton("Senior Member", "➁"), BorderLayout.WEST);
        cmButtonPanel.add(createRankButton("Elite Member", "➂"), BorderLayout.WEST);
        cmButtonPanel.add(createRankButton("Lieutenant", "⭐"), BorderLayout.WEST);

        ssArea.add(cmButtonPanel, BorderLayout.NORTH);

        for (ItemData item : selectedRank.getItems()) {
            ssArea.add(createItemLabel(item));
        }

        ssArea.setPreferredSize(new Dimension(200, 700));
    }

    public JPanel getLayout() {
        return ssArea;
    }

    private JLabel createItemLabel(ItemData item) {
        JLabel label = new JLabel();
        label.setBorder(new EmptyBorder(5, 5, 5, 5));
        label.setToolTipText(item.getName());
        try {
            URL url = new URL(item.getArtwork());
            final HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestProperty(
                    "User-Agent",
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
            String type = connection.getContentType();
            InputStream stream = connection.getInputStream();


            if (type.equals("image/gif")) {
                //workaround for gif images
            } else {
                final BufferedImage image = ImageIO.read(stream);
                Image img = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
                label.setIcon(new ImageIcon(img));

            }
        } catch (IOException e) {
            System.out.println(e);
        }

        return label;
    }

    private JButton createRankButton(String name, String tooltip )
    {
        final JButton button = new JButton(name);
        button.setToolTipText(tooltip);
        button.setPreferredSize(new Dimension(33, 33));
        button.setFont(new Font("Arial Unicode MS", Font.PLAIN, 12));
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

                }
            }
        });

        return button;
    }
}
