// O. Bittel;
// 9.3.2017

package aufgabe4;

public class GenSetApplication {

    public static void main(String[] args) {
    	
		// Testen von add und contains:
		System.out.println("***** Testen von add und contains *****");
    	Set<Number> numArrSet = new ArraySet<>();
    	Set<Integer> intArrSet = new ArraySet<>();
    	Set<String> strArrSet = new ArraySet<>();
		
		Set<Number> numLLSet = new LinkedListSet<>();
    	Set<Integer> intLLSet = new LinkedListSet<>();
    	Set<String> strLLSet = new LinkedListSet<>();
    	
    	numArrSet.add(4.0);
    	numArrSet.add(50);
    	numArrSet.add(12.995);
		numLLSet.add(4.0);
    	numLLSet.add(50);
    	numLLSet.add(12.995);
    	
    	intArrSet.add(2);
    	intArrSet.add(40080);
    	intArrSet.add(69874);
    	intArrSet.add(2);
		intLLSet.add(2);
    	intLLSet.add(40080);
    	intLLSet.add(69874);
    	intLLSet.add(2);
    	
    	strArrSet.add("Hallo");
    	strArrSet.add("Welt");
    	strArrSet.add("123");
		strLLSet.add("Hallo");
    	strLLSet.add("Welt");
    	strLLSet.add("123");
	
    	System.out.println(numArrSet.toString());//4.0, 50, 12.995
		System.out.println(numLLSet.toString());//4.0, 50, 12.995
    	System.out.println(intArrSet.toString());//2, 40080, 69874
		System.out.println(intLLSet.toString());//2, 40080, 69874
    	System.out.println(strArrSet.toString());//Hallo, Welt, 123
		System.out.println(strLLSet.toString());//Hallo, Welt, 123
    	
    	System.out.println(numArrSet.contains(4.0));//true
		System.out.println(numLLSet.contains(4.0));//true
		System.out.println(intArrSet.contains(2));//true
		System.out.println(intLLSet.contains(2));//true
		System.out.println(intArrSet.contains(40080));//true
		System.out.println(intLLSet.contains(40080));//true
		System.out.println(strArrSet.contains("123"));//true
		System.out.println(strLLSet.contains("123"));//true
		
		
		// Testen von addAll:
		System.out.println("***** Testen von addAll *****");
		Set<Integer> intArrSet2 = new ArraySet<>();
		intArrSet2.add(2);
		intArrSet2.add(3);
		intArrSet2.add(50);
		intArrSet2.addAll(intLLSet);
		System.out.println(intArrSet2.toString());//2, 3, 50, 40080, 69874
		
		numLLSet.addAll(intArrSet2);
		System.out.println(numLLSet.toString());//4.0, 50, 12.995, 2, 3, 40080, 69874
		
		Set<Object> objArrSet = new ArraySet<>();
		objArrSet.addAll(numLLSet);
		objArrSet.addAll(intLLSet);
		objArrSet.addAll(strLLSet);
		System.out.println(objArrSet.toString());//4.0, 50, 12.995, 2, 3, 40080, 69874, Hallo, Welt, 123
		
		// Testen von containsAll:
		System.out.println("***** Testen von containsAll *****");
		System.out.println(objArrSet.containsAll(numArrSet));//true
		//System.out.println(numArrSet.containsAll(objArrSet));//false
		
		// Test von removeAll:
		System.out.println("***** Testen von removeAll *****");
		System.out.println(intArrSet2.toString());//2, 3, 50, 40080, 69874
		System.out.println(numArrSet.toString());//4.0, 50, 12.995
		intArrSet2.removeAll(numArrSet);
		System.out.println(intArrSet2.toString());//2, 3, 40080, 69874
    }

}