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

        for( String s  : readFilesWithNIO(fich) ) {
            divd = s.split(" ");
            if (divd.length == 7
            && this.CatClis.existeCliente(divd[4])
            && this.CatProds.existeProduto(divd[0])){
                int mes = parseInt(divd[5]);
                int filial = parseInt(divd[6]);
                double preco = parseDouble(divd[1]);
                int quant = parseInt(divd[2]);

                this.CatFat.addCatFaturacao(divd[0], mes, quant,
                        (divd[3].equals("N")?(double) quant * preco : 0),
                        (divd[3].equals("P")?(double) quant * preco: 0),
                        filial);
                this.CatFiliais.addClienteFilial(filial,
                        divd[0],divd[4],preco,
                        quant,divd[3],mes);
            }
        }

        this.CatFat.adicionaFatNComp(this.CatProds.getListProds());
    }


    public List<Integer> query_2(int mes, int filial){
        List<Integer> l = new ArrayList<>();
        int num_clis = 0;
        int num_vendas = 0;
        return l;
    }

    public static void main(String[] args){
        Crono tmp = new Crono();
        tmp.start();
        GereVendasModel c = new GereVendasModel();
        c.CatClis.readClientes("Clientes.txt");
        c.CatProds.readProdutos("Produtos.txt");
        c.readLinesWithBuff("Vendas_1M.txt");
//        c.loadFat();
//        c.loadCatFiliais();
        tmp.stop();
        System.out.println(tmp.print());
    }


}
