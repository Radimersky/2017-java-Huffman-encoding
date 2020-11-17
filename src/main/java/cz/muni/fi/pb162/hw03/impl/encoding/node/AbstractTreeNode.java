package cz.muni.fi.pb162.hw03.impl.encoding.node;

import cz.muni.fi.pb162.hw03.TreeNode;
import cz.muni.fi.pb162.hw03.impl.SymbolFrequency;

/**
 * Abstract class of tree node.
 *
 * @author Marek Radiměřský
 */
public abstract class AbstractTreeNode implements TreeNode {

    private SymbolFrequency symbol;
    private TreeNode leftChild;
    private TreeNode rightChild;

    /**
     * Constructor.
     */
    public AbstractTreeNode(){}

    /**
     * Constructor.
     * @param symbol of node
     * @param leftChild of node
     * @param rightChild of node
     */
    public AbstractTreeNode(SymbolFrequency symbol, TreeNode leftChild, TreeNode rightChild) {
        this.symbol = symbol;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    /**
     * Occurrence of the character.
     *
     * @return occurrence of the character, cannot be negative
     */
    @Override
    public int getFrequency() {
        return symbol.getFrequency();
    }

    public SymbolFrequency getSymbol() {
        return new SymbolFrequency(symbol.getCharacter(), symbol.getFrequency());
    }

    /**
     * Character representation in the tree node.
     *
     * @return representation of the character
     */
    @Override
    public Character getCharacter() {
        return symbol.getCharacter();
    }

    /**
     * Left child of the node.
     *
     * @return left child, null if does not exist
     */
    @Override
    public TreeNode getLeftChild() {
        if (leftChild == null) {
            return null;
        }

        if (leftChild.getClass() == LeafTreeNode.class) {
            return new LeafTreeNode(new SymbolFrequency(leftChild.getCharacter(), leftChild.getFrequency()));
        }
        TreeNode leftChildCopy  = leftChild.getLeftChild();
        TreeNode rightChildCopy  = leftChild.getRightChild();
        return new InnerTreeNode(leftChildCopy, rightChildCopy);
    }

    /**
     * Right child of the node.
     *
     * @return right child, null if does not exist
     */
    @Override
    public TreeNode getRightChild() {
        if (rightChild == null) {
            return null;
        }

        if (rightChild.getClass() == LeafTreeNode.class) {
            return new LeafTreeNode(new SymbolFrequency(rightChild.getCharacter(), rightChild.getFrequency()));
        }
        TreeNode leftChildCopy  = rightChild.getLeftChild();
        TreeNode rightChildCopy  = rightChild.getRightChild();
        return new InnerTreeNode(leftChildCopy, rightChildCopy);
    }

    /**
     * Node is a leaf, when it has no children (both children are nulls).
     *
     * @return true if node is a leaf, false otherwise.
     */
    @Override
    public boolean isLeaf() {
        if (leftChild == null && rightChild == null) {
            return true;
        }
        return false;
    }
}
