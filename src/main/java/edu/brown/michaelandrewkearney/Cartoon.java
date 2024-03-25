package edu.brown.michaelandrewkearney;

import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.Animation;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import java.util.Scanner;
import java.util.ArrayList;


public class Cartoon {
    private PaneOrganizer _organizer;
    private StackPane _cartoonPane;
    private VBox _infoBox;
    private Pane _mapPane;
    private StnData _stnData;
    private int[][][] _lineData;
    private boolean _fromto = true;
    private int _fromStnID;
    private int _toStnID;
    private int _delhiHour;
    private int _delhiMinute;
    private int _delhiSecond;
    private int _delhiMillis;
    private double _sliderValue;
    private boolean _updateSlider;
    private ArrayList<Logo> _trainList;
    private int[][] _next;
    private boolean _purgeSystem;
    private StationClicker _now;
    private StationClicker _recent;
    private StationClicker _past;
    private int _stnClicks = 0;
    private Pane _stationClickerPane;

    public Cartoon(PaneOrganizer organizer) {
        _delhiHour = 4;
        _delhiMinute = 41;
        _delhiSecond = 59;
        _delhiMillis = 0;
        _stnData = new StnData();
        _updateSlider = false;
        _sliderValue = 0.0;
        _purgeSystem = false;
        this.setUpNext();
        new LineData();
        _organizer = organizer;
        _cartoonPane = new StackPane();
        _cartoonPane.setPrefSize(1000,1000);
        _cartoonPane.setMaxSize(1000,1000);
        _trainList = new ArrayList<Logo>(500); //This stores all the instances of trains
        this.setUpMapPane();
        this.setUpStationClickerPane();
        this.setUpInfoBox();
        this.setUpStationClickers();
        _organizer.getRoot().setCenter(_cartoonPane);
        _mapPane.setOnKeyPressed(new KeyHandler());
        this.setUpTimeline();
    }

    //creates a new train on a certain line
    private void newTrain(int line) {
        Logo train = new Logo(line, this);
        _trainList.add(train);
    }

    private void setUpTimeline() {
        KeyFrame timeKF = new KeyFrame(Duration.millis(40.0), new TimeHandler());
        Timeline timeTimeline = new Timeline(timeKF);
        timeTimeline.setCycleCount(Animation.INDEFINITE);
        timeTimeline.play();
    }

    private class TimeHandler implements EventHandler<ActionEvent> {

        public TimeHandler() {
        }

        @Override
        public void handle(ActionEvent e) {
            //The below code updates each higher variables once 60 is reached (e.g. 61 sec -> 1 min 1 sec)
            _delhiMillis += 40 * (int) _sliderValue;
            if (_delhiMillis > 1000) {
                while (_delhiMillis > 1000) {
                    _delhiSecond++;
                    _delhiMillis -= 1000;
                }
                while (_delhiSecond >= 60) {
                    _delhiMinute++;
                    _delhiSecond -= 60;
                }
                while (_delhiMinute >=60) {
                    _delhiHour++;
                    _delhiMinute -= 60;
                }
                if (_delhiHour >= 24) {
                    _delhiHour = 0;
                }
                //Updates the time label in Control
                String timeLabel = "";
                if (_delhiHour < 10) {
                    timeLabel = "0";
                }
                timeLabel = timeLabel + String.valueOf(_delhiHour) + ":";
                if (_delhiMinute < 10) {
                    timeLabel = timeLabel + "0";
                }
                timeLabel = timeLabel + String.valueOf(_delhiMinute) + ":";
                if (_delhiSecond < 10) {
                    timeLabel = timeLabel + "0";
                }
                timeLabel = timeLabel + String.valueOf(_delhiSecond);
                _organizer.getControl().setTimeLabel(timeLabel);
            }
            //The array _next stores information about the next train on each line, the first train time, and the last
            //train time. This for clause is where all train instantiation comes from
            for (int i = 0; i < 18; i++) {
                if (((_delhiHour >= _next[i][0] && _delhiMinute >= _next[i][1]) || _delhiHour >= (_next[i][0] + 1)) && //Confirms it is after first train time
                    ((_delhiHour <= _next[i][2] && _delhiMinute <= _next[i][3]) || _delhiHour <= (_next[i][2] - 1))) { //Confirms it is before last train time
                    if (_next[i][6] == _delhiHour && _next[i][7] == _delhiMinute) {
                        //The below updates the time of the next train for a line once it is decided a train will be instantiated on that line
                        if (((_delhiHour >= 8) && (_delhiHour <= 10)) || ((_delhiHour >= 17) && (_delhiHour <= 19))) { //Defines peak hours
                            if (_next[i][7] + _next[i][4] >= 60) {
                                _next[i][6]++;
                                _next[i][7] = _next[i][7] + _next[i][4] - 60;

                            } else {
                                _next[i][7] += _next[i][4];
                            }
                        } else {
                            if (_next[i][7] + _next[i][5] >= 60) {
                                _next[i][6]++;
                                _next[i][7] = _next[i][7] + _next[i][5] - 60;

                            } else {
                                _next[i][7] += _next[i][5];
                            }
                        }
                        Cartoon.this.newTrain(i);
                    }
                }
            }
            //Updates speed of each train when the slider is changed
            if (_updateSlider) {
                for (int i = 0; i < _trainList.size(); i++) {
                    _trainList.get(i).updateSlider(_sliderValue);
                }
                _updateSlider = false; //toggles back the boolean so that speed isn't contantly updated
            }
            for (int i = 0; i < _trainList.size(); i++) {
                    _trainList.get(i).move();
            }
            if ((_delhiHour == 4) && (_delhiMinute == 30) && !_purgeSystem) {
                Cartoon.this.setUpNext();
                _purgeSystem = true;
            }
            if (_purgeSystem && _delhiMinute != 30) {
                _trainList = new ArrayList<Logo>(500);
                _purgeSystem = false;
            }
        }
    }

