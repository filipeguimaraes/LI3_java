import java.util.*;
import java.util.stream.Collectors;

public class CatFaturacao {
    private Map<Integer, Map<String, Map<Integer, DadosMes>>> vendidos;
    private Set<String> n_vendidos;

    public CatFaturacao(){
        this.vendidos=new HashMap<>();
        this.n_vendidos= new HashSet<>();
    }

    //alterado
    public CatFaturacao(Map<Integer, Map<String, Map<Integer, DadosMes>>> vendidos, Set<String> n_vendidos) {
        this.vendidos = vendidos;
        this.n_vendidos = n_vendidos;
    }

    public CatFaturacao(CatFaturacao cf){
        this.vendidos=cf.getVendidos();
        this.n_vendidos= (Set<String>) cf.getN_vendidos();
    }

    //alt
    public Map<Integer, Map<String, Map<Integer, DadosMes>>> getVendidos() {
        return vendidos;
    }

    //alt
    public void setVendidos(Map<Integer, Map<String, Map<Integer, DadosMes>>> vendidos) {
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

    public Set<String> getN_vendidos() {
        return n_vendidos;
    }

    public void setN_vendidos(Set<String> n_vendidos) {
        this.n_vendidos = n_vendidos;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vendidos" + this.vendidos.toString());
        sb.append("Não vendidos" + this.n_vendidos.toString());
        return sb.toString();
    }

    public CatFaturacao clone(){
        return new CatFaturacao(this);
    }

    public void addDadosMes(String cod, int mes, int quant, double fat_N, double fat_P, int fil){
        DadosMes dm;
        Map<String,Map<Integer,DadosMes>> cli = new HashMap<>();
        Map<Integer,DadosMes> dadosmes = new HashMap<>();
        if(this.vendidos.containsKey(fil)) {
            cli = this.vendidos.get(fil);
            if(cli.containsKey(cod)) {
                dadosmes = cli.get(cod);
                if (dadosmes.containsKey(mes)) {
                    dm = dadosmes.get(mes);
                    dm.addQuantidade(quant);
                    dm.addFaturacao_N(fat_N);
                    dm.addFaturacao_P(fat_P);
                    dm.addRegisto_vendas(1);
                } else {
                    dm = new DadosMes(quant,fat_N,fat_P,1);
                    dadosmes.put(mes,dm.clone());
                }
            }
            else{
                dm = new DadosMes(quant,fat_N,fat_P,1);
                dadosmes.put(mes,dm.clone());
                cli.put(cod,dadosmes);
            }
        }
        else{
            dm = new DadosMes(quant,fat_N,fat_P,1);
            dadosmes.put(mes,dm.clone());
            cli.put(cod,dadosmes);
            this.vendidos.put(fil,cli);
        }
   }


   public Set<String> getSetVendidos(){
        Set<String> clis_compram = new TreeSet<String>();
        for(Map<String,Map<Integer,DadosMes>> ss : new ArrayList<>(this.vendidos.values())){
            clis_compram.addAll(ss.keySet());
        }
        return clis_compram;
   }


    public void adicionaFatNComp(List<String> l){
        Set<String> clis_compram = this.getSetVendidos();
        for(String s : l){
            if(!clis_compram.contains(s)){
                this.addNaoVendidos(s);
            }
        }
    }


   public void addNaoVendidos(String cod){
        this.n_vendidos.add(cod);
   }

}
