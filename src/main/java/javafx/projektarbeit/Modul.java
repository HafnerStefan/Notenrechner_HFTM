package javafx.projektarbeit;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Modul {

    private StringProperty modulname;
    private StringProperty studiumsstatus;
    private DoubleProperty modulschnitt;

    List<Kurs> kursliste = new ArrayList<Kurs>();

    // default Constructor

    public Modul() {
        this(null, null);

    }

    // Constructor
    public Modul(String modulname, String studiumsstatus) {
        this.modulname = new SimpleStringProperty(modulname);
        this.studiumsstatus = new SimpleStringProperty(studiumsstatus);

    }

    // getter

    public String getModulname() {
        return modulname.get();
    }

    public String getStudiumsstatus() {
        return studiumsstatus.get();
    }

    public Double getModulschnitt() {
        return modulschnitt.get();
    }

    // setter

    public void setModulname(String modulname) {
        this.modulname.set(modulname);
    }

    public void setStudiumstatus(String studiumsstatus) {
        this.studiumsstatus.set(studiumsstatus);
    }

    // method

    public void addNewNote(String kursname, String kursID) {
        Kurs newKurs = new Kurs(kursname, kursID);
        kursliste.add(newKurs);
    }

    public List<Kurs> getKursliste() {
        return kursliste;
    }

    public StringProperty getModulnameProperty() {
        return modulname;
    }

    public StringProperty getStudiumsstatusProperty() {
        return studiumsstatus;
    }

    public DoubleProperty getModulschnittProperty() {
        return modulschnitt;
    }

}
