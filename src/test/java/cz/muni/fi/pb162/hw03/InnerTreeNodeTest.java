package cz.muni.fi.pb162.hw03;

import cz.muni.fi.pb162.hw03.impl.SymbolFrequency;
import cz.muni.fi.pb162.hw03.impl.encoding.node.InnerTreeNode;
import cz.muni.fi.pb162.hw03.impl.encoding.node.LeafTreeNode;
import org.junit.Test;

import static cz.muni.fi.pb162.hw03.LeafTreeNodeTest.assertAscendingOrder;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author Marek Sabo
 */
public class InnerTreeNodeTest {

    private static final LeafTreeNode THREE_C = new LeafTreeNode(new SymbolFrequency('C', 3));
    private static final LeafTreeNode TWO_C = new LeafTreeNode(new SymbolFrequency('C', 2));
    private static final LeafTreeNode THREE_A = new LeafTreeNode(new SymbolFrequency('A', 3));

    private static final InnerTreeNode INNER_2C_3A = new InnerTreeNode(TWO_C, THREE_A);

    @Test
    public void shouldNotBeALeaf() {
        assertThat(INNER_2C_3A.isLeaf()).isFalse();
    }

    @Test
    public void shouldHaveEmptyCharacter() {
        assertThat(INNER_2C_3A.getCharacter()).isEqualTo(TreeNode.EMPTY_CHAR);
    }

    @Test
    public void shouldHaveSumOfFrequencies() {
        assertThat(INNER_2C_3A.getFrequency()).isEqualTo(5);
    }

    @Test
    public void shouldNotHaveNullChildren() {
        assertThat(INNER_2C_3A.getLeftChild()).isNotNull();
        assertThat(INNER_2C_3A.getRightChild()).isNotNull();
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    @Test
    public void naturalOrderFrequency() {
        // given
        InnerTreeNode inner5 = INNER_2C_3A;
        InnerTreeNode inner6 = new InnerTreeNode(THREE_A, THREE_C);
        //then
        assertAscendingOrder(inner5, inner6);
    }

    @Test
    public void naturalOrderLeftChild() {
        // given
        InnerTreeNode inner3a2c = new InnerTreeNode(THREE_A, TWO_C);
        InnerTreeNode inner2c3c = new InnerTreeNode(TWO_C, THREE_C);
        //then
        assertAscendingOrder(inner2c3c, inner3a2c);
    }

    @Test
    public void naturalOrderRightChild() {
        // given
        InnerTreeNode inner2c3a = new InnerTreeNode(TWO_C, THREE_A);
        InnerTreeNode inner2c3c = new InnerTreeNode(TWO_C, THREE_C);
        //then
        assertAscendingOrder(inner2c3a, inner2c3c);
    }

    @Test
    public void naturalOrderLeafDifferentFrequency() {
        assertAscendingOrder(THREE_C, INNER_2C_3A);
    }

    @Test
    public void naturalOrderLeafSameFrequency() {
        // given
        LeafTreeNode fiveK = new LeafTreeNode(new SymbolFrequency('K', 5));
        // then
        assertAscendingOrder(INNER_2C_3A, fiveK);
    }

    @Test
    public void checkToString() {
        assertThat(INNER_2C_3A.toString()).isEqualTo("C-(5)-A");

    }

    @Test
    public void equalsSame() {
        // given
        InnerTreeNode inner2c3aCopy = new InnerTreeNode(
                new LeafTreeNode(new SymbolFrequency('C', 2)),
                new LeafTreeNode(new SymbolFrequency('A', 3)));
        //then
        assertThat(INNER_2C_3A).isEqualTo(INNER_2C_3A);
        assertThat(INNER_2C_3A).isEqualTo(inner2c3aCopy);
    }

    @Test
    public void equalsDifferent() {
        // given
        InnerTreeNode inner2c3f = new InnerTreeNode(
                new LeafTreeNode(new SymbolFrequency('C', 2)),
                new LeafTreeNode(new SymbolFrequency('F', 3)));
        InnerTreeNode inner1c3a = new InnerTreeNode(
                new LeafTreeNode(new SymbolFrequency('C', 1)),
                new LeafTreeNode(new SymbolFrequency('A', 3)));
        //then
        assertThat(INNER_2C_3A).isNotEqualTo(inner2c3f);
        assertThat(INNER_2C_3A).isNotEqualTo(inner1c3a);
        assertThat(INNER_2C_3A).isNotEqualTo(null);
        assertThat(INNER_2C_3A).isNotEqualTo(new Object());
    }

    @Test
    public void hashCodeSame() {
        // given
        InnerTreeNode inner2c3aCopy = new InnerTreeNode(
                new LeafTreeNode(new SymbolFrequency('C', 2)),
                new LeafTreeNode(new SymbolFrequency('A', 3)));
        //then
        assertThat(INNER_2C_3A.hashCode()).isEqualTo(INNER_2C_3A.hashCode());
        assertThat(INNER_2C_3A.hashCode()).isEqualTo(inner2c3aCopy.hashCode());
    }

    @Test
    public void hashCodeDifferent() {
        assertThat(INNER_2C_3A.hashCode()).isNotEqualTo(THREE_A.hashCode());
        assertThat(INNER_2C_3A.hashCode()).isNotEqualTo(new Object().hashCode());
    }

}
