import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.reflect.Array.get;

public class GereVendasModel{
    private CatProdutos CatProds;
    private CatClientes CatClis;
    private CatFaturacao CatFat;
    private CatFiliais CatFiliais;

    public GereVendasModel() {
        CatProds = new CatProdutos();
        CatClis = new CatClientes();
        CatFat = new CatFaturacao();
        CatFiliais = new CatFiliais();
    }

    public static boolean validaVenda(String s){
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

    public static List<String> readFilesWithNIO(String filePath) {
        Path p = Paths.get(filePath);
        List<String> l = null;
        try {
            l = Files.readAllLines(p, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return l;
    }

    public void readLinesWithBuff(String fich) {
        String[] divd = new String[7];

        for (String s : readFilesWithNIO(fich)) {
            divd = s.split(" ");
            if (divd.length == 7
                    && this.CatClis.existeCliente(divd[4])
                    && this.CatProds.existeProduto(divd[0])) {
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

        this.CatFat.adicionaFatNComp(this.CatProds.getListProds());
    }

    // Query 1
    /**
     *
     * @return
     */
    public List<String> getListaDeProdutosNaoComprados(){
        return this.CatFat.getListaNaoComprados();
    }

    // Query 2
    /**
     *
     * @param mes
     * @param filial
     * @return
     */
    public int [] getQuerie2(int mes, int filial){
        int [] r = new int[2];
        r[0] = this.CatFat.getNumVendasRegMesFilial(filial,mes);
        r[1] = this.CatFiliais.getNumClientesCompramMesFilial(filial,mes);
        return r;
    }

    // Query 3

    /**
     *
     * @param cliente
     * @return
     */
    public List<String> getQuerie3(String cliente){
        return this.CatFiliais.getQuantosProdsDifsEGastos(cliente);
    }

    // Query 4
    /**
     *
     * @param produto
     * @return
     */
    public List<String> getQuerie4(String produto){
        List<String> l = new ArrayList<>();
        StringBuilder sb;
        int i;
        int [] num_clis;
        int [] reg_vendas;
        double [] faturado_vendas;
        num_clis = this.CatFiliais.getClisDifsCompramProdMeses(produto);
        reg_vendas = this.CatFat.getVendasRegMeses(produto);
        faturado_vendas = this.CatFat.getVendasFaturadoMeses(produto);
        for (i=0;i<12;i++) {
            sb = new StringBuilder();
            sb.append(reg_vendas[i]).append(" ")
                    .append(num_clis[i])
                    .append(" ")
                    .append(faturado_vendas[i]);
            l.add(sb.toString());
        }
        return l;
    }

    // Query 5
    public List<AbstractMap.SimpleEntry<String,Integer>> getQuerie5(String cliente){
        Map m = this.CatFiliais.getProdutoQuantidadeDeUmCliente(cliente);
        List<AbstractMap.SimpleEntry<String,Integer>> l = new ArrayList<>(m.entrySet());
        Comparator<Map.Entry<String,Integer>> c = new Comparator<Map.Entry<String,Integer>>() {
            public int compare(Map.Entry<String,Integer> o1, Map.Entry<String,Integer> o2) {
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
            }
        };
        l.sort(c);
        return l;
    }

    // Query 6
    public List<String> getQuerie6(int x) {
        List<String> lreturn = new ArrayList<>();
        Map m = this.CatFat.getListaProdutosEQuantidadeVendida();
        List<Map.Entry<String,Integer>> l = new ArrayList<>(m.entrySet());
        Comparator<Map.Entry<String,Integer>> c = new Comparator<Map.Entry<String,Integer>>() {
            public int compare(Map.Entry<String,Integer> o1, Map.Entry<String,Integer> o2) {
                if (o1.getValue()-o2.getValue() == 0) return o1.getKey().compareTo(o2.getKey());
                else return o2.getValue()-o1.getValue();
            }
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

    // Query 7
    public List<String> getQuerie7(){
        List<String> l = new ArrayList<>();
        l.add(this.CatFiliais.getTop3CompradoresFilial(1));
        l.add(this.CatFiliais.getTop3CompradoresFilial(2));
        l.add(this.CatFiliais.getTop3CompradoresFilial(3));
        return l;
    }

    public static void main(String[] args){
        Crono tmp = new Crono();
        tmp.start();
        GereVendasModel c = new GereVendasModel();
        c.CatClis.readClientes("Clientes.txt");
        c.CatProds.readProdutos("Produtos.txt");
        c.readLinesWithBuff("Vendas_1M.txt");
//        c9.loadFat();
//        c.loadCatFiliais();
        tmp.stop();
        System.out.println(tmp.print());

        tmp.start();
        System.out.println(c.getQuerie7());

        tmp.stop();
        System.out.println(tmp.print());
    }


}
