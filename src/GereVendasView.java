import java.util.List;
import java.util.Scanner;

/**
 * Write a description of class GereVendasView here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GereVendasView implements InterfGereVendasView {

    private static final int COLUNAS=182;
    private static final int LINHAS=42;

    private static final String RESET = "\033[0m";  // Text Reset
    private static final String RED = "\033[0;31m";     // RED
    private static final String CYAN = "\033[1;36m";   // Cyan Bold

    private void espacos(int quantidade){
        for(int i=0;i<quantidade;i++) System.out.print(" ");
    }

    private void linhas(int quantidade){
        for(int i = quantidade; i>0; i--);
    }

    /**
     * Texto a azul
     */
    private void cyan(){
        System.out.println(CYAN);
    }

    /**
     * Volta a colocar a cor "normal"
     */
    private void resetColor(){
        System.out.println(RESET);
    }

    private void line(){
        for(int i=COLUNAS;i>0;i--) System.out.print("#");
        System.out.println();
    }

    private void clear(){
        for(int i=COLUNAS;i>0;i--) System.out.println();
    }

    /**
     * Formata uma opção
     * @param i Numero da opção
     * @param s Opção
     */
    private void printOpcao(int i,String s){
        System.out.println("     "+CYAN+i+")"+RESET+"  "+s);
    }

    public void imprimeLista(List<String> ls){
        System.out.println();
        int i=1;
        for (String s : ls) {
            printOpcao(i, s);
            i++;
        }
        System.out.println();
    }

    private void ban(){
        cyan();
        line();
        espacos((COLUNAS/2)-15);
        System.out.println(" $$$$$$\\\\   $$$$$$\\  $$\\    $$\\ ");
        espacos((COLUNAS/2)-15);
        System.out.println("$$  __$$\\ $$  __$$\\ $$ |   $$ |");
        espacos((COLUNAS/2)-15);
        System.out.println("$$ /  \\__|$$ /  \\__|$$ |   $$ |");
        espacos((COLUNAS/2)-15);
        System.out.println("\\$$$$$$\\  $$ |$$$$\\ \\$$\\  $$  |");
        espacos((COLUNAS/2)-15);
        System.out.println(" \\____$$\\ $$ |\\_$$ | \\$$\\$$  / ");
        espacos((COLUNAS/2)-15);
        System.out.println("$$\\   $$ |$$ |  $$ |  \\$$$  /  ");
        espacos((COLUNAS/2)-15);
        System.out.println("\\$$$$$$  |\\$$$$$$  |   \\$  /   ");
        espacos((COLUNAS/2)-15);
        System.out.println(" \\______/  \\______/     \\_/");
        line();
        resetColor();
    }

    private void banQueries(String descricao){
        cyan();
        line();
        int size = descricao.length();
        espacos((COLUNAS-size)/2);
        System.out.println(descricao);
        line();
        resetColor();
    }

    public void menuOpcoes(String[] opcoes){
        clear();
        ban();
        int number= ((LINHAS - 18) - opcoes.length) / 2;
        int i=1;
        linhas(number);
        for(String s: opcoes){
            printOpcao(i,s);
            i++;
        }
        cyan();
        linhas(number);
        line();
        resetColor();
        System.out.print("     Opção pretendida: ");
    }

    public void query1(List<String> produtos){
        clear();
        banQueries("QUERY 1");
        System.out.println(CYAN+"     Lista ordenada alfabeticamente com os códigos dos produtos nunca comprados"
                +RESET);
        imprimeLista(produtos);
        cyan();
        line();
        resetColor();
        System.out.println(CYAN+"     Número de produtos nunca comprados: "+RESET+produtos.size());
    }

}
