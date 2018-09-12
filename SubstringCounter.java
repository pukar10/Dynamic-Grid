//Pukar Subedi
//CS310

public class SubstringCounter implements Combiner<String, String, Integer>{
	
	/**
	 * Count how many times operand2 occurs in operand1 as a substring
	 * O(NM) where N is the length of operand1 and M is the length of Operand2
	 * 
	 * @param  operand1 string you are looking through
	 * @param  operand2 String you are trying to find in operand1
	 * @return Integer  Number of occurences of operand2 in operand1
	 */
	public Integer combine(String operand1, String operand2)
	{
		int lastIndex = 0;
		int count = 0;
		while(lastIndex != -1)
		{
			lastIndex = operand1.indexOf(operand2, lastIndex);
			if(lastIndex != -1)
			{
				count++;
				lastIndex += operand2.length()-1;
			}
		}
		return count;
	}
	
	// --------------------------------------------------------
	// example testing code... edit this as much as you want!
	// --------------------------------------------------------

	public static void main(String[] args){
		SubstringCounter sc = new SubstringCounter();
		if (sc.combine("abab","ab") == 2 && sc.combine("aa","aab") == 0
			&& sc.combine("23232","232") == 2 
			&& sc.combine("helloabchelloddefzdfjhello","hello")==3) {
			System.out.println("Yay 1");
		}

		System.out.println(sc.combine("helloabchelloddefzdfjhello","hello"));
		System.out.println(sc.combine("hihi", "hi"));
		System.out.println(sc.combine("aa","aab") == 0);
		System.out.println(sc.combine("23232","232"));
	}
}