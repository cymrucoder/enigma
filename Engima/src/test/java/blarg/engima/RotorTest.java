
package blarg.engima;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cymrucoder
 */
public class RotorTest {

    Rotor rotor;
    
    public RotorTest() {
        rotor = new Rotor("BDFHJLCPRTXVZNYEIWGAKMUSQO");
        
    }

    @Test
    public void testGetLeftForRight_whenPosition0Index0_shouldReturnIndex1() {
        rotor.setPosition(0);
        int expectedIndex = 1;
        int actualIndex = rotor.getLeftForRight(0);
        assertEquals(expectedIndex, actualIndex);
    }
    
    @Test
    public void testGetLeftForRight_whenPosition0Index1_shouldReturnIndex3() {
        rotor.setPosition(0);
        int expectedIndex = 3;
        int actualIndex = rotor.getLeftForRight(1);
        assertEquals(expectedIndex, actualIndex);
    }
    
    @Test
    public void testGetLeftForRight_whenPosition1Index0_shouldReturnIndex2() {
        rotor.setPosition(1);
        int expectedIndex = 2;
        int actualIndex = rotor.getLeftForRight(0);
        assertEquals(expectedIndex, actualIndex);
    }
    
    @Test
    public void testGetLeftForRight_whenPosition1Index1_shouldReturnIndex4() {
        rotor.setPosition(1);
        int expectedIndex = 4;
        int actualIndex = rotor.getLeftForRight(1);
        assertEquals(expectedIndex, actualIndex);
    }
    
    @Test
    public void testGetLeftForRight_whenPosition25Index25_shouldReturnIndex17() {
        rotor.setPosition(25);
        int expectedIndex = 17;
        int actualIndex = rotor.getLeftForRight(25);
        assertEquals(expectedIndex, actualIndex);
    }
    
    @Test
    public void testGetLeftForRight_whenPosition1Index25_shouldReturnIndex0() {
        rotor.setPosition(1);
        int expectedIndex = 0;
        int actualIndex = rotor.getLeftForRight(25);
        assertEquals(expectedIndex, actualIndex);
    }
    
    @Test
    public void testGetLeftForRight_whenPosition5Index1_shouldReturnIndex23() {
        rotor.setPosition(5);
        int expectedIndex = 23;
        int actualIndex = rotor.getLeftForRight(1);
        assertEquals(expectedIndex, actualIndex);
    }
}