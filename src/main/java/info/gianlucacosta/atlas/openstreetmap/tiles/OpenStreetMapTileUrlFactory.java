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
