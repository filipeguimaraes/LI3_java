import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


public class ClientesFilial {
    private int filial;
    private Map<String,ProdutosCompradosCliente> clientes_com_compras;

    public ClientesFilial(){
        this.filial = 0;
        this.clientes_com_compras = new HashMap<String, ProdutosCompradosCliente>();
    }

    public int getFilial() {
        return filial;
    }

    public Map<String, ProdutosCompradosCliente> getClientes_com_compras() {
        return clientes_com_compras;
    }

    public void setClientes_com_compras(Map<String, ProdutosCompradosCliente> clientes_com_compras) {
        this.clientes_com_compras = clientes_com_compras;
    }

    public void setFilial(int filial) {
        this.filial = filial;
    }

    public void addListaComprasProd(Venda v, String codProd, String codCli){
        if(!this.clientes_com_compras.containsKey(codCli)){
            ProdutosCompradosCliente lpc = new ProdutosCompradosCliente();
            lpc.setCliente(codCli);
            this.clientes_com_compras.put(codCli,lpc);
        }
        this.clientes_com_compras.get(codCli).addCompraProd(v,codProd);
    }

    public int getNumProdCompras(){
        int r=0;
        for( Integer i : this.clientes_com_compras.values().stream().map(ProdutosCompradosCliente::getNumProdCompras).collect(Collectors.toList()))
            r += i;
        return  r;
    }
}



