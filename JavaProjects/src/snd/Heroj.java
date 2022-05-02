package snd;

public abstract class Heroj implements Comparable<Heroj>{
    String ime;
    Nivo nivo;
    Rasa rasa;
    AtributiHeroja atributiHeroja;

    public Heroj(String ime, Nivo nivo, Rasa rasa, AtributiHeroja atributiHeroja) {
        this.ime = ime;
        this.nivo = nivo;
        this.rasa = rasa;
        this.atributiHeroja = atributiHeroja;
    }

    public String getIme() {
        return ime;
    }

    public Nivo getNivo() {
        return nivo;
    }

    public Rasa getRasa() {
        return rasa;
    }

    public AtributiHeroja getAtributiHeroja() {
        return atributiHeroja;
    }

    public abstract int izracunajMinNapad();
    public abstract int izracunajMaxNapad();
    public abstract int izracunajZivot();

    @Override
    public int compareTo(Heroj o) {
        if(this.nivo.getLevel() != o.nivo.getLevel())
            return o.nivo.getLevel() - this.nivo.getLevel();        // opadajuce, <0

        if(this instanceof HerojRatnik && o instanceof HerojMag)    // ako smo mi ratnici, hocemo da budemo veci
            return 1;

        if(this instanceof HerojMag && o instanceof HerojRatnik)  // ako smo mi magovi, hocemo da budemo manji
            return -1;

        return this.izracunajZivot() - o.izracunajZivot();     // ako smo iste vrste, onda se sortira po zivotu rastuce
    }

    @Override
    public String toString() {
        return String.format(" [%s] %s %s %s napad: %d-%d hp: %d",
                nivo, ime,rasa, atributiHeroja, izracunajMinNapad(), izracunajMaxNapad(), izracunajZivot());
    }
}
