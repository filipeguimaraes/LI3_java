import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.reflect.Array.get;

public class CatVendas {
    private List<Venda> vendas;

    public CatVendas() {
        this.vendas = new ArrayList<Venda>();
    }

    public CatVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

    public void addVenda(Venda venda){
        this.vendas.add(venda);
    }

    public static List<String> readLinesWithBuff(String fich){
        List<String> linhas = new ArrayList<>();
        String linha;
        String [] divd;

        try(BufferedReader inStream = new BufferedReader(new FileReader(fich))){
            while((linha= inStream.readLine())!=null){
                divd = linha.split(" ");
                if(divd.length == 7
                        && Venda.validaPreco((String) get(divd,1))
                        && Venda.validaQuant((String) get(divd,2))
                        && Venda.validaTipo((String) get(divd, 3))
                        && Venda.validaMes((String) get(divd,5))
                        && Venda.validaFilial((String) get(divd,6)))
                    linhas.add(linha);
                // linhas.add(Venda(divd));
            }
        }
        catch(IOException e){
            System.out.println(e);
        }

        return linhas;
    }


}
