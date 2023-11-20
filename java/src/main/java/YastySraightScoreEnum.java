import java.util.Map;
import java.util.function.Predicate;

public enum YastySraightScoreEnum {



    SMALL_STRAIGHT(entry -> entry.getKey() <= 5, 15),
    LARGE_STRAIGHT(entry -> entry.getKey() > 1,20);

    YastySraightScoreEnum(Predicate<Map.Entry<Integer, Long>> straightPredicate, int straightScore) {
        this.straightPredicate = straightPredicate;
        this.straightScore = straightScore;
    }

    private final Predicate<Map.Entry<Integer, Long>> straightPredicate;

    private final int straightScore;

    public Predicate<Map.Entry<Integer, Long>> getStraightPredicate() {
        return straightPredicate;
    }

    public int getStraightScore() {
        return straightScore;
    }

    public static final int NB_DICE_STRAIGHT = 5;
    public static final int NB_TALLIES_STRAIGHT = 1;
}
