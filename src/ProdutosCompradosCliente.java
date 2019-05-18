import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


public class ProdutosCompradosCliente {
    private String cliente;
    private Map<String,ComprasProduto> produtos_comprados;

    public ProdutosCompradosCliente() {
        this.produtos_comprados = new HashMap<String,ComprasProduto>();
        this.cliente = "";
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Map<String, ComprasProduto> getprodutos_comprados() {
        return produtos_comprados;
    }

    public void setprodutos_comprados(Map<String, ComprasProduto> produtos_comprados) {
        this.produtos_comprados = produtos_comprados;
    }

    public ProdutosCompradosCliente(String cliente, Map<String, ComprasProduto> produtos_comprados) {
        this.cliente = cliente;
        this.produtos_comprados = produtos_comprados;
    }

    public int hashCode() {
        return this.cliente.hashCode();
    }

    public void addCompraProd(int filial, String codProd, String codCli, double preco, int quant, String tipo, int mes){
        if(!this.produtos_comprados.containsKey(codProd)) {
            ComprasProduto lcp = new ComprasProduto();
            lcp.setCodProd(codProd);
            this.produtos_comprados.put(codProd, lcp);
        }
        this.produtos_comprados.get(codProd).addDados(filial,codProd,codCli,preco,quant,tipo,mes);
    }

    public int getNumProdCompras(){
        int r=0;
        for( Integer i : this.produtos_comprados.values().stream().map(ComprasProduto::getNumVezesQueProdComprado).collect(Collectors.toList()))
            r += i;
        return  r;
    }
}
