public class Parallelschaltung extends ZusammengesetzteSchaltung{

    double gesamtwiderstand = 0.0;
    public int anzahl = 0;

    public double getWiderstand() {
        for (Schaltung s : schaltung) {
            gesamtwiderstand += (1 / s.getWiderstand());
            anzahl++;
        }
        return 1/gesamtwiderstand;
    }

}
