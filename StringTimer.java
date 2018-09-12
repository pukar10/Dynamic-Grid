// TO DO: add your implementation and JavaDoc

public class StringTimer implements Combiner<String, Integer, String>{
	
	/**
	 * return a string as a repetition of the original string operand1
	 * the number of repeats is specified by integer operand2
	 * return empty string if perand2 is < 0
	 * O(NL) where N is value of operand2 and L is length of operand1
	 * 
	 * @param  operand1 string you want to copy
	 * @param  operand2 number of times you want your string to be copied
	 * @return String   return operand1, operand2 number of times.         
	 */
	public String combine(String operand1, Integer operand2)
	{
		if(operand2 <= 0)
		{
			return "";
		}else{
			String result = "";
			for(int i = 0; i < operand2; i++)
			{
				result += operand1;
			}
			return result;
		}

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