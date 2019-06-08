package Filiais;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;


public class ClientesFilial implements Serializable {
    private int filial;
    private Map<String,ProdutosCompradosCliente> clientes_com_compras;

    public ClientesFilial(){
        this.filial = 0;
        this.clientes_com_compras = new HashMap<String, ProdutosCompradosCliente>();
    }

    public int getFilial() {
        return filial;
    }

    private Map<String, ProdutosCompradosCliente> getClientes_com_compras() {
        return clientes_com_compras;
    }

    private void setClientes_com_compras(Map<String, ProdutosCompradosCliente> clientes_com_compras) {
        this.clientes_com_compras = clientes_com_compras;
    }

    /**
     * Método que altera o int relativo a filialá qual a informação da classe pretence.
     * @param filial Inteiro que representa a filial.
     */
    public void setFilial(int filial) {
        this.filial = filial;
    }

    /**
     * Método que adiciona a informação relativa a uma Venda na estrutura.
     * @param filial Inteiro que representa a filial.
     * @param codProd String do código de produto.
     * @param codCli String do código de cliente.
     * @param preco Double que representa o preço.
     * @param quant Inteiro que representa a quantidade comprada.
     * @param tipo String que representa o tipo de compra.
     * @param mes Inteiro que representa o mês.
     */
    public void addListaComprasProd(int filial, String codProd, String codCli, double preco, int quant, String tipo, int mes){
        if(!this.clientes_com_compras.containsKey(codCli)){
            ProdutosCompradosCliente lpc = new ProdutosCompradosCliente();
            lpc.setCliente(codCli);
            this.clientes_com_compras.put(codCli,lpc);
        }
        this.clientes_com_compras.get(codCli).addCompraProd(filial,codProd,codCli,preco,quant,tipo,mes);
    }


    /**
     * Método que percorre toda a sua estrutura e retorna o número global de vendas efetuadas.
     * @return Inteiro que representa o número global de vendas efetuadas.
     */
    public int getNumProdCompras(){
        int r=0;
        for( Integer i : this.clientes_com_compras.values().stream().map(ProdutosCompradosCliente::getNumProdCompras).collect(Collectors.toList()))
            r += i;
        return  r;
    }

    /**
     * Método que dado um mês(int mes) e um Set de clientes já verificados(Set<String> aux), adiciona a um novo
     * Set de clientes os que ainda nao se encontram do aux e compraram nesse mês.
     * @param mes Inteiro que representa um mês.
     * @param aux Set<String> de clientes que já foram verificados.
     * @return Set<String> de clientes que ainda não foram verificados e que compram no mês passado por argumento.
     */
    public HashSet<String> getHashSetClientesCompramMes(int mes, Set<String> aux){
        HashSet<String> clis = new HashSet<>();
        for(ProdutosCompradosCliente pcc : this.clientes_com_compras.values())
            if((!aux.contains(pcc.getCliente())) && pcc.clienteCompraNoMes(mes)) clis.add(pcc.getCliente());
        return clis;
    }

    /**
     * Método que dado um mês(int mes), adiciona a um novo Set de clientes os que compraram nesse mês.
     * @param mes Inteiro que representa um mês.
     * @return Set<String> de clientes que compram no mês passado por argumento.
     */
    public Set<String> getSetClientesCompramMes(int mes){
        HashSet<String> aux = new HashSet<>();
        for(ProdutosCompradosCliente pcc : this.clientes_com_compras.values())
            if((!aux.contains(pcc.getCliente())) && pcc.clienteCompraNoMes(mes)) aux.add(pcc.getCliente());
        return aux;
    }

    /**
     * Método que recebe um código de produto e uma Map<Integer,Set<String>> (contendo os meses e os
     * respetivos Sets de clientes que o compram nesse mês), usando o método addProdsCompramMes() para
     * percorre todos os clientes dessa filial adicionando o seu código quando compra em cada mês, respetivamente.
     * @param prod String código do produto.
     * @param prods_mes Map<Integer,Set<String>> Map auxiliar para conter os dados necessários.
     */
    public void addClientesCompramProdMes(String prod, Map<Integer,Set<String>> prods_mes){
        for(ProdutosCompradosCliente pcc : this.clientes_com_compras.values())
            pcc.addProdsCompramMes(prod,prods_mes);
    }

    /**
     * Método que recebe um código de cliente, e retorna um array com os números de registo de efetuadas,
     * respetivamente em cada mês.
     * @param cliente String código de cliente.
     * @return Array de inteiros com os números de registo de efetuadas, respetivamente em cada mês.
     * Sendo que o índice 0 do array corresponde ao mês 1, e assim sucessivamente.
     */
    public int [] getClienteQuantidadeCompradaFilialMeses(String cliente){
        return this.clientes_com_compras.get(cliente).getNumRegComprasMeses();
    }

    /**
     * Método que recebe um código de cliente, e retorna um array com os gastos totais,
     * respetivamente em cada mês.
     * @param cliente String código de cliente.
     * @return Array de doubles com os gastos totais, respetivamente em cada mês.
     * Sendo que o índice 0 do array corresponde ao mês 1, e assim sucessivamente.
     */
    public double [] getClienteGastosCompradaFilialMeses(String cliente){
        return  this.clientes_com_compras.get(cliente).getGastosMesesTotal();
    }

