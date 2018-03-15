package cartoon;

import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;

public class PaneOrganizer {
    private BorderPane _root;
    private Cartoon _cartoon;
    private Control _control;

    public PaneOrganizer() {
        _root = new BorderPane();
        _root.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        _cartoon = new Cartoon(this);
        _control = new Control(this);
        this.setUpCreditPane();
    }

    //Sets up the credit pane along the bottom edge of the app
    private void setUpCreditPane() {
        Pane creditPane = new Pane();
        Label label = new Label("Map designed by Jug Cerovic (http://www.jugcerovic.com/) with INAT " +
                                "(http://www.inat.fr/). Application built and animated by Michael Kearney.");
        label.setWrapText(true);
        label.setFont(Constants.CREDIT_FONT);
        label.setStyle("-fx-padding: 2;");
        creditPane.getChildren().add(label);
        _root.setBottom(creditPane);
    }

    //Accessor methods so that Cartoon and Control and their children can know about each other
    public Cartoon getCartoon() {
        return _cartoon;
    }

    public Control getControl() {
        return _control;
    }

    public BorderPane getRoot() {
        return _root;
    }
}
