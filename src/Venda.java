import java.util.Objects;

public class Venda {

    private String codProd;
    private double preco;
    private int compra;
    private String tipo;
    private String codCli;
    private int mes;
    private int filial;

    public Venda() {
        this.codProd = "";
        this.preco = 0;
        this.compra = 0;
        this.tipo = "";
        this.codCli = "";
        this.mes = 1;
        this.filial = 1;
    }

    public Venda(String codProd, double preco, int compra, String tipo, String codCli, int mes, int filial) {
        this.codProd = codProd;
        this.preco = preco;
        this.compra = compra;
        this.tipo = tipo;
        this.codCli = codCli;
        this.mes = mes;
        this.filial = filial;
    }

    public Venda(Venda umaVenda) {
        this.codProd = umaVenda.getCodProd();
        this.preco = umaVenda.getPreco();
        this.compra = umaVenda.getCompra();
        this.tipo = umaVenda.getTipo();
        this.codCli = umaVenda.getCodCli();
        this.mes = umaVenda.getMes();
        this.filial = umaVenda.getFilial();
    }


    public String getCodProd() {
        return codProd;
    }

    public void setCodProd(String codProd) {
        this.codProd = codProd;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getCompra() {
        return compra;
    }

    public void setCompra(int compra) {
        this.compra = compra;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCodCli() {
        return codCli;
    }

    public void setCodCli(String codCli) {
        this.codCli = codCli;
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
                compra == venda.compra &&
                mes == venda.mes &&
                filial == venda.filial &&
                Objects.equals(codProd, venda.codProd) &&
                Objects.equals(tipo, venda.tipo) &&
                Objects.equals(codCli, venda.codCli);
    }

    public String toString() {
        return "Venda{" +
                "codProd='" + codProd + '\'' +
                ", preco=" + preco +
                ", compra=" + compra +
                ", tipo='" + tipo + '\'' +
                ", codCli='" + codCli + '\'' +
                ", mes=" + mes +
                ", filial=" + filial +
                '}';
    }

    public Venda clone(){
        return new Venda(this);
    }
}
