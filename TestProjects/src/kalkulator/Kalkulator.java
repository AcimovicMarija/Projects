package kalkulator;

public class Kalkulator {
    private double a, b;

    public Kalkulator(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double saberi(){
        return this.a + this.b;
    }

    public double oduzmi(){
        return this.a - this.b;
    }

    public double pomnozi(){
        return this.a * this.b;
    }
}
