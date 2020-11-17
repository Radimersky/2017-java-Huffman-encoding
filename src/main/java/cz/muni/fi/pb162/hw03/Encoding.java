package cz.muni.fi.pb162.hw03;

/**
 * Represents character encoding interface.
 *
 * @author Marek Sabo
 */
public interface Encoding {

    /**
     * Codes char into its binary representation.
     *
     * @param encodingChar input character
     * @return coded binary representation
     */
    String getEncodingString(char encodingChar);

    /**
     * Getter for the root of the coded tree.
     * The tree is used for decoding encoded binary message.
     * Number 0 means left child, 1 means right child.
     *
     * @return root of the tree
     */
    TreeNode getRoot();
}
