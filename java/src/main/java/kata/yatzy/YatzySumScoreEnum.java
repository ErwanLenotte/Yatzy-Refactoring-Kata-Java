package kata.yatzy;

import java.util.function.IntPredicate;

public enum YatzySumScoreEnum {
    ONES(dice -> dice == 1),
    TWOS(dice -> dice == 2),
    THREES(dice -> dice == 3),
    FOURS(dice -> dice == 4),
    FIVES(dice -> dice == 5),
    SIXES(dice -> dice == 6),
    CHANCE(_ -> true);

    private final IntPredicate predicate;

    YatzySumScoreEnum(IntPredicate predicate) {
        this.predicate = predicate;
    }

    public IntPredicate getPredicate() {
        return predicate;
    }
}
