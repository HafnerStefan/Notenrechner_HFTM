package javafx.projektarbeit;

import java.util.List;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;

public class PrimaryController {

    // Attributes

    private TreeItem<String> selectedItemString;

    @FXML
    private Label relativerModulNotenSchnitt;

    @FXML
    private Label anzahlNoten;

    @FXML
    private Label benötigtenote;

    @FXML
    private TextField gw1;

    @FXML
    private TextField gw2;

    @FXML
    private TextField gw3;

    @FXML
    private TextField gw4;

    @FXML
    private Label k1_id;

    @FXML
    private Label k2_id;

    @FXML
    private Label k3_id;

    @FXML
    private Label k4_id;

    @FXML
    private Label kReSchnitt1;

    @FXML
    private Label kReSchnitt4;

    @FXML
    private Label kReSchnitt2;

    @FXML
    private Label kReSchnitt3;

    @FXML
    private Label kSchnitt1;

    @FXML
    private Label kSchnitt2;

    @FXML
    private Label kSchnitt3;

    @FXML
    private Label kSchnitt4;

    @FXML
    private Label ks;

    @FXML
    private Label ksrelativ;

    @FXML
    private TextField kursIDView;

    @FXML
    private TextField kursNameView;

    @FXML
    private VBox kursVBoxView;

    @FXML
    private TextField modulNameView;

    @FXML
    private TextField modulNameView1;

    @FXML
    private Label modulNotenSchnitt;

    @FXML
    private TextField modulStatusView;

    @FXML
    private TextField modulStatusView1;

    @FXML
    private VBox modulVboxView;

    @FXML
    private TextField note1;

    @FXML
    private TextField note2;

    @FXML
    private TextField note3;

    @FXML
    private TextField note4;

    @FXML
    private TreeView<String> treeView;

    // *********************************Buttons*********************************

    @FXML
    void addKursButton(ActionEvent event) {

        String savedSelectedItemString = selectedItemString.getValue();

        boolean isKurs = false;
        boolean isModul = false;
        // Prüft ist ein Kurs oder Modul
        for (Modul modul : App.modulInputList) {
            for (Kurs kurs : modul.getKursliste()) {
                if (kurs.getKursnameProperty().getValue().equals(savedSelectedItemString)) {
                    isKurs = true;
                    break;
                }
            }
            if (isKurs) {
                isModul = false;
                break;
            } else if (modul.getModulnameProperty().getValue().equals(savedSelectedItemString)) {
                isModul = true;
                break;
            }
        }

        if (isKurs) {
            // Füge den Kurs zu einem Modul hinzu
            for (Modul modul : App.modulInputList) {
                for (Kurs kurs : modul.getKursliste()) {
                    if (kurs.getKursnameProperty().getValue().equals(savedSelectedItemString)) {
                        if (modul.getKursliste().size() < 4) {
                            Kurs newKurs = new Kurs("New kurs", "New kurs");
                            System.out.println("Neuer Kurs Hinzugefügt");
                            modul.getKursliste().add(newKurs);
                        } else {

                        }
                        break;
                    }
                }
            }
        } else if (isModul) {
            // Füge dem ausgewählten Modul einen neuen Kurs hinzu
            for (Modul modul : App.modulInputList) {
                if (modul.getModulnameProperty().getValue().equals(savedSelectedItemString)) {
                    if (modul.getKursliste().size() < 4) {
                        Kurs newKurs = new Kurs("New kurs", "New kurs");
                        System.out.println("Neuer Kurs Hinzugefügt");
                        modul.getKursliste().add(newKurs);
                    } else {

                    }
                    break;
                }
            }
        }
        initialize();

        selectItemByString(treeView.getRoot(), savedSelectedItemString);

    }

