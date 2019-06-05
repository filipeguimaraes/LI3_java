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
        //GereVendasModel model = createData();
        GereVendasModel model = new GereVendasModel();
        /*
        if(model == null) {
            System.out.println("ERRO INICIALIZACAO");  // throw exception ?
            System.exit(-1);
        }
         */
        InterfGereVendasView view = new GereVendasView();
        GereVendasController control = new GereVendasController();
        control.setModel(model);
        control.setView(view);
        control.run();
        System.exit(0);
    }



}
