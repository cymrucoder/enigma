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
    public void testEncrypt_whenRotorsTBC_shouldReturnCiphertext() {
        enigma.setRotorPositions(('T' - 'A'), ('B' - 'A'), ('C' - 'A'));
        String plaintext = "AAAA";
        String expectedCiphertext = "NMIQ";
        assertEquals(expectedCiphertext, enigma.encrypt(plaintext));
    }
    
    // Reflector B, rotors I, II, III, code A, A, X
    @Test
    public void testEncrypt_whenRightRotorRollsover_shouldReturnCiphertext() {
        enigma.setRotorPositions(('A' - 'A'), ('A' - 'A'), ('X' - 'A'));
        String plaintext = "AAAA";
        String expectedCiphertext = "BOXY";
        assertEquals(expectedCiphertext, enigma.encrypt(plaintext));
    }
    
    // Reflector B, rotors I, II, III, code A, Z, X
    @Test
    public void testEncrypt_whenTwoRotorsRollover_shouldReturnCiphertext() {
        enigma.setRotorPositions(('A' - 'A'), ('Z' - 'A'), ('X' - 'A'));
        String plaintext = "AAAA";
        String expectedCiphertext = "KGPE";
        assertEquals(expectedCiphertext, enigma.encrypt(plaintext));
    }
    
    // Reflector B, rotors I, II, III, code Z, Z, X
    @Test
    public void testEncrypt_whenThreeRotorsRollover_shouldReturnCiphertext() {
        enigma.setRotorPositions(('Z' - 'A'), ('Z' - 'A'), ('X' - 'A'));
        String plaintext = "AAAA";
        String expectedCiphertext = "XXUB";
        assertEquals(expectedCiphertext, enigma.encrypt(plaintext));
    }
}
