package kata.yatzy;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static kata.yatzy.YatzyTypeEnum.*;

public class Yatzy {
    private static final int DEFAULT_SCORE = 0;


    private static final  Map<YatzyTypeEnum, YatzySumScoreEnum> scoreSumDiceMap = Map.of(
        ONES, YatzySumScoreEnum.ONES,
        TWOS, YatzySumScoreEnum.TWOS,
        THREES, YatzySumScoreEnum.THREES,
        FOURS, YatzySumScoreEnum.FOURS,
        FIVES, YatzySumScoreEnum.FIVES,
        SIXES, YatzySumScoreEnum.SIXES,
        CHANCE, YatzySumScoreEnum.CHANCE
    );

    private static final  Map<YatzyTypeEnum, YatzyTalliesScoreEnum> scoreTalliesMap = Map.of(
        PAIR, YatzyTalliesScoreEnum.PAIR,
        TWO_PAIRS, YatzyTalliesScoreEnum.TWO_PAIRS,
        THREE_OF_A_KIND, YatzyTalliesScoreEnum.THREE_OF_A_KIND,
        FOUR_OF_A_KIND, YatzyTalliesScoreEnum.FOUR_OF_A_KIND,
        YATZY, YatzyTalliesScoreEnum.YATZY
    );

    private static final  Map<YatzyTypeEnum, YaztySraightScoreEnum> scoreStraightMap = Map.of(
        SMALL_STRAIGHT, YaztySraightScoreEnum.SMALL_STRAIGHT,
        LARGE_STRAIGHT, YaztySraightScoreEnum.LARGE_STRAIGHT
    );



    private Yatzy() {
    }

    public static int score(int d1, int d2, int d3, int d4, int d5, YatzyTypeEnum yatzyTypeEnum) {
        Objects.requireNonNull(yatzyTypeEnum, "yatzyTypeEnum can't be null");
        checkDicesBoundaries(d1, d2, d3, d4, d5);

        return
            switch (yatzyTypeEnum) {
                case ONES, TWOS, THREES, FOURS, FIVES, SIXES, CHANCE ->
                    scoreSumDices(d2, d1, d3, d4, d5, scoreSumDiceMap.get(yatzyTypeEnum));
                case PAIR, TWO_PAIRS, THREE_OF_A_KIND, FOUR_OF_A_KIND, YATZY ->
                    scoreTallies(d1, d2, d3, d4, d5, scoreTalliesMap.get(yatzyTypeEnum));
                case SMALL_STRAIGHT, LARGE_STRAIGHT ->
                    scoreStraight(d1, d2, d3, d4, d5, scoreStraightMap.get(yatzyTypeEnum));
                case FULL_HOUSE -> scoreFullHouse(d1, d2, d3, d4, d5);
            };

    }

    private static void checkDicesBoundaries(int d1, int d2, int d3, int d4, int d5) {
        List<Integer> dices = Arrays.asList(d1, d2, d3, d4, d5);

        for (int i = 0; i < dices.size(); i++)
            if (dices.get(i) < 0 || dices.get(i) > 6)
                throw new IllegalArgumentException(String.format("d%d must be between 1 and 6", (i + 1)));
    }

    private static int scoreStraight(int d1, int d2, int d3, int d4, int d5, YaztySraightScoreEnum yastySraightScoreEnum) {
        return Stream.of(countTallies(d1, d2, d3, d4, d5))
            .mapToInt(tallies -> calculateStraightFromTallies(tallies, yastySraightScoreEnum))
            .findFirst()
            .orElse(DEFAULT_SCORE);
    }

    private static int scoreTallies(int d1, int d2, int d3, int d4, int d5, YatzyTalliesScoreEnum yatzyTalliesScoreEnum) {
        return Stream.of(countTallies(d1, d2, d3, d4, d5))
            .mapToInt(tallies -> calculateScoreFromTallies(tallies, yatzyTalliesScoreEnum))
            .findFirst()
            .orElse(DEFAULT_SCORE);
    }

    private static int scoreFullHouse(int d1, int d2, int d3, int d4, int d5) {
        var pairTotal = scoreTallies(d2, d1, d3, d4, d5, YatzyTalliesScoreEnum.PAIR);
        var tripletTotal = scoreTallies(d1, d2, d3, d4, d5, YatzyTalliesScoreEnum.THREE_OF_A_KIND);

        if (pairTotal != DEFAULT_SCORE && tripletTotal != DEFAULT_SCORE)
            return pairTotal + tripletTotal;

        return DEFAULT_SCORE;
    }

    private static Integer scoreSumDices(int d2, int d1, int d3, int d4, int d5, YatzySumScoreEnum scoreEnum) {
        return Stream.of(d1, d2, d3, d4, d5)
            .filter(scoreEnum.getPredicate())
            .reduce(DEFAULT_SCORE, Integer::sum);
    }

    private static Map<Integer, Long> countTallies(int d1, int d2, int d3, int d4, int d5) {
        return Stream.of(d1, d2, d3, d4, d5).collect(Collectors.groupingBy(
            Function.identity(),
            Collectors.counting()));
    }

    private static Integer calculateScoreFromTallies(Map<Integer, Long> tallies, YatzyTalliesScoreEnum scoreEnum) {
        return tallies.entrySet().stream()
            .filter(scoreEnum.getTalliesPredicate())
            .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
            .limit(scoreEnum.getNbCount())
            .map(Map.Entry::getKey)
            .map(scoreEnum.getCalculeScoreFunction())
            .reduce(DEFAULT_SCORE, Integer::sum);
    }

    private static int calculateStraightFromTallies(Map<Integer, Long> tallies, YaztySraightScoreEnum scoreEnum) {
        if (tallies.entrySet().stream()
            .filter(scoreEnum.getStraightPredicate())
            .filter(entry -> entry.getValue() == YaztySraightScoreEnum.NB_TALLIES_STRAIGHT)
            .count() == YaztySraightScoreEnum.NB_DICE_STRAIGHT)
            return scoreEnum.getStraightScore();
        return DEFAULT_SCORE;
    }
}