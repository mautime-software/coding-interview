package com.mausoft.interview.problems.educative;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/word-ladder
 *
 * Given two words, src and dest, and a list, words, return the number of words in the shortest transformation sequence from src to dest.
 * If no such sequence can be formed, return 0.
 *
 * A transformation sequence is a sequence of words (src -> word1 -> word2 -> wordN, that has the following properties:
 *  - wordN = dest
 *  - Every pair of consecutive words differs by a single character.
 *  - All the words in the sequence are present in the words. The src does not need to be present in words.
 */
public class WordLadder {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> transformations((String) e[0], (String) e[1], (String[]) e[2]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static int transformations(String src, String dest, String[] words) {
        Set<String> wordSet = Arrays.stream(words).collect(Collectors.toSet());
        Queue<String> queue = new LinkedList<>();
        queue.offer(src);
        int transformations = 1;
        while(!queue.isEmpty()) {
            String curr = queue.poll();
            Set<String> adjWords = findAdjWords(curr, wordSet);
            if (adjWords.isEmpty()) {
                continue;
            }
            if (adjWords.contains(dest)) {
                return transformations + 1;
            }
            for (String adjWord : adjWords) {
                queue.offer(adjWord);
                wordSet.remove(adjWord);
            }
            transformations++;
        }
        return 0;
    }

    private static Set<String> findAdjWords(String src, Set<String> words) {
        Set<String> adjWords = new HashSet<>();
        for (int i = 0; i < src.length(); i++) {
            String alpha = "abcdefghijklmnopqrstuvwxyz";
            for (int j = 0; j < alpha.length(); j++) {
                char[] aux = src.toCharArray();
                aux[i] = alpha.charAt(j);
                String newSrc = new String(aux);
                if (words.contains(newSrc)) {
                    adjWords.add(newSrc);
                }
            }
        }
        return adjWords;
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {"dog", "cat", new String[]{"hog", "dot", "pot", "pop", "mop", "map", "cap", "cat"}},
                {"hit", "cog", new String[]{"hot", "dot", "lot", "log", "cog"}},
                {"hat", "log", new String[]{"hot", "not", "dot", "lot", "cog"}},
                {"dog", "cat", new String[]{"hog", "dot", "pot", "pop", "mop", "map", "cap", "cat"}},
                {"dog", "flog", new String[]{"hot", "dot", "lot", "log", "cog","com","cam","frog"}}
        };
    }
}