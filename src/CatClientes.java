import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CatClientes{
    private Set<String> clientes;

    public CatClientes(){
        this.clientes=new TreeSet<>();
    }

    public CatClientes(Set<String> clientes) {
        this.setClientes(clientes);
    }

    public CatClientes(CatClientes cat){
        this.clientes=cat.getClientes();
    }

    public Set<String> getClientes() {
        return clientes;
    }

    public void setClientes(Set<String> clientes) {
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
        return this.clientes.contains(s);
    }

}