    @FXML
    void addModuleButton(ActionEvent event) {
        String savedSelectedItemString = selectedItemString.getValue();

        // Überprüfe, ob ein Modul mit dem Namen "New Module" bereits vorhanden ist
        boolean moduleNameExists = false;
        int moduleNumber = 2;
        String newModuleNameStart = "New Module";
        String newModuleName = newModuleNameStart;

        while (!moduleNameExists) {
            boolean moduleExists = false;

            for (Modul modul : App.modulInputList) {
                if (modul.getModulnameProperty().getValue().equals(newModuleName)) {
                    moduleExists = true;
                    break;
                }
            }

            if (moduleExists) {
                newModuleName = newModuleNameStart + " " + moduleNumber;
                moduleNumber++;
            } else {
                moduleNameExists = true;
            }
        }

        Modul nextModule = new Modul(newModuleName, newModuleName);
        System.out.println("Neuer Modul Hinzugefügt");
        App.modulInputList.add(nextModule);
        initialize();
        selectItemByString(treeView.getRoot(), savedSelectedItemString);
    }

    @FXML
    void deleteElementButton(ActionEvent event) {
        String savedSelectedItemString = selectedItemString.getValue();

        boolean isKurs = false;
        boolean isModul = false;
        boolean isKursFound = false;
        // Prüft ist ein Kurs oder Modul
        for (Modul modul : App.modulInputList) {
            for (Kurs kurs : modul.getKursliste()) {
                if (kurs.getKursnameProperty().getValue().equals(savedSelectedItemString)) {
                    isKurs = true;
                    break;
                }
            }
            if (isKurs) {
                isModul = false;
                break;
            } else if (modul.getModulnameProperty().getValue().equals(savedSelectedItemString)) {
                isModul = true;
                break;
            }
        }
        String lastItemInList = "Studium";

        if (isKurs) {
            // Füge den Kurs zu einem Modul hinzu
            for (Modul modul : App.modulInputList) {
                if (isKursFound) {
                    break;
                }
                for (Kurs kurs : modul.getKursliste()) {
                    if (kurs.getKursnameProperty().getValue().equals(savedSelectedItemString)) {
                        System.out.println("Modul " + kurs.getKursnameProperty().getValue() + "geloscht");
                        modul.getKursliste().remove(kurs);
                        isKursFound = true;
                        break;
                    } else {
                        lastItemInList = kurs.getKursnameProperty().getValue();

                    }

                    if (isKursFound) {
                        break;
                    }
                }

            }
        } else if (isModul) {

            // Füge dem ausgewählten Modul einen neuen Kurs hinzu
            for (Modul modul : App.modulInputList) {
                if (modul.getModulnameProperty().getValue().equals(savedSelectedItemString)) {
                    System.out.println("Modul " + modul.getModulnameProperty().getValue() + "geloscht");
                    App.modulInputList.remove(modul);
                    isKursFound = true;
                    break;
                } else {
                    lastItemInList = modul.getModulnameProperty().getValue();
                }
                if (isKursFound) {
                    break;
                }

            }
        }
        initialize();

        selectItemByString(treeView.getRoot(), lastItemInList);

    }

    @FXML
    void kursEditButton(ActionEvent event) {
        setDisableFalse();

    }

    @FXML
    void modulEditButton(ActionEvent event) {
        setDisableFalse();

    }

