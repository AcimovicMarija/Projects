package pozoristeKlaseEkvivalencije;

public class Pozoriste {
    //metoda koja racuna popust na kolicinu karata
    //1-5 karata, nema popusta (L1)
    //6-10 karata, 10 posto popusta (L2)
    //11-20 karata, 20 posto popusta (L3)
    //21 i vise, 30 posto popusta (L4)
    //(N1) 0 i negativne vrednosti
    // (N2) 200 i vise
    public static int izracunajPopust (int brojKarata) throws IllegalArgumentException{
        if (brojKarata < 0) {
            throw new IllegalArgumentException();
        } else if (brojKarata < 5){
            return 0;
        } else if (brojKarata <= 10){
            return 10;
        } else if (brojKarata <= 20){
            return 20;
        } else if (brojKarata <= 200){
            return 30;
        } else throw new IllegalArgumentException();

    }
}
