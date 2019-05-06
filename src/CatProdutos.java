import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CatProdutos{
    private Set<String> produtos;

    public CatProdutos(){
        this.produtos=new TreeSet<>();
    }

    public CatProdutos(Set<String> produtos) {
        this.setProdutos(produtos);
    }

    public CatProdutos(CatProdutos cat){
        this.produtos=cat.getProdutos();
    }

    public Set<String> getProdutos() {
        return produtos;
    }

    public void setProdutos(Set<String> produtos) {
        this.produtos = produtos;
    }

    public List<String> lista (){
        return this.produtos.stream().collect(Collectors.toList());
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CatProdutos that = (CatProdutos) o;
        return produtos.equals(that.produtos);
    }

    public String toString() {
        return "CatProdutos{" +
                "produtos=" + produtos +
                '}';
    }

    public CatProdutos clone(){
        return new CatProdutos(this);
    }



    public boolean existeProduto(String s){
        return this.produtos.contains(s);
    }

}
