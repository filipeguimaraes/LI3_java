import java.util.List;
import java.util.Map;
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
    private static final String CYAN_UNDERLINE = "\033[4;36m";   // Cyan underline

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
        cyan();
        for(int i=COLUNAS;i>0;i--) System.out.print("#");
        resetColor();
        System.out.println();
    }

    private void mudarDeLinha(){
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

    private void printOpcao(int i,Map.Entry<String,Integer> m){
        System.out.println("     "+CYAN+i+")"+RESET+" Produto: "+m.getKey()+CYAN+" | Quantidade: "+RESET+m.getValue());
    }

    private void printOpcaoCli(int i,Map.Entry<String,Integer> m){
        System.out.println("     "+CYAN+i+") Cliente: "+RESET+m.getKey()+CYAN+" | Quantidade: "+RESET+m.getValue());
    }

    private void printOpcaoC(int i,Map.Entry<String,Double> m){
        System.out.println("     "+CYAN+i+") Cliente: "+RESET+m.getKey()+CYAN+" | Valor gasto: "+RESET+m.getValue());
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

    public void imprimeListaProdutos (List<String> ls){
        System.out.println();
        int i=1;
        for (String s : ls){
            String[] aux = s.split(" ");
            System.out.println("     "+CYAN+i+") Produto: "+RESET+aux[0]+CYAN +
                    " | Clientes distintos que o compraram: "+RESET+aux[1]);
            i++;
        }
        System.out.println();
    }

    public void imprimeProdutos(List<Map.Entry<String,Integer>> lm){
        System.out.println();
        int i=1;
        for(Map.Entry<String,Integer> e : lm){
            printOpcao(i,e);
            i++;
        }
        System.out.println();
    }

    public void imprimeClientes(List<Map.Entry<String,Integer>> ls){
        System.out.println();
        int i=1;
        for(Map.Entry<String,Integer> e : ls){
            printOpcaoCli(i,e);
            i++;
        }
        System.out.println();
    }

    public void imprimeClis(List<Map.Entry<String,Double>> ls){
        System.out.println();
        int i=1;
        for(Map.Entry<String,Double> e : ls){
            printOpcaoC(i,e);
            i++;
        }
        System.out.println();
    }


    private void ban(){
        line();
        cyan();
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
        resetColor();
        line();
    }
/*
    public void imprimeTabela(int[] f1,int[] f2, int[] f3, int numero_elementos){

        int numero=(COLUNAS-20);
        int largura=0;
        if (COLUNAS%2 != 0) largura=((numero-7)/4);
        else largura=((numero-6)/4);
        int i;
        espacos(10);for(i=0;i<numero;i++) System.out.println("-");
        espacos(10);for(i=0;i<numero;i++) System.out.println("-");

        espacos(10);
        System.out.print("||");
        espacos((largura/2)-6);
        System.out.print(CYAN_UNDERLINE+"MÊS \\ FILIAL"+RESET);
        espacos((largura/2)-6);
        System.out.print("|");
        espacos((largura/2)-1);
        System.out.print(RED+"1"+RESET);
        espacos(largura/2);
        System.out.print("|");
        espacos((largura/2)-1);
        System.out.print(RED+"2"+RESET);
        espacos(largura/2);
        System.out.print("|");
        espacos((largura/2)-1);
        System.out.print(RED+"3"+RESET);
        espacos(largura/2);
        System.out.println("||");
        espacos(10); for(i=0;i<numero;i++){System.out.println("-");};
        int j;
        for(j=0;j<numero_elementos;j++){
            int a1=f1[j];
            int a2=f2[j];
            int a3=f3[j];
            int i1=0,i2=0,i3=0;
            if(a1==0) i1=1;
            if(a2==0) i2=1;
            if(a3==0) i3=1;
            for(; a1!=0;i1++) a1=a1/10;
            for(; a2!=0;i2++) a2=a2/10;
            for(; a3!=0;i3++) a3=a3/10;
            int aux=1;
            if(j>=9) aux=2;

            espacos(10); System.out.print("||");
            espacos((largura/2)-aux);
            System.out.printf(RED+"%d"+RESET,(j+1));
            espacos((largura/2));System.out.print("|");
            espacos((largura/2)-i1);
            System.out.printf("%d",f1[j]);
            espacos(largura/2);
            System.out.print("|");
            espacos((largura/2)-i2);
            System.out.printf("%d",f2[j]);
            espacos(largura/2);
            System.out.print("|");
            espacos((largura/2)-i3);
            System.out.printf("%d",f3[j]);
            espacos(largura/2);
            System.out.println("||");
            espacos(10);
            for(i=0;i<numero;i++) System.out.println("-");
            for(i=0;i<numero;i++) System.out.println("-");
        }
    }

 */

    public void fim(){
        cyan();
        int number=(LINHAS-10)/2;
        linhas(number);
        line();
        System.out.println("                      $$$$$$\\        $$\\                               $$\\ ");
        System.out.println("                     $$  __$$\\       $$ |                              $$ |");
        System.out.println("                     $$ /  $$ | $$$$$$$ | $$$$$$\\  $$\\   $$\\  $$$$$$$\\ $$ |");
        System.out.println("                     $$$$$$$$ |$$  __$$ |$$  __$$\\ $$ |  $$ |$$  _____|$$ |");
        System.out.println("                     $$  __$$ |$$ /  $$ |$$$$$$$$ |$$ |  $$ |\\$$$$$$\\  \\__|");
        System.out.println("                     $$ |  $$ |$$ |  $$ |$$   ____|$$ |  $$ | \\____$$\\     ");
        System.out.println("                     $$ |  $$ |\\$$$$$$$ |\\$$$$$$$\\ \\$$$$$$  |$$$$$$$  |$$\\ ");
        System.out.println("                     \\__|  \\__| \\_______| \\_______| \\______/ \\_______/ \\__|");
        line();
        linhas(number);
        resetColor();
    }

    private void banMensagem(String descricao){
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
        linhas(number);
        line();
        System.out.print("     Opção pretendida: ");
    }

    public void query1(List<String> produtos){
        clear();
        banMensagem("QUERY 1");
        System.out.println(CYAN+"     Lista ordenada alfabeticamente com os códigos dos produtos nunca comprados"
                +RESET);
        imprimeLista(produtos);
        line();
        System.out.println(CYAN+"     Número de produtos nunca comprados: "+RESET+produtos.size());
    }

    public void query2(int[] global, int[] filial1,int[] filial2,int[] filial3){
        clear();
        banMensagem("QUERY 2");
        mudarDeLinha();
        espacos((COLUNAS-6)/2);
        System.out.println(CYAN_UNDERLINE+"Global"+RESET);
        System.out.println(CYAN+"     Número total de vendas global: "+RESET+global[0]);
        System.out.println(CYAN+"     Número total de clientes envolvidos: "+RESET+global[1]);
        mudarDeLinha();
        line();
        mudarDeLinha();
        espacos((COLUNAS-8)/2);
        System.out.println(CYAN_UNDERLINE+"Filial 1"+RESET);
        System.out.println(CYAN+"     Número total de vendas global: "+RESET+filial1[0]);
        System.out.println(CYAN+"     Número total de clientes envolvidos: "+RESET+filial1[1]);
        mudarDeLinha();
        line();
        mudarDeLinha();
        espacos((COLUNAS-8)/2);
        System.out.println(CYAN_UNDERLINE+"Filial 2"+RESET);
        System.out.println(CYAN+"     Número total de vendas global: "+RESET+filial2[0]);
        System.out.println(CYAN+"     Número total de clientes envolvidos: "+RESET+filial2[1]);
        mudarDeLinha();
        line();
        mudarDeLinha();
        espacos((COLUNAS-8)/2);
        System.out.println(CYAN_UNDERLINE+"Filial 3"+RESET);
        System.out.println(CYAN+"     Número total de vendas global: "+RESET+filial3[0]);
        System.out.println(CYAN+"     Número total de clientes envolvidos: "+RESET+filial3[1]);
        cyan();
        line();
        mudarDeLinha();



    }

    @SuppressWarnings("Duplicates")
    public void query3(List<String> dados){
        clear();
        banMensagem("QUERY 3");
        mudarDeLinha();
        String[] mes1 = dados.get(0).split(" ");
        espacos((COLUNAS-7)/2);
        System.out.println(CYAN_UNDERLINE+"Janeiro"+RESET);
        System.out.println(CYAN+"     Número de compras: "+RESET+mes1[0]);
        System.out.println(CYAN+"     Número de produtos distintos comprados: "+RESET+mes1[1]);
        System.out.println(CYAN+"     Gasto total: "+RESET+mes1[2]);
        mudarDeLinha();
        line();
        mudarDeLinha();
        String[] mes2 = dados.get(1).split(" ");
        espacos((COLUNAS-7)/2);
        System.out.println(CYAN_UNDERLINE+"Fevereiro"+RESET);
        System.out.println(CYAN+"     Número de compras: "+RESET+mes2[0]);
        System.out.println(CYAN+"     Número de produtos distintos comprados: "+RESET+mes2[1]);
        System.out.println(CYAN+"     Gasto total: "+RESET+mes2[2]);
        mudarDeLinha();
        line();
        mudarDeLinha();
        String[] mes3 = dados.get(2).split(" ");
        espacos((COLUNAS-7)/2);
        System.out.println(CYAN_UNDERLINE+"Março"+RESET);
        System.out.println(CYAN+"     Número de compras: "+RESET+mes3[0]);
        System.out.println(CYAN+"     Número de produtos distintos comprados: "+RESET+mes3[1]);
        System.out.println(CYAN+"     Gasto total: "+RESET+mes3[2]);
        mudarDeLinha();
        line();
        mudarDeLinha();
        espacos((COLUNAS-7)/2);
        String[] mes4 = dados.get(3).split(" ");
        System.out.println(CYAN_UNDERLINE+"Abril"+RESET);
        System.out.println(CYAN+"     Número de compras: "+RESET+mes4[0]);
        System.out.println(CYAN+"     Número de produtos distintos comprados: "+RESET+mes4[1]);
        System.out.println(CYAN+"     Gasto total: "+RESET+mes4[2]);
        mudarDeLinha();
        line();
        mudarDeLinha();
        espacos((COLUNAS-7)/2);
        String[] mes5 = dados.get(4).split(" ");
        System.out.println(CYAN_UNDERLINE+"Maio"+RESET);
        System.out.println(CYAN+"     Número de compras: "+RESET+mes5[0]);
        System.out.println(CYAN+"     Número de produtos distintos comprados: "+RESET+mes5[1]);
        System.out.println(CYAN+"     Gasto total: "+RESET+mes5[2]);
        mudarDeLinha();
        line();
        mudarDeLinha();
        espacos((COLUNAS-7)/2);
        String[] mes6 = dados.get(5).split(" ");
        System.out.println(CYAN_UNDERLINE+"Junho"+RESET);
        System.out.println(CYAN+"     Número de compras: "+RESET+mes6[0]);
        System.out.println(CYAN+"     Número de produtos distintos comprados: "+RESET+mes6[1]);
        System.out.println(CYAN+"     Gasto total: "+RESET+mes6[2]);
        mudarDeLinha();
        line();
        mudarDeLinha();
        espacos((COLUNAS-7)/2);
        String[] mes7 = dados.get(6).split(" ");
        System.out.println(CYAN_UNDERLINE+"Julho"+RESET);
        System.out.println(CYAN+"     Número de compras: "+RESET+mes7[0]);
        System.out.println(CYAN+"     Número de produtos distintos comprados: "+RESET+mes7[1]);
        System.out.println(CYAN+"     Gasto total: "+RESET+mes7[2]);
        mudarDeLinha();
        line();
        mudarDeLinha();
        espacos((COLUNAS-7)/2);
        String[] mes8 = dados.get(7).split(" ");
        System.out.println(CYAN_UNDERLINE+"Agosto"+RESET);
        System.out.println(CYAN+"     Número de compras: "+RESET+mes8[0]);
        System.out.println(CYAN+"     Número de produtos distintos comprados: "+RESET+mes8[1]);
        System.out.println(CYAN+"     Gasto total: "+RESET+mes8[2]);
        mudarDeLinha();
        line();
        mudarDeLinha();
        espacos((COLUNAS-7)/2);
        String[] mes9 = dados.get(8).split(" ");
        System.out.println(CYAN_UNDERLINE+"Setembro"+RESET);
        System.out.println(CYAN+"     Número de compras: "+RESET+mes9[0]);
        System.out.println(CYAN+"     Número de produtos distintos comprados: "+RESET+mes9[1]);
        System.out.println(CYAN+"     Gasto total: "+RESET+mes9[2]);
        mudarDeLinha();
        line();
        mudarDeLinha();
        espacos((COLUNAS-7)/2);
        String[] mes10 = dados.get(9).split(" ");
        System.out.println(CYAN_UNDERLINE+"Outubro"+RESET);
        System.out.println(CYAN+"     Número de compras: "+RESET+mes10[0]);
        System.out.println(CYAN+"     Número de produtos distintos comprados: "+RESET+mes10[1]);
        System.out.println(CYAN+"     Gasto total: "+RESET+mes10[2]);
        mudarDeLinha();
        line();
        mudarDeLinha();
        espacos((COLUNAS-7)/2);
        String[] mes11 = dados.get(10).split(" ");
        System.out.println(CYAN_UNDERLINE+"Novembro"+RESET);
        System.out.println(CYAN+"     Número de compras: "+RESET+mes11[0]);
        System.out.println(CYAN+"     Número de produtos distintos comprados: "+RESET+mes11[1]);
        System.out.println(CYAN+"     Gasto total: "+RESET+mes11[2]);
        mudarDeLinha();
        line();
        mudarDeLinha();
        espacos((COLUNAS-7)/2);
        String[] mes12 = dados.get(11).split(" ");
        System.out.println(CYAN_UNDERLINE+"Dezembro"+RESET);
        System.out.println(CYAN+"     Número de compras: "+RESET+mes12[0]);
        System.out.println(CYAN+"     Número de produtos distintos comprados: "+RESET+mes12[1]);
        System.out.println(CYAN+"     Gasto total: "+RESET+mes12[2]);
        mudarDeLinha();
        line();
        mudarDeLinha();
    }

    public void query4(List<String> dados){
        clear();
        banMensagem("QUERY 4");
        mudarDeLinha();
        String[] mes1 = dados.get(0).split(" ");
        espacos((COLUNAS-7)/2);
        System.out.println(CYAN_UNDERLINE+"Janeiro"+RESET);
        System.out.println(CYAN+"     Número de vezes que este produto foi adquirido: "+RESET+mes1[0]);
        System.out.println(CYAN+"     Número de clientes distintos que adquiriram este produto: "+RESET+mes1[1]);
        System.out.println(CYAN+"     Total faturado: "+RESET+mes1[2]);
        mudarDeLinha();
        line();
        mudarDeLinha();
        String[] mes2 = dados.get(1).split(" ");
        espacos((COLUNAS-7)/2);
        System.out.println(CYAN_UNDERLINE+"Fevereiro"+RESET);
        System.out.println(CYAN+"     Número de vezes que este produto foi adquirido: "+RESET+mes2[0]);
        System.out.println(CYAN+"     Número de clientes distintos que adquiriram este produto: "+RESET+mes2[1]);
        System.out.println(CYAN+"     Total faturado: "+RESET+mes2[2]);
        mudarDeLinha();
        line();
        mudarDeLinha();
        String[] mes3 = dados.get(2).split(" ");
        espacos((COLUNAS-7)/2);
        System.out.println(CYAN_UNDERLINE+"Março"+RESET);
        System.out.println(CYAN+"     Número de vezes que este produto foi adquirido: "+RESET+mes3[0]);
        System.out.println(CYAN+"     Número de clientes distintos que adquiriram este produto: "+RESET+mes3[1]);
        System.out.println(CYAN+"     Total faturado: "+RESET+mes3[2]);
        mudarDeLinha();
        line();
        mudarDeLinha();
        espacos((COLUNAS-7)/2);
        String[] mes4 = dados.get(3).split(" ");
        System.out.println(CYAN_UNDERLINE+"Abril"+RESET);
        System.out.println(CYAN+"     Número de vezes que este produto foi adquirido: "+RESET+mes4[0]);
        System.out.println(CYAN+"     Número de clientes distintos que adquiriram este produto: "+RESET+mes4[1]);
        System.out.println(CYAN+"     Total faturado: "+RESET+mes4[2]);
        mudarDeLinha();
        line();
        mudarDeLinha();
        espacos((COLUNAS-7)/2);
        String[] mes5 = dados.get(4).split(" ");
        System.out.println(CYAN_UNDERLINE+"Maio"+RESET);
        System.out.println(CYAN+"     Número de vezes que este produto foi adquirido: "+RESET+mes5[0]);
        System.out.println(CYAN+"     Número de clientes distintos que adquiriram este produto: "+RESET+mes5[1]);
        System.out.println(CYAN+"     Total faturado: "+RESET+mes5[2]);
        mudarDeLinha();
        line();
        mudarDeLinha();
        espacos((COLUNAS-7)/2);
        String[] mes6 = dados.get(5).split(" ");
        System.out.println(CYAN_UNDERLINE+"Junho"+RESET);
        System.out.println(CYAN+"     Número de vezes que este produto foi adquirido: "+RESET+mes6[0]);
        System.out.println(CYAN+"     Número de clientes distintos que adquiriram este produto: "+RESET+mes6[1]);
        System.out.println(CYAN+"     Total faturado: "+RESET+mes6[2]);
        mudarDeLinha();
        line();
        mudarDeLinha();
        espacos((COLUNAS-7)/2);
        String[] mes7 = dados.get(6).split(" ");
        System.out.println(CYAN_UNDERLINE+"Julho"+RESET);
        System.out.println(CYAN+"     Número de vezes que este produto foi adquirido: "+RESET+mes7[0]);
        System.out.println(CYAN+"     Número de clientes distintos que adquiriram este produto: "+RESET+mes7[1]);
        System.out.println(CYAN+"     Total faturado: "+RESET+mes7[2]);
        mudarDeLinha();
        line();
        mudarDeLinha();
        espacos((COLUNAS-7)/2);
        String[] mes8 = dados.get(7).split(" ");
        System.out.println(CYAN_UNDERLINE+"Agosto"+RESET);
        System.out.println(CYAN+"     Número de vezes que este produto foi adquirido: "+RESET+mes8[0]);
        System.out.println(CYAN+"     Número de clientes distintos que adquiriram este produto: "+RESET+mes8[1]);
        System.out.println(CYAN+"     Total faturado: "+RESET+mes8[2]);
        mudarDeLinha();
        line();
        mudarDeLinha();
        espacos((COLUNAS-7)/2);
        String[] mes9 = dados.get(8).split(" ");
        System.out.println(CYAN_UNDERLINE+"Setembro"+RESET);
        System.out.println(CYAN+"     Número de vezes que este produto foi adquirido: "+RESET+mes9[0]);
        System.out.println(CYAN+"     Número de clientes distintos que adquiriram este produto: "+RESET+mes9[1]);
        System.out.println(CYAN+"     Total faturado: "+RESET+mes9[2]);
        mudarDeLinha();
        line();
        mudarDeLinha();
        espacos((COLUNAS-7)/2);
        String[] mes10 = dados.get(9).split(" ");
        System.out.println(CYAN_UNDERLINE+"Outubro"+RESET);
        System.out.println(CYAN+"     Número de vezes que este produto foi adquirido: "+RESET+mes10[0]);
        System.out.println(CYAN+"     Número de clientes distintos que adquiriram este produto: "+RESET+mes10[1]);
        System.out.println(CYAN+"     Total faturado: "+RESET+mes10[2]);
        mudarDeLinha();
        line();
        mudarDeLinha();
        espacos((COLUNAS-7)/2);
        String[] mes11 = dados.get(10).split(" ");
        System.out.println(CYAN_UNDERLINE+"Novembro"+RESET);
        System.out.println(CYAN+"     Número de vezes que este produto foi adquirido: "+RESET+mes11[0]);
        System.out.println(CYAN+"     Número de clientes distintos que adquiriram este produto: "+RESET+mes11[1]);
        System.out.println(CYAN+"     Total faturado: "+RESET+mes11[2]);
        mudarDeLinha();
        line();
        mudarDeLinha();
        espacos((COLUNAS-7)/2);
        String[] mes12 = dados.get(11).split(" ");
        System.out.println(CYAN_UNDERLINE+"Dezembro"+RESET);
        System.out.println(CYAN+"     Número de vezes que este produto foi adquirido: "+RESET+mes12[0]);
        System.out.println(CYAN+"     Número de clientes distintos que adquiriram este produto: "+RESET+mes12[1]);
        System.out.println(CYAN+"     Total faturado: "+RESET+mes12[2]);
        mudarDeLinha();
        line();
        mudarDeLinha();

    }

    public void query5(List<Map.Entry<String,Integer>> dados){
        clear();
        banMensagem("QUERY 5");
        imprimeProdutos(dados);
        line();
        System.out.println(CYAN+"     Número de produtos: "+RESET+dados.size());
        line();
    }

    public void query6(List<String> dados){
        clear();
        banMensagem("QUERY 6");
        imprimeListaProdutos(dados);
        line();
    }

    public void top3(String clientes){
        mudarDeLinha();
        String[] aux = clientes.split(" ");
        espacos((COLUNAS-12)/2);
        printOpcao(1,aux[0]);
        espacos((COLUNAS-12)/2);
        printOpcao(2,aux[1]);
        espacos((COLUNAS-12)/2);
        printOpcao(3,aux[2]);
        mudarDeLinha();
    }

    public void query7(List<String> dados){
        clear();
        banMensagem("QUERY 7");
        mudarDeLinha();
        espacos((COLUNAS-8)/2);
        System.out.println(CYAN_UNDERLINE+"Filial 1"+RESET);
        top3(dados.get(0));
        mudarDeLinha();
        line();
        espacos((COLUNAS-8)/2);
        System.out.println(CYAN_UNDERLINE+"Filial 2"+RESET);
        top3(dados.get(1));
        mudarDeLinha();
        line();
        espacos((COLUNAS-8)/2);
        System.out.println(CYAN_UNDERLINE+"Filial 3"+RESET);
        top3(dados.get(2));
        mudarDeLinha();
        line();
        mudarDeLinha();

    }

    public void query8(List<Map.Entry<String,Integer>> dados){
        clear();
        banMensagem("QUERY 8");
        mudarDeLinha();
        imprimeClientes(dados);
        line();

    }

    public void query9(List<Map.Entry<String,Double>> dados){
        clear();
        banMensagem("QUERY 9");
        mudarDeLinha();
        imprimeClis(dados);
        line();
    }


    public void carregaVendas(){
        clear();
        banMensagem("Introduzir nome do ficheiro de vendas");
        System.out.println();
        System.out.print("Nome do ficheiro"+RED+"(por defeito: \"Vendas_1M.txt\")"+RESET+": ");

    }

    public void carregaProdutos(){
        clear();
        banMensagem("Introduzir nome do ficheiro de vendas");
        System.out.println();
        System.out.print("Nome do ficheiro"+RED+"(por defeito: \"Produtos.txt\")"+RESET+": ");

    }

    public void carregaClientes(){
        clear();
        banMensagem("Introduzir nome do ficheiro de vendas");
        System.out.println();
        System.out.print("Nome do ficheiro"+RED+"(por defeito: \"Clientes.txt\")"+RESET+": ");

    }

    public void carregaPreDefinidos(){
        clear();
        banMensagem("Carregar ficheiros");
        System.out.println();
        System.out.println("Deseja carregar ficheiros pré-definidos? "+ RED +"(Sim: 1 | Não: 0)" + RESET);

    }

    public void recebeMes(){
        clear();
        banMensagem("Introduzir mês");
        mudarDeLinha();
        System.out.print("Introduza o mês: ");

    }

    public void recebeCliente(){
        clear();
        banMensagem("Introduzir código de cliente");
        mudarDeLinha();
        System.out.print("Introduza o código de cliente: ");
    }

    public void recebeProduto(){
        clear();
        banMensagem("Introduzir código de produto");
        mudarDeLinha();
        System.out.print("Introduza o código de produto: ");
    }

    public void recebeIntProd(){
        clear();
        banMensagem("Introduzir número de produtos");
        mudarDeLinha();
        System.out.print("Introduza o número de produtos: ");
    }

    public void recebeIntCli(){
        clear();
        banMensagem("Introduzir número de clientes");
        mudarDeLinha();
        System.out.print("Introduza o número de clientes: ");
    }

}
