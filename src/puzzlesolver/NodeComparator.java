package puzzlesolver;

import java.util.Comparator;

/**
 *
 * @author PedroHenrique
 */
public class NodeComparator implements Comparator<Node> {

    @Override
    public int compare(Node o1, Node o2) {
        if (o1.f() < o2.f()) {
            return -1;
        } else if (o1.f() == o2.f()) {
            return 0;
        }
        return 1;
    }

}