    @FXML
    void kursSaveButton(ActionEvent event) {
        String savedSelectedItemString = selectedItemString.getValue();

        for (Modul modul : App.modulInputList) {
            for (Kurs kurs : modul.getKursliste()) {
                if (kurs.getKursnameProperty().getValue().equals(savedSelectedItemString)) {
                    if (isInputValid()) {
                        ;
                        int removeindex = 99;

                        modul.setModulname(modulNameView.getText());
                        modul.setStudiumstatus(modulStatusView.getText());
                        kurs.setKursID(kursIDView.getText());
                        kurs.setKursname(kursNameView.getText());

                        if (!note1.getText().isBlank() && !gw1.getText().isBlank()) {
                            if (kurs.getNotenList().size() > 0) {
                                kurs.getNotenList().get(0).setNote(Double.parseDouble(note1.getText()));
                                kurs.getNotenList().get(0).setgewichtung(Double.parseDouble(gw1.getText()));

                            } else {

                                kurs.getNotenList()
                                        .add(new Note(Double.parseDouble(note1.getText()),
                                                Double.parseDouble(gw1.getText())));

                            }
                        } else if (kurs.getNotenList().size() > 0 && note1.getText().isBlank()
                                && gw1.getText().isBlank()) {
                            removeindex = 0;

                        } else if (kurs.getNotenList().size() > 0 && note1.getText().isBlank()) {
                            kurs.getNotenList().get(0).setNote(1.0);
                            kurs.getNotenList().get(0).setgewichtung(Double.parseDouble(gw1.getText()));
                        } else if (kurs.getNotenList().size() > 0
                                && gw1.getText().isBlank()) {
                            kurs.getNotenList().get(0).setNote(Double.parseDouble(note1.getText()));
                            kurs.getNotenList().get(0).setgewichtung(0.0);
                        }
                        ;

                        if (!note2.getText().isBlank() && !gw2.getText().isBlank()) {
                            if (kurs.getNotenList().size() > 1) {
                                kurs.getNotenList().get(1).setNote(Double.parseDouble(note2.getText()));
                                kurs.getNotenList().get(1).setgewichtung(Double.parseDouble(gw2.getText()));
                            } else {

                                kurs.getNotenList()
                                        .add(new Note(Double.parseDouble(note2.getText()),
                                                Double.parseDouble(gw2.getText())));

                            }
                        } else if (kurs.getNotenList().size() > 1 && note2.getText().isBlank()
                                && gw2.getText().isBlank()) {
                            removeindex = 1;

                        } else if (kurs.getNotenList().size() > 1 && note2.getText().isBlank()) {
                            kurs.getNotenList().get(1).setNote(1.0);
                            kurs.getNotenList().get(1).setgewichtung(Double.parseDouble(gw2.getText()));

                        } else if (kurs.getNotenList().size() > 1
                                && gw2.getText().isBlank()) {
                            kurs.getNotenList().get(1).setNote(Double.parseDouble(note2.getText()));
                            kurs.getNotenList().get(1).setgewichtung(0.0);
                        }
                        ;

                        if (!note3.getText().isBlank() && !gw3.getText().isBlank()) {
                            if (kurs.getNotenList().size() > 2) {
                                kurs.getNotenList().get(2).setNote(Double.parseDouble(note3.getText()));
                                kurs.getNotenList().get(2).setgewichtung(Double.parseDouble(gw3.getText()));
                            } else {

                                kurs.getNotenList()
                                        .add(new Note(Double.parseDouble(note3.getText()),
                                                Double.parseDouble(gw3.getText())));

                            }
                        } else if (kurs.getNotenList().size() > 2 && note3.getText().isBlank()
                                && gw3.getText().isBlank()) {
                            removeindex = 2;

                        } else if (kurs.getNotenList().size() > 3 && note3.getText().isBlank()) {
                            kurs.getNotenList().get(2).setNote(1.0);
                            kurs.getNotenList().get(2).setgewichtung(Double.parseDouble(gw3.getText()));

                        } else if (kurs.getNotenList().size() > 3
                                && gw3.getText().isBlank()) {
                            kurs.getNotenList().get(2).setNote(Double.parseDouble(note3.getText()));
                            kurs.getNotenList().get(2).setgewichtung(0.0);
                        }

                        if (!note4.getText().isBlank() && !gw4.getText().isBlank()) {
                            if (kurs.getNotenList().size() > 3) {
                                kurs.getNotenList().get(3).setNote(Double.parseDouble(note4.getText()));
                                kurs.getNotenList().get(3).setgewichtung(Double.parseDouble(gw4.getText()));
                            } else {

                                kurs.getNotenList()
                                        .add(new Note(Double.parseDouble(note4.getText()),
                                                Double.parseDouble(gw4.getText())));
                            }
                        } else if (kurs.getNotenList().size() > 3 && note4.getText().isBlank()
                                && gw4.getText().isBlank()) {
                            removeindex = 3;

                        } else if (kurs.getNotenList().size() > 3 && note4.getText().isBlank()) {
                            kurs.getNotenList().get(3).setNote(1.0);
                            kurs.getNotenList().get(3).setgewichtung(Double.parseDouble(gw4.getText()));

                        } else if (kurs.getNotenList().size() > 3
                                && gw4.getText().isBlank()) {
                            kurs.getNotenList().get(3).setNote(Double.parseDouble(note4.getText()));
                            kurs.getNotenList().get(3).setgewichtung(0.0);
                        }

                        ;

                        if (removeindex < 5) {
                            kurs.getNotenList().remove(removeindex);
                            removeindex = 99;
                            System.out.println("Note" + removeindex + " geloescht");

                        }
                    }

                    int modulIndex = App.modulInputList.indexOf(modul);
                    App.modulInputList.set(modulIndex, modul);

                }
            }
        }
        setDisableTrue();

        initialize();
        selectItemByString(treeView.getRoot(), savedSelectedItemString);

    }

