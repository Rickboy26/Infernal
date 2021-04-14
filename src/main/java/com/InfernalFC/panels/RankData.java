package com.InfernalFC.panels;

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
    @Getter @Setter
    public int total;
    @Getter @Setter
    public int pvm;
    @Getter @Setter
    public int community;
    @Getter @Setter
    public int ehb;
    @Getter @Setter
    public int combat;
    @Getter @Setter
    public int magic;
    @Getter @Setter
    public int herblore;
    @Getter @Setter
    public int coins;
    @Getter @Setter
    public int months;
}
