/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzlesolver;

import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author PedroHenrique
 */
public class EightPuzzle extends JFrame {

    private Node atual;

    public static void main(String args[]) {
        EightPuzzle eightPuzzle = new EightPuzzle();
    }

    public EightPuzzle() {
        super();
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        Puzzle puzzle = new Puzzle(3);

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
            atual = n;
            repaint();
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(EightPuzzle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 26));
        if (atual != null) {
            g.drawRect(40, 40, 300, 300);
            for (int i = 0; i < atual.getState().getMatriz().length; i++) {
                g.drawLine(140 + 100*i, 40, 140 + 100*i, 340);
                //g.drawLine(240, 40, 240, 340);
                g.drawLine(40, 140 + 100*i, 340, 140+100*i);
                //g.drawLine(40, 240, 340, 240);
            }
            for (int i = 0; i < atual.getState().getMatriz().length; i++) {
                for (int j = 0; j < atual.getState().getMatriz()[0].length; j++) {
                    if (atual.getState().getMatriz()[i][j] != Math.pow(atual.getState().getMatriz().length, 2)) {
                        g.drawString(atual.getState().getMatriz()[i][j] + "", 100 * j + 85, 100 * i + 95);
                    } else {
                        g.drawString("*", 100 * j + 85, 100 * i + 95);
                    }
                }
            }
        } else {
            g.drawString("Aguardando...", 50, 50);
        }
    }
}
