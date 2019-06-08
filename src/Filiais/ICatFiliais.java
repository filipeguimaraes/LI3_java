package Filiais;

import java.util.List;
import java.util.Map;

public interface ICatFiliais {

    /**
     * Método que retorna o número total de clientes que fazem compras.
     * @return Inteiro número total de clientes que fazem compras.
     */
    int getNumeroClientesQueCompram();

    /**
     * Método que retorna o número total de clientes que não fazem compras.
     * @return Inteiro número total de clientes que não fazem compras.
     */
    int getNumeroClientesQueNaoCompram(List<String> l);

    /**
     * Método que dado os dados relativos a uma compra adiciona á estrutura os mesmos.
     * @param filial Inteiro que representa a filial.
     * @param codProd String do código de produto.
     * @param codCli String do código de cliente.
     * @param preco Double que representa o preço.
     * @param quant Inteiro que representa a quantidade comprada.
     * @param tipo String que representa o tipo de compra.
     * @param mes Inteiro que representa o mês.
     */
    void addClienteFilial(int filial, String codProd, String codCli, double preco, int quant, String tipo, int mes);

    /**
     * Método que retorna o número global de compras feitos.
     * @return Inteiro número global de compras feitos.
     */
    int getNumProdCompras();

    /**
     * Método que dado uma filial e um mês, retorna o número de clientes que compram nessa filial ao longo desse mês.
     * @param filial int que representa a filial.
     * @param mes int que representa o mês.
     * @return Inteiro número de clientes que compram nessa filial ao longo desse mês.
     */
    int getNumClientesCompramMesFilial(int filial, int mes);

    /**
     * Método recebendo uma filial, retorna um Map<Integer,Integer> com o número de clientes diferentes(value) que
     * compram num determinado mês(key).
     * @param filial int que representa a filial.
     * @return Map<Integer,Integer> número de clientes diferentes(value) que compram num determinado mês(key).
     */
    Map<Integer,Integer> getMesClientesDiferentes(int filial);

    /**
     * Método que recebe um código de cliente, retorna uma lista em que cada elemento é uma String com o número de
     * registo de vendas respetivo ao cliente, número de produtos diferentes que o comprou e os gastos dessas vendas
     * eftuadas pelo cliente, respetivamente ao mês, por exemplo o primeiro elemento da lista corresponde ao mes 1 (Janeiro).
     * @param cliente Código de cliente (String).
     * @return Lista de Strings. Exemplo: ["3 3 523.23202", ...]
     */
    List<String> getQuantosProdsDifsEGastos(String cliente);

    /**
     * Método que recebe um código de produto, e retorna um array com o número de clientes diferentes que
     * compram o produto, respetivamente em cada mês.
     * @param produto String código de produto.
     * @return Array de inteiros com onúmero de clientes diferentes que compram o produto, respetivamente em cada mês.
     * Sendo que o índice 0 do array corresponde ao mês 1, e assim sucessivamente.
     */
    int [] getClisDifsCompramProdMeses(String produto);

    /**
     * Método que recebe um código de cliente, retorna um Map<String,Integer> contendo a quantidade (value)
     * correspondente a cada mês (key), em relação a todas as filiais.
     * @param cliente String código do cliente.
     * @return Map<String, Integer> contendo a quantidade correspondente a cada mês.
     */
    Map<String,Integer> getProdutoQuantidadeDeUmCliente(String cliente);

    /**
     * Método que recebe código de produto, verifica em todos os clientes se compram esse produto, que caso
     * seja verdade é adicionado os códigos dos respetivos clientes a um Set<String>  que contém os clientes
     * que compram esse produto.
     * @param prod String código do produto.
     * @return Set<String>  que contém os clientes que compram esse produto.
     */
    int getNumClisDiferentesCompraProd(String prod);

    /**
     * Método que recebendo uma filial, verifica se essa existe, devolvendo uma string com os Top 3 de clientes
     * que mais gastam nessa filial.
     * @param filial int que representa uma filial.
     * @return String com os Top 3 de clientes que mais gastam nesta filial.
     * Exemplo: "(1º que mais gastou) (2º que mais gastou) (3º que mais gastou)" ou caso não exista "N/A".
     */
    String getTop3CompradoresFilial(int filial);

    /**
     * Método percorre todas as filials, retornando uma List<Map.Entry<String,Integer>>
     * (código de cliente, número de diferentes produtos comprados pelo respetivo cliente).
     * @return List<Map.Entry<String,Integer>> de clientes e os produtos diferentes comprados pelo mesmo.
     */
    List<Map.Entry<String,Integer>> getClienteNumProdsCompDiferentes();

    /**
     * Método que dado um código de produto, percorre todas as filiais, retornando uma List<Map.Entry<String,Double>>
     * em que o par é (codigo de produto, e a respetiva faturação).
     * @param prod String código do produto.
     * @return List<Map.Entry<String,Double>> de código de produto e faturação gobal.
     */
    List<Map.Entry<String,Double>> getClientesFaturacaoProd(String prod);
}
