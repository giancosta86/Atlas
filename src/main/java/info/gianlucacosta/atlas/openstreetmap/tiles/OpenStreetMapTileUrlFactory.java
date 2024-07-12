package info.gianlucacosta.atlas.openstreetmap.tiles;

/**
 * Generates URLs for OpenStreetMap tiles
 */
@FunctionalInterface
public interface OpenStreetMapTileUrlFactory {
    /**
     * Returns the full URL string (including protocol and server) for the
     * tile having the given coordinates.
     *
     * @param z The zoom level (according to OpenStreetMap's convention)
     * @param x The x coordinate
     * @param y The y coordinate
     * @return The full URL string
     */
    String getTileUrl(int z, int x, int y);
}
