public class Widerstand implements Schaltung{
    public Widerstand(double widerstand) {
        this.widerstand = widerstand;
        anzahl++;
    }

    private double widerstand = 0;
    private int anzahl;


    public double getWiderstand() {
        return widerstand;

    }

    public int getAnzahlWiderstaende() {

        return anzahl;

    }

    public void add(Schaltung s) {
    }

    public void remove(Schaltung s) {
    }
}
