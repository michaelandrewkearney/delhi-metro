package edu.brown.michaelandrewkearney;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class MyLabel extends Label {

    public MyLabel(String string, boolean title, boolean dark) {
        super(string);
        this.setWrapText(!title);
        if (title) {
            this.setFont(Constants.TITLE_FONT);
        } else {
            this.setFont(Constants.DATA_FONT);
        }
        if (dark) {
            this.setTextFill(Constants.MY_GRAY_COLOR);
        } else {
            this.setTextFill(Color.WHITE);
        }
    }
}
