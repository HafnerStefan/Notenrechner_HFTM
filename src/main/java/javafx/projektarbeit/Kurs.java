package javafx.projektarbeit;

import java.util.ArrayList;
import java.util.List;

//import org.apache.commons.math3.linear.Array2DRowRealMatrix;
//import org.apache.commons.math3.linear.DecompositionSolver;
//import org.apache.commons.math3.linear.LUDecomposition;
//import org.apache.commons.math3.linear.RealMatrix;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Kurs {

    private StringProperty kursname;
    private StringProperty kursID;

    private DoubleProperty kursschnitt;
    private DoubleProperty relativeKursschnitt;
    private DoubleProperty notenbedarf;
    private IntegerProperty anzahlNotenZuSchreiben;

    List<Note> notenliste = new ArrayList<Note>();

    // default Constructor

    public Kurs() {
        this(null, null);
        this.kursschnitt = new SimpleDoubleProperty(0.0);

    }

    // Constructor
    public Kurs(String kursname, String kursID) {
        this.kursname = new SimpleStringProperty(kursname);
        this.kursID = new SimpleStringProperty(kursID);
        this.kursschnitt = new SimpleDoubleProperty(0.0);
        this.relativeKursschnitt = new SimpleDoubleProperty(0.0);
        this.notenbedarf = new SimpleDoubleProperty(0.0);
        this.anzahlNotenZuSchreiben = new SimpleIntegerProperty(0);

    }

    // getter

    public String getKursname() {
        return kursname.get();
    }

    public String getKursID() {
        return kursID.get();
    }

    public Double getKursSchnitt() {
        return kursschnitt.get();
    }

    // setter

    public void setKursname(String kursname) {
        this.kursname.set(kursname);
    }

    public void setKursID(String kursID) {
        this.kursID.set(kursID);
    }

    // method

    public void addNewNote(double note, double gewichtung) {
        Note newNote = new Note(note, gewichtung);
        notenliste.add(newNote);
    }

    public String toString() {
        return (kursname + " " + kursID + " " + kursschnitt);
    }

    public StringProperty getKursnameProperty() {
        return kursname;
    }

    public StringProperty getKursIDProperty() {
        return kursID;
    }

    public DoubleProperty getKursSchnittProperty() {
        MathKursschnitt();
        return kursschnitt;
    }

    public DoubleProperty getKursSchnittRelativProperty() {
        RelativMathKursschnitt();
        return relativeKursschnitt;
    }

    public DoubleProperty getNotenbedarfProperty() {
        berechneNotenbedarf();
        return notenbedarf;
    }

    public IntegerProperty getAnzahlNotenZuSchreibenProperty() {
        berechneNotenbedarf();
        return anzahlNotenZuSchreiben;
    }

    public List<Note> getNotenList() {
        return notenliste;
    }

    // Method

    public void MathKursschnitt() {

        double summe = 0.0;
        double gewichtungsumme = 0.0;
        int counter = 0;

        for (Note note : notenliste) {
            summe += note.getNote() * note.getgewichtung();
            gewichtungsumme += note.getgewichtung();
            counter++;

        }

        if (gewichtungsumme < 1.05 && gewichtungsumme > 0.1) {
            this.kursschnitt.set(summe);
        } else {
            this.kursschnitt.set(0.0);
        }

    }

    public void RelativMathKursschnitt() {

        double summe = 0.0;
        double gewichtungsumme = 0.0;
        int counter = 0;

        for (Note note : notenliste) {

            if (note.getNote() < 1.1) {
                summe += 4.0 * note.getgewichtung();

            } else {
                summe += note.getNote() * note.getgewichtung();
            }
            gewichtungsumme += note.getgewichtung();
            counter++;

        }

        if (gewichtungsumme < 1.05 && gewichtungsumme > 0.9) {
            this.relativeKursschnitt.set(summe);
        } else {
            this.relativeKursschnitt.set(0.0);
        }

    }

    public void berechneNotenbedarf() {

        double gewichtungsumme = 0.0;
        int AnzahlNoten = notenliste.size();
        int zuSchreiben = 0;
        double gewichtungGeschriebenerNoten = 0.0;
        double gewichtungZuSchreibenNoten = 0.0;
        double bedarf = 0.0;

        for (Note note : notenliste) {
            gewichtungsumme += note.getgewichtung();
            if (note.getNoteProperty().getValue() < 1.05) {
                zuSchreiben += 1;
                gewichtungZuSchreibenNoten += note.getgewichtungProperty().getValue();

            } else {
                gewichtungGeschriebenerNoten += note.getgewichtungProperty().getValue();
            }
        }

        bedarf = ((4.0 - kursschnitt.getValue()) / gewichtungZuSchreibenNoten) + 1;

        if (gewichtungsumme < 1.05 && gewichtungsumme > 0.9) {

            this.notenbedarf.set(bedarf);
            this.anzahlNotenZuSchreiben.set(zuSchreiben);
        } else {
            this.notenbedarf.set(0.0);
            this.anzahlNotenZuSchreiben.set(0);
        }

    }

}
