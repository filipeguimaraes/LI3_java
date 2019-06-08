/**
 * GestVendasAppMVC, classe que tem o método que cria a aplicação.
 *
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçalo Ferreira A84073
*/
public class GestVendasAppMVC {
    /**
     * Método que cria a aplicação
     */
    public static void main(String[] args){
        //GereVendasModel model = createData();
        IGereVendasModel model = new GereVendasModel();
        /*
        if(model == null) {
            System.out.println("ERRO INICIALIZACAO");  // throw exception ?
            System.exit(-1);
        }
         */
        InterfGereVendasView view = new GereVendasView();
        IGereVendasController control = new GereVendasController();
        control.setModel(model);
        control.setView(view);
        control.run();
        System.exit(0);
    }



}
