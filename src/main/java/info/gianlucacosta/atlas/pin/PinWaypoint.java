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
