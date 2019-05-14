import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ListaComprasProd {
    private String codProd;
    private List<Venda> lista;

    public ListaComprasProd(){
        this.codProd="";
        this.lista= new ArrayList<>();
    }

    public ListaComprasProd(String codProd, List<Venda> lista) {
        this.codProd = codProd;
        this.lista=lista;
    }

    public ListaComprasProd(ListaComprasProd l){
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
        return lista;
    }

    public void setLista(List<Venda> lista) {
        this.lista = lista;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListaComprasProd that = (ListaComprasProd) o;
        return codProd.equals(that.codProd) &&
                lista.equals(that.lista);
    }

    public String toString() {
        return "ListaComprasProd{" +
                "codProd='" + codProd + '\'' +
                ", lista=" + lista +
                '}';
    }

    public ListaComprasProd clone(){
        return new ListaComprasProd(this);
    }

    public void addDados(Venda v){
        this.lista.add(v);
    }

}
