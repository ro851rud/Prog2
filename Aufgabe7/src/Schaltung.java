public interface Schaltung {

    public double getWiderstand();

    public int getAnzahlWiderstaende();

    public void add(Schaltung s);

    public void remove(Schaltung s);
}
