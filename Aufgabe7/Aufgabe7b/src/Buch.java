public class Buch {
    public Buch(String name) {
        this.name = name;
    }

    private String name;
    public Person entleiher;

    public String getName() {
        return name;
    }

    public Person getEntleiher() {
        return entleiher;

    }

    public boolean wirdAusgeliehen(Person p) {
        if (this.entleiher == null) {
            this.entleiher = p;
            p.ausgelieheneBuecher.add(this);
            return true;
        }
        return false;
    }

    public boolean wirdZurueckGegeben(){
        if (this.entleiher.ausgelieheneBuecher.contains(this)){
            this.entleiher.ausgelieheneBuecher.remove(this);
            this.entleiher = null;
            return true;
        }
        return false;
    }

    public void print(){
        if(this.entleiher == null) {
            System.out.println("Buch: " + this.getName()
                                + " \"ist verfügbar\"");
        } else {
            System.out.println("Buch: " + this.getName()
                                + "\n\tEntleiher: "
                                + this.entleiher.getName());
        }
    }
}
