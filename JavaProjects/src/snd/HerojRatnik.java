package snd;

public class HerojRatnik extends Heroj{

    public HerojRatnik(String ime, Nivo nivo, Rasa rasa, AtributiHeroja atributiHeroja) {
        super(ime, nivo, rasa, atributiHeroja);
    }

    @Override
    public int izracunajMinNapad() {
        return nivo.getLevel() + atributiHeroja.getInteligencija();
    }
    @Override
    public int izracunajMaxNapad() {
        return (int) Math.ceil(nivo.getLevel() + atributiHeroja.getInteligencija() + 0.4 * atributiHeroja.getSpretnost());
    }
    @Override
    public int izracunajZivot() {
        return nivo.getLevel() + atributiHeroja.getKonstitucija();
    }

    @Override
    public String toString() {
        return "[ratnik]" + super.toString();
    }
}
