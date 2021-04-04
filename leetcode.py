"""
Command F for whatever you're looking for.
"""

# Leetcode explore section


def find_max_consecutive_ones(nums):
    """
    https://leetcode.com/problems/max-consecutive-ones/solution/

    Java equivalent in leetcode.java under findMaxConsecutiveOnes.
    """
    count = 0
    greatest_count = count
    for number in nums:
        if number == 1:
            count += 1
        else:
            count = 0
        if count > greatest_count:
            greatest_count = count
    return greatest_count


def find_numbers(nums):
    """
    https://leetcode.com/explore/learn/card/fun-with-arrays/521/introduction/3237/

    Input: nums = [555,901,482,1771]
    Output: 1
    Explanation:
    Only 1771 contains an even number of digits.

    Java equivalent in leetcode.java under findNumbers.
    """
    count = 0
    for number in nums:
        if len(str(number)) % 2 == 0:
            count += 1
    return count


def sorted_squares(nums):
    """
    https://leetcode.com/explore/learn/card/fun-with-arrays/521/introduction/3240/

    Input: nums = [-4,-1,0,3,10]
    Output: [0,1,9,16,100]
    Explanation: After squaring, the array becomes [16,1,0,9,100].
    After sorting, it becomes [0,1,9,16,100].

    Java equivalent in leetcode.java under sortedSquares.
    """
    for index in range(len(nums)):
        nums[index] = nums[index] * nums[index]
    return sorted(nums)


def duplicate_zeros(arr):
    """
    https://leetcode.com/explore/learn/card/fun-with-arrays/525/inserting-items-into-an-array/3245/
    https://leetcode.com/problems/duplicate-zeros/solution/

    Input: [1,0,2,3,0,4,5,0]
    Output: null
    Explanation: After calling your function, the input array is modified to: [1,0,0,2,3,0,0,4]
    Do not return anything, modify arr in-place instead.

    Java equivalent in leetcode.java under duplicateZeros.
    """
    arr_copy = arr
    index = 0
    while index < (len(arr_copy)):
        if index != len(arr_copy) -1 and arr_copy[index] == 0:
            arr.pop(-1)
            index += 1
            arr.insert(index, 0)
        index += 1

# Random section


def two_sum(nums, target):
    """
    https://leetcode.com/problems/two-sum/

    Ex:
    Input: nums = [2,7,11,15], target = 9
    Output: [0,1]
    Output: Because nums[0] + nums[1] == 9, we return [0, 1].

    Java equivalent in leetcode.java under twoSum.
    """
    my_dictionary = {}
    for index, value in enumerate(nums):
        if target - value in my_dictionary.keys():
            return [my_dictionary[target - value], index]
        my_dictionary[nums[index]] = index


answer = two_sum([11, 15, 2, 7], 9)
for number in answer:
    print(number)

# Google section


class MovingAverage:
    """
    https://leetcode.com/problems/moving-average-from-data-stream/solution/
    * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
     *
     * Example:
     *
     * MovingAverage m = new MovingAverage(3);
     * m.next(1) = 1
     * m.next(10) = (1 + 10) / 2
     * m.next(3) = (1 + 10 + 3) / 3
     * m.next(5) = (10 + 3 + 5) / 3

     # Snake eating it's tail. ouroboros. works

     """
    import time

    def __init__(self, window_size):
        self.window_size = window_size
        self.array = [0] * self.window_size
        self.window_sum = 0
        self.count = 0

    def next(self, newest_value):
        self.window_sum = self.window_sum - self.array[self.window_size - 1] + newest_value
        self.array.insert(0, newest_value)
        self.count += 1
        return self.window_sum / min(self.count, self.window_size)

    time.clock_gettime()


def test_moving_average():
    m = MovingAverage(3)
    print(m.next(1))
    print(m.next(10))
    print(m.next(3))
    print(m.next(5))


test_moving_average()

# Next question.


class Logger:
    """https://leetcode.com/problems/logger-rate-limiter/"""

    def __init__(self):
        self.message_timestamp_dictionary = {}

    def should_print_message(self, timestamp: int, message: str) -> bool:

        if message in self.message_timestamp_dictionary.keys():
            if timestamp - self.message_timestamp_dictionary[message] < 10:
                return False
        self.message_timestamp_dictionary[message] = timestamp

        return True


def test_logger_rate_limiter():
    logger = Logger()
    print(logger.should_print_message(1, "foo"))
    print(logger.should_print_message(2, "bar"))
    print(logger.should_print_message(3, "foo"))
    print(logger.should_print_message(8, "bar"))
    print(logger.should_print_message(10, "foo"))
    print(logger.should_print_message(11, "foo"))


test_logger_rate_limiter()


# Next question.


def max_score(card_points, k):
    """
    https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/
    In one step, you can take one card from the beginning or from the end of the row. You have to take exactly k cards.
    Your score is the sum of the points of the cards you have taken.
    Given the integer array cardPoints and the integer k, return the maximum score you can obtain.

    Input: cardPoints = [1,2,3,4,5,6,1], k = 3
    Output: 12
    Explanation: After the first step, your score will always be 1. However, choosing the rightmost card first will
    maximize your total score. The optimal strategy is to take the three cards on the right, giving a final score of
    1 + 6 + 5 = 12.

    """
    greatest_score = 0
    newest_score = 0

    for card_point in card_points[len(card_points) - k:]:
        newest_score += card_point
    greatest_score = newest_score

    for index in range(0, k):
        newest_score = newest_score - card_points[len(card_points) - k + index] + card_points[index]
        if newest_score > greatest_score:
            greatest_score = newest_score
    return greatest_score


def test_solution():
    print(max_score([1, 2, 3, 4, 5, 6, 1], 3))

# Next Question


def longest_subarray(nums, limit):
    """
    https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/submissions/
    """
    longest_nonempty_subarray = 0

    # edge case me
    if len(set(nums)) == 1:
        return len(nums)

    # regular case me
    for index, value in enumerate(nums):
        remaining_values = index
        while remaining_values < len(nums):
            subset = nums[index:remaining_values + 1]
            subset_length = len(subset)
            if abs(max(subset) - min(subset)) <= limit:
                if subset_length > longest_nonempty_subarray:
                    longest_nonempty_subarray = subset_length
                remaining_values += 1
            else:
                remaining_values = len(nums)
    return longest_nonempty_subarray

# Next Question


def swap_these_two_values(index_i, index_j):
    """
    https://leetcode.com/articles/two-pointer-technique/
    """
    return index_j, index_i


def reverse_data_structure(given_data_structure):
    index_i = 0
    index_j = len(given_data_structure) - 1
    while index_j < index_j:
        given_data_structure[index_i], given_data_structure[index_j] = swap_these_two_values(index_i, index_j)
        index_i += 1
        index_j -= 1
    return given_data_structure
