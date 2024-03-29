package com.InfernalFC.panels;

import com.InfernalFC.helpers.DataManager;
import com.InfernalFC.helpers.InventoryManager;
import com.InfernalFC.helpers.ResourceManager;
import com.InfernalFC.models.ItemData;
import com.InfernalFC.models.RankData;
import lombok.SneakyThrows;
import net.runelite.client.ui.ColorScheme;
import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Comparator;

public class RanksPanel extends JPanel{
    private final DataManager dataManager;
    private final ResourceManager resourceManager;
    private final InventoryManager inventoryManager;
    private JPanel cmButtonPanel = new JPanel();
    private JPanel itemPanel = new JPanel();
    private JPanel pointsPanel = new JPanel();
    private RankData selectedRank;
    private JLabel rankName = new JLabel("");

    @Inject
    private RanksPanel(DataManager dataManager, ResourceManager resourceManager, InventoryManager inventoryManager) {
        this.dataManager = dataManager;
        this.resourceManager = resourceManager;
        this.inventoryManager = inventoryManager;

        cmButtonPanel.add(createRankButton("Trial_icon.png", "Trial"), BorderLayout.WEST);
        cmButtonPanel.add(createRankButton("Recruit_icon.png", "Recruit"), BorderLayout.WEST);
        cmButtonPanel.add(createRankButton("Peon_icon.png", "Pawn"), BorderLayout.WEST);
        cmButtonPanel.add(createRankButton("Corporal_icon.png", "Corporal"), BorderLayout.WEST);
        cmButtonPanel.add(createRankButton("Sergeant_icon.png", "Sergeant"), BorderLayout.WEST);
        cmButtonPanel.add(createRankButton("Cadet_icon.png", "Cadet"), BorderLayout.WEST);

        cmButtonPanel.add(createRankButton("Officer_icon.png", "Officer"), BorderLayout.WEST);
        cmButtonPanel.add(createRankButton("Commander_icon.png", "Commander"), BorderLayout.WEST);
        cmButtonPanel.add(createRankButton("Colonel_icon.png", "Colonel"), BorderLayout.WEST);
        cmButtonPanel.add(createRankButton("Brigadier_icon.png", "Brigadier"), BorderLayout.WEST);
        cmButtonPanel.add(createRankButton("Admiral_icon.png", "Admiral"), BorderLayout.WEST);
        cmButtonPanel.add(createRankButton("Marshal_icon.png", "Marshal"), BorderLayout.WEST);


//        this.add(cmButtonPanel, BorderLayout.NORTH);

        itemPanel.setLayout(new GridLayout(0,4));
        pointsPanel.setLayout(new GridLayout(0,2));
        cmButtonPanel.setLayout(new GridLayout(0,6));

        rankName.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(cmButtonPanel);
        this.add(rankName);
        this.add(itemPanel);
        this.add(pointsPanel);
        this.setPreferredSize(new Dimension(200, 860));

    }

    public void rankChange(String rankName) {

        this.rankName.setText(rankName);
        RankData[] ranks = dataManager.GetRankData();
        selectedRank = Arrays.stream(ranks).filter(rank -> rankName.equals(rank.getName())).findFirst().orElse(null);

        int height = (int) Math.ceil((double) selectedRank.items.length / 4) * 50;
        itemPanel.setPreferredSize(new Dimension(200, height));
        itemPanel.removeAll();

        inventoryManager.UpdateInventoryItems();

        Arrays.sort(selectedRank.getItems(), Comparator.comparing(ItemData::getName));
        for (ItemData item : selectedRank.getItems()) {
            itemPanel.add(createItemLabel(item));
        }

        pointsPanel.removeAll();

        pointsPanel.add(new JLabel());
        pointsPanel.add(new JLabel());

        pointsPanel.add(createHeaderLabel("Points"));
        pointsPanel.add(new JLabel());
        pointsPanel.add(createIconLabel("Total_points.png", selectedRank.getTotal() + " Total"));
        pointsPanel.add(createIconLabel("Pvm_points.png", selectedRank.getPvm() + " PvM"));
        pointsPanel.add(createIconLabel("Ehb_points.png", selectedRank.getTasks() + " Diaries tier 4"));
        pointsPanel.add(createIconLabel("Community_points.png", selectedRank.getCommunity() + " Community"));
        pointsPanel.add(new JLabel());
        pointsPanel.add(new JLabel());

        pointsPanel.add(createHeaderLabel("Skills"));
        pointsPanel.add(new JLabel());
        pointsPanel.add(createIconLabel("Req_level.png", selectedRank.getCombat() + " Combat"));
        pointsPanel.add(createIconLabel("Req_total.png", "1750+ Total"));
        pointsPanel.add(createIconLabel("Req_ranged.png", "99 Ranged"));
        pointsPanel.add(createIconLabel("Req_magic.png", selectedRank.getMagic() + " Magic"));
        pointsPanel.add(createIconLabel("Req_herb.png", selectedRank.getHerblore() + " Herblore"));
        pointsPanel.add(new JLabel());
        pointsPanel.add(new JLabel());
        pointsPanel.add(new JLabel());

        pointsPanel.add(createHeaderLabel("Misc"));
        pointsPanel.add(new JLabel());
        pointsPanel.add(createIconLabel("Req_box.png", "<html>Ornate Jewellery <br/>Box</html>"));
        pointsPanel.add(createIconLabel("Req_pool.png", "Ornate Pool"));
        pointsPanel.add(createIconLabel("Req_piety.png", "Piety"));
        pointsPanel.add(createIconLabel("Req_rigour.png", "Rigour"));
        pointsPanel.add(createIconLabel("Req_augury.png", "Augury"));
        pointsPanel.add(createIconLabel("Req_gold.png", selectedRank.getCoins() + "m PvM Gear"));
        pointsPanel.add(createIconLabel("Req_time.png", selectedRank.getMonths() + " Months in clan"));

        itemPanel.updateUI();
        this.updateUI();
    }

    public void updateItems() {
        inventoryManager.UpdateInventoryItems();
        for (Component component : itemPanel.getComponents()) {
            JLabel label = (JLabel) component;
            if (inventoryManager.HasItem(label.getToolTipText())) {
                label.setOpaque(false);
            } else {
                label.setOpaque(true);
            }
        }
        itemPanel.updateUI();
    }

    private JLabel createItemLabel(ItemData item) {
        JLabel label = new JLabel();
        label.setToolTipText(item.getName());
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBorder(BorderFactory.createLineBorder(ColorScheme.DARK_GRAY_COLOR, 2));
        label.setBackground(new Color(39, 25, 25));

        if (inventoryManager.HasItem(item.getName())) {
            label.setOpaque(false);
        } else {
            label.setOpaque(true);
        }

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
        JLabel label = new JLabel(title);
        label.setFont(new Font("Arial", Font.BOLD, 10));
        try {
            Runnable task = () -> label.setIcon(resourceManager.GetIconImage(name, 20));

            Thread thread = new Thread(task);
            thread.start();

        } catch (Exception e) {
            System.out.println(e);
        }

        return label;
    }

    private JLabel createHeaderLabel(String name) {
        JLabel label = new JLabel(name);
        label.setFont(new Font("Arial", Font.BOLD, 16));

        return label;
    }

    private JButton createRankButton(String iconName, String tooltip )
    {
        Icon icon = resourceManager.GetIconImage(iconName, 0);
        final JButton button = new JButton(icon);

        button.setToolTipText(tooltip);
        button.setPreferredSize(new Dimension(38, 38));
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
