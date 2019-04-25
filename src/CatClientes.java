import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CatClientes{

    public static List<Cliente> readLinesWithBuff(String fich){
        List<Cliente> linhas = new ArrayList<>();
        String linha = null;

        try(
                BufferedReader inStream = new BufferedReader(new FileReader(fich))){
            while((linha= inStream.readLine())!=null){
                Cliente c = new Cliente(linha);
                if (c.validaClientes()) linhas.add(c);
            }
        }
        catch(IOException e){
            System.out.println(e);
        }
        return linhas;
    }

    public boolean existeCliente(String s){
        boolean r=false;
        Iterator<Cliente> it = this.readLinesWithBuff("Clientes.txt").iterator();
        Cliente cli;
        while(!r && it.hasNext()){
           cli=it.next();
           r=cli.getCodCli().equals(s);
        }
        return r;
    }

}
