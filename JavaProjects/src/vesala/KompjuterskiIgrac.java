package vesala;

import java.util.Random;

public class KompjuterskiIgrac extends Igrac{
    private static Random random;

    public KompjuterskiIgrac(String ime, int nivo, int brOdigranihPartija, int brOsvojenihPartija) {
        super(ime, nivo, brOdigranihPartija, brOsvojenihPartija);
        random = new Random();
    }

    @Override
    public char odaberiSlovo(Rec trazenaRec) {
        while (true){
            char slovo = (char) ('a'+random.nextInt(26));
            if(!trazenaRec.getIsprobanaSlova().contains(slovo))
                return slovo;
        }
    }

}
