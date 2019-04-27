import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class Venda {
    private Produto codProd;
    private Cliente codCli;
    private double preco;
    private int quantidade;
    private String tipo;
    private int mes;
    private int filial;

    public Venda(Produto codProd, Cliente codCli, double preco, int quantidade, String tipo, int mes, int filial) {
        this.codProd = codProd;
        this.codCli = codCli;
        this.preco = preco;
        this.quantidade = quantidade;
        this.tipo = tipo;
        this.mes = mes;
        this.filial = filial;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public Produto getCodProd() {
        return codProd;
    }

    public void setCodProd(Produto codProd) {
        this.codProd = codProd;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Cliente getCodCli() {
        return codCli;
    }

    public void setCodCli(Cliente codCli) {
        this.codCli = codCli;
    }

    public int getFilial() {
        return filial;
    }

    public void setFilial(int filial) {
        this.filial = filial;
    }

    static boolean validaPreco(String preco){
        double p = parseDouble(preco);
        return 0 <= p && p <=999.99 ;
    }

    static boolean validaMes(String mes){
        int m = parseInt(mes);
        return 0 < m && m < 13;
    }

    static boolean validaFilial(String filial){
        int f = parseInt(filial);
        return 0 < f && f < 4;
    }

    static boolean validaTipo(String tipo){
        return tipo.equals("N") || tipo.equals("P");
    }

    static boolean validaQuant(String quant) {
        int q = parseInt(quant);
        return 0 < q && q < 201;
    }
}
