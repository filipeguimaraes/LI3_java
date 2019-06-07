import javax.print.DocFlavor;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

public class CatFiliais implements ICatFiliais {
    private Map<Integer,ClientesFilial> filial_clientes;

    public CatFiliais(){
        this.filial_clientes = new HashMap<>();
    }

    public CatFiliais(Map<Integer, ClientesFilial> filial_clientes) {
        this.filial_clientes = filial_clientes;
    }

    private Map<Integer, ClientesFilial> getFilial_clientes() {
        return filial_clientes;
    }

    private void setFilial_clientes(Map<Integer, ClientesFilial> filial_clientes) {
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

    private Set<String> getSetClientesQueCompram(){
        Set<String> clis_compram = new HashSet<>();
        for(ClientesFilial cf : this.filial_clientes.values()){
            clis_compram.addAll(cf.getClisCompramFilial());
        }
        return clis_compram;
    }

    public int getNumeroClientesQueCompram(){
        return this.getSetClientesQueCompram().size();
    }

    private Set<String> getSetClientesQueNaoCompram(List<String> l) {
        Set<String> nao_vendidos = new HashSet<>();
        Set<String> prods_comprados = this.getSetClientesQueCompram();
        for (String s : l) {
            if (!prods_comprados.contains(s)) {
                nao_vendidos.add(s);
            }
        }
        return nao_vendidos;
    }

    public int getNumeroClientesQueNaoCompram(List<String> l){
        return this.getSetClientesQueNaoCompram(l).size();
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

    public int getNumClientesCompramMesFilial(int filial, int mes){
        HashSet<String> clis_compra_mes;
        clis_compra_mes= new HashSet<>();
        if(filial==0){
            for (ClientesFilial cf : this.filial_clientes.values()) {
                //clis_compra_mes.addAll(cf.getHashSetClientesCompramMes(mes,clis_compra_mes));
                cf.getHashSetClientesCompramMes(mes,clis_compra_mes);
            }
        }
        else {
            if(this.filial_clientes.containsKey(filial)){
                //clis_compra_mes = this.filial_clientes.get(filial).getHashSetClientesCompramMes(mes,clis_compra_mes);
                this.filial_clientes.get(filial).getHashSetClientesCompramMes(mes,clis_compra_mes);
            }
            else{
                return 0;
            }
        }
        return clis_compra_mes.size();
    }

    public Map<Integer,Integer> getMesClientesDiferentes(int filial){
        Map<Integer,Integer> map = new HashMap<>();
        int i;
        for (i=1; i<13; i++){
            if(this.filial_clientes.containsKey(filial)){
                map.put(i,this.filial_clientes.get(filial).getSetClientesCompramMes(i).size());
            }
        }
        return map;
    }

    public List<String> getQuantosProdsDifsEGastos(String cliente){
        Map<Integer,Set<String>> prods_mes = new HashMap<>();
        List<String> l = new ArrayList<>();
        StringBuilder sb;
        int [] r_quant_reg_total = new int [12];
        double [] r_gastos_total = new double [12];
        double [] gastos_filial;
        int [] quantos_filial;
        int i;
        for(ClientesFilial cf : this.filial_clientes.values()){
            quantos_filial = cf.getClienteQuantidadeCompradaFilialMeses(cliente);
            gastos_filial = cf.getClienteGastosCompradaFilialMeses(cliente);
            cf.verificaComprasDeClientes(cliente,prods_mes);
            for(i=0;i<12;i++){
                r_quant_reg_total[i] += quantos_filial[i];
                r_gastos_total[i] += gastos_filial[i];
            }
        }
        int num_clis;
        for (i=0;i<12;i++){
            num_clis = 0;
            sb = new StringBuilder();
            if(prods_mes.containsKey(i+1)) num_clis = prods_mes.get(i+1).size();
            sb.append(r_quant_reg_total[i]).append(" ")
              .append(num_clis).append(" ")
              .append(r_gastos_total[i]);
            l.add(sb.toString());
        }
        return l;
    }


    public int [] getClisDifsCompramProdMeses(String produto){
        Map<Integer,Set<String>> clis_mes = new HashMap<>();
        int i;
        for (i=1;i<13;i++) {
            Set<String> set = new HashSet<>();
            clis_mes.put(i, set);
        }
        for(ClientesFilial cf : this.filial_clientes.values()){
            cf.addClientesCompramProdMes(produto,clis_mes);
        }
        int [] num_clis = new int [12];
        for (i=1;i<13;i++){
            if(clis_mes.containsKey(i)) num_clis[i-1] = clis_mes.get(i).size();
        }
        return num_clis;
    }

    public Map<String,Integer> getProdutoQuantidadeDeUmCliente(String cliente){
        Map<String,Integer> map = new HashMap<>();
        int k;
        for(ClientesFilial cf : this.filial_clientes.values()){
            for(Map.Entry<String,Integer> me : cf.getProdsQuantCli(cliente).entrySet() ){
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

    public int getNumClisDiferentesCompraProd(String prod){
        Set<String> set = new HashSet<>();
        for(ClientesFilial cf : this.filial_clientes.values()){
            set.addAll(cf.getClisCompramProduto(prod,set));
        }
        return set.size();
    }

    public String getTop3CompradoresFilial(int filial){
        if(this.filial_clientes.containsKey(filial))
            return this.filial_clientes.get(filial).getTop3Faturacao();
        else return "N/A";
    }

    public List<Map.Entry<String,Integer>> getClienteNumProdsCompDiferentes(){
        List<Map.Entry<String,Integer>> l = new ArrayList<>();
        Map<String,Set<String>> map = new HashMap<>();
        for(ClientesFilial cf : this.filial_clientes.values()){
            for(Map.Entry<String,Set<String>> me : cf.getClienteProdsDiferentes().entrySet() ){
                if(map.containsKey(me.getKey())){
                    Set<String> aux = new HashSet<>(map.get(me.getKey()));
                    aux.addAll(me.getValue());
                    map.put(me.getKey(),aux);
                }
                else{
                    map.put(me.getKey(),me.getValue());
                }
            }
        }
        for (Map.Entry<String,Set<String>> me : map.entrySet()){
            l.add(new AbstractMap.SimpleEntry<>(me.getKey(),me.getValue().size()));
        }
        return l;
    }

    public List<Map.Entry<String,Double>> getClientesFaturacaoProd(String prod){
        List<Map.Entry<String,Double>> l = new ArrayList<>();
        Map<String,Double> map = new HashMap<>();
        for(ClientesFilial cf : this.filial_clientes.values()){
            for(Map.Entry<String,Double> me : cf.getClisFatProd(prod).entrySet() ){
                if(map.containsKey(me.getKey())){
                    map.put(me.getKey(),map.get(me.getKey())+me.getValue());
                }
                else{
                    map.put(me.getKey(),me.getValue());
                }
            }
        }
        for (Map.Entry<String,Double> me : map.entrySet()){
            l.add(new AbstractMap.SimpleEntry<>(me.getKey(),me.getValue()));
        }
        return l;
    }
}
