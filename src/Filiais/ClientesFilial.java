package Filiais;

import java.util.*;
import java.util.stream.Collectors;


public class ClientesFilial {
    private int filial;
    private Map<String, ProdutosCompradosCliente> clientes_com_compras;

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

    public Set<String> getSetClientesCompramMes(int mes){
        HashSet<String> aux = new HashSet<>();
        for(ProdutosCompradosCliente pcc : this.clientes_com_compras.values())
            if((!aux.contains(pcc.getCliente())) && pcc.clienteCompraNoMes(mes)) aux.add(pcc.getCliente());
        return aux;
    }

    public void addClientesCompramProdMes(String prod, Map<Integer,Set<String>> prods_mes){
        for(ProdutosCompradosCliente pcc : this.clientes_com_compras.values())
            pcc.addProdsCompramMes(prod,prods_mes);
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

    public Map<String,Integer> getProdsQuantCli(String cliente){
        Map<String,Integer> map = new HashMap<>();
        if(this.clientes_com_compras.containsKey(cliente)){
            for(AbstractMap.SimpleEntry<String,Integer> se : this.clientes_com_compras.get(cliente).getListaProdutoQuantidade()){
                map.put(se.getKey(),se.getValue());
            }
        }
        return map;
    }

    public Set<String> getClisCompramProduto(String prod, Set<String> old){
        Set<String> set = new HashSet<>();
        for(ProdutosCompradosCliente pcc : this.clientes_com_compras.values()){
            if(!old.contains(prod) && pcc.clienteCompraProd(prod)) set.add(pcc.getCliente());
        }
        return set;
    }

    public String getTop3Faturacao(){
        List<Map.Entry<String,Double>> l = new ArrayList<>();
        for(ProdutosCompradosCliente pcc : this.clientes_com_compras.values()){
            l.add(new AbstractMap.SimpleEntry<String,Double>(pcc.getCliente(),pcc.getGastosTotal()));
        }
        Comparator<Map.Entry<String,Double>> c = new Comparator<Map.Entry<String,Double>>() {
            public int compare(Map.Entry<String,Double> o1, Map.Entry<String,Double> o2) {
                if(o1.getValue()<o2.getValue()) {
                    return 1;
                }
                else{
                    if(o1.getValue()>o2.getValue()){
                        return -1;
                    }
                    else{
                        return (o1.getKey().compareTo(o2.getKey()));
                    }
                }
            }
        };
        l.sort(c);
        int i;
        StringBuilder sb;
        sb = new StringBuilder();
        for (i=0;i<2;i++) {
            sb.append(l.get(i).getKey()).append(" ");
        }
        sb.append(l.get(i).getKey());
        return sb.toString();
    }

    public Map<String,Set<String>> getClienteProdsDiferentes(){
        Map<String,Set<String>> map = new HashMap<>();
        for(ProdutosCompradosCliente pcc : this.clientes_com_compras.values()){
            map.put(pcc.getCliente(), pcc.getProdsDifComprados());
        }
        return  map;
    }

    public Map<String,Double> getClisFatProd(String prod){
        Map<String,Double> map = new HashMap<>();
        double d;
        for(ProdutosCompradosCliente pcc : this.clientes_com_compras.values()){
            d = pcc.getGastoProdutoTotal(prod);
            if(d != 0){
                map.put(pcc.getCliente(),d);
            }
        }
        return map;
    }

    public Set<String> getClisCompramFilial(){
        return this.clientes_com_compras.keySet();
    }
}



