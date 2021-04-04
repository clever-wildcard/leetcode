class Solution {

    public int findMaxConsecutiveOnes(int[] nums) {
        /***
         *     https://leetcode.com/problems/max-consecutive-ones/solution/
         *     Python equivalent in leetcode.py under find_max_consecutive_ones.
         */
        int count = 0;
        int greatestCount = count;
        for (int index = 0; index < nums.length; index++) {
            if (nums[index] == 1 ) {
                count++;
            } else {
                if (count > greatestCount) {
                    greatestCount = count;
                }
                count = 0;
            }

        }
        if (count > greatestCount) {
            greatestCount = count;
        }
        return greatestCount;
    }

// Next Question

    public int findNumbers(int[] nums) {
        /**
         * https://leetcode.com/explore/learn/card/fun-with-arrays/521/introduction/3237/
         * Python equivalent in leecode.py under find_numbers.
         */
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (String.valueOf(nums[i]).length() % 2 == 0) {
                count++;
            }
        }
        return count;
    }

// Next Question

    public int[] sortedSquares(int[] nums) {
        /**
         *     https://leetcode.com/explore/learn/card/fun-with-arrays/521/introduction/3240/
         *
         *     Input: nums = [-4,-1,0,3,10]
         *     Output: [0,1,9,16,100]
         *     Explanation: After squaring, the array becomes [16,1,0,9,100].
         *     After sorting, it becomes [0,1,9,16,100].
         *
         *     Python equivalent in leetcode.py under sorted_squares.
         */
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] * nums[i];
        }
        Arrays.sort(nums);
        return nums;
    }

// Next Question

    public void duplicateZeros(int[] arr) {
        /***
         * https://leetcode.com/problems/duplicate-zeros/
         * Input: [1,0,2,3,0,4,5,0]
         * Output: null
         * Explanation: After calling your function, the input array is modified to: [1,0,0,2,3,0,0,4]
         *
         * Python equivalent in leetcode.py under duplicate_zeros.
         */
        int index = 0;
        int[] arrrrgh = new int[arr.length * 2];
        int iish = 0;
        for (int i = 0; i < arr.length; i++) {
            arrrrgh[iish] = arr[i];
            if (arr[i] == 0) {
                iish++;
                arrrrgh[iish] = 0;
            }
            iish++;
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = arrrrgh[i];
        }

    }

// Next Question

    public int[] twoSum(int[] nums, int target) {
        /**
         *         https://leetcode.com/problems/two-sum/
         *
         *         Ex:
         *         Input: nums = [2,7,11,15], target = 9
         *         Output: [0,1]
         *         Output: Because nums[0] + nums[1] == 9, we return [0, 1].
         *
         *         Python equivalent in leetcode.py under two_sum.
         */
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int index = 0; index < nums.length; index++) {
            if (map.containsKey(target - nums[index])) {
                return new int[]{index, map.get(target - nums[index])};
            }
            map.put(nums[index], index);
        }
        return new int[2];
    }

// Next Question

    public String minWindow(String S, String T) {
        /**
         * https://leetcode.com/problems/minimum-window-subsequence/
         *
         */
        List<Integer> tInS = new ArrayList<>();
        String sSubstring = S;
        int index = 0;
        int findMeInS;
        int substringStart = 0;
        while (index < T.length()) {
            findMeInS = S.substring(substringStart).indexOf(T.charAt(index));
            if (findMeInS == -1) {System.out.println("anna");

                return "";
            }
            if (tInS.size() == 0) {
                tInS.add(findMeInS);
                substringStart += findMeInS + 1;
                index++;
            }
            else {
                int fuckingJava = findMeInS + tInS.get(tInS.size() - 1) + 1;
                tInS.add(fuckingJava);
                substringStart += findMeInS + 1;
                index++;
                String checkMe = sSubstring.substring(0, findMeInS);
                for (int edgeCaseIndex = 0; edgeCaseIndex < index; edgeCaseIndex++) {
                    if (checkMe.indexOf(T.charAt(edgeCaseIndex)) > 0) {
                        index = 0;
                        substringStart = tInS.get(tInS.size() - 1);
                        tInS = new ArrayList<>();
                        System.out.println("-1");
                    }


                }
            }
            for (int thing : tInS) {
                System.out.print(thing);
            }
            System.out.println("");
        }
        for (int thing : tInS) {
            System.out.print(thing);
        }
        System.out.println("");
        return S.substring(tInS.get(0), tInS.get(tInS.size() - 1) + 1);
    }

    public static void main(String... args) {
        minWindow("cnhczmccqouqadqtmjjzl", "mm");
    }

// Next Question

    public int longestSubarray(int[] nums, int limit) {
        /*
        longestNonemptySubarray = max(allSubarrayLengths)
        allSubarrayLengths = list of length of all subarrays in allNonemptySubarrays
        allNonemptySubarrays = any subarray in allSubarrays where abs(max(subarray) - min(subarray)) <= limit
        allSubarrays = [array[0:0], array[0:1], array[0:2], array[1:1], array[1:2], array[2:2]]
        */
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
        int longestSubarray = longestSubarray(new int[]{8, 2, 4, 7}, 4);
        System.out.println(longestSubarray);
    }

// Next Question

    public int maxScore(int[] cardPoints, int k) {
        /**
         * https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/
         */
        int greatestWindowSum = 0; // instantiate and initialize greatestWindowSum
        int currentWindowSum = 0; // instantiate and initialize currentWindowSum

        for (int i = cardPoints.length - k; i < cardPoints.length; i++) { // for index = the windowSizeth index from array tail, as long as index < array length, index++
            currentWindowSum += cardPoints[i]; // generate the zeroth value for currentWindowSum
        }

        greatestWindowSum = currentWindowSum; // set greatestWindowSum equal to currentWindowSum

        for (int i = 0; i < k; i++) { // for index = 0, as long as index < windowSize; index+++
            currentWindowSum = currentWindowSum - cardPoints[cardPoints.length - k + i] + cardPoints[i]; // currentWindowSum = currentWindowSum - tail array value approaching end of tail + head array value approaching end of head
            if (currentWindowSum > greatestWindowSum) { // set greatestWindowSum = currentWindowSum as appropriate
                greatestWindowSum = currentWindowSum;
            }
        }

        return greatestWindowSum;
    }


    public static void main(String ... args) {
        System.out.println(maxScore(new int[]{1, 2, 3, 4, 5, 6, 1}, 3));
    }
}