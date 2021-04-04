//package com.example.google;
// https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/submissions/

public class ELongestContinuousSubarrayWithAbsoluteDifferenceLessThanOrEqualToLimit {
    /*
    longestNonemptySubarray = max(allSubarrayLengths)
    allSubarrayLengths = list of length of all subarrays in allNonemptySubarrays
    allNonemptySubarrays = any subarray in allSubarrays where abs(max(subarray) - min(subarray)) <= limit
    allSubarrays = [array[0:0], array[0:1], array[0:2], array[1:1], array[1:2], array[2:2]]
    */
    public int longestSubarray(int[] nums, int limit) {
        int longestNonemptySubarrayLength = 0;
        for (int index = 0; index < nums.length; index++) {
            int max = 0;
            int min = nums[index];
            int nonemptySubarrayLengthCounter = 0;
            for (int inner_index = index; inner_index < nums.length; inner_index++) {
                int inner_value = nums[inner_index];
                if (inner_value > max) {
                    max = inner_value;
                }
                if (inner_value < min) {
                    min = inner_value;
                }
                if (Math.abs(max - min) <= limit) {
                    nonemptySubarrayLengthCounter++;
                }
            }
            if (nonemptySubarrayLengthCounter > longestNonemptySubarrayLength) {
                longestNonemptySubarrayLength = nonemptySubarrayLengthCounter;
            }
        }
        return longestNonemptySubarrayLength;
    }

        public static void main (String ...args){
            ELongestContinuousSubarrayWithAbsoluteDifferenceLessThanOrEqualToLimit e = new ELongestContinuousSubarrayWithAbsoluteDifferenceLessThanOrEqualToLimit();
            int longestSubarray = e.longestSubarray(new int[]{8, 2, 4, 7}, 4);
            System.out.println(longestSubarray);
        }
}

