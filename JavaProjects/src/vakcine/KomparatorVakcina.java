package vakcine;

import java.util.Comparator;

public class KomparatorVakcina implements Comparator<Vakcina> {
    @Override
    public int compare(Vakcina v1, Vakcina v2) {
        if(v1 instanceof Fajzer && v2 instanceof SputnjikV)
            return -1;
        else if(v1 instanceof SputnjikV && v2 instanceof Fajzer)
            return +1;
        else if (v1 instanceof Fajzer && v2 instanceof Fajzer)
            return - Integer.compare( ((Fajzer)v1).getJacinaDoze().getRbr(),  ((Fajzer)v2).getJacinaDoze().getRbr() );
        else
            return Integer.compare( ((SputnjikV)v1).getBrMeseci(), ((SputnjikV)v2).getBrMeseci() );
    }
}
