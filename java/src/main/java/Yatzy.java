import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Yatzy {
    private static final int DEFAULT_SCORE = 0;
    private static final int PAIR_VALUE = 2;

    public static int chance(int d1, int d2, int d3, int d4, int d5) {
        return sumDiceWithFilter(d2, d1, d3, d4, d5, YatsySumScoreEnum.CHANCE);
    }
    
    public static int ones(int d1, int d2, int d3, int d4, int d5) {
        return sumDiceWithFilter(d2, d1, d3, d4, d5, YatsySumScoreEnum.ONES);
    }
    
    public static int twos(int d1, int d2, int d3, int d4, int d5) {
        return sumDiceWithFilter(d1, d2, d3, d4, d5, YatsySumScoreEnum.TWOS);
    }

    public static int threes(int d1, int d2, int d3, int d4, int d5) {
        return sumDiceWithFilter(d1, d2, d3, d4, d5, YatsySumScoreEnum.THREES);
    }


    public static int fours(int d1, int d2, int d3, int d4, int d5) {
        return sumDiceWithFilter(d1, d2, d3, d4, d5, YatsySumScoreEnum.FOURS);
    }

    public static int fives(int d1, int d2, int d3, int d4, int d5) {
        return sumDiceWithFilter(d1, d2, d3, d4, d5, YatsySumScoreEnum.FIVES);
    }

    public static int sixes(int d1, int d2, int d3, int d4, int d5) {
        return sumDiceWithFilter(d1, d2, d3, d4, d5, YatsySumScoreEnum.SIXES);
    }

    public static int yatzy(int d1, int d2, int d3, int d4, int d5) {
        Map<Integer, Long> tallies = countTallies(d1, d2, d3, d4, d5);

        return calculateScoreFromTallies(tallies, YatsyTalliesScoreEnum.YATSY);
    }

    public static int scorePair(int d1, int d2, int d3, int d4, int d5) {
        Map<Integer, Long> tallies = countTallies(d1, d2, d3, d4, d5);
        return calculateScoreFromTallies(tallies, YatsyTalliesScoreEnum.PAIR);
    }

    public static int twoPair(int d1, int d2, int d3, int d4, int d5) {
        Map<Integer, Long> tallies = countTallies(d1, d2, d3, d4, d5);

        return tallies.entrySet().stream()
            .filter(entry -> entry.getValue() >= PAIR_VALUE)
            .map(entry -> entry.getKey() * PAIR_VALUE)
            .reduce(DEFAULT_SCORE, Integer::sum);
    }


    public static int threeOfAKind(int d1, int d2, int d3, int d4, int d5) {
        Map<Integer, Long> tallies = countTallies(d1, d2, d3, d4, d5);
        return calculateScoreFromTallies(tallies, YatsyTalliesScoreEnum.THREE_OF_A_KIND);
    }


    public static int fourOfAKind(int d1, int d2, int d3, int d4, int d5) {
        Map<Integer, Long> tallies = countTallies(d1, d2, d3, d4, d5);

        return calculateScoreFromTallies(tallies, YatsyTalliesScoreEnum.FOUR_OF_A_KIND);
    }


    public static int smallStraight(int d1, int d2, int d3, int d4, int d5) {
        Map<Integer, Long> tallies = countTallies(d1, d2, d3, d4, d5);
        return calculateStraightFromTallies(tallies, YastySraightScoreEnum.SMALL_STRAIGHT);
    }

    public static int largeStraight(int d1, int d2, int d3, int d4, int d5) {
        Map<Integer, Long> tallies = countTallies(d1, d2, d3, d4, d5);
        return calculateStraightFromTallies(tallies,YastySraightScoreEnum.LARGE_STRAIGHT);
    }

    public static int fullHouse(int d1, int d2, int d3, int d4, int d5) {

        var pairTotal = scorePair(d1, d2, d3, d4, d5);
        var tripletTotal = threeOfAKind(d1, d2, d3, d4, d5);

        if (pairTotal != DEFAULT_SCORE && tripletTotal != DEFAULT_SCORE)
            return pairTotal + tripletTotal;

        return DEFAULT_SCORE;
    }

    private static Integer sumDiceWithFilter(int d2, int d1, int d3, int d4, int d5, YatsySumScoreEnum scoreTypeEnum) {
        return Stream.of(d1, d2, d3, d4, d5).filter(scoreTypeEnum.getPredicate()).reduce(DEFAULT_SCORE, Integer::sum);
    }

    private static Map<Integer, Long> countTallies(int d1, int d2, int d3, int d4, int d5) {
        return Stream.of(d1, d2, d3, d4, d5).collect(Collectors.groupingBy(
            Function.identity(),
            Collectors.counting()));
    }

    private static Integer calculateScoreFromTallies(Map<Integer, Long> tallies, YatsyTalliesScoreEnum scoreTypeEnum) {
        return tallies.entrySet().stream()
            .filter(scoreTypeEnum.getTalliesPredicate())
            .max(Map.Entry.comparingByKey())
            .map(scoreTypeEnum.getCalculeScoreFunction())
            .orElse(DEFAULT_SCORE);
    }

    private static int calculateStraightFromTallies(Map<Integer, Long> tallies,YastySraightScoreEnum scoreEnum) {
        if (tallies.entrySet().stream()
            .filter(scoreEnum.getStraightPredicate())
            .filter(entry -> entry.getValue() == YastySraightScoreEnum.NB_TALLIES_STRAIGHT)
            .count() == YastySraightScoreEnum.NB_DICE_STRAIGHT)
            return scoreEnum.getStraightScore();
        return DEFAULT_SCORE;
    }

}



