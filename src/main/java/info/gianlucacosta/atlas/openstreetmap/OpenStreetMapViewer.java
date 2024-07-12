package info.gianlucacosta.atlas.openstreetmap;

import info.gianlucacosta.atlas.openstreetmap.tiles.OpenStreetMapTileFactory;
import info.gianlucacosta.zephyros.swing.components.InteractiveHtmlPane;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.TileFactoryInfo;

import java.awt.Graphics;

/**
 * Simplified map viewer for OpenStreetMap.
 * <p>
 * In particular, it provides dedicated zoom methods, acting according to
 * OpenStreetMap's convention (that is, 0 = world-level zoom).
 * <p>
 * As an important licensing issue, it always shows the tile provider's
 * attribution notice in a corner - or at least a default one
 * mentioning OpenStreetMap.
 */
public class OpenStreetMapViewer extends JXMapViewer {
    private final InteractiveHtmlPane attributionPane;

    public OpenStreetMapViewer() {
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);

        setTileFactory(
                tileFactory);

        String attribution = "";

        attributionPane = new InteractiveHtmlPane(
                attribution);

        add(attributionPane);
    }

    public OpenStreetMapTileFactory getOpenStreetMapTileFactory() {
        return (OpenStreetMapTileFactory) getTileFactory();
    }

    /**
     * @return The zoom level according to OpenStreetMap's convention
     */
    public int getOpenStreetMapZoom() {
        return getTileFactory().getInfo().getTotalMapZoom() - getZoom();
    }

    /**
     * Sets the zoom level according to OpenStreetMap's convention
     *
     * @param openStreetMapZoom Zoom level. 0 = world-level zoom
     */
    public void setOpenStreetMapZoom(int openStreetMapZoom) {
        int internalZoom = getTileFactory().getInfo().getTotalMapZoom() - openStreetMapZoom;

        setZoom(internalZoom);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        attributionPane.setLocation(
                getWidth() - attributionPane.getWidth(),
                getHeight() - attributionPane.getHeight());
    }
}
