
/**
 * Write a description of class GereVendasView here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GereVendasView
{

    private static final int COLUNAS=42;
    private static final int ECRA=42;

    private void espacos(int quantidade){
        int i;
        for(i=0;i<quantidade;i++) System.out.print(" ");
    }

    /**
     * Texto a azul
     */
    public void cyan(){
        System.out.println("\033[0;36m");
    }

    private void line(){
        for(int i=COLUNAS;i>0;i--) System.out.println("#");
    }

    public void ban(){
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
    }
/*
    public void mainMenu(String[] opcoes){
        clear(ECRA);
        ban();
        int number= ((ECRA - 18) - opcoes.length) / 2;
        int i=1;
        clear(number);
        for(String s: opcoes){
            printOpcao(i,s);
            i++;
        }
        cyan();
        clear(number);
        line(1);
        resetColor();
        System.out.println("     Opção pretendida: ");
    }

 */
}
