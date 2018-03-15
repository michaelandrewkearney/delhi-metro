package cartoon;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;

/**
 * This cartoon models the actual locations of trains of the Delhi Metro throughout the day. General information about
 * the application can be found in the information pane of the application (click "Information").
 * The App contains a Pane Organizer, which contains a Cartoon and a Control. The Cartoon deals with more graphical
 * elements, while Control deals with the information panes and number crunching.
 * The Timeline works at a rate of 25 fps, meaning its duration is 40 milliseconds. Every time the TimeHandler is
 * called, it checks whether or not a line needs a new train to be instantiated on the _mapPane. If so, it does. The
 * Timeline also controls a custom-built clock (i.e. the instance variables _delhiHour, _delhiMinute, _delhiSecond, etc)
 * which avoids having to dela with a date. This allows the appication to wrap around at the end of each day, so if you
 * wait through the night, trains will start up again at 4:42 AM. (Note: Per Indian custom, military time is used).
 *
 * Functionality Requirements:
 *  - each "train" is a composite shape (see Logo.java)
 *  - _mapPane (in Cartoon.java) contains these shapes (although they are added in Logo.java)
 *  - Quit button quits the application properly
 *  - KeyHandler: the arrow keys increment and decrement the slider, making the animation speed up or slow down. Except
 *    for when the Information button is clicked, focus is maintained on the mapPane to make sure that the arrow keys
 *    always work.
 *  - Timeline exists in Cartoon.java
 *  - The Labels in Control.setUpTimeBox() change based on the time of day. The Labels in Control.setUpFareBox()
 *    respond to clicks on stations. Clicking on two stations will display the fare between those stations and the
 *    stations will be highlighted on the map.
 *  - PaneOrganizer, Cartoon, and Control all use javafx.scene.layout classes
 *
 *
 * This map was design by Jug Cerovic (http://www.jugcerovic.com/), an architect whose work I admire. The map is used
 * with permission both of the designer and of the Head TAs.
 *
 * Several asset files are used. These should be in the folder "assets" in Cartoon like so:
 * assets/DINPRO-BLACK.OTF
 * assets/DINPRO-BOLD.OTF
 * assets/DINPRO-LIGHT.OTF
 * assets/DINPRO-MEDIUM.OTF
 * assets/DINPRO-REGULAR.OTF
 * assets/fareValues.txt
 * assets/lineValues.txt
 * assets/map.png
 *
 * If any files did not get uploaded by accident, please email me and I can send them: michael_kearney@brown.edu
 *
 * Known Issues: The trains on the blue line don't travel exactly on the blue line. It's something to do with the math.
 */

public class App extends Application {

    @Override
    public void start(Stage stage) {
        PaneOrganizer organizer = new PaneOrganizer();
        Scene scene = new Scene(organizer.getRoot(),1250,1020);
        stage.getIcons().addAll(new Image(App.class.getResource("/assets/icons/logo16.png").toExternalForm()),
            new Image(App.class.getResource("/assets/icons/logo20.png").toExternalForm()),
            new Image(App.class.getResource("/assets/icons/logo32.png").toExternalForm()));
        stage.setScene(scene);
        stage.setTitle("Delhi Metro / \u0926\u093F\u0932\u094D\u0932\u0940 \u092E\u0947\u091F\u094D\u0930\u094B");
        stage.show();
    }

    public static void main(String[] argv) {
        launch(argv);
    }
}
