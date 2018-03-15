package cartoon;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Constants {
    public static final double STATION_CLICKER_RADIUS_X = 1.6/595.22*1000*3;
    public static final double STATION_CLICKER_RADIUS_Y = 1.6/595.22*1000*3;
    public static final Color RED_LINE_COLOR = Color.hsb(357.78, 0.8695, 0.8707);
    public static final Color ORANGE_LINE_COLOR = Color.hsb(19.47, 0.7831, 0.8901);
    public static final Color YELLOW_LINE_COLOR = Color.hsb(42.92, 0.8943, 0.9248);
    public static final Color GREEN_LINE_COLOR = Color.hsb(139.07, 0.8301, 0.692);
    public static final Color BLUE_LINE_COLOR = Color.hsb(202.7, 1.0, 0.8707);
    public static final Color VIOLET_LINE_COLOR = Color.hsb(282.51, 0.6969, 0.5325);
    public static final Color RAPID_LINE_COLOR = Color.hsb(191.68, 1.0, 0.8757);
    public static final Color MY_GRAY_COLOR = Color.hsb(63.07, 0.1076, 0.3573);
    public static final Color LOGO_RED = Color.hsb(358.42, 0.7917, 0.7529);
    public static final Font DIN_BLACK = Font.loadFont(Constants.class.getResource("/assets/DINPRO-BLACK.OTF").toExternalForm(), 12);
    public static final Font DIN_BOLD = Font.loadFont(Constants.class.getResource("/assets/DINPRO-BOLD.OTF").toExternalForm(), 12);
    public static final Font DIN_MEDIUM = Font.loadFont(Constants.class.getResource("/assets/DINPRO-MEDIUM.OTF").toExternalForm(), 12);
    public static final Font DIN_REGULAR = Font.loadFont(Constants.class.getResource("/assets/DINPRO-REGULAR.OTF").toExternalForm(), 12);
    public static final Font DIN_LIGHT = Font.loadFont(Constants.class.getResource("/assets/DINPRO-LIGHT.OTF").toExternalForm(), 12);
    public static final Font BIG_BUTTON_FONT = new Font("DINPRO-MEDIUM", 24);
    public static final Font DATA_FONT = new Font("DINPRO-MEDIUM", 14);
    public static final Font CREDIT_FONT = new Font("DINPRO-REGULAR", 12);
    public static final Font TITLE_FONT = new Font("DINPRO-BLACK", 20);
    public static final Font CLOSE_FONT = new Font("DINPRO-BLACK", 34);
    public static final Font FARE_FONT = new Font("DINPRO-REGULAR", 16);
}
