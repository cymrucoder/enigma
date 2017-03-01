package blarg.engima;

/**
 *
 * @author cymrucoder
 */
public class Enigma {
    
    // Ugly implementation with letters
    String rotor1 = "EKMFLGDQVZNTOWYHXUSPAIBRCJ";
    String rotor2 = "AJDKSIRUXBLHWTMCQGZNPYFVOE";
    String rotor3 = "BDFHJLCPRTXVZNYEIWGAKMUSQO";
    String reflectorB = "YRUHQSLDPXNGOKMIEBFZCWVJAT";
    
    int posRot1;
    int posRot2;
    int posRot3;
    
    public Enigma() {
        posRot1 = 0;
        posRot2 = 0;
        posRot3 = 0;
    }
    
    public void setRotorPositions(int one, int two, int three) {
        posRot1 = one;
        posRot2 = two;
        posRot3 = three;
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
    
    public String encrypt(String plaintext) {
        String output = "";
        
        for (char ch : plaintext.toCharArray()) {
            posRot3++;
            
            // Get in "index" of the input
            int rotor3FirstInput = ch - 'A';
            // Then adjust for the rotation and get the output, and then unadjust for rotation (the -'A' is just because ASCII)
            int rotor3FirstOutput = rotor3.charAt(rotor3FirstInput + posRot3) - 'A' - posRot3;            
            rotor3FirstOutput = rollPosition(rotor3FirstOutput);// Keep in mind the these Output values are the absolute index of the output
            
            int rotor2FirstInput = rollPosition(rotor3FirstOutput + posRot2);                        
            int rotor2FirstOutput = rotor2.charAt(rotor2FirstInput) - 'A' - posRot2;            
            rotor2FirstOutput = rollPosition(rotor2FirstOutput);
            
            int rotor1FirstInput = rollPosition(rotor2FirstOutput + posRot1);            
            int rotor1FirstOutput = rotor1.charAt(rotor1FirstInput) - 'A' - posRot1;
            rotor1FirstOutput = rollPosition(rotor1FirstOutput);    
            
            int reflectorOutput = reflectorB.charAt(rotor1FirstOutput) - 'A';            
            reflectorOutput = rollPosition(reflectorOutput);
            
            int rotor1SecondInput = rollPosition(reflectorOutput + posRot1) + 'A';// Now go through the rotors in reverse, so look up the letter within the string            
            int rotor1SecondOutput = rotor1.indexOf(rotor1SecondInput) - posRot1;
            rotor1SecondOutput = rollPosition(rotor1SecondOutput);            
            
            int rotor2SecondInput = rollPosition(rotor1SecondOutput + posRot2) + 'A';            
            int rotor2SecondOutput = rotor2.indexOf(rotor2SecondInput) - posRot2;
            rotor2SecondOutput = rollPosition(rotor2SecondOutput);
            
            int rotor3SecondInput = rollPosition(rotor2SecondOutput + posRot3) + 'A';            
            int rotor3SecondOutput = rotor3.indexOf(rotor3SecondInput) - posRot3;            
            rotor3SecondOutput = rollPosition(rotor3SecondOutput);
            
            output += (char) (rotor3SecondOutput + 'A');            
        }        
        return output;
    }
}
