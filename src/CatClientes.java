import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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
        return this.clientes.contains(s);
    }

        public int readClientes(String fich){
        String linha = null;
        int i=0;
        try(
                BufferedReader inStream = new BufferedReader(new FileReader(fich))){
            while((linha= inStream.readLine())!=null){
                
                
                if (Cliente.validaCliente(linha)) {
                    ICliente c = new Cliente(linha);
                    clientes.add(c);
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
