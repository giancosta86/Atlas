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

import org.jxmapviewer.viewer.DefaultWaypoint;

/**
 * Internally used to glue pin drawings to the map
 *
 * @param <T> The underlying pin type
 */
class PinWaypoint<T extends Pin> extends DefaultWaypoint {
    private final T pin;
    private final PinDrawing<T> pinDrawing;

    public PinWaypoint(
            T pin,
            PinDrawing<T> pinDrawing
    ) {
        super(pin.getPosition());

        this.pin = pin;

        this.pinDrawing = pinDrawing;
    }


    public T getPin() {
        return pin;
    }

    public PinDrawing<T> getPinDrawing() {
        return pinDrawing;
    }
}
