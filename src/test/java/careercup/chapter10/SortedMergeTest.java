package careercup.chapter10;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;

/**
 * Test for {@link SortedMerge}
 *
 * @author ahalikov
 */
public class SortedMergeTest {
  @Test
  public void testMerge0() throws Exception {
    assertNull(SortedMerge.merge(null, null, 5, 4));
  }

  @Test
  public void testMerge() throws Exception {
    int[] a = new int[]{1, 2, 5, 6, 7, 0, 0, 0, 0};
    int[] b = {-1, 4, 8, 11};
    assertArrayEquals(
      new int[]{-1, 1, 2, 4, 5, 6, 7, 8, 11},
      SortedMerge.merge(a, b, 5, 4)
    );
  }
}