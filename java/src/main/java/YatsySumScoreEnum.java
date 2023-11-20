import java.util.function.Predicate;

public enum YatsySumScoreEnum {
    ONES(dice -> dice == 1),
    TWOS(dice -> dice == 2),
    THREES(dice -> dice == 3),
    FOURS(dice -> dice == 4),
    FIVES(dice -> dice == 5),
    SIXES(dice -> dice == 6),
    CHANCE(dice -> true);

    private final Predicate<Integer> predicate;

    YatsySumScoreEnum(Predicate<Integer> predicate) {
        this.predicate = predicate;
    }

    public Predicate<Integer> getPredicate() {
        return predicate;
    }
}
