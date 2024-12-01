package src;

import java.util.*;

public class Day1 {
    public static void main(String[] args) {
        Web web = new Web("https://adventofcode.com/2024/day/1/input");
        String content = web.content();

        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();
        for (String row : content.split("\\n")) {
            int left = Integer.parseInt(row.split(" {3}")[0]);
            int right = Integer.parseInt(row.split(" {3}")[1]);
            leftList.add(left);
            rightList.add(right);
        }

        totalDistance(leftList, rightList);

        similarityScore(leftList, rightList);
    }

    private static void totalDistance(List<Integer> leftList, List<Integer> rightList) {
        Collections.sort(leftList);
        Collections.sort(rightList);

        int totalDistance = 0;
        for (int i = 0; i < leftList.size(); i++) {
            totalDistance += Math.abs(leftList.get(i) - rightList.get(i));
        }

        System.out.println(totalDistance);
    }

    private static void similarityScore(List<Integer> leftList, List<Integer> rightList) {
        Map<Integer, Integer> rightMap = new HashMap<>();

        for (int i = 0; i < rightList.size(); i++) {
            Integer num = rightList.get(i);
            rightMap.put(num, rightMap.getOrDefault(num, 0) + 1);
        }

        int total = 0;
        for (Integer i : leftList) {
            total += i * rightMap.getOrDefault(i, 0);
        }
        System.out.println(total);
    }


}
