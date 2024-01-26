package com.mausoft.interview.problems;

import com.mausoft.interview.common.util.Interval;
import com.mausoft.interview.common.util.TestExecutor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/meeting-rooms-ii
 *
 * We are given an input array of meeting time intervals, intervals, where each interval has a start time and an end time.
 * Your task is to find the minimum number of meeting rooms required to hold these meetings.
 */
public class MeetingRoomsII {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> calculateMeetingRooms((List<Interval>) e[0]);
        TestExecutor.runTestCases(function, dataProvider());
    }
    public static int calculateMeetingRooms(List<Interval> meetings) {
        meetings.sort(Comparator.comparingInt(Interval::getStart));
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int  i = 0; i < meetings.size(); i++) {
            Interval meeting = meetings.get(i);
            if (!minHeap.isEmpty() && meeting.getStart() >= minHeap.peek()) {
                minHeap.poll();
            }
            minHeap.offer(meeting.getEnd());
        }
        return minHeap.size();
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {new ArrayList<>(List.of(Interval.from(1, 3), Interval.from(1, 2), Interval.from(2, 4), Interval.from(6, 7), Interval.from(4, 5)))},
                {new ArrayList<>(List.of(Interval.from(2, 8), Interval.from(3, 4), Interval.from(3, 9), Interval.from(5, 11), Interval.from(8, 20), Interval.from(11, 15)))},
                {new ArrayList<>(List.of(Interval.from(1, 3), Interval.from(2, 6), Interval.from(8, 10), Interval.from(9, 15), Interval.from(12, 14)))},
                {new ArrayList<>(List.of(Interval.from(1, 2), Interval.from(4, 6), Interval.from(3, 4), Interval.from(7, 8)))},
                {new ArrayList<>(List.of(Interval.from(1, 7), Interval.from(2, 6), Interval.from(3, 7), Interval.from(4, 8), Interval.from(5, 8)))},
                {new ArrayList<>(List.of(Interval.from(1, 2), Interval.from(1, 2), Interval.from(1, 2)))}
        };
    }
}