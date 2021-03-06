package Produtos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * CatProdutos, Class que contém os códigos de produto.
 *
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçalo Ferreira A84073
 */
public class CatProdutos implements ICatProdutos, Serializable {
    private Set<IProduto> produtos;

    public CatProdutos(){
        this.produtos = new HashSet<>();
    }

    public CatProdutos(Set<IProduto> produtos) {
        this.setProdutos(produtos);
    }

    public CatProdutos(CatProdutos cat){
        this.produtos=cat.getProdutos();
    }

    private Set<IProduto> getProdutos() {
        return produtos;
    }

    private void setProdutos(Set<IProduto> produtos) {
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

    /**
     * Método que verifica se um determinado produto existe
     * @param s código de produto
     * @return true se existir, false caso contrário
     */
    public boolean existeProduto(String s){
        IProduto ip = new Produto(s);
        return this.produtos.contains(ip);
    }

    /**
     * Método que recebe um ficheiro, lê e insere
     * @param fich nome do ficheiro
     */
    public void loadProdutos(String fich){
        String linha;

        try(
                BufferedReader inStream = new BufferedReader(new FileReader(fich))){
            while((linha= inStream.readLine())!=null){
                this.produtos.add(new Produto(linha));
            }
        }
        catch(IOException e){
            System.out.println(e);
        }
    }

    /**
     * Método que lê um ficheiro.
     * @param filePath path do ficheiro
     * @return lista com todas as linhas lidas
     */
    private static List<String> readFilesWithNIO(String filePath) {
        Path p = Paths.get(filePath);
        List<String> l = null;
        try {
            l = Files.readAllLines(p, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return l;
    }

    /**
     * Método que adiciona os produtos válidos à estrutura
     * @param fich "Produtos.txt"
     */
    public void readProdutos (String fich){
        for( String s : readFilesWithNIO(fich) ){
            if (Produto.validaProduto(s)) {
                IProduto c = new Produto(s);
                produtos.add(c);
            }
        }

    }

    /**
     * Método que calcula o número de produtos válidos
     * @return número de produtos válidos
     */
    public int getTamanho(){
        return this.produtos.size();
    }

}
