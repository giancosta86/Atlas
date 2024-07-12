package info.gianlucacosta.atlas.pin;

import java.awt.Color;

/**
 * Common pin, including a title and a color
 */
public interface CommonPin extends Pin {
    String getLabelText();

    Color getColor();
}
