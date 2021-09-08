package com.InfernalFC.helpers;

import com.InfernalFC.InfernalFCConfig;
import com.InfernalFC.models.ColorTileMarker;
import com.InfernalFC.models.GroundMarkerPoint;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.runelite.api.Client;
import net.runelite.api.coords.WorldPoint;

import javax.inject.Inject;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class MarkersManager {

    private final Client client;
    private final InfernalFCConfig config;
    private final String testMarkers = "[{\"regionId\":12850,\"regionX\":7,\"regionY\":12,\"z\":2,\"color\":\"#FFFFFF00\"}]";

    private Map<String, Collection<ColorTileMarker>> markerMapping;

    @Inject
    private MarkersManager(Gson gson, Client client, InfernalFCConfig config) {
        this.client = client;
        this.config = config;
        markerMapping = new HashMap<>();
        markerMapping.put("test", translateToColorTileMarker(gson.fromJson(testMarkers, new TypeToken<List<GroundMarkerPoint>>(){}.getType())));
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

    public Collection<ColorTileMarker> getMarkers() {
        List<ColorTileMarker> anotherList = new ArrayList<>();

        if (config.chamberTektonOption()) {
            anotherList.addAll(markerMapping.get("test"));
        }
        return anotherList;
    }
}
