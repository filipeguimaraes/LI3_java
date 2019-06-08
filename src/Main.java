public class Main {

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
        GereVendasController control = new GereVendasController();
        control.setModel(model);
        control.setView(view);
        control.run();
        System.exit(0);
    }



}
