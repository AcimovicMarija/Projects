package kalkulator;

import org.junit.jupiter.api.Assertions;

class KalkulatorTest {

    @org.junit.jupiter.api.Test
    void getA() {
        Kalkulator k1 = new Kalkulator(10.0, 5.0);
        Assertions.assertEquals(10.0, k1.getA());
    }

    @org.junit.jupiter.api.Test
    void getB() {
        Kalkulator k1 = new Kalkulator(10.0, 5.0);
        Assertions.assertEquals(5.0, k1.getB());
    }

    @org.junit.jupiter.api.Test
    void saberi() {
        Kalkulator k1 = new Kalkulator(10.0, 5.0);
        Assertions.assertEquals(15.0, k1.saberi());
    }

    @org.junit.jupiter.api.Test
    void oduzmi() {
        Kalkulator k1 = new Kalkulator(10.0, 5.0);
        Assertions.assertEquals(5.0, k1.oduzmi());
    }

    @org.junit.jupiter.api.Test
    void pomnozi() {
        Kalkulator k1 = new Kalkulator(10.0, 5.0);
        Assertions.assertEquals(50.0, k1.pomnozi());
    }
}