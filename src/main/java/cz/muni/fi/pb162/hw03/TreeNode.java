package cz.muni.fi.pb162.hw03;

/**
 * Represents node in a Huffman tree.
 *
 * @author Marek Sabo
 */

public interface TreeNode extends Comparable<TreeNode> {

    /**
     * Represents special name for inner tree node which gets created by joining two nodes.
     * Thus, it cannot contain 2 characters.
     */
    Character EMPTY_CHAR = '\0';

    /**
     * Occurrence of the character.
     *
     * @return occurrence of the character, cannot be negative
     */
    int getFrequency();

    /**
     * Character representation in the tree node.
     *
     * @return representation of the character
     */
    Character getCharacter();

    /**
     * Left child of the node.
     *
     * @return left child, null if does not exist
     */
    TreeNode getLeftChild();

    /**
     * Right child of the node.
     *
     * @return right child, null if does not exist
     */
    TreeNode getRightChild();

    /**
     * Node is a leaf, when it has no children (both children are nulls).
     *
     * @return true if node is a leaf, false otherwise.
     */
    boolean isLeaf();

}
