package Clientes;

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
 * CatClientes, Class que contém os códigos de clientes.
 *
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçalo Ferreira A84073
 */
public class CatClientes implements ICatClientes, Serializable {
    private Set<ICliente> clientes;

    public CatClientes(){
        this.clientes=new HashSet<>();
    }

    public CatClientes(Set<ICliente> clientes) {
        this.setClientes(clientes);
    }

    public CatClientes(CatClientes cat){
        this.clientes=cat.getClientes();
    }

    public List<String> getListClientes(){
        return this.clientes.stream().map(ICliente::getCodCli).collect(Collectors.toList());
    }

    private Set<ICliente> getClientes() {
        return clientes;
    }

    private void setClientes(Set<ICliente> clientes) {
        this.clientes = clientes;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CatClientes that = (CatClientes) o;
        return clientes.equals(that.clientes);
    }

    public String toString() {
        return "CatClientes{" +
                "clientes=" + clientes +
                '}';
    }

    public CatClientes clone(){
        return new CatClientes(this);
    }


    /**
     * Método que verifica se um determinado cliente existe
     * @param s código de cliente
     * @return true se existir, false caso contrário
     */
    public boolean existeCliente(String s){
        ICliente ic = new Cliente(s);
        return this.clientes.contains(ic);
    }

    /**
     * Método que recebe um ficheiro, lê e insere
     * @param fich nome do ficheiro
     */
    public void loadClientes(String fich){
        String linha;
        try(
                BufferedReader inStream = new BufferedReader(new FileReader(fich))){
            while((linha= inStream.readLine())!=null){
                this.clientes.add(new Cliente(linha));
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
     * Método que adiciona os clientes válidos à estrutura
     * @param fich "Clientes.txt"
     */
    public void readClientes(String fich){
        for( String s : readFilesWithNIO(fich) ){
            if (Cliente.validaCliente(s)) {
                ICliente c = new Cliente(s);
                clientes.add(c);
            }
        }
    }

    /**
     * Método que calcula o número de clientes válidos
     * @return número de clientes válidos
     */
    public int getTamanho(){
        return this.clientes.size();
    }

}
