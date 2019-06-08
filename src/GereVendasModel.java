import Exceptions.MesException;
import Exceptions.ProdutoException;
import Exceptions.ClienteException;
import Clientes.CatClientes;
import Clientes.ICatClientes;
import Produtos.CatProdutos;
import Produtos.ICatProdutos;
import Faturacao.CatFaturacao;
import Faturacao.ICatFaturacao;
import Filiais.CatFiliais;
import Filiais.ICatFiliais;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
/**
 * GereVendasModel, contém todos os dados do sistema.
 *
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçalo Ferreira A84073
 */
public class GereVendasModel implements IGereVendasModel {
    private ICatProdutos CatProds;
    private ICatClientes CatClis;
    private ICatFaturacao CatFat;
    private ICatFiliais CatFiliais;
    private int numero_de_vendas_lidas;
    private int vendas_gratis;
    private String nomeFich;

    public GereVendasModel() {
        CatProds = new CatProdutos();
        CatClis = new CatClientes();
        CatFat = new CatFaturacao();
        CatFiliais = new CatFiliais();
        numero_de_vendas_lidas = 0;
        vendas_gratis = 0;
        nomeFich = "";
    }

    /**
     * Método que valida uma venda.
     * @param s venda
     * @return true se a venda for válida, false caso contrário.
     */
    private static boolean validaVenda(String s){
        String[] split = s.split(" ");
        double preco = parseDouble(split[1]);
        int quantidade = parseInt(split[2]);
        String tipo = split[3];
        int mes = parseInt(split[5]);
        int filial = parseInt(split[6]);
        return 0 <= preco && preco <=999.99
                && 0 < mes  && mes < 13
                && 0 < filial && filial < 4
                && (tipo.equals("N") || tipo.equals("P"))
                && 0 < quantidade && quantidade < 201;
    }

