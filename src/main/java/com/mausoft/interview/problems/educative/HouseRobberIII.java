package com.mausoft.interview.problems.educative;

import com.mausoft.interview.common.util.TestExecutor;
import com.mausoft.interview.common.util.TreeNode;

import java.util.function.Function;

import static com.mausoft.interview.common.util.TreeNode.from;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/solution-house-robber-iii
 *
 * A thief has discovered a new neighborhood to target, where the houses can be represented as nodes in a binary tree.
 * The money in the house is the data of the respective node. The thief can enter the neighborhood from a house represented as root of the binary tree.
 * Each house has only one parent house. The thief knows that if he robs two houses that are directly connected, the police will be notified.
 *
 * The thief wants to know the maximum amount of money he can steal from the houses without getting caught by the police.
 * The thief needs your help determining the maximum amount of money he can rob without alerting the police.
 */
public class HouseRobberIII {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> heist((TreeNode<Integer>) e[0]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static int heist(TreeNode<Integer> house) {
        int[] results = rob(house);
        return Math.max(results[0], results[1]);
    }

    private static int[] rob(TreeNode<Integer> house) {
        if (house == null) {
            return new int[2];
        }
        int[] left = rob(house.getLeft());
        int[] right = rob(house.getRight());
        int includeHouse = house.getValue() + left[1] + right[1]; //include house value + non-included house values
        int excludeHouse = Math.max(left[1], left[0]) + Math.max(right[1], right[0]); //exclude house, sum of max amount robbed from its children
        return new int[]{includeHouse, excludeHouse};
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {from(10, from(9, from(15), from(7)), from(20))},
                {from(7, from(9, from(15), from(20)), from(10))},
                {from(8, from(2, from(1), from(4)), from(17, from(19), from(5)))},
                {from(7, from(3, from(1), from(3)), from(4))},
                {from(9, from(5, from(1), from(3)), from(7))},
                {from(9, from(7, null, from(1, from(8, null, from(12)), from(10))), null)}
        };
    }
}