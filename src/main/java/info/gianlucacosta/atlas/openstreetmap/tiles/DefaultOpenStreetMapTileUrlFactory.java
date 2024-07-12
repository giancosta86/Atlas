package info.gianlucacosta.atlas.openstreetmap.tiles;

/**
 * Relies on a standard URL scheme for generating OpenStreetMap tile URLs:
 * <b>BASE_URL</b>/<b>z</b>/<b>x</b>/<b>y</b>.<b>EXTENSION</b>.
 */
public class DefaultOpenStreetMapTileUrlFactory implements OpenStreetMapTileUrlFactory {
    private final String baseURL;
    private final String extension;

    /**
     * Removes the trailing "/" from the given base URL
     *
     * @param baseURL The base URL to normalize
     * @return The normalized base URL
     */
    public static String normalizeBaseURL(String baseURL) {
        return baseURL.endsWith("/") ?
                baseURL.substring(
                        0,
                        baseURL.length() - 1
                )
                :
                baseURL;
    }

    /**
     * @param baseURL   The base URL (including the protocol)
     * @param extension The file extension for map tiles (usually "png")
     */
    public DefaultOpenStreetMapTileUrlFactory(String baseURL, String extension) {
        this.baseURL =
                normalizeBaseURL(baseURL);

        this.extension =
                extension.startsWith(".")
                        ?
                        extension.substring(1)
                        :
                        extension;
    }

    @Override
    public String getTileUrl(int z, int x, int y) {
        return String.format(
                "%s/%s/%s/%s.%s",
                baseURL,
                z,
                x,
                y,
                extension
        );
    }
}
