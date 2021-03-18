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
    public TimeZone Timezone;
    @Getter @Setter
    public Boolean Gearcheck;
    @Getter @Setter
    public int Points;
    @Getter @Setter
    public int RecruitedBy;
    @Getter @Setter
    public int ParentAccount;
    @Getter @Setter
    public String LegacyBumps;
    @Getter @Setter
    public String Comments;
    @Getter @Setter
    public Boolean IsAdmin;
    @Getter @Setter
    public Date JoinDate;
    @Getter @Setter
    public String Legacyevents;
    @Getter @Setter
    public String Legacymentor;
    @Getter @Setter
    public String Legacyrecruits;
    @Getter @Setter
    public String Legacyhelper;
    @Getter @Setter
    public int Rank_id;
    @Getter @Setter
    public Boolean Mentor;
    @Getter @Setter
    public String DiscordId;
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
    @Getter @Setter
    public String[] Offenses;
    @Getter @Setter
    public int AltAccountPvmPoints;
    @Getter @Setter
    public int AltAccountAltPoints;
    @Getter @Setter
    public int AltAccountBumpPoints;
    @Getter @Setter
    public int AltAccountEventPoints;
    @Getter @Setter
    public int AltAccountHelperPoints;


}
