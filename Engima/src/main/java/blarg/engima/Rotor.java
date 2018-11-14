package blarg.engima;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author cymrucoder
 */
public class Rotor {
    private final String cipher;
    private int position;
    private List<Integer> rightToLeftShifts;
    private List<Integer> leftToRightShifts;
    private int ringSetting = 0;
    private int turnoverPoint;
    
    public Rotor(String cipher) {
        this.cipher = cipher.toUpperCase();
        rightToLeftShifts = new ArrayList<>();
        leftToRightShifts = new ArrayList<>(Collections.nCopies(cipher.length(), 0));
                
        // So BDFHJLC...
        // First char is B, plaintext A, B-A -> 1
        // At C, plaintext is G, C-G -> -4
        
        for (int i = 0; i < cipher.length(); i++) {
            int shift = cipher.charAt(i) - ('A' + i);
            rightToLeftShifts.add(shift);
            leftToRightShifts.set(i + shift, shift * -1);
        }
        
        position = 0;
        turnoverPoint = cipher.length() - 1;// If not specified, turnover will be at end of loop
    }
    
    public int getLeftForRight(int rightIndex) {
        int leftIndex = rollPosition(rightIndex + rightToLeftShifts.get(rollPosition(rightIndex + position - ringSetting)));
//        System.out.println("RIndex " + rightIndex + " with position " + position + " and ring setting " + ringSetting);
//        int sRightIndex = rightIndex + position - ringSetting;
//        System.out.println("sRightIndex is " + sRightIndex);
//        int rolledSRightIndex = rollPosition(sRightIndex);
//        System.out.println("rolled is " + rolledSRightIndex);
//        int shift = rightToLeftShifts.get(rolledSRightIndex);
//        System.out.println("Shift is " + shift);
//        int total = rolledSRightIndex + shift;
//        System.out.println("Total " + total);
//        int rollTotal = rollPosition(total);
//        System.out.println("roleld total is " + rollTotal + "\n\n\n");
//        //int leftIndex = rollPosition(rightIndex + position - ringSetting + rightToLeftShifts.get(rollPosition(rightIndex + position - ringSetting)));
//        int leftIndex = rollTotal;
// This is missing the rightindex+position+ringsetting at the start in the uncommented one which I think is correct but breaks tests
// Okay maybe that is wrong actually
//       System.out.println("RIndex " + rightIndex + " with position " + position + " and ring setting " + ringSetting);
//        int sRightIndex = rightIndex + position - ringSetting;
//        System.out.println("sRightIndex is " + sRightIndex);
//        int rolledSRightIndex = rollPosition(sRightIndex);
//        System.out.println("rolled is " + rolledSRightIndex);
//        int shift = rightToLeftShifts.get(rolledSRightIndex);
//        System.out.println("Shift is " + shift);
//        int total = rightIndex + shift;
//        System.out.println("Total " + total);
//        int rollTotal = rollPosition(total);
//        System.out.println("roleld total is " + rollTotal + "\n\n\n");
//        //int leftIndex = rollPosition(rightIndex + position - ringSetting + rightToLeftShifts.get(rollPosition(rightIndex + position - ringSetting)));
//        int leftIndex = rollTotal;
        return leftIndex;
    }
    
    public int getRightForLeft(int leftIndex) {
        int rightIndex = rollPosition(leftIndex + leftToRightShifts.get(rollPosition(leftIndex + position - ringSetting)));
        return rightIndex;
    }
    
    /**
     * Sets rotor to given position (accounting for rollover and negatives).
     * E.g., position 26 rolls over to 0, position -1 rolls back to 25
     * @param position 
     */
    public void setPosition(int position) {
        this.position = position % cipher.length();
    }
    
    public void setRingSetting(int position) {
        this.ringSetting = position;
    }
    
    public void setTurnoverPoint(int point) {
        this.turnoverPoint = point;
    }
    
    /**
     * Rotates the rotor one position.
     * @return true if this should trigger the next rotor to rotate.
     */
    public boolean rotate() {
        boolean turnover = false;
        
        position++;
        
        if (position == turnoverPoint) {// Only rotate if position is on the turnover point now
            turnover = true;
        }               
        
        if (position > 25) { //TODO Ugly magic number
            position = 0;
        }
        
        return turnover;
    }
    
    /**
     * Shift value of position to within range 0 to 25 (inclusive)
     * For example, 26 will return 1, -2 will return 24
     * @param position Current value of position
     * @return position if position is within [0, 25], otherwise shift to within that range
     */
    private int rollPosition(int position) {
        int rolledPosition = position % 26;// Ugly magic number
            
        if (rolledPosition < 0) {
            rolledPosition += 26;// Ugly magic number
        }
        return rolledPosition;
    }
    
    public boolean isOneAwayFromTurnover() {
        return rollPosition(position + 1) == turnoverPoint;
    }
}
