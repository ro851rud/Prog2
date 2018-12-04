/*

 * class Tokenizer
 * Zerlegt einen String in eine Folge von Tokens.
 * Tokens: +, -, *, /, (, ), $ oder Gleitkommazahl (ohne Vorzeichen und Exponent).
 *
 * O. Bittel; 22.03.2018
 * Geändert am 5.4.2018; Gleitkommazahlen können nun mit Dezimalpunkt 
 * (z.B. 3.24 statt 3,24) eingegeben werden. 
 *
 */
package expressionevaluation;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author oliverbittel
 */
public class Tokenizer {

    public final static String[] KEYWORDS = {"(", "+", "*", "-", "/", ")", "$"};
    private final Scanner in;

    /**
     *
     * @param s String, der in eine Folge von Tokens zerlegt werden soll.
     */
    public Tokenizer(String s) {
        s = s.replaceAll("\\(", " ( ");
        s = s.replaceAll("\\)", " ) ");
        s = s.replaceAll("\\+", " + ");
        s = s.replaceAll("\\*", " * ");
        s = s.replaceAll("\\/", " / ");
        s = s.replaceAll("\\-", " - ");
        s = s.replaceAll(",", ".");
        s += " $";
        //System.out.println(s);
        
        in = new Scanner(s);
        
        // Use "." as decimal separator:
        //System.out.println(Locale.getDefault());
        in.useLocale(Locale.US);

        NumberFormat f = NumberFormat.getInstance(in.locale());
        if (f instanceof DecimalFormat) {
            //System.out.println("Used decimal separator: " + ((DecimalFormat) f).getDecimalFormatSymbols().getDecimalSeparator());
        }
        
        
    }

    /**
     * Liefert den nächsten Token.
     *
     * @return String aus KEYWORDS oder double-Wert, falls das nächste Token
     * eine Gleitkommazahl ist; null bei Fehler. Bei String-Ende wird "$"
     * zurückgeliefert.
     */
    public Object nextToken() {
        if (!in.hasNext()) {
            return null; // Eingabeende
        }
        if (in.hasNextDouble()) {
            return in.nextDouble();
        }
        String token = in.next();
        for (String t : KEYWORDS) {
            if (token.equals(t)) {
                return token;
            }
        }
        System.out.println("Error: Kein Arethmethischer Ausdruck");
        return null;
    }

	// Zu Testzwecken:
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        String s = "(2.1+3.2*4.5-4)/2";
        Tokenizer tokenizer = new Tokenizer(s);
        Object token;
        while ((token = tokenizer.nextToken()) != null) {
            System.out.println(token);
        }
    }
}
