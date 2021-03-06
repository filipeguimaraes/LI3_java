import Exceptions.MesException;
import Exceptions.ProdutoException;
import Exceptions.ClienteException;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Controlador, controla o funcionamento do sistema.
 *
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçalo Ferreira A84073
 */
public class GereVendasController implements IGereVendasController{
    private IGereVendasModel model;
    private InterfGereVendasView view;

    private static final String CLIENTES = "Clientes.txt";
    private static final String PRODUTOS = "Produtos.txt";
    private static final String VENDAS_1M = "Vendas_1M.txt";
    private static final String VENDAS_3M = "Vendas_3M.txt";
    private static final String VENDAS_5M = "Vendas_5M.txt";
    private static String OBJ_PATH = "gestVendas.dat";

    public void setModel(IGereVendasModel model) {
        this.model = model;
    }

    public void setView(InterfGereVendasView view) {
        this.view = view;
    }

    /**
     * Metodo que trata do menu inicial.
     */
    public void run(){
         String[] opcoes={"Carregar ficheiros",
                    "Consultas estatísticas",
                    "Queries interativas",
                    "Guardar estado",
                    "Carregar estado",
                    "Sair"};
         int escolha=0;
         do {
             switch (escolha) {
                 case 0:
                     view.menuOpcoes(opcoes);
                     escolha=Input.lerInt();
                     break;
                 case 1:
                     view.carregaFicheiros();
                     int def = Input.lerInt();
                     String vendas;
                     if(def==2){
                         vendas =VENDAS_3M;
                     } else if (def == 3) {
                         vendas = VENDAS_5M;
                     } else {
                         vendas = VENDAS_1M;
                     }
                     Crono.start();
                     model.load(CLIENTES, PRODUTOS, vendas);
                     view.tempo(Crono.stop());
                     enterContinuar();
                     escolha=0;
                     break;
                 case 2:
                     escolha = runEstatisticas();
                     break;
                 case 3:
                     escolha = runQueries();
                     break;
                 case 4:
                     String path4;
                     view.escreveObjeto();
                     int aux4 = Input.lerInt();
                     if( aux4 == 1 ){
                         path4 = OBJ_PATH;
                     } else if( aux4 == 2 ){
                         view.nomeFicheiro();
                         path4 = Input.lerString();
                     } else{
                         escolha=0;
                         break;
                     }
                     try {
                         Crono.start();
                         Carregamento.escreverFicheiroOjeto(this.model,path4);
                         view.tempo(Crono.stop());
                         System.out.println("Guardado com sucesso");
                         enterContinuar();
                     } catch (IOException e){
                         System.out.println("Erro ao escrever ficheiro: "+e.getMessage());
                         enterContinuar();
                         continue;
                     }
                     escolha=0;
                     break;
                 case 5:
                     String path;
                     view.carregarObjeto();
                     int aux = Input.lerInt();
                     if( aux == 1 ){
                         path = OBJ_PATH;
                     } else if( aux == 2 ){
                         view.nomeFicheiro();
                         path = Input.lerString();
                     } else{
                         escolha=0;
                         break;
                     }
                     try {
                         Crono.start();
                         this.model = Carregamento.lerFicheiroObjeto(path);
                         view.tempo(Crono.stop());
                         enterContinuar();
                         escolha=0;
                     } catch (IOException e){
                         System.out.println("Erro ao carregar ficheiro");
                         enterContinuar();
                         escolha=0;
                         continue;
                     } catch (ClassNotFoundException e){
                         System.out.println("Erro: "+e.getMessage());
                         enterContinuar();
                         escolha = 0;
                         continue;
                     }
                     break;
                 default:
                     System.out.println("Ocorreu um erro.");
                     enterContinuar();
                     escolha=0;
                     break;
             }
         } while (escolha!=6);
         view.fim();
    }

