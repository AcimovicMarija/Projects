package vakcine;

import java.util.Random;

public class Fajzer extends Vakcina{
    private Jacina jacinaDoze;

    public Fajzer(String identifikator, Jacina jacinaDoze) {
        super(identifikator);
        this.jacinaDoze = jacinaDoze;
    }

    public Jacina getJacinaDoze() {
        return jacinaDoze;
    }

    @Override
    public boolean vakcinisi() {
        broj++;

        Random random = new Random(java.lang.System.currentTimeMillis());
        int uspehVakcinacije = random.nextInt(100);

        switch (jacinaDoze){
            case SLABA: return uspehVakcinacije<30;
            case SREDNJA: return uspehVakcinacije<60;
            case JAKA: return uspehVakcinacije<90;
            default: return false;
        }
    }

    @Override
    public String toString() {
        return getIdentifikator() + " " + jacinaDoze.toString();
    }
}
