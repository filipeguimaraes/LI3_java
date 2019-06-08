package Filiais;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;


public class ProdutosCompradosCliente implements Serializable {
    private String cliente;
    private Map<String, ComprasProduto> produtos_comprados;

    public ProdutosCompradosCliente() {
        this.produtos_comprados = new HashMap<String, ComprasProduto>();
        this.cliente = "";
    }

    /**
     * Método que retorna o código de cliente a quem pretence a informação.
     * @return String código de cliente.
     */
    public String getCliente() {
        return cliente;
    }

    /**
     * Método que altera a variavel de código de cliente.
     * @param cliente String código de cliente.
     */
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    private Map<String, ComprasProduto> getprodutos_comprados() {
        return produtos_comprados;
    }

    private void setprodutos_comprados(Map<String, ComprasProduto> produtos_comprados) {
        this.produtos_comprados = produtos_comprados;
    }

    private ProdutosCompradosCliente(String cliente, Map<String, ComprasProduto> produtos_comprados) {
        this.cliente = cliente;
        this.produtos_comprados = produtos_comprados;
    }

    public int hashCode() {
        return this.cliente.hashCode();
    }

    /**
     * Método que recebendo a informações relativas a vuma venda adiciona á estrutura adequadamente.
     * @param filial int que representa a filail.
     * @param codProd String código de produto.
     * @param codCli String código de cliente.
     * @param preco double que representa o preco.
     * @param quant int que representa a quantidade comprada.
     * @param tipo String que representa o tipo de compra.
     * @param mes int que representa o mês.
     */
    public void addCompraProd(int filial, String codProd, String codCli, double preco, int quant, String tipo, int mes){
        if(!this.produtos_comprados.containsKey(codProd)) {
            ComprasProduto lcp = new ComprasProduto();
            lcp.setCodProd(codProd);
            this.produtos_comprados.put(codProd, lcp);
        }
        this.produtos_comprados.get(codProd).addDados(filial,codProd,codCli,preco,quant,tipo,mes);
    }

    /**
     * Método retorna o número total de compras efetuados pelo cliente.
     * @return Inteiro número total de compras efetuados pelo cliente.
     */
    public int getNumProdCompras(){
        int r=0;
        for( Integer i : this.produtos_comprados.values().stream().map(ComprasProduto::getNumVendasProd).collect(Collectors.toList()))
            r += i;
        return  r;
    }

    /**
     * Método dado um mês, verifica se o produto efetua pelo menos uma compra nesse mês.
     * @param mes int que reprenseta o mês.
     * @return true caso o cliente efetue uma compra nesse mês.
     *         false caso contrário.
     */
    public boolean clienteCompraNoMes(int mes){
        for(ComprasProduto cp : this.produtos_comprados.values()){
            if(cp.vendeMes(mes))
                return true;
        }
        return false;
    }

    /**
     * Método que um Set<String> que contém os códigos dos produtos que o cliente comprou.
     * @return Set<String> que contém os códigos dos produtos que o cliente comprou.
     */
    public Set<String> getProdsDifComprados(){
        return this.produtos_comprados.keySet();
    }

    /**
     * Método que retorna um array com os gastos de todas as vendas feitas pelo cliente em cada mês.
     * @return Array de doubles com osgastos de todas as vendas feitas pelo cliente em cada mês.
     * Sendo que o índice 0 do array corresponde ao mês 1, e assim sucessivamente.
     */
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

    /**
     * Método que retorna um array com o número de registo de vendas feitas pelo cliente em cada mês.
     * @return Array de inteiros com o número de registo de vendas feitas pelo cliente em cada mês.
     * Sendo que o índice 0 do array corresponde ao mês 1, e assim sucessivamente.
     */
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

    /**
     * Método que adiciona ao Map<Integer,Set<String>> o código de produto (value) ao respetivo mês(key) em que
     * efetua uma compra.
     * @param prods_mes Map<Integer,Set<String>>.
     */
    public void verificaCompraDeProdutosDiferentes(Map<Integer,Set<String>> prods_mes){
        Set<Integer> l;
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

    /**
     * Método que adiciona ao Map<Integer,Set<String>> o código de cliente (value) ao respetivo mês(key) em que
     * efetua uma compra.
     * @param prod String código produto.
     * @param clis_mes Map<Integer,Set<String>>.
     */
    public void addProdsCompramMes(String prod, Map<Integer,Set<String>> clis_mes){
        if(this.produtos_comprados.containsKey(prod))
            this.produtos_comprados.get(prod).compraProdutoMes(clis_mes);
    }

    /**
     * Método que retorna uma lista de pares (código produto,quantidade comprada) dos produtos comprados pelo cliente.
     * @return List<AbstractMap.SimpleEntry<String,Integer>> lista de pares (código produto,quantidade comprada).
     */
    public List<AbstractMap.SimpleEntry<String,Integer>> getListaProdutoQuantidade(){
        List<AbstractMap.SimpleEntry<String,Integer>> l = new ArrayList<>();
        for(Map.Entry<String, ComprasProduto> me : this.produtos_comprados.entrySet()){
            l.add(new AbstractMap.SimpleEntry<>(me.getKey(),me.getValue().getQuantidadeTotalVendidaProduto()));
        }
        return l;
    }

    /**
     * Método que recebendo um código de produto verifica se o cliente o comprou.
     * @param prod String código do produto.
     * @return true caso o cliente tenha comprado o produto.
     *         false caso contrário.
     */
    public boolean clienteCompraProd(String prod){
        return this.produtos_comprados.containsKey(prod);
    }

    /**
     * Método que retorna o gasto total deste cliente.
     * @return double gasto total do cliente.
     */
    public double getGastosTotal(){
        double gastos = 0;
        for(ComprasProduto cp : this.produtos_comprados.values()){
            gastos += cp.getGastoProd();
        }
        return gastos;
    }

    /**
     * Método que dado um produto retorna o gasto total do cliente em relaão ao produto.
     * @param prod String código do produto.
     * @return double gasto total no produto, pelo cliente.
     */
    public double getGastoProdutoTotal(String prod){
        if(this.produtos_comprados.containsKey(prod)){
            return this.produtos_comprados.get(prod).getGastoProd();
        }
        else{
            return 0.0;
        }
    }
}
