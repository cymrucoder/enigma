package blarg.engima;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author cymrucoder
 */
public class Enigma {
    
    private static final String REFLECTOR_B = "YRUHQSLDPXNGOKMIEBFZCWVJAT";// I'm not really happy with this being default but it will do for now
    
    private Map<String, Rotor> rotors;
    private Map<Integer, String> setRotors;
    private Plugboard plugboard;
    
    private Rotor reflector;// Reflector is just a special case of rotor.  Don't rotate it and only get right to left.
    // But that means calling it "Rotor" isn't correct.  It should be more generally called "Cipher" or something
    // TODO Think about it.
    
    public Enigma() {
        rotors = new HashMap<>();
        setRotors = new HashMap<>();
        
        reflector = new Rotor(REFLECTOR_B);
        
        plugboard = new Plugboard();
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
    
    public void setRotorRingSetting(int index, int position) {
        rotors.get(setRotors.get(index)).setRingSetting(position);
    }
    
    public void setReflector(String cipher) {
        reflector = new Rotor(cipher);
    }
    
    public void connectPlugboard(int firstPlugIndex, int secondPlugIndex) {
        plugboard.connect(firstPlugIndex, secondPlugIndex);
    }
    
    public void clearPlugboard() {
        plugboard.clear();
    }
    
    public String encrypt(String plaintext) {
        String output = "";
        
        for (char ch : plaintext.toCharArray()) {
            
            turnRotors();
            
            // Get in "index" of the input
            int tracker = ch - 'A';
            
            tracker = plugboard.convert(tracker);
            
            for (int i = setRotors.size() - 1; i >= 0; i--) {
                tracker = ((Rotor) rotors.get(setRotors.get(i))).getLeftForRight(tracker);
            }
            
            if (reflector != null) {// I'm not sure an actual Enigma can operate without the reflector but whatever
                tracker = reflector.getLeftForRight(tracker);
            }
            
            for (int i = 0; i < setRotors.size(); i++) {
                tracker = ((Rotor) rotors.get(setRotors.get(i))).getRightForLeft(tracker);
            }

            tracker = plugboard.convert(tracker);
            
            // Revert number back to letter and add to output
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
