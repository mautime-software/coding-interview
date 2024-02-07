package com.mausoft.interview.problems.educative;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.*;
import java.util.function.Function;

/**
 * In this challenge, you are given a list of words written in an alien language, where the words are sorted lexicographically by the rules of this language.
 * Surprisingly, the aliens also use English lowercase letters, but possibly in a different order.
 *
 * Given a list of words written in the alien language, you have to return a string of unique letters sorted in the lexicographical order of the alien language
 * as derived from the list of words.
 *
 * If there’s no solution, that is, no valid lexicographical ordering, you can return an empty string.
 */
public class AlienDictionary {

    public static void main(String... args) {
        Function<Object[], Object> function = e -> alienDictionary((String[]) e[0]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static String alienDictionary(String[] words) {
        Map<Character, List<Character>> adjMatrix = buildAdjMatrix(words);
        Set<Character> visiting = new HashSet<>();
        Set<Character> visited = new HashSet<>();
        LinkedList<Character> stack = new LinkedList<>();
        for (char c : adjMatrix.keySet()) {
            if (!visited.contains(c)) {
                if (!traverseGraph(adjMatrix, c, visiting, visited, stack)) {
                    return "";
                }
            }
        }
        if (adjMatrix.keySet().size() == stack.size()) {
            return prepareDictionary(stack);
        }
        return "";
    }

    private static Map<Character, List<Character>> buildAdjMatrix(String[] words) {
        Map<Character, List<Character>> adjMatrix = new HashMap<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                adjMatrix.putIfAbsent(c, new ArrayList<>());
            }
        }
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            int j = 0;
            while (j < word1.length() || j < word2.length()) {
                char c1 = word1.charAt(j);
                char c2 = word2.charAt(j);
                if (c1 != c2) {
                    adjMatrix.get(c1).add(c2);
                    break;
                }
                j++;
            }
        }
        return adjMatrix;
    }

    private static boolean traverseGraph(Map<Character, List<Character>> adjMatrix, char c, Set<Character> visiting, Set<Character> visited, LinkedList<Character> stack) {
        if (visited.contains(c)) {
            return true;
        }
        if (visiting.contains(c)) {
            return false; //detect cycles
        }
        visiting.add(c);
        for (char next : adjMatrix.get(c)) {
            if (!traverseGraph(adjMatrix, next, visiting, visited, stack)) {
                return false; //Propagate the false result if the child DFS found a cycle
            }
        }
        visiting.remove(c);
        visited.add(c);
        stack.push(c);
        return true;
    }

    private static String prepareDictionary(LinkedList<Character> stack) {
        StringBuilder strBuilder = new StringBuilder(stack.size());
        while (!stack.isEmpty()) {
            strBuilder.append(stack.pop());
        }
        return strBuilder.toString();
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {new String[]{"mzosr", "mqov", "xxsvq", "xazv", "xazau", "xaqu", "suvzu", "suvxq", "suam", "suax", "rom", "rwx", "rwv"}},
                {new String[]{"passengers", "to", "the", "unknown"}},
                {new String[]{"wrt","wrf","er","ett","rftt"}}
        };
    }
}