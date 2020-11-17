package cz.muni.fi.pb162.hw03.impl;


/**
 * Creates objects that holds character and frequency of character.
 *
 * @author Marek Radiměřský
 */
public class SymbolFrequency implements Comparable<SymbolFrequency> {
    private Character character;
    private int frequency;

    /**
     * Constructor.
     * @param character symbol
     * @param frequency of character
     */
    public SymbolFrequency(Character character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    /**
     * Character getter.
     * @return character
     */
    public Character getCharacter() {
        return character;
    }

    public int getFrequency() {
        return frequency;
    }

    @Override
    public String toString() {
        return frequency + "x" + "'" + character + "'";
    }

    @Override
    public int compareTo(SymbolFrequency s) {
        int comp = Integer.compare(frequency, s.frequency);
        if(comp == 0) {
            return character.compareTo(s.character);
        } else return comp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SymbolFrequency that = (SymbolFrequency) o;

        if (frequency != that.frequency) return false;
        return this.character.equals(that.character);
    }

    @Override
    public int hashCode() {
        int result = character != null ? character.hashCode() : 0;
        result = 31 * result + frequency;
        return result;
    }
}
