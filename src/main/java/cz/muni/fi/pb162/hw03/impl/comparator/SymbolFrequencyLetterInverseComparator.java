package cz.muni.fi.pb162.hw03.impl.comparator;

import cz.muni.fi.pb162.hw03.impl.SymbolFrequency;

import java.util.Comparator;

/**
 * Inverse comparator of SymbolFrequency class.
 * object are being compared by letter.
 *
 * @author Marek Radiměřský
 */
public class SymbolFrequencyLetterInverseComparator implements Comparator<SymbolFrequency> {
    /**
     * compares SymbolFrequency objects
     * @param a object a
     * @param b object b
     * @return result of comparing a with b.
     */
    public int compare(SymbolFrequency a, SymbolFrequency b) {
        int comp = Integer.compare(a.getFrequency(), b.getFrequency());
        if(comp == 0) {
            return -(a.getCharacter().compareTo(b.getCharacter()));
        } else return comp;
    }
}
