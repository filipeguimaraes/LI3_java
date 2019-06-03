import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
/*    public static List<String> readFilesWithNIO(String filePath){
        Path p = Paths.get(filePath);
        List<String> l = null;
        try{
            l= Files.readAllLines(p, StandardCharsets.UTF_8);
        } catch (IOException e){
            e.printStackTrace();
        }
        return l;
    }*/

/*
    public static List<String> readLinesWithBuff(String fich){
        List<String> linhas = new ArrayList<>();
        String linha = null;

        try(
            BufferedReader inStream = new BufferedReader(new FileReader(fich))){
                while((linha= inStream.readLine())!=null){
                ////// if linha.metodo verifica-se
                linhas.add(linha);
                }
            }
        catch(IOException e){
        System.out.println(e);
        }
        return linhas;
    }

 */

    public static void main(String[] args){

    }



}
