import java.util.*;
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

    public void addListaComprasProd(int filial, String codProd, String codCli, double preco, int quant, String tipo, int mes){
        if(!this.clientes_com_compras.containsKey(codCli)){
            ProdutosCompradosCliente lpc = new ProdutosCompradosCliente();
            lpc.setCliente(codCli);
            this.clientes_com_compras.put(codCli,lpc);
        }
        this.clientes_com_compras.get(codCli).addCompraProd(filial,codProd,codCli,preco,quant,tipo,mes);
    }


    public int getNumProdCompras(){
        int r=0;
        for( Integer i : this.clientes_com_compras.values().stream().map(ProdutosCompradosCliente::getNumProdCompras).collect(Collectors.toList()))
            r += i;
        return  r;
    }

    public void getHashSetClientesCompramMes(int mes, HashSet<String> aux){
        //HashSet<String> clis = new HashSet<>();
        for(ProdutosCompradosCliente pcc : this.clientes_com_compras.values())
            if((!aux.contains(pcc.getCliente())) && pcc.clienteCompraNoMes(mes)) aux.add(pcc.getCliente());
        //return clis;
    }



    public int [] getClienteQuantidadeCompradaFilialMeses(String cliente){
        return this.clientes_com_compras.get(cliente).getNumRegComprasMeses();
    }

    public double [] getClienteGastosCompradaFilialMeses(String cliente){
        return  this.clientes_com_compras.get(cliente).getGastosMesesTotal();
    }

    public void verificaComprasDeClientes(String cliente, Map<Integer,Set<String>> prods_mes){
        this.clientes_com_compras.get(cliente).verificaCompraDeProdutosDiferentes(prods_mes);
    }
}



