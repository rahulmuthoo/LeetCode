package easy;

import java.util.HashMap;
import java.util.Map;

public class ClimbingStairs {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	// My accepted solution
	Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    public int climbStairs(int n) {
        if(n == 1) return 1;
        if(n == 2) return 2;
        if(map.containsKey(n))
        {
            return map.get(n);
        }
        else
        {
            int value = climbStairs(n-1) + climbStairs(n-2);
            map.put(n, value);
            return value;
        }
    }
    
    // A bit of tweaking
    public int climbStairs1(int n) {
        if(n <= 2) return n;
        
        else if(map.containsKey(n))
        {
            return map.get(n);
        }
        else
        {
            int value = climbStairs(n-1) + climbStairs(n-2);
            map.put(n, value);
            return value;
        }
    }

}
