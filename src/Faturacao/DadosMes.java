package Faturacao;

import java.io.Serializable;
import java.util.Objects;

public class DadosMes implements Serializable {
    private int mes;
    private int quantidade;
    private double faturacao_N;
    private double faturacao_P;
    private int registo_vendas;

    public DadosMes(){
        this.mes=0;
        this.quantidade = 0;
        this.faturacao_N = 0;
        this.faturacao_P = 0;
        this.registo_vendas = 0;

    }

    public DadosMes(int mes) {
        this.mes = mes;
        this.quantidade = 0;
        this.faturacao_N = 0;
        this.faturacao_P = 0;
        this.registo_vendas = 0;
    }

    public DadosMes(int mes, int quantidade, double faturacao_N, double faturacao_P, int registo_vendas) {
        this.mes = mes;
        this.quantidade = quantidade;
        this.faturacao_N = faturacao_N;
        this.faturacao_P = faturacao_P;
        this.registo_vendas = registo_vendas;
    }

    public DadosMes(DadosMes dm){
        this.mes = dm.getMes();
        this.quantidade = dm.getQuantidade();
        this.faturacao_N = dm.getFaturacao_N();
        this.faturacao_P=dm.getFaturacao_P();
        this.registo_vendas = dm.getRegisto_vendas();

    }

    /**
     * Método que retorna a quantidade vendida.
     * @return int quantidade vendida.
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * Método que retorna a mês.
     * @return int mês.
     */
    public int getMes() {
        return mes;
    }

    private void setMes(int mes) {
        this.mes = mes;
    }

    private void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    private void addQuantidade(int quantidade) {
        this.quantidade += quantidade;
    }

    /**
     * Método que retorna a faturação em normal.
     * @return double faturação em normal.
     */
    public double getFaturacao_N() {
        return faturacao_N;
    }

    private void setFaturacao_N(double faturacao_N) {
        this.faturacao_N = faturacao_N;
    }

    private void addFaturacao_N(double faturacao_N) {
        this.faturacao_N += faturacao_N;
    }

    /**
     * Método que retorna a faturação em promoção.
     * @return double faturação em promoção.
     */
    public double getFaturacao_P() {
        return faturacao_P;
    }

    private void setFaturacao_P(double faturacao_P) {
        this.faturacao_P = faturacao_P;
    }

    private void addFaturacao_P(double faturacao_P) {
        this.faturacao_P += faturacao_P;
    }

    /**
     * Método que retorna a número de registo de vendas.
     * @return int número de registo de vendas.
     */
    public int getRegisto_vendas() {
        return registo_vendas;
    }

    private void setRegisto_vendas(int registo_vendas) {
        this.registo_vendas = registo_vendas;
    }

    private void addRegisto_vendas(int registo_vendas) {
        this.registo_vendas += registo_vendas;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DadosMes dadosMes = (DadosMes) o;
        return quantidade == dadosMes.quantidade &&
                Double.compare(dadosMes.faturacao_N, faturacao_N) == 0 &&
                Double.compare(dadosMes.faturacao_P, faturacao_P) == 0 &&
                registo_vendas == dadosMes.registo_vendas;
    }

    public String toString() {
        return "DadosMes{" +
                "quantidade=" + quantidade +
                ", faturacao_N=" + faturacao_N +
                ", faturacao_P=" + faturacao_P +
                ", registo_vendas=" + registo_vendas +
                '}';
    }

    public DadosMes clone(){
        return new DadosMes(this);
    }

    /**
     * Método que com os dados de faturação referentes a um mês adiciona os dados aos já existentes.
     * @param quant int que representa a quantidade vendida.
     * @param fat_N double que representa o faturado em normal.
     * @param fat_P double que representa o faturado em promoção.
     */
    public void addDadosMes(int quant, double fat_N, double fat_P){
        this.addQuantidade(quant);
        this.addFaturacao_N(fat_N);
        this.addFaturacao_P(fat_P);
        this.registo_vendas++;
    }

    /**
     * Método que retorna o total faturado neste mês.
     * @return double total faturado neste mês.
     */
    public double getFaturacaoTotal(){
        return this.faturacao_N+this.faturacao_P;
    }

}
