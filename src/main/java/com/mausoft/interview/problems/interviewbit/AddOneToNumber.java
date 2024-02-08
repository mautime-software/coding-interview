package com.mausoft.interview.problems.interviewbit;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

/**
 * https://www.interviewbit.com/problems/add-one-to-number/
 *
 * Given a non-negative number represented as an array of digits, add 1 to the number ( increment the number represented by the digits ).
 *
 * The digits are stored such that the most significant digit is at the head of the list.
 */
public class AddOneToNumber {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> addOne((List<Integer>)e[0]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static List<Integer> addOne(List<Integer> nums) {
        int num = toInt(nums);
        return toArray(num + 1);
    }

    private static int toInt(List<Integer> nums) {
        int num = 0;
        int multiplier = 1;
        for (int i = nums.size() - 1; i >= 0; i--) {
            num += nums.get(i) * multiplier;
            multiplier *= 10;
        }
        return num;
    }

    private static List<Integer> toArray(int num) {
        Deque<Integer> stack = new LinkedList<>();
        while (true) {
            int mod = num % 10;
            stack.push(mod);
            num = num / 10;
            if (num == 0) {
                break;
            }
        }
        return stack.stream().toList();
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {List.of(1, 2, 3)},
                {List.of(9, 9, 9)},
                {List.of(-1)},
                {List.of(-2)},
                {List.of(-1, 0, 0, 0)}
        };
    }
}