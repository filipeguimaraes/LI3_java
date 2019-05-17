import java.util.HashMap;
import java.util.Map;

public class DadosAnual {
    String codProd;
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
}
