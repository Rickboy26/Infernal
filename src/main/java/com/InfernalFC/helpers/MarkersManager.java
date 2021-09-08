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
    private final String tektonMarkers = "[{\"regionId\":4919,\"regionX\":37,\"regionY\":46,\"z\":0,\"color\":\"#FF04FFD4\"}]";

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
    }

    public Collection<ColorTileMarker> getMarkers() {
        List<ColorTileMarker> anotherList = new ArrayList<>();

        if (config.chamberTektonOption()) {
            anotherList.addAll(markerMapping.get("Tekton"));
        }
        return anotherList;
    }
}