    /**
     * Método que lê um ficheiro.
     * @param filePath path do ficheiro
     * @return lista com todas as linhas lidas
     */
    private static List<String> readFilesWithNIO(String filePath) {
        Path p = Paths.get(filePath);
        List<String> l = null;
        try {
            l = Files.readAllLines(p, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return l;
    }

    @SuppressWarnings("Duplicates")
    private void loadFatFil(String fich){
        String s;
        String[] divd;
        this.numero_de_vendas_lidas = 0;
        this.vendas_gratis = 0;
        try(
                BufferedReader inStream = new BufferedReader(new FileReader(fich))){
            while((s= inStream.readLine())!=null){
                this.numero_de_vendas_lidas++;
                divd = s.split(" ");
                if (divd.length == 7
                        && this.CatClis.existeCliente(divd[4])
                        && this.CatProds.existeProduto(divd[0])
                        && validaVenda(s)) {
                    int mes = parseInt(divd[5]);
                    int filial = parseInt(divd[6]);
                    double preco = parseDouble(divd[1]);
                    int quant = parseInt(divd[2]);

                    this.CatFat.addCatFaturacao(divd[0], mes, quant,
                            (divd[3].equals("N") ? (double) quant * preco : 0),
                            (divd[3].equals("P") ? (double) quant * preco : 0),
                            filial);
                    this.CatFiliais.addClienteFilial(filial,
                            divd[0], divd[4], preco,
                            quant, divd[3], mes);
                    if((double) quant * preco == 0)
                        this.vendas_gratis++;

                }
            }
        }
        catch(IOException e){
            System.out.println(e);
        }
    }

    @SuppressWarnings("Duplicates")
    private void readLinesWithBuff(String fich) {
        this.numero_de_vendas_lidas = 0;
        this.vendas_gratis = 0;
        String[] divd;

        for (String s : readFilesWithNIO(fich)) {
            this.numero_de_vendas_lidas++;
            divd = s.split(" ");
            if (divd.length == 7
                    && this.CatClis.existeCliente(divd[4])
                    && this.CatProds.existeProduto(divd[0])
                    && validaVenda(s)) {
                int mes = parseInt(divd[5]);
                int filial = parseInt(divd[6]);
                double preco = parseDouble(divd[1]);
                int quant = parseInt(divd[2]);

                this.CatFat.addCatFaturacao(divd[0], mes, quant,
                        (divd[3].equals("N") ? (double) quant * preco : 0),
                        (divd[3].equals("P") ? (double) quant * preco : 0),
                        filial);
                this.CatFiliais.addClienteFilial(filial,
                        divd[0], divd[4], preco,
                        quant, divd[3], mes);
            }
        }
    }


    /**
     * Metodo que retorna a lista de produtos não comprados.
     * @return Lista de códigos de produtos não comprados. Exemplo: ["AF1184", "UJ3773", ...]
     */
    public List<String> getListaDeProdutosNaoComprados(){
        return this.CatFat.getListaNaoComprados(this.CatProds.getListProds());
    }


    /**
     * Método que recebe uma filial e um mês, retorna um array com dois inteiros em que o primeiro corresponde
     * ao número de vendas no mês nessa filial e o segundo o número de clientes que compram nesse mês.
     * @param mes Mês do ano (int).
     * @param filial Número de filial (int);
     * @return Array(de inteiros) de duas posições.
     */
    public int [] getQuerie2(int mes, int filial) throws MesException{
        if(mes<1 || mes>12) throw new MesException("Esse mês não é válido!");
        else {
            int[] r = new int[2];
            r[0] = this.CatFat.getNumVendasRegMesFilial(filial, mes);
            r[1] = this.CatFiliais.getNumClientesCompramMesFilial(filial, mes);
            return r;
        }
    }


    /**
     * Método que recebe um código de cliente, retorna uma lista em que cada elemento é uma String com o número de
     * registo de vendas respetivo ao cliente, número de produtos diferentes que o comprou e os gastos dessas vendas
     * eftuadas pelo cliente, respetivamente ao mês, por exemplo o primeiro elemento da lista corresponde ao mes 1 (Janeiro).
     * @param cliente Código de cliente (String).
     * @return Lista de Strings. Exemplo: ["3 3 523.23202", ...]
     */
    public List<String> getQuerie3(String cliente) throws ClienteException{
        if(!this.CatClis.getListClientes().contains(cliente)) throw new ClienteException("Esse cliente não existe!");
        else {
            return this.CatFiliais.getQuantosProdsDifsEGastos(cliente);
        }
    }


    /**
     * Método que recebe um código de produto, retorna uma lista de Strings, em que cada String tem o número de vendas
     * registadas com esse produto, número de clientes diferentes que compram esse produto e total faturado na venda
     * desse produto; respetivamente aos meses.
     * @param produto Código produto (String).
     * @return Lista de Strings. Exemplo: ["3 3 523.23202", ...]
     */
    public List<String> getQuerie4(String produto) throws ProdutoException{
        if(!this.CatProds.getListProds().contains(produto)) throw new ProdutoException("Esse produto não existe!");
        else {
            List<String> l = new ArrayList<>();
            StringBuilder sb;
            int i;
            int [] num_clis;
            int [] reg_vendas;
            double [] faturado_vendas;
            num_clis = this.CatFiliais.getClisDifsCompramProdMeses(produto);
            reg_vendas = this.CatFat.getVendasRegMeses(produto);
            faturado_vendas = this.CatFat.getVendasFaturadoMeses(produto);
            for (i = 0; i < 12; i++) {
                sb = new StringBuilder();
                sb.append(reg_vendas[i]).append(" ")
                        .append(num_clis[i])
                        .append(" ")
                        .append(faturado_vendas[i]);
                l.add(sb.toString());
            }
            return l;
        }
    }

    /**
     * Método que dado um código de cliente retorna uma lista(Top) dos produtos que comprou em
     * relação a quantidade comprada. A lista é composta por pares de código produto e quantidade
     * comprada;estes pares são ordenados por mais quantidade comprada, que caso seja igual
     * ordena pelo código de produto.
     * @param cliente Código de cliente.
     * @return Lista de pares exemplo: [("AF1184",5321), ("ZA3421",3213), ...]
     */
    public List<Map.Entry<String,Integer>> getQuerie5(String cliente) throws ClienteException{
        if(!this.CatClis.getListClientes().contains(cliente)) throw new ClienteException("Esse cliente não existe!");
        else {
            Map<String, Integer> m = this.CatFiliais.getProdutoQuantidadeDeUmCliente(cliente);
            List<Map.Entry<String, Integer>> l = new ArrayList<>(m.entrySet());
            Comparator<Map.Entry<String, Integer>> c = (o1, o2) -> {
                if (o1.getValue() < o2.getValue()) {
                    return 1;
                } else {
                    if (o1.getValue() > o2.getValue()) {
                        return -1;
                    } else {
                        return (o1.getKey().compareTo(o2.getKey()));
                    }
                }
            };
            l.sort(c);
            return l;
        }
    }

    /**
     * Método que dado um x, retorna um Top(x) de produtos por mais unidades vendidas, e tendo também o
     * número de clientes diferentes que compraram esse produto.
     * @param x Tamanho do Top de produtos pretendido.
     * @return Lista exemplo: ["AF1184 732", "KR8394 662", ...] sendo código produto e número de clientes diferentes
     * separado por um espaço (" ").
     */
    public List<String> getQuerie6(int x) {
        List<String> lreturn = new ArrayList<>();
        Map m = this.CatFat.getListaProdutosEQuantidadeVendida();
        List<Map.Entry<String,Integer>> l = new ArrayList<>(m.entrySet());
        Comparator<Map.Entry<String,Integer>> c = (o1, o2) -> {
            if (o1.getValue()-o2.getValue() == 0) return o1.getKey().compareTo(o2.getKey());
            else return o2.getValue()-o1.getValue();
        };
        l.sort(c);
        l = l.subList(0,x+1);
        List<Integer> num_clis = new ArrayList<>();
        for(Map.Entry<String,Integer> se : l){
            num_clis.add(this.CatFiliais.getNumClisDiferentesCompraProd(se.getKey()));
        }
        int i;
        StringBuilder sb;
        for (i=0;i<x;i++) {
            sb = new StringBuilder();
            sb.append(l.get(i).getKey()).append(" ")
                    .append(num_clis.get(i));
            lreturn.add(sb.toString());
        }
        return lreturn;
    }

    /**
     * Método que retorna a lista com o Top3 dos maiores compradores(clientes) por filial, sendo retornada uma lista
     * de Strings, conrespondendo cada String ao Top3 de cada filial.
     * @return Lista de Strings exemplo: ["K3992 A8832 J2366", "H8329 ...", ...]
     * Sendo neste exemplo A8832 o segundo maior comprador da filial 1
     * e H8329 o maior comprador da filial 2.
     */
    public List<String> getQuerie7(){
        List<String> l = new ArrayList<>();
        l.add(this.CatFiliais.getTop3CompradoresFilial(1));
        l.add(this.CatFiliais.getTop3CompradoresFilial(2));
        l.add(this.CatFiliais.getTop3CompradoresFilial(3));
        return l;
    }

    /**
     * Método que dado um tamanho(x), retorna o Top(x) de clientes que compram o maior número de produtos
     * diferentes, apresentados numa lista de pares (Código cliente, Quantidade de compras).
     * Apresenta ainda uma ordem decrescente sobre a quantidade de compras, que se for igual,
     * ordena por sua vez alfabéticamente por código cliente.
     * @param x Tamanho do Top pretendido.
     * @return Lista de pares exemplo: [("A1184", 32123), ("K2311", 2339), ...]
     */
    public List<Map.Entry<String,Integer>> getQuerie8(int x){
        List<Map.Entry<String,Integer>> l = this.CatFiliais.getClienteNumProdsCompDiferentes();
        Comparator<Map.Entry<String,Integer>> c = (o1, o2) -> {
            if (o1.getValue() - o2.getValue() != 0) return o2.getValue() - o1.getValue();
            else return o1.getKey().compareTo(o2.getKey());
        };
        l.sort(c);
        if(l.size()>x) {
            l = l.subList(0, x);
        }
        return l;
    }


    /**
     * Metodo que dado prod e tam retorna uma lista de pares, que reflete a o quanto um cliente gastou nesse produto.
     * Esse tem a informação (Codigo cliente, Faturado produto),
     * ordenado por maior faturação e secundáriamente caso tenha a mesma faturação por código cliente.
     * @param produto Código do produto.
     * @param tamanho Número de produtos que pertende na lista de retorno.
     * @return Lista de pares exemplo: [("A1184", 1233321.32123), ("K2311", 23398.34027), ...]
     */
    public List<Map.Entry<String,Double>> getQuerie9(String produto, int tamanho) throws ProdutoException{
        if(!this.CatProds.getListProds().contains(produto)) throw new ProdutoException("Esse produto não existe!");
        else {
            List<Map.Entry<String, Double>> l = this.CatFiliais.getClientesFaturacaoProd(produto);
            Comparator<Map.Entry<String, Double>> c = (o1, o2) -> {
                double k = o1.getValue() - o2.getValue();
                if (k != 0) {
                    if (k > 0) {
                        return -1;
                    } else {
                        return 1;
                    }
                } else {
                    return o1.getKey().compareTo(o2.getKey());
                }
            };
            l.sort(c);
            if (l.size() > tamanho) {
                l = l.subList(0, tamanho);
            }
            return l;
        }
    }


    /**
     * Método que retorna uma lista de Strings com a informação mês a mês, e para cada mês filial a filial,
     * a facturação total a cada produto.
     * @return Lista de Strings as quais têm o seguinte
     * formato "<cod_prod>:<filial 1 mes 1 faturado>;<filial 2 mes 1 faturado>;<filial 3 mes 1 filial> faturado>#<filial 1 mes 2 faturado>;<filial 2 mes 2 faturado>;<filial 3 mes 2 faturado>#..."
     * sendo ":" -> divisão cod_prod e dados; "#" divisão entre meses; ";" divisão entre dados por filial.
     */
    public List<String> getQuerie10(){
        List<String> listOfProds = this.CatProds.getListProds();
        List<String> l = new ArrayList<>();
        Map<Integer,Map<Integer,Double>> filiais = new HashMap<>();
        StringBuilder sb;
        int i,j;
        double k;
        for (String s : listOfProds){
            for (i=1; i<4; i++){
                filiais.put(i,this.CatFat.getFatsProdMesFiliais(s,i));
            }

            sb = new StringBuilder();
            sb.append(s).append(":");
            for (j=1; j<13; j++){
                for(i=1; i<4; i++){
                    if(filiais.get(i) != null && filiais.get(i).containsKey(j)) k = filiais.get(i).get(j);
                    else k = 0;
                    sb.append(k);
                    if(i<3)sb.append(";");
                }
                if(j<12 )sb.append("#");
            }
            sb.append("\n");
            l.add(sb.toString());
        }
        return l;
    }

    public List<Integer> getComprasMes(){
        Map<Integer,Integer> m = this.CatFat.comprasMes();
        List<Integer> l = new ArrayList<>();
        int i;
        for(i=1; i<13; i++){
            l.add(m.getOrDefault(i, 0));
        }
        return l;
    }


    public List<Double> getFaturacaoFiliais(){
        List<Double> l = new ArrayList<>();
        double d1 = this.CatFat.fatFilial(1).values().stream()
                               .mapToDouble(Double::doubleValue)
                               .sum();
        double d2= this.CatFat.fatFilial(2).values().stream()
                              .mapToDouble(Double::doubleValue)
                              .sum();
        double d3 = this.CatFat.fatFilial(3).values().stream()
                               .mapToDouble(Double::doubleValue)
                               .sum();
        l.add(0,d1+d2+d3);
        l.add(1,d1);
        l.add(2,d2);
        l.add(3,d3);
        return l;
    }

    public List<String> getDistintosCli(){
        List<String> l = new ArrayList<>();
        Map<Integer,Integer> map;
        StringBuilder sb;
        int k;
        for(int j=1; j<4; j++){
            map = this.CatFiliais.getMesClientesDiferentes(j);
            sb = new StringBuilder();
            for (int i=1;i<12;i++) {
                k = map.getOrDefault(i, 0);
                sb.append(k).append(" ");
            }
            k = map.getOrDefault(j, 0);
            sb.append(k);
            l.add(sb.toString());
        }
        return l;
    }

    /**
     * Método que devolve as informações relativas a um produto
     * @return lista cuja primeira posição corresponde ao número total de produtos, a segunda ao total de diferentes produtos comprados e a terceira ao total de não comprados
     */
    public List<Integer> getInfoProdutos(){
        List<Integer> l = new ArrayList<>();
        l.add(this.CatProds.getTamanho());
        l.add(this.CatFat.getDifs());
        l.add(this.CatFat.getNComprados(this.CatProds.getListProds()));
        return l;
    }

    /**
     * Método que devolve as informações relativas a um cliente
     * @return lista cuja primeira posição corresponde ao número total de clientes, a segunda ao total de clientes que realizaram compras e a terceira ao total de clientes que nada compram
     */
    public List<Integer> getInfoClis(){
        List<Integer> l = new ArrayList<>();
        l.add(this.CatClis.getTamanho());
        l.add(this.CatFiliais.getNumeroClientesQueCompram());
        l.add(this.CatFiliais.getNumeroClientesQueNaoCompram(this.CatClis.getListClientes()));
        return l;
    }

    /**
     * Método que devolve as informações relativas à faturação
     * @return lista cuja primeira posição corresponde ao número de compras de valor total igual a 0 e a segunda à faturação total
     */
    public List<Double> getInfoFat(){
        List<Double> l = new ArrayList<>();
        l.add((double)this.vendas_gratis);
        l.add(this.CatFat.getFaturacaoGlobal());
        return l;
    }

    /**
     * Método que devolve as informações relativas a uma venda.
     * @return lista cuja primeira posição corresponde ao nome do ficheiro e a segunda ao número de vendas erradas
     */
    public List<String> getInfoVendas(){
        List<String> l = new ArrayList<>();
        int errados = this.numero_de_vendas_lidas - this.CatFiliais.getNumProdCompras();
        l.add(nomeFich);
        l.add(String.valueOf(errados));
        return l;
    }

    /**
     * Método que lê os ficheiros dando parse a informação útil para os modúlos.
     * @param clientestxt Ficheiro de Clientes.
     * @param produtostxt Ficheiro de Produtos.
     * @param vendastxt Ficheiro de Vendas.
     */
    public void load(String clientestxt, String produtostxt, String vendastxt){
        this.nomeFich = vendastxt;
        this.CatClis.loadClientes(clientestxt);
        this.CatProds.loadProdutos(produtostxt);
        this.loadFatFil(vendastxt);
    }


}
