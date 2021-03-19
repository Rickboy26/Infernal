package com.clanevents.panels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RankData {
    public RankData() {}

    @Getter @Setter
    public int Id;
    @Getter @Setter
    public String Name;
}
