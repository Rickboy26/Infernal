package com.InfernalFC.panels;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class EventData {
    public EventData() {}

    @Getter @Setter
    public String name;
    @Getter @Setter
    public Date date;
    @Getter @Setter
    public String description;
}
