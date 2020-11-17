package cz.muni.fi.pb162.hw03;

/**
 * Represents encoding encoding and decoding interface.
 * The encoding logic, represented by {@link Encoding} interface, is parameter of the constructor.
 *
 * @author Marek Sabo
 */
public interface BinaryCodec {

    /**
     * Converts message into a coded one.
     * Uses {@link Encoding#getEncodingString(char)} to encode the message.
     * In Huffman coding it converts string "abaca" to "0100110" (a = 0, b = 10, c = 11).
     *
     * @param originalMessage readable message
     * @return compressed binary message
     */
    String encode(String originalMessage);

    /**
     * Converts coded message into original one.
     * Uses {@link Encoding#getRoot()} to decode the message, following the tree branches.
     * In Huffman coding it converts string "0100110" to "abaca".
     * Tree with mapping a = 0, b = 10, c = 11 looks like this:
     *       *
     *      / \
     *     a  *
     *       / \
     *      b  c
     *
     * @param binaryMessage binary message
     * @return decoded readable message
     */
    String decode(String binaryMessage);

}
