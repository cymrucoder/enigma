package blarg.engima;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cymrucoder
 */
public class EnigmaTest {
    
    Enigma enigma;
    private static final String ROTOR_ONE = "EKMFLGDQVZNTOWYHXUSPAIBRCJ";
    private static final String ROTOR_TWO = "AJDKSIRUXBLHWTMCQGZNPYFVOE";
    private static final String ROTOR_THREE = "BDFHJLCPRTXVZNYEIWGAKMUSQO";
    
    private static final String REFLECTOR_B = "YRUHQSLDPXNGOKMIEBFZCWVJAT";
    private static final String REFLECTOR_C = "FVPJIAOYEDRZXWGCTKUQSBNMHL";
    
    public EnigmaTest() {
        enigma = new Enigma();
        enigma.addRotor("I", ROTOR_ONE);
        enigma.addRotor("II", ROTOR_TWO);
        enigma.addRotor("III", ROTOR_THREE);
    }
    
    private void setupEnigma(String firstRotor, String secondRotor, String thirdRotor, int firstPosition, int secondPosition, int thirdPosition) {
        enigma.clearRotors();
        enigma.setRotor(0, firstRotor);
        enigma.setRotor(1, secondRotor);
        enigma.setRotor(2, thirdRotor);
        enigma.setRotorRotation(0, firstPosition);
        enigma.setRotorRotation(1, secondPosition);
        enigma.setRotorRotation(2, thirdPosition);
    }
    
    private void setupEnigma(String firstRotor, String secondRotor, String thirdRotor, int firstPosition, int secondPosition, int thirdPosition, String reflector) {
        enigma.clearRotors();
        enigma.setRotor(0, firstRotor);
        enigma.setRotor(1, secondRotor);
        enigma.setRotor(2, thirdRotor);
        enigma.setRotorRotation(0, firstPosition);
        enigma.setRotorRotation(1, secondPosition);
        enigma.setRotorRotation(2, thirdPosition);
        enigma.setReflector(reflector);
    }
    
    // Reflector B, rotors I, II, III, code A, A, A
    @Test
    public void testEncrypt_whenRotorsAAA_shouldReturnCiphertext() {
        setupEnigma("I", "II", "III", 0, 0, 0);        
        String plaintext = "AAAA";
        String expectedCiphertext = "BDZG";
        enigma.setRotorTurnoverPoint(0, ('R' - 'A'));
        enigma.setRotorTurnoverPoint(1, ('F' - 'A'));
        enigma.setRotorTurnoverPoint(2, ('W' - 'A'));
        assertEquals(expectedCiphertext, enigma.encrypt(plaintext));
    }
    
    // Reflector B, rotors I, II, III, code E, H, K
    @Test
    public void testEncrypt_whenRotorsEHK_shouldReturnCiphertext() {
        setupEnigma("I", "II", "III", ('E' - 'A'), ('H' - 'A'), ('K' - 'A'));
        String plaintext = "AAAA";
        String expectedCiphertext = "OQNU";
        assertEquals(expectedCiphertext, enigma.encrypt(plaintext));
    }
    
    // Reflector B, rotors I, II, III, code T, B, C
    @Test
    public void testEncrypt_whenRotorsTBC_shouldReturnCiphertext() {
        setupEnigma("I", "II", "III", ('T' - 'A'), ('B' - 'A'), ('C' - 'A'));
        String plaintext = "AAAA";
        String expectedCiphertext = "NMIQ";
        assertEquals(expectedCiphertext, enigma.encrypt(plaintext));
    }
    
    // Reflector B, rotors I, II, III, code A, A, X
    // I can't compare this to online enigma simulators because I can't set the custom turnover points (I think these were all assumed to be 0, which isn't realistic, but I might fix it at some point)
//    @Test
//    public void testEncrypt_whenRightRotorRollsover_shouldReturnCiphertext() {
//        setupEnigma("I", "II", "III", ('A' - 'A'), ('A' - 'A'), ('X' - 'A'));
//        String plaintext = "AAAA";
//        String expectedCiphertext = "BOXY";
//        assertEquals(expectedCiphertext, enigma.encrypt(plaintext));
//    }
    
