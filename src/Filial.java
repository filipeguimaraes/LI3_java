import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Filial {
    private Map<Integer,Map<String, Map<String,ListaComprasProd>>> clientes_compram;

    public Filial(){
        this.clientes_compram=new HashMap<>();
    }

    public Filial(Map<Integer, Map<String, Map<String, ListaComprasProd>>> clientes_compram) {
        this.clientes_compram = clientes_compram;
    }

    public Filial(Filial f){
        this.clientes_compram=f.getClientes_compram();
    }

    public Map<Integer, Map<String, Map<String, ListaComprasProd>>> getClientes_compram() {
        return clientes_compram;
    }

    public void setClientes_compram(Map<Integer, Map<String, Map<String, ListaComprasProd>>> clientes_compram) {
        this.clientes_compram = clientes_compram;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Filial filial = (Filial) o;
        return clientes_compram.equals(filial.clientes_compram);
    }

    public String toString() {
        return "Filial{" +
                "clientes_compram=" + clientes_compram +
                '}';
    }

    public Filial clone(){
        return new Filial(this);
    }

    public void addListaComprasProd(int fil, String codCli, String codProd, Venda v){
        ListaComprasProd lcp;
        Map<String, Map<String,ListaComprasProd>> cli = new HashMap<>();
        Map<String,ListaComprasProd> dadosvendas = new HashMap<>();
        if(this.clientes_compram.containsKey(fil)){
            cli = this.clientes_compram.get(fil);
            if(cli.containsKey(codCli)){
                dadosvendas = cli.get(codCli);
                if(dadosvendas.containsKey(codProd)){
                    lcp = dadosvendas.get(codProd);
                    lcp.addDados(v);
                } else{
                    lcp = new ListaComprasProd();
                    lcp.addDados(v);
                    dadosvendas.put(codProd,lcp.clone());
                }
            }
            else{
                lcp = new ListaComprasProd();
                lcp.addDados(v);
                dadosvendas.put(codProd,lcp.clone());
                cli.put(codCli,dadosvendas);
            }
        }
        else{
            lcp = new ListaComprasProd();
            lcp.addDados(v);
            dadosvendas.put(codProd,lcp.clone());
            cli.put(codCli,dadosvendas);
            this.clientes_compram.put(fil,cli);
        }

    }


}