    private void setUpMapPane() {
        _mapPane = new Pane();
        Image map = new Image(Cartoon.class.getClassLoader().getResourceAsStream("map.png"), 1000.0, 1000.0, true, true);
        _mapPane.setBackground(new Background(new BackgroundImage(map, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        _cartoonPane.getChildren().add(_mapPane);
    }

    private void setUpStationClickerPane() {
        _stationClickerPane = new Pane();
        _cartoonPane.getChildren().add(_stationClickerPane);

    }

    private void setUpInfoBox() {
        _infoBox = new VBox(4);
        MyLabel introTitle = new MyLabel("Introduction", true, false);
        MyLabel intro = new MyLabel("This map animates the scheduled locations of every Delhi Metro train throughout "
                                    + "the day. Locations are based on data provided by the Delhi Metro Rail "
                                    + "Corporation. On a perfect day with no delays, train locations will be perfectly "
                                    + "accurate all day. Frequency of trains on certain lines increases during peak "
                                    + "hours.", false, false);
        MyLabel introSpacer = new MyLabel("", false, false);
        MyLabel instructionsTitle = new MyLabel("Instructions", true, false);
        MyLabel instructions = new MyLabel("The slider to the left adjusts how fast time passes. Drag the slider up to "
                                           + "start the animation and increase the speed of the animation. Dragging "
                                           + "the slider all the way up will make 1 second in the animation reflect 15 "
                                           + "minutes in the real world. Drag the slider all the way down to stop the "
                                           + "animation. You can also adjust the speed of the animation with your up "
                                           + "and down arrow keys. The buttons to the right of the slider will set the "
                                           + "map to animate in real time or pause the animation. At maxiumum speed, "
                                           + "the animation will progress through one day (24 hours) in 96 seconds.",
                                           false, false);
        MyLabel instructionsSpacer = new MyLabel("", false, false);
        MyLabel fareCalcTitle = new MyLabel("Fare Calculator", true, false);
        MyLabel fareCalc = new MyLabel("Click on any two stations to calculate the fare between them in Indian Rupees "
                                       + "(\u20B9). Fares are based on distance traveled. Entering and exiting the "
                                       + "same station costs \u20B98.", false, false);
        MyLabel fareCalcSpacer = new MyLabel("", false, false);
        MyLabel underConstructionTitle = new MyLabel("Under Construction", true, false);
        MyLabel underConstruction = new MyLabel("The Inner Ring Road and Outer Ring Road lines (pink and magenta, "
                                                + "respectively) are under construction and are not currently operable. "
                                                + "Trains do not run on these lines and non-interchange stations are "
                                                + "not selectable for fare calculation.", false, false);
        MyLabel underConstructionSpacer = new MyLabel("", false, false);
        MyLabel creditTitle = new MyLabel("Credits", true, false);
        MyLabel credit = new MyLabel("The map graphic was designed by Jug Cerovic and INAT and used with permission.",
                                     false, false);
        MyLabel creditSpacer = new MyLabel("", false, false);
        MyLabel close = new MyLabel("Click the \"Go To Map\" button to close this overlay.", true, false);
        close.setFont(Constants.CLOSE_FONT);
        _infoBox.setMaxSize(1000, 910);
        _infoBox.setBackground(new Background(new BackgroundFill(Color.hsb(63.07, 0.1076, 0.3573, 0.95),
            new CornerRadii(20.0), new Insets(190.0, 70.0, 65.0, 70.0))));
        _infoBox.setStyle("-fx-padding: 220 110 0 110;");
        _infoBox.getChildren().addAll(introTitle, intro, introSpacer, instructionsTitle, instructions,
                                      instructionsSpacer, fareCalcTitle, fareCalc, fareCalcSpacer, underConstructionTitle,
                                      underConstruction, underConstructionSpacer, creditTitle, credit, creditSpacer, close);
        _cartoonPane.getChildren().add(_infoBox);
    }

    private void setUpStationClickers() {
        for (int i = 1; i < 165; i++) {
            StationClicker stationClicker = new StationClicker(i, _stnData);
            _mapPane.getChildren().add(stationClicker.getColorEllipse());
            _stationClickerPane.getChildren().add(stationClicker.getClicker());
            stationClicker.getClicker().setOnMouseReleased(new StnClickHandler(stationClicker));
        }
    }

    private class StnClickHandler implements EventHandler<MouseEvent> {
        private StationClicker _stationClicker;
        private int _stnID;

        public StnClickHandler(StationClicker stationClicker) {
            _stnID = stationClicker.getStnID();
            _stationClicker = stationClicker;
        }

        @Override
        public void handle(MouseEvent e) {
            _stnClicks++;
            if (_fromto) {
                _organizer.getControl().setFromStn(_stnData.getStnName(_stnID));
                _fromStnID = _stnID;
            }
            else {
                _organizer.getControl().setToStn(_stnData.getStnName(_stnID));
                _toStnID = _stnID;
            }
            _fromto = !_fromto;
            if (_stnClicks > 1) {
                _organizer.getControl().setFare(String.valueOf(_stnData.getFare(_fromStnID - 1, _toStnID - 1)));
            }
            //Below, highlights stations when they are clicked
            switch (_stnClicks) {
                case 3:
                default:
                    _past = _recent;
                    _past.toggleColor(false);
                case 2:
                    _recent = _now;
                    _recent.toggleColor(true);
                case 1:
                    _now = _stationClicker;
                    _now.toggleColor(true);
            }
        }
    }

    private class KeyHandler implements EventHandler<KeyEvent> {

        public KeyHandler() {

        }

        @Override
        public void handle(KeyEvent e) {
            if (e.getCode() == KeyCode.UP || e.getCode() == KeyCode.KP_UP || e.getCode() == KeyCode.RIGHT ||
                e.getCode() == KeyCode.KP_RIGHT) {
                _organizer.getControl().getSlider().increment();
            } else if (e.getCode() == KeyCode.DOWN || e.getCode() == KeyCode.KP_DOWN || e.getCode() == KeyCode.LEFT ||
                       e.getCode() == KeyCode.KP_RIGHT) {
                _organizer.getControl().getSlider().decrement();
            }
            _sliderValue = _organizer.getControl().getSlider().getValue();
            Cartoon.this.updateSpeed(_sliderValue);
            e.consume();
        }
    }

    //sets up the values of the next train times, first train times, last train times, and frequencies
    private void setUpNext() {
        // observed frequency
        int[][] next = {{6,5,23,59,8,8,6,5}, //rapid metro
                        {4,45,23,30,20,20,4,45}, //airport inbound
                        {4,45,23,30,20,20,4,45}, //airport outbound
                        {5,0,6,0,12,12,5,0}, //early yellow
                        {6,0,23,18,6,24,6,0}, //yellow inbound
                        {5,45,23,30,6,24,5,45}, //yellow outbound
                        {6,0,23,30,8,24,6,0}, // violet inbound
                        {6,0,23,30,8,24,6,0}, // violet outbound
                        {5,10,23,6,24,24,5,10}, // green kirti nagar
                        {5,19,23,0,24,24,5,19}, // green inderlok
                        {5,30,23,0,6,24,5,30}, //red inbound
                        {5,45,23,31,6,24,5,45}, //red outbound
                        {4,42,5,54,24,24,4,42}, //early blue vaishali
                        {4,54,6,6,24,24,4,54}, // early blue noida
                        {6,18,22,22,12,24,6,18}, // blue inbound vaishali
                        {6,0,22,30,12,24,6,0}, // blue inbound noida
                        {6,0,23,5,12,24,6,0}, // blue outbound vaishali
                        {6,0,23,5,12,24,6,0}}; //blue outbound noida
        _next = next;
    }

    //Scans in data about lines from text file
    private class LineData {
        public LineData() {
            _lineData = new int[18][2][48];
            this.setUpLineData();
        }

        private void setUpLineData() {
            Scanner scanner = new Scanner(getClass().getClassLoader().getResourceAsStream("lineValues.txt"));
            for (int i = 0; i < 18; i++) {
                for (int j = 0; j < 48; j++) {
                    _lineData[i][0][j] = scanner.nextInt();
                }
                for (int j = 0; j < 48; j++) {
                    _lineData[i][1][j] = scanner.nextInt();
                }
            }
            scanner.close();
        }
    }

    public void removeFromArray(Logo train) {
        _trainList.remove(_trainList.indexOf(train));
    }

    public Pane getMapPane() {
        return _mapPane;
    }

    public StnData getStnData() {
        return _stnData;
    }

    public int[][][] getLineData() {
        return _lineData;
    }

    public double getSliderValue() {
        return _sliderValue;
    }

    public void updateSpeed(double sliderValue) {
        _sliderValue = sliderValue;
        _updateSlider = true;
    }

    public void toggleInfoBox(boolean showInfo) {
        _infoBox.setVisible(!showInfo);
    }

    public Pane getRoot() {
        return _cartoonPane;
    }

    public void focusOnMap() {
        _mapPane.requestFocus();
    }

}
