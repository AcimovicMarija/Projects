package snd;

public enum Rasa {
    Covek, Vilenjak, Patuljak;

    public static Rasa napraviRasu(String s){
        switch (s){
            case "covek": return Covek;
            case "vilenjak": return Vilenjak;
            case "patuljak": return Patuljak;
            default: return Covek;
        }
    }
}
