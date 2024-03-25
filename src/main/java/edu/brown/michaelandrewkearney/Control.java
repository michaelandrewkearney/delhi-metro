package edu.brown.michaelandrewkearney;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.geometry.Orientation;

public class Control {
    private BorderPane _controlPane;
    private PaneOrganizer _organizer;
    private Slider _slider;
    private Button _infoButton;
    private Button _quitButton;
    private MyLabel _timeLabel;
    private MyLabel _fromStnDataLabel;
    private MyLabel _toStnDataLabel;
    private MyLabel _fareDataLabel;
    private boolean _showInfo = true;

    public Control(PaneOrganizer organizer) {
        _organizer = organizer;
        _controlPane = new BorderPane();
        _controlPane.setPrefWidth(220);
        _organizer.getRoot().setRight(_controlPane);
        this.setUpSlider();
        this.setUpDataBox();
        this.setUpButtons();
    }

    @SuppressWarnings("static-access")
	private void setUpSlider() {
        // Instantiate _slider and its AnchorPane
        _slider = new Slider(0, 900, 0);
        _slider.setBlockIncrement(25.0);
        _slider.setMajorTickUnit(1.0);
        _slider.setMinorTickCount(0);
        _slider.setSnapToTicks(true);
        _slider.setShowTickMarks(false);
        _slider.setOnMouseReleased(new SliderFocusHandler());
        _slider.setFocusTraversable(false);
        AnchorPane sliderPane = new AnchorPane(_slider);
        _controlPane.setLeft(sliderPane);
        // Format _slider layout
        _slider.setOrientation(Orientation.VERTICAL);
        sliderPane.setLeftAnchor(_slider, 0.0);
        sliderPane.setTopAnchor(_slider, 10.0);
        sliderPane.setRightAnchor(_slider, 5.0);
        sliderPane.setBottomAnchor(_slider, 10.0);
    }

    private class SliderFocusHandler implements EventHandler<MouseEvent> {

        public SliderFocusHandler() {}

        @Override
        public void handle(MouseEvent e) {
            _organizer.getCartoon().updateSpeed(_slider.getValue());
            _organizer.getCartoon().focusOnMap();
            e.consume();
        }
    }

    private void setUpDataBox() {
        VBox dataBox = new VBox(25);
        dataBox.setStyle("-fx-padding: 10;");
        _controlPane.setCenter(dataBox);
        //Instruction Box
        this.setUpTimeBox(dataBox);
        this.setUpSpeedButtons(dataBox);
        this.setUpFareBox(dataBox);
    }

    private void setUpTimeBox(VBox dataBox) {
        Label timeTitle = new Label("Time in Delhi");
        timeTitle.setFont(Constants.TITLE_FONT);
        timeTitle.setTextFill(Color.hsb(63.07, 0.1076, 0.3573));
        _timeLabel = new MyLabel("04:42:00", false, true);
        _timeLabel.setFont(Constants.FARE_FONT);
        _timeLabel.setStyle("-fx-padding: 0 0 0 10;");
        VBox timeBox = new VBox(10);
        timeBox.setStyle("-fx-border-color: #465644; -fx-border-width: 1px; -fx-padding: 10;");
        timeBox.getChildren().addAll(timeTitle, _timeLabel);
        dataBox.getChildren().add(timeBox);
    }

    @SuppressWarnings("static-access")
	private void setUpSpeedButtons(VBox dataBox) {
        Button pauseButton = new Button("Pause Animation");
        pauseButton.setFocusTraversable(false);
        pauseButton.setFont(Constants.DATA_FONT);
        pauseButton.setTextFill(Color.hsb(63.07, 0.1076, 0.3573));
        pauseButton.setOnAction(new PauseHandler());
        Button realButton = new Button("Real Time");
        realButton.setFocusTraversable(false);
        realButton.setFont(Constants.DATA_FONT);
        realButton.setTextFill(Color.hsb(63.07, 0.1076, 0.3573));
        realButton.setOnAction(new RealHandler());
        AnchorPane pauseAnchor = new AnchorPane(pauseButton);
        AnchorPane realAnchor = new AnchorPane(realButton);
        pauseAnchor.setLeftAnchor(pauseButton, 0.0);
        pauseAnchor.setRightAnchor(pauseButton, 0.0);
        realAnchor.setLeftAnchor(realButton, 0.0);
        realAnchor.setRightAnchor(realButton, 0.0);
        VBox speedBox = new VBox(10);
        speedBox.setStyle("-fx-border-color: #465644; -fx-border-width: 1px; -fx-padding: 10;");
        speedBox.getChildren().addAll(realAnchor, pauseAnchor);
        dataBox.getChildren().add(speedBox);
    }

    private class PauseHandler implements EventHandler<ActionEvent> {

