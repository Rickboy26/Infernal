package com.clanevents.panels;

import lombok.Getter;
import lombok.Setter;

public class RankData {
    public RankData() {}

    @Getter @Setter
    public int id;
    @Getter @Setter
    public String name;
    @Getter @Setter
    public ItemData[] items;
}
