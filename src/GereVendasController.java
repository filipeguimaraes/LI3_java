import java.util.List;
import java.util.Scanner;

/**
 * Write a description of class GereVendasController here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GereVendasController {
    private GereVendasModel model;
    private InterfGereVendasView view;

    public void setModel(GereVendasModel model) {
        this.model = model;
    }

    public void setView(InterfGereVendasView view) {
        this.view = view;
    }

    /**
     * Metodo que trata do menu inicial
     */
    public void run(){
         String[] opcoes={"Carregar ficheiros",
                    "Consultas estatísticas",
                    "Queries interativas",
                    "Sair"};
         int escolha=0;
         do {
             switch (escolha) {
                 case 0:
                     view.menuOpcoes(opcoes);
                     escolha=Input.lerInt();
                     break;
                 case 1:
                     view.carregaPreDefinidos();
                     int def = Input.lerInt();
                     String pathProdutos;
                     String pathClientes;
                     String pathVendas;
                     if (def==0) {
                         view.carregaProdutos();
                         pathProdutos = Input.lerString();
                         view.carregaClientes();
                         pathClientes = Input.lerString();
                         view.carregaVendas();
                         pathVendas = Input.lerString();
                     }else {
                         pathProdutos = "Produtos.txt";
                         pathClientes = "Clientes.txt";
                         pathVendas = "Vendas_1M.txt";
                     }
                     Crono.start();
                     model.load(pathClientes, pathProdutos, pathVendas);
                     Crono.stop();
                     Crono.print();
                     escolha=0;
                     break;
                 case 2:
                     escolha = runEstatisticas();
                     break;
                 case 3:
                     escolha = runQueries();
                     break;
                 default:
                     System.out.println("Ocorreu um erro.");
                     enterContinuar();
                     escolha=0;
                     break;
             }
         } while (escolha!=4);
         view.fim();
    }


    /**
    * Método que trata do menu de extras
    * @return Retorna -1 para voltar ao menu anterior
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
                     view.query1(model.getListaDeProdutosNaoComprados());
                     enterContinuar();
                     break;
                 case 2:
                     view.recebeMes();
                     int mes = Input.lerInt();
                     view.query2(model.getQuerie2(mes,0),model.getQuerie2(mes,1),
                                 model.getQuerie2(mes,2),model.getQuerie2(mes,3));
                     enterContinuar();
                     break;
                 case 3:
                     view.recebeCliente();
                     String cliente3 = Input.lerString();
                     view.query3(model.getQuerie3(cliente3));
                     enterContinuar();
                     break;
                 case 4:
                     view.recebeProduto();
                     String prod = Input.lerString();
                     view.query4(model.getQuerie4(prod));
                     enterContinuar();
                     break;
                 case 5:
                     view.recebeCliente();
                     String cliente5 = Input.lerString();
                     view.query5(model.getQuerie5(cliente5));
                     enterContinuar();
                     break;
                 case 6:
                     view.recebeIntProd();
                     int numprod = Input.lerInt();
                     view.query6(model.getQuerie6(numprod));
                     enterContinuar();
                     break;
                 case 7:
                     view.query7(model.getQuerie7());
                     enterContinuar();
                     break;
                 case 8:
                     view.recebeIntCli();
                     int numcli8 = Input.lerInt();
                     view.query8(model.getQuerie8(numcli8));
                     enterContinuar();
                     break;
                 case 9:
                     view.recebeProduto();
                     String codprod = Input.lerString();
                     view.recebeIntCli();
                     int numcli9 = Input.lerInt();
                     view.query9(model.getQuerie9(codprod,numcli9));
                     enterContinuar();
                     break;
                 case 10:
                     int nav=-1;
                     List<String> lista= model.getQuerie10();
                     int i = 0;
                     while (nav!=0) {
                         view.query10(lista.get(i));
                         nav = Input.lerInt();
                         if (nav == 1 && i>0) {
                             i--;
                         } else if (nav == 2 && i < lista.size())
                             i++;
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


    private void enterContinuar() {
        Scanner s=new Scanner(System.in);
        System.out.print("Pressione enter para continuar . . . ");
        s.nextLine();
    }
}