    @Test
    public void testEncrypt_whenRightRotorRollsover_shouldReturnCiphertext() {
        setupEnigma("I", "II", "III", ('A' - 'A'), ('A' - 'A'), ('U' - 'A'));
        enigma.setRotorTurnoverPoint(0, ('R' - 'A'));
        enigma.setRotorTurnoverPoint(1, ('F' - 'A'));
        enigma.setRotorTurnoverPoint(2, ('W' - 'A'));
        String plaintext = "AAAA";
        String expectedCiphertext = "MUQO";
        assertEquals(expectedCiphertext, enigma.encrypt(plaintext));
    }
    
    // Reflector B, rotors I, II, III, code A, Z, X
    // I can't compare this to online enigma simulators because I can't set the custom turnover points (I think these were all assumed to be 0, which isn't realistic, but I might fix it at some point)
//    @Test
//    public void testEncrypt_whenTwoRotorsRollover_shouldReturnCiphertext() {
//        setupEnigma("I", "II", "III", ('A' - 'A'), ('Z' - 'A'), ('X' - 'A'));
//        enigma.setRotorTurnoverPoint(0, 0);
//        enigma.setRotorTurnoverPoint(1, 0);
//        enigma.setRotorTurnoverPoint(2, 0);
//        String plaintext = "AAAA";
//        String expectedCiphertext = "KGPE";
//        assertEquals(expectedCiphertext, enigma.encrypt(plaintext));
//    }
    
    // Reflector B, rotors I, II, III, code Z, Z, X
    // I can't compare this to online enigma simulators because I can't set the custom turnover points (I think these were all assumed to be 0, which isn't realistic, but I might fix it at some point)
//    @Test
//    public void testEncrypt_whenThreeRotorsRollover_shouldReturnCiphertext() {
//        setupEnigma("I", "II", "III", ('Z' - 'A'), ('Z' - 'A'), ('X' - 'A'));
//        String plaintext = "AAAA";
//        String expectedCiphertext = "XXUB";
//        assertEquals(expectedCiphertext, enigma.encrypt(plaintext));
//    }
    
    // Reflector B, rotors I, III, II, code A, A, A
    @Test
    public void testEncypt_whenTwoRotorsSwapped_shouldReturnCiphertext() {
        setupEnigma("I", "III", "II", ('A' - 'A'), ('A' - 'A'), ('A' - 'A'));
        String plaintext = "AAAA";
        String expectedCiphertext = "TQUK";
        assertEquals(expectedCiphertext, enigma.encrypt(plaintext));
    }
    
    // Reflector B, rotors I, III, II, code A, A, A
    @Test
    public void testEncypt_whenThreeRotorsSwapped_shouldReturnCiphertext() {
        setupEnigma("III", "I", "II", ('A' - 'A'), ('A' - 'A'), ('A' - 'A'));
        String plaintext = "AAAA";
        String expectedCiphertext = "CNBE";
        assertEquals(expectedCiphertext, enigma.encrypt(plaintext));
    }
    
    // Reflector B, rotors I, III, II, code A, A, A
    // I can't compare this to online enigma simulators because I can't set the custom turnover points (I think these were all assumed to be 0, which isn't realistic, but I might fix it at some point)
//    @Test
//    public void testEncypt_whenThreeRotorsSwappedAndThreeRollovers_shouldReturnCiphertext() {
//        setupEnigma("III", "I", "II", ('Z' - 'A'), ('Z' - 'A'), ('X' - 'A'));
//        String plaintext = "AAAA";
//        String expectedCiphertext = "ZUJC";
//        assertEquals(expectedCiphertext, enigma.encrypt(plaintext));
//    }
    
    // Reflector C, rotors I, II, III, code A, A, A
    @Test
    public void testEncrypt_whenRotorsAAAReflectorC_shouldReturnCiphertext() {
        setupEnigma("I", "II", "III", 0, 0, 0, REFLECTOR_C);
        String plaintext = "AAAA";
        String expectedCiphertext = "PJBU";
        assertEquals(expectedCiphertext, enigma.encrypt(plaintext));
    }
    
