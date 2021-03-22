package com.clanevents.panels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemData {
    public ItemData() {}

    @Getter @Setter
    public int Id;
    @Getter @Setter
    public String Name;
    @Getter @Setter
    public String Artwork;
}
