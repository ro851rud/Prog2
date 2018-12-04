package expressionevaluation;

import java.util.Scanner;

/**
 * Klasse zum Auswerten von arithmetischen Ausdrücken.
 * O. Bittel; 22.03.2018
 */
public class Evaluator {

	private static final String ANSI_BLUE = "\u001B[34m";
	
	private static final Object[] stack = new Object[100]; // Stack
	private static int size = 0;   // Anzahl Elemente im Stack
	
	private static Object token;
	private static Tokenizer tokenizer;
	private static boolean ready;
	private static boolean finish;

	/**
	 * Wertet expr als arithmetischen Ausdruck aus.
	 *
	 * @param expr Arthmetischer Ausdruck als String
	 * @return Wert des Ausdrucks oder null, falls der Ausdruck fehlerhaft ist.
	 */
	public static Double eval(String expr) {
		size = 0;
		tokenizer = new Tokenizer(expr);
		token = tokenizer.nextToken();
		ready = true;
		stack[0] = "$";
		finish = false;
		
		while (!finish && ready) {
			shift();
		}
		if (ready) {
		    return Double.parseDouble(stack[size].toString());
		}
		
		return null;
	}

	private static boolean shift() {
		// Prüfe, ob shift gemacht werden muss und führe shift durch.
		// Liefere true zurück, falls shift gemacht wurde, sonst false.
		if((isOp(stack[size]) || isKlAuf(stack[size]) || isDollar(stack[size])) && (isKlAuf(token) || isVal(token))) {
		    doShift();
		    return true;
		} else if(size >= 2 && (isKlZu(stack[size]) && isVal(stack[size-1]) && isKlAuf(stack[size-2]) &&
                (isKlZu(token) || isOp(token) || isDollar(token)))) {
	            doReduceKlAufValKlZu();
		        return false;
		} else if (size >= 1 && isDollar(stack[size-1]) && isVal(stack[size])) {
		    if (isDollar(token)) {
		        accept();
		        return false;
		    } else if (isOp(token)) {
		        doShift();
		        return false;
		    } else {
		        ready = false;
		        accept();
		        return false;
		    }
		} else if (size >= 1 && isVal(stack[size]) && isKlAuf(stack[size-1]) && 
		        (isKlZu(token) || isOp(token))) {
		        doShift();
		        return true;
		} else if (size >= 2 && isVal(stack[size]) && isOp(stack[size-1]) && isVal(stack[size-2])) {
		    if (isKlZu(token) || isDollar(token)) {
		        doReduceValOpVal();
		        return false;
		    } else if (isOp(token)) {
		        if ((isMinus(stack[size-1]) || isPlus(stack[size-1])) && (isMult(token) || isDiv(token))) {
		            doShift();
		            return true;
		        } else {
		            doReduceValOpVal();
		            return false;
		        }
		    }
		    ready = false;
		    accept();
		    return false;
		} else {
		    ready = false;
		    accept();
		    return false;
		}
	}

	private static void doShift() {
		//System.out.println("shift");
		size += 1;
		stack[size] = token;
		token = tokenizer.nextToken();
	}
	
	private static void doReduceKlAufValKlZu() {
		//System.out.println("reduce ( val )");
		stack[size-2] = stack[size-1];
		stack[size-1] = stack[size] = null;
		size -= 2;
	}

	private static void doReduceValOpVal() {
		//System.out.println("reduce val op val");
		if (isMult(stack[size-1])) {
		    stack[size-2] = Double.parseDouble(stack[size-2].toString()) *
		                    Double.parseDouble(stack[size].toString());
		} else if (isDiv(stack[size-1])) {
		    stack[size-2] = Double.parseDouble(stack[size-2].toString()) /
                            Double.parseDouble(stack[size].toString());
		} else if (isPlus(stack[size-1])) {
		    stack[size-2] = Double.parseDouble(stack[size-2].toString()) +
		                    Double.parseDouble(stack[size].toString());
		} else if (isMinus(stack[size-1])) {
		    stack[size-2] = Double.parseDouble(stack[size-2].toString()) -
                            Double.parseDouble(stack[size].toString());
		}
		stack[size-1] = stack[size] = null;
		size -= 2;
	}
	
	private static boolean accept() {
		// Prüfe ob accept vorliegt und liefere true zurück, sonst false.
		if(stack[size] instanceof Double && ready) {
		    finish = true;
		    return true;
		}
		return false;
	}

	private static boolean isKlAuf(Object o) {
		return o instanceof String && ((String) o).equals("(");
	}

	private static boolean isKlZu(Object o) {
		return o instanceof String && ((String) o).equals(")");
	}

	private static boolean isPlus(Object o) {
		return o instanceof String && ((String) o).equals("+");
	}

	private static boolean isMinus(Object o) {
		return o instanceof String && ((String) o).equals("-");
	}

	private static boolean isMult(Object o) {
		return o instanceof String && ((String) o).equals("*");
	}

	private static boolean isDiv(Object o) {
		return o instanceof String && ((String) o).equals("/");
	}
	
	private static boolean isDollar(Object o) {
		return o instanceof String && ((String) o).equals("$");
	}

	private static boolean isOp(Object o) {
		if (!(o instanceof String))
			return false;
		String s = (String) o;
		return (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/"));
	}

	private static boolean isVal(Object o) {
		return o instanceof Double;
	}


	/**
	 * Liest von der Konsole eine Folge von Zeilen, wertet jede Zeile als
	 * Ausdruck aus und gibt seinen Wert aus. (repl = read-evaluate-print-loop).
	 */
	public static void repl() {
		Scanner in = new Scanner(System.in);
		System.out.print(ANSI_BLUE + ">> ");
				
		while (in.hasNextLine()) {
			String line = in.nextLine();
			if (line.equals("end")) {
			    System.out.println("bye");
			    return;
			} else if (line != null ) {
			    System.out.println(eval(line));
			}
		}
		in.close();
	}

	/**
	 * Testprogramm.
	 *
	 * @param args wird nicht benutzt.
	 */
	public static void main(String[] args) {
		// Tests:
		String s1 = "(2+3*4-4)/2";
		String s2 = "(2+3*4-4))/2";
		String s3 = "(2+3*4-4)//2";
		String s4 = "1/2*2";
		String s5 = "1+2";

		System.out.println(eval(s1));	// 5.0
		System.out.println(eval(s2));	// null; Syntaxfehler
		System.out.println(eval(s3));	// null; Syntaxfehler
		System.out.println(eval(s4));	// 1.0
		System.out.println(eval(s5));	// 3.0
		
		// read-evaluate-print-loop:
		repl();
	}
}
