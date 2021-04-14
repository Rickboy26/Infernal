package com.InfernalFC.panels;

import net.runelite.api.Client;
import net.runelite.api.InventoryID;
import net.runelite.api.Item;
import net.runelite.api.ItemContainer;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Singleton
public class InventoryManager {
    Item[] items;
    private final Client client;
    public Map<String, int[]> itemMapping;

    @Inject
    private InventoryManager(Client client) {
        this.client = client;

        itemMapping = Stream.of(new Object[][] {
                { "Void ranger helm", new int[] { 11664, 24184 } },
                { "Primordial boots", new int[] { 13239, 13240 } },
                { "Scythe of Vitur", new int[] { 22325, 22486, 22487, 22664 } },
                { "Barrows gloves", new int[] { 7462, 23593, 23594 } },
                { "Necklace of anguish", new int[] { 19547, 19548, 22249 } },
                { "Bandos godsword", new int[] { 11804, 11805, 20370 } },
                { "BCP / Torso", new int[] { 10551, 24175, 11832,  11833 } },
                { "Imbued Magic Cape", new int[] { 21776, 21780, 21784, 21791, 21793, 23603, 23604, 23605, 23606, 23607, 23608, 24232, 24233, 24234, 24248, 24249, 24250} },
                { "Avernic defender", new int[] { 22322, 24186 } },
                { "Ferocious gloves", new int[] { 22981 } },
                { "Dragon warhammer", new int[] { 20785, 13577, 13576 } },
                { "Occult necklace", new int[] { 12002, 12003, 19720, 13654 } },
                { "Berserker ring (i)", new int[] { 11773, 25264 } },
                { "Twisted bow", new int[] { 20997, 20998 } },
                { "Void top (Elite)", new int[] { 13072, 24178 } },
                { "Neitiznot faceguard", new int[] { 24271 } },
                { "Tormented bracelet", new int[] { 19544, 19545, 23444 } },
                { "Ava's assembler", new int[] { 22109, 24222, 21898, 24135 } },
                { "Tent / Rapier / Blade", new int[] { 12006, 22324, 22483, 23628, 23629, 23995, 23997, 23998, 24551, 24553 } },
                { "Trident/Sanguinesti", new int[] { 12899, 12900, 12901, 22292, 22294, 22295, 22323, 22481, 22482} },
                { "Infernal cape", new int[] { 21284, 21285, 21295, 21297, 23623, 24133, 24224 } },
                { "Toxic blowpipe", new int[] { 12924, 12925, 12926 } },
                { "Void knight gloves", new int[] { 8842, 24182 } },
                { "Void robe (Elite)", new int[] { 13073, 24180 } },
                { "Amulet of torture", new int[] { 19533, 19554, 20366 } },
                { "Infernal/Fire Cape", new int[] { 21284, 21285, 21295, 21297, 23623, 24133, 24224, 6570, 10566, 13329, 24134, 24223 } },
                { "Scythe / Tbow", new int[] { 22325, 22486, 22487, 22664, 20997, 20998 } },
                { "Scythe + 250m / Tbow", new int[] { 22325, 22486, 22487, 22664, 20997, 20998, 1004, 8899 } },

        }).collect(Collectors.toMap(data -> (String) data[0], data -> (int[]) data[1]));
    }

    public void UpdateInventoryItems() {
        ItemContainer container = client.getItemContainer(InventoryID.INVENTORY);
        if (container != null) {
            items = container.getItems();
        }
    }

    public boolean HasItem(String name) {
        int[] reqItems = itemMapping.get(name);
        if (items == null) {
            return true;
        }
        for(Item item : items) {
            if (Arrays.stream(reqItems).anyMatch(x -> x == item.getId())) {
                return true;
            }
        }
        return false;
    }


}
