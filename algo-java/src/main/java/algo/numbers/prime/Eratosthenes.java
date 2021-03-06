package algo.numbers.prime;

import java.util.Arrays;

/**
 * Sieve of Eratosthenes
 * Problem: prime numbers
 * Input: n <= 10^8
 * Output: prime numbers from 0 to n
 *
 * @author Artur Khalikov
 */
class Eratosthenes {
  /**
   * Brut-force implementation
   * Performance is O(log (log N))
   */
  static boolean[] sieve(int n) {
    boolean[] sieve = new boolean[n];
    Arrays.fill(sieve, true);
    sieve[0] = false;
    sieve[1] = false;
    for (int i = 2; i * i < n; i++) {
      if (sieve[i])
        for (int j = i * i; j < n; j += i)
          sieve[j] = false;
    }
    return sieve;
  }
}
