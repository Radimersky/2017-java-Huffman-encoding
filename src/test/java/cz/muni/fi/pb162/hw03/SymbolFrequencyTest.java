package cz.muni.fi.pb162.hw03;

import cz.muni.fi.pb162.hw03.impl.SymbolFrequency;
import cz.muni.fi.pb162.hw03.impl.comparator.SymbolFrequencyInverseComparator;
import cz.muni.fi.pb162.hw03.impl.comparator.SymbolFrequencyLetterInverseComparator;
import org.junit.Test;

import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Marek Sabo
 */
public class SymbolFrequencyTest {

    @Test
    public void checkToString() {
        // given
        SymbolFrequency fiveA = new SymbolFrequency('A', 5);
        //then
        assertThat(fiveA.toString()).isEqualTo("5x'A'");
    }

    @Test
    public void equalsSame() {
        // given
        SymbolFrequency fiveA = new SymbolFrequency('A', 5);
        SymbolFrequency fiveACopy = new SymbolFrequency('A', 5);
        //then
        assertThat(fiveA).isEqualTo(fiveACopy);
        assertThat(fiveACopy).isEqualTo(fiveA);
    }

    @Test
    public void equalsDifferent() {
        // given
        SymbolFrequency fiveA = new SymbolFrequency('A', 5);
        SymbolFrequency oneB = new SymbolFrequency('B', 1);
        //then
        assertThat(fiveA).isNotEqualTo(oneB);
        assertThat(oneB).isNotEqualTo(fiveA);
        assertThat(oneB).isNotEqualTo(new Object());
    }

    @Test
    public void hashCodeSame() {
        // given
        SymbolFrequency fiveA = new SymbolFrequency('A', 5);
        SymbolFrequency fiveACopy = new SymbolFrequency('A', 5);
        //then
        assertThat(fiveA.hashCode()).isEqualTo(fiveACopy.hashCode());
    }

    @Test
    public void hashCodeDifferent() {
        // given
        SymbolFrequency fiveA = new SymbolFrequency('A', 5);
        SymbolFrequency oneB = new SymbolFrequency('B', 1);
        //then
        assertThat(fiveA.hashCode()).isNotEqualTo(oneB.hashCode());
    }

    @Test
    public void naturalOrderCompareSame() {
        // given
        SymbolFrequency sixA = new SymbolFrequency('A', 6);
        // then
        assertThat(sixA).isEqualByComparingTo(sixA);
    }

    @Test
    public void naturalOrderCompareFrequency() {
        // given
        SymbolFrequency fiveA = new SymbolFrequency('A', 5);
        SymbolFrequency sixA = new SymbolFrequency('A', 6);
        // then
        assertThat(sixA).isGreaterThan(fiveA);
    }

    @Test
    public void naturalOrderCompareFrequencyAndChar() {
        // given
        SymbolFrequency fiveA = new SymbolFrequency('A', 5);
        SymbolFrequency fiveB = new SymbolFrequency('B', 5);
        // then
        assertThat(fiveA).isLessThan(fiveB);
    }


    @Test
    public void inverseComparatorCompareSame() {
        // given
        SymbolFrequency sixA = new SymbolFrequency('A', 6);
        Comparator<SymbolFrequency> comparator = new SymbolFrequencyInverseComparator();
        // then
        assertThat(sixA).usingComparator(comparator).isEqualByComparingTo(sixA);
    }

    @Test
    public void inverseComparatorCompareFrequency() {
        // given
        SymbolFrequency fiveA = new SymbolFrequency('A', 5);
        SymbolFrequency sixA = new SymbolFrequency('A', 6);
        Comparator<SymbolFrequency> comparator = new SymbolFrequencyInverseComparator();
        // then
        assertThat(sixA).usingComparator(comparator).isLessThan(fiveA);
    }

    @Test
    public void inverseComparatorCompareFrequencyAndChar() {
        // given
        SymbolFrequency fiveA = new SymbolFrequency('A', 5);
        SymbolFrequency fiveB = new SymbolFrequency('B', 5);
        Comparator<SymbolFrequency> comparator = new SymbolFrequencyInverseComparator();
        // then
        assertThat(fiveA).usingComparator(comparator).isGreaterThan(fiveB);
    }

    @Test
    public void letterInverseComparatorCompareSame() {
        // given
        SymbolFrequency sixA = new SymbolFrequency('A', 6);
        Comparator<SymbolFrequency> comparator = new SymbolFrequencyLetterInverseComparator();
        // then
        assertThat(sixA).usingComparator(comparator).isEqualByComparingTo(sixA);
    }

    @Test
    public void letterInverseComparatorCompareFrequency() {
        // given
        SymbolFrequency fiveA = new SymbolFrequency('A', 5);
        SymbolFrequency sixA = new SymbolFrequency('A', 6);
        Comparator<SymbolFrequency> comparator = new SymbolFrequencyLetterInverseComparator();
        // then
        assertThat(sixA).usingComparator(comparator).isGreaterThan(fiveA);
    }

    @Test
    public void letterInverseComparatorCompareFrequencyAndChar() {
        // given
        SymbolFrequency fiveA = new SymbolFrequency('A', 5);
        SymbolFrequency fiveB = new SymbolFrequency('B', 5);
        Comparator<SymbolFrequency> comparator = new SymbolFrequencyLetterInverseComparator();
        // then
        assertThat(fiveA).usingComparator(comparator).isGreaterThan(fiveB);
    }

}
