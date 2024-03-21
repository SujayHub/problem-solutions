package com.slothbaby.io.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@SuppressWarnings("java:S106")
public class Solutions {

  /**
   * Main method to test solutions.
   *
   * @param args - arguments passed to main methode
   */
  public static void main(String[] args) {

    System.out.println(kidsWithCandies(new int[]{2, 3, 5, 1, 3}, 3));
    printBrake();

    System.out.println(canPlaceFlowers(new int[]{1, 0, 0, 0, 0, 1}, 2));
    printBrake();

    System.out.println(reverseVowels("leetcode"));
    printBrake();

    System.out.println(reverseWords("a good   example"));
    printBrake();

    System.out.println(Arrays.toString(productExceptSelf(new int[]{1, 2, 3, 4, 5})));
    printBrake();

    System.out.println(increasingTriplet(new int[]{20, 100, 10, 12, 5, 13}));
    printBrake();

    System.out.println(compress(new char[]{'a', 'a', 'b', 'b', 'c', 'c', 'c'}));
    printBrake();

    int[] jumblesZeros = new int[]{0, 1, 0, 3, 12};
    moveZeroes(jumblesZeros);
    System.out.println(Arrays.toString(jumblesZeros));
    printBrake();

    System.out.println(isSubsequence("def", "abcdef"));
    printBrake();

    System.out.println(waysToEatChocolate(8));
    printBrake();

    System.out.println(maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    printBrake();

    System.out.println(maxOperations(new int[]{3, 1, 5, 1, 1, 1, 1, 1, 2, 2, 3, 2, 2}, 1));
    printBrake();

    System.out.println(findMaxAverage(new int[]{1, 12, -5, -6, 50, 3}, 4));
    printBrake();

    System.out.println(maxVowels("tryhard", 4));
    printBrake();

    System.out.println(longestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2));
    printBrake();

    System.out.println(longestSubarray(new int[]{0, 1, 1, 1, 0, 1, 1, 0, 1}));
    printBrake();

    System.out.println(pivotIndex(new int[]{1, 7, 3, 6, 5, 6}));
    printBrake();

    System.out.println(findDifference(new int[]{1, 2, 3}, new int[]{2, 4, 6}));
    printBrake();

    System.out.println(uniqueOccurrences(new int[]{1, 1, 0, 3, 3, 3}));
    printBrake();

    System.out.println(closeStrings("aacbbb", "bbbcaa"));
    printBrake();
  }

  private static void printBrake() {
    System.out.println("----------------------------------------");
    System.out.println("                                         ");
  }

  private static List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {

    int maxCandy = 0;
    List<Boolean> result = new ArrayList<>(candies.length);

    for (int j : candies) {
      maxCandy = Math.max(maxCandy, j);
    }

    for (int candy : candies) {
      if ((candy + extraCandies) >= maxCandy) {
        result.add(Boolean.TRUE);
      } else {
        result.add(Boolean.FALSE);
      }
    }

    return result;

  }

  private static boolean canPlaceFlowers(int[] flowerbed, int n) {

    int flowersPlanted = 0;

    if (flowerbed.length == 0) {
      return n == 0;
    }

    if (flowerbed.length == 1) {
      if (n == 0) {
        return true;
      }

      return n == 1 && flowerbed[0] == 0;
    }

    for (int i = 0; i < flowerbed.length; i++) {
      if (flowerbed[i] == 0) {
        int left = i - 1;
        int right = i + 1;

        // checking middle
        boolean checkMiddle =
            left > 0 && right < flowerbed.length - 1 &&
                flowerbed[left] == 0 && flowerbed[right] == 0;

        // checking beginning
        boolean checkBeginning = i == 0 && right < flowerbed.length - 1 && flowerbed[right] == 0;

        // checking ending
        boolean checkEnd = i == flowerbed.length - 1 && flowerbed[left] == 0;

        if (checkBeginning || checkMiddle || checkEnd) {
          flowersPlanted++;
          flowerbed[i] = 1;
        }
      }
    }

    return flowersPlanted >= n;
  }

