// TO DO: add your implementation and JavaDoc

public class StringTimer implements Combiner<String, Integer, String>{
	
	public String combine(String operand1, Integer operand2){
		// return a string as a repetition of the original string operand1
		// the number of repeats is specified by integer operand2
		// e.g. combine("hat",3) returns "hathathat"
		// return empty string if operand2 is 0 or negative
		
		// O(NL) where N is the value of operand2 and L is the length of operand1
		// Hint: remember the big-O of copying a string...
	}
	
	// --------------------------------------------------------
	// example testing code... edit this as much as you want!
	// --------------------------------------------------------

	public static void main(String[] args){
		StringTimer st = new StringTimer();
		if (st.combine("a",1).equals("a") && st.combine("ab",3).equals("ababab")
			&& st.combine("abc",-1).equals("")) {
			System.out.println("Yay 1");
		}

	}
}