package transliteratorParametrizovanoTestiranje;

public class Proba {
    public static void main(String[] args) {
        Transliterator t = Transliterator.getInstance();

        // Ovo je primer korišćenja klase Transliterator. Postoji greška. Napišite testove da je nađete.
        System.out.println(t.transliterate("Пресловљавање ћирилице на латиницу."));
    }
}