  private static String reverseVowels(String s) {
    if (s.length() <= 1) {
      return s;
    }

    char[] stringChars = s.toCharArray();
    boolean[] vowels = new boolean[128];
    char[] vowelChars = "aeiouAEIOU".toCharArray();
    for (char c : vowelChars) {
      vowels[c] = true;
    }

    int i = 0;
    int j = s.length() - 1;

    while (i < j) {

      while (i < j && !vowels[stringChars[i]]) {
        i++;
      }
      while (i < j && !vowels[stringChars[j]]) {
        j--;
      }

      if (i < j) {
        char temp = stringChars[i];
        stringChars[i] = stringChars[j];
        stringChars[j] = temp;
        i++;
        j--;
      }
    }

    return String.valueOf(stringChars);

  }

  private static String reverseWords(String s) {
    s = s.trim();
    String[] words = s.split(" ");
    StringBuilder sb = new StringBuilder();
    for (int i = words.length - 1; i >= 0; i--) {
      if (words[i].isEmpty()) {
        continue;
      }
      sb.append(words[i]);
      if (i != 0) {
        sb.append(" ");
      }
    }
    return sb.toString();
  }

  private static int[] productExceptSelf(int[] nums) {

    int[] ans = new int[nums.length];

    // calculate left-to-right product
    int product = 1;
    for (int i = 0; i < nums.length; i++) {
      ans[i] = product;
      product = product * nums[i];
    }

    // calculate right-to-left product and multiply it with the left-to-right product array
    product = 1;
    for (int i = nums.length - 1; i >= 0; i--) {
      ans[i] = ans[i] * product;
      product = product * nums[i];
    }
    return ans;
  }

  private static boolean increasingTriplet(int[] nums) {

    if (nums == null || nums.length < 3) {
      return false;
    }

    int small = Integer.MAX_VALUE;
    int mid = Integer.MAX_VALUE;

    for (int num : nums) {
      if (num <= small) {
        small = num;
      } else if (num <= mid) {
        mid = num;
      } else {
        return true;
      }
    }

    return false;
  }

  private static int compress(char[] chars) {

    Character currentChar = null;
    int currentCharCounter = 0;
    int result = -1;

    for (char a : chars) {
      if (currentChar == null || a != currentChar) {

        if (currentCharCounter > 1) {
          result += 1;
          chars[result] = currentChar;
          char[] counterChars = ("" + currentCharCounter).toCharArray();
          for (char c : counterChars) {
            result += 1;
            chars[result] = c;
          }
        } else if (currentCharCounter == 1) {
          result += 1;
          chars[result] = currentChar;
        }
        currentCharCounter = 1;
        currentChar = a;
      } else {
        currentCharCounter++;
      }
    }

    if (currentCharCounter > 1) {
      result += 1;
      chars[result] = currentChar;
      char[] counterChars = ("" + currentCharCounter).toCharArray();
      for (char c : counterChars) {
        result += 1;
        chars[result] = c;
      }
    } else if (currentCharCounter == 1) {
      result += 1;
      chars[result] = currentChar;
    }

    return result + 1;
  }

  private static void moveZeroes(int[] nums) {
    int nextPos = 0;

    for (int num : nums) {
      if (num != 0) {
        nums[nextPos] = num;
        nextPos++;
      }
    }
    while (nextPos < nums.length) {
      nums[nextPos] = 0;
      nextPos++;
    }
  }

  private static boolean isSubsequence(String s, String t) {
    int i = 0, j = 0;
    int n = t.length();
    int m = s.length();
    char[] ss = s.toCharArray();
    char[] tt = t.toCharArray();

    if (m < 1) {
      return true;
    }

    while (i < n) {
      if (tt[i] == ss[j]) {
        j++;
      }
      i++;

      if (j == m) {
        return true;
      }
    }

    return false;
  }

