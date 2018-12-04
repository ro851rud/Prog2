
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.*;

public class HaeufigkeitsanalyseEinesDeutschenTextes {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Map<String,Integer> haeufigkeit = ermittleHaufigekeiten("C:\\Users\\robin\\Desktop\\Studium AIN\\2. Semester\\Programmiertechnik II\\Aufgaben\\Aufgabe12\\src\\Faust.txt");
        printTop100(haeufigkeit);
    }

    static List<String> liste = new LinkedList<>();

    public static Map<String,Integer> ermittleHaufigekeiten(String fileName) throws FileNotFoundException, IOException {

        LineNumberReader in = new LineNumberReader(new FileReader(fileName));
        String line;

        Map<String,Integer> haeufigkeit = new TreeMap<>(); 	// enthaelt zu jedem Wort seine Haefigkeit

        while ((line = in.readLine()) != null) {
            String[] wf = line.split("[^a-z^A-Z^ß^ä^ö^ü^Ä^Ö^Ü]+");
            for (String w: wf) {
                if (w.length() == 0 || w.length() == 1)
                    continue;
                //System.out.println(w);
                // Ihr Code:
                // ...

                Integer get = haeufigkeit.get(w);
                if (get == null) {
                    haeufigkeit.put(w, 1);
                } else {
                    haeufigkeit.put(w, get + 1);
                }
            }
        }
        for (String schluessel : haeufigkeit.keySet()) {
            liste.add(schluessel);
        }
        Collections.sort(liste, String.CASE_INSENSITIVE_ORDER);
        System.out.println(liste);
        return haeufigkeit;
    }

    public static void printTop100(Map<String,Integer> h) {
        // Ihr Code:
        List fulllist = new ArrayList(h.values());
        Collections.sort(fulllist);
        Collections.reverse(fulllist);
        System.out.println(fulllist);
        Object o = fulllist.get(99);
        String s = o.toString();
        Integer i = Integer.parseInt(s);

        liste.stream().filter(x -> h.get(x) >= i)
                .forEach(x -> System.out.println(x + " " + h.get(x)));
        
    }

}
