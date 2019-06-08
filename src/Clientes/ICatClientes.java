package Clientes;

import java.util.List;

public interface ICatClientes {

    List<String> getListClientes();

    /**
     * Método que verifica se um determinado cliente existe
     * @param s código de cliente
     * @return true se existir, false caso contrário
     */
    boolean existeCliente(String s);

    /**
     * Método que recebe um ficheiro, lê e insere
     * @param fich nome do ficheiro
     */
    void loadClientes(String fich);

    /**
     * Método que adiciona os clientes válidos à estrutura
     * @param fich "Clientes.txt"
     */
    void readClientes(String fich);

    /**
     * Método que calcula o número de clientes válidos
     * @return número de clientes válidos
     */
    int getTamanho();
}
