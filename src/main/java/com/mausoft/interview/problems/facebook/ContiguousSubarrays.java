package com.mausoft.interview.problems.facebook;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.LinkedList;
import java.util.Stack;
import java.util.function.Function;

/**
 * You are given an array arr of N integers. For each index i, you are required to determine the number of contiguous subarrays that fulfills the following conditions:
 * The value at index i must be the maximum element in the contiguous subarrays, and
 * These contiguous subarrays must either start from or end on index i.
 * Signature
 * int[] countSubarrays(int[] arr)
 * Input
 * Array arr is a non-empty list of unique integers that range between 1 to 1,000,000,000
 * Size N is between 1 and 1,000,000
 * Output
 * An array where each index i contains an integer denoting the maximum number of contiguous subarrays of arr[i]
 *
 * Example:
 * arr = [3, 4, 1, 6, 2]
 * output = [1, 3, 1, 5, 1]
 * Explanation:
 * For index 0 - [3] is the only contiguous subarray that starts (or ends) with 3, and the maximum value in this subarray is 3.
 * For index 1 - [4], [3, 4], [4, 1]
 * For index 2 - [1]
 * For index 3 - [6], [6, 2], [1, 6], [4, 1, 6], [3, 4, 1, 6]
 * For index 4 - [2]
 * So, the answer for the above input is [1, 3, 1, 5, 1]
 */
public class ContiguousSubarrays {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> contiguousSubarraysOptimized((int[]) e[0]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    /**
     * Stack based O(n) solution in Java.
     *
     * Maintain a stack such that it always contains the index for the last maximum encountered.
     *  - If the next element is greater than the arr[stack.peek()] then pop the top of the stack till we find an equal or greater element.
     *  - If stack is empty then it means that the current element is the maximum of all and hence there are (current index + 1) possible arrays meeting the criteria.
     *  - If stack is not empty, then (current index - stack top) will be possible arrays for the index position
     * Repeat the same steps from end of the array to get the final solution.
     */
    public static int[] contiguousSubarraysOptimized(int[] nums) {
        LinkedList<Integer> stack = new LinkedList<>();
        int[] left = new int[nums.length];
        //Calculate max contiguous subarrays to the left
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                left[i] = i + 1;
            } else {
                left[i] = i - stack.peek();
            }
            stack.push(i);
        }
        stack.clear();
        int[] right = new int[nums.length];
        //Calculate max contiguous subarrays to the right
        for (int i = nums.length - 1; i >= 0; i--) {
            while(!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                right[i] = nums.length - i - 1;
            } else {
                right[i] = stack.peek() - i - 1;
            }
            stack.push(i);
        }
        int[] results = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            results[i] = left[i] + right[i];
        }
        return results;
    }

    public static int[] contiguousSubarrays(int[] nums) { //On^2
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int count = 1;
            int l = i - 1;
            while (l >= 0 && nums[i] >= nums[l]) {
                count++;
                l--;
            }
            int r = i + 1;
            while(r < nums.length && nums[i] >= nums[r]) {
                count ++;
                r++;
            }
            result[i] = count;
        }
        return result;
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {new int[]{3, 4, 1, 6, 2}},
                {new int[]{2, 4, 7, 1, 5, 3}}
        };
    }
}