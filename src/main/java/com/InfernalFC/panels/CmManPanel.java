package com.InfernalFC.panels;

import com.InfernalFC.InfernalFCConfig;
import com.InfernalFC.InfernalFCPanel;
import com.InfernalFC.helpers.InventoryManager;
import com.InfernalFC.helpers.ResourceManager;
import com.InfernalFC.models.CmManData;
import com.google.inject.Provider;
import lombok.SneakyThrows;
import net.runelite.client.ui.ColorScheme;
import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;

public class CmManPanel extends JPanel {
    @Inject()
    private Provider<InfernalFCPanel> infernalFCPanel;
    private final InventoryManager inventoryManager;
    private final ResourceManager resourceManager;

    private JPanel cmButtonPanel = new JPanel();
    private JPanel itemPanel = new JPanel();
    private String color1;
    private String color2;
    private int cmMan = 3;
    private CmManData data;

    @Inject
    private CmManPanel(InfernalFCConfig config, CmManData data, ResourceManager resourceManager, InventoryManager inventoryManager) {
        this.resourceManager = resourceManager;
        this.inventoryManager = inventoryManager;
        this.data = data;
        //Set the color
        color1 = "#"+Integer.toHexString(config.col1color().getRGB()).substring(2);
        color2 = "#"+Integer.toHexString(config.col2color().getRGB()).substring(2);

        for (int i = 3; i < 10; i++) {
            cmButtonPanel.add(createCmButton(i), BorderLayout.WEST);
        }

        this.setPreferredSize(new Dimension(200, 400));
        this.add(cmButtonPanel, BorderLayout.NORTH);

        this.add(createTitleLabel("Prep numbers"));

        itemPanel.setLayout(new GridLayout(0,4));
        itemPanel.setPreferredSize(new Dimension(200, 100));
        setItemPanel();

        this.add(itemPanel);

        this.add(createTitleLabel("Roles"));

        this.add(createRoleButton("Surge 1"));
        this.add(createRoleButton("Surge 2"));
        this.add(createRoleButton("Prep"));
        this.add(createRoleButton("Chinner"));
        this.add(createRoleButton("Crosser"));
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
                    setItemPanel();

                }
            }
        });

        return label;
    }

    private JButton createRoleButton(String role)
    {
        final JButton label = new JButton(role);
        label.setPreferredSize(new Dimension(200, 30));
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
                    infernalFCPanel.get().cmManRolePanel.setRole(role);
                    infernalFCPanel.get().SwitchPanel("cmmanrole");
                }
            }
        });

        return label;
    }

    private void setItemPanel() {
        try {
            String[][] cmManData = this.data.itemMapping.get(String.valueOf(cmMan));

            itemPanel.removeAll();
            for (String[] row : cmManData) {
                itemPanel.add(createItemLabel((String) Array.get(row, 0), (String) Array.get(row, 1)));
            }
            itemPanel.updateUI();
            this.updateUI();
        } catch (Exception e) {

        }
    }

    private JLabel createItemLabel(String name, String quantity) {
        JLabel label = new JLabel();
        label.setToolTipText(name);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBorder(BorderFactory.createLineBorder(ColorScheme.DARK_GRAY_COLOR, 2));
        label.setBackground(new Color(39, 25, 25));

        if (inventoryManager.HasItem(name)) {
            label.setOpaque(false);
        } else {
            label.setOpaque(true);
        }

        try {
            Runnable task = () -> {
                Icon icon  = resourceManager.GetItemImage(name);
                label.setIcon(getLabeledIcon(icon, quantity));
            };

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
        return new ImageIcon(img.getScaledInstance(w, h,  java.awt.Image.SCALE_SMOOTH));
    }

    private JLabel createTitleLabel(String title) {
        JLabel label = new JLabel(title, SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(200, 20));
        label.setForeground(Color.yellow);
        return label;
    }
}
