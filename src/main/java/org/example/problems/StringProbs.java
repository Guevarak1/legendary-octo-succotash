package org.example.problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StringProbs {
    public static void main(String[] args) {
        String palindrome = "ccc";
        longestPalindrome(palindrome);

        String[] strArr = {"flower", "flow", "flight"};
        longestCommonPrefix(strArr);
    }

    //**********************
    //EASIES
    //**********************

    private static boolean validPalindrome(String s) {
        char[] charArr = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase().toCharArray();
        int backwardsIndex = charArr.length;

        for (int i = 0; i < charArr.length; i++) {
            backwardsIndex--;
            if (charArr[i] != charArr[backwardsIndex]) return false;
        }
        return true;
    }

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        HashMap<Character, Integer> sMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            int charCounter = sMap.getOrDefault(c, 0);
            sMap.put(c, charCounter + 1);
        }

        HashMap<Character, Integer> tMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            int charCounter = tMap.getOrDefault(c, 0);
            tMap.put(c, charCounter + 1);
        }

        for (char c : sMap.keySet()) {
            int charCount = tMap.getOrDefault(c, -1);
            if (charCount != sMap.get(c))
                return false;
        }

        for (char c : tMap.keySet()) {
            int charCount = sMap.getOrDefault(c, -1);
            if (charCount != tMap.get(c))
                return false;
        }

        return true;
    }

    public static boolean isAnagramSort(String s, String t) {
        char[] sCharArr = s.toCharArray();
        char[] tCharArr = t.toCharArray();
        Arrays.sort(sCharArr);
        Arrays.sort(tCharArr);
        return Arrays.equals(sCharArr, tCharArr);
    }

    //https://leetcode.com/problems/longest-palindrome/
    public static int longestPalindrome(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int length = 0;

        //collect and remove the pairs of chars as we iterate through the string.
        for (int i = 0; i < s.length(); i++) {
            char letter = s.charAt(i);
            if (map.get(letter) != null) {
                length += 2;
                map.remove(letter);
            } else {
                map.put(letter, 1);
            }
        }
        // If the hashMap was empty then all characters had a pair in the string.
        // If not then there exists at least one single pair character that can be put in the middle of palindrome string.
        return map.isEmpty() ? length : length + 1;
    }

    //todo come back to this one
    public static String longestCommonPrefix(String[] strs) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < strs[0].length(); i++) {
            for (String s : strs) {
                char[] charArr = s.toCharArray();
                if (i == charArr.length && charArr[i] != strs[0].charAt(i)) return res.toString();
                else res.append(strs[0].charAt(i));
            }
        }
        return res.toString();
    }

    //can construct ransom note from magazine. each char from magazine can only be used once
    public static boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> magMap = new HashMap<>();
        for (char c : magazine.toCharArray()) {
            int currCount = magMap.getOrDefault(c, 1);
            magMap.put(c, currCount + 1);
        }

        Map<Character, Integer> ransMap = new HashMap<>();
        for (char c : ransomNote.toCharArray()) {
            int currCount = ransMap.getOrDefault(c, 1);
            ransMap.put(c, currCount + 1);
        }

        for (char c : ransMap.keySet()) {
            if (!magMap.containsKey(c)) return false;
            int requiredCharCount = ransMap.get(c);
            int magCharCount = magMap.get(c);
            if (magCharCount < requiredCharCount) return false;
        }

        return true;
    }

    //**********************
    //MEDIUMS
    //**********************

}
