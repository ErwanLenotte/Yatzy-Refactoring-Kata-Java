import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public enum YatsyTalliesScoreEnum {

    PAIR(entry -> entry.getValue() >= 2, entry -> entry.getKey() * 2),
    THREE_OF_A_KIND(entry -> entry.getValue() >= 3 ,entry -> entry.getKey() * 3),
    FOUR_OF_A_KIND(entry -> entry.getValue() >= 4,entry -> entry.getKey() * 4),
    YATSY(entry -> entry.getValue() >= 5,  entry -> 50);
    private final Predicate<Map.Entry<Integer, Long>> talliesPredicate;
    private final Function<Map.Entry<Integer, Long>, Integer> calculeScoreFunction;

    YatsyTalliesScoreEnum(Predicate<Map.Entry<Integer, Long>> talliesPredicate, Function<Map.Entry<Integer, Long>, Integer> calculeScoreFunction) {
        this.talliesPredicate = talliesPredicate;
        this.calculeScoreFunction = calculeScoreFunction;
    }

    public Predicate<Map.Entry<Integer, Long>> getTalliesPredicate() {
        return talliesPredicate;
    }

    public Function<Map.Entry<Integer, Long>, Integer> getCalculeScoreFunction() {
        return calculeScoreFunction;
    }
}
