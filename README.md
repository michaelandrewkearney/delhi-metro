# Delhi Metro Visualizer

## About this Project

This is the first computer program I ever built. It animates the actual locations of trains of the Delhi Metro throughout the day. It is beautiful, buggy, and made me realize the power of programming.

## Preview

See the Delhi Metro come alive at rush hour!

![Animation Preview](preview.mp4)

Calculate your fare by clicking on stations!

![Fare Calculator Preview](fare.mp4)

## How to use this program

Build the project with Maven and run the project with JavaFX:
```bash
mvn clean javafx:run
```

## Architecture Notes
The App contains a Pane Organizer, which contains a Cartoon and a Control. The Cartoon deals with more graphical  elements, while Control deals with the information panes and number crunching.  The Timeline works at a rate of 25 fps, meaning its duration is 40 milliseconds. Every time the TimeHandler is  called, it checks whether or not a line needs a new train to be instantiated on the _mapPane. If so, it does. The  Timeline also controls a custom-built clock (i.e. the instance variables _delhiHour, _delhiMinute, _delhiSecond, etc) which avoids having to deal with a date. This allows the application to wrap around at the end of each day, so if you  wait through the night, trains will start up again at 4:42 AM. (Note: per Indian custom, 24-hour time is used).

## Functionality Requirements
- each "train" is a composite shape (see Logo.java)
- _mapPane (in Cartoon.java) contains these shapes (although they are added in Logo.java)
- Quit button quits the application properly
- KeyHandler: the arrow keys increment and decrement the slider, making the animation speed up or slow down. Except for when the Information button is clicked, focus is maintained on the mapPane to make sure that the arrow keys always work.
- Timeline exists in Cartoon.java
- The Labels in Control.setUpTimeBox() change based on the time of day. The Labels in Control.setUpFareBox() respond to clicks on stations. Clicking on two stations will display the fare between those stations and the stations will be highlighted on the map.
- PaneOrganizer, Cartoon, and Control all use javafx.scene.layout classes

## Acknowledgements
This map of the Delhi Metro was designed by [Jug Cerovic](http://www.jugcerovic.com/) as part of the [INAT](inat.fr) project. The map is used with permission of the designer.

The font used is [DIN](https://fonts.adobe.com/fonts/din-2014#fonts-section), designed by [Vasily Biryukov](https://fonts.adobe.com/designers/vasily-biryukov). The included OTF files are licensed for personal use through Adobe Fonts.

Fare and schedule information is from the Delhi Metro Rail Corporation.
