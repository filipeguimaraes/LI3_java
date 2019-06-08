package Faturacao;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class ProdutosFilial implements Serializable {
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

    private Map<String, DadosAnual> getprodutos_filial() {
        return produtos_filial;
    }

    private void setprodutos_filial(Map<String, DadosAnual> produtos_filial) {
        this.produtos_filial = produtos_filial;
    }

    private int getFilial() {
        return filial;
    }

    private void setFilial(int filial) {
        this.filial = filial;
    }

    /**
     * Método que retorna num Set<String> o código de todos os produtos vendidos nesta filial.
     * @return Set<String> o código de todos os produtos vendidos nesta filial.
     */
    public Set<String> getCodClisFilial(){
        return this.produtos_filial.keySet();
    }

    /**
     * Método recebe os dados relativos á faturação de uma compra e adiciona á estrutura o produto adequadamente.
     * @param codProd String código do produto.
     * @param mes int que representa o mês.
     * @param quant int que representa a quantidade vendida.
     * @param fat_N double que representa a faturação em normal.
     * @param fat_P double que representa a faturação em promoção.
     */
    public void addProdutosFilial(String codProd, int mes, int quant, double fat_N, double fat_P){
        if(!this.produtos_filial.containsKey(codProd)){
            DadosAnual da = new DadosAnual(codProd);
            this.produtos_filial.put(codProd,da);
        }
        this.produtos_filial.get(codProd).addDadosAnual(mes,quant,fat_N,fat_P);
    }

    /**
     * Método dado um mês, retorna o número de registo de vendas na filial desse mês.
     * @param  mes int que representa o mês.
     * @return int número de registo de vendas na filial desse mês.
     */
    public int getNumVendasRegMes(int mes){
        int r = 0;
        for(DadosAnual da : this.produtos_filial.values()){
            r += da.getNumVendidoMes(mes);
        }
        return r;
    }

    /**
     * Método que dado um código de produto, retorna um array de inteiros contendo o número de registo
     * de vendas nesta filial, aos respetivos meses.
     * @param produto String código de produto.
     * @return Array de inteiros contendo o número de registo de vendas nesta filial, aos respetivos meses.
     * Sendo que o índice 0 do array corresponde ao mês 1, e assim sucessivamente.
     */
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

    /**
     * Método que dado um código de produto, retorna um array de doubles contendo a faturação dess produto
     * nesta filial, aos respetivos meses.
     * @param produto String código de produto.
     * @return Array de doubles contendo a faturação dess produto nesta filial, aos respetivos meses.
     * Sendo que o índice 0 do array corresponde ao mês 1, e assim sucessivamente.
     */
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

    /**
     * Método percorre todos os dados anuais da filial e retorna um Map<String,Integer> contendo a quantidade
     * total(value) de cada código de produto(key), relativamente a esta filial.
     * @return Map<String, Integer> contendo a quantidade total(value) da filial de cada código de produto(key),
     * relativamente a esta filial.
     */
    public Map<String,Integer> getProdsQuant(){
        Map<String,Integer> m = new HashMap<>();
        for(Map.Entry<String, DadosAnual> me : this.produtos_filial.entrySet()){
            m.put(me.getKey(),me.getValue().getQuantidadeAnual());
        }
        return m;
    }

    /**
     * Método dado um código de produto, retorna um Map<Integer,Double> contendo a faturação
     * total(value) de cada mês(key), relativamente a esta filial.
     * @param prod String código do produto.
     * @return Map<String, Double> contendo a quantidade total(value) da filial de cada mês(key),
     * relativamente a esta filial.
     */
    public Map<Integer,Double> getFatsProdMes(String prod){
        if(this.produtos_filial.containsKey(prod)){
            return this.produtos_filial.get(prod).getFaturacaoPorMes();
        }
        else{
            return null; // throw
        }
    }

    /**
     * Método percorre todos os dados anuais da filial e retorna um  Map<Integer, Integer> contendo o número
     * de registo de vendas(value) num determinado mês(key).
     * @return Map<Integer, Integer> contendo o número de registo de vendas(value) num determinado mês(key).
     */
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

    /**
     * Método percorre todos os dados anuais da filial e retorna um  Map<Integer,Double>
     * contendo a faturação total(value) num determinado mês(key).
     * @return Map<Integer, Double> contendo a faturação total(value) num determinado mês(key).
     */
    public Map<Integer,Double> getFatMesFilial(){
        Map<Integer,Double> map = new TreeMap<>();
        double k;
        for(DadosAnual da : this.produtos_filial.values()){
            for(Map.Entry<Integer,Double> me : da.getFaturacaoPorMes().entrySet() ){
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

    /**
     * Método percorre todos os dados anuais dos produtos somando o faturação total de cada, retornando
     * a faturação total da filial.
     * @return double faturação total da filial.
     */
    public double getFaturacaoTotalFilial(){
        double r = 0;
        for(DadosAnual da : this.produtos_filial.values()){
            r += da.getFaturacaoTotalProd();
        }
        return r;
    }
}
