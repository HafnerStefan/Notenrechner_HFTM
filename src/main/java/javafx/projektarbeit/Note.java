package javafx.projektarbeit;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Note {

    private DoubleProperty note;
    private DoubleProperty gewichtung;

    // default Constructor

    public Note() {
        this.note = new SimpleDoubleProperty(1.0);
        this.gewichtung = new SimpleDoubleProperty(1 / 3);

    }

    // Constructor
    public Note(double note, double gewichtung) {
        this.note = new SimpleDoubleProperty(note);
        this.gewichtung = new SimpleDoubleProperty(gewichtung);

    }

    // getter

    public Double getNote() {
        return note.get();
    }

    public double getgewichtung() {
        return gewichtung.get();
    }

    // setter

    public void setNote(double note) {
        this.note.set(note);
    }

    public void setgewichtung(double gewichtung) {
        this.gewichtung.set(gewichtung);
    }

    // method
    public String toString() {
        return (note + " " + gewichtung);
    }

    public DoubleProperty getNoteProperty() {
        return note;
    }

    public DoubleProperty getgewichtungProperty() {
        return gewichtung;
    }

}