    @FXML
    void modulSaveButton(ActionEvent event) {

        String savedSelectedItemString = selectedItemString.getValue();

        for (Modul modul : App.modulInputList) {

            if (modul.getModulnameProperty().getValue().equals(savedSelectedItemString)) {
                if (isInputValid()) {
                    modul.setModulname(modulNameView1.getText());
                    modul.setStudiumstatus(modulStatusView1.getText());

                }
            }

            int modulIndex = App.modulInputList.indexOf(modul);
            App.modulInputList.set(modulIndex, modul);

        }
        setDisableTrue();

        initialize();
        selectItemByString(treeView.getRoot(), savedSelectedItemString);

    }

    // *********************************methods*********************************

    @FXML
    private void initialize() {

        TreeItem<String> rootItem = new TreeItem<>("Studium");
        treeView.setRoot(rootItem);
        rootItem.setExpanded(true);

        for (Modul modul : App.modulInputList) {
            TreeItem<String> modulItem = new TreeItem<>(modul.getModulnameProperty().getValue());
            rootItem.getChildren().add(modulItem);
            modulItem.setExpanded(true);

            for (Kurs kurs : modul.getKursliste()) {
                TreeItem<String> kursItem = new TreeItem<>(kurs.getKursnameProperty().getValue());
                modulItem.getChildren().add(kursItem);
                kursItem.setExpanded(true);
            }
        }

        treeView.getSelectionModel().selectedItemProperty()
                .addListener((ObservableValue<? extends TreeItem<String>> observable,
                        TreeItem<String> oldValue,
                        TreeItem<String> newValue) -> {
                    if (newValue != null) {
                        selectedItemString = newValue;
                        showDetails(newValue);
                    }
                });

    }

    private void selectItemByString(TreeItem<String> currentItem, String searchString) {
        if (currentItem.getValue().equals(searchString)) {
            treeView.getSelectionModel().select(currentItem);
            return;
        }

        for (TreeItem<String> child : currentItem.getChildren()) {
            selectItemByString(child, searchString);
        }
    }

    private void setDisableTrue() {
        modulNameView.setDisable(true);
        modulStatusView.setDisable(true);
        kursNameView.setDisable(true);
        kursIDView.setDisable(true);
        note1.setDisable(true);
        note2.setDisable(true);
        note3.setDisable(true);
        note4.setDisable(true);
        gw1.setDisable(true);
        gw2.setDisable(true);
        gw3.setDisable(true);
        gw4.setDisable(true);

        modulNameView1.setDisable(true);
        modulStatusView1.setDisable(true);

    };

    private void setDisableFalse() {
        modulNameView.setDisable(false);
        modulStatusView.setDisable(false);
        kursNameView.setDisable(false);
        kursIDView.setDisable(false);
        note1.setDisable(false);
        note2.setDisable(false);
        note3.setDisable(false);
        note4.setDisable(false);
        gw1.setDisable(false);
        gw2.setDisable(false);
        gw3.setDisable(false);
        gw4.setDisable(false);

        modulNameView1.setDisable(false);
        modulStatusView1.setDisable(false);

    };

