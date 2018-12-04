import java.util.ArrayList;
import java.util.List;

public class Person {
    public Person(String name) {
        this.name = name;
    }

    private String name;
    public List<Buch> ausgelieheneBuecher = new ArrayList<Buch>();

    public String getName(){
        return this.name;
    }

    public boolean leihtAus(Buch b) {
        if (b.entleiher == null) {
            this.ausgelieheneBuecher.add(b);
            b.entleiher = this;
            return true;
        }
        return false;
    }

    public boolean gibtZurueck(Buch b){
        if (ausgelieheneBuecher.contains(b)) {
            ausgelieheneBuecher.remove(b);
            b.entleiher = null;
            return true;
        }
        return false;
    }

    public void print(){
        System.out.println("Name: " + this.name);
        for (Buch b : this.ausgelieheneBuecher) {
            System.out.println("\tBuch: " + b.getName());
        }
        System.out.println("\n");
    }
}
