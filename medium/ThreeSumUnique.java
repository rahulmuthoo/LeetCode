package medium;

import java.util.*;


public class ThreeSumUnique {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// int[] nums = {13,14,1,2,-11,-11,-1,5,-1,-11,-9,-12,5,-3,-7,-4,-12,-9,8,-13,-8,2,-6,8,11,7,7,-6,8,-9,0,6,13,-14,-15,9,12,-9,-9,-4,-4,-3,-9,-14,9,-8,-11,13,-10,13,-15,-11,0,-14,5,-4,0,-3,-3,-7,-4,12,14,-14,5,7,10,-5,13,-14,-2,-6,-9,5,-12,7,4,-8,5,1,-10,-3,5,6,-9,-5,9,6,0,14,-15,11,11,6,4,-6,-10,-1,4,-11,-8,-13,-10,-2,-1,-7,-9,10,-7,3,-4,-2,8,-13};
		int[] nums = {0,0,0,0};
		threeSum(nums);
		System.out.println("Done!!");

	}

	/* a + b + c = 0 */
	public static List<List<Integer>> threeSum(int[] num) 
	{
		Arrays.sort(num);
		List<List<Integer>> res = new LinkedList<>(); 

		for (int i = 0; i < num.length-1; i++) 
		{
			if (i == 0 || 
					(i > 0 && num[i] != num[i-1])) // If the current number is same as the previous, we don't want to count it twice.
			{
				int lo = i+1;
				int hi = num.length-1;
				

				while (lo < hi) 
				{
				    int sum = num[i] + num[lo] + num[hi];
					if (sum == 0) 
					{
					    // Add this tuple to list and move forward to check other candidates
						res.add(Arrays.asList(num[i], num[lo], num[hi]));
                        
						// To skip duplicates before moving on the the next triplet 
						// Increase low as long as it is below high and the same number repeats
						while (lo < hi && num[lo] == num[lo+1]) 
							lo++;

						// Decrease high as long as it is above low and the same number repeats
						while (lo < hi && num[hi] == num[hi-1]) 
							hi--;

						// All duplicates skipped till here, if any. Now, move on to the next
						lo++; 
						hi--;
					} 

					else if (sum < 0) 
						lo++;

					else 
						hi--;
				}
			}
		}
		return res;
	}

}
