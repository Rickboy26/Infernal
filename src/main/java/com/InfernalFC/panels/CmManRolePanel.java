package com.InfernalFC.panels;

import com.InfernalFC.InfernalFCConfig;
import com.InfernalFC.InfernalFCPanel;
import com.InfernalFC.helpers.InventoryManager;
import com.InfernalFC.helpers.ResourceManager;
import com.InfernalFC.models.CmManData;
import com.InfernalFC.models.CmRoleEquipment;
import com.InfernalFC.models.ItemData;
import com.google.inject.Provider;
import lombok.SneakyThrows;
import net.runelite.client.ui.ColorScheme;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class CmManRolePanel extends JPanel {
    @Inject()
    private Provider<InfernalFCPanel> infernalFCPanel;
    private final InventoryManager inventoryManager;
    private final ResourceManager resourceManager;

    private String role = "Surge BGS";

    private JPanel titlePanel = new JPanel();
    private JPanel equipmentPanel = new JPanel();
    private JPanel inventoryPannel = new JPanel();
    private JPanel runePanel = new JPanel();
    private JPanel prepotPanel = new JPanel();
    private JLabel notes = new JLabel("", SwingConstants.LEFT);
    private String color1;
    private String color2;
    private CmManData data;

    @Inject
    private CmManRolePanel(InfernalFCConfig config, CmManData data, ResourceManager resourceManager, InventoryManager inventoryManager) {
        this.resourceManager = resourceManager;
        this.inventoryManager = inventoryManager;
        this.data = data;
        //Set the color
        color1 = "#"+Integer.toHexString(config.col1color().getRGB()).substring(2);
        color2 = "#"+Integer.toHexString(config.col2color().getRGB()).substring(2);

        this.setPreferredSize(new Dimension(200, 1200));

        equipmentPanel.setLayout(new GridLayout(5,3));
        equipmentPanel.setPreferredSize(new Dimension(150, 250));

        inventoryPannel.setLayout(new GridLayout(0,4));
        inventoryPannel.setPreferredSize(new Dimension(200, 350));

        runePanel.setLayout(new GridLayout(1,3));
        runePanel.setPreferredSize(new Dimension(150, 50));

        prepotPanel.setLayout(new GridLayout(2,3));
        prepotPanel.setPreferredSize(new Dimension(150, 100));

        notes.setBackground(ColorScheme.DARKER_GRAY_COLOR);
        notes.setOpaque(true);


        this.add(titlePanel);
        this.add(createTitleLabel("Equipment"));
        this.add(equipmentPanel);

        this.add(createTitleLabel("Inventory"));
        this.add(inventoryPannel);

        this.add(createTitleLabel("Runes"));
        this.add(runePanel);

        this.add(createTitleLabel("Prepot"));
        this.add(prepotPanel);

        this.add(createTitleLabel("Notes"));
        this.add(notes);

        this.updateUI();
    }

    public void setRole(String role) {
        this.role = role;
        this.setTitleRow();
        setEquipmentPanel();
        setInventoryPannel();
        setRunePannel();
        setPrepotPanel();
        setNotes();
    }

    private JButton createBackButton()
    {
        final JButton label = new JButton("<");
        label.setToolTipText("Back");
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
                    infernalFCPanel.get().SwitchPanel("cmman");
                }
            }
        });

        return label;
    }

    private void setEquipmentPanel() {
        equipmentPanel.removeAll();
        CmRoleEquipment equipment = data.roleGearMapping.get(role);
        equipmentPanel.add(createDummyLabel());
        equipmentPanel.add(createItemLabel(equipment.helmet.name));
        equipmentPanel.add(createDummyLabel());

        equipmentPanel.add(createItemLabel(equipment.cape.name));
        equipmentPanel.add(createItemLabel(equipment.necklace.name));
        equipmentPanel.add(createItemLabel(equipment.ammo.name));

        equipmentPanel.add(createItemLabel(equipment.weapon.name));
        equipmentPanel.add(createItemLabel(equipment.torso.name));
        equipmentPanel.add(createItemLabel(equipment.shield.name));

        equipmentPanel.add(createDummyLabel());
        equipmentPanel.add(createItemLabel(equipment.pants.name));
        equipmentPanel.add(createDummyLabel());

        equipmentPanel.add(createItemLabel(equipment.gloves.name));
        equipmentPanel.add(createItemLabel(equipment.boots.name));
        equipmentPanel.add(createItemLabel(equipment.ring.name));

        equipmentPanel.updateUI();
    }

    private void setTitleRow() {
        titlePanel.removeAll();

        titlePanel.add(createBackButton());

        JLabel title = new JLabel(role, SwingConstants.CENTER);
        title.setPreferredSize(new Dimension(150, 26));
        title.setForeground(Color.yellow);
        title.setFont(new Font("Serif", Font.BOLD, 14));
        titlePanel.add(title);

        JLabel dummy = new JLabel();
        dummy.setPreferredSize(new Dimension(26, 26));
        titlePanel.add(dummy);
    }

    private void setInventoryPannel() {
        inventoryPannel.removeAll();
        ItemData[] items = data.roleInventoryMapping.get(role);

        for (ItemData item : items) {
            inventoryPannel.add(createItemLabel(item.name));
        }
        int rest = 28 - items.length;

        for(int i = 0; i < rest; ++i) {
            inventoryPannel.add(createEmptyLabel());
        }

        inventoryPannel.updateUI();
    }

    private void setRunePannel() {
        runePanel.removeAll();
        ItemData[] items = data.roleRuneMapping.get(role);

        for (ItemData item : items) {
            runePanel.add(createItemLabel(item.name));
        }

        runePanel.updateUI();
    }

    private void setPrepotPanel() {
        prepotPanel.removeAll();
        ItemData[] items = data.rolePrepotMapping.get(role);

        for (ItemData item : items) {
            prepotPanel.add(createItemLabel(item.name));
        }
        int rest = 6 - items.length;

        for(int i = 0; i < rest; ++i) {
            prepotPanel.add(createEmptyLabel());
        }

        prepotPanel.updateUI();
    }

    private void setNotes() {
        String note = data.roleNoteMapping.get(role);
        notes.setText("<html><p style=\"width:150px\">" +note +"</p></html>");
    }

    private JLabel createTitleLabel(String title) {
        JLabel label = new JLabel(title, SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(200, 20));
        label.setForeground(Color.yellow);
        return label;
    }

    private JLabel createDummyLabel() {
        JLabel label = new JLabel();
        label.setMinimumSize(new Dimension(30, 30));
        label.setMaximumSize(new Dimension(30, 30));
        label.setBorder(BorderFactory.createLineBorder(ColorScheme.DARK_GRAY_COLOR, 2));
        return label;
    }

    private JLabel createEmptyLabel() {
        JLabel label = new JLabel();
        label.setMinimumSize(new Dimension(30, 30));
        label.setMaximumSize(new Dimension(30, 30));
        label.setBackground(ColorScheme.DARKER_GRAY_COLOR);
        label.setBorder(BorderFactory.createLineBorder(ColorScheme.DARK_GRAY_COLOR, 2));
        label.setOpaque(true);
        return label;
    }

    private JLabel createItemLabel(String name) {
        JLabel label = new JLabel();
        label.setToolTipText(name);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBorder(BorderFactory.createLineBorder(ColorScheme.DARK_GRAY_COLOR, 2));
        label.setOpaque(true);

        if (inventoryManager.HasItem(name)) {
            label.setBackground(ColorScheme.DARKER_GRAY_COLOR);
        } else {
            label.setBackground(new Color(39, 25, 25));
        }

        try {
            Runnable task = () -> label.setIcon(resourceManager.GetItemImage(name));

            Thread thread = new Thread(task);
            thread.start();

        } catch (Exception e) {
            System.out.println(e);
        }

        return label;
    }
}
