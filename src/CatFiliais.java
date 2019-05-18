import javax.print.DocFlavor;
import java.util.*;
import java.util.stream.Collectors;

public class CatFiliais {
    private Map<Integer,ClientesFilial> filial_clientes;

    public CatFiliais(){
        this.filial_clientes = new HashMap<>();
    }

    public CatFiliais(Map<Integer, ClientesFilial> filial_clientes) {
        this.filial_clientes = filial_clientes;
    }

    public Map<Integer, ClientesFilial> getFilial_clientes() {
        return filial_clientes;
    }

    public void setFilial_clientes(Map<Integer, ClientesFilial> filial_clientes) {
        this.filial_clientes = filial_clientes;
    }

    public String toString() {
        return "Filial{" +
                "filial_clientes=" + filial_clientes +
                '}';
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CatFiliais filial = (CatFiliais) o;
        return Objects.equals(filial_clientes, filial.filial_clientes);
    }

    public int hashCode() {
        return Objects.hash(filial_clientes);
    }

    public void addClienteFilial(int filial, String codProd, String codCli, double preco, int quant, String tipo, int mes){
        if(!this.filial_clientes.containsKey(filial)){
            ClientesFilial cf = new ClientesFilial();
            cf.setFilial(filial);
            this.filial_clientes.put(filial,cf);
        }
        this.filial_clientes.get(filial).addListaComprasProd(filial,codProd,codCli,preco,quant,tipo,mes);
    }

    public int getNumProdCompras(){
        int r=0;
        for( Integer i : this.filial_clientes.values().stream().map(ClientesFilial::getNumProdCompras).collect(Collectors.toList()))
            r += i;
        return r;
    }
}
