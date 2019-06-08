package Filiais;


import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * CatFiliais, Class que estrutura uma relação entre cliente e compras.
 *
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçalo Ferreira A84073
 */
public class CatFiliais implements ICatFiliais, Serializable {
    private Map<Integer, ClientesFilial> filial_clientes;

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

    private Set<String> getSetClientesQueCompram(){
        Set<String> clis_compram = new HashSet<>();
        for(ClientesFilial cf : this.filial_clientes.values()){
            clis_compram.addAll(cf.getClisCompramFilial());
        }
        return clis_compram;
    }

    /**
     * Método que retorna o número total de clientes que fazem compras.
     * @return Inteiro número total de clientes que fazem compras.
     */
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

    /**
     * Método que retorna o número total de clientes que não fazem compras.
     * @return Inteiro número total de clientes que não fazem compras.
     */
    public int getNumeroClientesQueNaoCompram(List<String> l){
        return this.getSetClientesQueNaoCompram(l).size();
    }

    /**
     * Método que dado os dados relativos a uma compra adiciona á estrutura os mesmos.
     * @param filial Inteiro que representa a filial.
     * @param codProd String do código de produto.
     * @param codCli String do código de cliente.
     * @param preco Double que representa o preço.
     * @param quant Inteiro que representa a quantidade comprada.
     * @param tipo String que representa o tipo de compra.
     * @param mes Inteiro que representa o mês.
     */
    public void addClienteFilial(int filial, String codProd, String codCli, double preco, int quant, String tipo, int mes){
        if(!this.filial_clientes.containsKey(filial)){
            ClientesFilial cf = new ClientesFilial();
            cf.setFilial(filial);
            this.filial_clientes.put(filial,cf);
        }
        this.filial_clientes.get(filial).addListaComprasProd(filial,codProd,codCli,preco,quant,tipo,mes);
    }

    /**
     * Método que retorna o número global de compras feitos.
     * @return Inteiro número global de compras feitos.
     */
    public int getNumProdCompras(){
        int r=0;
        for( Integer i : this.filial_clientes.values().stream().map(ClientesFilial::getNumProdCompras).collect(Collectors.toList()))
            r += i;
        return r;
    }

    /**
     * Método que dado uma filial e um mês, retorna o número de clientes que compram nessa filial ao longo desse mês.
     * @param filial int que representa a filial.
     * @param mes int que representa o mês.
     * @return Inteiro número de clientes que compram nessa filial ao longo desse mês.
     */
    public int getNumClientesCompramMesFilial(int filial, int mes){
        HashSet<String> clis_compra_mes;
        clis_compra_mes= new HashSet<>();
        if(filial==0){
            for (ClientesFilial cf : this.filial_clientes.values()) {
                clis_compra_mes.addAll(cf.getHashSetClientesCompramMes(mes,clis_compra_mes));
            }
        }
        else {
            if(this.filial_clientes.containsKey(filial)){
                clis_compra_mes = this.filial_clientes.get(filial).getHashSetClientesCompramMes(mes,clis_compra_mes);
            }
            else{
                return 0;
            }
        }
        return clis_compra_mes.size();
    }

    /**
     * Método recebendo uma filial, retorna um Map<Integer,Integer> com o número de clientes diferentes(value) que
     * compram num determinado mês(key).
     * @param filial int que representa a filial.
     * @return Map<Integer,Integer> número de clientes diferentes(value) que compram num determinado mês(key).
     */
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

    /**
     * Método que recebe um código de cliente, retorna uma lista em que cada elemento é uma String com o número de
     * registo de vendas respetivo ao cliente, número de produtos diferentes que o comprou e os gastos dessas vendas
     * eftuadas pelo cliente, respetivamente ao mês, por exemplo o primeiro elemento da lista corresponde ao mes 1 (Janeiro).
     * @param cliente Código de cliente (String).
     * @return Lista de Strings. Exemplo: ["3 3 523.23202", ...]
     */
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

    /**
     * Método que recebe um código de produto, e retorna um array com o número de clientes diferentes que
     * compram o produto, respetivamente em cada mês.
     * @param produto String código de produto.
     * @return Array de inteiros com onúmero de clientes diferentes que compram o produto, respetivamente em cada mês.
     * Sendo que o índice 0 do array corresponde ao mês 1, e assim sucessivamente.
     */
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

    /**
     * Método que recebe um código de cliente, retorna um Map<String,Integer> contendo a quantidade (value)
     * correspondente a cada mês (key), em relação a todas as filiais.
     * @param cliente String código do cliente.
     * @return Map<String, Integer> contendo a quantidade correspondente a cada mês.
     */
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

    /**
     * Método que recebe código de produto, verifica em todos os clientes se compram esse produto, que caso
     * seja verdade é adicionado os códigos dos respetivos clientes a um Set<String>  que contém os clientes
     * que compram esse produto.
     * @param prod String código do produto.
     * @return Set<String>  que contém os clientes que compram esse produto.
     */
    public int getNumClisDiferentesCompraProd(String prod){
        Set<String> set = new HashSet<>();
        for(ClientesFilial cf : this.filial_clientes.values()){
            set.addAll(cf.getClisCompramProduto(prod,set));
        }
        return set.size();
    }

    /**
     * Método que recebendo uma filial, verifica se essa existe, devolvendo uma string com os Top 3 de clientes
     * que mais gastam nessa filial.
     * @param filial int que representa uma filial.
     * @return String com os Top 3 de clientes que mais gastam nesta filial.
     * Exemplo: "(1º que mais gastou) (2º que mais gastou) (3º que mais gastou)" ou caso não exista "N/A".
     */
    public String getTop3CompradoresFilial(int filial){
        if(this.filial_clientes.containsKey(filial))
            return this.filial_clientes.get(filial).getTop3Faturacao();
        else return "N/A";
    }

    /**
     * Método percorre todas as filials, retornando uma List<Map.Entry<String,Integer>>
     * (código de cliente, número de diferentes produtos comprados pelo respetivo cliente).
     * @return List<Map.Entry<String,Integer>> de clientes e os produtos diferentes comprados pelo mesmo.
     */
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

    /**
     * Método que dado um código de produto, percorre todas as filiais, retornando uma List<Map.Entry<String,Double>>
     * em que o par é (codigo de produto, e a respetiva faturação).
     * @param prod String código do produto.
     * @return List<Map.Entry<String,Double>> de código de produto e faturação gobal.
     */
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
