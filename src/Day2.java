package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day2 {
    public static void main(String[] args) {
        Web web = new Web("https://adventofcode.com/2024/day/2/input");
        String content = web.content();

        List<List<Integer>> list = new ArrayList<>();
        for (String row : content.split("\\n")) {
            String[] numbers = row.split(" ");
            List<Integer> nums = new ArrayList<>();
            for (String number : numbers) {
                nums.add(Integer.parseInt(number));
            }
            list.add(nums);
        }

//        List<List<Integer>> newlist = new ArrayList<>();
//
//        newlist.add(Arrays.asList(7, 6, 4, 2, 1));
//        newlist.add(Arrays.asList(1, 2, 7, 8, 9));
//        newlist.add(Arrays.asList(9, 7, 6, 2, 1));
//        newlist.add(Arrays.asList(1, 3, 2, 4, 5));
//        newlist.add(Arrays.asList(8, 6, 4, 4, 1));
//        newlist.add(Arrays.asList(1, 3, 6, 7, 9));

        part2(list);
    }

    private static void part1(List<List<Integer>> numbers) {
        int safeCounter = 0;
        for (List<Integer> row : numbers) {
            System.out.print(row);
            boolean isSafe = isRowSafe(row);
            if (isSafe) {
                safeCounter++;
            }
        }
        System.out.println(safeCounter);
    }

    private static boolean isRowSafe(List<Integer> row) {
        boolean isSafe = true;
        int prevDifference = 0;
        for (int i = 0; i < row.size() - 1; i++) {
            int currentValue = row.get(i);
            int nextValue = row.get(i + 1);
            if (prevDifference < 0 && nextValue - currentValue >= 0) {
                isSafe = false;
                break;
            }
            if (prevDifference > 0 && nextValue - currentValue <= 0) {
                isSafe = false;
                break;
            }
            if (currentValue - nextValue == 0 || Math.abs(currentValue - nextValue) > 3) {
                isSafe = false;
                break;
            }
            prevDifference = nextValue - currentValue;
        }
        return isSafe;
    }

    private static void part2(List<List<Integer>> numbers) {
        int safeCounter = 0;
        for (List<Integer> row : numbers) {
            boolean isSafe = isRowSafe(row);
            if (isSafe) {
                safeCounter++;
            } else {
                for (int i = 0; i < row.size(); i++) {
                    List<Integer> newRow = new ArrayList<>();
                    for (int j = 0; j < row.size(); j++) {
                        if (i != j)
                            newRow.add(row.get(j));
                    }
                    boolean isNewRowSafe = isRowSafe(newRow);
                    if (isNewRowSafe) {
                        safeCounter++;
                        break;
                    }
                }
            }
        }
        System.out.println(safeCounter);
    }
}
