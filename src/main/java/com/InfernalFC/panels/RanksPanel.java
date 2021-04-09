package com.InfernalFC.panels;

import lombok.SneakyThrows;
import net.runelite.client.ui.ColorScheme;
import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;


public class RanksPanel extends JPanel{
    private final DataManager dataManager;
    private final ResourceManager resourceManager;
    private JPanel cmButtonPanel = new JPanel();
    private JPanel itemPanel = new JPanel();
    private RankData selectedRank;

    @Inject
    private RanksPanel(DataManager dataManager, ResourceManager resourceManager) {
        this.dataManager = dataManager;
        this.resourceManager = resourceManager;

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
        RankData[] ranks = dataManager.GetRankData();
        selectedRank = Arrays.stream(ranks).filter(rank -> rankName.equals(rank.getName())).findFirst().orElse(null);

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
            Runnable task = () -> label.setIcon(resourceManager.GetItemImage(item.getName()));

            Thread thread = new Thread(task);
            thread.start();

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
