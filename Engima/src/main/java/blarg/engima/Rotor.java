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
    }
    
    public int getLeftForRight(int rightIndex) {
        int leftIndex = rollPosition(rightIndex + rightToLeftShifts.get(rollPosition(rightIndex + position - ringSetting)));
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
    
    /**
     * Rotates the rotor one position.
     * @return true if this should trigger the next rotor to rotate.
     */
    public boolean rotate() {
        position++;
        if (position > 25) { //TODO Ugly magic number
            position = 0;
            return true;
        }
        return false;
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
}
