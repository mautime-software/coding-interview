package com.mausoft.interview.problems.educative;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.*;
import java.util.function.Function;

public class FindAllPossibleRecipesFromSupplies {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> findAllRecipes((String[])e[0], (List<List<String>>) e[1], (String[]) e[2]);
        TestExecutor.runTestCases(function, dataProvider());
    }
    public static List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Map<String, List<String>> adjMatrix = buildAdjMatrix(recipes, ingredients);
        Map<String, Integer> indegreeMatrix = buildIndegreeMatrix(recipes, ingredients);
        Deque<String> queue = new ArrayDeque<>();
        for (int i = 0; i < supplies.length; i++) {
            queue.offer(supplies[i]);
        }
        List<String> results = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String ingredient = queue.poll();
                for (String recipe : adjMatrix.getOrDefault(ingredient, Collections.emptyList())) {
                    indegreeMatrix.put(recipe, indegreeMatrix.get(recipe) - 1);
                    if (indegreeMatrix.get(recipe) == 0) { // all ingredients available for recipe
                        results.add(recipe);
                        queue.offer(recipe); //offer recipe as ingredient, so other recipes dependent of this recipe as ingredient can be made
                    }
                }
            }
        }
        return results;
    }

    private static Map<String, List<String>> buildAdjMatrix(String[] recipes, List<List<String>> ingredients) {
        Map<String, List<String>> adjMatrix = new HashMap<>();
        for (int i = 0; i < recipes.length; i++) {
            for (String ingredient : ingredients.get(i)) {
                adjMatrix.computeIfAbsent(ingredient, k -> new ArrayList<>()).add(recipes[i]);
            }
        }
        return adjMatrix;
    }

    private static Map<String, Integer> buildIndegreeMatrix(String[] recipes, List<List<String>> ingredients) {
        Map<String, Integer> indegreeMatrix = new HashMap<>();
        for (int i = 0; i < recipes.length; i++) {
            indegreeMatrix.put(recipes[i], ingredients.get(0).size());
        }
        return indegreeMatrix;
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {new String[]{"bread","sandwich","burger"}, List.of(List.of("yeast","flour"),List.of("bread","meat"),List.of("sandwich","meat","bread")), new String[]{"yeast","flour","meat"}},
                {new String[]{"bread", "sandwich"}, List.of(List.of("yeast", "flour"), List.of("bread", "meat")) , new String[]{"yeast", "flour", "meat"}},
                {new String[]{"bread"} , List.of(List.of("yeast", "flour")), new String[]{"yeast", "flour", "corn"}},
                {new String[]{"pasta", "egg", "chicken"} , List.of(List.of("yeast", "flour"), List.of("pasta", "meat"), List.of("egg", "meat", "pasta")) , new String[]{"yeast", "flour", "meat"}}
        };
    }
}