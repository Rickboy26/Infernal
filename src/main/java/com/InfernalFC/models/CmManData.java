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
                { "Surge 1", new CmRoleEquipment() {
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
                { "Surge 2", new CmRoleEquipment() {
                    {
                        helmet = new ItemData() { { name = "Torva full helm";}};
                        cape = new ItemData() { { name = "Infernal cape";}};
                        necklace = new ItemData() { { name = "Amulet of torture";}};
                        ammo = new ItemData() { { name = "Dragon arrows";}};
                        weapon = new ItemData() { { name = "Dragon warhammer";}};
                        torso = new ItemData() { { name = "Torva platebody";}};
                        shield = new ItemData() { { name = "Avernic defender";}};
                        pants = new ItemData() { { name = "Torva platelegs";}};
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
                { "Chinner", new CmRoleEquipment() {
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
                { "Crosser", new CmRoleEquipment() {
                    {
                        helmet = new ItemData() { { name = "Torva full helm";}};
                        cape = new ItemData() { { name = "Infernal cape";}};
                        necklace = new ItemData() { { name = "Amulet of torture";}};
                        ammo = new ItemData() { { name = "Dragon arrows";}};
                        weapon = new ItemData() { { name = "Dragon warhammer";}};
                        torso = new ItemData() { { name = "Torva platebody";}};
                        shield = new ItemData() { { name = "Avernic defender";}};
                        pants = new ItemData() { { name = "Torva platelegs";}};
                        gloves = new ItemData() { { name = "Ferocious gloves";}};
                        boots = new ItemData() { { name = "Primordial boots";}};
                        ring = new ItemData() { { name = "Brimstone ring";}};
                    }
                } },

        }).collect(Collectors.toMap(data -> (String) data[0], data -> (CmRoleEquipment) data[1]));

        roleInventoryMapping = Stream.of(new Object[][] {
                { "Surge 1", new ItemData[] {
                        new ItemData() { { name = "Twisted bow";}},
                        new ItemData() { { name = "Armadyl chestplate";}},
                        new ItemData() { { name = "Zaryte vambraces";}},
                        new ItemData() { { name = "Pegasian boots";}},
                        new ItemData() { { name = "Necklace of anguish";}},
                        new ItemData() { { name = "Armadyl chainskirt";}},
                        new ItemData() { { name = "Ava's assembler";}},
                        new ItemData() { { name = "Slayer helmet";}},
                        new ItemData() { { name = "Sanguinesti staff";}},
                        new ItemData() { { name = "Ancestral robe top";}},
                        new ItemData() { { name = "Imbued Magic Cape";}},
                        new ItemData() { { name = "Tome of Fire";}},
                        new ItemData() { { name = "Occult necklace";}},
                        new ItemData() { { name = "Ancestral robe bottom";}},
                        new ItemData() { { name = "Tormented bracelet";}},
                        new ItemData() { { name = "Harmonised nightmare staff";}},
                        new ItemData() { { name = "Crystal pickaxe";}},
                        new ItemData() { { name = "Dragon claws";}},
                        new ItemData() { { name = "Scythe of Vitur";}},
                        new ItemData() { { name = "Saradomin brew (4)";}},
                        new ItemData() { { name = "Salve amulet (ei)";}},
                        new ItemData() { { name = "Berserker ring (i)";}},
                        new ItemData() { { name = "Super restore (4)";}},
                        new ItemData() { { name = "Saradomin brew (4)";}},
                        new ItemData() { { name = "Rune pouch";}},
                        new ItemData() { { name = "Toxic blowpipe";}},
                        new ItemData() { { name = "Stamina potion (4)";}},
                        new ItemData() { { name = "Stamina potion (4)";}},


                } },
                { "Surge 2", new ItemData[] {
                        new ItemData() { { name = "Twisted bow";}},
                        new ItemData() { { name = "Armadyl chestplate";}},
                        new ItemData() { { name = "Zaryte vambraces";}},
                        new ItemData() { { name = "Pegasian boots";}},
                        new ItemData() { { name = "Necklace of anguish";}},
                        new ItemData() { { name = "Armadyl chainskirt";}},
                        new ItemData() { { name = "Ava's assembler";}},
                        new ItemData() { { name = "Slayer helmet";}},
                        new ItemData() { { name = "Sanguinesti staff";}},
                        new ItemData() { { name = "Ancestral robe top";}},
                        new ItemData() { { name = "Imbued Magic Cape";}},
                        new ItemData() { { name = "Tome of Fire";}},
                        new ItemData() { { name = "Occult necklace";}},
                        new ItemData() { { name = "Ancestral robe bottom";}},
                        new ItemData() { { name = "Tormented bracelet";}},
                        new ItemData() { { name = "Harmonised nightmare staff";}},
                        new ItemData() { { name = "Crystal pickaxe";}},
                        new ItemData() { { name = "Dragon claws";}},
                        new ItemData() { { name = "Scythe of Vitur";}},
                        new ItemData() { { name = "Saradomin brew (4)";}},
                        new ItemData() { { name = "Salve amulet (ei)";}},
                        new ItemData() { { name = "Berserker ring (i)";}},
                        new ItemData() { { name = "Super restore (4)";}},
                        new ItemData() { { name = "Saradomin brew (4)";}},
                        new ItemData() { { name = "Rune pouch";}},
                        new ItemData() { { name = "Bandos godsword";}},
                        new ItemData() { { name = "Stamina potion (4)";}},
                        new ItemData() { { name = "Stamina potion (4)";}},
                } },
                { "Prep", new ItemData[] {
                        new ItemData() { { name = "Twisted bow";}},
                        new ItemData() { { name = "Armadyl chestplate";}},
                        new ItemData() { { name = "Zaryte vambraces";}},
                        new ItemData() { { name = "Pegasian boots";}},
                        new ItemData() { { name = "Necklace of anguish";}},
                        new ItemData() { { name = "Armadyl chainskirt";}},
                        new ItemData() { { name = "Ava's assembler";}},
                        new ItemData() { { name = "Slayer helmet";}},
                        new ItemData() { { name = "Sanguinesti staff";}},
                        new ItemData() { { name = "Ancestral robe top";}},
                        new ItemData() { { name = "Imbued Magic Cape";}},
                        new ItemData() { { name = "Arcane spirit shield";}},
                        new ItemData() { { name = "Occult necklace";}},
                        new ItemData() { { name = "Ancestral robe bottom";}},
                        new ItemData() { { name = "Tormented bracelet";}},
                        new ItemData() { { name = "Scythe of Vitur";}},
                        new ItemData() { { name = "Crystal pickaxe";}},
                        new ItemData() { { name = "Dragon claws";}},
                        new ItemData() { { name = "Berserker ring (i)";}},
                        new ItemData() { { name = "Lockpick";}},
                        new ItemData() { { name = "Salve amulet (ei)";}},
                        new ItemData() { { name = "Toxic blowpipe";}},
                        new ItemData() { { name = "Saradomin brew (4)";}},
                        new ItemData() { { name = "Lockpick";}},
                        new ItemData() { { name = "Rune pouch";}},
                        new ItemData() { { name = "Water rune";}},
                        new ItemData() { { name = "Stamina potion (4)";}},
                        new ItemData() { { name = "Stamina potion (4)";}},
                } },
                { "Chinner", new ItemData[] {
                        new ItemData() { { name = "Twisted bow";}},
                        new ItemData() { { name = "Armadyl chestplate";}},
                        new ItemData() { { name = "Zaryte vambraces";}},
                        new ItemData() { { name = "Pegasian boots";}},
                        new ItemData() { { name = "Necklace of anguish";}},
                        new ItemData() { { name = "Armadyl chainskirt";}},
                        new ItemData() { { name = "Ava's assembler";}},
                        new ItemData() { { name = "Slayer helmet";}},
                        new ItemData() { { name = "Sanguinesti staff";}},
                        new ItemData() { { name = "Ancestral robe top";}},
                        new ItemData() { { name = "Imbued Magic Cape";}},
                        new ItemData() { { name = "Arcane spirit shield";}},
                        new ItemData() { { name = "Occult necklace";}},
                        new ItemData() { { name = "Ancestral robe bottom";}},
                        new ItemData() { { name = "Tormented bracelet";}},
                        new ItemData() { { name = "Scythe of Vitur";}},
                        new ItemData() { { name = "Crystal pickaxe";}},
                        new ItemData() { { name = "Dragon claws";}},
                        new ItemData() { { name = "Berserker ring (i)";}},
                        new ItemData() { { name = "Saradomin brew (4)";}},
                        new ItemData() { { name = "Salve amulet (ei)";}},
                        new ItemData() { { name = "Toxic blowpipe";}},
                        new ItemData() { { name = "Super restore (4)";}},
                        new ItemData() { { name = "Saradomin brew (4)";}},
                        new ItemData() { { name = "Rune pouch";}},
                        new ItemData() { { name = "Chinchompa";}},
                        new ItemData() { { name = "Stamina potion (4)";}},
                        new ItemData() { { name = "Stamina potion (4)";}},
                } },
                { "Crosser", new ItemData[] {
                        new ItemData() { { name = "Twisted bow";}},
                        new ItemData() { { name = "Armadyl chestplate";}},
                        new ItemData() { { name = "Zaryte vambraces";}},
                        new ItemData() { { name = "Pegasian boots";}},
                        new ItemData() { { name = "Necklace of anguish";}},
                        new ItemData() { { name = "Armadyl chainskirt";}},
                        new ItemData() { { name = "Ava's assembler";}},
                        new ItemData() { { name = "Slayer helmet";}},
                        new ItemData() { { name = "Sanguinesti staff";}},
                        new ItemData() { { name = "Ancestral robe top";}},
                        new ItemData() { { name = "Imbued Magic Cape";}},
                        new ItemData() { { name = "Arcane spirit shield";}},
                        new ItemData() { { name = "Occult necklace";}},
                        new ItemData() { { name = "Ancestral robe bottom";}},
                        new ItemData() { { name = "Tormented bracelet";}},
                        new ItemData() { { name = "Scythe of Vitur";}},
                        new ItemData() { { name = "Crystal pickaxe";}},
                        new ItemData() { { name = "Dragon claws";}},
                        new ItemData() { { name = "Bandos godsword";}},
                        new ItemData() { { name = "Saradomin brew (4)";}},
                        new ItemData() { { name = "Salve amulet (ei)";}},
                        new ItemData() { { name = "Berserker ring (i)";}},
                        new ItemData() { { name = "Super restore (4)";}},
                        new ItemData() { { name = "Saradomin brew (4)";}},
                        new ItemData() { { name = "Rune pouch";}},
                        new ItemData() { { name = "Toxic blowpipe";}},
                        new ItemData() { { name = "Stamina potion (4)";}},
                        new ItemData() { { name = "Stamina potion (4)";}},
                } }

        }).collect(Collectors.toMap(data -> (String) data[0], data -> (ItemData[]) data[1]));

        roleRuneMapping = Stream.of(new Object[][] {
                { "Surge 1", new ItemData[] {
                        new ItemData() { { name = "Mist rune";}},
                        new ItemData() { { name = "Wrath rune";}},
                        new ItemData() { { name = "Law rune";}}
                } },
                { "Surge 2", new ItemData[] {
                        new ItemData() { { name = "Mist rune";}},
                        new ItemData() { { name = "Wrath rune";}},
                        new ItemData() { { name = "Law rune";}}
                } },
                { "Prep", new ItemData[] {
                        new ItemData() { { name = "Death rune";}},
                        new ItemData() { { name = "Astral rune";}},
                        new ItemData() { { name = "Lava rune";}}
                } },
                { "Chinner", new ItemData[] {
                        new ItemData() { { name = "Death rune";}},
                        new ItemData() { { name = "Lava rune";}},
                        new ItemData() { { name = "Astral rune";}}
                } },
                { "Crosser", new ItemData[] {
                        new ItemData() { { name = "Death rune";}},
                        new ItemData() { { name = "Astral rune";}},
                        new ItemData() { { name = "Lava rune";}}
                } }

        }).collect(Collectors.toMap(data -> (String) data[0], data -> (ItemData[]) data[1]));

        rolePrepotMapping = Stream.of(new Object[][] {
                { "Surge 1", new ItemData[] {
                        new ItemData() { { name = "Super combat potion (1)";}},
                        new ItemData() { { name = "Stamina potion (1)";}},
                        new ItemData() { { name = "Antidote++ (1)";}},
                        new ItemData() { { name = "Anglerfish";}},
                        new ItemData() { { name = "Anglerfish";}},
                        new ItemData() { { name = "Vengeance";}},
                } },
                { "Surge 2", new ItemData[] {
                        new ItemData() { { name = "Super combat potion (1)";}},
                        new ItemData() { { name = "Stamina potion (1)";}},
                        new ItemData() { { name = "Antidote++ (1)";}},
                        new ItemData() { { name = "Anglerfish";}},
                        new ItemData() { { name = "Anglerfish";}},
                        new ItemData() { { name = "Vengeance";}},
                } },
                { "Prep", new ItemData[] {
                        new ItemData() { { name = "Super combat potion (1)";}},
                        new ItemData() { { name = "Stamina potion (1)";}},
                        new ItemData() { { name = "Antidote++ (1)";}},
                        new ItemData() { { name = "Anglerfish";}},
                        new ItemData() { { name = "Anglerfish";}},
                        new ItemData() { { name = "Vengeance";}},
                } },
                { "Chinner", new ItemData[] {
                        new ItemData() { { name = "Super combat potion (1)";}},
                        new ItemData() { { name = "Stamina potion (1)";}},
                        new ItemData() { { name = "Antidote++ (1)";}},
                        new ItemData() { { name = "Anglerfish";}},
                        new ItemData() { { name = "Anglerfish";}},
                        new ItemData() { { name = "Vengeance";}},
                } },
                { "Crosser", new ItemData[] {
                        new ItemData() { { name = "Super combat potion (1)";}},
                        new ItemData() { { name = "Stamina potion (1)";}},
                        new ItemData() { { name = "Antidote++ (1)";}},
                        new ItemData() { { name = "Anglerfish";}},
                        new ItemData() { { name = "Anglerfish";}},
                        new ItemData() { { name = "Vengeance";}},
                } }

        }).collect(Collectors.toMap(data -> (String) data[0], data -> (ItemData[]) data[1]));

        roleNoteMapping = Stream.of(new Object[][] {
                { "Surge 1", "Lure tekton <br/> <font color='blue'>Mage</font> vanguard" },
                { "Surge 2", "<font color='green'>Range</font> vanguard" },
                { "Prep", "<b>3</b> Water runes for each humidify" },
                { "Chinner", "<font color='red'>Melee</font> vanguard" },
                { "Crosser", "<font color='red'>Melee</font> vanguard/float <br/> Cross tighrope <br/> Can bring Conditional BGS" }

        }).collect(Collectors.toMap(data -> (String) data[0], data -> (String) data[1]));
    }
}
