package com.mausoft.interview.problems;


import com.mausoft.interview.common.util.TestExecutor;

import java.util.LinkedList;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * https://www.khanacademy.org/computing/computer-science/algorithms/towers-of-hanoi/a/towers-of-hanoi
 *
 * 2^(n-1) - Total movements
 */
public class TowersOfHanoi {
    public static void main(String... args) {
        Consumer<Object[]> consumer = e -> hanoi((int) e[0], (LinkedList<Integer>) e[1], (LinkedList<Integer>) e[2], (LinkedList<Integer>) e[3]);
        TestExecutor.runTestCases(consumer, dataProvider());
    }

    public static void hanoi(int disks, LinkedList<Integer> src, LinkedList<Integer> aux, LinkedList<Integer> dest) {
        if (disks == 1) {
            dest.push(src.pop());
            return;
        }
        hanoi(disks - 1, src, dest, aux);
        dest.push(src.pop());
        hanoi(disks - 1, aux, src, dest);
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                buildHanoiTowers(3),
                buildHanoiTowers(4),
                buildHanoiTowers(10)
        };
    }

    private static Object[] buildHanoiTowers(int disks) {
        return new Object[]{disks,
                new LinkedList<>(IntStream.rangeClosed(1, disks).boxed().collect(Collectors.toList())),
                new LinkedList<>(),
                new LinkedList<>()};
    }
}
