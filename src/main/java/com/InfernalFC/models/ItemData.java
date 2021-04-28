package com.InfernalFC.models;

import lombok.Getter;
import lombok.Setter;

public class ItemData {
    public ItemData() {}

    @Getter @Setter
    public int id;
    @Getter @Setter
    public String name;
    @Getter @Setter
    public String artwork;
}
