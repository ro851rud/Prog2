public class Reihenschaltung extends ZusammengesetzteSchaltung{

    public double geswiderstand = 0.0;
    public int anzahl = 0;

    public double getWiderstand() {
        for (Schaltung s : schaltung) {
            geswiderstand += s.getWiderstand();
            anzahl++;
        }
        return geswiderstand;
    }

}
