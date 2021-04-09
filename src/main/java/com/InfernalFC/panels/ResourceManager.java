package com.InfernalFC.panels;

import com.InfernalFC.InfernalFCPlugin;
import net.runelite.client.util.ImageUtil;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ResourceManager {
    private final Map<String, String> itemMapping;

    @Inject
    private ResourceManager() {
        itemMapping = Stream.of(new String[][] {
                { "Void ranger helm", "Void_ranger_helm_detail.png" },
                { "Primordial boots", "Primordial_boots_detail.png" },
                { "Scythe of Vitur", "Scythe_of_vitur_detail.png" },
                { "Barrows gloves", "Barrows_gloves_detail.png" },
                { "Necklace of anguish", "Necklace_of_anguish_detail.png" },
                { "Bandos godsword", "Bandos_godsword_detail.png" },
                { "BCP / Torso", "Torso_bcp_detail.gif" },
                { "Imbued Magic Cape", "Imbued_magic_cape_detail.gif" },
                { "Avernic defender", "Avernic_defender_detail.png" },
                { "Ferocious gloves", "Ferocious_gloves_detail.png" },
                { "Dragon warhammer", "Dragon_warhammer_detail.png" },
                { "Occult necklace", "Occult_necklace_detail.png" },
                { "Berserker ring (i)", "Berserker_ring_(i)_detail.png" },
                { "Twisted bow", "Twisted_bow_detail.png" },
                { "Void top (Elite)", "Elite_void_top_detail.png" },
                { "Neitiznot faceguard", "Neitiznot_faceguard_detail.png" },
                { "Tormented bracelet", "Tormented_bracelet_detail.png" },
                { "Ava's assembler", "Ava's_assembler_detail.png" },
                { "Tent / Rapier / Blade", "Rapier_whip_blade_detail.gif" },
                { "Trident/Sanguinesti", "Sang_trid_detail.gif" },
                { "Infernal cape", "Inf_cape_detail.gif" },
                { "Toxic blowpipe", "Toxic_blowpipe_(empty)_detail.png" },
                { "Void knight gloves", "Void_knight_gloves_detail.png" },
                { "Void robe (Elite)", "Elite_void_robe_detail.png" },
                { "Amulet of torture", "Amulet_of_torture_detail.png" },
                { "Infernal/Fire Cape", "Fire_inf_detail.gif" },
                { "Scythe / Tbow", "Tbow_scythe_detail.gif" },
                { "Scythe + 250m / Tbow", "Tbow_scythe250_detail.gif" },
        }).collect(Collectors.toMap(data -> data[0], data -> data[1]));
    }

    public Icon GetItemImage(String item) {
        String filename = itemMapping.get(item);
        BufferedImage image = ImageUtil.loadImageResource(InfernalFCPlugin.class, "items\\" + itemMapping.get(item));
        if (filename.endsWith(".gif")) {
            return new ImageIcon(InfernalFCPlugin.class.getResource("\\items\\" + itemMapping.get(item)));
        } else {
            return new ImageIcon(image.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH));
        }
    }


}
