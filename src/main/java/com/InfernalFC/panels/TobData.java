package com.InfernalFC.panels;

import javax.inject.Inject;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TobData {
    public Map<String, String[][]> itemMapping;

    @Inject
    private TobData() {
        itemMapping = Stream.of(new Object[][] {
                { "3", new String[][] {
                        { "Melee DPS",""},
                        { "Maiden", "Dwh:1x / Bgs:1x" },
                        { "Bloat", "Bgs:1x / Scy:4x / Hally:1x" },
                        { "Nylo", "Claws:1x / Scy:1x" },
                        { "Soteseg", "Phase 1&2" },
                        { "Xarpus", "Dwh:1x / Bgs:1x" }

                } }

        }).collect(Collectors.toMap(data -> (String) data[0], data -> (String[][]) data[1]));
    }
}
