package Faturacao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class DadosAnual implements Serializable {
    private String codProd;
    private Map<Integer, DadosMes> dados_mensais;

    public DadosAnual() {
        this.codProd = "";
        this.dados_mensais = new HashMap<Integer, DadosMes>();
    }

    public DadosAnual(String codProd) {
        this.codProd = codProd;
        this.dados_mensais = new HashMap<Integer, DadosMes>();
    }

    public DadosAnual(String codProd, Map<Integer, DadosMes> dados_mensais) {
        this.codProd = codProd;
        this.dados_mensais = dados_mensais;
    }

    private String getCodProd() {
        return codProd;
    }

    private void setCodProd(String codProd) {
        this.codProd = codProd;
    }

    private Map<Integer, DadosMes> getDados_mensais() {
        return dados_mensais;
    }

    private void setDados_mensais(Map<Integer, DadosMes> dados_mensais) {
        this.dados_mensais = dados_mensais;
    }

    /**
     * Método recebe os dados de faturação e adiciona adequadamente ao mês.
     * @param mes int que representa o mês.
     * @param quant int que representa a quantidade vendida.
     * @param fat_N double que representa a faturação em normal.
     * @param fat_P double que representa a faturação em promoção.
     */
    public void addDadosAnual(int mes, int quant, double fat_N, double fat_P){
        if(!this.dados_mensais.containsKey(mes)){
            DadosMes dm = new DadosMes(mes);
            this.dados_mensais.put(mes,dm);
        }
        this.dados_mensais.get(mes).addDadosMes(quant,fat_N,fat_P);
    }

    /**
     * Método que dado um mês, retorna o número de registo de vendas desse mês.
     * @param mes int que representa o mês.
     * @return int número de registo de vendas desse mês.
     */
    public int getNumVendidoMes(int mes){
        if(this.dados_mensais.containsKey(mes))
            return this.dados_mensais.get(mes).getRegisto_vendas();
        else
            return 0;
    }

    /**
     * Método que dado um mês, retorna o total faturado nesse mês.
     * @param mes int que representa o mês.
     * @return double total faturado nesse mês.
     */
    public double getFaturadoMes(int mes){
        if(this.dados_mensais.containsKey(mes))
            return this.dados_mensais.get(mes).getFaturacaoTotal();
        else
            return 0;
    }

    /**
     * Método retorna quantidade total vendida ao longo do ano.
     * @return int quantidade total vendida ao longo do ano.
     */
    public int getQuantidadeAnual(){
        int r = 0;
        for(DadosMes dm : this.dados_mensais.values()){
            r += dm.getQuantidade();
        }
        return r;
    }

    /**
     * Método que percorre todos os dados mensais, retornando um Map<Integer,Double> contendo a faturação total(value)
     * num determinado mês(key).
     * @return Map<Integer, Double> contendo a faturação total(value) num determinado mês(key).
     */
    public Map<Integer,Double> getFaturacaoPorMes(){
        Map<Integer,Double> map = new HashMap<>();
        for (DadosMes dm : this.dados_mensais.values()){
            map.put(dm.getMes(),dm.getFaturacaoTotal());
        }
        return map;
    }

    /**
     * Método que percorre todos os dados mensais, retornando um Map<Integer,Integer> contendo o número de registo
     * de vendas(value) num determinado mês(key).
     * @return Map<Integer, Integer> contendo o número de registo de vendas(value) num determinado mês(key).
     */
    public Map<Integer,Integer> getComprasMesProduto(){
        Map<Integer,Integer> map = new HashMap<>();
        for(DadosMes dm : this.dados_mensais.values()){
            map.put(dm.getMes(),dm.getRegisto_vendas());
        }
        return map;
    }


    /**
     * Método retorna a faturação total ao longo do ano.
     * @return double faturação total ao longo do ano.
     */
    public double getFaturacaoTotalProd(){
        double r = 0;
        for(DadosMes dm : this.dados_mensais.values()){
            r += dm.getFaturacaoTotal();
        }
        return r;
    }
}
