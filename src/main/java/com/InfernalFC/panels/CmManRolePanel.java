package com.InfernalFC.panels;

import com.InfernalFC.InfernalFCConfig;
import com.InfernalFC.InfernalFCPanel;
import com.InfernalFC.helpers.InventoryManager;
import com.InfernalFC.helpers.ResourceManager;
import com.InfernalFC.models.CmManData;
import com.InfernalFC.models.CmRoleEquipment;
import com.google.inject.Provider;
import lombok.Getter;
import lombok.SneakyThrows;
import net.runelite.client.ui.ColorScheme;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;

public class CmManRolePanel extends JPanel {
    @Inject()
    private Provider<InfernalFCPanel> infernalFCPanel;
    private final InventoryManager inventoryManager;
    private final ResourceManager resourceManager;

    private String role = "Role 1";

    private JPanel itemPanel = new JPanel();
    private JPanel equipmentPanel = new JPanel();
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

        this.setPreferredSize(new Dimension(200, 400));

        this.add(createBackButton());

        equipmentPanel.setLayout(new GridLayout(5,3));
        equipmentPanel.setPreferredSize(new Dimension(200, 250));
        setEquipmentPanel();

        itemPanel.setLayout(new GridLayout(7,4));
        itemPanel.setPreferredSize(new Dimension(200, 100));
        setItemPanel();

        this.add(equipmentPanel);
        this.add(itemPanel);

        this.updateUI();
    }

    public void setRole(String role) {
        this.role = role;
        setEquipmentPanel();
    }

    private JButton createBackButton()
    {
        final JButton label = new JButton("<");
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

    private void setItemPanel() {

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

    private Icon getLabeledIcon(Icon oldIcon, String text) {
        int w = oldIcon.getIconWidth();
        int h = oldIcon.getIconHeight();
        BufferedImage img = new BufferedImage(
                w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        oldIcon.paintIcon(null, g2d, 0, 0);
        g2d.setPaint(Color.yellow);
        g2d.drawString(text, 0, 10);
        g2d.dispose();
        return new ImageIcon(img.getScaledInstance(30, 30,  Image.SCALE_SMOOTH));
    }
}
