import java.util.*;
import java.util.stream.Collectors;

public class CatFaturacao implements  ICatFaturacao{
    private Map<Integer, ProdutosFilial> vendidos;

    public CatFaturacao() {
        this.vendidos = new HashMap<>();
    }

    //alterado
    public CatFaturacao(Map<Integer, ProdutosFilial> vendidos) {
        this.vendidos = vendidos;
    }

    public CatFaturacao(CatFaturacao cf) {
        this.vendidos = cf.getVendidos();
    }

    public List<String> getListaNaoComprados(List<String> l) {
        return this.adicionaFatNComp(l).stream().sorted().collect(Collectors.toList());
    }

    public int getNComprados(List<String> l){
        return this.getListaNaoComprados(l).size();
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
        return vendidos.equals(that.vendidos);
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vendidos" + this.vendidos.toString());
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
        for (ProdutosFilial pf : this.vendidos.values()) {
            clis_compram.addAll(pf.getCodClisFilial());
        }
        return clis_compram;
    }

    public int getDifs(){
        return this.getSetVendidos().size();
    }


    private TreeSet<String> adicionaFatNComp(List<String> l) {
        TreeSet<String> nao_vendidos = new TreeSet<>();
        TreeSet<String> prods_comprados = this.getSetVendidos();
        for (String s : l) {
            if (!prods_comprados.contains(s)) {
                nao_vendidos.add(s);
            }
        }
        return nao_vendidos;
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



   public Map<Integer,Double> getFatsProdMesFiliais(String prod, int filial){
        if(this.vendidos.containsKey(filial)){
            return this.vendidos.get(filial).getFatsProdMes(prod);
        }
        else{
            return null;
        }
   }

   public Map<Integer,Integer> comprasMes(){
        Map<Integer,Integer> map = new HashMap<>();
        int k;
        for(ProdutosFilial pf : this.vendidos.values()){
            for(Map.Entry<Integer,Integer> me : pf.getComprasMesFilial().entrySet() ){
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

   public Map<Integer,Double> fatFilial(int filial){
        Map<Integer,Double> map = new HashMap<>();
       double k;
       if(this.vendidos.containsKey(filial)){
           for(Map.Entry<Integer,Double> me : this.vendidos.get(filial).getFatMesFilial().entrySet() ){
               if(map.containsKey(me.getKey())){
                   k = map.get(me.getKey()) + me.getValue();
                   map.put(me.getKey(),k);
               }
               else{
                   map.put(me.getKey(),me.getValue());
               }
           }
           return map;
       }
       else{
           return null;
       }
   }

   public double getFaturacaoGlobal(){
        double r = 0;
        for(ProdutosFilial pf : this.vendidos.values()){
            r += pf.getFaturacaoTotalFilial();
        }
        return r;
   }

}
