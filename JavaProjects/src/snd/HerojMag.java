package snd;

public class HerojMag extends Heroj{

    public HerojMag(String ime, Nivo nivo, Rasa rasa, AtributiHeroja atributiHeroja) {
        super(ime, nivo, rasa, atributiHeroja);
    }

    @Override
    public int izracunajMinNapad() {
        return nivo.getLevel() + atributiHeroja.getSnaga();
    }
    @Override
    public int izracunajMaxNapad() {
        return (int) Math.ceil(nivo.getLevel() + atributiHeroja.getSnaga() + 0.4 * atributiHeroja.getSpretnost());
    }
    @Override
    public int izracunajZivot() {
        return (int) Math.ceil(nivo.getLevel() + atributiHeroja.getKonstitucija() + 0.3f * atributiHeroja.getSnaga());
    }

    @Override
    public String toString() {
        return "[mag]" + super.toString();
    }
}
