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

package info.gianlucacosta.atlas.openstreetmap;

import info.gianlucacosta.atlas.openstreetmap.tiles.OpenStreetMapTileFactory;
import info.gianlucacosta.zephyros.swing.components.InteractiveHtmlPane;
import org.jxmapviewer.JXMapViewer;

import java.awt.Graphics;

/**
 * Simplified map viewer for OpenStreetMap.
 * <p>
 * In particular, it provides dedicated zoom methods, acting according to
 * OpenStreetMap's convention (that is, 0 = world-level zoom).
 * <p>
 * As an important licensing issue, it always shows the tile provider's
 * attribution notice in a corner - or at least a default one
 * mentioning OpenStreetMap.
 */
public class OpenStreetMapViewer extends JXMapViewer {
    private final InteractiveHtmlPane attributionPane;

    public OpenStreetMapViewer(OpenStreetMapTileFactory tileFactory) {
        setTileFactory(
                tileFactory
        );

        String attribution =
                tileFactory
                        .getOpenStreetMapTileFactoryInfo()
                        .getAttribution();

        attributionPane =
                new InteractiveHtmlPane(
                        attribution
                );

        add(attributionPane);
    }


    public OpenStreetMapTileFactory getOpenStreetMapTileFactory() {
        return (OpenStreetMapTileFactory) getTileFactory();
    }


    /**
     * @return The zoom level according to OpenStreetMap's convention
     */
    public int getOpenStreetMapZoom() {
        return getTileFactory().getInfo().getTotalMapZoom() - getZoom();
    }


    /**
     * Sets the zoom level according to OpenStreetMap's convention
     *
     * @param openStreetMapZoom Zoom level. 0 = world-level zoom
     */
    public void setOpenStreetMapZoom(int openStreetMapZoom) {
        int internalZoom =
                getTileFactory().getInfo().getTotalMapZoom() - openStreetMapZoom;

        setZoom(internalZoom);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        attributionPane.setLocation(
                getWidth() - attributionPane.getWidth(),
                getHeight() - attributionPane.getHeight()
        );
    }
}
