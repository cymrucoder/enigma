package blarg.engima;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cymrucoder
 */
public class Rotor {
    private final String cipher;
    private int position;
    List<Integer> shifts;
    
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
        shifts = new ArrayList<>();
        
        char plaintext = 'A';
        
        // So BDFHJLC...
        // First char is B, plaintext A, B-A -> 1
        // At C, plaintext is G, C-G -> -4
        
        for (char ch : cipher.toCharArray()) {
            shifts.add(ch - plaintext);
            plaintext++;
        }
        
        position = 0;
    }
    
    public int getLeftForRight(int rightIndex) {
        return 0;
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
}
