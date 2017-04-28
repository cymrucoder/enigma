package blarg.engima;

/**
 *
 * @author cymrucoder
 */
public class Rotor {
    private final String cipher;
    private int position;
    
    public Rotor(String cipher) {
        this.cipher = cipher;
        position = 0;
    }
    
    int getLeftForRight(int rightIndex) {
        return 0;
    }
    
    int getRightForLeft(int leftIndex) {
        return 0;
    }
    
    /**
     * Sets rotor to given position (accounting for rollover and negatives).
     * E.g., position 26 rolls over to 0, position -1 rolls back to 25
     * @param position 
     */
    void setPosition(int position) {
        this.position = position % cipher.length();
    }
    
    /**
     * Rotates the rotor one position.
     * @return true if this should trigger the next rotor to rotate.
     */
    boolean rotate() {
        return false;
    }
}
