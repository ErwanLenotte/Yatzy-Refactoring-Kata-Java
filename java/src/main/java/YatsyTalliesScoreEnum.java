import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public enum YatsyTalliesScoreEnum {

    PAIR(entry -> entry.getValue() >= 2, entry -> entry * 2, 1),

    TWO_PAIRS(entry -> entry.getValue() >= 2, entry -> entry * 2, 2),
    THREE_OF_A_KIND(entry -> entry.getValue() >= 3 ,entry -> entry * 3, 1),
    FOUR_OF_A_KIND(entry -> entry.getValue() >= 4,entry -> entry * 4, 1),
    YATSY(entry -> entry.getValue() >= 5,  entry -> 50, 1);
    private final Predicate<Map.Entry<Integer, Long>> talliesPredicate;
    private final  Function<Integer, Integer> calculeScoreFunction;
    private final int nbCount;

    YatsyTalliesScoreEnum(Predicate<Map.Entry<Integer, Long>> talliesPredicate, Function<Integer, Integer> calculeScoreFunction, int nbCount) {
        this.talliesPredicate = talliesPredicate;
        this.calculeScoreFunction = calculeScoreFunction;
        this.nbCount = nbCount;
    }

    public Predicate<Map.Entry<Integer, Long>> getTalliesPredicate() {
        return talliesPredicate;
    }

    public  Function<Integer, Integer> getCalculeScoreFunction() {
        return calculeScoreFunction;
    }

    public int getNbCount() {
        return nbCount;
    }
}
