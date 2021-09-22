package com.InfernalFC.models;

import javax.inject.Inject;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CmManData {
    public Map<String, String[][]> itemMapping;
    public Map<String, CmRoleEquipment> roleGearMapping;

    @Inject
    private CmManData() {
        itemMapping = Stream.of(new Object[][] {
                { "3", new String[][] {
                        { "Endarkened juice", "25" },
                        { "Stinkhorn mushroom", "12" },
                        { "Cicely", "1" },
                        { "Humidify", "2" },
                        { "Buchu leaf", "38" },
                        { "Golpar leaf", "0" },
                        { "Noxifer leaf", "0" },
                        { "Cavern grubs", "47" }
                } },
                { "4", new String[][] {
                        { "Endarkened juice", "30" },
                        { "Stinkhorn mushroom", "16" },
                        { "Cicely", "1" },
                        { "Humidify", "2" },
                        { "Buchu leaf", "47" },
                        { "Golpar leaf", "0" },
                        { "Noxifer leaf", "0" },
                        { "Cavern grubs", "63" }
                } },
                { "5", new String[][] {
                        { "Endarkened juice", "40" },
                        { "Stinkhorn mushroom", "20" },
                        { "Cicely", "2" },
                        { "Humidify", "3" },
                        { "Buchu leaf", "62" },
                        { "Golpar leaf", "0" },
                        { "Noxifer leaf", "1" },
                        { "Cavern grubs", "79" }
                } },
                { "6", new String[][] {
                        { "Endarkened juice", "42" },
                        { "Stinkhorn mushroom", "26" },
                        { "Cicely", "5" },
                        { "Humidify", "3" },
                        { "Buchu leaf", "67" },
                        { "Golpar leaf", "6" },
                        { "Noxifer leaf", "3" },
                        { "Cavern grubs", "95" }
                } },
                { "7", new String[][] {
                        { "Endarkened juice", "51" },
                        { "Stinkhorn mushroom", "30" },
                        { "Cicely", "5" },
                        { "Humidify", "4" },
                        { "Buchu leaf", "82" },
                        { "Golpar leaf", "6" },
                        { "Noxifer leaf", "3" },
                        { "Cavern grubs", "111" }
                } },
                { "8", new String[][] {
                        { "Endarkened juice", "56" },
                        { "Stinkhorn mushroom", "30" },
                        { "Cicely", "5" },
                        { "Humidify", "4" },
                        { "Buchu leaf", "86" },
                        { "Golpar leaf", "6" },
                        { "Noxifer leaf", "3" },
                        { "Cavern grubs", "127" }
                } },
                { "9", new String[][] {
                        { "Endarkened juice", "60" },
                        { "Stinkhorn mushroom", "40" },
                        { "Cicely", "11" },
                        { "Humidify", "6" },
                        { "Buchu leaf", "111" },
                        { "Golpar leaf", "12" },
                        { "Noxifer leaf", "5" },
                        { "Cavern grubs", "143" }
                } }
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (String[][]) data[1]));

        roleGearMapping = Stream.of(new Object[][] {
                { "Role 1", new CmRoleEquipment() {
                    {
                        helmet = new ItemData() { { name = "Void ranger helm";}};
                        cape = new ItemData() { { name = "Infernal cape";}};
                        necklace = new ItemData() { { name = "Necklace of anguish";}};
                        ammo = new ItemData() { { name = "Dragon arrows";}};
                        weapon = new ItemData() { { name = "Avernic defender";}};
                        torso = new ItemData() { { name = "Void top (Elite)";}};
                        shield = new ItemData() { { name = "Arcane spirit shield";}};
                        pants = new ItemData() { { name = "Void robe (Elite)";}};
                        gloves = new ItemData() { { name = "Barrows gloves";}};
                        boots = new ItemData() { { name = "Pegasian boots";}};
                        ring = new ItemData() { { name = "Berserker ring (i)";}};
                    }
                } },
                { "Role 2", new CmRoleEquipment() {
                    {
                        helmet = new ItemData() { { name = "Void ranger helm2";}};
                        cape = new ItemData() { { name = "Infernal cape";}};
                        necklace = new ItemData() { { name = "Necklace of anguish";}};
                        ammo = new ItemData() { { name = "Dragon arrows";}};
                        weapon = new ItemData() { { name = "Avernic defender";}};
                        torso = new ItemData() { { name = "Void top (Elite)";}};
                        shield = new ItemData() { { name = "Arcane spirit shield";}};
                        pants = new ItemData() { { name = "Void robe (Elite)";}};
                        gloves = new ItemData() { { name = "Barrows gloves";}};
                        boots = new ItemData() { { name = "Pegasian boots";}};
                        ring = new ItemData() { { name = "Berserker ring (i)";}};
                    }
                } },
                { "Role 3", new CmRoleEquipment() {
                    {
                        helmet = new ItemData() { { name = "Void ranger helm";}};
                        cape = new ItemData() { { name = "Infernal cape";}};
                        necklace = new ItemData() { { name = "Necklace of anguish";}};
                        ammo = new ItemData() { { name = "Dragon arrows";}};
                        weapon = new ItemData() { { name = "Avernic defender";}};
                        torso = new ItemData() { { name = "Void top (Elite)";}};
                        shield = new ItemData() { { name = "Arcane spirit shield";}};
                        pants = new ItemData() { { name = "Void robe (Elite)";}};
                        gloves = new ItemData() { { name = "Barrows gloves";}};
                        boots = new ItemData() { { name = "Pegasian boots";}};
                        ring = new ItemData() { { name = "Berserker ring (i)";}};
                    }
                } },
                { "Role 4", new CmRoleEquipment() {
                    {
                        helmet = new ItemData() { { name = "Void ranger helm";}};
                        cape = new ItemData() { { name = "Infernal cape";}};
                        necklace = new ItemData() { { name = "Necklace of anguish";}};
                        ammo = new ItemData() { { name = "Dragon arrows";}};
                        weapon = new ItemData() { { name = "Avernic defender";}};
                        torso = new ItemData() { { name = "Void top (Elite)";}};
                        shield = new ItemData() { { name = "Arcane spirit shield";}};
                        pants = new ItemData() { { name = "Void robe (Elite)";}};
                        gloves = new ItemData() { { name = "Barrows gloves";}};
                        boots = new ItemData() { { name = "Pegasian boots";}};
                        ring = new ItemData() { { name = "Berserker ring (i)";}};
                    }
                } },
                { "Role 5", new CmRoleEquipment() {
                    {
                        helmet = new ItemData() { { name = "Void ranger helm";}};
                        cape = new ItemData() { { name = "Infernal cape";}};
                        necklace = new ItemData() { { name = "Necklace of anguish";}};
                        ammo = new ItemData() { { name = "Dragon arrows";}};
                        weapon = new ItemData() { { name = "Avernic defender";}};
                        torso = new ItemData() { { name = "Void top (Elite)";}};
                        shield = new ItemData() { { name = "Arcane spirit shield";}};
                        pants = new ItemData() { { name = "Void robe (Elite)";}};
                        gloves = new ItemData() { { name = "Barrows gloves";}};
                        boots = new ItemData() { { name = "Pegasian boots";}};
                        ring = new ItemData() { { name = "Berserker ring (i)";}};
                    }
                } },

        }).collect(Collectors.toMap(data -> (String) data[0], data -> (CmRoleEquipment) data[1]));

    }
}
