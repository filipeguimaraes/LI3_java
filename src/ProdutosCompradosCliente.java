import java.util.*;
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
        for( Integer i : this.produtos_comprados.values().stream().map(ComprasProduto::getNumVendasProd).collect(Collectors.toList()))
            r += i;
        return  r;
    }

    public boolean clienteCompraNoMes(int mes){
        for(ComprasProduto cp : this.produtos_comprados.values()){
            if(cp.vendeMes(mes))
                return true;
        }
        return false;
    }

    public Set<String> getProdsDifComprados(){
        return this.produtos_comprados.keySet();
    }

    public double [] getGastosMesesTotal(){
        double [] r_gastos = new double [12];
        double [] gastos;
        int i;
        for(ComprasProduto cp : this.produtos_comprados.values()){
            gastos = cp.getGastoMes();
            for(i=0;i<12;i++){
                r_gastos[i] += gastos[i];
            }
        }
        return r_gastos;
    }

    public int [] getNumRegComprasMeses(){
        int [] r_quant_reg = new int [12];
        int [] quantos;
        int i;
        for(ComprasProduto cp : this.produtos_comprados.values()){
            quantos = cp.getNumComprasMes();
            for(i=0;i<12;i++){
                r_quant_reg[i] += quantos[i];
            }
        }
        return r_quant_reg;
    }

    public void verificaCompraDeProdutosDiferentes(Map<Integer,Set<String>> prods_mes){
        List<Integer> l;
        for(ComprasProduto cp : this.produtos_comprados.values()){
            l = cp.getMesesEmQueVendeProd();
            for(Integer i: l){
                if(!prods_mes.containsKey(i)){
                    Set<String> set = new HashSet<>();
                    prods_mes.put(i,set);
                }
                prods_mes.get(i).add(cp.getCodProd());
            }
        }
    }

    public void addProdsCompramMes(String prod, Map<Integer,Set<String>> clis_mes){
        if(this.produtos_comprados.containsKey(prod))
            this.produtos_comprados.get(prod).compraProdutoMes(clis_mes);
    }

    public List<AbstractMap.SimpleEntry<String,Integer>> getListaProdutoQuantidade(){
        List<AbstractMap.SimpleEntry<String,Integer>> l = new ArrayList<>();
        for(Map.Entry<String,ComprasProduto> me : this.produtos_comprados.entrySet()){
            l.add(new AbstractMap.SimpleEntry<>(me.getKey(),me.getValue().getQuantidadeTotalVendidaProduto()));
        }
        return l;
    }

    public boolean clienteCompraProd(String prod){
        return this.produtos_comprados.containsKey(prod);
    }

    public double getGastosTotal(){
        double gastos = 0;
        for(ComprasProduto cp : this.produtos_comprados.values()){
            gastos += cp.getGastoProd();
        }
        return gastos;
    }

    public double getGastoProdutoTotal(String prod){
        if(this.produtos_comprados.containsKey(prod)){
            return this.produtos_comprados.get(prod).getGastoProd();
        }
        else{
            return 0.0;
        }
    }
}
