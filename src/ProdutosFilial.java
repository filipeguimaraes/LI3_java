import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ProdutosFilial {
    int filial;
    private Map<String, DadosAnual> clientes_filial;

    public ProdutosFilial() {
        this.filial = 0;
        this.clientes_filial = new HashMap<String, DadosAnual>();
    }

    public ProdutosFilial(int filial) {
        this.filial = filial;
        this.clientes_filial = new HashMap<String, DadosAnual>();
    }

    public Map<String, DadosAnual> getClientes_filial() {
        return clientes_filial;
    }

    public void setClientes_filial(Map<String, DadosAnual> clientes_filial) {
        this.clientes_filial = clientes_filial;
    }

    public int getFilial() {
        return filial;
    }

    public void setFilial(int filial) {
        this.filial = filial;
    }

    public Set<String> getCodClisFilial(){
        return this.clientes_filial.keySet();
    }

    public void addProdutosFilial(String codProd, int mes, int quant, double fat_N, double fat_P){
        if(!this.clientes_filial.containsKey(codProd)){
            DadosAnual da = new DadosAnual(codProd);
            this.clientes_filial.put(codProd,da);
        }
        this.clientes_filial.get(codProd).addDadosAnual(mes,quant,fat_N,fat_P);
    }
}
