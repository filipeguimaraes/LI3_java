import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static java.lang.reflect.Array.get;

public class SGV {
    private CatProdutos CatProds;
    private CatClientes CatClis;
    private CatVendas CatVendas;
    private CatFaturacao CatFat;

    public SGV() {
        CatProds = new CatProdutos();
        CatClis = new CatClientes();
        CatVendas = new CatVendas();
        CatFat = new CatFaturacao();
    }

    public int readLinesWithBuff(String fich) {
        this.CatVendas = new CatVendas();
        String linha = "";
        String[] divd = new String[7];
        int i =0;

        try (BufferedReader inStream = new BufferedReader(new FileReader(fich))) {
            while ((linha = inStream.readLine()) != null) {

                divd = linha.split(" ");
                Venda v = new Venda(divd);
                if (divd.length == 7
                        && this.CatClis.existeCliente(v.getCodCli())
                        && this.CatProds.existeProduto(v.getCodProd())
                        && v.validaVenda()) {
                    this.CatVendas.addVenda(linha);
              
                    i++;
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return i;
    }


    public void loadFat (){
        List<String> lv = this.CatVendas.getVendas();
        List<Venda> lvs = lv.stream().map(Venda::new).collect(Collectors.toList());
        this.CatFat = new CatFaturacao();
        for(Venda v  : lvs){
            this.CatFat.addDadosMes(v.getCodProd(), v.getMes(), v.getQuantidade(),
                    (v.getTipo().equals("N")?(double) v.getQuantidade() * v.getPreco() : 0),
                    (v.getTipo().equals("P")?(double) v.getQuantidade() * v.getPreco() : 0),
                    v.getFilial());
        }
        this.CatFat.adicionaFatNComp(lv);
    }


    public static void main(String[] args){
        SGV c = new SGV();
        c.CatClis.readClientes("Clientes.txt");
        c.CatProds.readProdutos("Produtos.txt");

        System.out.println("v "+ c.readLinesWithBuff("Vendas_1M.txt"));
        c.loadFat();
        System.out.println(c.CatFat.getSetVendidos().size());

    }


}
