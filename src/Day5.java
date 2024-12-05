package src;

import java.util.*;

import static java.util.Collections.swap;

public class Day5 {

    public static void main(String[] args) {
        Web web = new Web("https://adventofcode.com/2024/day/5/input");
        String content[] = web.content().split("\n");

        Map<Integer, List<Integer>> rules = new HashMap<>();
        List<List<Integer>> updatedPages = new ArrayList<>();

        for (String row : content) {
            if (row.split("\\|").length == 2) {
                String first = row.split("\\|")[0];
                String second = row.split("\\|")[1];

                List<Integer> pages = rules.getOrDefault(Integer.valueOf(first), new ArrayList<>());
                pages.add(Integer.valueOf(second));

                rules.put(Integer.valueOf(first), pages);
            } else if (row.split(",").length > 1) {
                String[] split = row.split(",");
                updatedPages.add(Arrays.stream(split).map(Integer::valueOf).toList());
            }
        }

        part1(rules, updatedPages);
        part2(rules, updatedPages);
    }

    private static void part1(Map<Integer, List<Integer>> rules, List<List<Integer>> updatedPages) {
        int sum = getSum(rules, updatedPages);

        System.out.println(sum);
    }

    private static int getSum(Map<Integer, List<Integer>> rules, List<List<Integer>> updatedPages) {
        int sum = 0;
        List<List<Integer>> correctPages = new ArrayList<>();
        for (int i = 0; i < updatedPages.size(); i++) {
            boolean correct = true;
            List<Integer> integers = updatedPages.get(i);

            for (int j = 0; j < integers.size() - 1; j++) {
                Integer i1 = integers.get(j);
                List<Integer> rulesForOnePage = rules.getOrDefault(i1, new ArrayList<>());
                if (rulesForOnePage.isEmpty()) {
                    correct = false;
                    break;
                }
                for (int k = j + 1; k < integers.size(); k++) {
                    Integer i2 = integers.get(k);
                    if (!rulesForOnePage.contains(i2)) {
                        correct = false;
                        break;
                    }
                }
                if (!correct) {
                    break;
                }
            }
            if (correct) correctPages.add(integers);
        }

        for (List<Integer> pages : correctPages) {
            sum += pages.get(pages.size() / 2);
        }
        return sum;
    }

    private static void part2(Map<Integer, List<Integer>> rules, List<List<Integer>> updatedPages) {
        int sum = 0;

        List<List<Integer>> inCorrectPages = new ArrayList<>();

        for (int i = 0; i < updatedPages.size(); i++) {
            boolean correct = true;
            List<Integer> integers = updatedPages.get(i);

            for (int j = 0; j < integers.size() - 1; j++) {
                Integer i1 = integers.get(j);
                List<Integer> rulesForOnePage = rules.getOrDefault(i1, new ArrayList<>());
                if (rulesForOnePage.isEmpty()) {
                    correct = false;
                    break;
                }
                for (int k = j + 1; k < integers.size(); k++) {
                    Integer i2 = integers.get(k);
                    if (!rulesForOnePage.contains(i2)) {
                        correct = false;
                        break;
                    }
                }
                if (!correct) {
                    break;
                }
            }
            if (!correct) inCorrectPages.add(integers);
        }

        List<List<Integer>> correctPages = new ArrayList<>();

        for (int i = 0; i < inCorrectPages.size(); i++) {
            List<Integer> pages = new ArrayList<>(inCorrectPages.get(i));
            for (int j = 0; j < pages.size() - 1; j++) {
                Integer currentPage = pages.get(j);
                List<Integer> rulesForOnePage = rules.getOrDefault(currentPage, new ArrayList<>());
                boolean swapped = false;
                if (rulesForOnePage.isEmpty()) {
                    swap(pages, j, j + 1);
                    j--;
                    continue;
                }

                for (int k = j + 1; k < pages.size(); k++) {
                    Integer page = pages.get(k);
                    if (!rulesForOnePage.contains(page)) {
                        swap(pages, k, j);
                        swapped = true;
                        break;
                    }
                }
                if (swapped) {
                    j--;
                } else if (j == pages.size() / 2) {
                    //middle found is enough
                    correctPages.add(pages);
                    break;
                }
            }

        }


        for (List<Integer> pages : correctPages) {
            sum += pages.get(pages.size() / 2);
        }

        System.out.println(sum);
    }

}
