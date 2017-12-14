package puzzlesolver;

/**
 *
 * @author PedroHenrique
 */
public class Puzzle {

    private int[][] matriz;

    public Puzzle(int dimensao) {
        matriz = new int[dimensao][dimensao];
        for (int i = 0; i < dimensao; i++) {
            for (int j = 0; j < dimensao; j++) {
                matriz[i][j] = i * dimensao + j + 1;
            }
        }

        do {
            embaralharMatriz();

        } while (!checkIfIsSolvable(matriz));
    }

    private void embaralharMatriz() {
        for (int i = 0; i < 2 * matriz.length * matriz.length; i++) {
            int x1 = (int) (Math.random() * matriz.length);
            int y1 = (int) (Math.random() * matriz.length);

            int x2 = (int) (Math.random() * matriz.length);
            int y2 = (int) (Math.random() * matriz.length);

            int aux = matriz[x2][y2];
            matriz[x2][y2] = matriz[x1][y1];
            matriz[x1][y1] = aux;
        }
    }

    private boolean checkIfIsSolvable(int[][] matrizInv) {

        int[] array = new int[(int) Math.pow(matrizInv.length, 2)];
        int count = 0;
        for (int i = 0; i < matrizInv.length; i++) {
            for (int j = 0; j < matrizInv.length; j++) {
                array[count] = matrizInv[i][j];
                count++;
            }
        }

        int inversions = 0;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j] && array[i] != (int) Math.pow(matriz.length, 2)) {
                    inversions++;
                }
            }
        }

        int[] posicaoEspaco = findElementInMatrix(matriz, (int) Math.pow(matrizInv.length, 2));
        int row = posicaoEspaco[0];
        /*if (matriz.length % 2 == 0 && (matriz.length - row) % 2 == 0) {
            return (inversions % 2) != 0;
        } else {
            return (inversions % 2) == 0;
        }*/
        if (((matriz.length % 2 != 0) && (inversions % 2 == 0)) || ((matriz.length % 2 == 0) && (((matriz.length - row) % 2 != 0) == (inversions % 2 == 0)))) {
            return true;
        }
        return false;
    }

    public Puzzle(Puzzle other) {
        if (other != null) {
            matriz = new int[other.matriz.length][other.matriz.length];
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[0].length; j++) {
                    matriz[i][j] = other.matriz[i][j];
                }
            }
        }
    }

    public boolean isEmpty() {
        return matriz == null;
    }

    public int h() {
        int soma = 0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                if (matriz[i][j] != Math.pow(matriz.length, 2)) {
                    int valorI = (matriz[i][j] - 1) / matriz.length;
                    int valorJ = (matriz[i][j] - 1) % matriz.length;
                    soma += Math.abs(i - valorI) + Math.abs(j - valorJ);
                }
            }
        }

        return soma;
    }

// Retorna posição de determinado valor na matriz
    private int[] findElementInMatrix(int[][] matrix, int element) {
        boolean continuar = true;
        int i, j = 0;
        for (i = 0; i < matrix.length && continuar; i++) {
            for (j = 0; j < matrix.length && continuar; j++) {
                if (matrix[i][j] == element) {
                    continuar = false;
                }
            }
        }
        i--;
        j--;
        int[] retorno = {i, j};
        return retorno;
    }

    Puzzle move(Direction d) {
        Puzzle newPuzzle = new Puzzle(this);
        int[] posicaoEspaco = findElementInMatrix(matriz, (int) Math.pow(newPuzzle.getMatriz().length, 2));
        int i = posicaoEspaco[0];
        int j = posicaoEspaco[1];
        switch (d) {
            case UP:
                if (i != 0) {
                    int aux = newPuzzle.matriz[i][j];
                    newPuzzle.matriz[i][j] = newPuzzle.matriz[i - 1][j];
                    newPuzzle.matriz[i - 1][j] = aux;
                } else {
                    return null;
                }
                break;
            case DOWN:
                if (i != matriz.length - 1) {
                    int aux = newPuzzle.matriz[i][j];
                    newPuzzle.matriz[i][j] = newPuzzle.matriz[i + 1][j];
                    newPuzzle.matriz[i + 1][j] = aux;
                } else {
                    return null;
                }
                break;
            case LEFT:
                if (j != 0) {
                    int aux = newPuzzle.matriz[i][j];
                    newPuzzle.matriz[i][j] = newPuzzle.matriz[i][j - 1];
                    newPuzzle.matriz[i][j - 1] = aux;
                } else {
                    return null;
                }
                break;
            case RIGHT:
                if (j != matriz.length - 1) {
                    int aux = newPuzzle.matriz[i][j];
                    newPuzzle.matriz[i][j] = newPuzzle.matriz[i][j + 1];
                    newPuzzle.matriz[i][j + 1] = aux;
                } else {
                    return null;
                }
                break;
        }

        return newPuzzle;
    }

    boolean isGoal() {
        return this.h() == 0;
    }

    public int[][] getMatriz() {
        return matriz;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                int aux = matriz[i][j];
                if (aux != Math.pow(matriz.length, 2)) {
                    sb.append(aux);
                } else {
                    sb.append("*");
                }
                sb.append("\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
