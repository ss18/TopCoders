/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TopCoder;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author ss18
 */
public class BridgeCrossingOptimized extends BridgeCrossing {
    
    @Override
    protected void goingFromRightToLeft(ArrayList<Integer> left, ArrayList<Integer> right, Integer time, ArrayList<Integer> allResults) {
        if (left.isEmpty()) {
            allResults.add(time);
        } else {
            Integer min = Collections.min(right);
            ArrayList<Integer> newLeft = new ArrayList<>(left);
            ArrayList<Integer> newRight = new ArrayList<>(right);
            newLeft.add(min);
            newRight.remove(min);
            this.goingFromLeftToRight(newLeft, newRight, time + min, allResults);                
        }
    }     
}
