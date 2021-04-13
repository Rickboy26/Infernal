package com.InfernalFC.panels;

import javax.inject.Inject;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CmManData {
    public Map<String, String[][]> itemMapping;

    @Inject
    private CmManData() {
        itemMapping = Stream.of(new Object[][] {
                { "3", new String[][] {
                        { "Juice", "25" },
                        { "Mushroom", "12" },
                        { "Cicely", "1" },
                        { "Buchu", "38" },
                        { "Noxifier", "0" },
                        { "Golpar", "0" },
                        { "Humidifies", "2" },
                        { "Grubs", "47" }
                } },
                { "4", new String[][] {
                        { "Juice", "30" },
                        { "Mushroom", "16" },
                        { "Cicely", "1" },
                        { "Buchu", "47" },
                        { "Noxifier", "0" },
                        { "Golpar", "0" },
                        { "Humidifies", "2" },
                        { "Grubs", "63" }
                } },
                { "5", new String[][] {
                        { "Juice", "40" },
                        { "Mushroom", "20" },
                        { "Cicely", "2" },
                        { "Buchu", "62" },
                        { "Noxifier", "1" },
                        { "Golpar", "0" },
                        { "Humidifies", "3" },
                        { "Grubs", "79" }
                } },
                { "6", new String[][] {
                        { "Juice", "42" },
                        { "Mushroom", "26" },
                        { "Cicely", "5" },
                        { "Buchu", "67" },
                        { "Noxifier", "3" },
                        { "Golpar", "6" },
                        { "Humidifies", "3" },
                        { "Grubs", "95" }
                } },
                { "7", new String[][] {
                        { "Juice", "51" },
                        { "Mushroom", "30" },
                        { "Cicely", "5" },
                        { "Buchu", "82" },
                        { "Noxifier", "3" },
                        { "Golpar", "6" },
                        { "Humidifies", "4" },
                        { "Grubs", "111" }
                } },
                { "8", new String[][] {
                        { "Juice", "56" },
                        { "Mushroom", "30" },
                        { "Cicely", "5" },
                        { "Buchu", "86" },
                        { "Noxifier", "3" },
                        { "Golpar", "6" },
                        { "Humidifies", "4" },
                        { "Grubs", "127" }
                } },
                { "9", new String[][] {
                        { "Juice", "60" },
                        { "Mushroom", "40" },
                        { "Cicely", "11" },
                        { "Buchu", "111" },
                        { "Noxifier", "5" },
                        { "Golpar", "12" },
                        { "Humidifies", "6" },
                        { "Grubs", "143" }
                } }
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (String[][]) data[1]));
    }
}
