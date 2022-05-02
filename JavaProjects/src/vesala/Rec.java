package vesala;

import java.util.ArrayList;
import java.util.List;

public class Rec {
    private String rec;
    private String nagovestaj;
    private List<Character> isprobanaSlova;
    private int tezina;

    public static int tezinaReci(String rec){
        long brSuglasnika = rec.toLowerCase().trim().chars()
                .filter(c -> c>='a' && c<='z')
                .filter(c-> c!='a' && c!='e' && c!='i' && c!='o' && c!='u')
                .count();

        double procenatSuglasnika = brSuglasnika / (double)rec.length();
        double tezinaProcenat = procenatSuglasnika * (1-(double)rec.length()/(rec.length()+10));

        if(tezinaProcenat<0.333)
            return 1;
        else if(tezinaProcenat<0.666)
            return 2;
        else
            return 3;
    }

    public Rec(String rec) {
        this.rec = rec;
        this.isprobanaSlova = new ArrayList<>();
        this.tezina = tezinaReci(rec);
    }

    public Rec(String rec, String nagovestaj) {
        this.rec = rec;
        this.nagovestaj = nagovestaj;
        this.isprobanaSlova = new ArrayList<>();
        this.tezina = tezinaReci(rec);
    }

    public String getRec() {
        return rec;
    }

    public String getNagovestaj() {
        return nagovestaj;
    }

    public List<Character> getIsprobanaSlova() {
        return isprobanaSlova;
    }

    public int getTezina() {
        return tezina;
    }

    public void setRec(String rec) {
        this.rec = rec;
    }

    public String pogodjenaSlovaReci(){
        char[] reci = rec.toCharArray();
        for(int i=0; i<reci.length; i++)
            if(!isprobanaSlova.contains(reci[i]))
                reci[i]='_';
        return String.valueOf(reci);
    }

    public void dodajIsprobanoSlovo(char slovo){
        isprobanaSlova.add(slovo);
    }

    @Override
    public String toString() {
        return rec + ", " + tezina;
    }
}
