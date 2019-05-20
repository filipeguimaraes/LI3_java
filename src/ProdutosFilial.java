import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ProdutosFilial {
    int filial;
    private Map<String, DadosAnual> produtos_filial;

    public ProdutosFilial() {
        this.filial = 0;
        this.produtos_filial = new HashMap<String, DadosAnual>();
    }

    public ProdutosFilial(int filial) {
        this.filial = filial;
        this.produtos_filial = new HashMap<String, DadosAnual>();
    }

    public Map<String, DadosAnual> getprodutos_filial() {
        return produtos_filial;
    }

    public void setprodutos_filial(Map<String, DadosAnual> produtos_filial) {
        this.produtos_filial = produtos_filial;
    }

    public int getFilial() {
        return filial;
    }

    public void setFilial(int filial) {
        this.filial = filial;
    }

    public Set<String> getCodClisFilial(){
        return this.produtos_filial.keySet();
    }

    public void addProdutosFilial(String codProd, int mes, int quant, double fat_N, double fat_P){
        if(!this.produtos_filial.containsKey(codProd)){
            DadosAnual da = new DadosAnual(codProd);
            this.produtos_filial.put(codProd,da);
        }
        this.produtos_filial.get(codProd).addDadosAnual(mes,quant,fat_N,fat_P);
    }

    public int getNumVendasRegMes(int mes){
        int r = 0;
        for(DadosAnual da : this.produtos_filial.values()){
            r += da.getNumVendidoMes(mes);
        }
        return r;
    }
}
