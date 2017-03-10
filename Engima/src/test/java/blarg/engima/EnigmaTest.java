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
        enigma.setRotorRotations(('A' - 'A'), ('A' - 'A'), ('A' - 'A'));
        String plaintext = "AAAA";
        String expectedCiphertext = "BDZG";
        assertEquals(expectedCiphertext, enigma.encrypt(plaintext));
    }
    
    // Reflector B, rotors I, II, III, code E, H, K
    @Test
    public void testEncrypt_whenRotorsEHK_shouldReturnCiphertext() {
        enigma.setRotorRotations(('E' - 'A'), ('H' - 'A'), ('K' - 'A'));
        String plaintext = "AAAA";
        String expectedCiphertext = "OQNU";
        assertEquals(expectedCiphertext, enigma.encrypt(plaintext));
    }
    
    // Reflector B, rotors I, II, III, code T, B, C
    @Test
    public void testEncrypt_whenRotorsTBC_shouldReturnCiphertext() {
        enigma.setRotorRotations(('T' - 'A'), ('B' - 'A'), ('C' - 'A'));
        String plaintext = "AAAA";
        String expectedCiphertext = "NMIQ";
        assertEquals(expectedCiphertext, enigma.encrypt(plaintext));
    }
    
    // Reflector B, rotors I, II, III, code A, A, X
    @Test
    public void testEncrypt_whenRightRotorRollsover_shouldReturnCiphertext() {
        enigma.setRotorRotations(('A' - 'A'), ('A' - 'A'), ('X' - 'A'));
        String plaintext = "AAAA";
        String expectedCiphertext = "BOXY";
        assertEquals(expectedCiphertext, enigma.encrypt(plaintext));
    }
    
    // Reflector B, rotors I, II, III, code A, Z, X
    @Test
    public void testEncrypt_whenTwoRotorsRollover_shouldReturnCiphertext() {
        enigma.setRotorRotations(('A' - 'A'), ('Z' - 'A'), ('X' - 'A'));
        String plaintext = "AAAA";
        String expectedCiphertext = "KGPE";
        assertEquals(expectedCiphertext, enigma.encrypt(plaintext));
    }
    
    // Reflector B, rotors I, II, III, code Z, Z, X
    @Test
    public void testEncrypt_whenThreeRotorsRollover_shouldReturnCiphertext() {
        enigma.setRotorRotations(('Z' - 'A'), ('Z' - 'A'), ('X' - 'A'));
        String plaintext = "AAAA";
        String expectedCiphertext = "XXUB";
        assertEquals(expectedCiphertext, enigma.encrypt(plaintext));
    }
    
    // Reflector B, rotors I, III, II, code A, A, A
    @Test
    public void testEncypt_whenTwoRotorsSwapped_shouldReturnCiphertext() {
        enigma.setRotors(1, 3, 2);
        enigma.setRotorRotations(('A' - 'A'), ('A' - 'A'), ('A' - 'A'));
        String plaintext = "AAAA";
        String expectedCiphertext = "TQUK";
        assertEquals(expectedCiphertext, enigma.encrypt(plaintext));
    }
    
    // Reflector B, rotors I, III, II, code A, A, A
    @Test
    public void testEncypt_whenThreeRotorsSwapped_shouldReturnCiphertext() {
        enigma.setRotors(3, 1, 2);
        enigma.setRotorRotations(('A' - 'A'), ('A' - 'A'), ('A' - 'A'));
        String plaintext = "AAAA";
        String expectedCiphertext = "CNBE";
        assertEquals(expectedCiphertext, enigma.encrypt(plaintext));
    }
    
    // Reflector B, rotors I, III, II, code A, A, A
    @Test
    public void testEncypt_whenThreeRotorsSwappedAndThreeRollovers_shouldReturnCiphertext() {
        enigma.setRotors(3, 1, 2);
        enigma.setRotorRotations(('Z' - 'A'), ('Z' - 'A'), ('X' - 'A'));
        String plaintext = "AAAA";
        String expectedCiphertext = "ZUJC";
        assertEquals(expectedCiphertext, enigma.encrypt(plaintext));
    }
}
