package com.InfernalFC.helpers;

import com.InfernalFC.InfernalFCConfig;
import com.InfernalFC.models.ColorTileMarker;
import com.InfernalFC.models.GroundMarkerPoint;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.runelite.api.Client;
import net.runelite.api.coords.WorldPoint;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class MarkersManager {

    private final Client client;
    private final InfernalFCConfig config;
    private final Gson gson;
    private final String tektonMarkers = "[{\"regionId\":13138,\"regionX\":47,\"regionY\":42,\"z\":3,\"color\":\"#FF1AAA01\",\"label\":\"Lure\"},{\"regionId\":13138,\"regionX\":48,\"regionY\":37,\"z\":3,\"color\":\"#FF1AAA01\",\"label\":\"Lure\"},{\"regionId\":13138,\"regionX\":47,\"regionY\":37,\"z\":3,\"color\":\"#FF08DEFF\",\"label\":\"Start\"}]";
    private final String crabsMarkers = "[{\"regionId\":13395,\"regionX\":11,\"regionY\":41,\"z\":3,\"color\":\"#FF797979\"},{\"regionId\":13395,\"regionX\":8,\"regionY\":48,\"z\":3,\"color\":\"#FF1AAA01\",\"label\":\"2nd\"},{\"regionId\":13395,\"regionX\":12,\"regionY\":48,\"z\":3,\"color\":\"#FF1AAA01\",\"label\":\"2\"},{\"regionId\":13395,\"regionX\":10,\"regionY\":47,\"z\":3,\"color\":\"#FF08DEFF\",\"label\":\"3rd\"},{\"regionId\":13395,\"regionX\":14,\"regionY\":47,\"z\":3,\"color\":\"#FF08DEFF\",\"label\":\"3\"},{\"regionId\":13395,\"regionX\":12,\"regionY\":51,\"z\":3,\"color\":\"#FF1AAA01\",\"label\":\"Range\"},{\"regionId\":13395,\"regionX\":12,\"regionY\":55,\"z\":3,\"color\":\"#FFFF0000\",\"label\":\"Melee\"},{\"regionId\":13395,\"regionX\":10,\"regionY\":46,\"z\":3,\"color\":\"#FFFF0000\",\"label\":\"Melee\"},{\"regionId\":13395,\"regionX\":11,\"regionY\":56,\"z\":3,\"color\":\"#FFFF0000\",\"label\":\"Melee\"},{\"regionId\":13395,\"regionX\":11,\"regionY\":46,\"z\":3,\"color\":\"#FFFF00FF\",\"label\":\"1\"},{\"regionId\":13395,\"regionX\":7,\"regionY\":46,\"z\":3,\"color\":\"#FFFF00FF\",\"label\":\"1st\"},{\"regionId\":13395,\"regionX\":13,\"regionY\":43,\"z\":3,\"color\":\"#FFFFFFFF\",\"label\":\"No Hits\"},{\"regionId\":13395,\"regionX\":9,\"regionY\":42,\"z\":3,\"color\":\"#FF08DEFF\",\"label\":\"Mage\"}]";
    private final String iceMarkers = "[{\"regionId\":13139,\"regionX\":41,\"regionY\":50,\"z\":3,\"color\":\"#FFFFFFFF\"},{\"regionId\":13139,\"regionX\":41,\"regionY\":52,\"z\":3,\"color\":\"#FFFFFFFF\"},{\"regionId\":13139,\"regionX\":39,\"regionY\":52,\"z\":3,\"color\":\"#FFFFFFFF\"},{\"regionId\":13139,\"regionX\":39,\"regionY\":50,\"z\":3,\"color\":\"#FFFFFFFF\"},{\"regionId\":13139,\"regionX\":39,\"regionY\":51,\"z\":3,\"color\":\"#FFFFFFFF\",\"label\":\"Safe\"},{\"regionId\":13139,\"regionX\":41,\"regionY\":51,\"z\":3,\"color\":\"#FFFFFFFF\",\"label\":\"Step Here First\"},{\"regionId\":13139,\"regionX\":54,\"regionY\":52,\"z\":3,\"color\":\"#FFFFFFFF\",\"label\":\"Run\"},{\"regionId\":13139,\"regionX\":54,\"regionY\":50,\"z\":3,\"color\":\"#FFFFFFFF\",\"label\":\"Run\"}]";
    private final String shamanMarkers ="[{\"regionId\":13138,\"regionX\":47,\"regionY\":3,\"z\":3,\"color\":\"#FF797979\"},{\"regionId\":13138,\"regionX\":46,\"regionY\":3,\"z\":3,\"color\":\"#FF797979\"},{\"regionId\":13138,\"regionX\":46,\"regionY\":5,\"z\":3,\"color\":\"#FF08DEFF\",\"label\":\"Run\"},{\"regionId\":13138,\"regionX\":49,\"regionY\":5,\"z\":3,\"color\":\"#FF08DEFF\",\"label\":\"Run\"},{\"regionId\":13138,\"regionX\":53,\"regionY\":7,\"z\":3,\"color\":\"#FFFF00FF\",\"label\":\"1st\"},{\"regionId\":13138,\"regionX\":56,\"regionY\":14,\"z\":3,\"color\":\"#FFFF00FF\",\"label\":\"2nd\"},{\"regionId\":13138,\"regionX\":39,\"regionY\":7,\"z\":3,\"color\":\"#FF08DEFF\",\"label\":\"3rd\"}]";
    private final String vanguardsMarkers = "[{\"regionId\":13139,\"regionX\":50,\"regionY\":8,\"z\":2,\"color\":\"#FFFF0000\",\"label\":\"East Spawn\"},{\"regionId\":13139,\"regionX\":44,\"regionY\":21,\"z\":2,\"color\":\"#FFFF0000\",\"label\":\"East Spawn Tank\"},{\"regionId\":13139,\"regionX\":43,\"regionY\":14,\"z\":2,\"color\":\"#FFFF0000\",\"label\":\"West Spawn Tank\"},{\"regionId\":13139,\"regionX\":44,\"regionY\":11,\"z\":2,\"color\":\"#FFFF0000\",\"label\":\"North Spawn Tank\"},{\"regionId\":13139,\"regionX\":47,\"regionY\":26,\"z\":2,\"color\":\"#FFFF0000\",\"label\":\"North \\u0026 West SS\"},{\"regionId\":13139,\"regionX\":45,\"regionY\":27,\"z\":2,\"color\":\"#FFFF0000\",\"label\":\"North \\u0026 West SS\"},{\"regionId\":13139,\"regionX\":52,\"regionY\":13,\"z\":2,\"color\":\"#FFFF0000\",\"label\":\"SS\"},{\"regionId\":13139,\"regionX\":53,\"regionY\":13,\"z\":2,\"color\":\"#FFFF0000\",\"label\":\"Lure\"},{\"regionId\":13139,\"regionX\":54,\"regionY\":10,\"z\":2,\"color\":\"#FF797979\"},{\"regionId\":13139,\"regionX\":44,\"regionY\":23,\"z\":2,\"color\":\"#FFFFFFFF\",\"label\":\"Main\"},{\"regionId\":13139,\"regionX\":43,\"regionY\":12,\"z\":2,\"color\":\"#FFFFFFFF\",\"label\":\"Main\"},{\"regionId\":13139,\"regionX\":55,\"regionY\":17,\"z\":2,\"color\":\"#FFFFFFFF\",\"label\":\"Main\"}]";
    private final String thievingMarkers = "[{\"regionId\":13140,\"regionX\":58,\"regionY\":16,\"z\":2,\"color\":\"#FFFF0000\",\"label\":\"1\"},{\"regionId\":13140,\"regionX\":59,\"regionY\":15,\"z\":2,\"color\":\"#FFFF0000\",\"label\":\"1\"},{\"regionId\":13140,\"regionX\":56,\"regionY\":13,\"z\":2,\"color\":\"#FF08DEFF\",\"label\":\"2\"},{\"regionId\":13140,\"regionX\":58,\"regionY\":12,\"z\":2,\"color\":\"#FF08DEFF\",\"label\":\"2\"},{\"regionId\":13140,\"regionX\":57,\"regionY\":10,\"z\":2,\"color\":\"#FF08DEFF\",\"label\":\"2\"},{\"regionId\":13140,\"regionX\":53,\"regionY\":11,\"z\":2,\"color\":\"#FF08DEFF\",\"label\":\"2\"},{\"regionId\":13140,\"regionX\":54,\"regionY\":8,\"z\":2,\"color\":\"#FF1AAA01\",\"label\":\"3\"},{\"regionId\":13140,\"regionX\":56,\"regionY\":9,\"z\":2,\"color\":\"#FF1AAA01\",\"label\":\"3\"},{\"regionId\":13140,\"regionX\":56,\"regionY\":7,\"z\":2,\"color\":\"#FF1AAA01\",\"label\":\"3\"},{\"regionId\":13140,\"regionX\":55,\"regionY\":6,\"z\":2,\"color\":\"#FF1AAA01\",\"label\":\"3\"},{\"regionId\":13140,\"regionX\":53,\"regionY\":15,\"z\":2,\"color\":\"#FFFFD400\",\"label\":\"4\"},{\"regionId\":13140,\"regionX\":52,\"regionY\":13,\"z\":2,\"color\":\"#FFFFD400\",\"label\":\"4\"},{\"regionId\":13140,\"regionX\":50,\"regionY\":16,\"z\":2,\"color\":\"#FFFFD400\",\"label\":\"4\"},{\"regionId\":13140,\"regionX\":49,\"regionY\":13,\"z\":2,\"color\":\"#FFFFD400\",\"label\":\"4\"},{\"regionId\":13140,\"regionX\":54,\"regionY\":15,\"z\":2,\"color\":\"#FFFF0000\",\"label\":\"1\"},{\"regionId\":13140,\"regionX\":56,\"regionY\":14,\"z\":2,\"color\":\"#FFFF0000\",\"label\":\"1\"},{\"regionId\":13140,\"regionX\":48,\"regionY\":12,\"z\":2,\"color\":\"#FFFFFFFF\",\"label\":\"*\"},{\"regionId\":13140,\"regionX\":50,\"regionY\":11,\"z\":2,\"color\":\"#FFFFFFFF\",\"label\":\"*\"},{\"regionId\":13140,\"regionX\":49,\"regionY\":9,\"z\":2,\"color\":\"#FFFFFFFF\",\"label\":\"*\"},{\"regionId\":13140,\"regionX\":46,\"regionY\":9,\"z\":2,\"color\":\"#FFFFFFFF\",\"label\":\"*\"},{\"regionId\":13140,\"regionX\":60,\"regionY\":20,\"z\":2,\"color\":\"#00FFFFFF\",\"label\":\"Bank: Hammer, Scythe, Mage gear\"},{\"regionId\":13140,\"regionX\":60,\"regionY\":19,\"z\":2,\"color\":\"#00FFFFFF\",\"label\":\"Withdraw: Overload, 1 Brew, 3 Rests\"},{\"regionId\":13140,\"regionX\":53,\"regionY\":20,\"z\":2,\"color\":\"#FFFFFFFF\"}]";
    private final String vespulaMarkers = "[{\"regionId\":13394,\"regionX\":15,\"regionY\":33,\"z\":2,\"color\":\"#FF797979\"},{\"regionId\":13394,\"regionX\":16,\"regionY\":33,\"z\":2,\"color\":\"#FF797979\"},{\"regionId\":13394,\"regionX\":13,\"regionY\":40,\"z\":2,\"color\":\"#FF08DEFF\",\"label\":\"No Poke\"},{\"regionId\":13394,\"regionX\":17,\"regionY\":44,\"z\":2,\"color\":\"#FF08DEFF\",\"label\":\"Safe\"},{\"regionId\":13394,\"regionX\":13,\"regionY\":38,\"z\":2,\"color\":\"#FF1AAA01\",\"label\":\"Tendrils Spawn\"},{\"regionId\":13394,\"regionX\":13,\"regionY\":45,\"z\":2,\"color\":\"#FF797979\"}]";
    private final String tightropeMarkers = "[{\"regionId\":13139,\"regionX\":50,\"regionY\":54,\"z\":2,\"color\":\"#FF08DEFF\",\"label\":\"Telegrab\"},{\"regionId\":13139,\"regionX\":46,\"regionY\":54,\"z\":2,\"color\":\"#FF1AAA01\",\"label\":\"Chinspot\"},{\"regionId\":13139,\"regionX\":47,\"regionY\":55,\"z\":2,\"color\":\"#FF1AAA01\",\"label\":\"Safespot\"},{\"regionId\":13139,\"regionX\":60,\"regionY\":57,\"z\":2,\"color\":\"#FFFFFFFF\",\"label\":\"Drop Crystal\"},{\"regionId\":13139,\"regionX\":42,\"regionY\":49,\"z\":2,\"color\":\"#FFFFFFFF\"},{\"regionId\":13139,\"regionX\":42,\"regionY\":53,\"z\":2,\"color\":\"#FFFFFFFF\"},{\"regionId\":13139,\"regionX\":42,\"regionY\":51,\"z\":2,\"color\":\"#FFFFFFFF\"},{\"regionId\":13139,\"regionX\":41,\"regionY\":51,\"z\":2,\"color\":\"#00FFFFFF\",\"label\":\"Mage Safespot\"}]";
    private final String guardiansMarkers = "[{\"regionId\":13138,\"regionX\":47,\"regionY\":20,\"z\":1,\"color\":\"#FFFF0000\"},{\"regionId\":13138,\"regionX\":46,\"regionY\":20,\"z\":1,\"color\":\"#FFFF0000\"}]";
    private final String vasaMarkers = "[{\"regionId\":13138,\"regionX\":38,\"regionY\":52,\"z\":1,\"color\":\"#FFFFFFFF\"},{\"regionId\":13138,\"regionX\":38,\"regionY\":50,\"z\":1,\"color\":\"#FFFFFFFF\"},{\"regionId\":13138,\"regionX\":38,\"regionY\":46,\"z\":1,\"color\":\"#FFFFFFFF\"},{\"regionId\":13138,\"regionX\":38,\"regionY\":44,\"z\":1,\"color\":\"#FFFFFFFF\"},{\"regionId\":13138,\"regionX\":38,\"regionY\":42,\"z\":1,\"color\":\"#FFFFFFFF\"},{\"regionId\":13138,\"regionX\":38,\"regionY\":53,\"z\":1,\"color\":\"#FFFF0000\"},{\"regionId\":13138,\"regionX\":38,\"regionY\":51,\"z\":1,\"color\":\"#FFFF0000\"},{\"regionId\":13138,\"regionX\":38,\"regionY\":45,\"z\":1,\"color\":\"#FFFF0000\"},{\"regionId\":13138,\"regionX\":38,\"regionY\":43,\"z\":1,\"color\":\"#FFFF0000\"},{\"regionId\":13138,\"regionX\":38,\"regionY\":41,\"z\":1,\"color\":\"#FFFF0000\"},{\"regionId\":13138,\"regionX\":38,\"regionY\":48,\"z\":1,\"color\":\"#FFFFFFFF\"},{\"regionId\":13138,\"regionX\":38,\"regionY\":47,\"z\":1,\"color\":\"#FFFF0000\"},{\"regionId\":13138,\"regionX\":38,\"regionY\":49,\"z\":1,\"color\":\"#FFFF0000\"},{\"regionId\":13138,\"regionX\":37,\"regionY\":48,\"z\":1,\"color\":\"#00FFFFFF\",\"label\":\"Do Not Cross\"}]";
    private final String mysticMarkers = "[{\"regionId\":13138,\"regionX\":10,\"regionY\":17,\"z\":1,\"color\":\"#FFFF00FF\",\"label\":\"Tendril Skip\"},{\"regionId\":13138,\"regionX\":21,\"regionY\":10,\"z\":1,\"color\":\"#FFFFFFFF\"},{\"regionId\":13138,\"regionX\":22,\"regionY\":10,\"z\":1,\"color\":\"#FFFFFFFF\"},{\"regionId\":13138,\"regionX\":23,\"regionY\":10,\"z\":1,\"color\":\"#FFFFFFFF\"},{\"regionId\":13138,\"regionX\":26,\"regionY\":23,\"z\":1,\"color\":\"#FFFFFFFF\"},{\"regionId\":13138,\"regionX\":23,\"regionY\":25,\"z\":1,\"color\":\"#FFFFFFFF\"},{\"regionId\":13138,\"regionX\":12,\"regionY\":25,\"z\":1,\"color\":\"#FFFFFFFF\"},{\"regionId\":13138,\"regionX\":11,\"regionY\":25,\"z\":1,\"color\":\"#FFFFFFFF\"},{\"regionId\":13138,\"regionX\":9,\"regionY\":23,\"z\":1,\"color\":\"#FFFFFFFF\"},{\"regionId\":13138,\"regionX\":7,\"regionY\":15,\"z\":1,\"color\":\"#FFFFFFFF\"}]";
    private final String muttadileMarkers = "[{\"regionId\":13139,\"regionX\":48,\"regionY\":1,\"z\":1,\"color\":\"#FF797979\"},{\"regionId\":13139,\"regionX\":47,\"regionY\":1,\"z\":1,\"color\":\"#FF797979\"},{\"regionId\":13139,\"regionX\":39,\"regionY\":6,\"z\":1,\"color\":\"#00FFFFFF\",\"label\":\"Bucket\"},{\"regionId\":13139,\"regionX\":51,\"regionY\":16,\"z\":1,\"color\":\"#FF1AAA01\",\"label\":\"Tank\"},{\"regionId\":13139,\"regionX\":37,\"regionY\":10,\"z\":1,\"color\":\"#FF1AAA01\",\"label\":\"Safespot\"},{\"regionId\":13139,\"regionX\":51,\"regionY\":14,\"z\":1,\"color\":\"#FF1AAA01\",\"label\":\"Tank\"}]";
    private final String olmMarkers = "[{\"regionId\":12889,\"regionX\":37,\"regionY\":45,\"z\":0,\"color\":\"#FF08DEFF\"},{\"regionId\":12889,\"regionX\":28,\"regionY\":45,\"z\":0,\"color\":\"#FF08DEFF\"},{\"regionId\":12889,\"regionX\":28,\"regionY\":43,\"z\":0,\"color\":\"#FF08DEFF\"},{\"regionId\":12889,\"regionX\":37,\"regionY\":43,\"z\":0,\"color\":\"#FF08DEFF\"},{\"regionId\":12889,\"regionX\":35,\"regionY\":45,\"z\":0,\"color\":\"#FFFF00FF\"},{\"regionId\":12889,\"regionX\":35,\"regionY\":43,\"z\":0,\"color\":\"#FFFF00FF\"},{\"regionId\":12889,\"regionX\":30,\"regionY\":43,\"z\":0,\"color\":\"#FFFF00FF\"},{\"regionId\":12889,\"regionX\":30,\"regionY\":45,\"z\":0,\"color\":\"#FFFF00FF\"},{\"regionId\":12889,\"regionX\":37,\"regionY\":50,\"z\":0,\"color\":\"#FFFFFFFF\",\"label\":\"Mage Start\"},{\"regionId\":12889,\"regionX\":28,\"regionY\":38,\"z\":0,\"color\":\"#FFFFFFFF\",\"label\":\"Mage Start\"},{\"regionId\":12889,\"regionX\":28,\"regionY\":47,\"z\":0,\"color\":\"#FFFF0000\",\"label\":\"Thumb\"},{\"regionId\":12889,\"regionX\":37,\"regionY\":41,\"z\":0,\"color\":\"#FFFF0000\",\"label\":\"Thumb\"},{\"regionId\":12889,\"regionX\":28,\"regionY\":50,\"z\":0,\"color\":\"#FFFF0000\",\"label\":\"Pinky\"},{\"regionId\":12889,\"regionX\":37,\"regionY\":38,\"z\":0,\"color\":\"#FFFF0000\",\"label\":\"Pinky\"},{\"regionId\":12889,\"regionX\":35,\"regionY\":44,\"z\":0,\"color\":\"#00FF00FF\",\"label\":\"Mage Skip\"},{\"regionId\":12889,\"regionX\":30,\"regionY\":44,\"z\":0,\"color\":\"#00FF00FF\",\"label\":\"Mage Skip\"},{\"regionId\":12889,\"regionX\":28,\"regionY\":44,\"z\":0,\"color\":\"#0008DEFF\",\"label\":\"Runners\"},{\"regionId\":12889,\"regionX\":37,\"regionY\":44,\"z\":0,\"color\":\"#0008DEFF\",\"label\":\"Runners\"}]";
    private final String tipsMarkers = "[{\"regionId\":13137,\"regionX\":13,\"regionY\":48,\"z\":1,\"color\":\"#00FFFFFF\",\"label\":\"Salve\"},{\"regionId\":13137,\"regionX\":16,\"regionY\":50,\"z\":1,\"color\":\"#00FFFFFF\",\"label\":\"Axe Check\"},{\"regionId\":13141,\"regionX\":49,\"regionY\":23,\"z\":2,\"color\":\"#00FFFFFF\",\"label\":\"Withdraw All Brews\"},{\"regionId\":13141,\"regionX\":48,\"regionY\":23,\"z\":2,\"color\":\"#00FFFFFF\",\"label\":\"6-8 Doses Restores\"},{\"regionId\":13397,\"regionX\":20,\"regionY\":12,\"z\":3,\"color\":\"#FFFFFFFF\",\"label\":\"Drop Seeds\"},{\"regionId\":13141,\"regionX\":48,\"regionY\":2,\"z\":1,\"color\":\"#00FFFFFF\",\"label\":\"Drop: Axe, Stamina\"},{\"regionId\":13393,\"regionX\":15,\"regionY\":36,\"z\":2,\"color\":\"#00FFFFFF\",\"label\":\"Sip: Overload, Stamina\"},{\"regionId\":13140,\"regionX\":47,\"regionY\":1,\"z\":2,\"color\":\"#00FFFFFF\",\"label\":\"Equip Range\"},{\"regionId\":13139,\"regionX\":48,\"regionY\":34,\"z\":3,\"color\":\"#FFFF00FF\"},{\"regionId\":13139,\"regionX\":54,\"regionY\":37,\"z\":3,\"color\":\"#FFFF00FF\"},{\"regionId\":13139,\"regionX\":48,\"regionY\":33,\"z\":3,\"color\":\"#FFFFFFFF\",\"label\":\"Drop 2 Pots\"},{\"regionId\":13395,\"regionX\":15,\"regionY\":47,\"z\":3,\"color\":\"#FF797979\",\"label\":\"Pots\"},{\"regionId\":13139,\"regionX\":50,\"regionY\":44,\"z\":3,\"color\":\"#00FFFFFF\",\"label\":\"Bank: Bp, Salve, Pickaxe, Axe, Lockpick\"}]";
    private final String easterMarkers = "[{\"regionId\":12598,\"regionX\":44,\"regionY\":18,\"z\":0,\"color\":\"#FFE81172\",\"label\":\"All members - Without them we wouldn\\u0027t be here!\"},{\"regionId\":12598,\"regionX\":42,\"regionY\":20,\"z\":0,\"color\":\"#FFFF9000\",\"label\":\"Infernal\\u0027s Hall of Fame\"},{\"regionId\":12598,\"regionX\":46,\"regionY\":18,\"z\":0,\"color\":\"#FF994040\",\"label\":\"Sting Rae - Legend\"},{\"regionId\":12598,\"regionX\":45,\"regionY\":16,\"z\":0,\"color\":\"#FFD1841A\",\"label\":\"Billers - Beauty of a person\"},{\"regionId\":12598,\"regionX\":48,\"regionY\":17,\"z\":0,\"color\":\"#FF9D0A0A\",\"label\":\"Xray - CM Documents\"},{\"regionId\":12598,\"regionX\":45,\"regionY\":17,\"z\":0,\"color\":\"#FFFF0000\",\"label\":\"Afk Jasper - Leader of the Clan\"},{\"regionId\":12598,\"regionX\":47,\"regionY\":15,\"z\":0,\"color\":\"#FF00FF1D\",\"label\":\"Bztm - TOB Documents\"},{\"regionId\":12598,\"regionX\":48,\"regionY\":19,\"z\":0,\"color\":\"#FF1425AA\",\"label\":\"Blue helper - Community Involvement\"},{\"regionId\":12598,\"regionX\":47,\"regionY\":17,\"z\":0,\"color\":\"#FF4CCD1E\",\"label\":\"Nugget - Website Developer\"},{\"regionId\":12598,\"regionX\":45,\"regionY\":15,\"z\":0,\"color\":\"#FFAB0FDF\",\"label\":\"Slay Amani - Lovely Events\"},{\"regionId\":12598,\"regionX\":46,\"regionY\":15,\"z\":0,\"color\":\"#FF001DFF\",\"label\":\"Rick - Plugin Developer/Events\"},{\"regionId\":12598,\"regionX\":48,\"regionY\":14,\"z\":0,\"color\":\"#FF120A15\",\"label\":\"Skala - Friendly \\u0026 Lovely\"},{\"regionId\":12598,\"regionX\":44,\"regionY\":16,\"z\":0,\"color\":\"#FFEFFD00\",\"label\":\"i Tricky i - Admin King\"},{\"regionId\":12598,\"regionX\":44,\"regionY\":14,\"z\":0,\"color\":\"#FF1071C8\",\"label\":\"Puzzle - Tiles Boy\"},{\"regionId\":12598,\"regionX\":47,\"regionY\":16,\"z\":0,\"color\":\"#FFECEAED\",\"label\":\"Trackies - Good boy\"},{\"regionId\":12598,\"regionX\":48,\"regionY\":16,\"z\":0,\"color\":\"#FF742899\",\"label\":\"Tiring - Lazy person\"},{\"regionId\":12598,\"regionX\":44,\"regionY\":15,\"z\":0,\"color\":\"#FF2B0B3A\",\"label\":\"Zansus - Our great Leader!\"},{\"regionId\":12598,\"regionX\":46,\"regionY\":17,\"z\":0,\"color\":\"#FF0F0B11\",\"label\":\"Firnik - Super friendly!\"},{\"regionId\":12598,\"regionX\":43,\"regionY\":14,\"z\":0,\"color\":\"#FF17D03A\",\"label\":\"Danmingo - Heron\"},{\"regionId\":12598,\"regionX\":48,\"regionY\":18,\"z\":0,\"color\":\"#FFB82020\",\"label\":\"Stevo - What a guy\"},{\"regionId\":12598,\"regionX\":48,\"regionY\":15,\"z\":0,\"color\":\"#FFEF9D0B\",\"label\":\"Sjakupovic - Java God\"},{\"regionId\":12598,\"regionX\":47,\"regionY\":14,\"z\":0,\"color\":\"#FF0ECDB3\",\"label\":\"Ziggy man 77 - Never Sleeps\"},{\"regionId\":12598,\"regionX\":47,\"regionY\":18,\"z\":0,\"color\":\"#FFF700FC\",\"label\":\"All Event-Helpers!\"},{\"regionId\":12598,\"regionX\":45,\"regionY\":14,\"z\":0,\"color\":\"#FFFFFA00\",\"label\":\"All Mentors!\"},{\"regionId\":12598,\"regionX\":46,\"regionY\":16,\"z\":0,\"color\":\"#FF485C83\",\"label\":\"7T\"}]";

    private Map<String, Collection<ColorTileMarker>> markerMapping;

    @Inject
    private MarkersManager(Gson gson, Client client, InfernalFCConfig config) {
        this.client = client;
        this.config = config;
        this.gson = gson;
        loadPoints();
    }

    private Collection<ColorTileMarker> translateToColorTileMarker(Collection<GroundMarkerPoint> points)
    {
        if (points.isEmpty())
        {
            return Collections.emptyList();
        }

        return points.stream()
                .map(point -> new ColorTileMarker(
                        WorldPoint.fromRegion(point.getRegionId(), point.getRegionX(), point.getRegionY(), point.getZ()),
                        point.getColor(), point.getLabel()))
                .flatMap(colorTile ->
                {
                    final Collection<WorldPoint> localWorldPoints = WorldPoint.toLocalInstance(client, colorTile.getWorldPoint());
                    return localWorldPoints.stream().map(wp -> new ColorTileMarker(wp, colorTile.getColor(), colorTile.getLabel()));
                })
                .collect(Collectors.toList());
    }

    public void loadPoints() {
        markerMapping = new HashMap<>();
        markerMapping.put("Tekton", translateToColorTileMarker(gson.fromJson(tektonMarkers, new TypeToken<List<GroundMarkerPoint>>(){}.getType())));
        markerMapping.put("Crabs", translateToColorTileMarker(gson.fromJson(crabsMarkers, new TypeToken<List<GroundMarkerPoint>>(){}.getType())));
        markerMapping.put("Icedemon", translateToColorTileMarker(gson.fromJson(iceMarkers, new TypeToken<List<GroundMarkerPoint>>(){}.getType())));
        markerMapping.put("Shamans", translateToColorTileMarker(gson.fromJson(shamanMarkers, new TypeToken<List<GroundMarkerPoint>>(){}.getType())));
        markerMapping.put("Vanguards", translateToColorTileMarker(gson.fromJson(vanguardsMarkers, new TypeToken<List<GroundMarkerPoint>>(){}.getType())));
        markerMapping.put("Thieving", translateToColorTileMarker(gson.fromJson(thievingMarkers, new TypeToken<List<GroundMarkerPoint>>(){}.getType())));
        markerMapping.put("Vespula", translateToColorTileMarker(gson.fromJson(vespulaMarkers, new TypeToken<List<GroundMarkerPoint>>(){}.getType())));
        markerMapping.put("Tightrope", translateToColorTileMarker(gson.fromJson(tightropeMarkers, new TypeToken<List<GroundMarkerPoint>>(){}.getType())));
        markerMapping.put("Guardians", translateToColorTileMarker(gson.fromJson(guardiansMarkers, new TypeToken<List<GroundMarkerPoint>>(){}.getType())));
        markerMapping.put("Vasa", translateToColorTileMarker(gson.fromJson(vasaMarkers, new TypeToken<List<GroundMarkerPoint>>(){}.getType())));
        markerMapping.put("Mystics", translateToColorTileMarker(gson.fromJson(mysticMarkers, new TypeToken<List<GroundMarkerPoint>>(){}.getType())));
        markerMapping.put("Muttadile", translateToColorTileMarker(gson.fromJson(muttadileMarkers, new TypeToken<List<GroundMarkerPoint>>(){}.getType())));
        markerMapping.put("Olm", translateToColorTileMarker(gson.fromJson(olmMarkers, new TypeToken<List<GroundMarkerPoint>>(){}.getType())));
        markerMapping.put("Tips", translateToColorTileMarker(gson.fromJson(tipsMarkers, new TypeToken<List<GroundMarkerPoint>>(){}.getType())));
        markerMapping.put("EasterEgg", translateToColorTileMarker(gson.fromJson(easterMarkers, new TypeToken<List<GroundMarkerPoint>>(){}.getType())));
        markerMapping.put("Test", translateToColorTileMarker(gson.fromJson(testMarkers, new TypeToken<List<GroundMarkerPoint>>(){}.getType())));
    }


    public Collection<ColorTileMarker> getMarkers() {
        List<ColorTileMarker> anotherList = new ArrayList<>();

        if (config.chamberTektonOption()) {
            anotherList.addAll(markerMapping.get("Tekton"));
        }
        if (config.chamberCrabOption()) {
            anotherList.addAll(markerMapping.get("Crabs"));
        }
        if (config.chamberIceOption()) {
            anotherList.addAll(markerMapping.get("Icedemon"));
        }
        if (config.chamberShamansOption()) {
            anotherList.addAll(markerMapping.get("Shamans"));
        }
        if (config.chamberVanguardsOption()) {
            anotherList.addAll(markerMapping.get("Vanguards"));
        }
        if (config.chamberThievingOption()) {
            anotherList.addAll(markerMapping.get("Thieving"));
        }
        if (config.chamberVespulaOption()) {
            anotherList.addAll(markerMapping.get("Vespula"));
        }
        if (config.chamberTightropeOption()) {
            anotherList.addAll(markerMapping.get("Tightrope"));
        }
        if (config.chamberGuardiansOption()) {
            anotherList.addAll(markerMapping.get("Guardians"));
        }
        if (config.chamberVasaOption()) {
            anotherList.addAll(markerMapping.get("Vasa"));
        }
        if (config.chamberMysticOption()) {
            anotherList.addAll(markerMapping.get("Mystics"));
        }
        if (config.chamberMuttadileOption()) {
            anotherList.addAll(markerMapping.get("Muttadile"));
        }
        if (config.chamberOlmOption()) {
            anotherList.addAll(markerMapping.get("Olm"));
        }
        if (config.chamberTipsOption()) {
            anotherList.addAll(markerMapping.get("Tips"));
        }
        if (config.chamberEasterEggOption()) {
            anotherList.addAll(markerMapping.get("EasterEgg"));
        }
        if (config.chamberTestOption()) {
            anotherList.addAll(markerMapping.get("Test"));
        }
        return anotherList;
    }
}
