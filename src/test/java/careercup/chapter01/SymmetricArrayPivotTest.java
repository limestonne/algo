package careercup.chapter01;

import org.junit.Test;

import static careercup.chapter01.SymmetricArrayPivot.*;
import static org.junit.Assert.*;

/**
 * Test for {@link SymmetricArrayPivot}
 *
 * @author ahalikov
 */
public class SymmetricArrayPivotTest {

    @Test
    public void testFindPivot0() throws Exception {
        assertEquals(-1, findPivot(null));
        assertEquals(-1, findPivot(new int[]{}));
    }

    @Test
    public void testFindPivot() throws Exception {
        assertEquals(1, findPivot(new int[]{1, 2, 1}));
        assertEquals(2, findPivot(new int[]{-1, 100, 1, 1, 98}));
    }
}