package Faturacao;

import java.util.List;
import java.util.Map;

public interface ICatFaturacao {

    /**
     * Método retorna a lista de códigos de produtos não comprados, dada a lista de códigos de produtos existentes.
     * @param l Lista de todos os códigos de produtos existentes.
     * @return Lista de códigos de produtos não comprados.
     */
    List<String> getListaNaoComprados(List<String> l);

    /**
     * Método que calcula o número de produtos que não são vendidos, dada a lista de códigos de produtos existentes.
     * @param l Lista de todos os códigos de produtos existentes.
     * @return Inteiro número de produtos não comprados
     */
    int getNComprados(List<String> l);

    /**
     * Método que dado os dados de uma venda relativa a um produto adiciona á estrutura.
     * @param fil Inteiro que representa a filial.
     * @param cod String do código de produto.
     * @param fat_P Double que representa o faturação normal.
     * @param fat_N Double que representa o faturação promoção.
     * @param quant Inteiro que representa a quantidade comprada.
     * @param mes Inteiro que representa o mês.
     */
    void addCatFaturacao(String cod, int mes, int quant, double fat_N, double fat_P, int fil);

    /**
     * Método que retorna o numero de produtos diferentes vendidos.
     * @return Inteiro numero de produtos diferentes vendidos.
     */
    int getDifs();

    /**
     * Método que uma dada filial e um dado mês, retorna um número de vendas registadas.
     * @param filial Inteiro que representa a filial.
     * @param mes Inteiro que representa o mês.
     * @return Inteiro número de vendas registadas para a filial e o mês.
     */
    int getNumVendasRegMesFilial(int filial, int mes);

    /**
     * Método que dado um código de produto, retorna um array de inteiros que contem o número de registo
     * de vendas, respetivamente ao mês.
     * @param produto String código do produto.
     * @return Array de inteiros que contem o número de registo de vendas, respetivamente ao mês.
     * Sendo que o índice 0 do array corresponde ao mês 1, e assim sucessivamente.
     */
    int [] getVendasRegMeses(String produto);

    /**
     * Método que dado um código de produto, retorna um array de doubles que contem o total faturado no mês
     * das vendas desse produto, respetivamente ao mês.
     * @param produto String código do produto.
     * @return Array de doubles que contem o total faturado no mês das vendas desse produto, respetivamente ao mês.
     * Sendo que o índice 0 do array corresponde ao mês 1, e assim sucessivamente.
     */
    double [] getVendasFaturadoMeses(String produto);

    /**
     * Método percorre todas as filiais, retornando um Map<String,Integer> contendo a quantidade vendida(value)
     * de um produto(key).
     * @return Map<String, Integer> contendo a quantidade vendida(value) de um produto(key).
     */
    Map<String,Integer> getMapProdutosEQuantidadeVendida();

    /**
     * Método que dado um código de produto e uma filial, retorna um Map<Integer,Double> contendo um total faturado(value)
     * num determinado mês(key) do produto na filial indicada.
     * @param prod String do código de produto.
     * @param filial Inteiro que representa a filial.
     * @return Map<Integer, Double> contendo um total faturado(value) num determinado mês(key) do produto na filial indicada.
     */
    Map<Integer,Double> getFatsProdMesFiliais(String prod, int filial);

    /**
     * Método percorre todas as filiais, retornando um Map<Integer,Integer> contendo a número de vendas registadas(value)
     * num determinado mês(key).
     * @return Map<Integer, Integer> contendo a número de vendas registadas(value) num determinado mês(key).
     */
    Map<Integer,Integer> comprasMes();

    /**
     * Método que dada uma filial, retorna um Map<Integer,Double> contendo um total faturado(value)
     * num determinado mês(key) na filial indicada.
     * @param filial Inteiro que representa a filial.
     * @return Map<Integer, Double> contendo um total faturado(value) num determinado mês(key) na filial indicada.
     */
    Map<Integer,Double> fatFilial(int filial);

    /**
     * Método que retorna a faturação global.
     * @return double a faturação global.
     */
    double getFaturacaoGlobal();

}
