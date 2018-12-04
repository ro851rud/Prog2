public class Main {
    public static void main(String[] args) {

        Schaltung ps1 = new Parallelschaltung();
        ps1.add(new Widerstand(400));
        ps1.add(new Widerstand(100));
        Schaltung ps2 = new Parallelschaltung();
        ps2.add(new Widerstand(200));
        ps2.add(new Widerstand(600));
        ps2.add(new Widerstand(300));
        Schaltung rs = new Reihenschaltung();
        rs.add(ps1);
        rs.add(ps2);
        rs.add(new Widerstand(120));
        System.out.println(rs.getWiderstand()); // 300
        System.out.println(rs.getAnzahlWiderstaende()); // 6

    }
}