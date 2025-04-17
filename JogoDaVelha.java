import java.util.Random;

public class JogoDaVelha {
    protected static final int X = 1, O = -1;
    protected static final int VAZIO = 0;
    protected int tabuleiro[][];
    protected int jogador;
    protected int d;

    public JogoDaVelha(int tamanho) {
        limpaTabuleiro();
        this.tabuleiro = new int[tamanho][tamanho];
        this.d = tamanho;
    };

    public void limpaTabuleiro() {
        for (int linha = 0; linha < d; linha++) {
            for (int coluna = 0; coluna < d; coluna++) {
                tabuleiro[linha][coluna] = VAZIO;
            }
        }
        jogador = X;
    };

    public void poePeca(int linha, int coluna) {
        if (linha < 0 || linha > 4 || coluna < 0 || coluna > 4) {
            throw new IllegalArgumentException("Posicao invalida");
        }
        if (tabuleiro[linha][coluna] != VAZIO)
            throw new IllegalArgumentException("Posicao ocupada");
        tabuleiro[linha][coluna] = jogador;

        jogador = -jogador;
    }

    public int[] gerarLinhaColuna() {
        Random gerador = new Random();
        int linha = gerador.nextInt(0, d);
        int coluna = gerador.nextInt(0, d);
        return new int[] { linha, coluna };
    }

    public int poePecaAutomatico() {
        int[] posicao = gerarLinhaColuna();
        while (tabuleiro[posicao[0]][posicao[1]] != VAZIO) {
            posicao = gerarLinhaColuna();
        }
        tabuleiro[posicao[0]][posicao[1]] = jogador;
        int estadoJogo = vencedor();

        if (estadoJogo == 1) {
            return 1;
        } else if (estadoJogo == -1) {
            return -1;
        } else if (estadoJogo == 0) {
            return 0;
        } 

        jogador = -jogador;
        return 2;
    }
    public String venceuUsando() {
        // Verificar linhas
        for (int i = 0; i < d; i++) {
            if (tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][1] == tabuleiro[i][2] && tabuleiro[i][0] != VAZIO) {
                return "linha";
            }
        }

        // Verificar colunas
        for (int i = 0; i < d; i++) {
            if (tabuleiro[0][i] == tabuleiro[1][i] && tabuleiro[1][i] == tabuleiro[2][i] && tabuleiro[0][i] != VAZIO) {
                return "coluna";
            }
        }

        // Verificar diagonal principal
        if (tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][2] && tabuleiro[0][0] != VAZIO) {
            return "diagonal";
        }

        // Verificar diagonal secundária
        if (tabuleiro[0][2] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][0] && tabuleiro[0][2] != VAZIO) {
            return "diagonal";
        }

        return "nenhum";
    }
    public boolean descVencedor(int marca) {
        int somaDiagonal1 = 0;
        int somaDiagonal2 = 0;
        for (int linha = 0; linha < d; linha++) {
            int somaLinha = 0;
            for (int coluna = 0; coluna < d; coluna++) {
                somaLinha += tabuleiro[linha][coluna];
                if (linha == coluna) {
                    somaDiagonal1 += tabuleiro[linha][coluna];
                }

                if (linha + coluna == 2) {
                    somaDiagonal2 += tabuleiro[linha][coluna];
                }
            }
            if (somaLinha == marca * d) {
                return true;
            }
        }

        if (somaDiagonal1 == marca * d) {
            return true;
        }

        if (somaDiagonal2 == marca * d) {
            return true;
        }

        for (int coluna = 0; coluna < d; coluna++) {
            int somaColuna = 0;
            for (int linha = 0; linha < d; linha++) {
                somaColuna += tabuleiro[linha][coluna];
            }
            if (somaColuna == marca * d) {
                return true;
            }
        }

        return false;
    }

    public int vencedor() {
        if (descVencedor(X)) {
            return X;
        } else if (descVencedor(O)) {
            return O;
        } else {
            for (int linha = 0; linha < d; linha++) {
                for (int coluna = 0; coluna < d; coluna++) {
                    if (tabuleiro[linha][coluna] == VAZIO) {
                        return 2;
                    }
                }
            }
        }
        return 0;
    }

    public String toString() {
        String s = "";
        for (int linha = 0; linha < d; linha++) {
            for (int coluna = 0; coluna < d; coluna++) {
                switch (tabuleiro[linha][coluna]) {
                    case X:
                        s += "X";
                        break;
                    case O:
                        s += "O";
                        break;
                    case VAZIO:
                        s += " ";
                        break;
                }
                if (coluna < d - 1)
                    s += "|"; 
            }
            if (linha < d - 1) {
                String tracos = "-".repeat(d * 2 - 1); 
                s += "\n" + tracos + "\n"; 
            }
        }
        return s;
    }

    public int getJogador() {
        return jogador;
    }
}
