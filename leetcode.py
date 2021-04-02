"""
Command F for whatever you're looking for.
"""


def two_sum(nums, target):
    """
    https://leetcode.com/problems/two-sum/

    Ex:
    Input: nums = [2,7,11,15], target = 9
    Output: [0,1]
    Output: Because nums[0] + nums[1] == 9, we return [0, 1].
    """
    my_dictionary = {}
    for index, value in enumerate(nums):
        if target - value in my_dictionary.keys():
            return [my_dictionary[target - value], index]
        my_dictionary[nums[index]] = index


answer = two_sum([11, 15, 2, 7], 9)
for number in answer:
    print(number)


"""
java equivalent:

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int index = 0; index < nums.length; index++) {
            if (map.containsKey(target - nums[index])) {
                return new int[]{index, map.get(target - nums[index])};
            }
            map.put(nums[index], index);
        }
        return new int[2];
"""
