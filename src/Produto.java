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
        sb.append("codigo produto" + this.codProd);
        return sb.toString();
    }

    public Produto clone(){
        return new Produto(this);
    }

    public boolean validaLetras_Prod (){
        boolean r = true;
        for(int i=0;r && i<2;i++)
        r=Character.isUpperCase(this.codProd.charAt(i));
        return r;
    }

    public boolean validaInt_Prod (){
        boolean r=true;
        for(int i=2;r && i<6;i++){
            r=Character.isDigit(this.codProd.charAt(i));
        }
        return r;
    }

    public boolean compProduto_Prod(){
        return this.codProd.length()==6;
    }



    public boolean validaIntProduto(){
        if(this.validaInt_Prod()){
            return (1000 <= parseInt(this.codProd.substring(2))  && parseInt(this.codProd.substring(2)) <= 9999);
        }
        else return false;
    }


    public boolean validaProduto(){
        if(this.compProduto_Prod()){
            return (this.validaLetras_Prod() && this.validaIntProduto());
        }
        else return false;
    }

    public int compareTo(Produto p){return this.codProd.compareTo(p.getCodProd());}


}
