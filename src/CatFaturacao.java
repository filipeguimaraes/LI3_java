import java.util.*;
import java.util.stream.Collectors;

public class CatFaturacao {
    private Map<Integer, ProdutosFilial> vendidos;
    private Set<String> n_vendidos;

    public CatFaturacao(){
        this.vendidos=new HashMap<>();
        this.n_vendidos= new HashSet<>();
    }

    //alterado
    public CatFaturacao(Map<Integer, ProdutosFilial> vendidos, Set<String> n_vendidos) {
        this.vendidos = vendidos;
        this.n_vendidos = n_vendidos;
    }

    public CatFaturacao(CatFaturacao cf){
        this.vendidos=cf.getVendidos();
        this.n_vendidos= (Set<String>) cf.getN_vendidos();
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

    private Set<String> getN_vendidos() {
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

    public void addCatFaturacao(String cod, int mes, int quant, double fat_N, double fat_P, int fil){
        if(!this.vendidos.containsKey(fil)){
            ProdutosFilial pf = new ProdutosFilial(fil);
            this.vendidos.put(fil,pf);
        }
        this.vendidos.get(fil).addProdutosFilial(cod,mes,quant,fat_N,fat_P);
   }


   private TreeSet<String> getSetVendidos(){
        TreeSet<String> clis_compram = new TreeSet<String>();
        for(ProdutosFilial pf : new ArrayList<>(this.vendidos.values())){
            clis_compram.addAll(pf.getCodClisFilial());
        }
        return clis_compram;
   }


    public void adicionaFatNComp(List<String> l){
        TreeSet<String> prods_comprados = this.getSetVendidos();
        for(String s : l){
            if(!prods_comprados.contains(s)){
                this.addNaoVendidos(s);
            }
        }
    }


   public void addNaoVendidos(String cod){
        this.n_vendidos.add(cod);
   }

}
