package com.mausoft.interview.problems.educative;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/two-sum/
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * You can return the answer in any order.
 *
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 *
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 *
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 */
public class TwoSum {
    public static void main(String... args) {
        int[] nums = {3,3};
        int target = 6;
        String result = Arrays.stream(twoSum(nums, target)).mapToObj(Integer::toString).collect(Collectors.joining(", "));
        System.out.printf("[%s]", result);
    }
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> diffMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            if (diffMap.containsKey(diff)) {
                return new int[]{diffMap.get(diff), i};
            }
            diffMap.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }
}