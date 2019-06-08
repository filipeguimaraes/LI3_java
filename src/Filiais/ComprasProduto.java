package Filiais;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class ComprasProduto implements Serializable {
    private String codProd;
    private List<Venda> lista;

    public ComprasProduto(){
        this.codProd="";
        this.lista= new ArrayList<>();
    }

    public ComprasProduto(String codProd, List<Venda> lista) {
        this.codProd = codProd;
        this.lista=lista;
    }

    public ComprasProduto(ComprasProduto l){
        this.codProd=l.getCodProd();
        this.lista=l.getLista();
    }

    public String getCodProd() {
        return codProd;
    }

    public void setCodProd(String codProd) {
        this.codProd = codProd;
    }

    public List<Venda> getLista() {
        return lista.stream().map(Venda::clone).collect(Collectors.toList());
    }

    public int getNumVendasProd(){
        return this.lista.size();
    }

    public int [] getNumComprasMes(){
        int [] r = new int [12];
        for(Venda v : this.lista){
            r[v.getMes()-1]++;
        }
        return r;
    }

    public double [] getGastoMes(){
        double [] r = new double [12];
        for(Venda v : this.lista){
            r[v.getMes()-1] += v.getPreco() * v.getQuantidade();
        }
        return r;
    }

    public double getGastoProd(){
        double r = 0;
        for(Venda v : this.lista){
            r += v.getPreco() * v.getQuantidade();
        }
        return r;
    }

    public String toString() {
        return "ComprasProduto{" +
                "lista=" + lista +
                '}';
    }

    public boolean vendeMes(int mes){
        for(Venda v : lista){
            if(v.getMes()==mes){
                return true;
            }
        }
        return false;
    }

    public List<Integer> getMesesEmQueVendeProd(){
        List<Integer> l = new ArrayList<>();
        for(Venda v : this.lista){
            l.add(v.getMes());
        }
        return l;
    }

    public void setLista(List<Venda> lista) {
        this.lista = lista;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComprasProduto that = (ComprasProduto) o;
        return codProd.equals(that.codProd) &&
                lista.equals(that.lista);
    }

    public ComprasProduto clone(){
        return new ComprasProduto(this);
    }

    public void addDados(int filial, String codProd, String codCli, double preco, int quant, String tipo, int mes){
        Venda v = new Venda(codProd,codCli,preco,quant,tipo,mes,filial);
        if(v.validaVenda())this.lista.add(v);
    }

    public void compraProdutoMes(Map<Integer, Set<String>> clis_mes){
        for(Venda v : this.lista){
            clis_mes.get(v.getMes()).add(v.getCodCli());
        }
    }

    public int getQuantidadeTotalVendidaProduto(){
        int r = 0;
        for(Venda v : this.lista){
            r += v.getQuantidade();
        }
        return r;
    }

}
