import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        boolean JogarNovamente = true;
        
        while (JogarNovamente) {
            System.out.println("Escolha o tamanho: ");
            int tamanho = sc.nextInt();
            
            JogoDaVelha partida = new JogoDaVelha(tamanho);
            int estadoJogo = partida.poePecaAutomatico();
            
            while (estadoJogo == 2) {
                System.out.println(partida.toString());
                System.out.println("Vez do jogador " + (partida.getJogador() == 1 ? "X" : "O"));
                System.out.println("\n");
                estadoJogo = partida.poePecaAutomatico();
            }
        if (estadoJogo == 1) {
            System.out.println("Jogador X venceu!");
            System.out.println("Venceu usando: " + partida.venceuUsando());
        } else if (estadoJogo == -1) {
            System.out.println("Jogador O venceu!");
            System.out.println("Venceu usando: " + partida.venceuUsando());
        } else {
            System.out.println("Empate!");
        }
        
            System.out.println(partida.toString());
            
            if (estadoJogo == 1) {
                System.out.println("Jogador X venceu!");
                System.out.println("Venceu usando: " + partida.venceuUsando());
            } else if (estadoJogo == -1) {
                System.out.println("Jogador O venceu!");
                System.out.println("Venceu usando: " + partida.venceuUsando());
            } else if (estadoJogo == 0) {
                System.out.println("Empate!");
            }
            int resposta = -1;
            do {
                System.out.println("Jogar de novo? 1 para Sim e 0 para Não: ");
                try {
                    resposta = sc.nextInt();
                    if (resposta != 0 && resposta != 1) {
                        System.out.println("Açao invalida!!!");
                    }
                } catch (Exception e) {
                    System.out.println("Apenas 0 ou 1: ");
                    sc.nextLine(); 
                }
            } while (resposta != 0 && resposta != 1);
            
            JogarNovamente = (resposta == 1);
        }
        System.out.println("Fim de jogo!!!");
        sc.close();
    }
}