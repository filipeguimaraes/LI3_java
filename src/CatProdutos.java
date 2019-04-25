import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CatProdutos{

    public static List<Produto> readLinesWithBuff(String fich){
        List<Produto> linhas = new ArrayList<>();
        String linha = null;

        try(
                BufferedReader inStream = new BufferedReader(new FileReader(fich))){
            while((linha= inStream.readLine())!=null){
                Produto c = new Produto(linha);
                if (c.validaProdutos()) linhas.add(c);
            }
        }
        catch(IOException e){
            System.out.println(e);
        }
        return linhas;
    }

    public boolean existeProduto(String s){
        boolean r=false;
        Iterator<Produto> it = this.readLinesWithBuff("Produtos.txt").iterator();
        Produto prod;
        while(!r && it.hasNext()){
            prod=it.next();
            r=prod.getCodProd().equals(s);
        }
        return r;
    }

}
