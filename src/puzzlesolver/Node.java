package puzzlesolver;

import java.util.Objects;

/**
 *
 * @author PedroHenrique
 */
public class Node {

    private Puzzle state;
    private Node parent;
    private int depth;

    public Node(Node parent, Puzzle state) {
        this.state = state;
        this.parent = parent;
        if (parent != null) {
            this.depth = parent.depth + 1;
        } else {
            this.depth = 1;
        }
    }

    public int f() {
        return depth + state.h();
    }

    public Puzzle getState() {
        return state;
    }

    public void setState(Puzzle state) {
        this.state = state;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    @Override
    public boolean equals(Object o) {
        if (o != null) {
            Node node = (Node) o;
            for (int i = 0; i < state.getMatriz().length; i++) {
                for (int j = 0; j < state.getMatriz().length; j++) {
                    if (state.getMatriz()[i][j] != node.getState().getMatriz()[i][j]) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.state);
        hash = 47 * hash + Objects.hashCode(this.parent);
        hash = 47 * hash + this.depth;
        return hash;
    }
}
