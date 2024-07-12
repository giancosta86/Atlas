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
