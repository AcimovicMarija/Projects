package student;

public class Test {
    final private String imePredmeta;
    final private String datumIso;
    final private int brojPitanja;
    final private int brojTacnihOdgovora;

    /**
     * Konstruise objekat koji sadrzi podatke o testi, medju kojima su ime predmeta,
     * datum u ISO formatu, broj pitanja na testu kao i broj tacno odgovorenih pitanja.
     * Konstruktor ne dozvoljava setovanje broja pitanja na vrednost manju od 1, a
     * broj tacno odgovorenih pitanja ne moze biti vrednost manja od 0.
     * Za vrednosti imena predmeta i broja indeksa, koje su tekstualne, ne postoje ogranicenja.
     * @param imePredmeta Tekstualni podatak koji predstavlja ime predmeta
     * @param datumIso Tekstualni podatak koji predstavlja datum u ISO formatu
     * @param brojPitanja Ceo broj, ne manji od 1, koji predstavlja broj pitanja na testu
     * @param brojTacnihOdgovora Ceo broj, ne manji od 0, koji predstavlja broj tacno odgvoorenih pitanja
     */

    public Test(String imePredmeta, String datumIso, int brojPitanja, int brojTacnihOdgovora) {
        this.imePredmeta = imePredmeta;
        this.datumIso = datumIso;
        this.brojPitanja = Math.max(1, brojPitanja);
        this.brojTacnihOdgovora = Math.max(0, brojTacnihOdgovora);
    }

    public String getImePredmeta() {
        return imePredmeta;
    }

    public String getDatumIso() {
        return datumIso;
    }

    public int getBrojPitanja() { return brojPitanja; }

    public int getBrojTacnihOdgovora() { return brojTacnihOdgovora; }
    public double getProcenatTacnihOdgovora() {

        return 100. * brojTacnihOdgovora / brojPitanja;
    }

    /**
     * Vraca broj poena ostvarenih na testu izracunat na osnovu procenta
     * tacno odgovorenih pitanja i maksimalnog broja poena koliko
     * vredi test, tako da za 100% tacan test vrati maksimum broja poena
     */
    public double getBrojPoena(int maksimum) {
        return 0.01 * maksimum * getProcenatTacnihOdgovora();
    }

    @Override
    public String toString() {
        return  String.format(
                "%s;%s;%d;%d;%.2f",
                imePredmeta,
                datumIso,
                brojTacnihOdgovora,
                brojPitanja,
                getProcenatTacnihOdgovora()
        );
    }
}
