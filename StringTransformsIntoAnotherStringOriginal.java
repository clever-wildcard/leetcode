package com.example.google;

import java.util.ArrayList;
import java.util.List;

public class GStringTransformsIntoAnotherStringOriginal {
    public boolean canConvert(String str1, String str2) {
        int uniqueLettersInStr1 = uniqueLettersInString(str1);
        int uniqueLettersInStr2 = uniqueLettersInString(str2);

        // return false if the number of unique letters in str1 == 26 and number of unique letters
        // in str2 also equals 26 and str1 and str2 don't match
        if ((uniqueLettersInStr1 == 26) && (uniqueLettersInStr2 == 26) && (str1.equals(str2) == false)) {
            return false;
        }


        // "leetcode": "codeleet", should return false
        // return false if there's a substring in str1 where there isn't one in str2
        if (str1.length() > 2) {
            for (int index = 0; index < str2.length() - 1; index++) {
                // Return false if str1 contains a substring here and str2 does not.
                // Find substring in str1.
                boolean str1ContainsSubstringhere = false;
                boolean str2ContainsSubstringhere = false;
                if (Character.toString(str1.charAt(index)).equals(Character.toString(str1.charAt(index + 1)))) {
                    System.out.println("str1ContainsSubstringhere = true");
                    str1ContainsSubstringhere = true;
                    // See if there's a substring in str2 at same place.
                    if (Character.toString(str2.charAt(index)).equals(Character.toString(str2.charAt(index + 1)))) {
                        System.out.println("str2ContainsSubstringhere = true");
                        str2ContainsSubstringhere = true;
                    }
                    // End see if there's a substring in str2 at same place.
                }
                // End find substring in str1 section.
                if (str1ContainsSubstringhere == true && str2ContainsSubstringhere == false) {
                    return false;
                }
                // End return false if str1 contains a substring here and str2 does not.
            }
        }


        // "aa":"ab" returns false
        if (str1.length() == 2) {
            if (bracket(str1, 0).equals(bracket(str1, 1))) {
                if (!(bracket(str2, 0).equals(bracket(str2, 1)))) {
                    return false;
                }
            }
        }


        // "aabaa": "ccdee"; returns false
        if (str1.length() > 2) {
            String str1Letter;
            String str2Letter;
            for (int index = 0; index < str2.length() - 1; index++) {
                // Find what str1 letter we're on, and find the indecis of any matches.
                str1Letter = bracket(str1, index);
                List<Integer> indecisOfLetterMatchesInStr1 = new ArrayList<>();
                if (letterIsFoundElsewhereInString(str1Letter, index, str1) == true) {
                    indecisOfLetterMatchesInStr1 = whereLetterIsFoundElsewhereInString(str1Letter, str1);
                }
                // End find what str1 letter we're on, and find the indecis of any matches.

                // Find what str2 letter we're on, and find the indecis of any matches.
                str2Letter = bracket(str2, index);
                List<Integer> indecisOfLetterMatchesInStr2 = new ArrayList<>();
                if (letterIsFoundElsewhereInString(str2Letter, index, str2) == true) {
                    indecisOfLetterMatchesInStr2 = whereLetterIsFoundElsewhereInString(str2Letter, str2);
                }
                // End find what str2 letter we're on, and find the indecis of any matches.


                // Return false if str1 contains more letter matches than str2
                // Return false if letter match sizes were not the same
                if ((indecisOfLetterMatchesInStr1.size() > indecisOfLetterMatchesInStr2.size())) {
                    return false;
                }

                // Return false if letter matches exist in places in str1 where they do not exist in str2.
                for (int innerIndex = 0; innerIndex < indecisOfLetterMatchesInStr1.size(); innerIndex++) {
                    if (indecisOfLetterMatchesInStr1.get(innerIndex) != indecisOfLetterMatchesInStr2.get(innerIndex)) {
                        return false;
                    }
                }

            }
        }
        return true;
    }

    public String bracket(String string, Integer index) {
        return Character.toString(string.charAt(index));
    }

    public int uniqueLettersInString(String string) {
        int uniqueLetterCount = 0;
        String letter;
        for (int index = 0; index < string.length(); index++) {
            letter = bracket(string, index);
            if (letterIsFoundElsewhereInString(letter, index, string) == false) {
                uniqueLetterCount++;
            }
        }
        return uniqueLetterCount;
    }

    public boolean letterIsFoundElsewhereInString(String letter, int indexOfIncomingLetter, String string) {
        for (int index = 0; index < string.length(); index++) {
            if (indexOfIncomingLetter != index) {
                if (letter.equals(Character.toString(string.charAt(index)))) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<Integer> whereLetterIsFoundElsewhereInString(String letter, String string) {
        List<Integer> indecisOfLetterMatches = new ArrayList<>();
        for (int index = 0; index < string.length(); index++) {
            if (letter.equals(bracket(string, index)) == true) {
                indecisOfLetterMatches.add(index);
            }
        }
        return indecisOfLetterMatches;
    }
}
