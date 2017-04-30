package blarg.engima;

/**
 *
 * @author cymrucoder
 */
public class Enigma {
    
    private static final String ROTOR_ONE = "EKMFLGDQVZNTOWYHXUSPAIBRCJ";
    private static final String ROTOR_TWO = "AJDKSIRUXBLHWTMCQGZNPYFVOE";
    private static final String ROTOR_THREE = "BDFHJLCPRTXVZNYEIWGAKMUSQO";
    private static final String REFLECTOR_B = "YRUHQSLDPXNGOKMIEBFZCWVJAT";
    
    private Rotor[] rotors = new Rotor[3];    
    private Rotor[] setRotors = new Rotor[3];
    
    private Rotor reflectorB;// Reflector is just a special case of rotor.  Don't rotate it and only get right to left.
    
    public Enigma() {
        rotors[0] = new Rotor(ROTOR_ONE);
        rotors[1] = new Rotor(ROTOR_TWO);
        rotors[2] = new Rotor(ROTOR_THREE);
        
        setRotors[0] = rotors[0];
        setRotors[1] = rotors[1];
        setRotors[2] = rotors[2];
        
        reflectorB = new Rotor(REFLECTOR_B);
    }
    
    public void setRotors(int left, int middle, int right) {
        // TODO what if you swap rotors while they are already rotated?
        setRotors[0] = rotors[left - 1];
        setRotors[1] = rotors[middle - 1];
        setRotors[2] = rotors[right - 1];
    }
    
    public void setRotorRotations(int one, int two, int three) {
        // These argument names are terrible
        setRotors[0].setPosition(one);
        setRotors[1].setPosition(two);
        setRotors[2].setPosition(three);
    }
    
    public String encrypt(String plaintext) {
        String output = "";
        
        for (char ch : plaintext.toCharArray()) {
            
            turnRotors();
            
            // Get in "index" of the input
            int rotor3FirstInput = ch - 'A';
            
            int rotor3FirstOutput = setRotors[2].getLeftForRight(rotor3FirstInput);
            int rotor2FirstOutput = setRotors[1].getLeftForRight(rotor3FirstOutput);
            int rotor1FirstOutput = setRotors[0].getLeftForRight(rotor2FirstOutput);
            
            int reflectorOutput = reflectorB.getLeftForRight(rotor1FirstOutput);
            
            int rotor1SecondOutput = setRotors[0].getRightForLeft(reflectorOutput);
            int rotor2SecondOutput = setRotors[1].getRightForLeft(rotor1SecondOutput);
            int rotor3SecondOutput = setRotors[2].getRightForLeft(rotor2SecondOutput);
            
            output += (char) (rotor3SecondOutput + 'A');            
        }        
        return output;
    }
    
    private void turnRotors() {
        if (setRotors[2].rotate()) {
            if (setRotors[1].rotate()) {
                setRotors[0].rotate();
            }
        }
    }
}
