import java.util.Objects;

public class Produto {
    private String codProd;

    public Produto(){
        this.codProd="";
    }

    public Produto(String codProd){
        this.codProd=codProd;
    }

    public Produto(Produto umProduto){
        this.codProd=umProduto.getCodProd();
    }

    public String getCodProd() {
        return codProd;
    }

    public void setCodProd(String codProd) {
        this.codProd = codProd;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(codProd, produto.codProd);
    }

    public String toString() {
        return "Produto{" +
                "codProd='" + codProd + '\'' +
                '}';
    }

    public Produto clone(){
        return new Produto(this);
    }
}
