import java.util.Objects;
import static java.lang.Integer.parseInt;

public class Cliente implements Comparable<Cliente>, ICliente{
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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("codigo cliente" + this.codCli);
        return sb.toString();
    }

    public Cliente clone(){
        return new Cliente(this);
    }

    public boolean validaLetras_Cli (){
        boolean r = true;
        r=Character.isUpperCase(this.codCli.charAt(0));
        return r;
    }

    public boolean validaInt_Cli (){
        boolean r=true;
        for(int i=1;r && i<5;i++){
            r=Character.isDigit(this.codCli.charAt(i));
        }
        return r;
    }

    public boolean compCliente_Cli(){
        return this.codCli.length()==5;
    }



    public boolean validaIntCliente(){
        if(this.validaInt_Cli()){
            return (1000 <= parseInt(this.codCli.substring(1))  && parseInt(this.codCli.substring(1)) <= 5000);
        }
        else return false;
    }


   public boolean validaCliente(){
        if(this.compCliente_Cli()){
            return (this.validaLetras_Cli() && this.validaIntCliente());
        }
        else return false;
    }

    public int compareTo(Cliente c){return this.codCli.compareTo(c.getCodCli());}


}
