import java.util.ArrayList;
import java.util.List;

public abstract class ZusammengesetzteSchaltung implements Schaltung{

    public List<Schaltung> schaltung = new ArrayList<>();

    private double widerstand = 0;


    public int getAnzahlWiderstaende(){
        int wzaehler = 0;
        for (Schaltung s : schaltung) {
            wzaehler += s.getAnzahlWiderstaende();
        }
        return wzaehler;
    }

    public double getWiderstand() {
        return widerstand;
    }

    public void add(Schaltung s){
        schaltung.add(s);
    }

    public void remove(Schaltung s) {
        schaltung.remove(s);
    }
}
