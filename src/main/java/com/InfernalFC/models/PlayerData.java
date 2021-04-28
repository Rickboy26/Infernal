package com.InfernalFC.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class PlayerData {

    public PlayerData() {}

    @Getter @Setter
    public int id;
    @Getter @Setter
    public String username;
    @Getter @Setter
    public Boolean active;
    @Getter @Setter
    public Boolean gearcheck;
    @Getter @Setter
    public int points;
    @Getter @Setter
    public int parentAccount;
    @Getter @Setter
    public Date poinDate;
    @Getter @Setter
    public int Rank_id;
    @Getter @Setter
    public Boolean mentor;
    @Getter @Setter
    public Boolean isEventStaff;
    @Getter @Setter
    public Boolean isClanAdmin;
    @Getter @Setter
    public int overallPoints;
    @Getter @Setter
    public int pvmPoints;
    @Getter @Setter
    public int nonPvmPoints;
    @Getter @Setter
    public int altPoints;
    @Getter @Setter
    public int bumpPoints;
    @Getter @Setter
    public int recruitPoints;
    @Getter @Setter
    public int eventPoints;
    @Getter @Setter
    public int mentorPoints;
    @Getter @Setter
    public int helperPoints;
    @Getter @Setter
    public int donationPoints;
    @Getter @Setter
    public int valueSplit;
    @Getter @Setter
    public int valueTanked;

}