    private void clearKursDetails() {

        note1.setText("");
        note2.setText("");
        note3.setText("");
        note4.setText("");
        gw1.setText("");
        gw2.setText("");
        gw3.setText("");
        gw4.setText("");

    }

    // *********************************Daten
    // Anzeigen*********************************

    private void showDetails(TreeItem<String> selectedItem) {
        clearKursDetails();
        setDisableTrue();

        if (selectedItem != null) {
            String selectedValue = selectedItem.getValue();

            boolean isKurs = false;
            boolean isModul = false;

            for (Modul modul : App.modulInputList) {
                for (Kurs kurs : modul.getKursliste()) {
                    if (kurs.getKursnameProperty().getValue().equals(selectedValue)) {
                        isKurs = true;
                        break;
                    }
                }
                if (isKurs) {
                    isModul = false;
                    break;
                } else if (modul.getModulnameProperty().getValue().equals(selectedValue)) {
                    isModul = true;
                    break;
                }
            }

            if (isKurs) {
                modulVboxView.setVisible(false);
                kursVBoxView.setVisible(true);

                for (Modul modul : App.modulInputList) {
                    for (Kurs kurs : modul.getKursliste()) {
                        if (kurs.getKursnameProperty().getValue().equals(selectedValue)) {
                            modulNameView.setText(modul.getModulnameProperty().getValue());
                            modulStatusView.setText(modul.getStudiumsstatusProperty().getValue());
                            kursNameView.setText(kurs.getKursnameProperty().getValue());
                            kursIDView.setText(kurs.getKursIDProperty().getValue());

                            ks.setText(Double.toString(App.round((kurs.getKursSchnittProperty().getValue()), 3)));
                            if (kurs.getKursSchnittRelativProperty().getValue() == 0.0) {
                                ksrelativ.setText("Nicht berechenbar");
                            } else {
                                ksrelativ.setText(
                                        Double.toString(
                                                (App.round(kurs.getKursSchnittRelativProperty().getValue(), 3))));
                            }

                            getKursDetails(kurs);
                            kurs.berechneNotenbedarf();

                            if (kurs.getKursSchnittProperty().getValue() < 4) {
                                benötigtenote
                                        .setText(Double.toString(App.round(kurs.getNotenbedarfProperty().getValue(),
                                                3))); //
                                anzahlNoten.setText(Integer
                                        .toString((kurs.getAnzahlNotenZuSchreibenProperty().getValue()))); //

                            } else {
                                benötigtenote.setText("");
                                anzahlNoten.setText("");
                            }

                            return;
                        }
                    }
                }
            } else if (isModul) {
                modulVboxView.setVisible(true);
                kursVBoxView.setVisible(false);

                for (Modul modul : App.modulInputList) {

                    if (modul.getModulnameProperty().getValue().equals(selectedValue)) {
                        modulNameView1.setText(modul.getModulnameProperty().getValue());
                        modulStatusView1.setText(modul.getStudiumsstatusProperty().getValue());

                        for (Kurs kurs : modul.getKursliste()) {

                            getModulDetails(modul);

                            return;
                        }
                    }
                }

            }

            clearKursDetails();
        }
    }

