import java.util.Objects;
import static java.lang.Integer.parseInt;

public class Produto implements Comparable<Produto>,IProduto {
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
        StringBuilder sb = new StringBuilder();
        sb.append("codigo produto " + this.codProd);
        return sb.toString();
    }

    public Produto clone(){
        return new Produto(this);
    }

    public static boolean validaProduto(String s){
        boolean r;
        r = s.length()==6;
        for(int i=0;r && i<2;i++)
        r= Character.isUpperCase(s.charAt(i));
        for(int i=0;r && i<2;i++)
        r= Character.isUpperCase(s.charAt(i));
        r = r && (1000 <= parseInt(s.substring(2))  && parseInt(s.substring(2)) <= 9999);
        return r;
    }

    public int hashCode(){
        return this.codProd.hashCode();
    }

    public int compareTo(Produto p){return this.codProd.compareTo(p.getCodProd());}


}
