package student;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StudentTest {

    @Test
    void getIme() {
        Student s = new Student("ime studenta", "prezime studenta", "66121map");
        Assertions.assertEquals("ime studenta", s.getIme());
    }

    @Test
    void getPrezime() {
        Student s = new Student("ime studenta", "prezime studenta", "66121map");
        Assertions.assertEquals("prezime studenta", s.getPrezime());
    }

    @Test
    void getBrojIndeksa() {
        Student s = new Student("ime studenta", "prezime studenta", "66121map");
        Assertions.assertEquals("66121map", s.getBrojIndeksa());
    }

    @Test
    void getBrojPoenaZaPredmet() {
        Student s = new Student("ime studenta", "prezime studenta", "66121map");

        student.Test t = new student.Test(
                "Analiza 1", "2014-01-20", 30, 15);

        s.evidentirajRadjeniTest(t);

        double ocekivaniBrojPoena = 15;
        double dobijeniBrojPoena = s.getBrojPoenaZaPredmet("Analiza 1");

        Assertions.assertEquals(ocekivaniBrojPoena, dobijeniBrojPoena, 0.01);
    }

    @Test
    void getBrojPoenaZaPredmetZaUnetihViseTestova() {
        Student s = new Student("ime studenta", "prezime studenta", "66121map");

        student.Test t1 = new student.Test(
                "Analiza 1", "2013-12-25", 30, 20);
        student.Test t2 = new student.Test(
                "Analiza 1", "2014-01-20", 30, 15);
        student.Test t3 = new student.Test(
                "Analiza 1", "2014-02-04", 30, 25);

        s.evidentirajRadjeniTest(t1);
        s.evidentirajRadjeniTest(t2);
        s.evidentirajRadjeniTest(t3);

        double ocekivaniBrojPoena = 25;
        double dobijeniBrojPoena = s.getBrojPoenaZaPredmet("Analiza 1");

        Assertions.assertEquals(ocekivaniBrojPoena, dobijeniBrojPoena, 0.01);
    }

    @Test
    void getBrojPoenaZaNepostojeciPredmet() {
        Student s = new Student("ime studenta", "prezime studenta", "66121map");

        student.Test t1 = new student.Test(
                "Analiza 1", "2014-12-25", 30, 20);
        student.Test t2 = new student.Test(
                "Analiza 1", "2014-01-20", 30, 15);
        student.Test t3 = new student.Test(
                "Analiza 1", "2014-02-04", 30, 25);

        s.evidentirajRadjeniTest(t1);
        s.evidentirajRadjeniTest(t2);
        s.evidentirajRadjeniTest(t3);

        double ocekivaniBrojPoena = 0;
        double dobijeniBrojPoena = s.getBrojPoenaZaPredmet("Ne postoji predmet sa ovim imenom");

        Assertions.assertEquals(ocekivaniBrojPoena, dobijeniBrojPoena, 0.01);
    }
}