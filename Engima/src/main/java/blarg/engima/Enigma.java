package blarg.engima;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author cymrucoder
 */
public class Enigma {
    
    private static final String REFLECTOR_B = "YRUHQSLDPXNGOKMIEBFZCWVJAT";
    
    private Map<String, Rotor> rotors;
    private Map<Integer, String> setRotors;
    
    private Rotor reflectorB;// Reflector is just a special case of rotor.  Don't rotate it and only get right to left.
    
    public Enigma() {
        rotors = new HashMap<>();
        setRotors = new HashMap<>();
        
        reflectorB = new Rotor(REFLECTOR_B);
    }
    
    public void addRotor(String name, String cipher) {
        rotors.put(name, new Rotor(cipher));
    }
    
    public void setRotor(int index, String rotorName) {
        if (rotors.containsKey(rotorName)) {
            setRotors.put(index, rotorName);
        }
    }
    
    public void removeRotor(int index) {
        setRotors.remove(index);
    }
    
    public void clearRotors() {
        setRotors.clear();
    }
    
    public void setRotorRotation(int index, int position) {
        rotors.get(setRotors.get(index)).setPosition(position);
    }
    
    public String encrypt(String plaintext) {
        String output = "";
        
        for (char ch : plaintext.toCharArray()) {
            
            turnRotors();
            
            // Get in "index" of the input
            int tracker = ch - 'A';
            
            for (int i = setRotors.size() - 1; i >= 0; i--) {
                tracker = ((Rotor) rotors.get(setRotors.get(i))).getLeftForRight(tracker);
            }
            
            tracker = reflectorB.getLeftForRight(tracker);
            
            for (int i = 0; i < setRotors.size(); i++) {
                tracker = ((Rotor) rotors.get(setRotors.get(i))).getRightForLeft(tracker);
            }
            
            output += (char) (tracker + 'A');            
        }        
        return output;
    }
    
    private void turnRotors() {
        for (int i = setRotors.size() - 1; i >= 0; i--) {
            if (((Rotor) rotors.get(setRotors.get(i))).rotate() == false) {
                break;
            }
        }
    }
}
