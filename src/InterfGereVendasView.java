import java.util.List;
import java.util.Map;

public interface InterfGereVendasView {

    void menuOpcoes(String[] opcoes);

    void query1(List<String> produtos);

    void query2(int[] global, int[] filial1,int[] filial2,int[] filial3);

    void query3(List<String> dados);

    void query4(List<String> dados);

    void query5(List<Map.Entry<String,Integer>> dados);

    void query6(List<String> dados);

    void query7(List<String> dados);

    void query8(List<Map.Entry<String,Integer>> dados);

    void query9(List<Map.Entry<String,Double>> dados);

    void query10(String dados);

    void totalComprasMes(List<Integer> dados);

    void fatTotal(List<Double> dados);

    void distintosCli(List<String> dados);

    void carregaVendas();

    void carregaProdutos();

    void carregaClientes();

    void carregaPreDefinidos();

    void recebeMes();

    void recebeCliente();

    void recebeProduto();

    void recebeIntProd();

    void recebeIntCli();

    void tempo(Double tempo);

    void tempoSimples(Double tempo);

    void fim();
}
