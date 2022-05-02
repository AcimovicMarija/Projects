package vakcine;

public abstract class Vakcina {
    private String identifikator;
    public static int broj=0;   // broj vakcinisanih osoba

    public Vakcina(String identifikator) {
        this.identifikator = identifikator;
    }

    public String getIdentifikator() {
        return identifikator;
    }

    public abstract boolean vakcinisi();
}
