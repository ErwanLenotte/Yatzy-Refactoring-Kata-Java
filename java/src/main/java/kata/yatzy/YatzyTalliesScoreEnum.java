package kata.yatzy;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public enum YatzyTalliesScoreEnum {

    PAIR(entry -> entry.getValue() >= 2, entry -> entry * 2, 1),
    TWO_PAIRS(entry -> entry.getValue() >= 2, entry -> entry * 2, 2),
    THREE_OF_A_KIND(entry -> entry.getValue() >= 3, entry -> entry * 3, 1),
    FOUR_OF_A_KIND(entry -> entry.getValue() >= 4, entry -> entry * 4, 1),
    YATZY(entry -> entry.getValue() >= 5, _ -> 50, 1);
    private final Predicate<Map.Entry<Integer, Long>> talliesPredicate;
    private final Function<Integer, Integer> calculeScoreFunction;
    private final int nbCount;

    YatzyTalliesScoreEnum(Predicate<Map.Entry<Integer, Long>> talliesPredicate, UnaryOperator<Integer> calculateScoreOperation, int nbCount) {
        this.talliesPredicate = talliesPredicate;
        this.calculeScoreFunction = calculateScoreOperation;
        this.nbCount = nbCount;
    }

    public Predicate<Map.Entry<Integer, Long>> getTalliesPredicate() {
        return talliesPredicate;
    }

    public Function<Integer, Integer> getCalculeScoreFunction() {
        return calculeScoreFunction;
    }

    public int getNbCount() {
        return nbCount;
    }
}
