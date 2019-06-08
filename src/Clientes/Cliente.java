package Clientes;

import java.io.Serializable;
import java.util.Objects;
import static java.lang.Integer.parseInt;

/**
 * Cliente, Class que contém o código de cliente.
 *
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçalo Ferreira A84073
 */
public class Cliente implements Comparable<Cliente>, ICliente, Serializable {
    private String codCli;

    public Cliente(){
        this.codCli="";
    }

    public Cliente(String codCli) {
        this.codCli = codCli;
    }

    public Cliente(Cliente umCliente){
        this.codCli=umCliente.getCodCli();
    }

    public String getCodCli() {
        return codCli;
    }

    public void setCodCli(String codCli) {
        this.codCli = codCli;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return cliente.getCodCli().equals(this.codCli);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("codigo cliente " + this.codCli);
        return sb.toString();
    }

    public Cliente clone(){
        return new Cliente(this);
    }

    /**
     * Método que verifica se a primeira letra de um código de cliente é maiúscula
     * @return true se for maiúscula, false caso contrário
     */
    public boolean validaLetras_Cli (){
        boolean r = true;
        r=Character.isUpperCase(this.codCli.charAt(0));
        return r;
    }

    /**
     * Método que verifica se os 4 elementos a seguir à primeira letra de um código de cliente são dígitos
     * @return true se forem dígitos, false caso contrário
     */
    public boolean validaInt_Cli (){
        boolean r=true;
        for(int i=1;r && i<5;i++){
            r=Character.isDigit(this.codCli.charAt(i));
        }
        return r;
    }

    /**
     * Método que verifica se o comprimento de um código de cliente é 5
     * @return true se for 5, false caso contrário
     */
    public boolean compCliente_Cli(){
        return this.codCli.length()==5;
    }


    /**
     * Método que verifica se os 4 dígitos a seguir à primeira letra de um código de cliente representam um inteiro entre 1000 e 5000
     * @return true se representarem um inteiro entre 1000 e 5000, false caso contrário
     */
    public boolean validaIntCliente(){
        if(this.validaInt_Cli()){
            return (1000 <= parseInt(this.codCli.substring(1))  && parseInt(this.codCli.substring(1)) <= 5000);
        }
        else return false;
    }


    /**
     * Método que verifica se um cliente é válido
     * @param s código de cliente
     * @return true se for válido, false caso contrário
     */
    public static boolean validaCliente(String s){
        boolean r;
        r=s.length()==5;
        r=Character.isUpperCase(s.charAt(0));
        for(int i=1;r && i<5;i++){
            r=Character.isDigit(s.charAt(i));
        }
        r= r && (1000 <= parseInt(s.substring(1))  && parseInt(s.substring(1)) <= 5000);
        return r;
    }

    /**
     * Método que calcula o hashCode de um código de cliente
     * @return hashCode de um código de cliente
     */
    public int hashCode(){
        return this.codCli.hashCode();
    }

    /**
     * Método que compara dois clientes segundo a ordem alfabética
     * @param c cliente
     * @return 0 se forem iguais, 1 se o primeiro for lexicograficamente maior que o segundo e -1 caso contrário
     */
    public int compareTo(Cliente c){return this.codCli.compareTo(c.getCodCli());}


}
