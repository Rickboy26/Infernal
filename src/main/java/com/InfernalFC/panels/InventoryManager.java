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
                { "Void ranger helm", new int[] { 1, 2 } },
                { "Primordial boots", new int[] { 1, 2 } },
                { "Scythe of Vitur", new int[] { 1, 2 } },
                { "Barrows gloves", new int[] { 1, 2 } },
                { "Necklace of anguish", new int[] { 1, 2 } },
                { "Bandos godsword", new int[] { 1, 2 } },
                { "BCP / Torso", new int[] { 1, 2 } },
                { "Imbued Magic Cape", new int[] { 1, 2 } },
                { "Avernic defender", new int[] { 1, 2 } },
                { "Ferocious gloves", new int[] { 1, 2 } },
                { "Dragon warhammer", new int[] { 1, 2 } },
                { "Occult necklace", new int[] { 1, 2 } },
                { "Berserker ring (i)", new int[] { 1, 2 } },
                { "Twisted bow", new int[] { 1, 2 } },
                { "Void top (Elite)", new int[] { 1, 2 } },
                { "Neitiznot faceguard", new int[] { 1, 2 } },
                { "Tormented bracelet", new int[] { 1, 2 } },
                { "Ava's assembler", new int[] { 1, 2 } },
                { "Tent / Rapier / Blade", new int[] { 1, 2 } },
                { "Trident/Sanguinesti", new int[] { 1, 2 } },
                { "Infernal cape", new int[] { 1, 2 } },
                { "Toxic blowpipe", new int[] { 1, 2 } },
                { "Void knight gloves", new int[] { 1, 2 } },
                { "Void robe (Elite)", new int[] { 1, 2 } },
                { "Amulet of torture", new int[] { 1, 2 } },
                { "Infernal/Fire Cape", new int[] { 1, 2 } },
                { "Scythe / Tbow", new int[] { 1, 2 } },
                { "Scythe + 250m / Tbow", new int[] { 1, 2 } },

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
        for(Item item : items) {
            return Arrays.stream(reqItems).anyMatch(x -> x == item.getId());
        }
        return false;
    }


}
