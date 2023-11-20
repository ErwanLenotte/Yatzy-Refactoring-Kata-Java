import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Yatzy {
    private static final int DEFAULT_SCORE = 0;
    private static final int PAIR_VALUE = 2;
    private static final int PAIR_TALLIES = 2;
    private static final int YATSY_SCORE = 50;
    private static final int YATSY_VALUE = 5;
    private static final int TRIPLET_VALUE = 3;
    private static final int TRIPLET_TALLIES = 3;
    private static final int QUADRUPLET_VALUE = 4;
    private static final int QUADRUPLET_TALLIES = 4;
    private static final int NB_DICE_STRAIGHT = 5;
    private static final int SMALL_STRAIGHT_SCORE = 15;
    private static final int MAX_DICE_SMALL_STRAIGHT = 5;
    private static final int LARGE_STRAIGHT_SCORE = 20;
    private static final int NB_TALLIES_STRAIGHT = 1;
    private static final int MIN_DICE_LARGE_STRAIGHT = 1;


    public static int chance(int d1, int d2, int d3, int d4, int d5) {
        return sumDiceWithFilter(d2, d1, d3, d4, d5, YatsyScoreTypeEnum.CHANCE);
    }
    
    public static int ones(int d1, int d2, int d3, int d4, int d5) {
        return sumDiceWithFilter(d2, d1, d3, d4, d5, YatsyScoreTypeEnum.ONES);
    }
    
    public static int twos(int d1, int d2, int d3, int d4, int d5) {
        return sumDiceWithFilter(d1, d2, d3, d4, d5, YatsyScoreTypeEnum.TWOS);
    }

    public static int threes(int d1, int d2, int d3, int d4, int d5) {
        return sumDiceWithFilter(d1, d2, d3, d4, d5,YatsyScoreTypeEnum.THREES);
    }


    public static int fours(int d1, int d2, int d3, int d4, int d5) {
        return sumDiceWithFilter(d1, d2, d3, d4, d5,YatsyScoreTypeEnum.FOURS);
    }

    public static int fives(int d1, int d2, int d3, int d4, int d5) {
        return sumDiceWithFilter(d1, d2, d3, d4, d5, YatsyScoreTypeEnum.FIVES);
    }

    public static int sixes(int d1, int d2, int d3, int d4, int d5) {
        return sumDiceWithFilter(d1, d2, d3, d4, d5,YatsyScoreTypeEnum.SIXES);
    }

    public static int yatzy(int d1, int d2, int d3, int d4, int d5) {
        Map<Integer, Long> tallies = countTallies(d1, d2, d3, d4, d5);

        return tallies.entrySet().stream()
            .filter(entry -> entry.getValue() >= YATSY_VALUE)
            .findAny()
            .map(entry -> YATSY_SCORE)
            .orElse(DEFAULT_SCORE);
    }

    public static int scorePair(int d1, int d2, int d3, int d4, int d5) {
        Map<Integer, Long> tallies = countTallies(d1, d2, d3, d4, d5);

        return tallies.entrySet().stream()
            .filter(entry -> entry.getValue() >= PAIR_TALLIES)
            .max(Map.Entry.comparingByKey())
            .map(entry -> entry.getKey() * PAIR_VALUE)
            .orElse(DEFAULT_SCORE);
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

        return tallies.entrySet().stream()
            .filter(entry -> entry.getValue() >= TRIPLET_TALLIES)
            .max(Map.Entry.comparingByKey())
            .map(entry -> entry.getKey() * TRIPLET_VALUE)
            .orElse(DEFAULT_SCORE);
    }


    public static int fourOfAKind(int d1, int d2, int d3, int d4, int d5) {
        Map<Integer, Long> tallies = countTallies(d1, d2, d3, d4, d5);

        return tallies.entrySet().stream()
            .filter(entry -> entry.getValue() >= QUADRUPLET_TALLIES)
            .max(Map.Entry.comparingByKey())
            .map(entry -> entry.getKey() * QUADRUPLET_VALUE)
            .orElse(DEFAULT_SCORE);
    }


    public static int smallStraight(int d1, int d2, int d3, int d4, int d5) {
        Map<Integer, Long> tallies = countTallies(d1, d2, d3, d4, d5);

        if (tallies.entrySet().stream()
            .filter(entry -> entry.getValue() == NB_TALLIES_STRAIGHT && entry.getKey() <= MAX_DICE_SMALL_STRAIGHT)
            .count() == NB_DICE_STRAIGHT)
            return SMALL_STRAIGHT_SCORE;
        return DEFAULT_SCORE;
    }

    public static int largeStraight(int d1, int d2, int d3, int d4, int d5) {
        Map<Integer, Long> tallies = countTallies(d1, d2, d3, d4, d5);

        if (tallies.entrySet().stream()
            .filter(entry -> entry.getValue() == NB_TALLIES_STRAIGHT && entry.getKey() > MIN_DICE_LARGE_STRAIGHT)
            .count() == NB_DICE_STRAIGHT)
            return LARGE_STRAIGHT_SCORE;
        return DEFAULT_SCORE;
    }

    public static int fullHouse(int d1, int d2, int d3, int d4, int d5) {

        Map<Integer, Long> tallies = countTallies(d1, d2, d3, d4, d5);

        var pairTotal = tallies.entrySet().stream()
            .filter(entry -> entry.getValue() >= PAIR_TALLIES)
            .max(Map.Entry.comparingByKey())
            .map(entry -> entry.getKey() * PAIR_VALUE)
            .orElse(DEFAULT_SCORE);

        var tripletTotal = tallies.entrySet().stream()
            .filter(entry -> entry.getValue() >= TRIPLET_TALLIES)
            .max(Map.Entry.comparingByKey())
            .map(entry -> entry.getKey() * TRIPLET_VALUE)
            .orElse(DEFAULT_SCORE);

        if (pairTotal != DEFAULT_SCORE && tripletTotal != DEFAULT_SCORE)
            return pairTotal + tripletTotal;

        return DEFAULT_SCORE;
    }

    private static Integer sumDiceWithFilter(int d2, int d1, int d3, int d4, int d5, YatsyScoreTypeEnum scoreTypeEnum) {
        return Stream.of(d1, d2, d3, d4, d5).filter(scoreTypeEnum.getPredicate()).reduce(DEFAULT_SCORE, Integer::sum);
    }

    private static Map<Integer, Long> countTallies(int d1, int d2, int d3, int d4, int d5) {
        return Stream.of(d1, d2, d3, d4, d5).collect(Collectors.groupingBy(
            Function.identity(),
            Collectors.counting()));
    }


}



