package alexander.sergeev.app3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("EvenArrayFilter")
class EvenArrayFilterTest {

    private EvenArrayFilter filter;

    @BeforeEach
    void setUp() {
        filter = new EvenArrayFilter();
    }

    @Test
    @DisplayName("filter([1,2,3,4,5,6,7,8,9,10]) returns [2,4,6,8,10]")
    void shouldFilterTaskArray() {
        int[] input = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertThat(filter.filter(input)).containsExactly(2, 4, 6, 8, 10);
    }

    @Test
    @DisplayName("filter([0]) returns [0]")
    void shouldKeepZero() {
        assertThat(filter.filter(new int[]{0})).containsExactly(0);
    }

    @Test
    @DisplayName("filter([-4,-3,-2,-1]) returns [-4,-2]")
    void shouldKeepNegativeEvens() {
        assertThat(filter.filter(new int[]{-4, -3, -2, -1}))
                .containsExactly(-4, -2);
    }

    @Test
    @DisplayName("filter([]) returns empty array")
    void shouldReturnEmptyForEmptyInput() {
        assertThat(filter.filter(new int[]{})).isEmpty();
    }

    @Test
    @DisplayName("filter([1,3,5,7]) returns empty array")
    void shouldReturnEmptyForAllOddInput() {
        assertThat(filter.filter(new int[]{1, 3, 5, 7})).isEmpty();
    }

    @Test
    @DisplayName("filter keeps order and duplicates of even numbers")
    void shouldKeepOrderAndDuplicates() {
        assertThat(filter.filter(new int[]{4, 2, 4, 6, 2}))
                .containsExactly(4, 2, 4, 6, 2);
    }

    @Test
    @DisplayName("filter handles Integer.MIN_VALUE")
    void shouldHandleIntegerMinValue() {
        assertThat(filter.filter(new int[]{Integer.MIN_VALUE}))
                .containsExactly(Integer.MIN_VALUE);
    }

    @Test
    @DisplayName("filter handles Integer.MAX_VALUE")
    void shouldDropIntegerMaxValue() {
        assertThat(filter.filter(new int[]{Integer.MAX_VALUE})).isEmpty();
    }

    @Test
    @DisplayName("null input throws NPE")
    void shouldThrowOnNullWithClearMessage() {
        assertThatThrownBy(() -> filter.filter(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("Array must not be null");
    }

    @Test
    @DisplayName("does not mutate input array")
    void shouldNotMutateInput() {
        int[] input = {1, 2, 3, 4};
        int[] snapshot = input.clone();
        filter.filter(input);
        assertThat(input).containsExactly(snapshot);
    }

    @Test
    @DisplayName("returns a new array")
    void shouldReturnNewArray() {
        int[] input = {2, 4};
        int[] result = filter.filter(input);
        assertThat(result).isNotSameAs(input);
    }
}