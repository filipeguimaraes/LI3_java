import java.util.Objects;

public class Produto {
    private String codProd;

    public Produto(){
        this.codProd="";
    }

    public Produto(String codProd){
        this.codProd=codProd;
    }

    public Produto(Produto umProduto){
        this.codProd=umProduto.getCodProd();
    }

    public String getCodProd() {
        return codProd;
    }

    public void setCodProd(String codProd) {
        this.codProd = codProd;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(codProd, produto.codProd);
    }

    public String toString() {
        return "Produto{" +
                "codProd='" + codProd + '\'' +
                '}';
    }

    public Produto clone(){
        return new Produto(this);
    }

    public boolean validaLetras_Prod (){
        boolean r = true;
        for(int i=0;r && i<2;i++)
        r=Character.isUpperCase(this.codProd.charAt(i));
        return r;
    }

    public boolean validaInt_Prod (){
        boolean r=true;
        for(int i=2;r && i<6;i++){
            r=Character.isDigit(this.codProd.charAt(i));
        }
        return r;
    }

    public boolean compProduto_Prod(){
        return this.codProd.length()==6;
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


    public boolean validaIntProduto(){
        if(this.validaInt_Prod()){
            return (1000 <= atoi(this.codProd.substring(2))  && atoi(this.codProd.substring(2)) <= 9999);
        }
        else return false;
    }


    public boolean validaProdutos(){
        if(this.compProduto_Prod()){
            return (this.validaLetras_Prod() && this.validaIntProduto());
        }
        else return false;
    }



}
