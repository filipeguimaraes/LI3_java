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

import static java.lang.reflect.Array.get;

public class GereVendasModel {
    private CatProdutos CatProds;
    private CatClientes CatClis;
    private CatVendas CatVendas;
    private CatFaturacao CatFat;
    private CatFiliais filial;

    public GereVendasModel() {
        CatProds = new CatProdutos();
        CatClis = new CatClientes();
        CatVendas = new CatVendas();
        CatFat = new CatFaturacao();
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
        this.CatVendas = new CatVendas();
        String[] divd = new String[7];

        for( String s  : readFilesWithNIO(fich) ) {
            divd = s.split(" ");
            Venda v = new Venda(divd);
            if (divd.length == 7
                    && this.CatClis.existeCliente(v.getCodCli())
                    && this.CatProds.existeProduto(v.getCodProd())
                    && v.validaVenda()) {
                this.CatVendas.addVenda(s);
            }
        }
    }


    public void loadFat (){
        List<String> lv = this.CatVendas.getVendas();
        List<Venda> lvs = lv.stream().map(Venda::new).collect(Collectors.toList());
        this.CatFat = new CatFaturacao();
        for(Venda v  : lvs){
            this.CatFat.addCatFaturacao(v.getCodProd(), v.getMes(), v.getQuantidade(),
                    (v.getTipo().equals("N")?(double) v.getQuantidade() * v.getPreco() : 0),
                    (v.getTipo().equals("P")?(double) v.getQuantidade() * v.getPreco() : 0),
                    v.getFilial());
        }
        this.CatFat.adicionaFatNComp(this.CatProds.getListProds());
    }


    public void loadCatFiliais (){
        List<String> lv = this.CatVendas.getVendas();
        List<Venda> lvs = lv.stream().map(Venda::new).collect(Collectors.toList());
        this.filial = new CatFiliais();
        for(Venda v  : lvs){
            this.filial.addClienteFilial(v.getFilial(),
                    v.getCodProd(),v.getCodCli(),v);
        }
    }

    public List<Integer> query_2(int mes, int filial){
        List<Integer> l = new ArrayList<>();
        int num_clis = 0;
        int num_vendas = 0;
        return l;
    }

    public static void main(String[] args){
        GereVendasModel c = new GereVendasModel();
        c.CatClis.readClientes("Clientes.txt");
        c.CatProds.readProdutos("Produtos.txt");
        c.readLinesWithBuff("Vendas_1M.txt");
        c.loadFat();
        c.loadCatFiliais();
    }


}
