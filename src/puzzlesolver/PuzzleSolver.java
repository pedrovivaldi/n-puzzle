package puzzlesolver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author PedroHenrique
 */
public class PuzzleSolver {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Qual a dimensão da matriz do 8 Puzzle?");
        int dimensao = sc.nextInt();

        Puzzle puzzle = new Puzzle(dimensao);

        Busca busca = new Busca(puzzle);

        System.out.println("Iniciando a busca...");
        Node node = busca.search();

        List<Node> nodesParents = new ArrayList<>();
        while (node != null) {
            nodesParents.add(node);
            node = node.getParent();
        }

        Collections.reverse(nodesParents);

        for (Node n : nodesParents) {
            System.out.println(n.getState());
        }
    }

}
