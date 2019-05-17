import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class CatClientes{
    private Set<ICliente> clientes;

    public CatClientes(){
        this.clientes=new TreeSet<>();
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

    public Set<ICliente> getClientes() {
        return clientes;
    }

    public void setClientes(Set<ICliente> clientes) {
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


    public boolean existeCliente(String s){
        ICliente ic = new Cliente(s);
        return this.clientes.contains(ic);
    }

    public static List<String> readFilesWithNIO(String filePath) {
        Path p = Paths.get(filePath);
        List<String> l = null;
        try {
            l = Files.readAllLines(p, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return l;
    }


    public void readClientes(String fich){
        for( String s : readFilesWithNIO(fich) ){
            if (Cliente.validaCliente(s)) {
                ICliente c = new Cliente(s);
                clientes.add(c);
            }
        }
    }

}
