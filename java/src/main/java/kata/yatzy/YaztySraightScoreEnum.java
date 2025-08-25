package kata.yatzy;

import java.util.Map;
import java.util.function.Predicate;

public enum YaztySraightScoreEnum {


    SMALL_STRAIGHT(entry -> entry.getKey() <= 5, 15),
    LARGE_STRAIGHT(entry -> entry.getKey() > 1, 20);

    public static final int NB_DICE_STRAIGHT = 5;
    public static final int NB_TALLIES_STRAIGHT = 1;
    private final Predicate<Map.Entry<Integer, Long>> straightPredicate;
    private final int straightScore;

    YaztySraightScoreEnum(Predicate<Map.Entry<Integer, Long>> straightPredicate, int straightScore) {
        this.straightPredicate = straightPredicate;
        this.straightScore = straightScore;
    }

    public Predicate<Map.Entry<Integer, Long>> getStraightPredicate() {
        return straightPredicate;
    }

    public int getStraightScore() {
        return straightScore;
    }
}
