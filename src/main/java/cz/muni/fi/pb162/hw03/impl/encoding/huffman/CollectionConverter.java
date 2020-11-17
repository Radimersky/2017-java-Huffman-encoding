package cz.muni.fi.pb162.hw03.impl.encoding.huffman;

import cz.muni.fi.pb162.hw03.TreeNode;
import cz.muni.fi.pb162.hw03.impl.SymbolFrequency;
import cz.muni.fi.pb162.hw03.impl.encoding.node.LeafTreeNode;

import java.util.Iterator;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;


/**
 * Implements collection converters.
 *
 * @author Marek Radiměřský
 */
public class CollectionConverter {

    /**
     * Converts char set to leaf node set.
     * @param charSet to convert
     * @return leaf node set
     */
    public static NavigableSet<TreeNode> charSetToLeafNodeSet(Set<SymbolFrequency> charSet) {
        NavigableSet set = new TreeSet();
        for (SymbolFrequency symbolFrequency : charSet) {
            set.add(new LeafTreeNode(symbolFrequency));
        }

        return set;
    }

    /**
     * Converts node map to encoding map.
     * @param nodeStringMap to convert
     * @return encoding map
     */
    public static Map<Character, String> nodeMapToEncodingMap(Map<TreeNode, String> nodeStringMap) {
        Map<Character, String> encodingMap = new TreeMap<>();

        Iterator it = nodeStringMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            LeafTreeNode node = (LeafTreeNode) pair.getKey();
            Character character = node.getCharacter();
            String binary = (String) pair.getValue();
            encodingMap.put(character, binary);
            it.remove(); // avoids a ConcurrentModificationException
        }

        return encodingMap;
    }
}
