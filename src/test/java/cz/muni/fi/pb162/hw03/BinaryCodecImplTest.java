package cz.muni.fi.pb162.hw03;

import cz.muni.fi.pb162.hw03.impl.ReadWriteIOImpl;
import cz.muni.fi.pb162.hw03.impl.SymbolFrequency;
import cz.muni.fi.pb162.hw03.impl.BinaryCodecImpl;
import cz.muni.fi.pb162.hw03.impl.encoding.huffman.FrequencyTable;
import cz.muni.fi.pb162.hw03.impl.encoding.huffman.HuffmanEncoding;
import cz.muni.fi.pb162.hw03.impl.encoding.node.InnerTreeNode;
import cz.muni.fi.pb162.hw03.impl.encoding.node.LeafTreeNode;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Marek Sabo
 */
public class BinaryCodecImplTest {

    private BinaryCodec binaryCodec;

    @Before
    public void setUp() throws Exception {
        binaryCodec = new BinaryCodecImpl(new TestEncoding());
    }

    @Test
    public void encodeMessage() {
        // given
        String originalMessage = "abaca";
        String expectedResult = "0100110";
        // when
        String encodedMessage = binaryCodec.encode(originalMessage);
        // then
        assertThat(encodedMessage)
                .as("Message %s should be encoded into %s", originalMessage, expectedResult)
                .isEqualTo(expectedResult);
    }

    @Test
    public void decodeMessage() {
        // given
        String codedMessage = "0100110";
        String originalMessage = "abaca";
        // when
        String decodedMessage = binaryCodec.decode(codedMessage);
        // then
        assertThat(decodedMessage)
                .as("Message %s should be decoded into %s", codedMessage, originalMessage)
                .isEqualTo(originalMessage);
    }

    @Test
    public void encodeAndDecodeShortMessage() throws IOException {
        encodeAndDecodeMessage(new File("test.txt"));
    }

    @Test
    public void encodeAndDecodeLongMessage() throws IOException {
        encodeAndDecodeMessage(new File("midsummer.txt"));
    }

    public void encodeAndDecodeMessage(File file) throws IOException {
        // given
        String originalMessage = new ReadWriteIOImpl().fileToString(file);
        FrequencyTable frequencyTable = new FrequencyTable(originalMessage);
        HuffmanEncoding huffman = new HuffmanEncoding(frequencyTable);
        BinaryCodec binaryCodec = new BinaryCodecImpl(huffman);
        // when
        String encodedMessage = binaryCodec.encode(originalMessage);
        String decodedMessage = binaryCodec.decode(encodedMessage);
        // then
        assertThat(decodedMessage)
                .as("Message %s should be same as original %s", decodedMessage, originalMessage)
                .isEqualTo(originalMessage);
    }

    private class TestEncoding implements Encoding {

        private SymbolFrequency fiveA = new SymbolFrequency('a', 5);
        private SymbolFrequency oneB = new SymbolFrequency('b', 2);
        private SymbolFrequency twoC = new SymbolFrequency('c', 1);

        private TreeNode tree = new InnerTreeNode(
                new LeafTreeNode(fiveA),
                new InnerTreeNode(new LeafTreeNode(oneB), new LeafTreeNode(twoC))
        );

        /**
         * Tree with mapping a = 0, b = 10, c = 11.
         *     *
         *    / \
         *   a  *
         *  / \
         * b  c
         */
        @Override
        public String getEncodingString(char encodingChar) {
            switch (encodingChar) {
                case 'a':
                    return "0";
                case 'b':
                    return "10";
                case 'c':
                    return "11";
                default:
                    return "";
            }
        }

        @Override
        public TreeNode getRoot() {
            return tree;
        }
    }
}
