package student;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Student {
    private final String ime;
    private final String prezime;
    private final String brojIndeksa;
    private final List<student.Test> radjeniTestovi;

    /**
     * Konstruise objekat klase Student sa svim potrebnim podacima
     * @param ime Ime studenta
     * @param prezime Prezime studenta
     * @param brojIndeksa Broj indeksa studenta
     */
    public Student(String ime, String prezime, String brojIndeksa) {
        this.ime = ime;
        this.prezime = prezime;
        this.brojIndeksa = brojIndeksa;
        this.radjeniTestovi = new LinkedList<>();
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getBrojIndeksa() {
        return brojIndeksa;
    }

    public void evidentirajRadjeniTest(student.Test radjeniTest) {
        this.radjeniTestovi.add(radjeniTest);
    }

    public double getBrojPoenaZaPredmet(String predmet) {
        final Optional<student.Test> najnoviji =
                this.radjeniTestovi
                        .stream()
                        .filter(test -> test.getImePredmeta().equals(predmet))
                        .sorted((r1, r2) -> r2.getDatumIso().compareTo(r1.getDatumIso()))
                        .findFirst();

        if (!najnoviji.isPresent()) {
            return 0.;
        }

        return najnoviji.get().getBrojPoena(30);
    }
}

