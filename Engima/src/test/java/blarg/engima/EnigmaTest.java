package blarg.engima;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cymrucoder
 */
public class EnigmaTest {
    
    Enigma enigma;
    
    public EnigmaTest() {
        enigma = new Enigma();
    }
    
    // Reflector B, rotors I, II, III, code A, A, A
    @Test
    public void testEncrypt_whenRotorsAAA_shouldReturnCiphertext() {
        enigma.setRotorPositions(('A' - 'A'), ('A' - 'A'), ('A' - 'A'));
        String plaintext = "AAAA";
        String expectedCiphertext = "BDZG";
        assertEquals(expectedCiphertext, enigma.encrypt(plaintext));
    }
    
    // Reflector B, rotors I, II, III, code E, H, K
    @Test
    public void testEncrypt_whenRotorsEHK_shouldReturnCiphertext() {
        enigma.setRotorPositions(('E' - 'A'), ('H' - 'A'), ('K' - 'A'));
        String plaintext = "AAAA";
        String expectedCiphertext = "OQNU";
        assertEquals(expectedCiphertext, enigma.encrypt(plaintext));
    }
    
    // Reflector B, rotors I, II, III, code T, B, C
    @Test
    public void testEncrypt_whenRotorsTBY_shouldReturnCiphertext() {
        enigma.setRotorPositions(('T' - 'A'), ('B' - 'A'), ('C' - 'A'));
        String plaintext = "AAAA";
        String expectedCiphertext = "NMIQ";
        assertEquals(expectedCiphertext, enigma.encrypt(plaintext));
    }
    
}
