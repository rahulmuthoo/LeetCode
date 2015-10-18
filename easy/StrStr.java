package easy;


// https://www.youtube.com/watch?v=GTJr8OvyEVQ


public class StrStr {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String haystack = "mississippi";
		String needle = "issip";
		// System.out.println(strStr(haystack, needle));
		System.out.println(strStr(haystack, needle));
		// String sample = "acacabacacabacacac";

		// int[] array = failureFunction(sample.toCharArray());
		// int[] array = computeTemporaryArray(sample.toCharArray());
		// System.out.println(Arrays.toString(array));

	}

	// Order of mn
	public static int strStr1(String haystack, String needle) 
	{
		for (int i = 0; ; i++) 
		{
			for (int j = 0; ; j++) 
			{
				if (j == needle.length()) 
					return i;

				if (i + j == haystack.length()) 
					return -1;

				if (needle.charAt(j) != haystack.charAt(i + j)) 
					break;
			}
		}
	}

	// KMP Algorithm - Order of m + n
	/**
	 * KMP algorithm of pattern matching.
	 */
	public static int strStr(String haystack, String needle)
	{
		if(needle.length() == 0) return 0;
		if(haystack.length() == 0) return -1;

		// O(n)
		int lps[] = computeTemporaryArray(needle);
		
		int i=0;
		int j=0;

		while(i < haystack.length() && j < needle.length())
		{
			if(haystack.charAt(i) == needle.charAt(j))
			{
				i++;
				j++;

				if (j == needle.length()) 
					return i-j;
			}

			// If a mismatch occurs
			else
			{
				if(j != 0) // Move back and take the new index from there
				{
					j = lps[j-1];
				}
				// j is still at zero and there is a mismatch. Move i one step ahead.
				else
				{
					i++;
				}
			}
		}

		// Use this function if you have to return the presense of a string
		/*if(j == needle.length())
	        {
	            return true;
	        }*/

		return -1;
	}

	/**
	 * Compute temporary array to maintain size of suffix which is same as prefix
	 * Time/space complexity is O(size of pattern). 
	 * This array tells me that what is the longest suffix at every point which is also the prefix.
	 */
	// https://www.youtube.com/watch?v=KG44VoDtsAA
	private static int[] computeTemporaryArray(String pattern)
	{
		int [] lps = new int[pattern.length()];
		
		// First point is always 0
		int j =0;

		// Note that there is no i++ here in this for
		for(int i=1; i < pattern.length();)
		{
			// Match is found
			if(pattern.charAt(i) == pattern.charAt(j))
			{
				lps[i] = j + 1;
				j++;
				i++;
			}

			// Match is not found
			else
			{
				// If j is not at zero, move back and do not increase i
				if(j != 0)
				{
					j = lps[j-1];
				}
				// If j is at zero, there is no other option but to proceed further and mark that cell as zero
				else
				{
					lps[i] =0;
					i++;
				}
			}
		}
		return lps;
	}


	public static int strStr2(String haystack, String needle) 
	{
		if (needle.length() == 0) return 0;
		if (needle.length() <= haystack.length()) 
		{
			int[] f = failureFunction(needle.toCharArray());
			int i = 0, j = 0;

			while (i < haystack.length()) 
			{
				if (haystack.charAt(i) == needle.charAt(j)) 
				{
					i++; j++;
					if (j == needle.length()) return i-j;
				} 

				else if (j > 0) j = f[j];
				else i++;
			}
		}

		return -1;
	}

	private static int[] failureFunction(char[] str) 
	{

		int[] f = new int[str.length + 1];
		for (int i = 2; i < f.length; i++) 
		{
			int j = f[i-1];

			while (j > 0 && str[j] != str[i-1]) 
				j = f[j];

			if (j > 0 || str[j] == str[i-1]) 
				f[i] = j+1;
		}

		return f;
	}


}
