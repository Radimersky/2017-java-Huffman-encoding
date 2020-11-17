package cz.muni.fi.pb162.hw03.impl.encoding.node;

import cz.muni.fi.pb162.hw03.TreeNode;
import cz.muni.fi.pb162.hw03.impl.SymbolFrequency;

/**
 * Class representing inner tree node.
 *
 * @author Marek Radiměřský
 */
public class InnerTreeNode extends AbstractTreeNode implements TreeNode{
    public static final Character EMPTY_CHAR = Character.MIN_VALUE;

    /**
     * Constructor.
     * @param leftNode left child of node
     * @param rightNode right child of node
     */
    public InnerTreeNode(TreeNode leftNode, TreeNode rightNode) {
        super(calculateFrequency(leftNode, rightNode), leftNode, rightNode);
    }

    /**
     * Calculates frequency of left, right child Symbols.
     * @param leftChild of node
     * @param rightChild of node
     * @return
     */
    private static SymbolFrequency calculateFrequency(TreeNode leftChild, TreeNode rightChild) {
        if (leftChild == null && rightChild == null) {
            return new SymbolFrequency(EMPTY_CHAR, 0);
        } else if (leftChild == null) {
            return new SymbolFrequency(EMPTY_CHAR, rightChild.getFrequency());
        } else if (rightChild == null) {
            return new SymbolFrequency(EMPTY_CHAR, leftChild.getFrequency());
        }

        return new SymbolFrequency(EMPTY_CHAR, leftChild.getFrequency() + rightChild.getFrequency());
    }

    /**
     * compareTo method.
     * @param o object to compare
     * @return result of comparing.
     */
    @Override
    public int compareTo(TreeNode o) {
        int comp = Integer.compare(getFrequency(), o.getFrequency());
        if (comp == 0) {
            if (isLeaf() && !o.isLeaf()) {
                return 1;
            }
            if (!isLeaf() && o.isLeaf()) {
                return -1;
            }
            if (isLeaf() && o.isLeaf()) {
                return getCharacter().compareTo(o.getCharacter());
            }
            if (!isLeaf() && !o.isLeaf()) {
                return copareToWhenBothObjectsAreInnerNode(o);
            }
        }
        return comp;
    }

    /**
     * Help ,method for compareTo used when compared objects are inner nodes.
     * @param o object to compare
     * @return result of comparing
     */
    private Integer copareToWhenBothObjectsAreInnerNode(TreeNode o) {
        if (getLeftChild() == null ^ o.getLeftChild() == null) {
            return (getLeftChild() == null) ? -1 : 1;
        }

        if (getLeftChild() == null && o.getLeftChild() == null) {
            return 0;
        }

        if (getRightChild() == null ^ o.getRightChild() == null) {
            return (getRightChild() == null) ? -1 : 1;
        }

        if (getRightChild() == null && o.getRightChild() == null) {
            return 0;
        }

        int leftChildComp = getLeftChild().compareTo(o.getLeftChild());

        if (leftChildComp == 0) {
            return getRightChild().compareTo(o.getRightChild());
        } else {
            return leftChildComp;
        }
    }

    /**
     * String representation.
     * @return string representation of object
     */
    @Override
    public String toString() {
        return getLeftChild().getCharacter() + "-(" + (getLeftChild().getFrequency()
                + getRightChild().getFrequency()) + ")-" + getRightChild().getCharacter();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InnerTreeNode that = (InnerTreeNode) o;

        if (!(getLeftChild().equals((that).getLeftChild()))) return false;
        return getRightChild().equals((that).getRightChild());
    }

    @Override
    public int hashCode() {
        int result = getLeftChild() != null ? getLeftChild().hashCode() : 0;
        result = 31 * result + (getRightChild() != null ? getRightChild().hashCode() : 0);
        return result;
    }
}
