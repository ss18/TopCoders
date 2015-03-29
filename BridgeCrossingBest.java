/**
 * SRM 146 DIV 2, 1000 points Problem
 * Problem Statement
 * A well-known riddle goes like this: Four people are crossing an old bridge. The bridge cannot hold more than two people at once. It is dark, so they can't walk without a flashlight, and they only have one flashlight! Furthermore, the time needed to cross the bridge varies among the people in the group. For instance, let's say that the people take 1, 2, 5 and 10 minutes to cross the bridge. When people walk together, they always walk at the speed of the slowest person. It is impossible to toss the flashlight across the bridge, so one person always has to go back with the flashlight to the others. What is the minimum amount of time needed to get all the people across the bridge?
 * In this instance, the answer is 17. Person number 1 and 2 cross the bridge together, spending 2 minutes. Then person 1 goes back with the flashlight, spending an additional one minute. Then person 3 and 4 cross the bridge together, spending 10 minutes. Person 2 goes back with the flashlight (2 min), and person 1 and 2 cross the bridge together (2 min). This yields a total of 2+1+10+2+2 = 17 minutes spent.
 * You want to create a computer program to help you solve new instances of this problem. Given an int[] times, where the elements represent the time each person spends on a crossing, your program should return the minimum possible amount of time spent crossing the bridge.

 * Definition
 * Class: BridgeCrossing
 * Method: minTime
 * Parameters: int[]
 * Returns: int
 * Method signature: int minTime(int[] times)
 * (be sure your method is public)

 * Limits
 * Time limit (s): 2.000
 * Memory limit (MB): 64

 * Notes
 * In an optimal solution, exactly two people will be sent across the bridge with the flashlight each time (if possible), and exactly one person will be sent back with the flashlight each time. In other words, in an optimal solution, you will never send more than one person back from the far side at a time, and you will never send less than two people across to the far side each time (when possible).

 * Constraints
 * times will have between 1 and 6 elements, inclusive.
 * Each element of times will be between 1 and 100, inclusive.

 * Examples
 * Input 1: { 1, 2, 5, 10 }
 * Correct answer: 17
 * The example from the text.

 * Input 2: { 1, 2, 3, 4, 5 }
 * Correct answer: 16
 * One solution is: 1 and 2 cross together (2min), 1 goes back (1min), 4 and 5 cross together (5min), 2 goes back (2min), 1 and 3 cross together (3min), 1 goes back (1min), 1 and 2 cross together (2min). This yields a total of 2 + 1 + 5 + 2 + 3 + 1 + 2 = 16 minutes spent.

 * Input 3: { 100 }
 * Correct answer: 100
 * Only one person crosses the bridge once.

 * Input 4: { 1, 2, 3, 50, 99, 100 }
 * Correct answer: 162

 * This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
 * */

package TopCoder;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * @author ss18
 * 
 * The idea behind that from right to left goes always fastest one. And from left to right could be 3 options:
 * 1) goes two slowest
 * 2) goes two fastest
 * 3) goes fastest and slowest
 * Conditions could be defined basted on this beautiful "(_n_1 + _0) > 2*_1", arithmetic mean
 * read more in method comments.
 * Time complexity O(n), which is much better than O(n^(n^3)) or O(n^(n^2))
 */
public class BridgeCrossingBest {
    
    /**
     * 
     * @param times - array where the elements represent the time each person spends on a crossing the bridge
     * @return best possible time
     */    
    public int minTime(int[] times) {
        
        if (times.length == 1) {
            return times[0];
        }
        
        ArrayList<Integer> array = new ArrayList<>();
        for (int i = 0; i < times.length; i++) {
            array.add(times[i]);
        }
        Collections.sort(array);
        
        return this.minTime(array);
    }
    
    /**
     * Auxiliary methods, which assumes that times are sorted, and size > 1
     * @param times - array where the elements represent the time each person spends on a crossing the bridge
     * @return best possible time
     */    
    protected Integer minTime(ArrayList<Integer> times) {    
        
        // best time storage
        Integer result = 0;
        
        // Two best(faster) people _0 & _1
        Integer _0 = times.get(0);
        Integer _1 = times.get(1);  
        
        while(times.size() > 3) {
            
            // Two worst people (slowest) (n) and (n-1) elements in array
            Integer _n = times.get(times.size() - 1);
            Integer _n_1 = times.get(times.size() - 2);
            
            // Idea of each iteration move two slowest cross the bridge
            times.remove(_n);
            times.remove(_n_1);
            
            // if arithmetic mean of (n-1) and 0 elements in array more than 1 element
            // than idea 1: two fast people goes first, than 1 comes back and slowest 
            // people goes together, and 1 comes back
            if ((_n_1 + _0) > 2*_1) {
                result += 2*_1 + _0 + _n;
                
            // else, it's gonna be true for the rest of the array, so stop while
            } else {
                result += _n + _0 + _n_1 + _0;
                break;
            }
        }
        
        // Fastest goes with slowest
        for(Integer s : times) {
            result += s;
        }
        
        // Fastest comes back, could be -fastest in case of size 2 :)
        result += times.get(0)*(times.size() - 3);
        
        return result;
    }
}
