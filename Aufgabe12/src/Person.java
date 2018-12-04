
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.function.Predicate;

public class Person {

    private String vorname;
    private String nachname;
    private LocalDate geburtstag;
    static Predicate<Person> p;
    static LocalDate achzehnjahre;
    static ArrayList<Person> persList = new ArrayList<>();
    static Comparator<Person> cmp;


    public static void main(String args[]){
        Person Moritz = new Person("Moritz", "Müller", LocalDate.of(2000, 04, 24));
        Person Martin = new Person("Martin", "Heis", LocalDate.of(2005, 03, 02));
        Person Pia = new Person("Pia", "Mahl", LocalDate.of(1995, 05, 19));
        Person Annika = new Person("Annika", "Ruck", LocalDate.of(1996, 06, 21));
        Person Dennis = new Person("Dennis", "Gruber", LocalDate.of(2001, 12, 31));
        Person Karla = new Person("Karla", "Morgen", LocalDate.of(2012, 10, 11));

        persList.add(Moritz);
        persList.add(Martin);
        persList.add(Pia);
        persList.add(Annika);
        persList.add(Dennis);
        persList.add(Karla);

        //Pruefung ob alle Volljaehrig
        istAchzehn(persList);

        //Sortierte Liste von jung nach alt
        sortjo(persList);

        //Sortiere Liste von alt nach jung
        sortoj(persList);

        //Strom erzeugen und filtern minderjaehrige Geburtrstage
        persList.stream()
                .filter(p.negate())
                .map(Person::getGeburtstag)
                .sorted()
                .forEach(x -> System.out.print("\n" + x));

        //Strom erzeugen und filtern der Nachmanen in Grossbuchstaben
        System.out.println();
        persList.stream()
                .map(x -> getNachname(x).toUpperCase())
                .sorted()
                .forEach(q -> System.out.print("\n" + q));
    }

    private Person(String vorname, String nachname, LocalDate geburtstag) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtstag = geburtstag;
    }


    public static void sortjo(ArrayList<Person> persList){
        System.out.println("\nListe von jung nach alt:");
        cmp = (a,b) -> getGeburtstag(a).compareTo(getGeburtstag(b));
        Collections.sort(persList, cmp);
        for (Person x : persList) {
            System.out.println(toString(x));
        }
    }

    public static void sortoj(ArrayList<Person> persList) {
        System.out.println("\nListe von alt nach jung:");
        cmp = Comparator.comparing(Person::getGeburtstag);
        Collections.sort(persList, cmp.reversed());
        for (Person x : persList) {
            System.out.println(toString(x));
        }
    }

    public static void istAchzehn(ArrayList<Person> persList){
        achzehnjahre = LocalDate.now().minusYears(18);
        p = x -> getGeburtstag(x).isBefore(achzehnjahre);

        if(forAll(persList, p)){
            System.out.println("Alle Personen sind Volljaehrig");
        } else {
            System.out.println("Nicht alle Personen sind Volljaehrig");
        }

    }
    static boolean forAll(ArrayList<Person> a, Predicate p) {
        for (Person x : a) {
            if(!p.test(x))
                return false;
        }
        return true;
    }

    public static String getVorname(Person x) {
        return x.vorname;
    }

    public static String getNachname(Person x) {
        return x.nachname;
    }

    public static  LocalDate getGeburtstag(Person x) {
        return x.geburtstag;
    }

    public void setVorname(String v) {
        this.vorname = v;
    }

    public void setNachname(String n) {
        this.nachname = n;
    }

    public void setGeburtstag(LocalDate g) {
        this.geburtstag = g;
    }

    public static String toString(Person p) {
        StringBuilder sb = new StringBuilder();
        sb.append(p.vorname).append(" ").append(p.nachname).append(" ").append(p.geburtstag);
        String s = sb.toString();
        return s;
    }

}