  private static int waysToEatChocolate(int size) {
    boolean[] vowels = new boolean[128];

    if (size <= 1) {
      return 1;
    }
    if (size == 2) {
      return 2;
    }
    int ways = 0;
    for (int i = 1; i <= size; i++) {
      ways += waysToEatChocolate(size - i);
    }
    return ways;
  }

  private static int maxArea(int[] height) {
    int left = 0;
    int right = height.length - 1;
    int maxArea = 0;

    while (left < right) {
      int currentArea = Math.min(height[left], height[right]) * (right - left);
      maxArea = Math.max(currentArea, maxArea);

      if (height[left] < height[right]) {
        left++;
      } else {
        right--;
      }
    }

    return maxArea;
  }

  private static int maxOperations(int[] nums, int k) {
    // Check for edge cases where it's not possible to form pairs
    if (nums.length < 2 || k < 1) {
      return 0;
    }

    // Initialize a counter for the number of operations
    int count = 0;

    // Create a HashMap to store the frequency of each number in the array
    Map<Integer, Integer> map = new HashMap<>();

    // Iterate through the array
    for (int num : nums) {
      // Calculate the difference between the target value k and the current element
      int temp = k - num;

      // Check if the difference (temp) is already present in the HashMap
      if (map.containsKey(temp)) {
        // If yes, increment the count as a pair is found
        count++;

        // If the frequency of the difference is 1, remove it from the HashMap
        if (map.get(temp) == 1) {
          map.remove(temp);
        }
        // If the frequency is greater than 1, decrement the frequency
        else {
          map.put(temp, map.get(temp) - 1);
        }
      }
      // If the difference is not present, add the current element to the HashMap
      else {
        map.put(num, map.getOrDefault(num, 0) + 1);
      }
    }

    // Return the total count of operations
    return count;
  }

  private int maxOperationsTwoPointer(int[] nums, int k) {
    // [3,1,3,4,3]
    // [1,3,3,3,4]
    int n = 0;
    for (int num : nums) {
      if (num < k) {
        nums[n++] = num;
      }
    }
    Arrays.sort(nums, 0, n);
    int res = 0, left = 0, right = n - 1;
    while (left < right) {
      if (nums[left] + nums[right] == k) {
        res++;
        left++;
        right--;
        continue;
      }
      if (nums[left] + nums[right] < k) {
        left++;
      } else {
        right--;
      }
    }
    return res;
  }

  private static double findMaxAverage(int[] nums, int k) {
    if (nums.length < k) {
      return 0;
    }

    int currentMaxSum = 0;
    int maxSum = 0;
    int n = k;

    while (n > 0) {
      maxSum = maxSum + nums[n - 1];
      n--;
    }
    currentMaxSum = maxSum;

    for (int i = k; i < nums.length; i++) {
      currentMaxSum = currentMaxSum + nums[i] - nums[i - k];
      maxSum = Math.max(maxSum, currentMaxSum);
    }

    return (double) maxSum / k;
  }

  private static int maxVowels(String s, int k) {

    Set<Character> set = Set.of('a', 'e', 'i', 'o', 'u');
    int vowel = 0;
    for (int i = 0; i < k; i++) {
      if (set.contains(s.charAt(i))) {
        vowel++;
      }
    }

    int maxvowel = vowel;

    for (int i = k; i < s.length(); i++) {
      if (set.contains(s.charAt(i - k))) {
        vowel--;
      }
      if (set.contains(s.charAt(i))) {
        vowel++;
      }
      maxvowel = Math.max(maxvowel, vowel);
    }

    return maxvowel;
  }

  private static int longestOnes(int[] nums, int k) {
    int right = 0;
    int left = 0;
    int maxOnes = 0;
    int maxZeros = 0;

    while (right < nums.length) {
      if (nums[right] == 0) {
        maxZeros++;
      }

      while (maxZeros > k) {
        if (nums[left] == 0) {
          maxZeros--;
        }
        left++;
      }

      maxOnes = Math.max(maxOnes, right - left + 1);

      right++;
    }

    return maxOnes;

  }

