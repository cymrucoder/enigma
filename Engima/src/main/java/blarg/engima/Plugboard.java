package blarg.engima;

/**
 *
 * @author cymrucoder
 */
public class Plugboard {
    
    private static final int NO_CONNECTION = -1;// Could maybe just set each element to its index to mean no connection
    
    private int[] connections = new int[26];
    
    public Plugboard() {
        clear();
    }
    
    public void connect(int firstPlugIndex, int secondPlugIndex) {
        
        // Disconnect plugs if already connected, and disconnect their current partners
        if (connections[firstPlugIndex] != NO_CONNECTION) {
            connections[connections[firstPlugIndex]] = NO_CONNECTION;
            connections[firstPlugIndex] = NO_CONNECTION;
        }
        
        if (connections[secondPlugIndex] != NO_CONNECTION) {
            connections[connections[secondPlugIndex]] = NO_CONNECTION;
            connections[secondPlugIndex] = NO_CONNECTION;
        }
        
        connections[firstPlugIndex] = secondPlugIndex;
        connections[secondPlugIndex] = firstPlugIndex;
    }
    
    public final void clear() {
        for (int i = 0; i < connections.length; i++) {            
            connections[i] = NO_CONNECTION;
        }
    }
    
    public int convert(int plugIndex) {
        return (connections[plugIndex] != NO_CONNECTION ? connections[plugIndex] : plugIndex);
    }
}
