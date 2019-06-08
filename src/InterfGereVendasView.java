import java.util.List;
import java.util.Map;

public interface InterfGereVendasView {

    /**
     * Imprime um menu com as informações fornecidas
     * @param opcoes Opções do menu
     */
    void menuOpcoes(String[] opcoes);

    /**
     * Imprime as informações da query 1
     * @param produtos Dados a imprimir
     */
    void query1(List<String> produtos);

    /**
     *  Imprime as informações da query 2
     * @param global Dados referentes a todas as filiais
     * @param filial1 Dados referentes à filial 1
     * @param filial2 Dados referentes à filial 2
     * @param filial3 Dados referentes à filial 3
     */
    void query2(int[] global, int[] filial1,int[] filial2,int[] filial3);

    /**
     * Imprime as informações da query 3
     * @param dados Dados como o número de compras, produtos e gasto total
     */
    void query3(List<String> dados);

    /**
     * Imprime as informações da query 4
     * @param dados Dados como o número de vezes que um porduto foi adquirido, clientes e total faturado por mês
     */
    void query4(List<String> dados);

    /**
     * Imprime as informações da query 5
     * @param dados Dados como os produtos e a quantidade comprada de um determinado cliente
     */
    void query5(List<Map.Entry<String,Integer>> dados);

    /**
     * Imprime as informações da query 6
     * @param dados Dados como os produtos mais vendidos bem como os clientes que os compraram
     */
    void query6(List<String> dados);

    /**
     * Imprime as informações da query 7
     * @param dados Dados, para cada filial, dos 3 clientes que mais compram
     */
    void query7(List<String> dados);

    /**
     * Imprime as informações da query 8
     * @param dados Lista de clientes
     */
    void query8(List<Map.Entry<String,Integer>> dados);

    /**
     * Imprime as informações da query 9
     * @param dados Clientes
     */
    void query9(List<Map.Entry<String,Double>> dados);

    /**
     * Imprime as informações da query 10
     * @param dados Informações de Produtos filial a filial, mês a mês
     */
    void query10(String dados);

    /**
     * Imprime o total de compras por mês
     * @param dados Total de compras por mês
     */
    void totalComprasMes(List<Integer> dados);

    /**
     * Imprime a informação sobre o ultimo ficheiro lido
     * @param prods Informação sobre os produtos
     * @param clis Informação sobre os clientes
     * @param fat Informação sobre a faturação
     * @param vendas Informação sobre as vendas
     */
    void info(List<Integer> prods, List<Integer> clis, List<Double> fat, List<String> vendas);

    /**
     *  Imprime a faturação total por mês para cada filial e o valor total global
     * @param dados Faturação total por mês para cada filial e o valor total global
     */
    void fatTotal(List<List<Double>> dados);

    /**
     * Imprime uma tabela valores referentes a clientes
     * @param dados Clientes distintos que compraram em cada mês filial a filial
     */
    void distintosCli(List<String> dados);

    /**
     * Imprime o menu para escolher os ficheiros a carregar
     */
    void carregaFicheiros();

    /**
     * Menu para receber o mês
     */
    void recebeMes();

    /**
     * Menu para receber o cliente
     */
    void recebeCliente();

    /**
     * Menu para receber o produto
     */
    void recebeProduto();

    /**
     * Menu para receber um numero de produtos a apresentar
     */
    void recebeIntProd();

    /**
     * Menu para receber o numero de clientes a apresentar
     */
    void recebeIntCli();

    /**
     * Apresenta no ecrã o tempo fornecido
     * @param tempo Tempo de execução
     */
    void tempo(Double tempo);

    /**
     * Apresenta no ecrã, de maneira simples, o tempo fornecido
     * @param tempo Tempo de execução
     */
    void tempoSimples(Double tempo);

    /**
     * Ecrã final
     */
    void fim();
}
