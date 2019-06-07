import java.util.List;

public interface ICatClientes {

    List<String> getListClientes();

    boolean existeCliente(String s);

    void loadClientes(String fich);

    void readClientes(String fich);

    int getTamanho();
}
