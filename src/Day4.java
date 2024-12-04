package src;

import java.util.ArrayList;
import java.util.List;

public class Day4 {
    final static String XMAS = "XMAS";

    public static void main(String[] args) {
        Web web = new Web("https://adventofcode.com/2024/day/4/input");
        String content[] = web.content().split("\n");

        part2(content);
    }

    private static void part1(String[] content) {
        int counter = 0;
        List<char[]> table = new ArrayList<>();
        for (String s : content) {
            table.add(s.toCharArray());
        }

        for (int j = 0; j < table.size(); j++) {
            char[] letters = table.get(j);
            for (int i = 0; i < letters.length; i++) {
                if (letters[i] != 'X') continue;
                //1.right
                if (i + 3 < letters.length &&
                        isEqualToXmas(letters[i], letters[i + 1], letters[i + 2], letters[i + 3])
                ) counter++;

                //2.left
                if (i - 3 >= 0 &&
                        isEqualToXmas(letters[i], letters[i - 1], letters[i - 2], letters[i - 3])
                ) counter++;

                //3.top
                if (j - 3 >= 0 &&
                        isEqualToXmas(table.get(j)[i], table.get(j - 1)[i], table.get(j - 2)[i], table.get(j - 3)[i])
                ) counter++;

                //4.bottom
                if (j + 3 < table.size() &&
                        isEqualToXmas(table.get(j)[i], table.get(j + 1)[i], table.get(j + 2)[i], table.get(j + 3)[i])
                ) counter++;

                //5.top-left
                if (j - 3 >= 0 && i - 3 >= 0 &&
                        isEqualToXmas(table.get(j)[i], table.get(j - 1)[i - 1], table.get(j - 2)[i - 2], table.get(j - 3)[i - 3])
                ) counter++;

                //6.top-right
                if (j - 3 >= 0 && i + 3 < letters.length &&
                        isEqualToXmas(table.get(j)[i], table.get(j - 1)[i + 1], table.get(j - 2)[i + 2], table.get(j - 3)[i + 3])
                ) counter++;

                //7.bottom-left
                if (j + 3 < table.size() && i - 3 >= 0 &&
                        isEqualToXmas(table.get(j)[i], table.get(j + 1)[i - 1], table.get(j + 2)[i - 2], table.get(j + 3)[i - 3])
                ) counter++;

                //8.bottom-right
                if (j + 3 < table.size() && i + 3 < letters.length &&
                        isEqualToXmas(table.get(j)[i], table.get(j + 1)[i + 1], table.get(j + 2)[i + 2], table.get(j + 3)[i + 3])
                ) counter++;

            }
        }
        System.out.println(counter);
    }

    static boolean isEqualToXmas(char char1, char char2, char char3, char char4) {
        StringBuilder sb = new StringBuilder();
        sb.append(char1)
                .append(char2)
                .append(char3)
                .append(char4);

        return sb.toString().equals(XMAS);
    }

    private static void part2(String[] content) {
        int counter = 0;
        List<char[]> table = new ArrayList<>();
        for (String s : content) {
            table.add(s.toCharArray());
        }

        for (int j = 1; j < table.size() - 1; j++) {
            char[] letters = table.get(j);
            for (int i = 1; i < letters.length - 1; i++) {
                if (letters[i] != 'A') continue;
                if (
                        ((table.get(j - 1)[i - 1] == 'M' && table.get(j + 1)[i + 1] == 'S') || (table.get(j - 1)[i - 1] == 'S' && table.get(j + 1)[i + 1] == 'M')) &&
                                ((table.get(j + 1)[i - 1] == 'M' && table.get(j - 1)[i + 1] == 'S') || (table.get(j + 1)[i - 1] == 'S' && table.get(j - 1)[i + 1] == 'M'))
                ) counter++;
            }
        }

        System.out.println(counter);
    }

}