  private static int longestSubarray(int[] nums) {
    int right = 0;
    int left = 0;
    int ans = 0;
    int maxZeros = 0;

    for (right = 0; right < nums.length; right++) {
      if (nums[right] == 0) {
        maxZeros++;
      }

      while (maxZeros > 1) {
        if (nums[left] == 0) {
          maxZeros--;
        }
        left++;
      }

      ans = Math.max(ans, right - left + 1 - maxZeros);
    }

    return (ans == nums.length) ? ans - 1 : ans;

  }

  private static int pivotIndex(int[] nums) {
    int totalSum = 0;
    int leftSum = 0;

    for (int n : nums) {
      totalSum += n;
    }

    for (int i = 0; i < nums.length; leftSum += nums[i++]) {
      if (2 * leftSum == totalSum - nums[i]) {
        return i;
      }
    }

    return -1;

  }

  private static List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
    final List<List<Integer>> ans = new ArrayList<>();
    List<Integer> ans1 = new ArrayList<>();
    List<Integer> ans2 = new ArrayList<>();
    Set<Integer> set1 = new HashSet<>();
    Set<Integer> set2 = new HashSet<>();

    for (int n : nums1) {
      set1.add(n);
    }
    for (int n : nums2) {
      set2.add(n);
    }
    for (int n : set1) {
      if (!set2.contains(n)) {
        ans1.add(n);
      }
    }
    for (int n : set2) {
      if (!set1.contains(n)) {
        ans2.add(n);
      }
    }
    ans.add(ans1);
    ans.add(ans2);
    return ans;

  }

  private List<List<Integer>> findDifferenceWithCarry(int[] nums1, int[] nums2) {
    final int base = 1000;

    int[] s1 = new int[2001];
    int[] s2 = new int[2001];

    for (int num : nums1) {
      s1[num + base]++;
    }
    for (int num : nums2) {
      s2[num + base]++;
    }

    List<List<Integer>> list = new ArrayList<>();
    list.add(new ArrayList<>());
    list.add(new ArrayList<>());

    for (int i = 0; i < s1.length; i++) {
      if (s1[i] == 0 && s2[i] != 0) {
        list.get(1).add(i - base);
      } else if (s1[i] != 0 && s2[i] == 0) {
        list.get(0).add(i - base);
      }
    }

    return list;
  }


  private static boolean uniqueOccurrences(int[] arr) {

    Map<Integer, Long> map =
        Arrays
            .stream(arr)
            .boxed()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    List<Long> counts = new ArrayList<>();

    for (Map.Entry<Integer, Long> entry : map.entrySet()) {
      if (counts.contains(entry.getValue())) {
        return false;
      } else {
        counts.add(entry.getValue());
      }
    }
    return true;

  }


  private static boolean uniqueOccurrences2(int[] arr) {
    Arrays.sort(arr);
    List<Integer> counts = new ArrayList<>();
    int currentCount = 0;
    Integer lastSeen = null;

    for (int j : arr) {
      if (lastSeen == null) {
        lastSeen = j;
        currentCount += 1;
      } else if (j != lastSeen) {
        if (counts.contains(currentCount)) {
          return false;
        } else {
          counts.add(currentCount);
        }

        lastSeen = j;
        currentCount = 1;
      } else {
        currentCount += 1;
      }
    }

    return !counts.contains(currentCount);
  }


  private static boolean closeStrings(String word1, String word2) {
    int[] freq1 = new int[26];
    int[] freq2 = new int[26];

    for (char ch : word1.toCharArray()) {
      freq1[ch - 'a']++;
    }

    for (char ch : word2.toCharArray()) {
      freq2[ch - 'a']++;
    }

    for (int i = 0; i < 26; i++) {
      if ((freq1[i] == 0 && freq2[i] != 0) || (freq1[i] != 0 && freq2[i] == 0)) {
        return false;
      }
    }

    Arrays.sort(freq1);
    Arrays.sort(freq2);

    for (int i = 0; i < 26; i++) {
      if (freq1[i] != freq2[i]) {
        return false;
      }
    }

    return true;
  }



}

