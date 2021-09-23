package com.InfernalFC.models;

import javax.inject.Inject;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CmManData {
    public Map<String, String[][]> itemMapping;
    public Map<String, CmRoleEquipment> roleGearMapping;
    public Map<String, ItemData[]> roleInventoryMapping;
    public Map<String, ItemData[]> roleRuneMapping;
    public Map<String, ItemData[]> rolePrepotMapping;
    public Map<String, String> roleNoteMapping;

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
                { "Surge BGS", new CmRoleEquipment() {
                    {
                        helmet = new ItemData() { { name = "Neitiznot faceguard";}};
                        cape = new ItemData() { { name = "Imbued Magic Cape";}};
                        necklace = new ItemData() { { name = "Occult necklace";}};
                        ammo = new ItemData() { { name = "Dragon arrows";}};
                        weapon = new ItemData() { { name = "Sanguinesti staff";}};
                        torso = new ItemData() { { name = "Ancestral robe top";}};
                        shield = new ItemData() { { name = "Tome of Fire";}};
                        pants = new ItemData() { { name = "Ancestral robe bottom";}};
                        gloves = new ItemData() { { name = "Tormented bracelet";}};
                        boots = new ItemData() { { name = "Primordial boots";}};
                        ring = new ItemData() { { name = "Brimstone ring";}};
                    }
                } },
                { "Surge DWH", new CmRoleEquipment() {
                    {
                        helmet = new ItemData() { { name = "Inquisitor's great helm";}};
                        cape = new ItemData() { { name = "Infernal cape";}};
                        necklace = new ItemData() { { name = "Amulet of torture";}};
                        ammo = new ItemData() { { name = "Dragon arrows";}};
                        weapon = new ItemData() { { name = "Dragon warhammer";}};
                        torso = new ItemData() { { name = "Inquisitor's hauberk";}};
                        shield = new ItemData() { { name = "Avernic defender";}};
                        pants = new ItemData() { { name = "Inquisitor's plateskirt";}};
                        gloves = new ItemData() { { name = "Ferocious gloves";}};
                        boots = new ItemData() { { name = "Primordial boots";}};
                        ring = new ItemData() { { name = "Brimstone ring";}};
                    }
                } },
                { "Prep", new CmRoleEquipment() {
                    {
                        helmet = new ItemData() { { name = "Inquisitor's great helm";}};
                        cape = new ItemData() { { name = "Infernal cape";}};
                        necklace = new ItemData() { { name = "Amulet of torture";}};
                        ammo = new ItemData() { { name = "Dragon arrows";}};
                        weapon = new ItemData() { { name = "Dragon warhammer";}};
                        torso = new ItemData() { { name = "Inquisitor's hauberk";}};
                        shield = new ItemData() { { name = "Avernic defender";}};
                        pants = new ItemData() { { name = "Inquisitor's plateskirt";}};
                        gloves = new ItemData() { { name = "Ferocious gloves";}};
                        boots = new ItemData() { { name = "Primordial boots";}};
                        ring = new ItemData() { { name = "Brimstone ring";}};
                    }
                } },
                { "Veng Chin", new CmRoleEquipment() {
                    {
                        helmet = new ItemData() { { name = "Inquisitor's great helm";}};
                        cape = new ItemData() { { name = "Infernal cape";}};
                        necklace = new ItemData() { { name = "Amulet of torture";}};
                        ammo = new ItemData() { { name = "Dragon arrows";}};
                        weapon = new ItemData() { { name = "Dragon warhammer";}};
                        torso = new ItemData() { { name = "Inquisitor's hauberk";}};
                        shield = new ItemData() { { name = "Avernic defender";}};
                        pants = new ItemData() { { name = "Inquisitor's plateskirt";}};
                        gloves = new ItemData() { { name = "Ferocious gloves";}};
                        boots = new ItemData() { { name = "Primordial boots";}};
                        ring = new ItemData() { { name = "Brimstone ring";}};
                    }
                } },
                { "Veng Leech / Cross", new CmRoleEquipment() {
                    {
                        helmet = new ItemData() { { name = "Neitiznot faceguard";}};
                        cape = new ItemData() { { name = "Infernal cape";}};
                        necklace = new ItemData() { { name = "Amulet of torture";}};
                        ammo = new ItemData() { { name = "Dragon arrows";}};
                        weapon = new ItemData() { { name = "Dragon warhammer";}};
                        torso = new ItemData() { { name = "Bandos chestplate";}};
                        shield = new ItemData() { { name = "Avernic defender";}};
                        pants = new ItemData() { { name = "Bandos tassets";}};
                        gloves = new ItemData() { { name = "Ferocious gloves";}};
                        boots = new ItemData() { { name = "Primordial boots";}};
                        ring = new ItemData() { { name = "Brimstone ring";}};
                    }
                } },

        }).collect(Collectors.toMap(data -> (String) data[0], data -> (CmRoleEquipment) data[1]));

        roleInventoryMapping = Stream.of(new Object[][] {
                { "Surge BGS", new ItemData[] {
                        new ItemData() { { name = "Bandos chestplate";}},
                        new ItemData() { { name = "Infernal cape";}},
                        new ItemData() { { name = "Armadyl helmet";}},
                        new ItemData() { { name = "Ava's assembler";}},
                        new ItemData() { { name = "Ferocious gloves";}},
                        new ItemData() { { name = "Amulet of torture";}},
                        new ItemData() { { name = "Armadyl chestplate";}},
                        new ItemData() { { name = "Necklace of anguish";}},
                        new ItemData() { { name = "Bandos godsword";}},
                        new ItemData() { { name = "Berserker ring (i)";}},
                        new ItemData() { { name = "Armadyl chainskirt";}},
                        new ItemData() { { name = "Barrows gloves";}},
                        new ItemData() { { name = "Harmonised nightmare staff";}},
                        new ItemData() { { name = "Avernic defender";}},
                        new ItemData() { { name = "Pegasian boots";}},
                        new ItemData() { { name = "Twisted bow";}},
                        new ItemData() { { name = "Scythe of Vitur";}},
                        new ItemData() { { name = "Dragon claws";}},
                        new ItemData() { { name = "Crystal pickaxe";}},
                        new ItemData() { { name = "Salve amulet (ei)";}},
                        new ItemData() { { name = "Saradomin brew (4)";}},
                        new ItemData() { { name = "Saradomin brew (4)";}},
                        new ItemData() { { name = "Saradomin brew (4)";}},
                        new ItemData() { { name = "Toxic blowpipe";}},
                        new ItemData() { { name = "Super restore (4)";}},
                        new ItemData() { { name = "Stamina potion (4)";}},
                        new ItemData() { { name = "Stamina potion (4)";}},
                        new ItemData() { { name = "Rune pouch";}},


                } },
                { "Surge DWH", new ItemData[] {
                        new ItemData() { { name = "Ancestral robe top";}},
                        new ItemData() { { name = "Imbued Magic Cape";}},
                        new ItemData() { { name = "Armadyl helmet";}},
                        new ItemData() { { name = "Ava's assembler";}},
                        new ItemData() { { name = "Ancestral robe bottom";}},
                        new ItemData() { { name = "Occult necklace";}},
                        new ItemData() { { name = "Armadyl chestplate";}},
                        new ItemData() { { name = "Necklace of anguish";}},
                        new ItemData() { { name = "Sanguinesti staff";}},
                        new ItemData() { { name = "Tormented bracelet";}},
                        new ItemData() { { name = "Armadyl chainskirt";}},
                        new ItemData() { { name = "Barrows gloves";}},
                        new ItemData() { { name = "Harmonised nightmare staff";}},
                        new ItemData() { { name = "Avernic defender";}},
                        new ItemData() { { name = "Pegasian boots";}},
                        new ItemData() { { name = "Twisted bow";}},
                        new ItemData() { { name = "Scythe of Vitur";}},
                        new ItemData() { { name = "Dragon claws";}},
                        new ItemData() { { name = "Crystal pickaxe";}},
                        new ItemData() { { name = "Salve amulet (ei)";}},
                        new ItemData() { { name = "Saradomin brew (4)";}},
                        new ItemData() { { name = "Saradomin brew (4)";}},
                        new ItemData() { { name = "Saradomin brew (4)";}},
                        new ItemData() { { name = "Toxic blowpipe";}},
                        new ItemData() { { name = "Super restore (4)";}},
                        new ItemData() { { name = "Stamina potion (4)";}},
                        new ItemData() { { name = "Stamina potion (4)";}},
                        new ItemData() { { name = "Rune pouch";}},
                } },
                { "Prep", new ItemData[] {
                        new ItemData() { { name = "Ancestral robe top";}},
                        new ItemData() { { name = "Imbued Magic Cape";}},
                        new ItemData() { { name = "Armadyl helmet";}},
                        new ItemData() { { name = "Ava's assembler";}},
                        new ItemData() { { name = "Ancestral robe bottom";}},
                        new ItemData() { { name = "Occult necklace";}},
                        new ItemData() { { name = "Armadyl chestplate";}},
                        new ItemData() { { name = "Necklace of anguish";}},
                        new ItemData() { { name = "Sanguinesti staff";}},
                        new ItemData() { { name = "Tormented bracelet";}},
                        new ItemData() { { name = "Armadyl chainskirt";}},
                        new ItemData() { { name = "Barrows gloves";}},
                        new ItemData() { { name = "Harmonised nightmare staff";}},
                        new ItemData() { { name = "Avernic defender";}},
                        new ItemData() { { name = "Pegasian boots";}},
                        new ItemData() { { name = "Twisted bow";}},
                        new ItemData() { { name = "Scythe of Vitur";}},
                        new ItemData() { { name = "Dragon claws";}},
                        new ItemData() { { name = "Crystal pickaxe";}},
                        new ItemData() { { name = "Salve amulet (ei)";}},
                        new ItemData() { { name = "Saradomin brew (4)";}},
                        new ItemData() { { name = "Super restore (4)";}},
                        new ItemData() { { name = "Berserker ring (i)";}},
                        new ItemData() { { name = "Toxic blowpipe";}},
                        new ItemData() { { name = "Stamina potion (4)";}},
                        new ItemData() { { name = "Stamina potion (4)";}},
                        new ItemData() { { name = "Water rune";}},
                        new ItemData() { { name = "Rune pouch";}},
                } },
                { "Veng Chin", new ItemData[] {
                        new ItemData() { { name = "Ancestral robe top";}},
                        new ItemData() { { name = "Imbued Magic Cape";}},
                        new ItemData() { { name = "Armadyl helmet";}},
                        new ItemData() { { name = "Ava's assembler";}},
                        new ItemData() { { name = "Ancestral robe bottom";}},
                        new ItemData() { { name = "Occult necklace";}},
                        new ItemData() { { name = "Armadyl chestplate";}},
                        new ItemData() { { name = "Necklace of anguish";}},
                        new ItemData() { { name = "Sanguinesti staff";}},
                        new ItemData() { { name = "Tormented bracelet";}},
                        new ItemData() { { name = "Armadyl chainskirt";}},
                        new ItemData() { { name = "Barrows gloves";}},
                        new ItemData() { { name = "Harmonised nightmare staff";}},
                        new ItemData() { { name = "Avernic defender";}},
                        new ItemData() { { name = "Pegasian boots";}},
                        new ItemData() { { name = "Twisted bow";}},
                        new ItemData() { { name = "Scythe of Vitur";}},
                        new ItemData() { { name = "Dragon claws";}},
                        new ItemData() { { name = "Crystal pickaxe";}},
                        new ItemData() { { name = "Salve amulet (ei)";}},
                        new ItemData() { { name = "Chinchompa";}},
                        new ItemData() { { name = "Saradomin brew (4)";}},
                        new ItemData() { { name = "Saradomin brew (4)";}},
                        new ItemData() { { name = "Toxic blowpipe";}},
                        new ItemData() { { name = "Super restore (4)";}},
                        new ItemData() { { name = "Stamina potion (4)";}},
                        new ItemData() { { name = "Stamina potion (4)";}},
                        new ItemData() { { name = "Rune pouch";}},
                } },
                { "Veng Leech / Cross", new ItemData[] {
                        new ItemData() { { name = "Ancestral robe top";}},
                        new ItemData() { { name = "Imbued Magic Cape";}},
                        new ItemData() { { name = "Armadyl helmet";}},
                        new ItemData() { { name = "Ava's assembler";}},
                        new ItemData() { { name = "Ancestral robe bottom";}},
                        new ItemData() { { name = "Occult necklace";}},
                        new ItemData() { { name = "Armadyl chestplate";}},
                        new ItemData() { { name = "Necklace of anguish";}},
                        new ItemData() { { name = "Sanguinesti staff";}},
                        new ItemData() { { name = "Tormented bracelet";}},
                        new ItemData() { { name = "Armadyl chainskirt";}},
                        new ItemData() { { name = "Barrows gloves";}},
                        new ItemData() { { name = "Harmonised nightmare staff";}},
                        new ItemData() { { name = "Avernic defender";}},
                        new ItemData() { { name = "Pegasian boots";}},
                        new ItemData() { { name = "Twisted bow";}},
                        new ItemData() { { name = "Scythe of Vitur";}},
                        new ItemData() { { name = "Dragon claws";}},
                        new ItemData() { { name = "Crystal pickaxe";}},
                        new ItemData() { { name = "Salve amulet (ei)";}},
                        new ItemData() { { name = "Saradomin brew (4)";}},
                        new ItemData() { { name = "Saradomin brew (4)";}},
                        new ItemData() { { name = "Saradomin brew (4)";}},
                        new ItemData() { { name = "Toxic blowpipe";}},
                        new ItemData() { { name = "Super restore (4)";}},
                        new ItemData() { { name = "Stamina potion (4)";}},
                        new ItemData() { { name = "Stamina potion (4)";}},
                        new ItemData() { { name = "Rune pouch";}},
                } }

        }).collect(Collectors.toMap(data -> (String) data[0], data -> (ItemData[]) data[1]));

        roleRuneMapping = Stream.of(new Object[][] {
                { "Surge BGS", new ItemData[] {
                        new ItemData() { { name = "Mist rune";}},
                        new ItemData() { { name = "Wrath rune";}},
                        new ItemData() { { name = "Law rune";}}
                } },
                { "Surge DWH", new ItemData[] {
                        new ItemData() { { name = "Mist rune";}},
                        new ItemData() { { name = "Wrath rune";}},
                        new ItemData() { { name = "Law rune";}}
                } },
                { "Prep", new ItemData[] {
                        new ItemData() { { name = "Death rune";}},
                        new ItemData() { { name = "Lava rune";}},
                        new ItemData() { { name = "Astral rune";}}
                } },
                { "Veng Chin", new ItemData[] {
                        new ItemData() { { name = "Death rune";}},
                        new ItemData() { { name = "Earth rune";}},
                        new ItemData() { { name = "Astral rune";}}
                } },
                { "Veng Leech / Cross", new ItemData[] {
                        new ItemData() { { name = "Death rune";}},
                        new ItemData() { { name = "Earth rune";}},
                        new ItemData() { { name = "Astral rune";}}
                } }

        }).collect(Collectors.toMap(data -> (String) data[0], data -> (ItemData[]) data[1]));

        rolePrepotMapping = Stream.of(new Object[][] {
                { "Surge BGS", new ItemData[] {
                        new ItemData() { { name = "Super combat potion (1)";}},
                        new ItemData() { { name = "Stamina potion (1)";}},
                        new ItemData() { { name = "Antidote++ (1)";}},
                        new ItemData() { { name = "Anglerfish";}},
                        new ItemData() { { name = "Anglerfish";}},
                        new ItemData() { { name = "Vengaence";}},
                } },
                { "Surge DWH", new ItemData[] {
                        new ItemData() { { name = "Super combat potion (1)";}},
                        new ItemData() { { name = "Stamina potion (1)";}},
                        new ItemData() { { name = "Antidote++ (1)";}},
                        new ItemData() { { name = "Anglerfish";}},
                        new ItemData() { { name = "Anglerfish";}},
                        new ItemData() { { name = "Vengaence";}},
                } },
                { "Prep", new ItemData[] {
                        new ItemData() { { name = "Super combat potion (1)";}},
                        new ItemData() { { name = "Stamina potion (1)";}},
                        new ItemData() { { name = "Antidote++ (1)";}},
                        new ItemData() { { name = "Anglerfish";}},
                        new ItemData() { { name = "Anglerfish";}},
                        new ItemData() { { name = "Vengaence";}},
                } },
                { "Veng Chin", new ItemData[] {
                        new ItemData() { { name = "Super combat potion (1)";}},
                        new ItemData() { { name = "Stamina potion (1)";}},
                        new ItemData() { { name = "Antidote++ (1)";}},
                        new ItemData() { { name = "Anglerfish";}},
                        new ItemData() { { name = "Anglerfish";}},
                        new ItemData() { { name = "Vengaence";}},
                } },
                { "Veng Leech / Cross", new ItemData[] {
                        new ItemData() { { name = "Super combat potion (1)";}},
                        new ItemData() { { name = "Stamina potion (1)";}},
                        new ItemData() { { name = "Antidote++ (1)";}},
                        new ItemData() { { name = "Anglerfish";}},
                        new ItemData() { { name = "Anglerfish";}},
                        new ItemData() { { name = "Vengaence";}},
                } }

        }).collect(Collectors.toMap(data -> (String) data[0], data -> (ItemData[]) data[1]));

        roleNoteMapping = Stream.of(new Object[][] {
                { "Surge BGS", "Lure tekton <br/> Mage vanguard" },
                { "Surge DWH", "Range vanguard" },
                { "Prep", "" },
                { "Veng Chin", "Melee vanguard" },
                { "Veng Leech / Cross", "<font color='red'>Melee</font> vanguard/float <br/> Cross tighrope <br/> Can bring Conditional BGS" }

        }).collect(Collectors.toMap(data -> (String) data[0], data -> (String) data[1]));
    }
}