    // *********************************Daten Anzeigen Modul
    // Daten*********************************
    private void getModulDetails(Modul modul) {
        List<Kurs> kursList = modul.getKursliste();
        if (kursList.size() >= 1) {
            k1_id.setText(modul.kursliste.get(0).getKursIDProperty().getValue());
            kSchnitt1.setText(
                    Double.toString((App.round(modul.kursliste.get(0).getKursSchnittProperty().getValue(), 2))));
            modulNotenSchnitt
                    .setText(Double.toString(App.round(modul.kursliste.get(0).getKursSchnittProperty().getValue(), 2)));
            kReSchnitt1.setText(
                    Double.toString(App.round(modul.kursliste.get(0).getKursSchnittRelativProperty().getValue(), 2)));
            relativerModulNotenSchnitt
                    .setText(Double.toString(
                            (App.round(modul.kursliste.get(0).getKursSchnittRelativProperty().getValue(), 2))));
        }

        if (kursList.size() >= 2) {
            k2_id.setText(modul.kursliste.get(1).getKursIDProperty().getValue());
            kSchnitt2
                    .setText(Double.toString(App.round(modul.kursliste.get(1).getKursSchnittProperty().getValue(), 2)));
            modulNotenSchnitt
                    .setText(Double.toString((App.round((modul.kursliste.get(0).getKursSchnittProperty().getValue()
                            + modul.kursliste.get(1).getKursSchnittProperty().getValue()) / 2, 2))));
            kReSchnitt2.setText(
                    Double.toString(App.round(modul.kursliste.get(1).getKursSchnittRelativProperty().getValue(), 2)));
            relativerModulNotenSchnitt
                    .setText(
                            Double.toString((App.round(
                                    (modul.kursliste.get(0).getKursSchnittRelativProperty().getValue()
                                            + modul.kursliste.get(1).getKursSchnittRelativProperty().getValue()) / 2,
                                    2))));
        }
        if (kursList.size() >= 3) {
            k3_id.setText(modul.kursliste.get(2).getKursIDProperty().getValue());
            kSchnitt3
                    .setText(Double.toString(App.round(modul.kursliste.get(2).getKursSchnittProperty().getValue(), 2)));
            modulNotenSchnitt
                    .setText(Double.toString((App.round((modul.kursliste.get(0).getKursSchnittProperty().getValue()
                            + modul.kursliste.get(1).getKursSchnittProperty().getValue()
                            + modul.kursliste.get(2).getKursSchnittProperty().getValue()) / 3, 2))));
            kReSchnitt3.setText(
                    Double.toString(App.round(modul.kursliste.get(2).getKursSchnittRelativProperty().getValue(), 2)));
            relativerModulNotenSchnitt
                    .setText(
                            Double.toString((App.round(
                                    (modul.kursliste.get(0).getKursSchnittRelativProperty().getValue()
                                            + modul.kursliste.get(1).getKursSchnittRelativProperty().getValue()
                                            + modul.kursliste.get(2).getKursSchnittRelativProperty().getValue()) / 3,
                                    2))));
        }
        if (kursList.size() >= 4) {
            k4_id.setText(modul.kursliste.get(3).getKursIDProperty().getValue());
            kSchnitt4
                    .setText(Double.toString(App.round(modul.kursliste.get(3).getKursSchnittProperty().getValue(), 2)));
            modulNotenSchnitt
                    .setText(Double.toString((App.round((modul.kursliste.get(0).getKursSchnittProperty().getValue()
                            + modul.kursliste.get(1).getKursSchnittProperty().getValue()
                            + modul.kursliste.get(2).getKursSchnittProperty().getValue()
                            + modul.kursliste.get(3).getKursSchnittProperty().getValue()) / 4, 2))));
            kReSchnitt4.setText(
                    Double.toString(App.round(modul.kursliste.get(3).getKursSchnittRelativProperty().getValue(), 2)));
            relativerModulNotenSchnitt
                    .setText(
                            Double.toString((App.round(
                                    (modul.kursliste.get(0).getKursSchnittRelativProperty().getValue()
                                            + modul.kursliste.get(1).getKursSchnittRelativProperty().getValue()
                                            + modul.kursliste.get(2).getKursSchnittRelativProperty().getValue()
                                            + modul.kursliste.get(3).getKursSchnittRelativProperty().getValue()) / 4,
                                    2))));
        }

    }

    // *********************************Daten Anzeigen Kurs
    // Daten*********************************

