package com.InfernalFC.helpers;

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
                { "Ancestral robe bottom", "Ancestral_robe_bottom_detail.png" },
                { "Ancestral robe top", "Ancestral_robe_top_detail.png" },
                { "Anglerfish", "Anglerfish_detail.png" },
                { "Antidote++ (1)", "Antidote_detail.png" },
                { "Arcane spirit shield", "Arcane_spirit_shield_detail.png" },
                { "Armadyl chainskirt", "Armadyl_chainskirt_detail.png" },
                { "Armadyl chestplate", "Armadyl_chestplate_detail.png" },
                { "Armadyl Helmet", "Armadyl_helmet_detail.png" },
                { "Astral rune", "Astral_rune_detail.png" },
                { "Bandos chestplate", "Bandos_chestplate_detail.png" },
                { "Bandos tassets", "Bandos_tassets_detail.png" },
                { "Brimstone ring", "Brimstone_ring_detail.png" },
                { "Chinchompa", "Chinchompa_detail.png" },
                { "Crystal pickaxe", "Crystal_pickaxe_detail.png" },
                { "Death rune", "Death_rune_detail.png" },
                { "Dragon arrows", "Dragon_arrow_detail.png" },
                { "Dragon claws", "Dragon_claws_detail.png" },
                { "Earth rune", "Earth_rune_detail.png" },
                { "Harmonised nightmare staff", "Harmonised_nightmare_staff_detail.png" },
                { "Inquisitor's great helm", "Inq_great_helm_detail.png" },
                { "Inquisitor's hauberk", "Inq_hauberk_detail.png" },
                { "Inquisitor's plateskirt", "Inq_plateskirt_detail.png" },
                { "Lava rune", "Lava_rune_detail.png" },
                { "Law rune", "Law_rune_detail.png" },
                { "Mist rune", "Mist_rune_detail.png" },
                { "Pegasian boots", "Pegasian_boots_detail.png" },
                { "Rune pouch", "Rune_pouch_detail.png" },
                { "Salve amulet (ei)", "Salve_amulet_detail.png" },
                { "Sanguinesti staff", "Sanguinesti_staff_detail.png" },
                { "Saradomin brew (4)", "Saradomin_brew_detail.png" },
                { "Stamina potion (4)", "Stamina_potion_detail.png" },
                { "Stamina potion (1)", "Stamina_potion1_detail.png" },
                { "Super combat potion (1)", "Super_combat_potion_detail.png" },
                { "Super restore (4)", "Super_restore_detail.png" },
                { "Tome of Fire", "Tome_of_fire_detail.png" },
                { "Vengeance", "Vengeance_icon.png" },
                { "Water rune", "Water_rune_detail.png" },
                { "Wrath rune", "Wrath_rune_detail.png" },
                { "Buchu leaf", "Buchu_leaf_icon.png" },
                { "Golpar leaf", "Golpar_icon.png" },
                { "Noxifer leaf", "Noxifer_icon.png" },
                { "Endarkened juice", "Endarkened_juice_icon.png" },
                { "Stinkhorn mushroom", "Stinkhorn_mushroom_icon.png" },
                { "Cicely", "Cicely_icon.png" },
                { "Humidify", "Humidify_icon.png" },
                { "Cavern grubs", "Cavern_grubs_icon.png" },

        }).collect(Collectors.toMap(data -> data[0], data -> data[1]));
    }

    public Icon GetItemImage(String item) {
        String filename = itemMapping.get(item);
        if (filename.endsWith(".gif")) {
            return new ImageIcon(InfernalFCPlugin.class.getResource("items/" + itemMapping.get(item)));
        } else {
            BufferedImage image = ImageUtil.loadImageResource(InfernalFCPlugin.class, "items/" + itemMapping.get(item));
            int[] di = calculateDimension(image.getHeight(), image.getWidth());
            return new ImageIcon(image.getScaledInstance(di[0], di[1],  java.awt.Image.SCALE_SMOOTH));
        }
    }

    public Icon GetIconImage(String name, int size) {
        BufferedImage image = ImageUtil.loadImageResource(InfernalFCPlugin.class, "icons/" + name);
        if (size > 0) {
            return new ImageIcon(image.getScaledInstance(size, size,  java.awt.Image.SCALE_SMOOTH));
        } else {
            return new ImageIcon(image);
        }
    }

    private int[] calculateDimension(int height, int width) {
        if (width > height) {
            int newWidth = (int) Math.round((double) height / (double) width * 30);
            return new int[] {30, newWidth};
        } else {
            int newHeight = (int) Math.round((double) width / (double) height * 30);
            return new int[] {newHeight, 30};
        }
    }

}
