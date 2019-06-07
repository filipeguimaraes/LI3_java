import java.util.List;
import java.util.Map;

public interface IGereVendasModel {

    /**
     * Metodo que retorna a lista de produtos não comprados.
     * @return Lista de códigos de produtos não comprados. Exemplo: ["AF1184", "UJ3773", ...]
     */
    List<String> getListaDeProdutosNaoComprados();

    /**
     * Método que recebe uma filial e um mês, retorna um array com dois inteiros em que o primeiro corresponde
     * ao número de vendas no mês nessa filial e o segundo o número de clientes que compram nesse mês.
     * @param mes Mês do ano (int).
     * @param filial Número de filial (int);
     * @return Array(de inteiros) de duas posições.
     */
    int [] getQuerie2(int mes, int filial) throws MesException;

    /**
     * Método que recebe um código de cliente, retorna uma lista em que cada elemento é uma String com o número de
     * registo de vendas respetivo ao cliente, número de produtos diferentes que o comprou e os gastos dessas vendas
     * eftuadas pelo cliente, respetivamente ao mês, por exemplo o primeiro elemento da lista corresponde ao mes 1 (Janeiro).
     * @param cliente Código de cliente (String).
     * @return Lista de Strings. Exemplo: ["3 3 523.23202", ...]
     */
    List<String> getQuerie3(String cliente) throws ClienteException;

    /**
     * Método que recebe um código de produto, retorna uma lista de Strings, em que cada String tem o número de vendas
     * registadas com esse produto, número de clientes diferentes que compram esse produto e total faturado na venda
     * desse produto; respetivamente aos meses.
     * @param produto Código produto (String).
     * @return Lista de Strings. Exemplo: ["3 3 523.23202", ...]
     */
    List<String> getQuerie4(String produto) throws ProdutoException;

    /**
     * Método que dado um código de cliente retorna uma lista(Top) dos produtos que comprou em
     * relação a quantidade comprada. A lista é composta por pares de código produto e quantidade
     * comprada;estes pares são ordenados por mais quantidade comprada, que caso seja igual
     * ordena pelo código de produto.
     * @param cliente Código de cliente.
     * @return Lista de pares exemplo: [("AF1184",5321), ("ZA3421",3213), ...]
     */
    List<Map.Entry<String,Integer>> getQuerie5(String cliente) throws ClienteException;

    /**
     * Método que dado um x, retorna um Top(x) de produtos por mais unidades vendidas, e tendo também o
     * número de clientes diferentes que compraram esse produto.
     * @param x Tamanho do Top de produtos pretendido.
     * @return Lista exemplo: ["AF1184 732", "KR8394 662", ...] sendo código produto e número de clientes diferentes
     * separado por um espaço (" ").
     */
    List<String> getQuerie6(int x);

    /**
     * Método que retorna a lista com o Top3 dos maiores compradores(clientes) por filial, sendo retornada uma lista
     * de Strings, conrespondendo cada String ao Top3 de cada filial.
     * @return Lista de Strings exemplo: ["K3992 A8832 J2366", "H8329 ...", ...]
     * Sendo neste exemplo A8832 o segundo maior comprador da filial 1
     * e H8329 o maior comprador da filial 2.
     */
    List<String> getQuerie7();

    /**
     * Método que dado um tamanho(x), retorna o Top(x) de clientes que compram o maior número de produtos
     * diferentes, apresentados numa lista de pares (Código cliente, Quantidade de compras).
     * Apresenta ainda uma ordem decrescente sobre a quantidade de compras, que se for igual,
     * ordena por sua vez alfabéticamente por código cliente.
     * @param x Tamanho do Top pretendido.
     * @return Lista de pares exemplo: [("A1184", 32123), ("K2311", 2339), ...]
     */
    List<Map.Entry<String,Integer>> getQuerie8(int x);

    /**
     * Metodo que dado prod e tam retorna uma lista de pares, que reflete a o quanto um cliente gastou nesse produto.
     * Esse tem a informação (Codigo cliente, Faturado produto),
     * ordenado por maior faturação e secundáriamente caso tenha a mesma faturação por código cliente.
     * @param produto Código do produto.
     * @param tamanho Número de produtos que pertende na lista de retorno.
     * @return Lista de pares exemplo: [("A1184", 1233321.32123), ("K2311", 23398.34027), ...]
     */
    List<Map.Entry<String,Double>> getQuerie9(String produto, int tamanho) throws ProdutoException;

    /**
     * Método que retorna uma lista de Strings com a informação mês a mês, e para cada mês filial a filial,
     * a facturação total a cada produto.
     * @return Lista de Strings as quais têm o seguinte
     * formato "<cod_prod>:<filial 1 mes 1 faturado>;<filial 2 mes 1 faturado>;<filial 3 mes 1 filial> faturado>#<filial 1 mes 2 faturado>;<filial 2 mes 2 faturado>;<filial 3 mes 2 faturado>#..."
     * sendo ":" -> divisão cod_prod e dados; "#" divisão entre meses; ";" divisão entre dados por filial.
     */
    List<String> getQuerie10();

    /**
     * Método que lê os ficheiros dando parse a informação útil para os modúlos.
     * @param clientestxt Ficheiro de Clientes.
     * @param produtostxt Ficheiro de Produtos.
     * @param vendastxt Ficheiro de Vendas.
     */
    void load(String clientestxt, String produtostxt, String vendastxt);

    List<Integer> getComprasMes();

    List<Double> getFaturacaoFiliais();

    List<String> getDistintosCli();

    List<Double> getInfoFat();

    List<Integer> getInfoClis();

    List<Integer> getInfoProdutos();

    List<String> getInfoVendas();
}
