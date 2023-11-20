import java.util.function.Predicate;

public enum YatsyScoreTypeEnum {
    ONES( dice -> dice == 1),
    TWOS(dice -> dice == 2),
    THREES(dice -> dice == 3),
    FOURS(dice -> dice == 4),
    FIVES(dice -> dice == 5),
    SIXES(dice -> dice == 6),
    CHANCE(dice -> true)
    ;

    public Predicate<Integer> getPredicate() {
        return predicate;
    }

    private final Predicate<Integer> predicate;

    YatsyScoreTypeEnum(Predicate<Integer> predicate) {
        this.predicate = predicate;
    }
}
