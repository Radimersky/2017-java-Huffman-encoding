package cz.muni.fi.pb162.hw03.impl.encoding.huffman;

import cz.muni.fi.pb162.hw03.Encoding;
import cz.muni.fi.pb162.hw03.HuffmanAlgorithm;
import cz.muni.fi.pb162.hw03.TreeNode;
import cz.muni.fi.pb162.hw03.impl.SymbolFrequency;
import cz.muni.fi.pb162.hw03.impl.encoding.node.InnerTreeNode;
import cz.muni.fi.pb162.hw03.impl.encoding.node.LeafTreeNode;

import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeMap;


/**
 * Class implementing Huffman encoding.
 *
 * @author Marek Radiměřský
 */
public class HuffmanEncoding implements Encoding, HuffmanAlgorithm {
    private TreeNode root;
    private Map<TreeNode, String> nodeMap;

    /**
     * Constructor
     * @param frequencyTable to work with
     */
    public HuffmanEncoding(FrequencyTable frequencyTable) {
        this.root = frequencyTableToTree(frequencyTable.createTable());
        createCodeTree(new TreeMap<>(), root, "");
    }

    /**
     * Codes char into its binary representation.
     *
     * @param encodingChar input character
     * @return coded binary representation
     */
    @Override
    public String getEncodingString(char encodingChar) {
        Map<Character, String> encodingMap = new TreeMap<>(CollectionConverter.nodeMapToEncodingMap(getNodeMap()));
        return encodingMap.get(encodingChar);
    }

    /**
     * nodeMap getter
     * @return nodeMap
     */
    public Map<TreeNode, String> getNodeMap() {
        Map<TreeNode, String> newNodeMap = new TreeMap<>(nodeMap);
        return newNodeMap;
    }

    /**
     * Getter for the root of the coded tree.
     * The tree is used for decoding encoded binary message.
     * Number 0 means left child, 1 means right child.
     *
     * @return root of the tree
     */
    @Override
    public TreeNode getRoot() {
        if (root == null) {
            return null;
        }

        return new InnerTreeNode(root.getLeftChild(), root.getRightChild());
    }

    /**
     * First Huffman algorithm step: transforms set of frequencies into a forest, and then forest into one big tree.
     *
     * @param characterFrequencies frequency characters retrieved from frequency table
     * @return root of the one big tree
     */
    @Override
    public TreeNode frequencyTableToTree(Set<SymbolFrequency> characterFrequencies) {
        // Creates forest of trees.
        NavigableSet<TreeNode> trees = CollectionConverter.charSetToLeafNodeSet(characterFrequencies);

        while (trees.size() != 1) {
            TreeNode firstLowestFrequencyNode = trees.pollFirst();
            TreeNode secondLowestFrequencyNode = trees.pollFirst();
            trees.add(new InnerTreeNode(firstLowestFrequencyNode, secondLowestFrequencyNode));
        }
        return trees.pollFirst();
    }

    /**
     * Recursive function, represents second step.
     * Takes existing root of the tree (or subtree) and the information how it should be coded.
     * Also takes map, which stores the coded representation of every node of the tree.
     *
     * @param map            map to be filled
     * @param node           tree node, every node have to contain an encoding information.
     * @param encodingString encoding message which the root has, the root of the root has obviously empty string
     */
    @Override
    public void createCodeTree(Map<TreeNode, String> map, TreeNode node, String encodingString) {
        nodeMap = createCodeTreeRecursive(map, node, encodingString);
        map.putAll(nodeMap);
    }

    /**
     * Help method for createCodeTree.
     * Creates code tree recursively
     * @param map            map to be filled
     * @param node           tree node, every node have to contain an encoding information.
     * @param encodingString encoding message which the root has, the root of the root has obviously empty string
     * @return filled map
     */
    private Map createCodeTreeRecursive(Map<TreeNode, String> map, TreeNode node, String encodingString) {
        if (node instanceof InnerTreeNode) {
            encodingString += 0;
            createCodeTreeRecursive(map, node.getLeftChild(), encodingString);
            encodingString = encodingString.substring(0, encodingString.length() - 1);

            encodingString += 1;
            createCodeTreeRecursive(map, node.getRightChild(), encodingString);
            encodingString = encodingString.substring(0, encodingString.length() - 1);

        } else if (node instanceof LeafTreeNode) {

            map.put(node, encodingString);
        }
        return map;
    }
}
