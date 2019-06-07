import java.util.List;
import java.util.Map;

public interface ICatFaturacao {

    List<String> getListaNaoComprados(List<String> l);

    int getNComprados(List<String> l);

    void addCatFaturacao(String cod, int mes, int quant, double fat_N, double fat_P, int fil);

    int getDifs();

    int getNumVendasRegMesFilial(int filial, int mes);

    int [] getVendasRegMeses(String produto);

    double [] getVendasFaturadoMeses(String produto);

    Map<String,Integer> getListaProdutosEQuantidadeVendida();

    Map<Integer,Double> getFatsProdMesFiliais(String prod, int filial);

    Map<Integer,Integer> comprasMes();

    Map<Integer,Double> fatFilial(int filial);

    double getFaturacaoGlobal();

}
