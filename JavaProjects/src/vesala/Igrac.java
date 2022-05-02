package vesala;

public abstract class Igrac {
    String ime;
    int nivo;
    int brOdigranihPartija;
    int brOsvojenihPartija;

    public Igrac(String ime, int nivo, int brOdigranihPartija, int brOsvojenihPartija) {
        this.ime = ime;
        this.nivo = nivo;
        this.brOdigranihPartija = brOdigranihPartija;
        this.brOsvojenihPartija = brOsvojenihPartija;
    }

    public String getIme() {
        return ime;
    }

    public int getNivo() {
        return nivo;
    }

    public int getBrOdigranihPartija() {
        return brOdigranihPartija;
    }

    public int getBrOsvojenihPartija() {
        return brOsvojenihPartija;
    }

    public abstract char odaberiSlovo(Rec trazenaRec);

    @Override
    public String toString() {
        return String.format("[lvl%d] %s, %f%", nivo, ime, brOsvojenihPartija/brOdigranihPartija);
    }
}
