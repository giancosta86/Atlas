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

import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.LocalResponseCache;

import java.nio.file.Path;

/**
 * Tile factory for OpenStreetMap
 */
public class OpenStreetMapTileFactory extends DefaultTileFactory {
    /**
     * @param tileFactoryInfo       The dedicated tile factory info
     * @param threadPoolSize        The size of the thread pool used to download tiles
     * @param geoCacheRootDirectory The root directory for caching maps. Tiles from different servers will be stored to different subdirectories
     * @param alwaysCheckForUpdates true if the online version should always be looked for first
     */
    public OpenStreetMapTileFactory(
            OpenStreetMapTileFactoryInfo tileFactoryInfo,
            int threadPoolSize,
            Path geoCacheRootDirectory,
            boolean alwaysCheckForUpdates
    ) {
        super(tileFactoryInfo);

        setThreadPoolSize(threadPoolSize);

        LocalResponseCache.installResponseCache(
                getInfo().getBaseURL(),
                geoCacheRootDirectory.toFile(),
                alwaysCheckForUpdates
        );
    }


    /**
     * @return The dedicated OpenStreetMap tile factory info
     */
    public OpenStreetMapTileFactoryInfo getOpenStreetMapTileFactoryInfo() {
        return (OpenStreetMapTileFactoryInfo) getInfo();
    }
}
