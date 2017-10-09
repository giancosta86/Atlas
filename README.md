# Atlas

*Interactive cartography for Java*


## Introduction


**Atlas** is a Java library dedicated to rendering *interactive geographic maps*.


![Example world map](https://github.com/giancosta86/Atlas/raw/master/screenshots/example.jpg "This interactive map was rendered by Atlas!")


It is built on top of 2 important technologies:

* [JXMapViewer2](https://github.com/msteiger/jxmapviewer2), a brilliant library providing the actual rendering component and the interaction-event handlers

* [OpenStreetMap](https://openstreetmap.org/), a well-known and very effective open standard for maps


Each of the above technologies respectively represents a focus point for Atlas:

* **Enhanced rendering** was inspired by one of JXMapViewer2 examples and actually provides a simplified way to add Swing components to the map at arbirary **GeoPosition**s. In particular, **PinLayer**, **Pin** and **PinDrawing** - and their subtypes - constitute a simple but effective API

* **OpenStreetMap** standards are employed in a dedicated package. In particular, its zoom level convention (**0 = whole world**) is supported in the **OpenStreetMapViewer** component and its ancillary classes (**OpenStreetMapTileFactory** and **OpenStreetMapTileFactoryInfo**).
As an important licensing issue, this interactive map also shows an HTML-based attribution label, for compliance with the license of different tile providers.

For further details, please refer to the Javadoc documentation.



## Requirements

Java 8u144 or later is recommended to employ the library.


## Referencing the library

Atlas is available on [Hephaestus](https://bintray.com/giancosta86/Hephaestus) and can be declared as a Gradle or Maven dependency; please refer to [its dedicated page](https://bintray.com/giancosta86/Hephaestus/Atlas).

Alternatively, you could download the JAR file from Hephaestus and manually add it to your project structure.


## OpenStreetMap license

For detailed information about employing [OpenStreetMap](https://openstreetmap.org/) in your application, please consult its [copyright](https://www.openstreetmap.org/copyright) page. OpenStreetMap is © OpenStreetMap contributors.



## Further references

* [JXMapViewer2](https://github.com/msteiger/jxmapviewer2)

* [OpenStreetMap](https://openstreetmap.org/)

* [Zephyros](https://github.com/giancosta86/Zephyros)

* [Hephaestus - Facebook page](https://www.facebook.com/hephaestus.repository/)
