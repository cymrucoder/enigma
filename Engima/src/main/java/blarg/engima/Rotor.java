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
    List<Integer> rightToLeftShifts;
    List<Integer> leftToRightShifts;
    
    /*
    
    ABCDEFGHIJKLMNOPQRSTUVWXYZ
    BDFHJLCPRTXCZNYEIWGAKMUSQO
    
    //Position 0 (A)
    getLFR(0) = 1
    getLFR(1) = 3
    getRFL(0) = 19
    getRFL(1) = 0
    
    //Position 1 (B)
    gLFR(0) = 2
    gLFR(1) = 4
    gRFL(0) = 25
    gRFL(1) = 5
    
    */
    
    public Rotor(String cipher) {
        this.cipher = cipher.toUpperCase();
        rightToLeftShifts = new ArrayList<>();
        leftToRightShifts = new ArrayList<>(Collections.nCopies(cipher.length(), 0));
                
        //char plaintext = 'A';
        //int i = 0;
        
        // So BDFHJLC...
        // First char is B, plaintext A, B-A -> 1
        // At C, plaintext is G, C-G -> -4
        
        //for (char ch : cipher.toCharArray()) {
        //    rightToLeftShifts.add(ch - plaintext);
        //    plaintext++;
        //}
        
        for (int i = 0; i < cipher.length(); i++) {
            int shift = cipher.charAt(i) - ('A' + i);
            rightToLeftShifts.add(shift);
            leftToRightShifts.set(i, shift * -1);
        }
        
        position = 0;
    }
    
    public int getLeftForRight(int rightIndex) {
        int leftIndex = rollPosition(rightIndex + rightToLeftShifts.get(rollPosition(rightIndex + position)));
        return leftIndex;
    }
    
    public int getRightForLeft(int leftIndex) {
        return 0;
    }
    
    /**
     * Sets rotor to given position (accounting for rollover and negatives).
     * E.g., position 26 rolls over to 0, position -1 rolls back to 25
     * @param position 
     */
    public void setPosition(int position) {
        this.position = position % cipher.length();
    }
    
    /**
     * Rotates the rotor one position.
     * @return true if this should trigger the next rotor to rotate.
     */
    public boolean rotate() {
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
