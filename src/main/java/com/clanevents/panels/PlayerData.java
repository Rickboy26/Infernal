package com.clanevents.panels;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.TimeZone;

public class PlayerData {

    public PlayerData() {}

    @Getter @Setter
    public int Id;
    @Getter @Setter
    public String Username;
    @Getter @Setter
    public Boolean Active;
    @Getter @Setter
    public Boolean Gearcheck;
    @Getter @Setter
    public int Points;
    @Getter @Setter
    public int ParentAccount;
    @Getter @Setter
    public Date JoinDate;
    @Getter @Setter
    public int Rank_id;
    @Getter @Setter
    public Boolean Mentor;
    @Getter @Setter
    public Boolean IsEventStaff;
    @Getter @Setter
    public Boolean IsClanAdmin;
    @Getter @Setter
    public int OverallPoints;
    @Getter @Setter
    public int PvmPoints;
    @Getter @Setter
    public int NonPvmPoints;
    @Getter @Setter
    public int AltPoints;
    @Getter @Setter
    public int BumpPoints;
    @Getter @Setter
    public int RecruitPoints;
    @Getter @Setter
    public int EventPoints;
    @Getter @Setter
    public int MentorPoints;
    @Getter @Setter
    public int HelperPoints;
    @Getter @Setter
    public int DonationPoints;
    @Getter @Setter
    public int ValueSplit;
    @Getter @Setter
    public int ValueTanked;

}
