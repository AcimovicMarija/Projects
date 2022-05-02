package vesala;

public class LjudskiIgrac extends Igrac{
    private char izabranoSlovo;

    public LjudskiIgrac(String ime, int nivo, int brOdigranihPartija, int brOsvojenihPartija) {
        super(ime, nivo, brOdigranihPartija, brOsvojenihPartija);
    }

    public void setIzabranoSlovo(char izabranoSlovo){
        if(Character.isAlphabetic(izabranoSlovo))
            this.izabranoSlovo = izabranoSlovo;
    }

    @Override
    public char odaberiSlovo(Rec trazenaRec) {
        return izabranoSlovo;
    }
}
