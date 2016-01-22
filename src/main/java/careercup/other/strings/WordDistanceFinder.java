package careercup.other.strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * You are given a list of words (such as might be tokenized
 * from a paragraph of text), and will provide a method that takes two
 * words and returns the shortest distance (in words) between those two
 * words in the provided text.
 *
 * Example:
 *   WordDistanceFinder finder = new WordDistanceFinder(
 *    Arrays.asList("the", "quick", "brown", "fox", "quick"));
 *
 *   assert(finder.distance("fox","the") == 3);
 *   assert(finder.distance("quick", "fox") == 1);
 */
public class WordDistanceFinder {
  private List words;
  private Map<String, List<Integer>> tracker;

  public WordDistanceFinder(List<String> words) {
    this.words = words;
    this.tracker = new HashMap<>();
    for (int i = 0; i < words.size(); i++) {
      String s = words.get(i);
      if (tracker.get(s) != null)
        tracker.get(s).add(i);
      else {
        List indexes = new ArrayList();
        indexes.add(i);
        tracker.put(s, indexes);
      }
    }
  }

  public int distance(String first, String second) {
    List<Integer> indexes1 = tracker.get(first);
    List<Integer> indexes2 = tracker.get(second);

    int min = Integer.MAX_VALUE, dist = 0;
    for (int x: indexes1) {
      for (int y: indexes2) {
        dist = Math.abs(x - y);
        if (dist < min)
          min = dist;
      }
    }

    return dist;
  }
}