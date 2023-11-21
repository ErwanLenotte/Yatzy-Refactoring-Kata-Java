package kata.yatsy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class YatzyTest {

    @Test
    void when_yatsy_score_type_is_null_should_throw_NullPointerException() {
        var nullPointerException = assertThrows(NullPointerException.class,
            () -> Yatzy.score(1, 1, 1, 1, 1, null));

        assertEquals("yatsyTypeEnum can't be null", nullPointerException.getMessage());
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when d1 is negative")
    void when_yatsy_d1_is_negative_should_throw_IllegalArgumentException() {
        var illegalArgumentException = assertThrows(IllegalArgumentException.class,
            () -> Yatzy.score(-1, 1, 1, 1, 1, YatsyTypeEnum.YATSY));

        assertEquals("d1 must be between 1 and 6", illegalArgumentException.getMessage());
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when d1 is negative")
    void when_yatsy_d1_is_greater_than_six_should_throw_IllegalArgumentException() {
        var illegalArgumentException = assertThrows(IllegalArgumentException.class,
            () -> Yatzy.score(7, 1, 1, 1, 1, YatsyTypeEnum.YATSY));
        assertEquals("d1 must be between 1 and 6", illegalArgumentException.getMessage());

    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when d2 is negative")
    void when_yatsy_d2_is_negative_should_throw_IllegalArgumentException() {
        var illegalArgumentException = assertThrows(IllegalArgumentException.class,
            () -> Yatzy.score(1, -1, 1, 1, 1, YatsyTypeEnum.YATSY));
        assertEquals("d2 must be between 1 and 6", illegalArgumentException.getMessage());

    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when d2 is greater than six")
    void when_yatsy_d2_is_greater_than_six_should_throw_IllegalArgumentException() {
        var illegalArgumentException = assertThrows(IllegalArgumentException.class,
            () -> Yatzy.score(1, 7, 1, 1, 1, YatsyTypeEnum.YATSY));
        assertEquals("d2 must be between 1 and 6", illegalArgumentException.getMessage());
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when d3 is negative")
    void when_yatsy_d3_is_negative_should_throw_IllegalArgumentException() {
        var illegalArgumentException = assertThrows(IllegalArgumentException.class,
            () -> Yatzy.score(1, 1, -1, 1, 1, YatsyTypeEnum.YATSY));
        assertEquals("d3 must be between 1 and 6", illegalArgumentException.getMessage());

    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when d3 is greater than six")
    void when_yatsy_d3_is_greater_than_six_should_throw_IllegalArgumentException() {
        var illegalArgumentException = assertThrows(IllegalArgumentException.class,
            () -> Yatzy.score(1, 1, 7, 1, 1, YatsyTypeEnum.YATSY));

        assertEquals("d3 must be between 1 and 6", illegalArgumentException.getMessage());
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when d4 is negative")
    void when_yatsy_d4_is_negative_should_throw_IllegalArgumentException() {
        var illegalArgumentException = assertThrows(IllegalArgumentException.class,
            () -> Yatzy.score(1, 1, -1, 1, 1, YatsyTypeEnum.YATSY));

        assertEquals("d3 must be between 1 and 6", illegalArgumentException.getMessage());
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when d4 is greater than six")
    void when_yatsy_d4_is_greater_than_six_should_throw_IllegalArgumentException() {
        var illegalArgumentException = assertThrows(IllegalArgumentException.class,
            () -> Yatzy.score(1, 1, 1, 7, 1, YatsyTypeEnum.YATSY));

        assertEquals("d4 must be between 1 and 6", illegalArgumentException.getMessage());
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when d5 is negative")
    void when_yatsy_d5_is_negative_should_throw_IllegalArgumentException() {
        var illegalArgumentException = assertThrows(IllegalArgumentException.class,
            () -> Yatzy.score(1, 1, 1, 1, -1, YatsyTypeEnum.YATSY));

        assertEquals("d5 must be between 1 and 6", illegalArgumentException.getMessage());
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when d5 is greater than six")
    void when_yatsy_d5_is_greater_than_six_should_throw_IllegalArgumentException() {
        var illegalArgumentException = assertThrows(IllegalArgumentException.class,
            () -> Yatzy.score(6, 1, 1, 1, 7, YatsyTypeEnum.YATSY));

        assertEquals("d5 must be between 1 and 6", illegalArgumentException.getMessage());
    }

    @ParameterizedTest
    @DisplayName("Chance : sum of all dice")
    @CsvFileSource(resources = "chance-sum-all-dice.csv", numLinesToSkip = 1)
    void chance_scores_sum_of_all_dice(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.score(d1, d2, d3, d4, d5, YatsyTypeEnum.CHANCE));
    }

    @DisplayName("kata.yatsy.Yatzy : score 50")
    @ParameterizedTest
    @CsvFileSource(resources = "yatzy-success.csv", numLinesToSkip = 1)
    void yatzy_scores_50(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.score(d1, d2, d3, d4, d5, YatsyTypeEnum.YATSY));
    }

    @DisplayName("kata.yatsy.Yatzy : score 0")
    @ParameterizedTest
    @CsvFileSource(resources = "yatzy-fail.csv", numLinesToSkip = 1)
    void yatzy_scores_0(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.score(d1, d2, d3, d4, d5, YatsyTypeEnum.YATSY));
    }

    @DisplayName("1s : score sum of all dice of one")
    @ParameterizedTest
    @CsvFileSource(resources = "test-1s-success.csv", numLinesToSkip = 1)
    void test_1s_success(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.score(d1, d2, d3, d4, d5, YatsyTypeEnum.ONES));
    }

    @DisplayName("1s : score 0")
    @ParameterizedTest
    @CsvFileSource(resources = "test-1s-fail.csv", numLinesToSkip = 1)
    void test_1s_fail(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.score(d1, d2, d3, d4, d5, YatsyTypeEnum.ONES));
    }

    @DisplayName("2s : score sum of all dice of two")
    @ParameterizedTest
    @CsvFileSource(resources = "test-2s-success.csv", numLinesToSkip = 1)
    void test_2s_success(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.score(d1, d2, d3, d4, d5, YatsyTypeEnum.TWOS));
    }

    @DisplayName("2s : score 0")
    @ParameterizedTest
    @CsvFileSource(resources = "test-2s-fail.csv", numLinesToSkip = 1)
    void test_2_fail(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.score(d1, d2, d3, d4, d5, YatsyTypeEnum.TWOS));
    }

    @DisplayName("3s : score sum of all dice of three")
    @ParameterizedTest
    @CsvFileSource(resources = "test-3s-success.csv", numLinesToSkip = 1)
    void test_3s_success(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.score(d1, d2, d3, d4, d5, YatsyTypeEnum.THREES));
    }

    @DisplayName("3s : score 0")
    @ParameterizedTest
    @CsvFileSource(resources = "test-3s-fail.csv", numLinesToSkip = 1)
    void test_3_fail(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.score(d1, d2, d3, d4, d5, YatsyTypeEnum.THREES));
    }

    @DisplayName("4s : score sum of all dice of four")
    @ParameterizedTest
    @CsvFileSource(resources = "test-4s-success.csv", numLinesToSkip = 1)
    void test_4s_success(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.score(d1, d2, d3, d4, d5, YatsyTypeEnum.FOURS));
    }

    @DisplayName("4s : score 0")
    @ParameterizedTest
    @CsvFileSource(resources = "test-4s-fail.csv", numLinesToSkip = 1)
    void test_4_fail(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.score(d1, d2, d3, d4, d5, YatsyTypeEnum.FOURS));
    }

    @DisplayName("5s : score sum of all dice of five")
    @ParameterizedTest
    @CsvFileSource(resources = "test-5s-success.csv", numLinesToSkip = 1)
    void test_5s_success(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.score(d1, d2, d3, d4, d5, YatsyTypeEnum.FIVES));
    }

    @DisplayName("5s : score 0")
    @ParameterizedTest
    @CsvFileSource(resources = "test-5s-fail.csv", numLinesToSkip = 1)
    void test_5_fail(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.score(d1, d2, d3, d4, d5, YatsyTypeEnum.FIVES));
    }

    @DisplayName("6s : score sum of all dice of six")
    @ParameterizedTest
    @CsvFileSource(resources = "test-6s-success.csv", numLinesToSkip = 1)
    void test_6s_success(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.score(d1, d2, d3, d4, d5, YatsyTypeEnum.SIXES));
    }

    @DisplayName("6s : score 0")
    @ParameterizedTest
    @CsvFileSource(resources = "test-6s-fail.csv", numLinesToSkip = 1)
    void test_6s_fail(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.score(d1, d2, d3, d4, d5, YatsyTypeEnum.SIXES));
    }

    @DisplayName("one_pair : score sum of one pair")
    @ParameterizedTest
    @CsvFileSource(resources = "test-one-pair-success.csv", numLinesToSkip = 1)
    void test_one_pair_success(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.score(d1, d2, d3, d4, d5, YatsyTypeEnum.PAIR));
    }

    @DisplayName("one_pair : score 0")
    @ParameterizedTest
    @CsvFileSource(resources = "test-one-pair-fail.csv", numLinesToSkip = 1)
    void test_one_pairs_fail(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.score(d1, d2, d3, d4, d5, YatsyTypeEnum.PAIR));
    }

    @DisplayName("two_pair : score sum of two pair")
    @ParameterizedTest
    @CsvFileSource(resources = "test-two-pair-success.csv", numLinesToSkip = 1)
    void test_two_pair_success(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.score(d1, d2, d3, d4, d5, YatsyTypeEnum.TWO_PAIRS));
    }

    @DisplayName("two_pair : score 0")
    @ParameterizedTest
    @CsvFileSource(resources = "test-two-pair-fail.csv", numLinesToSkip = 1)
    void test_two_pairs_fail(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.score(d1, d2, d3, d4, d5, YatsyTypeEnum.TWO_PAIRS));
    }

    @DisplayName("three_of_a_kind : score sum of a triplet")
    @ParameterizedTest
    @CsvFileSource(resources = "test-three-of-a-kind-success.csv", numLinesToSkip = 1)
    void test_three_of_a_kind_success(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.score(d1, d2, d3, d4, d5, YatsyTypeEnum.THREE_OF_A_KIND));
    }

    @DisplayName("three_of_a_kind : score 0")
    @ParameterizedTest
    @CsvFileSource(resources = "test-three-of-a-kind-fail.csv", numLinesToSkip = 1)
    void test_three_of_a_kind_fail(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.score(d1, d2, d3, d4, d5, YatsyTypeEnum.THREE_OF_A_KIND));
    }

    @DisplayName("four_of_a_kind : score sum of a quadruplet")
    @ParameterizedTest
    @CsvFileSource(resources = "test-four-of-a-kind-success.csv", numLinesToSkip = 1)
    void test_four_of_a_kind_success(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.score(d1, d2, d3, d4, d5, YatsyTypeEnum.FOUR_OF_A_KIND));
    }

    @DisplayName("four_of_a_kind : score 0")
    @ParameterizedTest
    @CsvFileSource(resources = "test-four-of-a-kind-fail.csv", numLinesToSkip = 1)
    void test_four_of_a_kind_fail(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.score(d1, d2, d3, d4, d5, YatsyTypeEnum.FOUR_OF_A_KIND));
    }

    @DisplayName("smallStraight : score 15")
    @ParameterizedTest
    @CsvFileSource(resources = "test-small-straight-success.csv", numLinesToSkip = 1)
    void test_small_straight_success(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.score(d1, d2, d3, d4, d5, YatsyTypeEnum.SMALL_STRAIGHT));
    }

    @DisplayName("smallStraight : score 0")
    @ParameterizedTest
    @CsvFileSource(resources = "test-small-straight-fail.csv", numLinesToSkip = 1)
    void test_small_straight_fail(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.score(d1, d2, d3, d4, d5, YatsyTypeEnum.SMALL_STRAIGHT));
    }

    @DisplayName("largeStraight : score 15")
    @ParameterizedTest
    @CsvFileSource(resources = "test-large-straight-success.csv", numLinesToSkip = 1)
    void test_large_straight_success(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.score(d1, d2, d3, d4, d5, YatsyTypeEnum.LARGE_STRAIGHT));
    }

    @DisplayName("largeStraight : score 0")
    @ParameterizedTest
    @CsvFileSource(resources = "test-large-straight-fail.csv", numLinesToSkip = 1)
    void test_large_straight_fail(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.score(d1, d2, d3, d4, d5, YatsyTypeEnum.LARGE_STRAIGHT));
    }

    @DisplayName("fullHouse : score total of pair and triplet")
    @ParameterizedTest
    @CsvFileSource(resources = "test-full-house-success.csv", numLinesToSkip = 1)
    void test_full_house_success(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.score(d1, d2, d3, d4, d5, YatsyTypeEnum.FULL_HOUSE));
    }

    @DisplayName("fullHouse : score 0")
    @ParameterizedTest
    @CsvFileSource(resources = "test-full-house-fail.csv", numLinesToSkip = 1)
    void test_full_house_fail(int d1, int d2, int d3, int d4, int d5, int expected) {
        assertEquals(expected, Yatzy.score(d1, d2, d3, d4, d5, YatsyTypeEnum.FULL_HOUSE));
    }
}
