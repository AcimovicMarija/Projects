package vakcine;

import java.util.Random;

public class SputnjikV extends Vakcina{
    private String vektor;  //ime nosioca (vektora) u vakcini
    private int brMeseci;

    public SputnjikV(String identifikator, String vektor, int brMeseci) {
        super(identifikator);
        this.vektor = vektor;
        this.brMeseci = brMeseci;
    }

    public String getVektor() {
        return vektor;
    }

    public int getBrMeseci() {
        return brMeseci;
    }

    @Override
    public boolean vakcinisi() {
        broj++;
        Random random = new Random(java.lang.System.currentTimeMillis());

        if(vektor.compareTo("RNK")==0)
            return true;
        else if(vektor.compareTo("DNK") == 0)
            return random.nextBoolean();        //sanse su 50:50 da se generise true ili false
        else
            return false;
    }

    @Override
    public String toString() {
        return getIdentifikator() + " " + vektor + " " + brMeseci;
    }
}
