package Produtos;

import java.util.List;

public interface ICatProdutos{

    List<String> getListProds();

    /**
     * Método que verifica se um determinado produto existe
     * @param s código de produto
     * @return true se existir, false caso contrário
     */
    boolean existeProduto(String s);

    /**
     * Método que recebe um ficheiro, lê e insere
     * @param fich nome do ficheiro
     */
    void loadProdutos(String fich);

    /**
     * Método que adiciona os produtos válidos à estrutura
     * @param fich "Produtos.txt"
     */
    void readProdutos (String fich);

    /**
     * Método que calcula o número de produtos válidos
     * @return número de produtos válidos
     */
    int getTamanho();
}
