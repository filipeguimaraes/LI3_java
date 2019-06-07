import java.util.HashMap;
import java.util.Map;

public class DadosAnual {
    private String codProd;
    private Map<Integer, DadosMes> dados_mensais;

    public DadosAnual() {
        this.codProd = "";
        this.dados_mensais = new HashMap<Integer, DadosMes>();
    }

    public DadosAnual(String codProd) {
        this.codProd = codProd;
        this.dados_mensais = new HashMap<Integer, DadosMes>();
    }

    public DadosAnual(String codProd, Map<Integer, DadosMes> dados_mensais) {
        this.codProd = codProd;
        this.dados_mensais = dados_mensais;
    }

    public String getCodProd() {
        return codProd;
    }

    public void setCodProd(String codProd) {
        this.codProd = codProd;
    }

    public Map<Integer, DadosMes> getDados_mensais() {
        return dados_mensais;
    }

    public void setDados_mensais(Map<Integer, DadosMes> dados_mensais) {
        this.dados_mensais = dados_mensais;
    }

    public void addDadosAnual(int mes, int quant, double fat_N, double fat_P){
        if(!this.dados_mensais.containsKey(mes)){
            DadosMes dm = new DadosMes(mes);
            this.dados_mensais.put(mes,dm);
        }
        this.dados_mensais.get(mes).addDadosMes(quant,fat_N,fat_P);
    }

    public int getNumVendidoMes(int mes){
        if(this.dados_mensais.containsKey(mes))
            return this.dados_mensais.get(mes).getRegisto_vendas();
        else
            return 0;
    }

    public double getFaturadoMes(int mes){
        if(this.dados_mensais.containsKey(mes))
            return this.dados_mensais.get(mes).getFaturacao_N()+this.dados_mensais.get(mes).getFaturacao_P();
        else
            return 0;
    }

    public int getQuantidadeAnual(){
        int r = 0;
        for(DadosMes dm : this.dados_mensais.values()){
            r += dm.getQuantidade();
        }
        return r;
    }

    public Map<Integer,Double> getFaturacaoPorMes(){
        Map<Integer,Double> map = new HashMap<>();
        for (DadosMes dm : this.dados_mensais.values()){
            map.put(dm.getMes(),dm.getFaturacaoTotal());
        }
        return map;
    }

    public Map<Integer,Integer> getComprasMesProduto(){
        Map<Integer,Integer> map = new HashMap<>();
        for(DadosMes dm : this.dados_mensais.values()){
            map.put(dm.getMes(),dm.getRegisto_vendas());
        }
        return map;
    }

    public Map<Integer,Double> getFaturacaoFilial(){
        Map<Integer,Double> map = new HashMap<>();
        for(DadosMes dm : this.dados_mensais.values()){
            map.put(dm.getMes(),dm.getFaturacaoTotal());
        }
        return map;
    }

    public double getFaturacaoTotalProd(){
        double r = 0;
        for(DadosMes dm : this.dados_mensais.values()){
            r += dm.getFaturacaoTotal();
        }
        return r;
    }
}
