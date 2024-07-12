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
