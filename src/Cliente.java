import java.util.Objects;

public class Cliente {
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
        return Objects.equals(codCli, cliente.codCli);
    }

    public String toString() {
        return "Cliente{" +
                "codCli='" + codCli + '\'' +
                '}';
    }

    public Cliente clone(){
        return new Cliente(this);
    }
}
