package cartoon;

import javafx.scene.shape.Ellipse;
import javafx.scene.paint.Color;

public class StationClicker {
    private int _id;
    private Ellipse _clickerEllipse;
    private Ellipse _colorEllipse;
    Color _lineColor;

    public StationClicker(int i, StnData stnData) {
        _id = i;
        _clickerEllipse = new Ellipse(stnData.getStnX(i), stnData.getStnY(i),
                                Constants.STATION_CLICKER_RADIUS_X, Constants.STATION_CLICKER_RADIUS_Y);
        _colorEllipse = new Ellipse(stnData.getStnX(i), stnData.getStnY(i),
                                Constants.STATION_CLICKER_RADIUS_X, Constants.STATION_CLICKER_RADIUS_Y);
        switch (stnData.getLine(i)) {
            case "red":
                _lineColor = Constants.RED_LINE_COLOR;
                break;
            case "orange":
                _lineColor = Constants.ORANGE_LINE_COLOR;
                break;
            case "yellow":
                _lineColor = Constants.YELLOW_LINE_COLOR;
                break;
            case "green":
                _lineColor = Constants.GREEN_LINE_COLOR;
                break;
            case "blue":
                _lineColor = Constants.BLUE_LINE_COLOR;
                break;
            case "violet":
                _lineColor = Constants.VIOLET_LINE_COLOR;
                break;
            case "rapid":
                _lineColor = Constants.RAPID_LINE_COLOR;
                break;
        }
        _clickerEllipse.setFill(Color.TRANSPARENT);
        _colorEllipse.setFill(Color.TRANSPARENT);
    }

    public void toggleColor(boolean on) {
        if (on) {
            _colorEllipse.setFill(_lineColor);
        } else {
            _colorEllipse.setFill(Color.TRANSPARENT);
        }
    }

    public int getStnID() {
        return _id;
    }

    public Ellipse getClicker() {
        return _clickerEllipse;
    }

    public Ellipse getColorEllipse() {
        return _colorEllipse;
    }
}