    /**
    * Método que trata do menu das queries.
    */
    private int runQueries(){
         String[] opcoes = {"Lista ordenada alfabeticamente com os códigos dos produtos nunca comprados e o seu respectivo total",
                    "Número total global/por filial de vendas realizadas e número total de clientes que as fizeram num determinado mês ",
                    "Determinar quantas compras um determinado cliente fez, quantos produtos distintos comprou e quanto gastou no total para cada mês",
                    "Determinar quantas vezes um determinado produto foi comprado, por quantos clientes diferentes e o total faturado de mês a mês",
                    "Determinar a lista de produtos que um determinado cliente mais comprou por ordem descrescente de quantidade (e quantos)/por ordem alfabética de códigos para quantidades iguais",
                    "Determinar o conjunto de X produtos mais vendidos em todo o ano indicando o número total de clientes distintos que o compraram",
                    "Determinar a lista dos 3 maiores compradores em termos de dinheiro faturado, para cada filial",
                    "Determinar os códigos de X clientes que compraram mais produtos diferentes (e quantos) por ordem decrescente de número de produtos",
                    "Determinar o conjunto de X clientes que mais compraram e, para cada um, o valor gasto ordenado por ordem decrescente de quantidade/por ordem alfabética dos códigos para quantidades iguais",
                    "Determinar a faturação total com cada produto, mês a mês e para cada mês filial a filial",
                    "Menu principal"};
         int escolha;
         do{
             view.menuOpcoes(opcoes);
             escolha = Input.lerInt();
             switch (escolha){
                 case 1:
                     Crono.start();
                     view.query1(model.getListaDeProdutosNaoComprados());
                     view.tempoSimples(Crono.stop());
                     enterContinuar();
                     break;
                 case 2:
                     view.recebeMes();
                     int mes = Input.lerInt();
                     try {
                         Crono.start();
                         view.query2(model.getQuerie2(mes, 0), model.getQuerie2(mes, 1),
                                 model.getQuerie2(mes, 2), model.getQuerie2(mes, 3));
                         view.tempoSimples(Crono.stop());
                         enterContinuar();
                     }catch(MesException e){
                         System.out.println(e.getMessage());
                         enterContinuar();
                         continue;
                     }
                     break;
                 case 3:
                     view.recebeCliente();
                     String cliente3 = Input.lerString();
                     try {
                         Crono.start();
                         view.query3(model.getQuerie3(cliente3));
                         view.tempoSimples(Crono.stop());
                         enterContinuar();
                     }catch (ClienteException e){
                         System.out.println(e.getMessage());
                         enterContinuar();
                         continue;
                     }
                     break;
                 case 4:view.tempoSimples(Crono.stop());
                     view.recebeProduto();
                     String prod = Input.lerString();
                     try {
                         Crono.start();
                         view.query4(model.getQuerie4(prod));
                         view.tempoSimples(Crono.stop());
                         enterContinuar();
                     }catch(ProdutoException e){
                         System.out.println(e.getMessage());
                         enterContinuar();
                         continue;
                     }
                     break;
                 case 5:
                     view.recebeCliente();
                     String cliente5 = Input.lerString();
                     try {
                         Crono.start();
                         view.query5(model.getQuerie5(cliente5));
                         view.tempoSimples(Crono.stop());
                         enterContinuar();
                     }catch (ClienteException e){
                         System.out.println(e.getMessage());
                         enterContinuar();
                         continue;
                     }
                     break;
                 case 6:
                     view.recebeIntProd();
                     int numprod = Input.lerInt();
                     Crono.start();
                     view.query6(model.getQuerie6(numprod));
                     view.tempoSimples(Crono.stop());
                     enterContinuar();
                     break;
                 case 7:
                     Crono.start();
                     view.query7(model.getQuerie7());
                     view.tempoSimples(Crono.stop());
                     enterContinuar();
                     break;
                 case 8:
                     view.recebeIntCli();
                     int numcli8 = Input.lerInt();
                     Crono.start();
                     view.query8(model.getQuerie8(numcli8));
                     view.tempoSimples(Crono.stop());
                     enterContinuar();
                     break;
                 case 9:
                     view.recebeProduto();
                     String codprod = Input.lerString();
                     view.recebeIntCli();
                     int numcli9 = Input.lerInt();
                     try {
                         Crono.start();
                         view.query9(model.getQuerie9(codprod, numcli9));
                         view.tempoSimples(Crono.stop());
                         enterContinuar();
                     }catch(ProdutoException e){
                         System.out.println(e.getMessage());
                         enterContinuar();
                         continue;
                     }
                     break;
                 case 10:
                     int nav=5;
                     Crono.start();
                     List<String> lista= model.getQuerie10();
                     view.tempoSimples(Crono.stop());
                     int i = 0;
                     while (nav!=0) {
                         view.query10(lista.get(i));
                         nav = Input.lerInt();
                         if (nav == 1 && i>0) {
                             i--;
                         } else if (nav == 2 && i < lista.size()){
                             i++;
                         } else if (nav == 3){
                             view.recebeProduto();
                             String produto = Input.lerString();
                             String a = lista.stream()
                                         .filter(l -> l.contains(produto))
                                         .findFirst()
                                         .orElse("N/A");
                             if(a.contains("N/A")){
                                 System.out.println("Produto não existe pressione 2");
                             } else view.query10(a);
                             nav = Input.lerInt();
                         }
                     }

                     break;
                 case 11:
                     break;
                 default:
                     System.out.println("Ocorreu um erro.");
                     enterContinuar();
                     break;
             }
         }while(escolha!=11);
         return 0;
    }

    /**
     * Método que trata do menu das consultas estatísticas.
     */
    private int runEstatisticas(){
        String[] opcoes = {"Informações sobre o último ficheiro de vendas lido",
                "Número total de compras por mês",
                "Faturação total por mês para cada filial e o valor total global",
                "Número de clientes distintos que compraram em cada mês filial a filial",
                "Menu principal"
        };
        int escolha;
        do{
            view.menuOpcoes(opcoes);
            escolha = Input.lerInt();
            switch (escolha){
                case 1:
                    view.info(model.getInfoProdutos(),model.getInfoClis(),model.getInfoFat(),model.getInfoVendas());
                    enterContinuar();
                    break;
                case 2:
                    view.totalComprasMes(model.getComprasMes());
                    enterContinuar();
                    break;
                case 3:
                    view.fatTotal(model.getFaturacaoFiliais());
                    enterContinuar();
                    break;
                case 4:
                    view.distintosCli(model.getDistintosCli());
                    enterContinuar();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Ocorreu um erro.");
                    enterContinuar();
                    break;
            }
        }while(escolha!=5);
        return 0;
    }

    /**
     * Método que imprime "enter para continuar...".
     */
    private void enterContinuar() {
        Scanner s=new Scanner(System.in);
        System.out.print("Pressione enter para continuar . . . ");
        s.nextLine();
    }
}
