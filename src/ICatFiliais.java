import java.util.List;
import java.util.Map;

public interface ICatFiliais {

    void addClienteFilial(int filial, String codProd, String codCli, double preco, int quant, String tipo, int mes);

    int getNumProdCompras();

    int getNumClientesCompramMesFilial(int filial, int mes);

    Map<Integer,Integer> getMesClientesDiferentes(int filial);

    List<String> getQuantosProdsDifsEGastos(String cliente);

    int [] getClisDifsCompramProdMeses(String produto);

    Map<String,Integer> getProdutoQuantidadeDeUmCliente(String cliente);

    int getNumClisDiferentesCompraProd(String prod);

    String getTop3CompradoresFilial(int filial);

    List<Map.Entry<String,Integer>> getClienteNumProdsCompDiferentes();

    List<Map.Entry<String,Double>> getClientesFaturacaoProd(String prod);
}
