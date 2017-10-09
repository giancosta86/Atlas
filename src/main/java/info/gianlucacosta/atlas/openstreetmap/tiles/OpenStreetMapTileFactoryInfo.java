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

package info.gianlucacosta.atlas.openstreetmap.tiles;

import org.jxmapviewer.viewer.TileFactoryInfo;

import java.util.Optional;

/**
 * Tile factory info tailored to OpenStreetMap
 */
public class OpenStreetMapTileFactoryInfo extends TileFactoryInfo {
    private static final String DEFAULT_OPEN_STREET_MAP_ATTRIBUTION =
            "© <a href='http://openstreetmap.org'>OpenStreetMap</a> contributors";

    private final OpenStreetMapTileUrlFactory tileUrlFactory;
    private final String attribution;

    public OpenStreetMapTileFactoryInfo(
            String name,
            String baseURL,
            int maxOpenStreetMapZoom,
            Optional<String> attributionOption
    ) {
        this(
                name,
                baseURL,
                2,
                maxOpenStreetMapZoom,
                18,
                256,
                new DefaultOpenStreetMapTileUrlFactory(
                        baseURL,
                        "png"
                ),
                attributionOption
        );
    }

    /**
     * @param name                 The name of the tile provider (mainly for UI purposes)
     * @param baseURL              The base URL of the tile provider
     * @param minOpenStreetMapZoom The minimum zoom level according to OpenStreetMap's
     *                             convention (0 = world-level zoom).
     *                             Actually, it cannot be &lt; 2 for graphical reasons.
     * @param maxOpenStreetMapZoom The maximum zoom level according to OpenStreetMap's
     *                             convention (usually 18)
     * @param zoomLevelsOnProvider The number of zoom level available on the tile
     *                             provider
     * @param tileSize             The size of a square tile (usually 256 px)
     * @param tileUrlFactory       The factory used to generate tile URLs for download
     * @param attributionOption    A string (usually with HTML tags) used to mention the
     *                             tile provider. If missing, a default one is provided
     */
    public OpenStreetMapTileFactoryInfo(
            String name,
            String baseURL,
            int minOpenStreetMapZoom,
            int maxOpenStreetMapZoom,
            int zoomLevelsOnProvider,
            int tileSize,
            OpenStreetMapTileUrlFactory tileUrlFactory,
            Optional<String> attributionOption
    ) {
        super(
                name,
                zoomLevelsOnProvider - maxOpenStreetMapZoom,
                zoomLevelsOnProvider - minOpenStreetMapZoom,
                zoomLevelsOnProvider,
                tileSize,
                true,
                true,
                baseURL,
                "x",
                "y",
                "z"
        );

        if (minOpenStreetMapZoom < 2) {
            throw new IllegalArgumentException();
        }

        if (maxOpenStreetMapZoom - minOpenStreetMapZoom + 1 > zoomLevelsOnProvider) {
            throw new IllegalArgumentException();
        }

        this.tileUrlFactory = tileUrlFactory;

        this.attribution =
                attributionOption.orElse(DEFAULT_OPEN_STREET_MAP_ATTRIBUTION);
    }

    @Override
    public String getTileUrl(int x, int y, int zoom) {
        int openStreetMapZoom =
                getTotalMapZoom() - zoom;

        return tileUrlFactory.getTileUrl(openStreetMapZoom, x, y);
    }


    public String getAttribution() {
        return attribution;
    }
}
