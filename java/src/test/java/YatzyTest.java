import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class YatzyTest {

    @ParameterizedTest
    @DisplayName("Chance : sum of all dice")
    @CsvFileSource(resources = "chance-sum-all-dice.csv", numLinesToSkip = 1)
    void chance_scores_sum_of_all_dice(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.chance(d1, d2, d3, d4, d5));
    }

    @DisplayName("Yatzy : score 50")
    @ParameterizedTest
    @CsvFileSource(resources = "yatzy-success.csv", numLinesToSkip = 1)
    void yatzy_scores_50(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.yatzy(d1, d2, d3, d4, d5));
    }

    @DisplayName("Yatzy : score 0")
    @ParameterizedTest
    @CsvFileSource(resources = "yatzy-fail.csv", numLinesToSkip = 1)
    void yatzy_scores_0(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.yatzy(d1, d2, d3, d4, d5));
    }

    @DisplayName("1s : score sum of all dice of one")
    @ParameterizedTest
    @CsvFileSource(resources = "test-1s-success.csv", numLinesToSkip = 1)
    public void test_1s_success(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.ones(d1, d2, d3, d4, d5));
    }

    @DisplayName("1s : score 0")
    @ParameterizedTest
    @CsvFileSource(resources = "test-1s-fail.csv", numLinesToSkip = 1)
    public void test_1s_fail(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.ones(d1, d2, d3, d4, d5));
    }

    @DisplayName("2s : score sum of all dice of two")
    @ParameterizedTest
    @CsvFileSource(resources = "test-2s-success.csv", numLinesToSkip = 1)
    public void test_2s_success(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.twos(d1, d2, d3, d4, d5));
    }

    @DisplayName("2s : score 0")
    @ParameterizedTest
    @CsvFileSource(resources = "test-2s-fail.csv", numLinesToSkip = 1)
    public void test_2_fail(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.twos(d1, d2, d3, d4, d5));
    }

    @DisplayName("3s : score sum of all dice of three")
    @ParameterizedTest
    @CsvFileSource(resources = "test-3s-success.csv", numLinesToSkip = 1)
    public void test_3s_success(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.threes(d1, d2, d3, d4, d5));
    }

    @DisplayName("3s : score 0")
    @ParameterizedTest
    @CsvFileSource(resources = "test-3s-fail.csv", numLinesToSkip = 1)
    public void test_3_fail(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.threes(d1, d2, d3, d4, d5));
    }

    @DisplayName("4s : score sum of all dice of four")
    @ParameterizedTest
    @CsvFileSource(resources = "test-4s-success.csv", numLinesToSkip = 1)
    public void test_4s_success(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected,  Yatzy.fours(d1, d2, d3, d4, d5));
    }

    @DisplayName("4s : score 0")
    @ParameterizedTest
    @CsvFileSource(resources = "test-4s-fail.csv", numLinesToSkip = 1)
    public void test_4_fail(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected,  Yatzy.fours(d1, d2, d3, d4, d5));
    }

    @DisplayName("5s : score sum of all dice of five")
    @ParameterizedTest
    @CsvFileSource(resources = "test-5s-success.csv", numLinesToSkip = 1)
    public void test_5s_success(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.fives(d1, d2, d3, d4, d5));
    }

    @DisplayName("5s : score 0")
    @ParameterizedTest
    @CsvFileSource(resources = "test-5s-fail.csv", numLinesToSkip = 1)
    public void test_5_fail(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected,  Yatzy.fives(d1, d2, d3, d4, d5));
    }

    @DisplayName("6s : score sum of all dice of six")
    @ParameterizedTest
    @CsvFileSource(resources = "test-6s-success.csv", numLinesToSkip = 1)
    public void test_6s_success(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.sixes(d1, d2, d3, d4, d5));
    }

    @DisplayName("6s : score 0")
    @ParameterizedTest
    @CsvFileSource(resources = "test-6s-fail.csv", numLinesToSkip = 1)
    public void test_6s_fail(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected,  Yatzy.sixes(d1, d2, d3, d4, d5));
    }

    @DisplayName("one_pair : score sum of one pair")
    @ParameterizedTest
    @CsvFileSource(resources = "test-one-pair-success.csv", numLinesToSkip = 1)
    public void test_one_pair_success(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.scorePair(d1, d2, d3, d4, d5));
    }

    @DisplayName("one_pair : score 0")
    @ParameterizedTest
    @CsvFileSource(resources = "test-one-pair-fail.csv", numLinesToSkip = 1)
    public void test_one_pairs_fail(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.scorePair(d1, d2, d3, d4, d5));
    }

    @DisplayName("two_pair : score sum of two pair")
    @ParameterizedTest
    @CsvFileSource(resources = "test-two-pair-success.csv", numLinesToSkip = 1)
    public void test_two_pair_success(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.twoPair(d1, d2, d3, d4, d5));
    }

    @DisplayName("two_pair : score 0")
    @ParameterizedTest
    @CsvFileSource(resources = "test-two-pair-fail.csv", numLinesToSkip = 1)
    public void test_two_pairs_fail(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.twoPair(d1, d2, d3, d4, d5));
    }

    @DisplayName("three_of_a_kind : score sum of a triplet")
    @ParameterizedTest
    @CsvFileSource(resources = "test-three-of-a-kind-success.csv", numLinesToSkip = 1)
    public void test_three_of_a_kind_success(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.threeOfAKind(d1, d2, d3, d4, d5));
    }

    @DisplayName("three_of_a_kind : score 0")
    @ParameterizedTest
    @CsvFileSource(resources = "test-three-of-a-kind-fail.csv", numLinesToSkip = 1)
    public void test_three_of_a_kind_fail(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.threeOfAKind(d1, d2, d3, d4, d5));
    }

    @DisplayName("four_of_a_kind : score sum of a quadruplet")
    @ParameterizedTest
    @CsvFileSource(resources = "test-four-of-a-kind-success.csv", numLinesToSkip = 1)
    public void test_four_of_a_kind_success(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.fourOfAKind(d1, d2, d3, d4, d5));
    }

    @DisplayName("four_of_a_kind : score 0")
    @ParameterizedTest
    @CsvFileSource(resources = "test-four-of-a-kind-fail.csv", numLinesToSkip = 1)
    public void test_four_of_a_kind_fail(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.fourOfAKind(d1, d2, d3, d4, d5));
    }

    @DisplayName("smallStraight : score 15")
    @ParameterizedTest
    @CsvFileSource(resources = "test-small-straight-success.csv", numLinesToSkip = 1)
    public void test_small_straight_success(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.smallStraight(d1, d2, d3, d4, d5));
    }

    @DisplayName("smallStraight : score 0")
    @ParameterizedTest
    @CsvFileSource(resources = "test-small-straight-fail.csv", numLinesToSkip = 1)
    public void test_small_straight_fail(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.smallStraight(d1, d2, d3, d4, d5));
    }

    @DisplayName("largeStraight : score 15")
    @ParameterizedTest
    @CsvFileSource(resources = "test-large-straight-success.csv", numLinesToSkip = 1)
    public void test_large_straight_success(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.largeStraight(d1, d2, d3, d4, d5));
    }

    @DisplayName("largeStraight : score 0")
    @ParameterizedTest
    @CsvFileSource(resources = "test-large-straight-fail.csv", numLinesToSkip = 1)
    public void test_large_straight_fail(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.largeStraight(d1, d2, d3, d4, d5));
    }

    @DisplayName("fullHouse : score total of pair and triplet")
    @ParameterizedTest
    @CsvFileSource(resources = "test-full-house-success.csv", numLinesToSkip = 1)
    public void test_full_house_success(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.fullHouse(d1, d2, d3, d4, d5));
    }

    @DisplayName("fullHouse : score 0")
    @ParameterizedTest
    @CsvFileSource(resources = "test-full-house-fail.csv", numLinesToSkip = 1)
    public void test_full_house_fail(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.fullHouse(d1, d2, d3, d4, d5));
    }
}
