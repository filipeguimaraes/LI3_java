package Faturacao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ProdutosFilial {
    private int filial;
    private Map<String, DadosAnual> produtos_filial;

    public ProdutosFilial() {
        this.filial = 0;
        this.produtos_filial = new HashMap<String, DadosAnual>();
    }

    public ProdutosFilial(int filial) {
        this.filial = filial;
        this.produtos_filial = new HashMap<String, DadosAnual>();
    }

    public Map<String, DadosAnual> getprodutos_filial() {
        return produtos_filial;
    }

    public void setprodutos_filial(Map<String, DadosAnual> produtos_filial) {
        this.produtos_filial = produtos_filial;
    }

    public int getFilial() {
        return filial;
    }

    public void setFilial(int filial) {
        this.filial = filial;
    }

    public Set<String> getCodClisFilial(){
        return this.produtos_filial.keySet();
    }

    public void addProdutosFilial(String codProd, int mes, int quant, double fat_N, double fat_P){
        if(!this.produtos_filial.containsKey(codProd)){
            DadosAnual da = new DadosAnual(codProd);
            this.produtos_filial.put(codProd,da);
        }
        this.produtos_filial.get(codProd).addDadosAnual(mes,quant,fat_N,fat_P);
    }

    public int getNumVendasRegMes(int mes){
        int r = 0;
        for(DadosAnual da : this.produtos_filial.values()){
            r += da.getNumVendidoMes(mes);
        }
        return r;
    }

    public int [] getVendasRegMesesFilial(String produto){
        if(this.produtos_filial.containsKey(produto)){
            int [] reg_prod_filial = new int [12];
            int i;
            for(i=0; i<12; i++){
                reg_prod_filial[i] += this.produtos_filial.get(produto).getNumVendidoMes(i+1);
            }
            return reg_prod_filial;
        }
        return null;
    }

    public double [] getVendasFaturadoMesesFilial(String produto){
        if(this.produtos_filial.containsKey(produto)){
            double [] fat_prod_filial = new double [12];
            int i;
            for(i=0; i<12; i++){
                fat_prod_filial[i] += this.produtos_filial.get(produto).getFaturadoMes(i+1);
            }
            return fat_prod_filial;
        }
        return null;
    }

    public Map<String,Integer> getProdsQuant(){
        Map<String,Integer> m = new HashMap<>();
        for(Map.Entry<String, DadosAnual> me : this.produtos_filial.entrySet()){
            m.put(me.getKey(),me.getValue().getQuantidadeAnual());
        }
        return m;
    }


    public Map<Integer,Double> getFatsProdMes(String prod){
        if(this.produtos_filial.containsKey(prod)){
            return this.produtos_filial.get(prod).getFaturacaoPorMes();
        }
        else{
            return null; // throw
        }
    }

    public Map<Integer,Integer> getComprasMesFilial(){
        Map<Integer,Integer> map = new HashMap<>();
        int k;
        for(DadosAnual da : this.produtos_filial.values()){
            for(Map.Entry<Integer,Integer> me : da.getComprasMesProduto().entrySet() ){
                if(map.containsKey(me.getKey())){
                    k = map.get(me.getKey()) + me.getValue();
                    map.put(me.getKey(),k);
                }
                else{
                    map.put(me.getKey(),me.getValue());
                }
            }
        }
        return map;
    }

    public Map<Integer,Double> getFatMesFilial(){
        Map<Integer,Double> map = new HashMap<>();
        double k;
        for(DadosAnual da : this.produtos_filial.values()){
            for(Map.Entry<Integer,Double> me : da.getFaturacaoFilial().entrySet() ){
                if(map.containsKey(me.getKey())){
                    k = map.get(me.getKey()) + me.getValue();
                    map.put(me.getKey(),k);
                }
                else{
                    map.put(me.getKey(),me.getValue());
                }
            }
        }
        return map;
    }

    public double getFaturacaoTotalFilial(){
        double r = 0;
        for(DadosAnual da : this.produtos_filial.values()){
            r += da.getFaturacaoTotalProd();
        }
        return r;
    }
}
