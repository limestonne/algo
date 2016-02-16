package careercup.chapter10;

import org.junit.Test;
import utils.Util;

import static org.junit.Assert.assertArrayEquals;

/**
 * Test for {@link GroupAnagrams}
 *
 * @author ahalikov
 */
public class GroupAnagramsTest {
  @Test
  public void testSort() throws Exception {
    System.out.println("testSort");
    String[] actual = new String[]{"abab", "Google", "Amazon", "Moo", "abba", "baba"};
    GroupAnagrams.sort(actual);
    Util.print(actual);
    assertArrayEquals(
      new String[]{"abab", "abba", "baba", "Amazon", "Google", "Moo"},
      actual
    );
  }

  @Test
  public void testSort2() throws Exception {
    System.out.println("testSort2");
    String[] actual = new String[]{"abab", "Google", "Amazon", "Microsoft", "abba", "baba"};
    GroupAnagrams.sort2(actual);
    Util.print(actual);
    //assertArrayEquals(
    //  new String[]{"abab", "abba", "baba", "Amazon", "Google", "Moo"},
    //  actual
    //);
  }
}