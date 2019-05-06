public interface IVenda {
    public String getCodProd();
    public void setCodProd(String codProd);
    public String getCodCli();
    public void setCodCli(String codCli);
    public double getPreco();
    public void setPreco(double preco);
    public int getQuantidade();
    public void setQuantidade(int quantidade);
    public String getTipo();
    public void setTipo(String tipo);
    public int getMes();
    public void setMes(int mes);
    public int getFilial();
    public void setFilial(int filial);
    public boolean validaVenda();
    public boolean validaPreco();
    public boolean validaMes();
    public boolean validaFilial();
    public boolean validaTipo();
    public boolean validaQuant();
    public double totalFaturado();
}
