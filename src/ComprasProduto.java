import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ComprasProduto {
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

    public String getCodProd() {
        return codProd;
    }

    public void setCodProd(String codProd) {
        this.codProd = codProd;
    }

    public List<Venda> getLista() {
        return lista.stream().map(Venda::clone).collect(Collectors.toList());
    }

    public String toString() {
        return "ComprasProduto{" +
                "lista=" + lista +
                '}';
    }

    public int getNumVendasMes(int mes){
        int r = 0;
        for(Venda v : lista){
            if(v.getMes()==mes){
                r++;
            }
        }
        return r;
    }

    public int getNumVezesQueProdComprado(){
        return this.getLista().size();
    }

    public void setLista(List<Venda> lista) {
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

    public void addDados(int filial, String codProd, String codCli, double preco, int quant, String tipo, int mes){
        Venda v = new Venda(codProd,codCli,preco,quant,tipo,mes,filial);
        if(v.validaVenda())this.lista.add(v);
    }

}
