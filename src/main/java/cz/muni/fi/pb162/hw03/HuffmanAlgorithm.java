package cz.muni.fi.pb162.hw03;

import cz.muni.fi.pb162.hw03.impl.SymbolFrequency;

import java.util.Map;
import java.util.Set;

/**
 * Interface for Huffman entropy coding.
 * After these steps, the algorithm should have an information how to code and decode the incoming messages.
 *
 * @author Marek Sabo
 */
public interface HuffmanAlgorithm {

    /**
     * First Huffman algorithm step: transforms set of frequencies into a forest, and then forest into one big tree.
     *
     * @param characterFrequencies frequency characters retrieved from frequency table
     * @return root of the one big tree
     */
    TreeNode frequencyTableToTree(Set<SymbolFrequency> characterFrequencies);

    /**
     * Recursive function, represents second step.
     * Takes existing root of the tree (or subtree) and the information how it should be coded.
     * Also takes map, which stores the coded representation of every node of the tree.
     *
     * @param map            map to be filled
     * @param node           tree node, every node have to contain an encoding information.
     * @param encodingString encoding message which the root has, the root of the root has obviously empty string
     */
    void createCodeTree(Map<TreeNode, String> map, TreeNode node, String encodingString);

}
