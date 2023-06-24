package javafx.projektarbeit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static ObservableList<Modul> modulInputList = getInitailModulData();

    public static void main(String[] args) {
        launch();
    }

    // methods

    public static double round(double val, int sca) {

        double s = Math.pow(10, sca);
        return Math.round(val * s) / s;
    }

    @Override
    public void start(Stage stage) throws IOException {

        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setSceneRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/" + fxml + ".fxml"));
        return fxmlLoader.load();

    }// testgit

    // Methode

    // Data initialization

    public static ObservableList<Modul> getInitailModulData() {

        List<Modul> modullist = new ArrayList<>();
        // New Modul
        modullist.add(new Modul("Systemtechnik", "Fachstudium Informatik"));
        modullist.get(0).kursliste.add(new Kurs("Seminar Tools & Steup ", "IN201"));
        modullist.get(0).kursliste.get(0).notenliste.add(new Note(5.2, 0.3));
        modullist.get(0).kursliste.get(0).notenliste.add(new Note(5.6, 0.7));

        modullist.get(0).kursliste.add(new Kurs("Betriebsystem", "IN202"));
        modullist.get(0).kursliste.get(1).notenliste.add(new Note(4.4, 1));

        modullist.get(0).kursliste.add(new Kurs("Internet-Technologien ", "IN203"));
        modullist.get(0).kursliste.get(2).notenliste.add(new Note());
        // New Modul
        modullist.add(new Modul("Programmiern", "Fachstudium Informatik"));
        modullist.get(1).kursliste.add(new Kurs("OOP1 ", "IN211"));
        modullist.get(1).kursliste.get(0).notenliste.add(new Note(5.0, 2.0 / 7.0)); // 5.0 ist die Note // 2.0/7.0 ist
                                                                                    // die Gewichtung dieser Note
        modullist.get(1).kursliste.get(0).notenliste.add(new Note(5.3, 2.0 / 7.0));
        modullist.get(1).kursliste.get(0).notenliste.add(new Note(1, 2.0 / 7.0));
        modullist.get(1).kursliste.get(0).notenliste.add(new Note(1, 1.0 / 7.0));

        modullist.get(1).kursliste.add(new Kurs("OOP2 ", "IN212"));

        modullist.get(1).kursliste.add(new Kurs("Grundlagen Web-Progammierung ", "IN212"));

        // New Modul
        modullist.add(new Modul("Datentechnik", "Fachstudium Informatik"));

        modullist.get(2).kursliste.add(new Kurs("Datenbank 1 ", "IN221"));
        modullist.get(2).kursliste.get(0).notenliste.add(new Note(4.6, 0.5));
        modullist.get(2).kursliste.get(0).notenliste.add(new Note(1.0, 0.5));

        modullist.get(2).kursliste.add(new Kurs("Datenbank 2 ", "IN222"));

        modullist.get(2).kursliste.add(new Kurs("XML ", "IN223"));
        modullist.get(2).kursliste.get(2).notenliste.add(new Note(1.0, 0.5));
        modullist.get(2).kursliste.get(2).notenliste.add(new Note(1.0, 0.5));

        ObservableList<Modul> observableModulList = FXCollections.observableList(modullist);

        // to write on console for test purposes

        for (Modul modul : modullist) {
            System.out.println(modul.toString());

        }
        return observableModulList;
    };

}