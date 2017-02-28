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
    
    // M3, Reflector B, rotors I, II, III, code A, A, A, and then A A A
    @Test
    public void testEcrypt_whenM3RefBDefaultRotors_shouldReturnCiphertext() {
        String plaintext = "AAAA";
        String expectedCiphertext = "BDZG";
        assertEquals(expectedCiphertext, enigma.encrypt(plaintext));
    }
    
}
