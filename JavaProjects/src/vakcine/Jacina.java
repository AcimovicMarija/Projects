package vakcine;

public enum Jacina {
    SLABA(0), SREDNJA(1), JAKA(2);
    private int rbr;
    private Jacina(int rbr) {
        this.rbr = rbr;
    }

    public int getRbr() {
        return rbr;
    }

    public static Jacina izBroja(int rbr){
        switch (rbr){
            case 0: return SLABA;
            case 1: return SREDNJA;
            case 2: return JAKA;
            default: throw new IllegalArgumentException("Pogresan rbr");
        }
    }

    @Override
    public String toString() {
        switch (rbr){
            case 0: return "SLABA";
            case 1: return "SREDNJA";
            case 2: return "JAKA";
            default: return "";
        }
    }
}
