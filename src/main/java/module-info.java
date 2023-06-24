module javafx.projektarbeit {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens javafx.projektarbeit to javafx.fxml;
    exports javafx.projektarbeit;
}
