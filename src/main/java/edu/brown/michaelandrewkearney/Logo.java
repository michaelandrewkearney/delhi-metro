package edu.brown.michaelandrewkearney;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import java.lang.Math;

public class Logo {
    private Pane _mapPane;
    private Ellipse _baseCircle;
    private Ellipse _maskCircle;
    private Rectangle _topRect;
    private Rectangle _bottomRect;
    private Line _line;
    private double _sliderValue;
    private StnData _stnData;
    private int[][][] _lineData;
    private int _lineNum;
    private int _nextStn;
    private int _prevStn;
    private double _pureDx;
    private double _pureDy;
    private double _dx;
    private double _dy;
    private int _stnCount;
    private double _xTraveled;
    private double _yTraveled;
    private double _xToTravel;
    private double _yToTravel;
    private Cartoon _cartoon;


    public Logo(int lineNum, Cartoon cartoon) {
        _cartoon = cartoon;
        _mapPane = _cartoon.getMapPane();
        _stnData = _cartoon.getStnData();
        _lineData = _cartoon.getLineData();
        _lineNum = lineNum;
        _sliderValue = _cartoon.getSliderValue();
        _stnCount = -1;
        _xTraveled = 0;
        _yTraveled = 0;
        double x = _stnData.getStnX(_lineData[_lineNum][0][0]);
        double y = _stnData.getStnY(_lineData[_lineNum][0][0]);
        _baseCircle = new Ellipse(x, y, 10.0, 10.0);
        _baseCircle.setFill(Constants.LOGO_RED);
        _maskCircle = new Ellipse(x, y, 7.5, 7.5);
        _maskCircle.setFill(Color.WHITE);
        _topRect = new Rectangle(x - 8.0, y - 4.25, 15.0, 2.5);
        _topRect.setFill(Constants.LOGO_RED);
        _bottomRect = new Rectangle(x - 8.0, y + 1.75, 15.0, 2.5);
        _bottomRect.setFill(Constants.LOGO_RED);
        _line = new Line(x - 3.0, y + 2.5, x + 3.0, y - 2.5);
        _line.setStroke(Constants.LOGO_RED);
        _line.setStrokeWidth(2.5);
        _mapPane.getChildren().addAll(_baseCircle, _maskCircle, _topRect, _bottomRect, _line);
        this.updateStns();
        this.updateSlider(_sliderValue);
    }

    public void updateLocation() {
        double x = _stnData.getStnX(_prevStn);
        double y = _stnData.getStnY(_prevStn);
        _baseCircle.setCenterX(x);
        _baseCircle.setCenterY(y);
        _maskCircle.setCenterX(x);
        _maskCircle.setCenterY(y);
        _topRect.setX(x - 8.0);
        _topRect.setY(y - 4.25);
        _bottomRect.setX(x - 8.0);
        _bottomRect.setY(y + 1.75);
        _line.setStartX(x - 3.0);
        _line.setStartY(y + 2.5);
        _line.setEndX(x + 3.0);
        _line.setEndY(y - 2.5);
    }

    public void move() {
        _baseCircle.setCenterX(_baseCircle.getCenterX() + _dx);
        _baseCircle.setCenterY(_baseCircle.getCenterY() + _dy);
        _maskCircle.setCenterX(_maskCircle.getCenterX() + _dx);
        _maskCircle.setCenterY(_maskCircle.getCenterY() + _dy);
        _topRect.setX(_topRect.getX() + _dx);
        _topRect.setY(_topRect.getY() + _dy);
        _bottomRect.setX(_bottomRect.getX() + _dx);
        _bottomRect.setY(_bottomRect.getY() + _dy);
        _line.setStartX(_line.getStartX() + _dx);
        _line.setStartY(_line.getStartY() + _dy);
        _line.setEndX(_line.getEndX() + _dx);
        _line.setEndY(_line.getEndY() + _dy);
        _xTraveled += Math.abs(_dx);
        _yTraveled += Math.abs(_dy);
        if ((_xTraveled >= Math.abs(_xToTravel)) && (_yTraveled >= Math.abs(_yToTravel))) {
            this.updateStns();
        }
    }

    //Updates the speed and target location of train once it reaches its destination station
    public void updateStns() {
        _stnCount++;
        _prevStn = _lineData[_lineNum][0][_stnCount];
        _nextStn = _lineData[_lineNum][0][_stnCount + 1];
        if (_stnCount > 0) {
            this.updateLocation();
        }
        if (_nextStn != 0) {
            _xTraveled -= Math.abs(_xToTravel);
            _yTraveled -= Math.abs(_yToTravel);
            _xToTravel = _stnData.getStnX(_nextStn) - _stnData.getStnX(_prevStn);
            _yToTravel = _stnData.getStnY(_nextStn) - _stnData.getStnY(_prevStn);
            _pureDx = _xToTravel/(((double) _lineData[_lineNum][1][_stnCount])*25.0);
            _pureDy = _yToTravel/(((double) _lineData[_lineNum][1][_stnCount])*25.0);
            this.updateDeltaXY();
        } else {
            _dx = 0;
            _dy = 0;
            _cartoon.removeFromArray(this);
            _mapPane.getChildren().removeAll(_baseCircle, _maskCircle, _topRect, _bottomRect, _line);
        }
    }

    public void updateDeltaXY() {
        _dx = _pureDx * _sliderValue;
        _dy = _pureDy * _sliderValue;
    }

    public void updateSlider(double sliderValue) {
         _sliderValue = sliderValue;
         this.updateDeltaXY();
    }
}
