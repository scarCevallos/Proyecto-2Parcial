module ec.edu.espol.chess {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens ec.edu.espol.chess to javafx.fxml;
    exports ec.edu.espol.chess;
}
