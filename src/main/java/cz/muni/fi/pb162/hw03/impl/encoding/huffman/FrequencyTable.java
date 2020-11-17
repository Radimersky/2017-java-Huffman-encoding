package cz.muni.fi.pb162.hw03.impl.encoding.huffman;

import cz.muni.fi.pb162.hw03.impl.SymbolFrequency;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;


/**
 * Class representing frequency table.
 *
 * @author Marek Radiměřský
 */
public class FrequencyTable {
    private String text;

    /**
     * Constructor.
     * @param text to create table from
     */
    public FrequencyTable(String text) {
        this.text = text;
    }

    /**
     * Creates frequency table.
     * @return frequency table
     */
    public TreeSet<SymbolFrequency> createTable() {
        HashMap<Character, Integer> map = getSymbolFrequencyMap();
        TreeSet<SymbolFrequency> set = createSymbolFrequencySetFromMap(map);
        return set;
    }

    /**
     * Symbol frequency map getter.
     * @return symbol frequency map
     */
    private HashMap<Character, Integer> getSymbolFrequencyMap() {
        HashMap<Character, Integer> map = new HashMap();

        String sText = text;
        for (int i = 0; i < sText.length(); i++) {
            char charText = sText.charAt(i);
            Integer val = map.get(charText);
            if (val != null) {
                map.put(charText, new Integer(val + 1));
            } else {
                map.put(charText, 1);
            }
        }
        return map;
    }

    /**
     * Creates SymbolFrequency set from map.
     * @param map to create set from
     * @return SymbolFrequency set
     */
    private TreeSet<SymbolFrequency> createSymbolFrequencySetFromMap(HashMap map) {
        TreeSet<SymbolFrequency> set = new TreeSet<>();

        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Character character = (Character) pair.getKey();
            int frequency = (int) pair.getValue();
            set.add(new SymbolFrequency(character, frequency));
            it.remove(); // avoids a ConcurrentModificationException
        }
        return set;
    }
}
