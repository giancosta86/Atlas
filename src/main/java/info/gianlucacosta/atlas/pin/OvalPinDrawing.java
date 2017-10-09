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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.BiConsumer;

/**
 * Oval visual representation of a pin.
 *
 * @param <T> The underlying pin type
 */
public class OvalPinDrawing<T extends CommonPin> extends PinDrawing<T> {
    public OvalPinDrawing(
            T pin,
            Dimension dimension,
            BiConsumer<T, MouseEvent> onClicked
    ) {
        super(pin);

        setToolTipText(pin.getLabelText());

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                onClicked.accept(pin, event);
            }
        });

        setPreferredSize(dimension);
        setSize(dimension);

        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(
                pin.getColor()
        );

        g.fillOval(
                0,
                0,
                getWidth(),
                getHeight()
        );


        g.setColor(Color.BLACK);

        g.drawOval(
                0,
                0,
                getWidth() - 1,
                getHeight() - 1
        );
    }

    @Override
    protected Point getLocalAnchorPoint() {
        return new Point(
                getWidth() / 2,
                getHeight() / 2
        );
    }
}
