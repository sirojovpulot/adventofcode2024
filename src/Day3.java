package src;

import java.util.Iterator;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    final static String doIns = "do()";
    final static String dontIns = "don't()";

    public static void main(String[] args) {
        Web web = new Web("https://adventofcode.com/2024/day/3/input");
        String content = web.content();
        Pattern p = Pattern.compile("mul\\(\\d*,\\d*\\)");
        Matcher m = p.matcher(content);
        Iterator<MatchResult> iterator = m.results().iterator();

//        part1(iterator);
        part2(iterator, content);
    }

    private static void part1(Iterator<MatchResult> iterator) {
        int res = 0;
        while (iterator.hasNext()) {
            MatchResult next = iterator.next();
            Result result = getResult(next);
            res += result.num1() * result.num2();
        }
        System.out.println(res);
    }

    private static void part2(Iterator<MatchResult> iterator, String content) {
        int res = 0;
        while (iterator.hasNext()) {
            MatchResult next = iterator.next();
            if (cantContinue(content, next.start())) {
                continue;
            }
            Result result = getResult(next);
            res += result.num1() * result.num2();
        }
        System.out.println(res);
    }

    private static Result getResult(MatchResult next) {
        String group = next.group(0);
        String[] numbers = group.substring(4, group.length() - 1).split(",");
        int num1 = Integer.parseInt(numbers[0]);
        int num2 = Integer.parseInt(numbers[1]);
        return new Result(num1, num2);
    }

    private record Result(int num1, int num2) {
    }

    private static boolean cantContinue(String content, int start) {
        String substring = content.substring(0, start);
        int dontIndex = substring.lastIndexOf(dontIns);
        int doIndex = substring.lastIndexOf(doIns);
        return dontIndex > doIndex;
    }
}
