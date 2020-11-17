package cz.muni.fi.pb162.hw03;

import cz.muni.fi.pb162.hw03.impl.SymbolFrequency;
import cz.muni.fi.pb162.hw03.impl.encoding.node.InnerTreeNode;
import cz.muni.fi.pb162.hw03.impl.encoding.node.LeafTreeNode;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author Marek Sabo
 */
public class LeafTreeNodeTest {

    private static final SymbolFrequency THREE_X = new SymbolFrequency('X', 3);
    private static final SymbolFrequency TWO_X = new SymbolFrequency('X', 2);
    private static final SymbolFrequency THREE_A = new SymbolFrequency('A', 3);

    private static final LeafTreeNode LEAF_3X = new LeafTreeNode(THREE_X);
    private static final LeafTreeNode LEAF_2X = new LeafTreeNode(TWO_X);
    private static final LeafTreeNode LEAF_3A = new LeafTreeNode(THREE_A);

    public static void assertAscendingOrder(TreeNode smaller, TreeNode bigger) {
        assertThat(smaller.compareTo(bigger))
                .as("%s should be smaller than %s", smaller, bigger)
                .isNegative();

        assertThat(bigger.compareTo(smaller))
                .as("%s should be greater than %s", bigger, smaller)
                .isPositive();
    }

    @Test
    public void shouldBeALeaf() {
        assertThat(LEAF_2X.isLeaf()).isTrue();
    }

    @Test
    public void shouldHaveNullChildren() {
        assertThat(LEAF_3X.getLeftChild()).isNull();
        assertThat(LEAF_3X.getRightChild()).isNull();
    }

    @Test
    public void naturalOrderFrequency() {
        // given
        LeafTreeNode leaf3X = new LeafTreeNode(THREE_X);
        LeafTreeNode leaf2X = new LeafTreeNode(TWO_X);
        //then
        assertAscendingOrder(leaf2X, leaf3X);
    }

    @Test
    public void naturalOrderChar() {
        // given
        LeafTreeNode leaf3A = new LeafTreeNode(THREE_A);
        LeafTreeNode leaf3X = new LeafTreeNode(THREE_X);
        //then
        assertAscendingOrder(leaf3A, leaf3X);
    }

    @Test
    public void naturalOrderNonLeafDifferentFrequency() {
        // given
        LeafTreeNode leaf3X = new LeafTreeNode(THREE_X);
        InnerTreeNode innerNode = new InnerTreeNode(
                new LeafTreeNode(TWO_X),
                new LeafTreeNode(THREE_A)
        );
        //then
        assertAscendingOrder(leaf3X, innerNode);
    }

    @Test
    public void naturalOrderNonLeafSameFrequency() {
        // given
        LeafTreeNode leaf3X = new LeafTreeNode(THREE_X);
        InnerTreeNode innerNode = new InnerTreeNode(
                new LeafTreeNode(TWO_X),
                new LeafTreeNode(new SymbolFrequency('G', 1))
        );
        //then
        assertAscendingOrder(innerNode, leaf3X);
    }

    @Test
    public void equalsSame() {
        // given
        LeafTreeNode threeACopy = new LeafTreeNode(new SymbolFrequency('A', 3));
        //then
        assertThat(LEAF_3A).isEqualTo(LEAF_3A);
        assertThat(LEAF_3A).isEqualTo(threeACopy);
    }

    @Test
    public void equalsDifferent() {
        assertThat(LEAF_3A).isNotEqualTo(LEAF_2X);
        assertThat(LEAF_3A).isNotEqualTo(LEAF_3X);
        assertThat(LEAF_2X).isNotEqualTo(LEAF_3X);
        assertThat(LEAF_2X).isNotEqualTo(null);
        assertThat(LEAF_2X).isNotEqualTo(new Object());
    }

    @Test
    public void hashCodeSame() {
        // given
        LeafTreeNode threeACopy = new LeafTreeNode(new SymbolFrequency('A', 3));
        //then
        assertThat(THREE_A.hashCode()).isEqualTo(threeACopy.hashCode());
    }

    @Test
    public void hashCodeDifferent() {
        assertThat(LEAF_3A.hashCode()).isNotEqualTo(LEAF_2X.hashCode());
        assertThat(LEAF_3A.hashCode()).isNotEqualTo(LEAF_3X.hashCode());
        assertThat(LEAF_2X.hashCode()).isNotEqualTo(LEAF_3X.hashCode());
    }

    @Test
    public void checkToString() {
        // given
        LeafTreeNode leaf3X = new LeafTreeNode(THREE_X);
        //then
        assertThat(leaf3X.toString()).isEqualTo("Leaf 3x'X'");

    }
}
