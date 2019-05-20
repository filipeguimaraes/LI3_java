import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.reflect.Array.get;

public class GereVendasModel {
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
    public List<String> getListaDeProdutosNaoComprados(){
        return this.CatFat.getListaNaoComprados();
    }

    // Query 2
    public int [] getQuerie2(int mes, int filial){
        int [] r = new int[2];
        r[0] = this.CatFat.getNumVendasRegMesFilial(filial,mes);
        r[1] = this.CatFiliais.getNumClientesCompramMesFilial(filial,mes);
        System.out.println(r[1]);
        return r;
    }

    public List<String> getQuerie3(String cliente){
        return this.CatFiliais.getQuantosProdsDifsEGastos(cliente);
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
        System.out.println(c.getQuerie3("Y1266"));

        tmp.stop();
        System.out.println(tmp.print());
    }


}
