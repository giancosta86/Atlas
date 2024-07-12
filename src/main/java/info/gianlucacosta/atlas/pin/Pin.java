package info.gianlucacosta.atlas.pin;

import org.jxmapviewer.viewer.GeoPosition;

import java.util.UUID;

/**
 * The most general-purpose pin
 */
public interface Pin {
    UUID getId();

    GeoPosition getPosition();
}