    private void getKursDetails(Kurs kurs) {
        List<Note> notenList = kurs.getNotenList();
        if (notenList.size() >= 1) {
            note1.setText(Double.toString(notenList.get(0).getNote()));
            gw1.setText(Double.toString(App.round(notenList.get(0).getgewichtung(), 3)));

        }
        if (notenList.size() >= 2) {
            note2.setText(Double.toString(notenList.get(1).getNote()));
            gw2.setText(Double.toString(App.round(notenList.get(1).getgewichtung(), 3)));

        }
        if (notenList.size() >= 3) {
            note3.setText(Double.toString(notenList.get(2).getNote()));
            gw3.setText(Double.toString(App.round(notenList.get(2).getgewichtung(), 3)));

        }
        if (notenList.size() >= 4) {
            note4.setText(Double.toString(notenList.get(3).getNote()));
            gw4.setText(Double.toString(App.round(notenList.get(3).getgewichtung(), 3)));

        }
    }

    // ------------------------Validierungen------------------------

    private boolean isInputValid() {

        String errorMessage = "";

        String savedSelectedItemString = selectedItemString.getValue();

        for (Modul modul : App.modulInputList) {
            if (modul.getModulnameProperty().getValue().equals(savedSelectedItemString)) {
                if (modulNameView1.getText().isBlank() || modulNameView1.getText().isEmpty()) {
                    errorMessage += "Modulname Fehler";
                    modulNameView1.setStyle("-fx-text-inner-color: red;");
                } else {
                    modulNameView1.setStyle("-fx-text-inner-color: Black;");
                }
                if (modulStatusView1.getText().isBlank() || modulStatusView1.getText().isEmpty()) {
                    errorMessage += "Modulstatus Fehler";
                    modulStatusView1.setStyle("-fx-text-inner-color: red;");
                } else {
                    modulStatusView1.setStyle("-fx-text-inner-color: Black;");
                }
            } else {

                for (Kurs kurs : modul.getKursliste()) {
                    if (kurs.getKursnameProperty().getValue().equals(savedSelectedItemString)) {

                        if (modulNameView.getText().isBlank() || modulNameView.getText().isEmpty()) {
                            errorMessage += "Modulname Fehler";
                            modulNameView.setStyle("-fx-text-inner-color: red;");
                        } else {
                            modulNameView.setStyle("-fx-text-inner-color: Black;");
                        }
                        if (modulStatusView.getText().isBlank() || modulStatusView.getText().isEmpty()) {
                            errorMessage += "Modulstatus Fehler";
                            modulStatusView.setStyle("-fx-text-inner-color: red;");
                        } else {
                            modulStatusView.setStyle("-fx-text-inner-color: Black;");
                        }

                        if (kursNameView.getText().isBlank() || kursNameView.getText().isEmpty()) {
                            errorMessage += "Kursname Fehler";
                            kursNameView.setStyle("-fx-text-inner-color: red;");
                        } else {
                            kursNameView.setStyle("-fx-text-inner-color: Black;");
                        }
                        if (kursIDView.getText().isBlank() || kursIDView.getText().isEmpty()) {
                            errorMessage += "KursID Fehler";
                            kursIDView.setStyle("-fx-text-inner-color: red;");
                        } else {
                            kursIDView.setStyle("-fx-text-inner-color: Black;");
                        }

                        if (note1.getText().isBlank() == false || note1.getText().isEmpty() == false) {
                            try {
                                double test = Double.parseDouble(note1.getText());
                                note1.setStyle("-fx-text-inner-color: Black;");
                            } catch (NumberFormatException e) {
                                errorMessage += "Note  1 ungültige eingabe";
                                note1.setStyle("-fx-text-inner-color: red;");
                            }
                        }

                        if (note2.getText().isBlank() == false || note2.getText().isEmpty() == false) {

                            try {
                                double test = Double.parseDouble(note2.getText());
                                note2.setStyle("-fx-text-inner-color: Black;");
                            } catch (NumberFormatException e) {
                                errorMessage += "Note  2 ungültige eingabe";
                                note2.setStyle("-fx-text-inner-color: red;");
                            }
                        }
                        if (note3.getText().isBlank() == false || note3.getText().isEmpty() == false) {

                            try {
                                double test = Double.parseDouble(note3.getText());
                                note3.setStyle("-fx-text-inner-color: Black;");
                            } catch (NumberFormatException e) {
                                errorMessage += "Note  3 ungültige eingabe";
                                note3.setStyle("-fx-text-inner-color: red;");
                            }

                        }
                        if (note4.getText().isBlank() == false || note4.getText().isEmpty() == false) {
                            try {
                                double test = Double.parseDouble(note4.getText());
                                note4.setStyle("-fx-text-inner-color: Black;");
                            } catch (NumberFormatException e) {
                                errorMessage += "Note  4 ungültige eingabe";
                                note4.setStyle("-fx-text-inner-color: red;");
                            }
                        }

                        double sum = 0.0;
                        if (kurs.getNotenList().size() >= 0) {

                            if (gw1.getText().isBlank() == false || gw1.getText().isEmpty() == false) {

                                try {
                                    double test = Double.parseDouble(gw1.getText());
                                    sum += test;
                                    gw1.setStyle("-fx-text-inner-color: Black;");
                                } catch (NumberFormatException e) {
                                    errorMessage += "Note gewichtung  1 ungültige eingabe";
                                    gw1.setStyle("-fx-text-inner-color: red;");
                                }
                            }
                        }
                        if (kurs.getNotenList().size() >= 1) {
                            if (gw2.getText().isBlank() == false || gw2.getText().isEmpty() == false) {

                                try {
                                    double test = Double.parseDouble(gw2.getText());
                                    sum += test;
                                    gw2.setStyle("-fx-text-inner-color: Black;");
                                } catch (NumberFormatException e) {
                                    errorMessage += "Note gewichtung  2 ungültige eingabe";
                                    gw2.setStyle("-fx-text-inner-color: red;");
                                }
                            }
                        }
                        if (kurs.getNotenList().size() >= 2) {
                            if (gw3.getText().isBlank() == false || gw3.getText().isEmpty() == false) {

                                try {
                                    double test = Double.parseDouble(gw3.getText());
                                    sum += test;
                                    gw3.setStyle("-fx-text-inner-color: Black;");
                                } catch (NumberFormatException e) {
                                    errorMessage += "Note gewichtung 3 ungültige eingabe";
                                    gw3.setStyle("-fx-text-inner-color: red;");
                                }
                            }
                        }
                        if (kurs.getNotenList().size() >= 3) {
                            if (gw4.getText().isBlank() == false || gw4.getText().isEmpty() == false) {

                                try {
                                    double test = Double.parseDouble(gw4.getText());
                                    sum += test;
                                    gw4.setStyle("-fx-text-inner-color: Black;");
                                } catch (NumberFormatException e) {
                                    errorMessage += "Note gewichtung 4 ungültige eingabe";
                                    gw4.setStyle("-fx-text-inner-color: red;");
                                }

                            }
                        }

                        if (sum > 1.05) {
                            errorMessage = "Gewichtung größer als 1.0 (also größer als 100%)";
                            gw1.setStyle("-fx-text-inner-color: red;");
                            gw2.setStyle("-fx-text-inner-color: red;");
                            gw3.setStyle("-fx-text-inner-color: red;");
                            gw4.setStyle("-fx-text-inner-color: red;");
                        } else {
                            gw1.setStyle("-fx-text-inner-color: black;");
                            gw2.setStyle("-fx-text-inner-color: black;");
                            gw3.setStyle("-fx-text-inner-color: black;");
                            gw4.setStyle("-fx-text-inner-color: black;");
                        }

                    }
                }

            }

        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the Error Message
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Felder Valdierung");
            alert.setHeaderText("Bitte korriegieren sie die ungülitige eingaben");
            alert.setContentText(errorMessage + " ");
            alert.showAndWait();
            return false;
        }
    }
}