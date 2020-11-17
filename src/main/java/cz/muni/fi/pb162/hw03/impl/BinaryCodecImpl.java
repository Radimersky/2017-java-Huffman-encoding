package cz.muni.fi.pb162.hw03.impl;

import cz.muni.fi.pb162.hw03.BinaryCodec;
import cz.muni.fi.pb162.hw03.Encoding;
import cz.muni.fi.pb162.hw03.TreeNode;
import cz.muni.fi.pb162.hw03.impl.encoding.node.InnerTreeNode;
import cz.muni.fi.pb162.hw03.impl.encoding.node.LeafTreeNode;


/**
 * Class implementing binary codec
 *
 * @author Marek Radiměřský
 */
public class BinaryCodecImpl implements BinaryCodec {
    private Encoding encoding;

    /**
     * Constructor.
     * @param encoding to work with
     */
    public BinaryCodecImpl(Encoding encoding) {
        this.encoding = encoding;
    }

    /**
     * Converts message into a coded one.
     * Uses {@link Encoding#getEncodingString(char)} to encode the message.
     * In Huffman coding it converts string "abaca" to "0100110" (a = 0, b = 10, c = 11).
     *
     * @param originalMessage readable message
     * @return compressed binary message
     */

    @Override
    public String encode(String originalMessage) {
        if (originalMessage == null) throw new NullPointerException("Message is null");
        if (encoding == null) throw new NullPointerException("Encoding is null");

        String encodedString = "";
        for (int x = 0; x < originalMessage.length(); x++) {
            Character subChar = originalMessage.charAt(x);
            encodedString += encoding.getEncodingString(subChar);
        }
        return encodedString;
    }

    /**
     * Converts coded message into original one.
     * Uses {@link Encoding#getRoot()} to decode the message, following the tree branches.
     * In Huffman coding it converts string "0100110" to "abaca".
     * Tree with mapping a = 0, b = 10, c = 11 looks like this:
     * *
     * / \
     * a  *
     * / \
     * b  c
     *
     * @param binaryMessage binary message
     * @return decoded readable message
     */
    @Override
    public String decode(String binaryMessage) {
        if (binaryMessage == null) throw new NullPointerException("Message is null");
        if (encoding == null) throw new NullPointerException("Encoding is null");

        String decodedMessage = "";
        InnerTreeNode rootNode = ((InnerTreeNode)encoding.getRoot());

        while (binaryMessage.length() != 0) {
            TreeNode childNode;
            if (binaryMessage.charAt(0) == '0') {
                childNode = rootNode.getLeftChild();
            } else if (binaryMessage.charAt(0) == '1') {
                childNode = rootNode.getRightChild();
            } else throw new AssertionError("Symbol in message is not 0 or 1");

            if (childNode instanceof LeafTreeNode) {
                decodedMessage += childNode.getCharacter();
                binaryMessage = binaryMessage.substring(1);
                rootNode = ((InnerTreeNode)encoding.getRoot());
            } else if (childNode instanceof InnerTreeNode) {
                rootNode = (InnerTreeNode)childNode;
                binaryMessage = binaryMessage.substring(1);
            }
        }
        return decodedMessage;
    }
}
