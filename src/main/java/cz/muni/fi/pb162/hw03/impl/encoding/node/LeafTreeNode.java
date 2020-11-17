package cz.muni.fi.pb162.hw03.impl.encoding.node;

import cz.muni.fi.pb162.hw03.TreeNode;
import cz.muni.fi.pb162.hw03.impl.SymbolFrequency;


/**
 * Class representing leaf tree node.
 *
 * @author Marek Radiměřský
 */
public class LeafTreeNode extends AbstractTreeNode implements TreeNode {

    /**
     * Constructor
     * @param symbol of node
     */
    public LeafTreeNode(SymbolFrequency symbol) {
        super(symbol, null, null);
    }

    /**
     * compareTo
     * @param o object to compare
     * @return result of comparing
     */
    @Override
    public int compareTo(TreeNode o) {
        int comp = Integer.compare(getFrequency(), o.getFrequency());
        if (comp == 0) {
            if (isLeaf() && !o.isLeaf()) {
                return 1;
            }
            if (isLeaf() && o.isLeaf()) {
                return getCharacter().compareTo(o.getCharacter());
            }
        }
        return comp;
    }

    @Override
    public String toString() {
        return "Leaf " + getSymbol().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LeafTreeNode that = (LeafTreeNode) o;
        return getSymbol().equals(that.getSymbol());
    }

    @Override
    public int hashCode() {
        return getSymbol() != null ? getSymbol().hashCode() : 0;
    }
}
