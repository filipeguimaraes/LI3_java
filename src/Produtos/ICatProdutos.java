package Produtos;

import java.util.List;

public interface ICatProdutos {

    List<String> getListProds();

    boolean existeProduto(String s);

    void loadProdutos(String fich);

    void readProdutos (String fich);

    int getTamanho();
}