    /**
     * Método que recebe um código de cliente e uma Map<Integer,Set<String>> (contendo os meses e os respetivos
     * Sets de produtos comprados nesse mês), usando o método verificaCompraDeProdutosDiferentes() para
     * percorre todos os clientes dessa filial adicionando os códigos de produto que compra em cada mês, respetivamente.
     * @param cliente String código do cliente.
     * @param prods_mes Map<Integer,Set<String>> Map auxiliar para conter os dados necessários.
     */
    public void verificaComprasDeClientes(String cliente, Map<Integer,Set<String>> prods_mes){
        this.clientes_com_compras.get(cliente).verificaCompraDeProdutosDiferentes(prods_mes);
    }

    /**
     * Método que recebe um código de cliente, retorna um Map<String,Integer> contendo a quantidade (value)
     * correspondente a cada mês (key).
     * @param cliente String código do cliente.
     * @return Map<String, Integer> contendo a quantidade correspondente a cada mês.
     */
    public Map<String,Integer> getProdsQuantCli(String cliente){
        Map<String,Integer> map = new HashMap<>();
        if(this.clientes_com_compras.containsKey(cliente)){
            for(AbstractMap.SimpleEntry<String,Integer> se : this.clientes_com_compras.get(cliente).getListaProdutoQuantidade()){
                map.put(se.getKey(),se.getValue());
            }
        }
        return map;
    }

    /**
     * Método que recebe código de produto e um Set<String> contendo os clientes já percorridos, verifica em todos
     * os clientes se compram nesta filial esse produto, que caso seja verdade é adicionado o código do respetivo
     * cliente a um Set<String>  que contém os clientes que compram esse produto nesta filial.
     * @param prod String código do produto.
     * @param old Set<String> de clientes já adicionados.
     * @return Set<String>  que contém os clientes que compram esse produto nesta filial, e que ainda não foram verificados.
     */
    public Set<String> getClisCompramProduto(String prod, Set<String> old){
        Set<String> set = new HashSet<>();
        for(ProdutosCompradosCliente pcc : this.clientes_com_compras.values()){
            if(!old.contains(prod) && pcc.clienteCompraProd(prod)) set.add(pcc.getCliente());
        }
        return set;
    }

    /**
     * Método que percorre todos os clientes para o obter o par (código cliente,gasto total), ordenando assim por
     * gastos totais e devolvendo uma string com os Top 3 de clientes que mais gastam nesta filial.
     * @return String com os Top 3 de clientes que mais gastam nesta filial.
     * Exemplo: "(1º que mais gastou) (2º que mais gastou) (3º que mais gastou)"
     */
    public String getTop3Faturacao(){
        List<Map.Entry<String,Double>> l = new ArrayList<>();
        for(ProdutosCompradosCliente pcc : this.clientes_com_compras.values()){
            l.add(new AbstractMap.SimpleEntry<String,Double>(pcc.getCliente(),pcc.getGastosTotal()));
        }
        Comparator<Map.Entry<String,Double>> c = (o1, o2) -> {
            if(o1.getValue()<o2.getValue()) {
                return 1;
            }
            else{
                if(o1.getValue()>o2.getValue()){
                    return -1;
                }
                else{
                    return (o1.getKey().compareTo(o2.getKey()));
                }
            }
        };
        l.sort(c);
        int i;
        StringBuilder sb;
        sb = new StringBuilder();
        for (i=0;i<2;i++) {
            sb.append(l.get(i).getKey()).append(" ");
        }
        sb.append(l.get(i).getKey());
        return sb.toString();
    }

    /**
     * Método percorre todos os clientes, retornando uma Map<String,Set<String>> com em que a key é o código de cliente
     * e value Set<String> que contem os códigos dos diferentes produtos comprados pelo respetivo cliente.
     * @return Map<String,Set<String>> de clientes e os produtos diferentes comprados pelo mesmo.
     */
    public Map<String,Set<String>> getClienteProdsDiferentes(){
        Map<String,Set<String>> map = new HashMap<>();
        for(ProdutosCompradosCliente pcc : this.clientes_com_compras.values()){
            map.put(pcc.getCliente(), pcc.getProdsDifComprados());
        }
        return  map;
    }

    /**
     * Método que dado um código de produto, percorre todos os clientes, retornando um Map<String,Double> em que a
     * key é o codigo de produto e com o respetivo value de faturação total nesta filial.
     * @param prod String código do produto.
     * @return Map<String,Double> de código de produto e faturação total nesta filial.
     */
    public Map<String,Double> getClisFatProd(String prod){
        Map<String,Double> map = new HashMap<>();
        double d;
        for(ProdutosCompradosCliente pcc : this.clientes_com_compras.values()){
            d = pcc.getGastoProdutoTotal(prod);
            if(d != 0){
                map.put(pcc.getCliente(),d);
            }
        }
        return map;
    }

    /**
     * Método que retorna o Set<String> dos códigos de cliente sque compram nesta filial.
     * @return Set<String> códigos de cliente.
     */
    public Set<String> getClisCompramFilial(){
        return this.clientes_com_compras.keySet();
    }
}



