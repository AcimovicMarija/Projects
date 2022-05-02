package pozoristeKlaseEkvivalencije;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PozoristeTest {
    //Klase ekvivalencije
    @org.junit.jupiter.api.Test
    void izracunajPopustLK1() {
        Assertions.assertEquals(0, Pozoriste.izracunajPopust(2));
    }

    @org.junit.jupiter.api.Test
    void izracunajPopustLK2() {
        Assertions.assertEquals(10, Pozoriste.izracunajPopust(8));
    }

    @org.junit.jupiter.api.Test
    void izracunajPopustLK3() {Assertions.assertEquals(20, Pozoriste.izracunajPopust(14));
    }

    @org.junit.jupiter.api.Test
    void izracunajPopustLK4() {
        Assertions.assertEquals(30, Pozoriste.izracunajPopust(30));
    }

    @org.junit.jupiter.api.Test
    void izracunajPopustNK1() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Pozoriste.izracunajPopust(-3);
        });

    }

    @Test
    void izracunajPopustNK2() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Pozoriste.izracunajPopust(225);
        });
    }

    //granicne vrednosti
    @Test
    void izracunajPopustGV0() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Pozoriste.izracunajPopust(0);
        });
    }

    @Test
    void izracunajPopustGVMinus1() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Pozoriste.izracunajPopust(-1);
        });
    }

    @Test
    void izracunajPopustGV1() {
        Assertions.assertEquals(0, Pozoriste.izracunajPopust(1));
    }

    @Test
    void izracunajPopustGV5() {
        Assertions.assertEquals(0, Pozoriste.izracunajPopust(5));
    }

    @Test
    void izracunajPopustGV4() {
        Assertions.assertEquals(0, Pozoriste.izracunajPopust(4));
    }

    @Test
    void izracunajPopustGV6() {
        Assertions.assertEquals(10, Pozoriste.izracunajPopust(6));
    }

}