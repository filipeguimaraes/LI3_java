import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
