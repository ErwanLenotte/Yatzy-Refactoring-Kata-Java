import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Yatzy {
    private static final int DEFAULT_SCORE = 0;

    public static int score(int d1, int d2, int d3, int d4, int d5, YatsyTypeEnum yatsyTypeEnum) {
        Objects.requireNonNull(yatsyTypeEnum, "yatsyTypeEnum can't be null");
        checkDicesBoundaries(d1, d2, d3, d4, d5);

        int score = DEFAULT_SCORE;
        switch (yatsyTypeEnum) {
            case ONES:
                score = scoreSumDices(d2, d1, d3, d4, d5, YatsySumScoreEnum.ONES);
                break;
            case TWOS:
                score = scoreSumDices(d2, d1, d3, d4, d5, YatsySumScoreEnum.TWOS);
                break;
            case THREES:
                score = scoreSumDices(d2, d1, d3, d4, d5, YatsySumScoreEnum.THREES);
                break;
            case FOURS:
                score = scoreSumDices(d2, d1, d3, d4, d5, YatsySumScoreEnum.FOURS);
                break;
            case FIVES:
                score = scoreSumDices(d2, d1, d3, d4, d5, YatsySumScoreEnum.FIVES);
                break;
            case SIXES:
                score = scoreSumDices(d2, d1, d3, d4, d5, YatsySumScoreEnum.SIXES);
                break;
            case CHANCE:
                score = scoreSumDices(d2, d1, d3, d4, d5, YatsySumScoreEnum.CHANCE);
                break;
            case PAIR:
                score = scoreTallies(d1, d2, d3, d4, d5, YatsyTalliesScoreEnum.PAIR);
                break;
            case TWO_PAIRS:
                score = scoreTallies(d1, d2, d3, d4, d5, YatsyTalliesScoreEnum.TWO_PAIRS);
                break;
            case THREE_OF_A_KIND:
                score = scoreTallies(d1, d2, d3, d4, d5, YatsyTalliesScoreEnum.THREE_OF_A_KIND);
                break;
            case FOUR_OF_A_KIND:
                score = scoreTallies(d1, d2, d3, d4, d5, YatsyTalliesScoreEnum.FOUR_OF_A_KIND);
                break;
            case SMALL_STRAIGHT:
                score = scoreStraight(d1, d2, d3, d4, d5, YastySraightScoreEnum.SMALL_STRAIGHT);
                break;
            case LARGE_STRAIGHT:
                score = scoreStraight(d1, d2, d3, d4, d5, YastySraightScoreEnum.LARGE_STRAIGHT);
                break;
            case YATSY:
                score = scoreTallies(d1, d2, d3, d4, d5, YatsyTalliesScoreEnum.YATSY);
                break;
            case FULL_HOUSE:
                score = scoreFullHouse(d1, d2, d3, d4, d5);
        }

        return score;
    }

    private static void checkDicesBoundaries(int d1, int d2, int d3, int d4, int d5) {
        List<Integer> dices = Arrays.asList(d1, d2, d3, d4, d5);

        for (int i = 0; i < dices.size(); i++)
            if (dices.get(i) < 0 || dices.get(i) > 6)
                throw new IllegalArgumentException(String.format("d%d must be between 1 and 6", (i + 1)));
    }

    private static int scoreStraight(int d1, int d2, int d3, int d4, int d5, YastySraightScoreEnum yastySraightScoreEnum) {
        return Stream.of(countTallies(d1, d2, d3, d4, d5)).mapToInt(tallies -> calculateStraightFromTallies(tallies, yastySraightScoreEnum)).findFirst().orElse(DEFAULT_SCORE);
    }

    private static int scoreTallies(int d1, int d2, int d3, int d4, int d5, YatsyTalliesScoreEnum yatsyTalliesScoreEnum) {
        return Stream.of(countTallies(d1, d2, d3, d4, d5)).mapToInt(tallies -> calculateScoreFromTallies(tallies, yatsyTalliesScoreEnum)).findFirst().orElse(DEFAULT_SCORE);
    }

    private static int scoreFullHouse(int d1, int d2, int d3, int d4, int d5) {
        var pairTotal = scoreTallies(d2, d1, d3, d4, d5, YatsyTalliesScoreEnum.PAIR);
        var tripletTotal = scoreTallies(d1, d2, d3, d4, d5, YatsyTalliesScoreEnum.THREE_OF_A_KIND);

        if (pairTotal != DEFAULT_SCORE && tripletTotal != DEFAULT_SCORE)
            return pairTotal + tripletTotal;

        return DEFAULT_SCORE;
    }

    private static Integer scoreSumDices(int d2, int d1, int d3, int d4, int d5, YatsySumScoreEnum scoreEnum) {
        return Stream.of(d1, d2, d3, d4, d5).filter(scoreEnum.getPredicate()).reduce(DEFAULT_SCORE, Integer::sum);
    }

    private static Map<Integer, Long> countTallies(int d1, int d2, int d3, int d4, int d5) {
        return Stream.of(d1, d2, d3, d4, d5).collect(Collectors.groupingBy(
            Function.identity(),
            Collectors.counting()));
    }

    private static Integer calculateScoreFromTallies(Map<Integer, Long> tallies, YatsyTalliesScoreEnum scoreEnum) {
        return tallies.entrySet().stream()
            .filter(scoreEnum.getTalliesPredicate())
            .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
            .limit(scoreEnum.getNbCount())
            .map(Map.Entry::getKey)
            .map(scoreEnum.getCalculeScoreFunction())
            .reduce(DEFAULT_SCORE, Integer::sum);
    }

    private static int calculateStraightFromTallies(Map<Integer, Long> tallies, YastySraightScoreEnum scoreEnum) {
        if (tallies.entrySet().stream()
            .filter(scoreEnum.getStraightPredicate())
            .filter(entry -> entry.getValue() == YastySraightScoreEnum.NB_TALLIES_STRAIGHT)
            .count() == YastySraightScoreEnum.NB_DICE_STRAIGHT)
            return scoreEnum.getStraightScore();
        return DEFAULT_SCORE;
    }
}