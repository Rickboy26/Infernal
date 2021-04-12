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
    private JPanel pointsPanel = new JPanel();
    private RankData selectedRank;
    private JLabel rankName = new JLabel("");

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

        itemPanel.setLayout(new GridLayout(0,5));
        pointsPanel.setLayout(new BoxLayout(pointsPanel, BoxLayout.PAGE_AXIS));

        rankName.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(rankName);
        this.add(itemPanel);
        this.add(pointsPanel);
        this.setPreferredSize(new Dimension(200, 600));

    }


    public void rankChange(String rankName) {
        this.rankName.setText(rankName);
        RankData[] ranks = dataManager.GetRankData();
        selectedRank = Arrays.stream(ranks).filter(rank -> rankName.equals(rank.getName())).findFirst().orElse(null);

        int height = (int) Math.ceil(selectedRank.items.length / 5) * 50;
        itemPanel.setPreferredSize(new Dimension(200, height));
        itemPanel.removeAll();

        for (ItemData item : selectedRank.getItems()) {
            itemPanel.add(createItemLabel(item));
        }

        pointsPanel.removeAll();
        pointsPanel.add(createIconLabel("Total_points.png", selectedRank.getTotal() + " Total"));
        pointsPanel.add(createIconLabel("Pvm_points.png", selectedRank.getPvm() + " PvM"));
        pointsPanel.add(createIconLabel("Community_points.png", selectedRank.getCommunity() + " Comunnity"));
        pointsPanel.add(createIconLabel("Ehb_points.png", selectedRank.getEhb() + " EHB"));

        pointsPanel.add(createIconLabel("Req_level.png", selectedRank.getCombat() + " Combat"));
        pointsPanel.add(createIconLabel("Req_total.png", "1750+ Total"));
        pointsPanel.add(createIconLabel("Req_ranged.png", "99 Ranged"));
        pointsPanel.add(createIconLabel("Req_magic.png", selectedRank.getMagic() + " Magic"));
        pointsPanel.add(createIconLabel("Req_herb.png", selectedRank.getHerblore() + " Herblore"));

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

    private JLabel createIconLabel(String name, String title) {
        JLabel label = new JLabel();
        label.setText(title);
        try {
            Runnable task = () -> label.setIcon(resourceManager.GetIconImage(name));

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
