package cz.muni.fi.pb162.hw03;

import cz.muni.fi.pb162.hw03.impl.SymbolFrequency;
import cz.muni.fi.pb162.hw03.impl.encoding.huffman.CollectionConverter;
import cz.muni.fi.pb162.hw03.impl.encoding.huffman.FrequencyTable;
import cz.muni.fi.pb162.hw03.impl.encoding.huffman.HuffmanEncoding;
import cz.muni.fi.pb162.hw03.impl.encoding.node.InnerTreeNode;
import cz.muni.fi.pb162.hw03.impl.encoding.node.LeafTreeNode;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

/**
 * @author Marek Sabo
 */
public class HuffmanEncodingTest {

    private static final SymbolFrequency TWO_B = new SymbolFrequency('b', 2);
    private static final SymbolFrequency TWO_C = new SymbolFrequency('c', 2);
    private static final SymbolFrequency FIVE_A = new SymbolFrequency('a', 5);

    @Test
    public void tableSetOrderedNaturally() {
        // given
        String message = "ababacaac";
        FrequencyTable table = new FrequencyTable(message);
        // when
        Set<SymbolFrequency> symbolFrequencySet = table.createTable();
        // then
        assertThat(symbolFrequencySet)
                .hasSize(3)
                .containsExactly(
                        TWO_B,
                        TWO_C,
                        FIVE_A
                );
    }

    @Test
    public void convertCharSetToLeafNodeSet() {
        // given
        Set<SymbolFrequency> charSet = new TreeSet<>(Arrays.asList(TWO_B, TWO_C, FIVE_A));
        // when
        NavigableSet<TreeNode> nodeSet = CollectionConverter.charSetToLeafNodeSet(charSet);
        // then
        assertThat(nodeSet)
                .hasSize(3)
                .containsExactly(
                        new LeafTreeNode(TWO_B),
                        new LeafTreeNode(TWO_C),
                        new LeafTreeNode(FIVE_A)
                );
    }

    @Test
    public void convertNodeMapToEncodingMap() {
        // given
        Map<TreeNode, String> nodeMap = new TreeMap<>();
        nodeMap.put(new LeafTreeNode(TWO_B), "00");
        nodeMap.put(new LeafTreeNode(TWO_C), "01");
        nodeMap.put(new LeafTreeNode(FIVE_A), "1");
        // when
        Map<Character, String> encodingMap = CollectionConverter.nodeMapToEncodingMap(nodeMap);
        // then
        assertThat(encodingMap)
                .hasSize(3)
                .containsOnly(
                        entry('b', "00"),
                        entry('c', "01"),
                        entry('a', "1")
                );
    }

    @Test
    public void checkFrequencyTableToTree() {
        // given
        Set<SymbolFrequency> charSet = new TreeSet<>(Arrays.asList(TWO_B, TWO_C, FIVE_A));
        HuffmanEncoding huffmanEncoding = new HuffmanEncoding(new FrequencyTable("a"));
        // when
        TreeNode root = huffmanEncoding.frequencyTableToTree(charSet);
        // then
        assertThat(root.isLeaf()).isFalse();

        TreeNode rightChild = root.getRightChild();
        assertThat(rightChild).isEqualTo(new LeafTreeNode(FIVE_A));

        TreeNode leftChild = root.getLeftChild();
        assertThat(leftChild.isLeaf()).isFalse();

        TreeNode rightGrandChild = leftChild.getRightChild();
        assertThat(rightGrandChild).isEqualTo(new LeafTreeNode(TWO_C));

        TreeNode leftGrandChild = leftChild.getLeftChild();
        assertThat(leftGrandChild).isEqualTo(new LeafTreeNode(TWO_B));
    }


    @Test
    public void checkCreateCodeTree() {
        // given
        HuffmanEncoding huffmanEncoding = new HuffmanEncoding(new FrequencyTable("a"));
        TreeNode treeNode = new InnerTreeNode(
                new InnerTreeNode(new LeafTreeNode(TWO_C), new LeafTreeNode(TWO_B)),
                new LeafTreeNode(FIVE_A)
        );
        // when
        Map<TreeNode, String> nodeMap = new TreeMap<>();
        huffmanEncoding.createCodeTree(nodeMap, treeNode, "");
        // then
        assertThat(nodeMap)
                .hasSize(3)
                .containsOnly(
                        entry(new LeafTreeNode(TWO_B), "01"),
                        entry(new LeafTreeNode(TWO_C), "00"),
                        entry(new LeafTreeNode(FIVE_A), "1")
                );
    }
}
