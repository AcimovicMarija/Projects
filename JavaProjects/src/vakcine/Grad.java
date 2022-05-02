package vakcine;

import java.util.Map;
import java.util.TreeMap;

public class Grad {
    private String ime;
    private Map<String, Boolean> ljudi;
    private int brUspesnoVakcinisanih=0;

    public Grad(String ime) {
        this.ime = ime;
        ljudi = new TreeMap<>();
    }

    public String getIme() {
        return ime;
    }

    public Map<String, Boolean> getLjudi() {
        return ljudi;
    }

    public void dodajOsobu(String jmbg, boolean uspeh){
        ljudi.put(jmbg, uspeh);
        if(uspeh)
            brUspesnoVakcinisanih++;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(brUspesnoVakcinisanih==0)
            return "";
        sb.append(ime + "\n");

        for(Map.Entry<String, Boolean> entry: ljudi.entrySet()){
            String jmbg = entry.getKey();
            Boolean uspeh = entry.getValue();

            if(uspeh)
                sb.append(jmbg + "\n");
        }

        return sb.toString();
    }
}
