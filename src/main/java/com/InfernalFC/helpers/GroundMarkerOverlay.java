/*
 * Copyright (c) 2018, TheLonelyDev <https://github.com/TheLonelyDev>
 * Copyright (c) 2018, Adam <Adam@sigterm.info>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.InfernalFC.helpers;

import com.InfernalFC.InfernalFCConfig;
import com.InfernalFC.InfernalFCPlugin;
import com.InfernalFC.models.ColorTileMarker;
import com.InfernalFC.models.GroundMarkerPoint;
import com.google.common.base.Strings;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Stroke;
import java.util.Arrays;
import java.util.Collection;
import javax.annotation.Nullable;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.Perspective;
import net.runelite.api.Point;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
import net.runelite.client.ui.overlay.OverlayUtil;

public class GroundMarkerOverlay extends Overlay
{
    private static final int MAX_DRAW_DISTANCE = 32;

    @Inject
    private InfernalFCConfig config;

    @Inject
    private MarkersManager markersManager;

    private final InfernalFCPlugin plugin;
    private final Client client;

    @Inject
    private GroundMarkerOverlay(Client client, InfernalFCPlugin plugin)
    {
        this.client = client;
        this.plugin = plugin;
        setPosition(OverlayPosition.DYNAMIC);
        setPriority(OverlayPriority.LOW);
        setLayer(OverlayLayer.ABOVE_SCENE);
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
//        final Collection<ColorTileMarker> points = plugin.getPoints();
        final Collection<ColorTileMarker> points = markersManager.getMarkers();
        if (points.isEmpty())
        {
            return null;
        }

//        Stroke stroke = new BasicStroke((float) config.borderWidth());
        Stroke stroke = new BasicStroke(1);
        for (final ColorTileMarker point : points)
        {
            WorldPoint worldPoint = point.getWorldPoint();
            if (worldPoint.getPlane() != client.getPlane())
            {
                continue;
            }

            Color tileColor = point.getColor();
//            if (tileColor == null || !config.rememberTileColors())
//            {
//                // If this is an old tile which has no color, or rememberTileColors is off, use marker color
//                tileColor = config.markerColor();
//            }

            drawTile(graphics, worldPoint, tileColor, point.getLabel(), stroke);
        }

        return null;
    }

    private void drawTile(Graphics2D graphics, WorldPoint point, Color color, @Nullable String label, Stroke borderStroke)
    {
        WorldPoint playerLocation = client.getLocalPlayer().getWorldLocation();

        if (point.distanceTo(playerLocation) >= MAX_DRAW_DISTANCE)
        {
            return;
        }

        LocalPoint lp = LocalPoint.fromWorld(client, point);
        if (lp == null)
        {
            return;
        }

        Polygon poly = Perspective.getCanvasTilePoly(client, lp);
        if (poly != null)
        {
            OverlayUtil.renderPolygon(graphics, poly, color, borderStroke);
        }

        if (!Strings.isNullOrEmpty(label))
        {
            Point canvasTextLocation = Perspective.getCanvasTextLocation(client, graphics, lp, label, 0);
            if (canvasTextLocation != null)
            {
                OverlayUtil.renderTextLocation(graphics, canvasTextLocation, label, color);
            }
        }
    }
}
