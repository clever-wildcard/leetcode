//package com.example.google;

import java.util.*;

/**
 * https://leetcode.com/problems/moving-average-from-data-stream/solution/
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 *
 * Example:
 *
 * MovingAverage m = new MovingAverage(3);
 * m.next(1) = 1
 * m.next(10) = (1 + 10) / 2
 * m.next(3) = (1 + 10 + 3) / 3
 * m.next(5) = (10 + 3 + 5) / 3
 */

// this works. it's the deque version.
//class MovingAverage {
//    Deque que = new ArrayDeque<Integer>();
//    int windowSize;
//    int count = 0;
//    int windowSum = 0;
//
//    /** Initialize your data structure here. */
//    public MovingAverage(int windowSize) {
//        this.windowSize = windowSize;
//    }
//
//    public double next(int value_to_append_to_queue) {
//
//        if (++count > windowSize) {
//            windowSum = windowSum - (int) que.getFirst();
//            que.removeFirst();
//        }
//
//        windowSum += value_to_append_to_queue;
//        que.add(value_to_append_to_queue);
//
//        return windowSum * 1.0 / Math.min(count, windowSize);
//
//    }
//}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */

// circular queue version. This also works.
public class AMovingAverage {
    int windowSize;
    int count = 0;
    List<Integer> windowsDontBendSpoons;
    double windowSum = 0;

    AMovingAverage(int windowSize) {
        this.windowSize = windowSize;
        windowsDontBendSpoons = new ArrayList<>(Collections.nCopies(windowSize, 0));
    }

    public double next(int newestValue) {
        windowSum = windowSum - windowsDontBendSpoons.get(windowSize - 1) + newestValue;
        count++;
        windowsDontBendSpoons.add(0, newestValue);
        return windowSum / Math.min(count, windowSize);
    }

    public static void main(String ... args) {
        AMovingAverage m = new AMovingAverage(3);
        System.out.println(m.next(1));
        System.out.println(m.next(10));
        System.out.println(m.next(3));
        System.out.println(m.next(5));
    }
}

