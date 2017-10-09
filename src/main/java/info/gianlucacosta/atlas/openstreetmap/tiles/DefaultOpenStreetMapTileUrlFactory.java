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
