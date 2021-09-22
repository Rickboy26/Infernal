package com.InfernalFC.models;

import lombok.Getter;
import lombok.Setter;

public class CmRoleEquipment {
    public CmRoleEquipment() {}

    public ItemData helmet;
    @Getter @Setter
    public ItemData cape;
    @Getter @Setter
    public ItemData necklace;
    @Getter @Setter
    public ItemData ammo;
    @Getter @Setter
    public ItemData weapon;
    @Getter @Setter
    public ItemData torso;
    @Getter @Setter
    public ItemData shield;
    @Getter @Setter
    public ItemData pants;
    @Getter @Setter
    public ItemData gloves;
    @Getter @Setter
    public ItemData boots;
    @Getter @Setter
    public ItemData ring;
}
