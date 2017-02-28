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
    
    int posRot1 = 0;
    int posRot2 = 0;
    int posRot3 = 0;
    
    public Enigma() {
        
    }
    
    /* Problems I've ran into so far
    * This can't handle rolling backwards so like -1 trips it up, input four As on AAA default to recreate: fixed for one rotor rolling under, might also roll over and/or affect other rotors
    */
    public String encrypt(String plaintext) {
        String output = "";
        
        for (char ch : plaintext.toCharArray()) {
            posRot3++;
            
            // Get in "index" of the input
            int rotor3Input = ch - 'A';
            // Then adjust for the rotation and get the output, and then unadjust for rotation (the -'A' is just because ASCII)
            int rotor3Output = rotor3.charAt(rotor3Input + posRot3) - 'A' - posRot3;
            
            int rotor2Output = rotor2.charAt(rotor3Output + posRot2) - 'A' - posRot2;

            int rotor1Output = rotor1.charAt(rotor2Output + posRot1) - 'A' - posRot1;
            
            int reflectorOutput = reflectorB.charAt(rotor1Output) - 'A';
            
            //output = "" + (char) rotor1Output;
            
            int rotor1Return = rotor1.indexOf(reflectorOutput + 'A' + posRot1) - posRot1;
            int rotor2Return = rotor2.indexOf(rotor1Return + 'A' + posRot2) - posRot2;
            int rotor3Return = rotor3.indexOf(rotor2Return + 'A' + posRot3) - posRot3;
            
            /* Ugly fix for rolling into negative numbers
            So if rotor 3 is in position D, with everything else default, then you get a C back from the middle
            This then gets turned into a C while going backwards through 3
            But C is above D, so the "index" is -1
            This breaks things in my code.  I suspect it could break things for the other rotors as well but haven't tested that yet
            Modulo to get within range -25 to 25, then if negative add it to 26 to get the rolled under value            
            */
            int mod = rotor3Return % 26;// Ugly magic number
            
            if (mod < 0) {
                rotor3Return = 26 + mod;// Ugly magic number
            }
            
            output += (char) (rotor3Return + 'A');
            
        }
        
        return output;
    }
}
