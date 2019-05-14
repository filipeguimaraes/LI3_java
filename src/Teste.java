public class Teste {
    public static void main(String[] args){


        CatClientes c = new CatClientes();
        c.readClientes("Clientes.txt");

        System.out.println(c);

    }
}
