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

    /**
     * Método que retorna o código de produto.
     * @return String código do produto ao qual pertence estes dados.
     */
    public String getCodProd() {
        return codProd;
    }

    /**
     * Método que altera a variavel de instancia codProd pelo input do método.
     * @param codProd String código do produto.
     */
    public void setCodProd(String codProd) {
        this.codProd = codProd;
    }

    private List<Venda> getLista() {
        return lista.stream().map(Venda::clone).collect(Collectors.toList());
    }

    /**
     * Método que retorna o número de vendas deste produto.
     * @return Inteiro número de vendas deste produto.
     */
    public int getNumVendasProd(){
        return this.lista.size();
    }

    /**
     * Método que retorna um array com o número de registo de vendas feito em cada mês.
     * @return Array de inteiros com o número de registo de vendas feito em cada mês.
     * Sendo que o índice 0 do array corresponde ao mês 1, e assim sucessivamente.
     */
    public int [] getNumComprasMes(){
        int [] r = new int [12];
        for(Venda v : this.lista){
            r[v.getMes()-1]++;
        }
        return r;
    }

    /**
     * Método que retorna um array com os gastos das vendas feito em cada mês.
     * @return Array de inteiros com os gastos das vendas feito em cada mês.
     * Sendo que o índice 0 do array corresponde ao mês 1, e assim sucessivamente.
     */
    public double [] getGastoMes(){
        double [] r = new double [12];
        for(Venda v : this.lista){
            r[v.getMes()-1] += v.getPreco() * v.getQuantidade();
        }
        return r;
    }

    /**
     * Método que percorre  a lista de compras, retornando o gasto total.
     * @return double gasto total.
     */
    public double getGastoProd(){
        double r = 0;
        for(Venda v : this.lista){
            r += v.getPreco() * v.getQuantidade();
        }
        return r;
    }

    public String toString() {
        return "ComprasProduto{" +
                "codProd=" + codProd +
                "lista=" + lista +
                '}';
    }

    /**
     * Método que verifica se alguma Venda na lista de compras foi efetuada no mês de input.
     * @param mes int mês.
     * @return true caso seja efetuada a compra nesse mês.
     *         false caso contrário.
     */
    public boolean vendeMes(int mes){
        for(Venda v : lista){
            if(v.getMes()==mes){
                return true;
            }
        }
        return false;
    }

    /**
     * Método que retorna um Set<Integer> que representam os meses em que as vendas são efetuadas.
     * @return Set<Integer> em que os Integer representam os meses em que as vendas são efetuadas.
     */
    public Set<Integer> getMesesEmQueVendeProd(){
        Set<Integer> l = new HashSet<>();
        for(Venda v : this.lista){
            l.add(v.getMes());
        }
        return l;
    }

    private void setLista(List<Venda> lista) {
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

    /**
     * Método que cria uma venda com os seguintes dados e adiciona á lista de vendas.
     * @param filial int corresponde á filial.
     * @param codProd String corresponde ao código do produto.
     * @param codCli String corresponde ao código do cliente.
     * @param preco double corresponde ao preco.
     * @param quant int corresponde á quantidade comprada.
     * @param tipo String tipo de compra.
     * @param mes int corresponde ao mês.
     */
    public void addDados(int filial, String codProd, String codCli, double preco, int quant, String tipo, int mes){
        Venda v = new Venda(codProd,codCli,preco,quant,tipo,mes,filial);
        if(v.validaVenda())this.lista.add(v);
    }

    /**
     * Método que percorre a lista de vendas e adiciona ao Map<Integer, Set<String>> (passado por input do método)
     * o código de cliente que efetuou a compra ao Set<String>, pela key que é o mês em que foi efetuada a venda.
     * @param clis_mes
     */
    public void compraProdutoMes(Map<Integer, Set<String>> clis_mes){
        for(Venda v : this.lista){
            clis_mes.get(v.getMes()).add(v.getCodCli());
        }
    }

    /**
     * Método que percorre a lista de vendas e retorna a soma da quantidade total comprada deste produto.
     * @return Inteiro que reprenseta quantidade total comprada deste produto.
     */
    public int getQuantidadeTotalVendidaProduto(){
        int r = 0;
        for(Venda v : this.lista){
            r += v.getQuantidade();
        }
        return r;
    }

}
