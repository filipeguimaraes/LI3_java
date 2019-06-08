package Produtos;

import java.io.Serializable;
import java.util.Objects;
import static java.lang.Integer.parseInt;

/**
 * Produto, Class que contém o código de produto.
 *
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçalo Ferreira A84073
 */
public class Produto implements Comparable<Produto>, IProduto, Serializable {
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

    /**
     * Método que verifica se um produto é válido
     * @param s código de produto
     * @return true se for válido, false caso contrário
     */
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

    /**
     * Método que calcula o hashCode de um código de produto
     * @return hashCode de um código de produto
     */
    public int hashCode(){
        return this.codProd.hashCode();
    }

    /**
     * Método que compara dois produtos segundo a ordem alfabética
     * @param p produto
     * @return 0 se forem iguais, 1 se o primeiro for lexicograficamente maior que o segundo e -1 caso contrário
     */
    public int compareTo(Produto p){return this.codProd.compareTo(p.getCodProd());}


}