        public PauseHandler() {}

        @Override
        public void handle (ActionEvent e) {
            _slider.setValue(0.0);
            _organizer.getCartoon().updateSpeed(0.0);
            _organizer.getCartoon().focusOnMap();
            e.consume();
        }
    }

    private class RealHandler implements EventHandler<ActionEvent> {

        public RealHandler() {}

        @Override
        public void handle (ActionEvent e) {
            _slider.setValue(1.0);
            _organizer.getCartoon().updateSpeed(1.0);
            _organizer.getCartoon().focusOnMap();
            e.consume();
        }
    }

    private void setUpFareBox(VBox dataBox) {
        Label fareTitle = new Label("Fare Calculator");
        fareTitle.setFont(Constants.TITLE_FONT);
        fareTitle.setTextFill(Color.hsb(63.07, 0.1076, 0.3573));
        MyLabel from = new MyLabel("From:", false, true);
        MyLabel to = new MyLabel("To:", false, true);
        MyLabel fare = new MyLabel("Fare:", false, true);
        _fromStnDataLabel = new MyLabel("Click a station", false, true);
        _fromStnDataLabel.setFont(Constants.FARE_FONT);
        _fromStnDataLabel.setStyle("-fx-padding: 0 0 0 10;");
        _toStnDataLabel = new MyLabel("Click a station", false, true);
        _toStnDataLabel.setFont(Constants.FARE_FONT);
        _toStnDataLabel.setStyle("-fx-padding: 0 0 0 10;");
        _fareDataLabel = new MyLabel("", false, true);
        _fareDataLabel.setFont(Constants.FARE_FONT);
        _fareDataLabel.setStyle("-fx-padding: 0 0 0 10;");
        VBox fareBox = new VBox(10);
        fareBox.setStyle("-fx-border-color: #465644; -fx-border-width: 1px; -fx-padding: 10;");
        fareBox.getChildren().addAll(fareTitle, from, _fromStnDataLabel, to, _toStnDataLabel, fare, _fareDataLabel);
        dataBox.getChildren().add(fareBox);
    }

    @SuppressWarnings("static-access")
	private void setUpButtons() {
        _infoButton = new Button("Go To Map");
        _infoButton.setFont(Constants.BIG_BUTTON_FONT);
        _infoButton.setTextFill(Color.hsb(63.07, 0.1076, 0.3573));
        _infoButton.setOnAction(new InfoHandler());
        _quitButton = new Button("Quit");
        _quitButton.setFont(Constants.BIG_BUTTON_FONT);
        _quitButton.setTextFill(Color.hsb(63.07, 0.1076, 0.3573));
        _quitButton.setOnAction(new QuitHandler());
        AnchorPane infoAnchor = new AnchorPane(_infoButton);
        AnchorPane quitAnchor = new AnchorPane(_quitButton);
        infoAnchor.setLeftAnchor(_infoButton, 0.0);
        infoAnchor.setRightAnchor(_infoButton, 0.0);
        quitAnchor.setLeftAnchor(_quitButton, 0.0);
        quitAnchor.setRightAnchor(_quitButton, 0.0);
        VBox buttonBox = new VBox(10, infoAnchor, quitAnchor);
        buttonBox.setStyle("-fx-border-style: solid none none none; -fx-border-color: #465644; -fx-border-width: 1px; "
                           + "-fx-padding: 10;");
        _controlPane.setBottom(buttonBox);
    }

    private class QuitHandler implements EventHandler<ActionEvent> {

        public QuitHandler() {}

        @Override
        public void handle (ActionEvent e) {
            Platform.exit();
            e.consume();
        }
    }

    private class InfoHandler implements EventHandler<ActionEvent> {
        public InfoHandler() {}

        @Override
        public void handle (ActionEvent e) {
            _organizer.getCartoon().toggleInfoBox(_showInfo);
            if (_showInfo) {
                _infoButton.setText("Information");
                _organizer.getCartoon().focusOnMap();
            }
            else {
                _infoButton.setText("Go To Map");
                _infoButton.requestFocus();
            }
            _infoButton.setFocusTraversable(!_showInfo);
            _quitButton.setFocusTraversable(!_showInfo);
            _showInfo = !_showInfo;
            e.consume();
        }
    }

    public BorderPane getRoot() {
        return _controlPane;
    }

    public Slider getSlider() {
        return _slider;
    }

    public void setTimeLabel(String string) {
        _timeLabel.setText(string);
    }

    public void setFromStn(String string) {
        _fromStnDataLabel.setText(string);
    }

    public void setToStn(String string) {
        _toStnDataLabel.setText(string);
    }

    public void setFare(String string) {
        _fareDataLabel.setText("\u20B9 " + string);
    }
}
