package com.InfernalFC.panels;

import com.InfernalFC.InfernalFCPlugin;
import lombok.SneakyThrows;
import net.runelite.client.ui.ColorScheme;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;


public class RanksPanel extends JPanel{
    private JPanel cmButtonPanel = new JPanel();
    private JPanel itemPanel = new JPanel();
    private RankData[] ranks;
    private RankData selectedRank;

    @Inject
    private RanksPanel(InfernalFCPlugin infernalFCPlugin) {
        this.ranks = infernalFCPlugin.getRanks();

        cmButtonPanel.add(createRankButton("⏱", "Trial"), BorderLayout.WEST);
        cmButtonPanel.add(createRankButton("☻", "Junior Member"), BorderLayout.WEST);
        cmButtonPanel.add(createRankButton("➀", "Member"), BorderLayout.WEST);
        cmButtonPanel.add(createRankButton("➁", "Senior Member"), BorderLayout.WEST);
        cmButtonPanel.add(createRankButton("➂", "Elite Member"), BorderLayout.WEST);
        cmButtonPanel.add(createRankButton("⭐", "Lieutenant"), BorderLayout.WEST);

        this.add(cmButtonPanel, BorderLayout.NORTH);

        GridLayout gridLayout = new GridLayout(0,5);
        itemPanel.setLayout(gridLayout);

        this.add(itemPanel);
        this.setPreferredSize(new Dimension(200, 400));
    }


    public void rankChange(String rankName) {
        selectedRank = Arrays.stream(ranks).filter(data -> rankName.equals(data.getName())).findFirst().orElse(null);

        int height = (int) Math.ceil(selectedRank.items.length / 5) * 50;
        itemPanel.setPreferredSize(new Dimension(200, height));
        itemPanel.removeAll();

        for (ItemData item : selectedRank.getItems()) {
            itemPanel.add(createItemLabel(item));
        }

        itemPanel.updateUI();
        this.updateUI();
    }

    private JLabel createItemLabel(ItemData item) {
        JLabel label = new JLabel();
        label.setToolTipText(item.getName());
        try {
            URL url = new URL(item.getArtwork());
            final HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestProperty("User-Agent", "RuneLite/InfernalFC plugin");
            String type = connection.getContentType();
            InputStream stream = connection.getInputStream();


            if (type.equals("image/gif")) {
                //workaround for gif images
                final BufferedImage image = ImageIO.read(stream);
            } else {
                final BufferedImage image = ImageIO.read(stream);
                Image img = image.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH);
                label.setIcon(new ImageIcon(img));

            }
        } catch (Exception e) {
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
                    rankChange(tooltip);
                }
            }
        });

        return button;
    }
}
