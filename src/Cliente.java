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

    public static   int atoi(String str) {
        /*validate input*/
        if (str == null || str.length() == 0) return 0;
        long longRes = 0; // result can be out of range
        /*whitespaces*/
        str = str.trim(); // remove front and trailing whitespaces
        /*sign*/
        boolean neg = false; // is negative or not
        if (str.charAt(0) == '-') {
            neg = true;
            str = str.substring(1, str.length());
        } else if (str.charAt(0) == '+') {
            str = str.substring(1, str.length());
        }
        /*calculation*/
        int i = 0;
        while (i < str.length()) { // calculate without sign
            char c = str.charAt(i);
            if (c >= '0' && c <= '9') {
                longRes = longRes * 10 + (c - '0');
            } else break; // break when not a digit
            i++;
        }
        longRes = neg ? longRes * (-1) : longRes; // add sign
        /*out of range*/
        if (longRes > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else if (longRes < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }

        return (int)longRes;
    }


    public boolean validaIntCliente(){
        if(this.validaInt_Cli()){
            return (1000 <= atoi(this.codCli.substring(1))  && atoi(this.codCli.substring(1)) <= 5000);
        }
        else return false;
    }


   public boolean validaClientes(){
        if(this.compCliente_Cli()){
            return (this.validaLetras_Cli() && this.validaIntCliente());
        }
        else return false;
    }

}
