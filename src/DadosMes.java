import java.util.Objects;

public class DadosMes {
    private int quantidade;
    private double faturacao_N;
    private double faturacao_P;
    private int registo_vendas;

    public DadosMes(){
        this.quantidade = 0;
        this.faturacao_N = 0;
        this.faturacao_P=0;
        this.registo_vendas = 0;

    }

    public DadosMes(int quantidade, double faturacao_N, double faturacao_P, int registo_vendas) {
        this.quantidade = quantidade;
        this.faturacao_N = faturacao_N;
        this.faturacao_P = faturacao_P;
        this.registo_vendas = registo_vendas;
    }

    public DadosMes(DadosMes dm){
        this.quantidade = dm.getQuantidade();
        this.faturacao_N = dm.getFaturacao_N();
        this.faturacao_P=dm.getFaturacao_P();
        this.registo_vendas = dm.getRegisto_vendas();

    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void addQuantidade(int quantidade) {
        this.quantidade += quantidade;
    }

    public double getFaturacao_N() {
        return faturacao_N;
    }

    public void setFaturacao_N(double faturacao_N) {
        this.faturacao_N = faturacao_N;
    }

    public void addFaturacao_N(double faturacao_N) {
        this.faturacao_N += faturacao_N;
    }

    public double getFaturacao_P() {
        return faturacao_P;
    }

    public void setFaturacao_P(double faturacao_P) {
        this.faturacao_P = faturacao_P;
    }

    public void addFaturacao_P(double faturacao_P) {
        this.faturacao_P += faturacao_P;
    }


    public int getRegisto_vendas() {
        return registo_vendas;
    }

    public void setRegisto_vendas(int registo_vendas) {
        this.registo_vendas = registo_vendas;
    }

    public void addRegisto_vendas(int registo_vendas) {
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

}
