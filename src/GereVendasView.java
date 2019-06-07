import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CyclicBarrier;

/**
 * Write a description of class GereVendasView here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GereVendasView implements InterfGereVendasView {

    private static final int COLUNAS=182; //Número de colunas
    private static final int LINHAS=42; //Número de linhas

    private static final String RESET = "\033[0m"; // Text Reset
    private static final String RED = "\033[0;31m"; // RED
    private static final String CYAN = "\033[1;36m"; // Cyan Bold
    private static final String CYAN_UNDERLINE = "\033[4;36m"; // Cyan underline

    /**
     * Imprime a quantidade recebida de espaços
     * @param quantidade Quantidade e espaços
     */
    private void espacos(int quantidade){
        for(int i=0;i<quantidade;i++) System.out.print(" ");
    }

    /**
     * Imprime linhas em branco
     * @param quantidade Quantidade de linhas
     */
    private void linhas(int quantidade){
        for(int i = quantidade; i>0; i--) System.out.println();
    }

    /**
     * Texto a azul
     */
    private void cyan(){
        System.out.print(CYAN);
    }

    /**
     * Volta a colocar a cor "normal"
     */
    private void resetColor(){
        System.out.print(RESET);
    }

    /**
     * Faz uma linha de #
     */
    private void line(){
        cyan();
        for(int i=COLUNAS;i>0;i--) System.out.print("#");
        resetColor();
    }

    /**
     * Avança para a próxima linha
     */
    private void mudarDeLinha(){
        System.out.println();
    }

    /**
     * Limpa o ecrã
     */
    private void clear(){
        for(int i=LINHAS;i>0;i--) System.out.println();
    }

    /**
     * Formata uma opção
     * @param i Numero da opção
     * @param s Opção
     */
    private void printOpcao(int i,String s){
        System.out.println("     "+CYAN+i+")"+RESET+"  "+s);
    }

    /**
     * Formata uma opção para um produto
     * @param i Numero da opção
     * @param m Opção
     */
    private void printOpcao(int i,Map.Entry<String,Integer> m){
        System.out.println("     "+CYAN+i+")"+" Produto: "+RESET+m.getKey()+CYAN+" | Quantidade: "+RESET+m.getValue());
    }

    /**
     * Formata uma opção para um cliente e a quantidade
     * @param i Numero da opção
     * @param m Opção
     */
    private void printOpcaoCli(int i,Map.Entry<String,Integer> m){
        System.out.println("     "+CYAN+i+") Cliente: "+RESET+m.getKey()+CYAN+" | Quantidade: "+RESET+m.getValue());
    }

    /**
     * Formata uma opção para um cliente e o valor gasto
     * @param i Numero da opção
     * @param m Opção
     */
    private void printOpcaoC(int i,Map.Entry<String,Double> m){
        System.out.println("     "+CYAN+i+") Cliente: "+RESET+m.getKey()+CYAN+" | Valor gasto: "+RESET+m.getValue());
    }

    private void printOpcao(int i, int s){
        System.out.println("     "+CYAN+i+") Mês: "+RESET+i+CYAN+" | Total de compras: "+RESET+s);
    }

    /**
     * Imprime uma lista de opcões
     * @param ls Lista de opções
     */
    private void imprimeLista(List<String> ls){
        System.out.println();
        int i=1;
        for (String s : ls) {
            printOpcao(i, s);
            i++;
        }
        System.out.println();
    }

    private void imprimeListaInteiros(List<Integer> ls){
        System.out.println();
        int i=1;
        for (Integer s : ls) {
            printOpcao(i, s);
            i++;
        }
        System.out.println();
    }

    /**
     * Imprime uma lista de produtos bem como os clientes distintos que o compraram
     * @param ls Lista de opções
     */
    private void imprimeListaProdutos (List<String> ls){
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

    /**
     * Imprime uma lista de produtos
     * @param lm Lista de produtos
     */
    private void imprimeProdutos(List<Map.Entry<String,Integer>> lm){
        System.out.println();
        int i=1;
        for(Map.Entry<String,Integer> e : lm){
            printOpcao(i,e);
            i++;
        }
        System.out.println();
    }

    /**
     * Imprime uma lista de clientes
     * @param ls Lista de clientes
     */
    private void imprimeClientes(List<Map.Entry<String,Integer>> ls){
        System.out.println();
        int i=1;
        for(Map.Entry<String,Integer> e : ls){
            printOpcaoCli(i,e);
            i++;
        }
        System.out.println();
    }

    /**
     * Imprime uma lista de clientes
     * @param ls Lista de clientes
     */
    private void imprimeClis(List<Map.Entry<String,Double>> ls){
        System.out.println();
        int i=1;
        for(Map.Entry<String,Double> e : ls){
            printOpcaoC(i,e);
            i++;
        }
        System.out.println();
    }

    private void ban(){
        line(); mudarDeLinha();
        cyan();
        espacos((COLUNAS-99)/2);
        System.out.println(" $$$$$$\\                        $$\\   $$\\    $$\\                           $$\\                     ");
        espacos((COLUNAS-99)/2);
        System.out.println("$$  __$$\\                       $$ |  $$ |   $$ |                          $$ |                    ");
        espacos((COLUNAS-99)/2);
        System.out.println("$$ /  \\__| $$$$$$\\   $$$$$$$\\ $$$$$$\\ $$ |   $$ | $$$$$$\\  $$$$$$$\\   $$$$$$$ | $$$$$$\\   $$$$$$$\\ ");
        espacos((COLUNAS-99)/2);
        System.out.println("$$ |$$$$\\ $$  __$$\\ $$  _____|\\_$$  _|\\$$\\  $$  |$$  __$$\\ $$  __$$\\ $$  __$$ | \\____$$\\ $$  _____|");
        espacos((COLUNAS-99)/2);
        System.out.println("$$ |\\_$$ |$$$$$$$$ |\\$$$$$$\\    $$ |   \\$$\\$$  / $$$$$$$$ |$$ |  $$ |$$ /  $$ | $$$$$$$ |\\$$$$$$\\  ");
        espacos((COLUNAS-99)/2);
        System.out.println("$$ |  $$ |$$   ____| \\____$$\\   $$ |$$\\ \\$$$  /  $$   ____|$$ |  $$ |$$ |  $$ |$$  __$$ | \\____$$\\ ");
        espacos((COLUNAS-99)/2);
        System.out.println("\\$$$$$$  |\\$$$$$$$\\ $$$$$$$  |  \\$$$$  | \\$  /   \\$$$$$$$\\ $$ |  $$ |\\$$$$$$$ |\\$$$$$$$ |$$$$$$$  |");
        espacos((COLUNAS-99)/2);
        System.out.println(" \\______/  \\_______|\\_______/    \\____/   \\_/     \\_______|\\__|  \\__| \\_______| \\_______|\\_______/ ");
        resetColor();
        line();
    }

    @SuppressWarnings("Duplicates")
    private void imprimeTabela(String[] f1,String[] f2, String[] f3){
        int numero_elementos =12;
        DecimalFormat df = new DecimalFormat("#.##");
        int numero=(COLUNAS-30);
        int largura= ((numero-8)/4);
        int i;
        espacos(10);for(i=0;i<numero;i++) System.out.print("-");
        mudarDeLinha();
        espacos(10);for(i=0;i<numero;i++) System.out.print("-");
        mudarDeLinha();
        espacos(10);
        System.out.print("||");
        espacos((largura/2)-6);
        System.out.print(CYAN_UNDERLINE+"MÊS \\ FILIAL"+RESET);
        espacos((largura/2)-6);
        System.out.print("|");
        espacos((largura/2));
        System.out.print(RED+"1"+RESET);
        espacos(largura/2);
        System.out.print("|");
        espacos((largura/2));
        System.out.print(RED+"2"+RESET);
        espacos(largura/2);
        System.out.print("|");
        espacos((largura/2));
        System.out.print(RED+"3"+RESET);
        espacos(largura/2);
        System.out.println("||");
        espacos(10);
        for(i=0;i<numero;i++) System.out.print("-");
        mudarDeLinha();
        for(int j=0;j<numero_elementos;j++){
            int aux=1;
            if(j>=9) aux=2;

            espacos(10); System.out.print("||");
            espacos((largura/2)-aux); System.out.printf(RED+"%d"+RESET,(j+1));
            espacos((largura/2));System.out.print("|");

            String aux1 = df.format(Double.valueOf(f1[j]));
            int espacos1 = aux1.length();
            espacos((largura/2)-(espacos1/2));
            System.out.print(aux1);
            espacos((largura/2)-(espacos1/2));

            System.out.print("|");

            String aux2 = df.format(Double.valueOf(f2[j]));
            int espacos2 = aux2.length();
            espacos((largura/2)-(espacos2/2));
            System.out.print(aux2);
            espacos(largura/2-(espacos2/2));

            System.out.print("|");

            String aux3 = df.format(Double.valueOf(f3[j]));
            int espacos3 = aux3.length();
            espacos((largura/2)-(espacos3/2));
            System.out.print(aux3);
            espacos(largura/2-(espacos3/2));

            System.out.println("||");

            espacos(10); for(i=0;i<numero;i++) System.out.print("-");
            mudarDeLinha();
        }
        espacos(10); for(i=0;i<numero;i++) System.out.print("-");
        mudarDeLinha();
    }


    public void fim(){
        cyan();
        int number=(LINHAS-10)/2;
        linhas(number);
        line(); mudarDeLinha();
        cyan();
        espacos((COLUNAS-54)/2);
        System.out.println("                     $$$$$$\\        $$\\                               $$\\ ");
        espacos((COLUNAS-54)/2);
        System.out.println("                     $$  __$$\\       $$ |                              $$ |");
        espacos((COLUNAS-54)/2);
        System.out.println("                     $$ /  $$ | $$$$$$$ | $$$$$$\\  $$\\   $$\\  $$$$$$$\\ $$ |");
        espacos((COLUNAS-54)/2);
        System.out.println("                     $$$$$$$$ |$$  __$$ |$$  __$$\\ $$ |  $$ |$$  _____|$$ |");
        espacos((COLUNAS-54)/2);
        System.out.println("                     $$  __$$ |$$ /  $$ |$$$$$$$$ |$$ |  $$ |\\$$$$$$\\  \\__|");
        espacos((COLUNAS-54)/2);
        System.out.println("                     $$ |  $$ |$$ |  $$ |$$   ____|$$ |  $$ | \\____$$\\     ");
        espacos((COLUNAS-54)/2);
        System.out.println("                     $$ |  $$ |\\$$$$$$$ |\\$$$$$$$\\ \\$$$$$$  |$$$$$$$  |$$\\ ");
        espacos((COLUNAS-54)/2);
        System.out.println("                     \\__|  \\__| \\_______| \\_______| \\______/ \\_______/ \\__|");
        resetColor();
        line();
        linhas(number);
        resetColor();
    }

    private void banMensagem(String descricao){
        cyan();
        line(); mudarDeLinha();
        int size = descricao.length();
        espacos((COLUNAS-size)/2);
        System.out.println(descricao);
        line();
        resetColor();
    }

    public void menuOpcoes(String[] opcoes){
        clear();
        ban();
        int number= ((LINHAS - 12) - opcoes.length) / 2;
        int i=1;
        linhas(number);
        for(String s: opcoes){
            printOpcao(i,s);
            i++;
        }
        linhas(number);
        line();
        mudarDeLinha();
        System.out.print("     Opção pretendida: ");
    }

    public void query1(List<String> produtos){
        clear();
        banMensagem("QUERY 1");
        System.out.println(
                CYAN+"     Lista ordenada alfabeticamente com os códigos dos produtos nunca comprados" +RESET);
        imprimeLista(produtos);
        line();
        mudarDeLinha();
        System.out.println(CYAN+"     Número de produtos nunca comprados: "+RESET+produtos.size());
    }

    @SuppressWarnings("Duplicates")
    public void query2(int[] global, int[] filial1,int[] filial2,int[] filial3){
        clear();
        banMensagem("QUERY 2");
        mudarDeLinha();
        linhas(2);
        System.out.println(CYAN_UNDERLINE+"Global"+RESET);
        System.out.println(CYAN+"     Número total de vendas global: "+RESET+global[0]);
        System.out.println(CYAN+"     Número total de clientes envolvidos: "+RESET+global[1]);
        linhas(3);
        line();
        linhas(3);
        System.out.println(CYAN_UNDERLINE+"Filial 1"+RESET);
        System.out.println(CYAN+"     Número total de vendas global: "+RESET+filial1[0]);
        System.out.println(CYAN+"     Número total de clientes envolvidos: "+RESET+filial1[1]);
        linhas(3);
        line();
        linhas(3);
        System.out.println(CYAN_UNDERLINE+"Filial 2"+RESET);
        System.out.println(CYAN+"     Número total de vendas global: "+RESET+filial2[0]);
        System.out.println(CYAN+"     Número total de clientes envolvidos: "+RESET+filial2[1]);
        linhas(3);
        line();
        linhas(3);
        System.out.println(CYAN_UNDERLINE+"Filial 3"+RESET);
        System.out.println(CYAN+"     Número total de vendas global: "+RESET+filial3[0]);
        System.out.println(CYAN+"     Número total de clientes envolvidos: "+RESET+filial3[1]);
        linhas(2);
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

    @SuppressWarnings("Duplicates")
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
        line(); mudarDeLinha();
    }

    public void query6(List<String> dados){
        clear();
        int number = (LINHAS-dados.size()-9)/2;
        banMensagem("QUERY 6");
        linhas(number);
        imprimeListaProdutos(dados);
        linhas(number);
        line(); mudarDeLinha();
    }

    private void top3(String clientes){
        mudarDeLinha();
        String[] aux = clientes.split(" ");
        printOpcao(1,aux[0]);
        printOpcao(2,aux[1]);
        printOpcao(3,aux[2]);
        mudarDeLinha();
    }

    public void query7(List<String> dados){
        clear();
        banMensagem("QUERY 7");
        linhas(3);
        System.out.println(CYAN_UNDERLINE+"Filial 1"+RESET);
        top3(dados.get(0));
        linhas(2);
        line();
        linhas(3);
        System.out.println(CYAN_UNDERLINE+"Filial 2"+RESET);
        top3(dados.get(1));
        linhas(2);
        line();
        linhas(3);
        System.out.println(CYAN_UNDERLINE+"Filial 3"+RESET);
        top3(dados.get(2));
        linhas(2);
        line();
        mudarDeLinha();
    }

    public void query8(List<Map.Entry<String,Integer>> dados){
        clear();
        banMensagem("QUERY 8");
        int number = (LINHAS -dados.size()-9)/2;
        linhas(number);
        imprimeClientes(dados);
        linhas(number);
        line(); mudarDeLinha();
    }

    public void query9(List<Map.Entry<String,Double>> dados){
        clear();
        banMensagem("QUERY 9");
        int number = (LINHAS-dados.size()-9)/2;
        linhas(number);
        imprimeClis(dados);
        linhas(number);
        line(); mudarDeLinha();
    }

    public void query10(String dados){
        clear();
        banMensagem("QUERY 10");
        linhas(3);
        String[] aux = dados.split(":");
        String produto = aux[0];
        String[] dadosMes = aux[1].split("#");
        String[] filial1 = new String[12];
        String[] filial2 = new String[12];
        String[] filial3 = new String[12];
        for (int i=0; i<12; i++){
            String[] dadosMesFilial = dadosMes[i].split(";");
            filial1[i]=dadosMesFilial[0];
            filial2[i]=dadosMesFilial[1];
            filial3[i]=dadosMesFilial[2];
        }
        System.out.println("          "+CYAN_UNDERLINE+"Produto:"+RESET+" "+produto);
        imprimeTabela(filial1,filial2,filial3);
        linhas(3);
        line();
        mudarDeLinha();
        System.out.println("Pagina Anterior "+RED+"(1)"+RESET+" | Pagina Seguinte "+RED+"(2)"+RESET
                +" | Avançar para um produto "+RED+"(3)"+RESET+" | Sair "+RED+"(0)"+RESET);
    }

    public void totalComprasMes(List<Integer> dados){
        clear();
        banMensagem("Número total de compras por mês");
        mudarDeLinha();
        int numero = (LINHAS-dados.size()-7)/2;
        linhas(numero);
        imprimeListaInteiros(dados);
        linhas(numero);
        line();
        mudarDeLinha();
    }

    public void info(List<Integer> prods, List<Integer> clis, List<Double> fat, List<String> vendas){
        clear();
        banMensagem("Informações sobre o último ficheiro de vendas lido");
        mudarDeLinha();
        System.out.println(CYAN+"Nome do ficheiro: "+RESET+vendas.get(0));
        System.out.println(CYAN+"Número total de registos de venda errados: "+RESET+vendas.get(1));
        System.out.println(CYAN+"Número total de produtos: "+RESET+prods.get(0));
        System.out.println(CYAN+"Número total de diferentes produtos comprados: "+RESET+prods.get(1));
        System.out.println(CYAN+"Número total de produtos não comprados: "+RESET+prods.get(2));
        System.out.println(CYAN+"Número total de clientes: "+RESET+clis.get(0));
        System.out.println(CYAN+"Número total de clientes que realizaram compras: "+RESET+clis.get(1));
        System.out.println(CYAN+"Número total de clientes que nada compraram: "+RESET+clis.get(2));
        System.out.println(CYAN+"Número total de compras de valor total igual a 0: "+RESET+fat.get(0));
        System.out.println(CYAN+"Faturação total: "+RESET+fat.get(1));
    }

    public void fatTotal(List<Double> dados){
        clear();
        banMensagem("Faturação total por mês para cada filial e o valor total global");
        mudarDeLinha();
        linhas(3);
        System.out.println(CYAN_UNDERLINE+"Global"+RESET);
        System.out.println(dados.get(0));
        linhas(3);
        line();
        linhas(3);
        System.out.println(CYAN_UNDERLINE+"Filial 1"+RESET);
        System.out.println(dados.get(1));
        linhas(3);
        line();
        linhas(3);
        System.out.println(CYAN_UNDERLINE+"Filial 2"+RESET);
        System.out.println(dados.get(2));
        linhas(3);
        line();
        linhas(3);
        System.out.println(CYAN_UNDERLINE+"Filial 3"+RESET);
        System.out.println(dados.get(3));
        linhas(3);
        line();
        mudarDeLinha();

    }

    public void distintosCli(List<String> dados){
        clear();
        banMensagem("Número de clientes distintos que compraram em cada mês filial a filial");
        String[] f1 = dados.get(0).split(" ");
        String[] f2 = dados.get(1).split(" ");
        String[] f3 = dados.get(2).split(" ");
        linhas(2);
        mudarDeLinha();
        imprimeTabela(f1,f2,f3);
        linhas(2);
        line();
        mudarDeLinha();
    }

    public void carregaVendas(){
        mudarDeLinha();
        mudarDeLinha();
        banMensagem("Introduzir nome do ficheiro de vendas");
        mudarDeLinha();
        System.out.print("Nome do ficheiro"+RED+"(por defeito: \"Vendas_1M.txt\")"+RESET+": ");
    }

    public void carregaProdutos(){
        mudarDeLinha();
        mudarDeLinha();
        banMensagem("Introduzir nome do ficheiro de vendas");
        mudarDeLinha();
        System.out.print("Nome do ficheiro"+RED+"(por defeito: \"Produtos.txt\")"+RESET+": ");
    }

    public void carregaClientes(){
        mudarDeLinha();
        mudarDeLinha();
        banMensagem("Introduzir nome do ficheiro de vendas");
        mudarDeLinha();
        System.out.print("Nome do ficheiro"+RED+"(por defeito: \"Clientes.txt\")"+RESET+": ");
    }

    public void carregaPreDefinidos(){
        clear();
        banMensagem("Carregar ficheiros");
        mudarDeLinha();
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

    public void tempo(Double tempo){
        mudarDeLinha();
        banMensagem("Tempo de execuçao");
        mudarDeLinha();
        mudarDeLinha();
        System.out.println(CYAN +"     Demorou "+RESET+tempo+" segundos.");
        mudarDeLinha();
        line();
        mudarDeLinha();
    }

    public void tempoSimples(Double tempo){
        mudarDeLinha();
        System.out.println(CYAN +"     Demorou "+RESET+tempo+" segundos.");
        mudarDeLinha();
    }

}