    // Reflector C, rotors I, II, III, code A, A, A, plugboard AB
    @Test
    public void testEncrypt_whenRotorsAAAPlugboardAB_shouldReturnCiphertext() {
        setupEnigma("I", "II", "III", 0, 0, 0, REFLECTOR_B);
        enigma.connectPlugboard(('A' - 'A'), ('B' - 'A'));
        String plaintext = "AAAA";
        String expectedCiphertext = "BJLC";
        assertEquals(expectedCiphertext, enigma.encrypt(plaintext));
    }
    
    // Reflector C, rotors I, II, III, code A, A, A, plugboard AB,CJ,TV,ER,IF,QM,WL
    @Test
    public void testEncrypt_whenRotorsAAAPlugboardCJTVER_shouldReturnCiphertext() {
        setupEnigma("I", "II", "III", 0, 0, 0, REFLECTOR_B);
        enigma.connectPlugboard(('A' - 'A'), ('B' - 'A'));
        enigma.connectPlugboard(('C' - 'A'), ('J' - 'A'));
        enigma.connectPlugboard(('T' - 'A'), ('V' - 'A'));
        enigma.connectPlugboard(('E' - 'A'), ('R' - 'A'));
        enigma.connectPlugboard(('I' - 'A'), ('F' - 'A'));
        enigma.connectPlugboard(('Q' - 'A'), ('M' - 'A'));
        enigma.connectPlugboard(('W' - 'A'), ('L' - 'A'));
        String plaintext = "RAWR";// Wanted to test a few different input letters for plugboard
        String expectedCiphertext = "ICAL";
        assertEquals(expectedCiphertext, enigma.encrypt(plaintext));
    }
    
    // Reflector C, rotors I, II, III, code A, A, A, plugboard AB then changed to AC
    // Test whether disconnecting works properly
    @Test
    public void testEncrypt_whenRotorsAAAPlugboardChanged_shouldReturnCiphertext() {
        setupEnigma("I", "II", "III", 0, 0, 0, REFLECTOR_B);
        enigma.connectPlugboard(('A' - 'A'), ('B' - 'A'));
        String plaintext = "AAAA";
        String expectedCiphertext = "BJLC";
        assertEquals(expectedCiphertext, enigma.encrypt(plaintext));
                
        enigma.connectPlugboard(('A' - 'A'), ('C' - 'A'));
        plaintext = "AAAA";
        expectedCiphertext = "NMCJ";// Remember that the rotors have already turned four times before doing this!  Because I forgot it before ;)
        assertEquals(expectedCiphertext, enigma.encrypt(plaintext));
    }
    
    // Reflector B, rotors I, II, III, code A, A, A, ring AAA
    @Test
    public void testEncrypt_whenRotorsAAARingAAA_shouldReturnCiphertext() {
        setupEnigma("I", "II", "III", 0, 0, 0);        
        String plaintext = "AAAA";
        String expectedCiphertext = "BDZG";
        assertEquals(expectedCiphertext, enigma.encrypt(plaintext));
    }
    
    // Reflector B, rotors I, II, III, code A, A, A, ring AAB
    @Test
    public void testEncrypt_whenRotorsAAARingAAB_shouldReturnCiphertext() {
        setupEnigma("I", "II", "III", 0, 0, 0);
        enigma.setRotorRingSetting(2, 'B' - 'A');
        String plaintext = "AAAA";
        String expectedCiphertext = "UBDZ";
        assertEquals(expectedCiphertext, enigma.encrypt(plaintext));
    }
    
    // Reflector B, rotors I, II, III, code A, A, A, ring AAZ
    @Test
    public void testEncrypt_whenRotorsAAARingAAZ_shouldReturnCiphertext() {
        setupEnigma("I", "II", "III", 0, 0, 0);
        //enigma.setRotorRingSetting(0, 'A' - 'A');
        //enigma.setRotorRingSetting(0, 'A' - 'A');
        enigma.setRotorRingSetting(2, 'Z' - 'A');
        String plaintext = "AAAA";
        String expectedCiphertext = "DZGO";
        assertEquals(expectedCiphertext, enigma.encrypt(plaintext));
    }
    
