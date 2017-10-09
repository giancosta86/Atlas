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

import javax.swing.JPanel;
import java.awt.Point;

/**
 * Visual representation of a pin on the map surface
 *
 * @param <T> The underlying pin type
 */
public abstract class PinDrawing<T extends Pin> extends JPanel {
    protected final T pin;

    public PinDrawing(T pin) {
        this.pin = pin;
    }

    /**
     * @return The coordinates - within the pin's coordinate system - of the anchor
     * point (= where the pin is attached to the map)
     */
    protected abstract Point getLocalAnchorPoint();
}
