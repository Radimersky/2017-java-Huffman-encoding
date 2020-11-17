package cz.muni.fi.pb162.hw03.impl.comparator;

import cz.muni.fi.pb162.hw03.impl.SymbolFrequency;

import java.util.Comparator;

/**
 * Inverese comparator of SymbolFrequency class.
 *
 * @author Marek Radiměřský
 */
public class SymbolFrequencyInverseComparator implements Comparator<SymbolFrequency> {
    /**
     * compares SymbolFrequency
     * @param a object a
     * @param b object b
     * @return compared object a and b
     */
    public int compare(SymbolFrequency a, SymbolFrequency b) {
        int comp = Integer.compare(a.getFrequency(), b.getFrequency());
        if(comp == 0) {
            return -(a.getCharacter().compareTo(b.getCharacter()));
        } else return -comp;
    }
}
