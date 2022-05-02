package student;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestTest {

    @Test
    void getImePredmeta() {
        student.Test t = new student.Test(
                "Analiza 1", "2014-01-20", 30, 15);
        Assertions.assertEquals("Analiza 1", t.getImePredmeta());
    }

    @Test
    void getDatumIso() {
        student.Test t = new student.Test(
                "ime predmeta", "2014-01-20", 30, 15);
        Assertions.assertEquals("2014-01-20", t.getDatumIso());
    }

    @Test
    void getBrojPitanja() {
        student.Test t = new student.Test(
                "ime predmeta", "2014-01-20", 30, 15);
        Assertions.assertEquals(30, t.getBrojPitanja());
    }

    @Test
    void getBrojPitanjaAkoJeSetovano0() {
        student.Test t = new student.Test(
                "ime predmeta", "2014-01-20", 0, 15);
        Assertions.assertEquals(1, t.getBrojPitanja());
    }

    @Test
    void getBrojPitanjaAkoJeSetovanaNegativnaVrednost() {
        student.Test t = new student.Test(
                "ime predmeta", "2014-01-20", -5, 15);
        Assertions.assertEquals(1, t.getBrojPitanja());
    }

    @Test
    void getBrojTacnihOdgovora() {
        student.Test t = new student.Test(
                "ime predmeta", "2014-01-20", 30, 15);
        Assertions.assertEquals(15, t.getBrojTacnihOdgovora());
    }

    @Test
    void getBrojTacnihOdgovoraAkoJeSetovanaNegativnaVrednost() {
        student.Test t = new student.Test(
                "ime predmeta", "2014-01-20", 30, -5);
        Assertions.assertEquals(0, t.getBrojTacnihOdgovora());
    }

    @Test
    void getProcenatTacnihOdgovora() {
        student.Test t = new student.Test(
                "ime predmeta", "2014-01-20", 30, 15);
        Assertions.assertEquals(50.0, t.getProcenatTacnihOdgovora());
    }

    @Test
    void getBrojPoena() {
        student.Test t = new student.Test(
                "Analiza 1", "2014-01-20", 30, 15);
        Assertions.assertEquals(15.0, t.getBrojPoena(30));
    }

    @Test
    void testToString() {
        student.Test t = new student.Test(
                "Analiza 1", "2014-01-20", 30, 15);
        String ocekivano = "Analiza 1;2014-01-20;15;30;50.00";
        String dobijeno = t.toString();
        Assertions.assertEquals(ocekivano, dobijeno);
    }
}