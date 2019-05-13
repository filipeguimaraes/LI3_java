import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CatProdutos{
    private Set<IProduto> produtos;

    public CatProdutos(){
        this.produtos=new TreeSet<>();
    }

    public CatProdutos(Set<IProduto> produtos) {
        this.setProdutos(produtos);
    }

    public CatProdutos(CatProdutos cat){
        this.produtos=cat.getProdutos();
    }

    public Set<IProduto> getProdutos() {
        return produtos;
    }

    public void setProdutos(Set<IProduto> produtos) {
        this.produtos = produtos;
    }

    public List<String> getListProds(){
        return this.produtos.stream().map(IProduto::getCodProd).collect(Collectors.toList());
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
        IProduto ip = new Produto(s);
        return this.produtos.contains(ip);
    }

    public int readProdutos (String fich){
        String linha = null;
        int i=0;
        try(
                BufferedReader inStream = new BufferedReader(new FileReader(fich))){
            while((linha= inStream.readLine())!=null){
                if (Produto.validaProduto(linha)) {
                    IProduto c = new Produto(linha);
                    produtos.add(c);
                    i++;
                }    
            }
        }
        catch(IOException e){
            System.out.println(e);
        }
        return i;
    }

}
