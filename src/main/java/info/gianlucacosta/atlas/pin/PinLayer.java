/*^
  ===========================================================================
  Atlas
  ===========================================================================
  Copyright (C) 2017 Gianluca Costa
  ===========================================================================
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
  ===========================================================================
*/

package info.gianlucacosta.atlas.pin;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.WaypointPainter;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * A simplified and enhanced way to manage pins on a map.
 * <p>
 * It can be added to a map by calling the map's setOverlayPainter() method:
 * then, the layer's method can be used to add/remove pins.
 * <p>
 * Because of its internal data structures, its dedicated methods should be
 * the only way for adding/removing pins, in lieu of raw waypoint-related methods.
 *
 * @param <T> The underlying pin type
 */
public class PinLayer<T extends Pin> extends WaypointPainter<PinWaypoint> {
    private final JXMapViewer mapViewer;
    private final Function<T, PinDrawing<T>> pinDrawingFactory;

    private Map<UUID, PinWaypoint> pinWaypoints =
            new HashMap<>();


    /**
     * Creates the pin layer
     *
     * @param mapViewer         The target map
     * @param pinDrawingFactory The Pin -&gt; PinDrawing function used to actually draw pins
     */
    public PinLayer(JXMapViewer mapViewer, Function<T, PinDrawing<T>> pinDrawingFactory) {
        this.mapViewer = mapViewer;
        this.pinDrawingFactory = pinDrawingFactory;
    }


    @Override
    protected void doPaint(Graphics2D g, JXMapViewer mapViewer, int width, int height) {
        getWaypoints()
                .forEach(pinWaypoint -> {
                    Point2D anchorPointInMap =
                            mapViewer
                                    .getTileFactory()
                                    .geoToPixel(
                                            pinWaypoint.getPosition(),
                                            mapViewer.getZoom()
                                    );

                    Rectangle mapBounds =
                            mapViewer.getViewportBounds();


                    PinDrawing pinDrawing =
                            pinWaypoint.getPinDrawing();


                    Point anchorPointInPinDrawing =
                            pinDrawing.getLocalAnchorPoint();

                    int pinDrawingX =
                            (int) (anchorPointInMap.getX() - anchorPointInPinDrawing.getX() - mapBounds.getX());

                    int pinDrawingY =
                            (int) (anchorPointInMap.getY() - anchorPointInPinDrawing.getY() - mapBounds.getY());

                    pinDrawing.setLocation(
                            pinDrawingX,
                            pinDrawingY
                    );
                });
    }


    /**
     * Replaces all the pins on the map with the given pins
     *
     * @param pins The new pins
     */
    public void setPins(Collection<T> pins) {
        pinWaypoints
                .values()
                .forEach(pinWaypoint ->
                        mapViewer.remove(
                                pinWaypoint.getPinDrawing()
                        )
                );


        pinWaypoints =
                pins
                        .stream()
                        .map(pin -> {
                            PinDrawing<T> pinDrawing =
                                    pinDrawingFactory.apply(pin);

                            return new PinWaypoint<>(
                                    pin,
                                    pinDrawing
                            );
                        })
                        .collect(Collectors.toMap(
                                pinWaypoint ->
                                        pinWaypoint.getPin().getId(),
                                Function.identity()
                        ));

        pinWaypoints
                .values()
                .forEach(pinWaypoint ->
                        mapViewer.add(
                                pinWaypoint.getPinDrawing()
                        )
                );

        setWaypoints(
                new HashSet<>(pinWaypoints.values())
        );

        mapViewer.repaint();
    }


    /**
     * Ensures that the given pin is on the map.
     * <p>
     * If the pin is already on the map according to its id, deletes it then adds
     * its given new version (which is especially useful for read-only pins,
     * whose new versions share the same id).
     * <p>
     * If the pin was not on the map, just adds it.
     *
     * @param pin The pin that must be on the map
     */
    public void ensurePin(T pin) {
        Set<PinWaypoint> updatedWaypoints =
                new HashSet<>(getWaypoints());

        PinWaypoint existingPinWaypoint =
                pinWaypoints.get(pin.getId());

        if (existingPinWaypoint != null) {
            mapViewer.remove(
                    existingPinWaypoint.getPinDrawing()
            );

            updatedWaypoints.remove(existingPinWaypoint);
        }


        PinDrawing<T> newPinDrawing =
                pinDrawingFactory.apply(pin);

        PinWaypoint<T> newPinWaypoint =
                new PinWaypoint<>(
                        pin,
                        newPinDrawing
                );

        pinWaypoints.put(
                pin.getId(),
                newPinWaypoint
        );


        mapViewer.add(
                newPinDrawing
        );

        updatedWaypoints.add(newPinWaypoint);

        setWaypoints(updatedWaypoints);

        mapViewer.repaint();
    }


    /**
     * Ensures that the given pin is <i>not</i> on the map.
     * <p>
     * If the pin is not on the map, nothing happens.
     *
     * @param pin The pin that must not be on the map
     */
    public void removePin(T pin) {
        PinWaypoint existingPinWaypoint =
                pinWaypoints.remove(pin.getId());

        if (existingPinWaypoint != null) {
            mapViewer.remove(
                    existingPinWaypoint.getPinDrawing()
            );

            Set<PinWaypoint> updatedWaypoints =
                    new HashSet<>(getWaypoints());

            updatedWaypoints.remove(existingPinWaypoint);
            setWaypoints(updatedWaypoints);

            mapViewer.repaint();
        }
    }
}