    // Reflector B, rotors I, II, III, code E, H, K
    @Test
    public void testEncrypt_whenRotorsEHKRingABC_shouldReturnCiphertext() {
        setupEnigma("I", "II", "III", ('E' - 'A'), ('H' - 'A'), ('K' - 'A'));
        enigma.setRotorRingSetting(0, 'A' - 'A');
        enigma.setRotorRingSetting(1, 'B' - 'A');
        enigma.setRotorRingSetting(2, 'C' - 'A');
        String plaintext = "AAAA";
        String expectedCiphertext = "QRNQ";
        assertEquals(expectedCiphertext, enigma.encrypt(plaintext));
    }
    
    // Reflector B, rotors I, II, III, code A, A, V, turnover should occur on first press
    @Test
    public void testEncrypt_whenRotorsAAV_shouldReturnCiphertext() {
        setupEnigma("I", "II", "III", 0, 0, ('V' - 'A'));
        enigma.setRotorTurnoverPoint(0, ('R' - 'A'));
        enigma.setRotorTurnoverPoint(1, ('F' - 'A'));
        enigma.setRotorTurnoverPoint(2, ('W' - 'A'));
        String plaintext = "AAAA";
        String expectedCiphertext = "UQOF";
        assertEquals(expectedCiphertext, enigma.encrypt(plaintext));
    }
    
    // Reflector B, rotors I, II, III, code A, A, U, ring AAU, turnover should occur on first press
    @Test
    public void testEncrypt_whenRotorsAAARingAAV_shouldReturnCiphertext() {
        setupEnigma("I", "II", "III", 0, 0, ('U' - 'A'));
        enigma.setRotorTurnoverPoint(0, ('R' - 'A'));
        enigma.setRotorTurnoverPoint(1, ('F' - 'A'));
        enigma.setRotorTurnoverPoint(2, ('W' - 'A'));
        enigma.setRotorRingSetting(0, ('A' - 'A'));
        enigma.setRotorRingSetting(1, ('A' - 'A'));
        enigma.setRotorRingSetting(2, ('U' - 'A'));
        String plaintext = "AAAA";
        String expectedCiphertext = "BHCX";
        assertEquals(expectedCiphertext, enigma.encrypt(plaintext));
    }
    
    // Reflector B, rotors I, II, III, code Q, E, V, ring ABC, turnover should occur on first press
    @Test
    public void testEncrypt_whenRotorsQEVRingAAV_shouldReturnCiphertext() {
        setupEnigma("I", "II", "III", ('A' - 'A'), ('A' - 'A'), ('U' - 'A'));
        enigma.setRotorRingSetting(0, 'A' - 'A');
        enigma.setRotorRingSetting(1, 'B' - 'A');
        enigma.setRotorRingSetting(2, 'C' - 'A');
        enigma.setRotorTurnoverPoint(0, ('R' - 'A'));
        enigma.setRotorTurnoverPoint(1, ('F' - 'A'));
        enigma.setRotorTurnoverPoint(2, ('W' - 'A'));
        String plaintext = "AAAA";
        String expectedCiphertext = "LBMF";
        assertEquals(expectedCiphertext, enigma.encrypt(plaintext));
    }
    
    // Reflector B, rotors III, II, I, code A, D, Q, ring AAA, double step anomoly
    @Test
    public void testEncrypt_whenRotors321ADQ_shouldDoubleStepAndReturnCiphertext() {
        setupEnigma("III", "II", "I", ('A' - 'A'), ('D' - 'A'), ('Q' - 'A'));
        enigma.setRotorTurnoverPoint(0, ('V' - 'A'));
        enigma.setRotorTurnoverPoint(1, ('E' - 'A'));
        enigma.setRotorTurnoverPoint(2, ('Q' - 'A'));
        String plaintext = "AAAA";
        String expectedCiphertext = "ZGOV";
        assertEquals(expectedCiphertext, enigma.encrypt(plaintext));
    }
}
