package puzzlesolver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author PedroHenrique
 */
public class Busca {

    private List<Node> fringe;
    private Set<Node> closed;
    private final Node inicio;

    public Busca(Node inicio) {
        this.inicio = inicio;
    }

    public Busca(Puzzle inicio) {
        this.inicio = new Node(null, inicio);
    }

    public Node search() {
        fringe = new ArrayList<>();
        closed = new HashSet<>();
        NodeComparator c = new NodeComparator();

        fringe.add(inicio);

        while (!fringe.isEmpty()) {
            Node auxNode = fringe.remove(0);
            closed.add(auxNode);

            System.out.println("Atual");
            System.out.println(auxNode.f());

            if (auxNode.getState().isGoal()) {
                return auxNode;
            }

            for (Direction d : Direction.values()) {
                Puzzle puzzle = new Puzzle(auxNode.getState().move(d));
                if (!puzzle.isEmpty()) {
                    Node node = new Node(auxNode, puzzle);

                    if (!isInClosedList(node) && !isInFringedList(node)) {
                        System.out.println("Filho");
                        System.out.println(node.f());

                        int index = Collections.binarySearch(fringe, node, c);
                        if (index < 0) {
                            fringe.add((-index - 1), node);
                        } else {
                            fringe.add(index, node);
                        }
                    }
                }
            }
        }

        return null;
    }

    private boolean isInClosedList(Node node2) {
        for (Node node : closed) {
            if (node.equals(node2)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean isInFringedList(Node node2) {
        for (Node node : fringe) {
            if (node.equals(node2)) {
                return true;
            }
        }
        return false;
    }
}
