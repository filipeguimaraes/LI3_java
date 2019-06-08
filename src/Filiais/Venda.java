package Filiais;


import java.io.Serializable;
import java.util.Objects;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class Venda implements IVenda, Serializable {
    private String codProd;
    private String codCli;
    private double preco;
    private int quantidade;
    private String tipo;
    private int mes;
    private int filial;

    public Venda(){
        this.codProd = "";
        this.codCli = "";
        this.preco = 0;
        this.quantidade = 0;
        this.tipo = "";
        this.mes = 1;
        this.filial = 1;
    }

    public Venda(String codProd, String codCli, double preco, int quantidade, String tipo, int mes, int filial) {
        this.codProd = codProd;
        this.codCli = codCli;
        this.preco = preco;
        this.quantidade = quantidade;
        this.tipo = tipo;
        this.mes = mes;
        this.filial = filial;
    }

    public Venda (Venda v){
        this.codProd = v.getCodProd();
        this.codCli = v.getCodCli();
        this.preco = v.getPreco();
        this.quantidade = v.getQuantidade();
        this.tipo = v.getTipo();
        this.mes = v.getMes();
        this.filial = v.getFilial();
    }

    public Venda (String[] s){
        this.codProd=s[0];
        this.preco=parseDouble(s[1]);
        this.quantidade=parseInt(s[2]);
        this.tipo=s[3];
        this.codCli=s[4];
        this.mes=parseInt(s[5]);
        this.filial=parseInt(s[6]);
    }

    public Venda (String v){
        String [] s = v.split(" ");
        this.codProd=s[0];
        this.preco=parseDouble(s[1]);
        this.quantidade=parseInt(s[2]);
        this.tipo=s[3];
        this.codCli=s[4];
        this.mes=parseInt(s[5]);
        this.filial=parseInt(s[6]);
    }

    public String getCodProd() {
        return codProd;
    }

    public void setCodProd(String codProd) {
        this.codProd = codProd;
    }

    public String getCodCli() {
        return codCli;
    }

    public void setCodCli(String codCli) {
        this.codCli = codCli;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getFilial() {
        return filial;
    }

    public void setFilial(int filial) {
        this.filial = filial;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venda venda = (Venda) o;
        return Double.compare(venda.preco, preco) == 0 &&
                quantidade == venda.quantidade &&
                mes == venda.mes &&
                filial == venda.filial &&
                Objects.equals(codProd, venda.codProd) &&
                Objects.equals(codCli, venda.codCli) &&
                Objects.equals(tipo, venda.tipo);
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("codigo produto " + this.codProd);
        sb.append("codigo cliente " + this.codCli);
        sb.append("preco " + this.preco);
        sb.append("quantidade " + this.quantidade);
        sb.append("tipo " + this.tipo);
        sb.append("mes " + this.mes);
        sb.append("filial " + this.filial);

        return sb.toString();
    }

    public Venda clone(){
        return new Venda(this);
    }

    /**
     * Método que verifica se uma venda é válida
     * @return true se for válida, false caso contrário
     */
    public boolean validaVenda(){
        return this.validaFilial()
                && this.validaMes()
                && this.validaQuant()
                && this.validaPreco()
                && this.validaTipo();
    }

    /**
     * Método que valida o preço de uma venda
     * @return true se estiver entre 0 e 999.99, false caso contrário
     */
    private boolean validaPreco(){
        return 0 <= this.preco && this.preco <=999.99 ;
    }

    /**
     * Método que valida o mês de uma venda
     * @return true se estiver entre 1 e 12, false caso contrário
     */
    private boolean validaMes(){
        return 0 < this.mes  && this.mes < 13;
    }

    /**
     * Método que valida a filial de uma venda
     * @return true se estiver entre 1 e 3, false caso contrário
     */
    private boolean validaFilial(){
        return 0 < this.filial && this.filial < 4;
    }

    /**
     * Método que valida o tipo de uma venda
     * @return true se for N ou P, false caso contrário
     */
    private boolean validaTipo(){
        return this.tipo.equals("N") || this.tipo.equals("P");
    }

    /**
     * Método que valida a quantidade de uma venda
     * @return true se estiver entre 1 e 200, false caso contrário
     */
    private boolean validaQuant() {
        return 0 < this.quantidade && this.quantidade < 201;
    }

    /**
     * Método que calcula o total faturado
     * @return total faturado
     */
    public double totalFaturado(){
        return this.quantidade*this.preco;
    }
}
