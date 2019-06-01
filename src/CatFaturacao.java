import java.util.*;
import java.util.stream.Collectors;

public class CatFaturacao {
    private Map<Integer, ProdutosFilial> vendidos;
    private List<String> n_vendidos;

    public CatFaturacao() {
        this.vendidos = new HashMap<>();
        this.n_vendidos = new ArrayList<>();
    }

    //alterado
    public CatFaturacao(Map<Integer, ProdutosFilial> vendidos, List<String> n_vendidos) {
        this.vendidos = vendidos;
        this.n_vendidos = n_vendidos;
    }

    public CatFaturacao(CatFaturacao cf) {
        this.vendidos = cf.getVendidos();
        this.n_vendidos = cf.getN_vendidos();
    }

    public List<String> getListaNaoComprados() {
        return this.n_vendidos.stream().sorted().collect(Collectors.toList());
    }

    //alt
    private Map<Integer, ProdutosFilial> getVendidos() {
        return vendidos;
    }

    //alt
    public void setVendidos(Map<Integer, ProdutosFilial> vendidos) {
        this.vendidos = vendidos;
    }

    //alt
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CatFaturacao that = (CatFaturacao) o;
        return vendidos.equals(that.vendidos) &&
                n_vendidos.equals(that.n_vendidos);
    }

    private List<String> getN_vendidos() {
        return n_vendidos;
    }

    public void setN_vendidos(List<String> n_vendidos) {
        this.n_vendidos = n_vendidos;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vendidos" + this.vendidos.toString());
        sb.append("Não vendidos" + this.n_vendidos.toString());
        return sb.toString();
    }

    public CatFaturacao clone() {
        return new CatFaturacao(this);
    }

    public void addCatFaturacao(String cod, int mes, int quant, double fat_N, double fat_P, int fil) {
        if (!this.vendidos.containsKey(fil)) {
            ProdutosFilial pf = new ProdutosFilial(fil);
            this.vendidos.put(fil, pf);
        }
        this.vendidos.get(fil).addProdutosFilial(cod, mes, quant, fat_N, fat_P);
    }


    private TreeSet<String> getSetVendidos() {
        TreeSet<String> clis_compram = new TreeSet<String>();
        for (ProdutosFilial pf : new ArrayList<>(this.vendidos.values())) {
            clis_compram.addAll(pf.getCodClisFilial());
        }
        return clis_compram;
    }


    public void adicionaFatNComp(List<String> l) {
        TreeSet<String> prods_comprados = this.getSetVendidos();
        for (String s : l) {
            if (!prods_comprados.contains(s)) {
                this.addNaoVendidos(s);
            }
        }
    }


    public int getNumVendasRegMesFilial(int filial, int mes) {
        int r = 0;
        if (filial == 0) {
            for (ProdutosFilial pf : this.vendidos.values()) {
                r += pf.getNumVendasRegMes(mes);
            }
        }
        else {
            if(this.vendidos.containsKey(filial)){
                r = this.vendidos.get(filial).getNumVendasRegMes(mes);
            }
            else{
                return 0; // throw...
            }
        }
        return r;
    }

    public int [] getVendasRegMeses(String produto){
        int [] num_reg_vendas = new int [12];
        int [] reg_aux;
        int i;
        for(ProdutosFilial pf : this.vendidos.values()){
            reg_aux = pf.getVendasRegMesesFilial(produto);
            if(reg_aux != null){
                for(i=0; i<12; i++){
                    num_reg_vendas[i] += reg_aux[i];
                }
            }
        }
        return num_reg_vendas;
    }

    public double [] getVendasFaturadoMeses(String produto){
        double [] faturado_vendas = new double [12];
        double [] faturado_aux;
        int i;
        for(ProdutosFilial pf : this.vendidos.values()){
            faturado_aux= pf.getVendasFaturadoMesesFilial(produto);
            if(faturado_aux != null){
                for(i=0; i<12; i++){
                    faturado_vendas[i] += faturado_aux[i];
                }
            }
        }
        return faturado_vendas;
    }

    public Map<String,Integer> getListaProdutosEQuantidadeVendida(){
        Map<String,Integer> map = new HashMap<>();
        int k;
        for(ProdutosFilial pf : this.vendidos.values()){
            for(Map.Entry<String,Integer> me : pf.getProdsQuant().entrySet() ){
                if(map.containsKey(me.getKey())){
                    k = map.get(me.getKey()) + me.getValue();
                    map.put(me.getKey(),k);
                }
                else{
                    map.put(me.getKey(),me.getValue());
                }
            }
        }
        return map;
    }

   public void addNaoVendidos(String cod){
        this.n_vendidos.add(cod);
   }

}
